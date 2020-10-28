package com.bluetree.groupspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveSpawnCommand implements CommandExecutor {
    private final Main core;

    public RemoveSpawnCommand(Main core) {
        this.core = core;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You are not a Player.");
            core.spawns.clear();
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "Usage: " + ChatColor.BLUE + "/" + label + " <rank/group>");
            return true;
        } else if (args.length == 1) {
            if (core.getConfig().getConfigurationSection("spawns." + args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "Spawn " + args[0] + " does not exist in the config.");
                return true;
            }
            core.getConfig().set("spawns." + args[0], (null));
            core.reloadConfigFile();
            sender.sendMessage(ChatColor.AQUA + "Removed group " + ChatColor.RED + args[0]);
            if (sender.hasPermission("groupspawn.updatechecker")) {
                new UpdateChecker(core, 12345).getVersion(version -> {
                    if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                    } else {

                        sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + " https://bit.ly/2TzELra") ;


                    }
                });
            }
        }

        return true;
    }
}