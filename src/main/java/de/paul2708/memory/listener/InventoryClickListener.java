package de.paul2708.memory.listener;

import de.paul2708.memory.event.GameClickEvent;
import de.paul2708.memory.game.Game;
import de.paul2708.memory.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Created by Paul on 23.04.2016.
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();

            if (e.getClickedInventory() == null) return;

            Game game = GameManager.getInstance().getGame(p);
            if (game != null) {
                e.setCancelled(true);

                Event event = new GameClickEvent(p, game, e.getCurrentItem(), e.getSlot(),
                        (e.getClickedInventory().getType() == InventoryType.PLAYER) ?
                                GameClickEvent.Type.PLAYER : GameClickEvent.Type.GAME);
                Bukkit.getPluginManager().callEvent(event);
            }
        }
    }
}
