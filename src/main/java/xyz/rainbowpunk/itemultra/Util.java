package xyz.rainbowpunk.itemultra;

import org.bukkit.ChatColor;

import java.io.InputStream;

public class Util {
    public static String resourceToString(String path) {
        InputStream input = Util.class.getResourceAsStream(path);
//        String string = String
        return null; //todo: doto
    }

    public static String translateColor(String string) {
        return translateColor('&', string);
    }

    public static String translateColor(char c, String string) {
        return ChatColor.translateAlternateColorCodes(c, string);
    }
}
