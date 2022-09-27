package me.trixkz.customchatcolor;

import lombok.Getter;
import lombok.Setter;
import me.trixkz.customchatcolor.listeners.PlayerChatListener;
import me.trixkz.customchatcolor.listeners.PlayerDataListener;
import me.trixkz.customchatcolor.managers.CommandManager;
import me.trixkz.customchatcolor.managers.PlayerDataManager;
import me.trixkz.customchatcolor.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;

@Getter
@Setter
public class CustomChatColor extends JavaPlugin {

    @Getter public static CustomChatColor instance;

    private CommandManager commandManager;
    private PlayerDataManager playerDataManager;

    public void onEnable() {
        instance = this;

        Bukkit.getConsoleSender().sendMessage("------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&dCustomChatColor &8- &av" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Made by z&eTrixkz"));
        Bukkit.getConsoleSender().sendMessage("------------------------------------------------");

        this.loadManagers();
        this.loadListeners();

        new ColorUtils();
    }

    public void onDisable() {
        for (Player player : this.getServer().getOnlinePlayers()) {
            this.playerDataManager.deletePlayer(player.getUniqueId());
        }

        instance = null;
    }

    private void loadManagers() {
        this.commandManager = new CommandManager();
        this.playerDataManager = new PlayerDataManager();
    }

    private void loadListeners() {
        Arrays.asList(
                new PlayerChatListener(),
                new PlayerDataListener()
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }
}
