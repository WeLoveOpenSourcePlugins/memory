package de.paul2708.memory.util;

import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by Paul on 23.04.2016.
 */
public class Util {

    public static final Random random = new Random();

    public static boolean isSame(ItemStack item, ItemStack copy) {
        if (item.getType() == copy.getType()) {
            if (item.getDurability() == copy.getDurability()) {
                return true;
            }
        }

        return false;
    }
}
