package com.bluetree.groupspawn;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Events implements Listener {

    private Permission perms;
    private final Main core;

    public Events(Main core) {
        this.core = core;
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {
        Location destination = core.spawns.get(core.getVault().getPrimaryGroup(event.getPlayer()));
        if (destination == null) {
            event.getPlayer().sendMessage("It doesnt work");
            return;

        }
        event.getPlayer().sendMessage("It works");
        event.setRespawnLocation(destination);
        return;


    }


}


