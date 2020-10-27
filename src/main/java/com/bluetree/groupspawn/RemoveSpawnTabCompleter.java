package com.bluetree.groupspawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RemoveSpawnTabCompleter implements TabCompleter {
    private final Main core;

    public RemoveSpawnTabCompleter(Main core) {
        this.core = core;
    }
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<String>();
        List<String> arg0 = new ArrayList<String>();

        arg0.clear();
        if (core.getConfig().getConfigurationSection("spawns").getKeys(false) == null ) return null;

        for (String target : core.getConfig().getConfigurationSection("spawns").getKeys(false)) {
            arg0.add(target);
        }
        if (!sender.hasPermission("groupspawn.addspawn")) return null;
        if (args.length == 1) {

            for (String a :arg0 ) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return result;

    }
}
