package nl.itslars.scoreboardlib.scoreboard;

import nl.itslars.scoreboardlib.EasyScoreboardLib;
import nl.itslars.scoreboardlib.lines.Line;
import nl.itslars.scoreboardlib.lines.TextLine;
import nl.itslars.scoreboardlib.lines.animations.Animation;
import nl.itslars.scoreboardlib.util.LineParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EasyScoreboard {

    private Player player;
    private long updateSpeed = 1L;
    private BukkitTask activeTask;

    private Scoreboard scoreboard;
    private Objective objective;

    private Map<Integer, Entry> entries = new HashMap<>();
    private Line title;
    private int titleAnimationIndex = 0;

    private String teamEntries = "abcdefghijklmnopqrstuvwxyz0123456789";

    public EasyScoreboard(Player player, Line title) {
        this.player = player;
        this.title = title;
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("scoreboard", "dummy");
        objective.setDisplayName(title.next());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void enable() {
        player.setScoreboard(scoreboard);
        update();
    }

    public void disable() {
        activeTask.cancel();
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void setUpdateSpeed(int updateSpeed) {
        this.updateSpeed = updateSpeed;
        activeTask.cancel();
        update();
    }

    private void update() {
        activeTask = new BukkitRunnable() {
            @Override
            public void run() {
                updateBoard();
            }
        }.runTaskTimerAsynchronously(EasyScoreboardLib.getInstance(), 0, updateSpeed);
    }

    private void updateBoard() {
        if(!player.isOnline()) {
            disable();
            return;
        }
        
        for(Map.Entry<Integer, Entry> mapEntry : entries.entrySet()) {
            int score = mapEntry.getKey();
            Entry entry = mapEntry.getValue();

            Line line = entry.getLine();
            Team team = entry.getTeam();

            boolean execute = true;
            if(line instanceof Animation) {
                if(!entry.executeNextAnimationFrame((Animation) line)) execute = false;
            }

            if(execute) {
                String text = line.next();
                String[] parsedLine = LineParser.parseText(text);

                team.setPrefix(parsedLine[0]);
                team.setSuffix(parsedLine[1]);

                objective.getScore(entry.getEntry()).setScore(score);
            }
        }

        if(title instanceof Animation) {
            titleAnimationIndex = (titleAnimationIndex + 1) % ((Animation) title).getDelay();
            if(titleAnimationIndex == 0) objective.setDisplayName(title.next());
        }
    }

    public void setLineText(int score, String text) {
        setLineText(score, new TextLine(text));
    }

    public void setLineText(int score, Line line) {
        Team team = scoreboard.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));
        String teamEntry = "§" + teamEntries.charAt(entries.size()) + "§r";
        team.addEntry(teamEntry);

        Entry entry = new Entry(team, teamEntry, line);
        entries.put(score, entry);
    }
}