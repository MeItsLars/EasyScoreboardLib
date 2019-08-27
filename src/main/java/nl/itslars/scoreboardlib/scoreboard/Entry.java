package nl.itslars.scoreboardlib.scoreboard;

import nl.itslars.scoreboardlib.lines.Line;
import nl.itslars.scoreboardlib.lines.animations.Animation;
import org.bukkit.scoreboard.Team;

public class Entry {

    private Team team;
    private String entry;
    private Line line;
    private int animationIndex = 0;

    public Entry(Team team, String entry, Line line) {
        this.team = team;
        this.entry = entry;
        this.line = line;
    }

    public Team getTeam() {
        return team;
    }

    public String getEntry() {
        return entry;
    }

    public Line getLine() {
        return line;
    }

    public boolean executeNextAnimationFrame(Animation animation) {
        animationIndex = (animationIndex + 1) % animation.getDelay();
        return animationIndex == 0;
    }
}
