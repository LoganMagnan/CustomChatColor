package me.trixkz.customchatcolor.listeners;

import me.trixkz.customchatcolor.CustomChatColor;
import me.trixkz.customchatcolor.chatcolor.ColorSet;
import me.trixkz.customchatcolor.playerdata.PlayerData;
import me.trixkz.customchatcolor.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private CustomChatColor main = CustomChatColor.getInstance();

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        PlayerData playerData = this.main.getPlayerDataManager().getPlayerData(player.getUniqueId());

        String message = event.getMessage();

        if (playerData.getGradientColor() == null) {
            event.setMessage(ColorUtils.translate(playerData.getChatColor() + message));
        } else {
            ColorSet<Integer, Integer, Integer> colorSetOneTemporary = new ColorSet<>(playerData.getGradientColor().getColorCodeOne().getRed(), playerData.getGradientColor().getColorCodeOne().getGreen(), playerData.getGradientColor().getColorCodeOne().getBlue());
            ColorSet<Integer, Integer, Integer> colorSetTwoTemporary = new ColorSet<>(playerData.getGradientColor().getColorCodeTwo().getRed(), playerData.getGradientColor().getColorCodeTwo().getGreen(), playerData.getGradientColor().getColorCodeTwo().getBlue());

            event.setMessage(ColorUtils.getGradientString(message, ColorUtils.getColorCodes(message, colorSetOneTemporary, colorSetTwoTemporary)));
        }
    }
}
