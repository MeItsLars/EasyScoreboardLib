# EasyScoreboardLib
Easy library for Spigot scoreboards. Intended for Minecraft 1.8-1.12.

This is a free scoreboard library, without flicker.

**Features:**
- No flicker!
- Up to 32 characters per line!
- Multiple lines with the same text!
- Animations on your scoreboard!
- Easy library access, with the possibility to create your own animations!
- Runs asynchronously!

**Installation:**
1) Download EasyScoreboardLib.jar from the spigot page: https://www.spigotmc.org/resources/easyscoreboardlib.70806/
2) Import it into your project, and add it to 'depend' in your 'plugin.yml':
    ```depend: [EasyScoreboardLib]```
3) You are ready to use it!

**Usage:**
Step 1: Create your scoreboard:
```EasyScoreboard scoreboard = EasyScoreboardLib.createScoreboard(player, "§cTest Scoreboard");```

Step 2: Add lines:
```
scoreboard.setLineText(11, "§m--------------------");
scoreboard.setLineText(10, "§eYour name:");
scoreboard.setLineText(9, "§c" + player.getName());
scoreboard.setLineText(8, "");
scoreboard.setLineText(7, "§eYour money:");
scoreboard.setLineText(6, "§c0");
scoreboard.setLineText(5, "");
scoreboard.setLineText(4, "§eNews:");
scoreboard.setLineText(3, new Scroller(2, 25, "§eWelcome to the server. We have a lot of cool gamemodes!"));
scoreboard.setLineText(2, "");
scoreboard.setLineText(1, "§7Double lines!");
scoreboard.setLineText(0, "§7Double lines!");
scoreboard.setLineText(-1, "§m--------------------");
scoreboard.setLineText(-2, "  §e§oplay.yourserver.com");
```

Step 3: Don't forget to enable the scoreboard!
```scoreboard.enable();```
