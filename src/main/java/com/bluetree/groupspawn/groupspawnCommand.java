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
            if (sender.hasPermission("groupspawn.updatechecker")) {
                new UpdateChecker(core, 12345).getVersion(version -> {
                    if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                    } else {
                        sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + " https://bit.ly/2TzELra") ;

                    }
                });
            }
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
                    if (sender.hasPermission("groupspawn.updatechecker")) {
                        new UpdateChecker(core, 12345).getVersion(version -> {
                            if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                            } else {
                                sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + "https://bit.ly/2TzELra") ;

                            }
                        });
                    }
                    return true;
                }
                core.loadConfigFile();
                sender.sendMessage(ChatColor.AQUA + "Groupspawn reloaded!");
                if (sender.hasPermission("groupspawn.updatechecker")) {
                    new UpdateChecker(core, 12345).getVersion(version -> {
                        if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                        } else {
                            sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). https://bit.ly/2TzELra");

                        }
                    });
                }
            }else {
                sender.sendMessage(ChatColor.RED + "Invalid subcommand " + args[0] + ".");
            }
        }
    return true;
    }
}
