package de.paul2708.memory.game;

import de.paul2708.memory.util.ItemManager;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 23.04.2016.
 */
public class Card {

    private int slot;
    private ItemStack item;

    public Card(int slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
    }

    public boolean isPair(Card card) {
        return ItemManager.isSame(item, card.getItem());
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }
}
