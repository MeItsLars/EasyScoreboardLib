package nl.itslars.scoreboardlib.lines.animations;

public class Blinker implements Animation {

    private int delay;
    private int counter = 0;
    private String[] lines;

    public Blinker(int delay, String... lines) {
        this.delay = delay;
        this.lines = lines;
    }

    @Override
    public String next() {
        if(counter >= lines.length) counter = 0;
        return lines[counter++];
    }

    @Override
    public int getDelay() {
        return delay;
    }
}
