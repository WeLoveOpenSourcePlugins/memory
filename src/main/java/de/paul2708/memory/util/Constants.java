package de.paul2708.memory.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 23.04.2016.
 */
public class Constants {

    public static final String TAG = "§8[§eMemory§8] §7";

    public static final ItemStack COVER = new ItemBuilder().name("?").type(Material.STAINED_GLASS_PANE).subID(0).getItem();
    public static final ItemStack BORDER = new ItemBuilder().name("").type(Material.STAINED_GLASS_PANE).subID(15).getItem();
}
