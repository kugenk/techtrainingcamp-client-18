package com.example.utils;

import com.example.basicfunctions.entity.markdown.MarkdownLine;
import com.example.basicfunctions.entity.markdown.MarkdownLineComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownParser {

    private final Pattern titlePattern;
    private final Pattern imagePattern;
    private final Pattern hyperlinkPattern;

    public MarkdownParser() {
        titlePattern = Pattern.compile("^(#+)");
        imagePattern = Pattern.compile("!\\[([^\\]]*)\\]\\(([^)]+)\\)");
        hyperlinkPattern = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    public List<MarkdownLine> parseArticleData(String data) {
        return parseTextLines(Arrays.asList(data.split("\\r?\\n")));
    }

    /**
     * Parse text lines to list of {@link MarkdownLine}.
     *
     * @param texts List of String
     * @return List of {@link MarkdownLine}
     */
    public List<MarkdownLine> parseTextLines(List<String> texts) {
        List<MarkdownLine> res = new ArrayList<>();
        for (String s : texts) {
            res.add(parseTextLine(s));
        }
        return res;
    }

    /**
     * Parse a String to {@link MarkdownLine}.
     *
     * @param text String to parse
     * @return {@link MarkdownLine}
     */
    public MarkdownLine parseTextLine(String text) {
        MarkdownLine mdLine = new MarkdownLine();
        setParseResult(text, mdLine);
        return mdLine;
    }

    /**
     * Get the title level indicated by # at the start of the text (as defined in Markdown syntax).
     *
     * @param text Input String.
     * @return level of title of given String. 0 for plain text.
     */
    public int getTitleLevel(String text) {
        int titleLevel = 0;
        if (text != null) {
            Matcher m = titlePattern.matcher(text);
            if (m.find()) {
                titleLevel = Objects.requireNonNull(m.group(0)).length();
            }
        }
        return titleLevel;
    }

    protected void setParseResult(String text, MarkdownLine line) {
        boolean isFirstText = true;
        int pos = 0;
        String substring = "";

        if (text != null) {
            int textLen = text.length();
            Matcher m = imagePattern.matcher(text);

            while (pos < textLen) {
                if (m.find()) {
                    if (m.start() > pos) {
                        substring = text.substring(pos, m.start());
                        if (isFirstText) {
                            substring = substring.replaceAll("^\\s+", "");
                            int titleLevel = getTitleLevel(substring);
                            substring = processFirstTextBlock(substring, titleLevel);
                            line.setFormatLevel(titleLevel);
                            isFirstText = false;
                        }
                        setTextParseWithHyperlink(substring, line);
                    }
                    line.add(new MarkdownLineComponent().asImage(m.group(2), m.group(1)));
                    pos = m.end() + 1;
                } else {
                    // process remaining characters
                    substring = text.substring(pos);
                    substring = substring.replaceAll("^\\s+", "");
                    if (isFirstText) {
                        int titleLevel = getTitleLevel(substring);
                        substring = processFirstTextBlock(substring, titleLevel);
                        line.setFormatLevel(titleLevel);
                    }
                    setTextParseWithHyperlink(substring, line);
                    break;
                }
            }
        }
    }

    private String leftStripOneSpace(String str) {
        if (str.charAt(0) == ' ') {
            return str.substring(1);
        }
        return str;
    }

    private String processFirstTextBlock(String text, int titleLevel) {
        String sharpRemoved = text.substring(titleLevel);
        if (titleLevel > 0) {
            sharpRemoved =  leftStripOneSpace(sharpRemoved);
        }
        return sharpRemoved;
    }

    private void setTextParseWithHyperlink(String text, MarkdownLine line) {
        int pos = 0;
        if (text != null) {
            int textLen = text.length();
            Matcher m = hyperlinkPattern.matcher(text);

            while (pos < textLen) {
                if (m.find()) {
                    if (m.start() > pos) {
                        line.add(
                                new MarkdownLineComponent().asText(text.substring(pos, m.start()))
                        );
                    }
                    line.add(new MarkdownLineComponent().asHyperlink(m.group(0)));
                    pos = m.end() + 1;
                } else {
                    line.add(new MarkdownLineComponent().asText(text.substring(pos)));
                    break;
                }
            }
        }
    }
}
