package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Paul on 26.04.2016.
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        // Queue
        if (Memory.getGameManager().getQueue().contains(player)) {
            Memory.getGameManager().getQueue().remove(player);
        }
    }
}
