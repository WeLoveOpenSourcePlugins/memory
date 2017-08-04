package de.paul2708.memory;

import de.paul2708.memory.file.MessageFile;
import de.paul2708.memory.game.GameManager;
import de.paul2708.memory.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Paul on 23.04.2016.
 */
public class Memory extends JavaPlugin {

    @Override
    public void onLoad() {
        Memory.instance = this;
    }

    @Override
    public void onEnable() {
        // Config
        Memory.messageFile = new MessageFile(getDataFolder());
        Memory.messageFile.load();

        // Game manager
        Memory.gameManager = new GameManager();

        registerListener();
    }

    @Override
    public void onDisable() {

    }

    private static Memory instance;
    private static MessageFile messageFile;
    private static GameManager gameManager;

    public static Memory getInstance() {
        return instance;
    }

    public static MessageFile getMessageFile() {
        return messageFile;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    private void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new InventoryCloseListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new GameClickListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
    }
}
