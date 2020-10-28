package com.bluetree.groupspawn;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Events implements Listener {

    private Permission perms;
    private final Main core;

    public Events(Main core) {
        this.core = core;
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().hasPermission("groupspawn.updatechecker")) {
            new UpdateChecker(core, 12345).getVersion(version -> {
                if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                } else {
                    event.getPlayer().sendMessage("A new version of " + ChatColor.GOLD + "GroupSpawn" + ChatColor.RESET + " is available: " + ChatColor.YELLOW + version.replace("_", " ") + ChatColor.RESET + " (You are currently using " + ChatColor.GOLD + core.getDescription().getVersion() + ChatColor.RESET + "). https://bit.ly/2TzELra");

                }
            });
        }
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
            if (!event.getPlayer().hasPermission("groupspawn.updatechecker")) return;
            Timer yourtimer = new Timer(true);
            yourtimer.schedule(new TimerTask()
            {


                @Override
                public void run() {
                    new UpdateChecker(core, 12345).getVersion(version -> {
                        if (core.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                        } else {
                            event.getPlayer().sendMessage("A new version of " + ChatColor.GOLD + "GroupSpawn" + ChatColor.RESET + " is available: " + ChatColor.YELLOW + version.replace("_", " ") + ChatColor.RESET + " (You are currently using " + ChatColor.GOLD + core.getDescription().getVersion() + ChatColor.RESET + "). https://bit.ly/2TzELra");

                        }
                    });

                }
            }, 5000);

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


