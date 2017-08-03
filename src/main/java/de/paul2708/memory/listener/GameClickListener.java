package de.paul2708.memory.listener;

import de.paul2708.memory.Memory;
import de.paul2708.memory.event.GameClickEvent;
import de.paul2708.memory.game.Card;
import de.paul2708.memory.game.Game;
import de.paul2708.memory.util.Constants;
import de.paul2708.memory.util.ItemBuilder;
import de.paul2708.memory.util.ItemManager;
import de.paul2708.memory.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Paul on 23.04.2016.
 */
public class GameClickListener implements Listener {

    @EventHandler
    public void onClick(GameClickEvent e) {
        Player p = e.getPlayer();
        Game game = e.getGame();
        GameClickEvent.Type type = e.getType();
        ItemStack item = e.getClickedItem();
        int slot = e.getSlot();

        // Check item
        if(item == null || item.getType() == null || item.getType() == Material.AIR) return;

        // Player inventory
        if(type == GameClickEvent.Type.PLAYER) {
            if(slot == 22) {
                game.sendMessage(Constants.TAG + Util.getName(p) + " hast das Spiel abgebrochen.");
                game.win(game.getOpponent(p));
                return;
            }
            if(slot == 13) {
                List<String> lore = item.getItemMeta().getLore();
                // First
                if(game.getFirst().getName().equalsIgnoreCase(p.getName())) {
                    if(lore.get(0).contains("§7Bereit")) {
                        lore.set(0, "§aBereit");
                        for(Player all : game.getPlayers()) {
                            all.getInventory().setItem(13, new ItemBuilder().name("Intro überspringen?").type(Material.BLAZE_POWDER).
                                    description(lore.get(0)).description(lore.get(1)).getItem());
                        }
                        if(lore.get(1).contains("§aBereit")) {
                            game.skipIntro();
                            game.sendMessage(Constants.TAG + "Das Intro wurde übersprungen.");
                        }
                    }
                }
                // Second
                if(game.getSecond().getName().equalsIgnoreCase(p.getName())) {
                    if(lore.get(1).contains("§7Bereit")) {
                        lore.set(1, "§aBereit");
                        for(Player all : game.getPlayers()) {
                            all.getInventory().setItem(13, new ItemBuilder().name("Intro überspringen?").type(Material.BLAZE_POWDER).
                                    description(lore.get(0)).description(lore.get(1)).getItem());
                        }
                        if(lore.get(0).contains("§aBereit")) {
                            game.skipIntro();
                            game.sendMessage(Constants.TAG + "Das Intro wurde übersprungen.");
                        }
                    }
                }

                return;
            }
        // Game inventory
        } else {
            // Shuffle
            if(game.isShuffle()) {
                p.sendMessage(Constants.TAG + "Es wird gerade gemischelt.");
                return;
            }
            // Turn
            if(!game.getTurn().getName().equalsIgnoreCase(p.getName())) {
                p.sendMessage(Constants.TAG + "Du bist gerade nicht an der Reihe.");
                return;
            }
            // Cover
            if(!ItemManager.isSame(item, Constants.COVER)) return;
            // Clickable
            if(!game.isClickable()) return;

            // Handle Click
            Card card = game.getCardBySlot(slot);
            game.changeInventory(slot, card.getItem());

            if(game.hasOneCard(p)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Memory.getInstance(), new Runnable() {

                    @Override
                    public void run() {
                        if(game.hasOtherCard(p, card)) {
                            game.sendMessage(Constants.TAG + Util.getName(p) + " §ahat ein Paar gefunden.");
                            game.givePair(p);
                            switch (game.checkWin()) {
                                case 0: break;
                                case 1: game.win(game.getFirst());
                                        return;
                                case 2: game.win(game.getSecond());
                                        return;
                                case 3: game.win(null);
                                        return;
                            }
                        } else {
                            game.sendMessage(Constants.TAG + Util.getName(p) + " §chat kein Paar gefunden.");
                        }

                        Bukkit.getScheduler().scheduleSyncDelayedTask(Memory.getInstance(), new Runnable() {

                            @Override
                            public void run() {
                                if(game.hasOtherCard(p, card)) {
                                    game.changeInventory(game.getCard(p).getSlot(), new ItemBuilder().type(Material.AIR).getItem());
                                    game.changeInventory(slot, new ItemBuilder().type(Material.AIR).getItem());

                                    game.sendMessage(Constants.TAG + Util.getName(p) + " §7darf noch einmal.");

                                    game.changeTitle(Util.getName(game.getFirst()) + " " + game.getScore(game
                                            .getFirst()) + " : " + game.getScore(game.getSecond()) + " " + Util.getName(game.getSecond()));
                                } else {
                                    game.changeInventory(game.getCard(p).getSlot(), Constants.COVER);
                                    game.changeInventory(slot, Constants.COVER);

                                    game.setTurn(p);
                                    game.sendMessage(Constants.TAG + Util.getName(game.getTurn()) + " §7ist nun an der Reihe.");
                                }

                                game.clearStack(p);
                                game.setClickable(true);
                            }
                        }, 40L);
                    }
                }, 10L);

                game.setClickable(false);
            } else {
                game.addCard(p, card);
                p.sendMessage(Constants.TAG + "§2Du darfst eine weitere Karte umdrehen.");
                game.setClickable(true);
            }

            game.changeInventory(card.getSlot(), card.getItem());
            return;
        }
    }
}
