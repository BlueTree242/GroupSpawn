package com.bluetree.groupspawn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class spawnTabCompleter implements TabCompleter {


    private final Main core;

    public spawnTabCompleter(Main core) {
        this.core = core;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<String>();
        List<String> arg0 = new ArrayList<String>();
        List<String> empty = new ArrayList<String>();

        if (core.getConfig().getConfigurationSection("spawns") == null) return empty;

        for (String target : core.getConfig().getConfigurationSection("spawns").getKeys(false)) {
            arg0.add(target);
        }

        if (sender.hasPermission("groupspawn.spawn.othergroup")) {
            if (args.length == 1) {

                for (String a : arg0) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                        result.add(a);
                }
                return result;
            } else if (args.length == 2) {
                if (sender.hasPermission("groupspawn.spawn.other")) {
                    return null;
                } else return empty;

            }


        }
        else return empty;
        return result;
    }


    }
