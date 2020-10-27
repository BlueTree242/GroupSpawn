package com.bluetree.groupspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setspawnCommand implements CommandExecutor {
    private final Main core;

    public setspawnCommand(Main core) {
        this.core = core;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You are not a Player.");

            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "Usage: " + ChatColor.BLUE + "/" + label + " <rank/group>");
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("not-configured")) {
                core.getConfig().set("spawns." + args[0] + ".world", ((Player) sender).getLocation().getWorld().getName());
                core.getConfig().set("spawns." + args[0] + ".X", ((Player) sender).getLocation().getBlockX());
                core.getConfig().set("spawns." + args[0] + ".Y", ((Player) sender).getLocation().getBlockY());
                core.getConfig().set("spawns." + args[0] + ".Z", ((Player) sender).getLocation().getBlockZ());
                core.getConfig().set("spawns." + args[0] + ".Yaw", ((Player) sender).getLocation().getYaw());
                core.getConfig().set("spawns." + args[0] + ".Pitch", ((Player) sender).getLocation().getPitch());

                core.reloadConfigFile();
                sender.sendMessage(ChatColor.AQUA + "Added group " + ChatColor.RED + "not configured, " + ChatColor.BLUE + "For groups not configured");
                return true;
            }
            core.getConfig().set("spawns." + args[0] + ".world", ((Player) sender).getLocation().getWorld().getName());
            core.getConfig().set("spawns." + args[0] + ".X", ((Player) sender).getLocation().getBlockX());
            core.getConfig().set("spawns." + args[0] + ".Y", ((Player) sender).getLocation().getBlockY());
            core.getConfig().set("spawns." + args[0] + ".Z", ((Player) sender).getLocation().getBlockZ());
            core.getConfig().set("spawns." + args[0] + ".Yaw", ((Player) sender).getLocation().getYaw());
            core.getConfig().set("spawns." + args[0] + ".Pitch", ((Player) sender).getLocation().getPitch());

            core.reloadConfigFile();
            sender.sendMessage(ChatColor.AQUA + "Added group " + ChatColor.RED + args[0]);
        }

        return true;
    }
}
