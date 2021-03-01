package de.paul2708.memory.util;

import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by Paul on 23.04.2016.
 */
public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static final Random random = new Random();

    public static boolean isSame(ItemStack item, ItemStack copy) {
        return item != null && item.isSimilar(copy);
    }
}
