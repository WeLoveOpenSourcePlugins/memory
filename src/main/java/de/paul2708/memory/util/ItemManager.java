package de.paul2708.memory.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

/**
 * Created by Paul on 23.04.2016.
 */
public class ItemManager {

    public static void clearInventory(Player p) {
        if(p == null) return;
        for (int i = 9; i < 36; i++) {
            ItemStack item = p.getInventory().getItem(i);
            if(item != null && item.getType() != null && item.getType() != Material.AIR) {
                p.getInventory().setItem(i, new ItemStack(Material.AIR));
            }
        }
    }

    public static boolean isSame(ItemStack item, ItemStack copy) {
        if(item.getType() == copy.getType()) {
            if(item.getDurability() == copy.getDurability()) {
                return true;
            }
        }

        return false;
    }

    public static ItemStack createHead(String owner, String display, List<String> lore) {
        ItemStack skull = new ItemStack(397, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(owner);
        meta.setDisplayName(display);
        meta.setLore(lore);
        skull.setItemMeta(meta);
        return skull;
    }
}
