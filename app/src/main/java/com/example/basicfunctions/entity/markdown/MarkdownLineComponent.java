package com.example.basicfunctions.entity.markdown;


public class MarkdownLineComponent {
    private MarkdownComponentType type;
    private String text;
    private String imagePath;

    public MarkdownLineComponent() {
        type = MarkdownComponentType.Text;
    }

    /**
     * Set this {@link MarkdownLineComponent} as Text.
     *
     * @param str content of the text
     *
     * @return This {@link MarkdownLineComponent}
     */
    public MarkdownLineComponent asText(String str) {
        type = MarkdownComponentType.Text;
        text = str;
        return this;
    }

    /**
     * Set this {@link MarkdownLineComponent} as Hyperlink.
     *
     * @param link hyperlink address
     *
     * @return This {@link MarkdownLineComponent}
     */
    public MarkdownLineComponent asHyperlink(String link) {
        type = MarkdownComponentType.Hyperlink;
        text = link;
        return this;
    }

    public String getText() {
        return text;
    }

    /**
     * Set this {@link MarkdownLineComponent} as an Image.
     *
     * @param path path to the Image
     * @param alt alternative name of the Image
     *
     * @return This {@link MarkdownLineComponent}
     */
    public MarkdownLineComponent asImage(String path, String alt) {
        type = MarkdownComponentType.Image;
        imagePath = path;
        text = alt;
        return this;
    }

    public MarkdownComponentType getType() {
        return type;
    }

    public void setImagePath(String path) {
        imagePath = path;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImageAlt(String alt) {
        text = alt;
    }

    public String getImageAlt() {
        return text;
    }

    public enum MarkdownComponentType {
        Text,
        BoldText,
        ItalicText,
        Hyperlink,
        Image
    }
}
