package dev.patika.plus.util;

public class Html {
    private final String htmlStart = "<html><body>";
    private final String htmlEnd = "</body></html>";
    private String body = "";

    public static Html form() {
        return new Html();
    }

    public Html add(String bodyCode) {
        this.body += bodyCode;
        return this;
    }

    public Html newline() {
        this.body += "<br>";
        return this;
    }

    public Html addBold(String text) {
        this.body += "<b>" + text + "</b>";
        return this;
    }

    public Html addItalic(String text) {
        this.body += "<i>" + text + "</i>";
        return this;
    }

    public Html addUnderline(String text) {
        this.body += "<u>" + text + "</u>";
        return this;
    }

    public Html addHeading(String text, int level) {
        this.body += "<h" + level + ">" + text + "</h" + level + ">";
        return this;
    }

    public Html addParagraph(String text) {
        this.body += "<p>" + text + "</p>";
        return this;
    }

    public Html startParagraph() {
        this.body += "<p>";
        return this;
    }

    public Html endParagraph() {
        this.body += "</p>";
        return this;
    }

    @Override
    public String toString() {
        return this.htmlStart + this.body + this.htmlEnd;
    }

}
