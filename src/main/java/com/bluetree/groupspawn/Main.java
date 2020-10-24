package com.bluetree.groupspawn;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.TabCompleter;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {


	public final Map<String, Location> spawns = new HashMap<>();
	public void onEnable() {
       spawns.clear();
       loadConfigFile();
		getServer().getPluginManager().registerEvents(new Events(this), this);
		this.saveDefaultConfig();
		this.reloadConfig();
		this.getCommand("setspawn").setExecutor(new setspawnCommand(this));
		getLogger().info(ChatColor.GREEN + "Enabled GroupSpawn");
		return;
		
		

	}

	@Override
	public void onDisable() {
		spawns.clear();
		getLogger().info(ChatColor.RED + "Disabled GroupSpawn");
	}

	public void loadConfigFile() {
        spawns.clear();
		this.saveDefaultConfig();
		this.reloadConfig();
		ConfigurationSection warpsSection = getConfig().getConfigurationSection("spawns");
		for (String spawnname : warpsSection.getKeys(false)) {
			ConfigurationSection spawnInfo = warpsSection.getConfigurationSection(spawnname);
			String worldName = spawnInfo.getString("world");
			double x = spawnInfo.getDouble("x");
			double y = spawnInfo.getDouble("y");
			double z = spawnInfo.getDouble("z");
			double yaw = spawnInfo.getDouble("Yaw");
			double Pitch = spawnInfo.getDouble("Pitch");

			World world = Bukkit.getWorld(worldName);
			spawns.put(spawnname,new Location(world, x, y, z));
		}


		}
	public void reloadConfigFile() {
        spawns.clear();
		this.saveConfig();
		this.reloadConfig();
		ConfigurationSection warpsSection = getConfig().getConfigurationSection("spawns");
		for (String spawnname : warpsSection.getKeys(false)) {
			ConfigurationSection spawnInfo = warpsSection.getConfigurationSection(spawnname);
			String worldName = spawnInfo.getString("world");
			double x = spawnInfo.getDouble("x");
			double y = spawnInfo.getDouble("y");
			double z = spawnInfo.getDouble("z");
			double yaw = spawnInfo.getDouble("Yaw");
			double Pitch = spawnInfo.getDouble("Pitch");

			World world = Bukkit.getWorld(worldName);
			spawns.put(spawnname, new Location(world, x, y, z));
		}
	}
		private static Permission perms = null;
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
	




}
