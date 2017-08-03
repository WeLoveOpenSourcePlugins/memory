package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.game.GameManager;
import de.paul2708.memory.game.Queue;
import de.paul2708.memory.util.Constants;
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
        Player p = e.getPlayer();
        Block block = e.getClickedBlock();

        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(block == null || block.getType() != Material.NOTE_BLOCK) return;
        e.setCancelled(true);

        Queue queue = Memory.getQueue();

        if(GameManager.getInstance().getGame(p) != null) {
            p.sendMessage(Constants.TAG + "Es läuft bereits noch ein Spiel mit dir.");
            return;
        }
        if (queue.contains(p)) {
            p.sendMessage(Constants.TAG + "§cDu bist bereits in der Warteschlange.");
        } else {
            queue.add(p);

            if (queue.getSize() == 2) {
                Player first = queue.getPlayer(0);

                GameManager.getInstance().createGame(first, p);

                queue.clear();
                return;
            }

            p.sendMessage(Constants.TAG + "§aDu wurdest der Warteschlange hinzugefügt!");
        }
    }
}
