package com.bluetree.groupspawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RemoveSpawnTabCompleter implements TabCompleter {
    private final Main core;

    public RemoveSpawnTabCompleter(Main core) {
        this.core = core;
    }
    List<String> arg0 = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<String>();
        if (!sender.hasPermission("groupspawn.addspawn")) return null;
        if (args.length == 1) {

            for (String a :core.getVault().getGroups() ) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return result;

    }
}
