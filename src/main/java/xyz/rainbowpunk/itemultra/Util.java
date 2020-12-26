package xyz.rainbowpunk.itemultra;

import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Util {
    public static String resourceToString(String path) {
        InputStream input = Util.class.getResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.joining("\n"));
    }

    public static String translateColor(String string) {
        return translateColor('&', string);
    }

    public static String translateColor(char c, String string) {
        return ChatColor.translateAlternateColorCodes(c, string);
    }
}
