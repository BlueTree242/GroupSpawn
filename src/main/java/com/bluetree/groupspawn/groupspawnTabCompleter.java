package com.bluetree.groupspawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class groupspawnTabCompleter implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> arg0 = new ArrayList<String>();

        arg0.clear();
        if (sender.hasPermission("groupspawn.reload")) arg0.add("reload");
        List<String> result = new ArrayList<String>();

        if (args.length == 1) {

            for (String a : arg0) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);

            }
            return result;
        }
        return result;

    }
}
