package me.trixkz.customchatcolor.utils;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.ChatPaginator;

public class StringUtils {

    public static String format(String string) {
        string = string.toLowerCase();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        byte b;
        int j;
        String[] arrayOfString;
        for (j = (arrayOfString = string.split("_")).length, b = 0; b < j; ) {
            String s = arrayOfString[b];
            if (i == 0) {
                builder.append(String.valueOf(Character.toUpperCase(s.charAt(0))) + s.substring(1));
            } else {
                builder.append(" " + Character.toUpperCase(s.charAt(0)) + s.substring(1));
            }
            i++;
            b++;
        }
        return builder.toString();
    }

    public static String parseConfig(String string) {
        String[] split = string.toLowerCase().split("_");
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]);
        for (int i = 1; i < split.length; i++) {
            String s = split[i];
            builder.append(String.valueOf(Character.toUpperCase(s.charAt(0))) + s.substring(1));
        }
        return builder.toString();
    }

    public static boolean contains(String string, String... contain) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = contain).length, b = 0; b < i; ) {
            String s = arrayOfString[b];
            if (string.contains(s))
                return true;
            b++;
        }
        return false;
    }

    public static boolean equals(String string, String... equal) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = equal).length, b = 0; b < i; ) {
            String s = arrayOfString[b];
            if (string.equals(s))
                return true;
            b++;
        }
        return false;
    }

    public static boolean endsWith(String string, String... end) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = end).length, b = 0; b < i; ) {
            String s = arrayOfString[b];
            if (string.endsWith(s))
                return true;
            b++;
        }
        return false;
    }

    public static void message(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void messages(Player p, String... msg) {
        Arrays.<String>asList(msg).forEach(s -> p.sendMessage(ChatColor.translateAlternateColorCodes('&', s)));
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static boolean hasWhiteSpace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        boolean found = matcher.find();
        if (found)
            return true;
        return false;
    }

    public static boolean hasSpecialChars(String s) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", 2);
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b)
            return true;
        return false;
    }

    public static boolean hasNumber(String s) {
        Pattern p = Pattern.compile("[0-9]", 2);
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b)
            return true;
        return false;
    }

    public List<String> formatLength(int numberOfChar, String s) {
        return Arrays.asList(ChatPaginator.wordWrap(s, numberOfChar));
    }

    public static boolean hasPerm(Player p, String perm) {
        if (!p.hasPermission(perm))
            return false;
        return true;
    }

    public static String alignCenter(int width, String s) {
        return String.format("%-" + width + "s", new Object[] { String.format("%" + (s.length() + (width - s.length()) / 2) + "s", new Object[] { s }) });
    }

    public static String listFormat(List<String> list, String colorCode) {
        return list.stream().map(key -> key.toString()).collect(Collectors.joining(String.valueOf(colorCode) + ", "));
    }

    public static String reset(String name) {
        return ChatColor.stripColor(name);
    }

    public static Double roundDouble(double amount) {
        return Double.valueOf((new BigDecimal(amount)).setScale(1, RoundingMode.HALF_UP).doubleValue());
    }

    public static double round(double value, int precision) {
        int scale = (int)Math.pow(10.0D, precision);
        return Math.round(value * scale) / scale;
    }

    public static void console(String string) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }

    public static String formatNumber(long number) {
        return (new DecimalFormat("###,###,###")).format(number);
    }

    public static boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null)
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
        }
        return directory.delete();
    }

    public static void broadcastGlobal(Player p, String... msg) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> !player.getUniqueId().equals(p.getUniqueId()))
                .forEach(player -> messages(player, msg));
    }

    public static void broadcastGlobalExclude(Collection<? extends UUID> excludePlayers, String... msg) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> !excludePlayers.contains(player.getUniqueId()))
                .forEach(player -> messages(player, msg));
    }

    public static void broadcastGlobalExcludes(Collection<? extends Player> excludePlayers, String... msg) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> !excludePlayers.contains(player))
                .forEach(player -> messages(player, msg));
    }

    public static void broadcastGlobalInclude(Collection<? extends UUID> includePlayers, String... msg) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> includePlayers.contains(player.getUniqueId()))
                .forEach(player -> messages(player, msg));
    }

    public static void broadcastGlobalIncludes(Collection<? extends Player> includePlayers, String... msg) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> includePlayers.contains(player))
                .forEach(player -> messages(player, msg));
    }

    enum NumberUnit {
        BILLION(1.0E9D, "B"),
        MILLION(1000000.0D, "M");

        private String format;

        private double devideUnit;

        NumberUnit(double devideUnit, String format) {
            this.devideUnit = devideUnit;
            this.format = format;
        }

        public double getDevideUnit() {
            return this.devideUnit;
        }

        public String getFormat() {
            return this.format;
        }
    }

    public static boolean compareUUID(UUID uuid1, UUID uuid2) {
        return uuid1.equals(uuid2);
    }

    public static UUID generateEmptyUUID() {
        return UUID.fromString("00000000-0000-0000-0000-000000000000");
    }
}