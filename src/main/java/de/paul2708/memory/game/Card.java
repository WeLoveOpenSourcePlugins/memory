package de.paul2708.memory.game;

import de.paul2708.memory.util.Util;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

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

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card card = (Card) obj;

            return Util.isSame(item, card.getItem());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slot, item);
    }
}
