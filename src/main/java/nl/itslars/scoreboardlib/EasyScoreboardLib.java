package nl.itslars.scoreboardlib;

import nl.itslars.scoreboardlib.lines.Line;
import nl.itslars.scoreboardlib.lines.TextLine;
import nl.itslars.scoreboardlib.scoreboard.EasyScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyScoreboardLib extends JavaPlugin {

    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static EasyScoreboard createScoreboard(Player player, Line line) {
        if(instance == null) {
            throw new IllegalArgumentException("The library has not been initialized. Use 'EasyScoreboardLib.initialize(JavaPlugin instance)' to initialize the library.");
        }
        return new EasyScoreboard(player, line);
    }

    public static EasyScoreboard createScoreboard(Player player, String title) {
        return createScoreboard(player, new TextLine(title));
    }
}