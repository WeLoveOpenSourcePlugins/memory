package de.paul2708.memory.theme;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 23.04.2016.
 */
public interface Theme {

    int getId();

    String getName();

    ItemStack[] getPairs();
}
