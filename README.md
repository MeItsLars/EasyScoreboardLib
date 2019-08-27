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

**Animations**

In the scoreboard, you can add animations. You can also create your own animations!

Adding animations:
To add an animation, you have to create a new instance of an object that implements the _animation_ interface. In this library, 3 animations have been provided:
- Scroller
- Typer
- Blinker

The scroller scrolls texts from left to right.
The typer 'types' texts.
The blinker shows texts one after another.

You can use these animations like this:

```scoreboard.setLineText(0, new Blinker(20, "Message A", "Message B", "Message C"));```

Every animation has its own unique constructor, but they all implement the _animation_ interface:

```
public interface Animation extends Line {
    String next();
    int getDelay();
}
```

You can create your own animations!
This is an example of an animation that will show a string, and then reverse it.

```
public class Reverser implements Animation {

    private int delay;
    private String text;
    private boolean reversed = false;

    public Reverser(int delay, String text) {
        this.delay = delay;
        this.text = text;
    }

    @Override
    public String next() {
        String result;

        if(reversed) result = text;
        else {
            StringBuilder stringBuilder = new StringBuilder(text);
            stringBuilder.reverse();
            result = stringBuilder.toString();
        }

        reversed = !reversed;
        return result;
    }

    @Override
    public int getDelay() {
        return delay;
    }
}
```

If you have any questions, join the support discord:
https://discordapp.com/invite/CGkdxsg

You are free to use this software in any or all of your projects, but don't claim this software is yours.
You don't have to give credit, but it would be appreciated :)
