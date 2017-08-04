package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.game.Game;
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
        if (e.getPlayer() instanceof Player) {
            Player player = (Player) e.getPlayer();
            Game game = Memory.getGameManager().getGame(player);

            if (game != null && !game.isUpdate()) {
                game.win(game.getOpponent(player));
            }
        }
    }
}
