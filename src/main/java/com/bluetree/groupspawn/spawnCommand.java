package com.bluetree.groupspawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnCommand implements CommandExecutor {
    private final Main core;

    public spawnCommand(Main core) {
        this.core = core;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                Location destination = core.spawns.get(core.getVault().getPrimaryGroup(((Player) sender).getPlayer()));
                if (destination == null) {
                    Location defloc = core.spawns.get("not-configured");
                    if (defloc == null) {
                        sender.sendMessage(ChatColor.RED + "There is no spawn for your group or for non-configured groups.");
                        return true;

                    }
                    ((Player) sender).teleport(defloc);
                    sender.sendMessage(ChatColor.GOLD + "Teleporting...");
                    if (sender.hasPermission("groupspawn.updatechecker")) {
                        new UpdateChecker(core, 12345).getVersion(version -> {
                            if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                            } else {
                                sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + " https://bit.ly/2TzELra") ;

                            }
                        });
                    }
                    return true;


                }
                ((Player) sender).teleport(destination);
                sender.sendMessage(ChatColor.GOLD + "Teleporting...");
                if (sender.hasPermission("groupspawn.updatechecker")) {
                    new UpdateChecker(core, 12345).getVersion(version -> {
                        if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                        } else {
                            sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + " https://bit.ly/2TzELra") ;

                        }
                    });
                }

                return true;


            } else if (args.length == 1) {
                if (!sender.hasPermission("groupspawn.spawn.othergroup")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to teleport to other group's spawn.");
                    return true;
                }
                if (core.getConfig().getConfigurationSection("spawns." + args[0]) == null) {
                    sender.sendMessage(ChatColor.RED + "Spawn " + args[0] + " was not found");

                } else {
                    Location destination = core.spawns.get(args[0]);
                    if (destination == null) {
                        sender.sendMessage(ChatColor.RED + "Spawn " + args[0] + " was not found");
                        return true;
                    } else {
                        ((Player) sender).getPlayer().teleport(destination);
                        sender.sendMessage(ChatColor.AQUA + "Teleported to spawn for group " + args[0]);

                    }

                }
            } else if (args.length == 2) {
                if (sender.hasPermission("groupspawn.spawn.other")) {
                    Player target = Bukkit.getPlayerExact(args[1]);

                    if (target == null) {
                        sender.sendMessage(ChatColor.RED + "Player is not online.");
                    } else {
                        Location destination = core.spawns.get(args[0]);
                        if (destination == null) {
                            Location defloc = core.spawns.get("not-configured");
                            if (defloc == null) {
                                sender.sendMessage(ChatColor.RED + "There is no spawn for this group or for non-configured groups.");
                                return true;

                            }
                            target.teleport(defloc);
                            sender.sendMessage(ChatColor.GOLD + "Teleporting...");
                            if (sender.hasPermission("groupspawn.updatechecker")) {
                                new UpdateChecker(core, 12345).getVersion(version -> {
                                    if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                                    } else {
                                        sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + " https://bit.ly/2TzELra") ;

                                    }
                                });
                            }
                            return true;


                        } else {
                            target.teleport(destination);
                            sender.sendMessage(ChatColor.GOLD + "Teleporting...");
                            if (sender.hasPermission("groupspawn.updatechecker")) {
                                new UpdateChecker(core, 12345).getVersion(version -> {
                                    if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                                    } else {
                                        sender.sendMessage("A new version of " + net.md_5.bungee.api.ChatColor.GOLD + "GroupSpawn" + net.md_5.bungee.api.ChatColor.RESET + " is available: " + net.md_5.bungee.api.ChatColor.YELLOW + version.replace("_", " ") + net.md_5.bungee.api.ChatColor.RESET + " (You are currently using " + net.md_5.bungee.api.ChatColor.GOLD + core.getDescription().getVersion() + net.md_5.bungee.api.ChatColor.RESET + "). " + " https://bit.ly/2TzELra") ;

                                    }
                                });
                            }

                        }
                    }
                }

            } else sender.sendMessage("You are not a player.");
            return true;

        }
        return true;
    }
}
