package me.trixkz.customchatcolor.managers;

import me.trixkz.customchatcolor.CustomChatColor;
import me.trixkz.customchatcolor.commands.ChatColorCommand;

public class CommandManager {

    private CustomChatColor main = CustomChatColor.getInstance();

    public CommandManager() {
        this.registerCommands();
    }

    public void registerCommands() {
        this.main.getCommand("chatcolor").setExecutor(new ChatColorCommand());
    }
}
