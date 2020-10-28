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
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    public Permission getVault() {
        return perms;
    }


    private Permission perms;
    public final Map<String, Location> spawns = new HashMap<>();

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning("Vault is not installed. please download it at https://www.spigotmc.org/resources/vault.34315/");

            this.setEnabled(false);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        spawns.clear();
        loadConfigFile();
        getServer().getPluginManager().registerEvents(new Events(this), this);
        this.saveDefaultConfig();
        this.reloadConfig();
        this.getCommand("spawn").setExecutor(new spawnCommand(this));
        this.getCommand("spawn").setTabCompleter(new spawnTabCompleter(this));

        getCommand("spawn").setPermissionMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this command."));

        this.getCommand("groupspawn").setExecutor(new groupspawnCommand(this));
        this.getCommand("groupspawn").setTabCompleter(new groupspawnTabCompleter());

        this.getCommand("removespawn").setExecutor(new RemoveSpawnCommand(this));

        getCommand("removespawn").setPermissionMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this command."));
        getCommand("removespawn").setTabCompleter(new RemoveSpawnTabCompleter(this));

        getCommand("setspawn").setPermissionMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this command."));
        this.getCommand("setspawn").setExecutor(new setspawnCommand(this));
        this.getCommand("setspawn").setTabCompleter(new SetSpawnTabCompleter(this));
        getLogger().info(ChatColor.GREEN + "Enabled GroupSpawn");
        setupPermissions();
        new UpdateChecker(this, 12345).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version.replace("_", " "))) {
                getLogger().info(ChatColor.GREEN + "No new version available. (" + version.replace("_", " ") + ")");
            } else {
                getLogger().info(ChatColor.GREEN + "A new version is available. Please download as fast as possible!" + " Your version: " + ChatColor.YELLOW + this.getDescription().getVersion() + ChatColor.GREEN + " New version: " + ChatColor.YELLOW + version.replace("_", " "));
            }
        });
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
            float Yaw = (float) spawnInfo.getDouble("Pitch");
            float Pitch = (float) spawnInfo.getDouble("Yaw");

            World world = Bukkit.getWorld(worldName);
            spawns.put(spawnname, new Location(world, X, Y, Z, Yaw, Pitch));

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
            float yaw = (float) spawnInfo.getDouble("Yaw");
            float Pitch = (float) spawnInfo.getDouble("Pitch");

            World world = Bukkit.getWorld(worldName);
            spawns.put(spawnname, new Location(world, X, Y, Z, yaw, Pitch));

        }
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


}
