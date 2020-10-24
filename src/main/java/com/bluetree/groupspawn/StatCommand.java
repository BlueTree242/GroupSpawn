package com.bluetree.groupspawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;



public class StatCommand implements TabCompleter {
	


	List<String> arg0 = new ArrayList<String>();
	List<String> arg1 = new ArrayList();

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("groupspawn.reload")) {
			if (arg0.isEmpty()) {
				arg0.add("reload");
				arg0.add("set");
			}
			if (args.length == 2) {
				if (args.length == 2) {
				arg1.add("group1");
				}
			}
		} else {

		}
		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
			for (String a : arg0) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
			 if (args.length == 2) {
			 	if (args[0].equalsIgnoreCase("set")) {
					for (String a : arg1) {
						if (a.toLowerCase().startsWith(args[1].toLowerCase()))
							result.add(a);
					}
				}
			 	else {}
				 
			 
		}
		return result;

	}

}
