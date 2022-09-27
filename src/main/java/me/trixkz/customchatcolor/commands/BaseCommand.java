package me.trixkz.customchatcolor.commands;

import me.trixkz.customchatcolor.CustomChatColor;
import me.trixkz.customchatcolor.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand implements CommandExecutor {

    private CustomChatColor main = CustomChatColor.getInstance();

    private CommandInfo commandInfo;

    public BaseCommand() {
        this.commandInfo = this.getClass().getDeclaredAnnotation(CommandInfo.class);
    }

    public abstract void executeAs(Player player, String[] args);

    public abstract void executeAs(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!this.commandInfo.permission().equals("")) {
            if (!sender.hasPermission(this.commandInfo.permission())) {
                sender.sendMessage(StringUtils.color("&cNo permission"));

                return true;
            }
        }

        if (this.commandInfo.player()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(StringUtils.color("&cYou have to be a player to do this"));

                return true;
            }

            Player player = (Player) sender;

            this.executeAs(player, args);

            return true;
        }

        this.executeAs(sender, args);

        return true;
    }
}