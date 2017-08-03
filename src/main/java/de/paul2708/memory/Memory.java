package de.paul2708.memory;

import de.paul2708.memory.game.GameManager;
import de.paul2708.memory.game.Queue;
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
        registerCommands();
        registerListener();

        Memory.queue = new Queue();
        GameManager.getInstance().initializeThemes();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private static Memory instance;
    private static Queue queue;

    public static Memory getInstance() {
        return instance;
    }

    public static Queue getQueue() {
        return queue;
    }

    private void registerCommands() {

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
