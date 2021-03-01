package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.event.GameClickEvent;
import de.paul2708.memory.game.Card;
import de.paul2708.memory.game.Game;
import de.paul2708.memory.profile.Profile;
import de.paul2708.memory.util.Constants;
import de.paul2708.memory.util.ItemBuilder;
import de.paul2708.memory.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 23.04.2016.
 */
public class GameClickListener implements Listener {

    @EventHandler
    public void onClick(GameClickEvent gameClickEvent) {
        Player player = gameClickEvent.getPlayer();
        Game game = gameClickEvent.getGame();
        Profile profile = game.getProfile(player);

        ItemStack item = gameClickEvent.getClickedItem();
        int slot = gameClickEvent.getSlot();

        // Check item
        if (item == null || item.getType() == Material.AIR) {
            return;
        }

        // Turn
        if (!game.getTurn().getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage(Memory.getMessageFile().getMessage("game.not_your_turn"));
            return;
        }
        // Cover
        if (Util.isSame(item, Constants.BORDER) || !game.isClickAble()) {
            return;
        }

        // Handle Click
        Card card = game.getCardBySlot(slot);

        if (profile.hasCard() && profile.getLastCard().getSlot() == slot) {
            return;
        }

        game.setClickAble(false);
        game.changeItem(slot, card.getItem());

        if (profile.hasCard()) {
            if (profile.getLastCard().equals(card)) {
                game.sendMessage(Memory.getMessageFile().getMessage("game.pair_found", player.getName()));
                profile.addScore();

                if (game.checkWin()) {
                    return;
                }
            } else {
                game.sendMessage(Memory.getMessageFile().getMessage("game.no_pair_found", player.getName()));

                game.setTurn(player);
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(Memory.getInstance(), () -> {

                if (profile.getLastCard().equals(card)) {
                    game.changeItem(profile.getLastCard().getSlot(), new ItemBuilder().type(Material.AIR).getItem());
                    game.changeItem(slot, new ItemBuilder().type(Material.AIR).getItem());

                    game.updateTitle();
                    game.sendMessage(Memory.getMessageFile().getMessage("game.again", player.getName()));
                } else {
                    game.changeItem(slot, Constants.COVER);
                    game.changeItem(profile.getLastCard().getSlot(), Constants.COVER);

                    game.setTurn(player);

                    game.sendMessage(Memory.getMessageFile().getMessage("game.turn", game.getTurn().getName()));
                }

                profile.setLastCard(null);
                game.setClickAble(true);
            }, 20L);
        } else {
            profile.setLastCard(card);

            player.sendMessage(Memory.getMessageFile().getMessage("game.again_player"));

            game.setClickAble(true);
        }
    }
}
