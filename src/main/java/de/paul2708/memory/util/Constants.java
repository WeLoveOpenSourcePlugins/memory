package de.paul2708.memory.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 23.04.2016.
 */
public class Constants {

    public static final String TAG = "[Memory] ";

    public static final ItemStack COVER;
    public static final ItemStack BORDER;

    static {
        COVER = new ItemBuilder().name("?").type(Material.STAINED_GLASS_PANE).subID(0).getItem();
        BORDER =  new ItemBuilder().name("").type(Material.STAINED_GLASS_PANE).subID(15).getItem();
    }

}
