package nl.itslars.scoreboardlib.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class LineParser {
    public static String[] parseText(String text) {
        String[] result = new String[2];

        if(text.length() <= 16) {
            result[0] = text;
            result[1] = "";
        } else {
            String prefix = text.substring(0, 16);
            StringBuilder suffix = new StringBuilder();

            if(prefix.endsWith("§")) {
                if(ChatColor.getByChar(text.charAt(16)).isColor()) {
                    suffix.append('§');
                    prefix = prefix.substring(0, 15);
                } else {
                    prefix = prefix.substring(0, 15);

                    List<ChatColor> colors = getLastColors(prefix);
                    for(int i = colors.size() - 1; i >= 0; i--) {
                        ChatColor color = colors.get(i);
                        suffix.append(color);
                    }

                    suffix.append('§');
                }
            } else {
                List<ChatColor> colors = getLastColors(prefix);
                for(int i = colors.size() - 1; i >= 0; i--) {
                    ChatColor color = colors.get(i);
                    suffix.append(color);
                }
            }
            suffix.append(text.substring(16));
            String suffixString = suffix.toString();
            suffixString = suffixString.substring(0, Math.min(suffixString.length(), 16));
            result[0] = prefix;
            result[1] = suffixString;
        }

        return result;
    }

    private static List<ChatColor> getLastColors(String text) {
        List<ChatColor> colors = new ArrayList<>();

        char previousChar = '\0';
        for(int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if(c == '§' && previousChar != '\0') {
                ChatColor color = ChatColor.getByChar(previousChar);
                if(color == ChatColor.RESET) return colors;

                colors.add(color);
                if(color.isColor()) return colors;
            } else {
                previousChar = c;
            }
        }

        return colors;
    }
}
