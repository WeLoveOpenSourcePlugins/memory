package de.paul2708.memory.theme;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 23.04.2016.
 */
public class Theme {

    private int id;
    private String name;
    private ItemStack[] pairs;

    public Theme(int id, String name, ItemStack... pairs) {
        this.id = id;
        this.name = name;
        this.pairs = pairs;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemStack[] getSoloPairs() {
        return pairs;
    }

    public List<ItemStack> getPairs() {
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < pairs.length; i++) items.add(pairs[i]);
        for (int i = 0; i < pairs.length; i++) items.add(pairs[i]);
        return items;
    }
}
