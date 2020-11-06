package de.paul2708.memory.file;

import de.paul2708.memory.Memory;
import de.paul2708.memory.util.Constants;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 28.07.2017.
 */
public class ConfigFile {

    private File directory;
    private File configFile;
    private YamlConfiguration configuration;

    private boolean first;

    public ConfigFile(File directory) {
        this.directory = directory;

        this.first = false;
    }

    public void load() {
        try {
            // Create directory
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Create file
            this.configFile = new File(directory.getPath(), "config.yml");
            if (!configFile.exists()) {
                configFile.createNewFile();
                this.first = true;

                Memory.getInstance().log(Constants.TAG + "§aconfig.yml was created. §cChange values if you want.");
            }

            // Load configuration
            this.configuration = YamlConfiguration.loadConfiguration(configFile);

            // Create default value
            if (first) {
                createDefaultValues();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDefaultValues() {
        configuration.set("join_per_block", true);
        configuration.set("join_block", Material.NOTE_BLOCK.toString().toUpperCase());

        try {
            configuration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canJoinPerBlock() {
        return configuration.getBoolean("join_per_block");
    }

    public String getMaterialType() {
        return configuration.getString("join_block");
    }
}
