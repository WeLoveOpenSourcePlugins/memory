package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.game.Game;
import de.paul2708.memory.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Created by Paul on 23.04.2016.
 */
public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getPlayer() instanceof Player) {
            Player p = ((Player) e.getPlayer());
            Game game = GameManager.getInstance().getGame(p);

            if(game != null) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Memory.getInstance(), new Runnable() {

                    @Override
                    public void run() {
                        if(p != null) {
                            p.openInventory(game.getInventory());
                        }
                    }
                }, 2L);
            }
        }
    }
}
