package com.bluetree.groupspawn;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
            Location defloc = core.spawns.get("not-configured");
            if (defloc == null) {

                return;

            }
            event.setRespawnLocation(defloc);
            return;



        }
        event.setRespawnLocation(destination);
        return;


    }
    @EventHandler
    public void playerJoined(PlayerJoinEvent event) {
        if (event.getPlayer().hasPlayedBefore()) {
            return;
        }
        Location destination = core.spawns.get(core.getVault().getPrimaryGroup(event.getPlayer()));
        if (destination == null) {
            Location defloc = core.spawns.get("not-configured");
            if (defloc == null) {
                return;


            }
            event.getPlayer().teleport(defloc);
            return;



        }
        event.getPlayer().teleport(destination);
        return;



    }


}


