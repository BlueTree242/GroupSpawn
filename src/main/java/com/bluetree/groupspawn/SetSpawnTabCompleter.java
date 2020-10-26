package com.bluetree.groupspawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;


public class SetSpawnTabCompleter implements TabCompleter {
    private final Main core;

    public SetSpawnTabCompleter(Main core) {
        this.core = core;
    }

    List<String> arg0 = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<String>();
        arg0.clear();

        List<String> arg0 = new ArrayList<String>();
        for (String target : core.getVault().getGroups()) {
            arg0.add(target);
        }
        arg0.add("not-configured");
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
