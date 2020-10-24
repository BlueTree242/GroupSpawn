package com.bluetree.groupspawn;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class Events implements Listener {

	private final Main core;

	public Events(Main core) {
		this.core = core;
	}
	private static Permission perms = null;

	@EventHandler
	public void playerRespawn(PlayerRespawnEvent event) {
		Location destination = core.spawns.get("owner");
		if (destination == null) {
			event.getPlayer().sendMessage("It doesnt work");
			return;
		}
		event.getPlayer().sendMessage("It works");
		event.setRespawnLocation(destination);
		return;

		
		
		
		
	}


		
	}


