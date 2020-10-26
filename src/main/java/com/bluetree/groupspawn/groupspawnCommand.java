package com.bluetree.groupspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class groupspawnCommand implements CommandExecutor {
    private final Main core;

    public groupspawnCommand(Main core) {
        this.core = core;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Running Groupspawn ver.&e" + core.getDescription().getVersion() + "&6."));
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("groupspawn.reload")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot reload the plugin."));
                    return true;
                }
                core.saveDefaultConfig();
                core.reloadConfig();
                if (core.getConfig().getConfigurationSection("spawns") == null){
                    sender.sendMessage(ChatColor.AQUA + "Groupspawn reloaded!\n" + ChatColor.RED + "Configuration is empty.");
                    return true;
                }
                core.loadConfigFile();
                sender.sendMessage(ChatColor.AQUA + "Groupspawn reloaded!");
            }else {
                sender.sendMessage(ChatColor.RED + "Invalid subcommand " + args[0] + ".");
            }
        }
    return true;
    }
}
