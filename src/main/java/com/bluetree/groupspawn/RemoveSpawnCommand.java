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
            core.getConfig().set("spawns." + args[0], (null));
            core.reloadConfigFile();
            sender.sendMessage(ChatColor.AQUA + "Removed group " + ChatColor.RED + args[0]);
        }

        return true;
    }
}