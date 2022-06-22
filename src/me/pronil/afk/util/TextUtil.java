package me.pronil.afk.util;


import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {
    public static String colorize(String s) {
        return (s == null) ? " " : ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> colorize(List<String> list) {
        List<String> newList = new ArrayList<>();
        list.forEach(s -> newList.add(colorize(s)));
        return newList;
    }
}
