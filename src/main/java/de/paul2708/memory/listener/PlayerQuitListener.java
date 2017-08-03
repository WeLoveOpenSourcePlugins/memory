package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.game.Game;
import de.paul2708.memory.game.GameManager;
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
        Player p = e.getPlayer();

        // Queue
        if(Memory.getQueue().contains(p)) {
            Memory.getQueue().remove(p);
        }
        // Game
        Game game = GameManager.getInstance().getGame(p);

        if(game != null) {
            game.win(game.getOpponent(p));
        }
    }
}
