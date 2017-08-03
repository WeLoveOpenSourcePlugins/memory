package de.paul2708.memory.event;

import de.paul2708.memory.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 23.04.2016.
 */
public class GameClickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player p;
    private Game game;
    private ItemStack item;
    private int slot;
    private Type type;

    public GameClickEvent(Player p, Game game, ItemStack item, int slot, Type type) {
        this.p = p;
        this.game = game;
        this.item = item;
        this.slot = slot;
        this.type = type;
    }

    public Player getPlayer() {
        return p;
    }

    public Game getGame() {
        return game;
    }

    public ItemStack getClickedItem() {
        return item;
    }

    public int getSlot() {
        return slot;
    }

    public Type getType() {
        return type;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum Type {

        PLAYER,
        GAME;
    }
}
