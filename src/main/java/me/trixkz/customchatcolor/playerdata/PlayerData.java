package me.trixkz.customchatcolor.playerdata;

import lombok.Getter;
import lombok.Setter;
import me.trixkz.customchatcolor.CustomChatColor;
import me.trixkz.customchatcolor.chatcolor.GradientColor;
import me.trixkz.customchatcolor.managers.PlayerDataManager;
import java.util.UUID;

@Getter
@Setter
public class PlayerData {

    private PlayerDataManager playerDataManager = CustomChatColor.getInstance().getPlayerDataManager();

    private UUID uniqueId;

    private String chatColor = "";
    private GradientColor gradientColor = null;

    public PlayerData(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }
}
