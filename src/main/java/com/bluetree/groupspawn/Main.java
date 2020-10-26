package com.bluetree.groupspawn;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    public Permission getVault() {
        return perms;
    }


    private Permission perms;
    public final Map<String, Location> spawns = new HashMap<>();

    public void onEnable() {
        spawns.clear();
        loadConfigFile();
        getServer().getPluginManager().registerEvents(new Events(this), this);
        this.saveDefaultConfig();
        this.reloadConfig();
        this.getCommand("removespawn").setExecutor(new RemoveSpawnCommand(this));

        getCommand("removespawn").setPermissionMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this command."));
        getCommand("removespawn").setTabCompleter(new RemoveSpawnTabCompleter(this));

        getCommand("setspawn").setPermissionMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this command."));
        this.getCommand("setspawn").setExecutor(new setspawnCommand(this));
        this.getCommand("setspawn").setTabCompleter(new SetSpawnTabCompleter(this));
        getLogger().info(ChatColor.GREEN + "Enabled GroupSpawn");
        setupPermissions();
        return;


    }

    @Override
    public void onDisable() {
        spawns.clear();
        getLogger().info(ChatColor.RED + "Disabled GroupSpawn");
    }

    public void loadConfigFile() {
        if (this.getConfig().getConfigurationSection("spawns") == null) return;

        spawns.clear();
        this.saveDefaultConfig();
        this.reloadConfig();
        ConfigurationSection spawnsSection = getConfig().getConfigurationSection("spawns");
        for (String spawnname : spawnsSection.getKeys(false)) {
            ConfigurationSection spawnInfo = spawnsSection.getConfigurationSection(spawnname);
            String worldName = spawnInfo.getString("world");
            double X = spawnInfo.getDouble("X");
            double Y = spawnInfo.getDouble("Y");
            double Z = spawnInfo.getDouble("Z");
            double yaw = spawnInfo.getDouble("Yaw");
            double Pitch = spawnInfo.getDouble("Pitch");

            World world = Bukkit.getWorld(worldName);
            spawns.put(spawnname, new Location(world, X, Y, Z));

        }


    }

    public void reloadConfigFile() {
        spawns.clear();
        this.saveConfig();
        this.reloadConfig();
        if (this.getConfig().getConfigurationSection("spawns") == null) return;
        ConfigurationSection warpsSection = getConfig().getConfigurationSection("spawns");
        for (String spawnname : warpsSection.getKeys(false)) {
            ConfigurationSection spawnInfo = warpsSection.getConfigurationSection(spawnname);
            String worldName = spawnInfo.getString("world");
            double X = spawnInfo.getDouble("X");
            double Y = spawnInfo.getDouble("Y");
            double Z = spawnInfo.getDouble("Z");
            double yaw = spawnInfo.getDouble("Yaw");
            double Pitch = spawnInfo.getDouble("Pitch");

            World world = Bukkit.getWorld(worldName);
            spawns.put(spawnname, new Location(world, X, Y, Z));

        }
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


}
