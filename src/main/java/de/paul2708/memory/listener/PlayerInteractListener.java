package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.game.Queue;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Paul on 23.04.2016.
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK ||
                block == null || block.getType() != Material.NOTE_BLOCK) {
            return;
        }

        e.setCancelled(true);

        Queue queue = Memory.getGameManager().getQueue();

        if (queue.contains(player)) {
            player.sendMessage(Memory.getMessageFile().getMessage("queue.already_in"));
        } else {
            queue.add(player);

            if (queue.getSize() == 2) {
                Player first = queue.getPlayer(0);

                Memory.getGameManager().createGame(first, player);

                queue.clear();
                return;
            }

            player.sendMessage(Memory.getMessageFile().getMessage("queue.added"));
        }
    }
}
