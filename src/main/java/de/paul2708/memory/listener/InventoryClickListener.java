package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.event.GameClickEvent;
import de.paul2708.memory.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Paul on 23.04.2016.
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();

            if (e.getClickedInventory() == null) {
                return;
            }

            Game game = Memory.getGameManager().getGame(player);
            if (game != null) {
                e.setCancelled(true);

                if (e.getClickedInventory() == null || !e.getView().getTitle().contains(player.getName())) {
                    return;
                }
                if (e.getAction() == InventoryAction.PICKUP_ALL || e.getAction() == InventoryAction.PICKUP_HALF) {
                    Event event = new GameClickEvent(player, game, e.getCurrentItem(), e.getSlot(), GameClickEvent.Type.GAME);
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }
    }
}
