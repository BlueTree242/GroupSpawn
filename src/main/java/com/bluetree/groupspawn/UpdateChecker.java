package com.bluetree.groupspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {

    private JavaPlugin plugin;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
    }

        public void getVersion(final Consumer<String> consumer) {
            Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
                try (InputStream inputStream = new URL("http://bluecraftweb.000webhostapp.com/Plugins/Groupspawn.html").openStream(); Scanner scanner = new Scanner(inputStream)) {
                    if (scanner.hasNext()) {
                        consumer.accept(scanner.next());
                    }
                } catch (IOException exception) {
                    this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
                }
            });
        }
}
