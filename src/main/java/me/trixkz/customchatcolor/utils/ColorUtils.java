package me.trixkz.customchatcolor.utils;

import lombok.Getter;
import lombok.Setter;
import me.trixkz.customchatcolor.chatcolor.ColorSet;
import net.md_5.bungee.api.ChatColor;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class ColorUtils {

    private static Map<ChatColor, ColorSet<Integer, Integer, Integer>> colorMap = new HashMap<>();

    public ColorUtils() {
        this.registerColors();
    }

    public void registerColors() {
        colorMap.put(ChatColor.BLACK, new ColorSet<>(0, 0, 0));
        colorMap.put(ChatColor.DARK_BLUE, new ColorSet<>(0, 0, 170));
        colorMap.put(ChatColor.DARK_GREEN, new ColorSet<>(0, 170, 0));
        colorMap.put(ChatColor.DARK_AQUA, new ColorSet<>(0, 170, 170));
        colorMap.put(ChatColor.DARK_RED, new ColorSet<>(170, 0, 0));
        colorMap.put(ChatColor.DARK_PURPLE, new ColorSet<>(170, 0, 170));
        colorMap.put(ChatColor.GOLD, new ColorSet<>(255, 170, 0));
        colorMap.put(ChatColor.GRAY, new ColorSet<>(170, 170, 170));
        colorMap.put(ChatColor.DARK_GRAY, new ColorSet<>(85, 85, 85));
        colorMap.put(ChatColor.BLUE, new ColorSet<>(85, 85, 255));
        colorMap.put(ChatColor.GREEN, new ColorSet<>(85, 255, 85));
        colorMap.put(ChatColor.AQUA, new ColorSet<>(85, 255, 255));
        colorMap.put(ChatColor.RED, new ColorSet<>(255, 85, 85));
        colorMap.put(ChatColor.LIGHT_PURPLE, new ColorSet<>(255, 85, 255));
        colorMap.put(ChatColor.YELLOW, new ColorSet<>(255, 255, 85));
        colorMap.put(ChatColor.WHITE, new ColorSet<>(255, 255, 255));
    }

    public static ChatColor getColor(String colorCode) {
        byte b;

        int i;

        ChatColor[] arrayOfChatColor;

        for (i = (arrayOfChatColor = ChatColor.values()).length, b = 0; b < i; ) {
            ChatColor colors = arrayOfChatColor[b];

            String colorsDecode = untranslate(colors.toString());

            if (colorCode.equalsIgnoreCase(colorsDecode)) {
                return colors;
            }

            b++;
        }

        return null;
    }

    public static boolean lookAtColorCode(String colorCode) {
        byte b;

        int i;

        ChatColor[] arrayOfChatColor;

        for (i = (arrayOfChatColor = ChatColor.values()).length, b = 0; b < i; ) {
            ChatColor colors = arrayOfChatColor[b];

            String colorsDecode = untranslate(colors.toString());

            if (colorCode.equalsIgnoreCase(colorsDecode)) {
                return true;
            }

            b++;
        }

        return false;
    }

    public static ColorSet<Integer, Integer, Integer> copyColorSet(String colorCode) {
        Color color = hexColorCodesToRGBColorCodes(colorCode);

        return new ColorSet(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static String getGradientString(String string, java.util.List<String> colorCodes) {
        String[] split = string.split("");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < colorCodes.size(); i++) {
            stringBuilder.append(ChatColor.of(colorCodes.get(i)) + split[i]);
        }

        return stringBuilder.toString();
    }

    public static List<String> getColorCodes(String text, ColorSet<Integer, Integer, Integer> colorSetOne, ColorSet<Integer, Integer, Integer> colorSetTwo) {
        List<String> colorCodes = new ArrayList<>();

        int red = ((colorSetOne.getRed() < colorSetTwo.getRed()) ? (colorSetTwo.getRed() - colorSetOne.getRed()) : (colorSetOne.getRed() - colorSetTwo.getRed())) / text.length();
        int green = ((colorSetOne.getGreen() < colorSetTwo.getGreen()) ? (colorSetTwo.getGreen() - colorSetOne.getGreen()) : (colorSetOne.getGreen() - colorSetTwo.getGreen())) / text.length();
        int blue = ((colorSetOne.getBlue() < colorSetTwo.getBlue()) ? (colorSetTwo.getBlue() - colorSetOne.getBlue()) : (colorSetOne.getBlue() - colorSetTwo.getBlue())) / text.length();

        for (int i = 0; i < text.length(); i++) {
            colorSetOne.setRed((colorSetOne.getRed() <= colorSetTwo.getRed()) ? (colorSetOne.getRed() + red) : (colorSetOne.getRed() - red));
            colorSetOne.setGreen((colorSetOne.getGreen() <= colorSetTwo.getGreen()) ? (colorSetOne.getGreen() + green) : (colorSetOne.getGreen() - green));
            colorSetOne.setBlue((colorSetOne.getBlue() <= colorSetTwo.getBlue()) ? (colorSetOne.getBlue() + blue) : (colorSetOne.getBlue() - blue));

            String hex = String.format("#%02x%02x%02x", colorSetOne.getRed(), colorSetOne.getGreen(), colorSetOne.getBlue());

            colorCodes.add(hex);
        }

        return colorCodes;
    }

    public static String translate(String string) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

        for (Matcher matcher = pattern.matcher(string); matcher.find(); matcher = pattern.matcher(string)) {
            String color = string.substring(matcher.start(), matcher.end());

            string = string.replace(color, ChatColor.of(color) + "");
        }

        string = ChatColor.translateAlternateColorCodes('&', string);

        return string;
    }

    public static Color hexColorCodesToRGBColorCodes(String string) {
        return new Color(Integer.valueOf(string.substring(1, 3),16), Integer.valueOf(string.substring(3, 5),16), Integer.valueOf(string.substring(5, 7),16));
    }

    public static boolean isValidHexColorCode(String string) {
        Pattern pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    public static String untranslate(String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == '§' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
            b[i] = '&';
            b[i + 1] = Character.toLowerCase(b[i + 1]);
        }
    }
        return new String(b);
    }
}
