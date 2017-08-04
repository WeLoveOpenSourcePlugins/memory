package de.paul2708.memory.file;

import de.paul2708.memory.Memory;
import de.paul2708.memory.util.Constants;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 28.07.2017.
 */
public class MessageFile {

    private File directory;
    private File configFile;
    private YamlConfiguration configuration;

    private boolean first;

    public MessageFile(File directory) {
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
            this.configFile = new File(directory.getPath(), "messages.yml");
            if (!configFile.exists()) {
                configFile.createNewFile();
                this.first = true;

                Memory.getInstance().log(Constants.TAG + "§amessages.yml was created. §cEdit and restart your server.");
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
        configuration.set("tag", "&8[&eMemory&8]");

        configuration.set("game.against", "%tag% &7You are playing against &e%player%&7.");
        configuration.set("game.first_turn", "%tag% &e%player% &7starts. (Theme: %theme%))");
        configuration.set("game.turn", "%tag% &6%player% goes on.");
        configuration.set("game.not_your_turn", "%tag% &cIt's not your turn.");
        configuration.set("game.pair_found", "%tag% &e%player% found a pair..");
        configuration.set("game.no_pair_found", "%tag% &7%player% didn't find a pair.");
        configuration.set("game.again", "%tag% &6%player% can take another card.");
        configuration.set("game.again_player", "%tag% &6You can take another card.");

        configuration.set("queue.already_in", "%tag% &cYour are already in the queue.");
        configuration.set("queue.added", "%tag% &aYou joined the queue. &7Waiting for player..");

        configuration.set("result.draw", "%tag% &eDraw! - Nobody won.");
        configuration.set("result.win", "%tag% &e%player% has won the game. (%score% pairs)");

        try {
            configuration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage(String path, String... replace) {
        String message = configuration.getString(path);

        message = message.replaceAll("%tag%", configuration.getString("tag"));

        if (replace.length != 0) {
            if (message.contains("%player%")) {
                message = message.replaceAll("%player%", replace[0]);
            }
            if (message.contains("%theme%")) {
                message = message.replaceAll("%theme%", replace[1]);
            }
            if (message.contains("%score%")) {
                message = message.replaceAll("%score%", replace[1]);
            }
        }

        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }
}
