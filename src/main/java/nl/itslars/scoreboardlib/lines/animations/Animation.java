package nl.itslars.scoreboardlib.lines.animations;

import nl.itslars.scoreboardlib.lines.Line;

public interface Animation extends Line {
    String next();
    int getDelay();
}
