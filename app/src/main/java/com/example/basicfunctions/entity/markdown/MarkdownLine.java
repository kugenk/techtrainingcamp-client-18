package com.example.basicfunctions.entity.markdown;

import java.util.ArrayList;
import java.util.List;


public class MarkdownLine {
    private final List<MarkdownLineComponent> components;
    private int formatLevel; // level of the title (0 - plain, 1 - primary, 2 - secondary, ...)

    public void setFormatLevel(int level) {
        formatLevel = level;
    }

    public int getFormatLevel() {
        return formatLevel;
    }

    public MarkdownLine() {
        components = new ArrayList<>();
        formatLevel = 0;
    }

    public List<MarkdownLineComponent> getComponents() {
        return components;
    }

    public int getSize() {
        return components.size();
    }

    /**
     * Add a {@link MarkdownLineComponent} to the {@link MarkdownLine}.
     *
     * @param comp {@link MarkdownLineComponent} to add
     */
    public void add(MarkdownLineComponent comp) {
        if (comp != null) {
            components.add(comp);
        }
    }

    public enum MarkdownLineType {
        TitleLine,
        NormalLine,
        NumberedListLine,
        PlainListLine,
        CodeBlock
    }
}
