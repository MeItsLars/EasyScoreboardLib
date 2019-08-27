package nl.itslars.scoreboardlib.lines;

public class TextLine implements Line {

    private String text;

    public TextLine(String text) {
        this.text = text;
    }

    @Override
    public String next() {
        return text;
    }
}
