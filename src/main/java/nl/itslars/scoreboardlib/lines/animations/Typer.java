package nl.itslars.scoreboardlib.lines.animations;

public class Typer implements Animation {

    private int delay;
    private String[] texts;
    private int textIndex = 0;

    private int shownIndex = 0;
    private int direction = 1;
    private boolean typer = false;

    public Typer(int delay, String... texts) {
        this.delay = delay;
        if(texts.length == 0) throw new IllegalArgumentException("At least 1 text must be provided.");
        this.texts = texts;
    }

    @Override
    public String next() {
        String shownText = "";
        String text = texts[textIndex];
        int currentIndex = shownIndex;
        if(text.startsWith("§") && text.length() > 1) {
            currentIndex = shownIndex - 2;
            if(currentIndex < 0) currentIndex = 0;
            shownText = text.substring(0, 2);
            text = text.substring(2);
        }

        shownText += text.substring(0, currentIndex);
        if(shownText.endsWith("§")) shownText = shownText.substring(0, shownText.length() - 1);

        shownIndex += direction;

        if(shownIndex == texts[textIndex].length()) direction = -1;
        else if(shownIndex == 0) {
            direction = 1;
            textIndex++;
            if(textIndex == texts.length) textIndex = 0;
        }

        if(typer) shownText += "§f_";
        typer = !typer;

        return shownText;
    }

    @Override
    public int getDelay() {
        return delay;
    }
}
