package me.trixkz.customchatcolor.commands;

import me.trixkz.customchatcolor.CustomChatColor;
import me.trixkz.customchatcolor.chatcolor.ColorSet;
import me.trixkz.customchatcolor.chatcolor.GradientColor;
import me.trixkz.customchatcolor.playerdata.PlayerData;
import me.trixkz.customchatcolor.utils.ColorUtils;
import me.trixkz.customchatcolor.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "chatcolor", permission = "customchatcolor.use", player = true)
public class ChatColorCommand extends BaseCommand {

    private CustomChatColor main = CustomChatColor.getInstance();

    @Override
    public void executeAs(Player player, String[] args) {
        PlayerData playerData = this.main.getPlayerDataManager().getPlayerData(player.getUniqueId());

        if (args.length == 0) {
            player.sendMessage(StringUtils.color("&8&l[&b&l!&8&l] &7Usage:"));
            player.sendMessage(StringUtils.color("&8&l[&b&l!&8&l] &7/chatcolor <color or hex code>"));
            player.sendMessage(StringUtils.color("&8&l[&b&l!&8&l] &7/chatcolor <color or hex code> <color or hex code>"));
        } else if (args.length == 1) {
            String colorCode = args[0];

            if (colorCode.contains("&")) {
                if (!ColorUtils.lookAtColorCode(colorCode)) {
                    StringUtils.message(player, "&8&l[&b&l!&8&l] &7Please use a valid chat color code");

                    return;
                }

                playerData.setChatColor(colorCode);
                playerData.setGradientColor(null);

                StringUtils.message(player, "&8&l[&b&l!&8&l] &7Your chat color has been set to: " + playerData.getChatColor() + "COLOR");
            } else if (colorCode.contains("#")) {
                if (!ColorUtils.isValidHexColorCode(colorCode) || colorCode.length() != 7) {
                    StringUtils.messages(player, "&8&l[&b&l!&8&l] &7Please use a valid hex color code");

                    return;
                }

                playerData.setChatColor(colorCode);
                playerData.setGradientColor(null);

                StringUtils.message(player, "&8&l[&b&l!&8&l] &7Your chat color has been set to: " + ChatColor.of(playerData.getChatColor()) + "COLOR");
            } else {
                StringUtils.message(player, "&8&l[&b&l!&8&l] &7Please use a valid color code");
            }
        } else if (args.length == 2) {
            String colorCodeOne = args[0];

            ColorSet<Integer, Integer, Integer> colorSetOne = null;
            ColorSet<Integer, Integer, Integer> colorSetTwo = null;

            if (colorCodeOne.contains("&")) {
                if (!ColorUtils.lookAtColorCode(colorCodeOne)) {
                    StringUtils.message(player, "&8&l[&b&l!&8&l] &7Please use a valid chat color code");

                    return;
                }

                ChatColor chatColor = ColorUtils.getColor(colorCodeOne);

                colorSetOne = new ColorSet<>(chatColor.getColor().getRed(), chatColor.getColor().getGreen(), chatColor.getColor().getBlue());
            } else if (colorCodeOne.contains("#")) {
                if (!ColorUtils.isValidHexColorCode(colorCodeOne) || colorCodeOne.length() != 7) {
                    StringUtils.messages(player, "&8&l[&b&l!&8&l] &7Please use a valid hex color code");

                    return;
                }

                colorSetOne = ColorUtils.copyColorSet(colorCodeOne);
            } else {
                StringUtils.message(player, "&8&l[&b&l!&8&l] &7Please use a valid color code");

                return;
            }

            String colorCodeTwo = args[1];

            if (colorCodeTwo.contains("&")) {
                if (!ColorUtils.lookAtColorCode(colorCodeTwo)) {
                    StringUtils.message(player, "&8&l[&b&l!&8&l] &7Please use a valid chat color code");

                    return;
                }

                ChatColor chatColor = ColorUtils.getColor(colorCodeTwo);

                colorSetTwo = new ColorSet<>(chatColor.getColor().getRed(), chatColor.getColor().getGreen(), chatColor.getColor().getBlue());
            } else if (colorCodeTwo.contains("#") || colorCodeTwo.length() != 7) {
                if (!ColorUtils.isValidHexColorCode(colorCodeTwo)) {
                    StringUtils.messages(player, "&8&l[&b&l!&8&l] &7Please use a valid hex color code");

                    return;
                }

                colorSetTwo = ColorUtils.copyColorSet(colorCodeTwo);
            } else {
                StringUtils.message(player, "&8&l[&b&l!&8&l] &7Please use a valid color code");

                return;
            }

            GradientColor gradientColor = new GradientColor(colorSetOne, colorSetTwo);

            playerData.setChatColor("");
            playerData.setGradientColor(gradientColor);

            ColorSet<Integer, Integer, Integer> colorSetOneTemporary = new ColorSet<>(playerData.getGradientColor().getColorCodeOne().getRed(), playerData.getGradientColor().getColorCodeOne().getGreen(), playerData.getGradientColor().getColorCodeOne().getBlue());
            ColorSet<Integer, Integer, Integer> colorSetTwoTemporary = new ColorSet<>(playerData.getGradientColor().getColorCodeTwo().getRed(), playerData.getGradientColor().getColorCodeTwo().getGreen(), playerData.getGradientColor().getColorCodeTwo().getBlue());

            StringUtils.message(player, "&8&l[&b&l!&8&l] &7Your chat color has been set to: " + ColorUtils.getGradientString("GRADIENT", ColorUtils.getColorCodes("GRADIENT", colorSetOneTemporary, colorSetTwoTemporary)));
        }
    }

    @Override
    public void executeAs(CommandSender sender, String[] args) {

    }
}
