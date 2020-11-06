package de.paul2708.memory.theme;

import de.paul2708.memory.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 03.08.2017.
 */
public class FoodTheme implements Theme {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String getName() {
        return "Food";
    }

    @Override
    public ItemStack[] getPairs() {
        /*return new ItemStack[] {
                new ItemBuilder().name("Brot").id(297).getItem(),
                new ItemBuilder().name("Gebratenes Kaninchen").id(412).getItem(),
                new ItemBuilder().name("Kartoffel").id(392).getItem(),
                new ItemBuilder().name("Steak").type(Material.COOKED_BEEF).getItem(),
                new ItemBuilder().name("Karotte").id(391).getItem(),
                new ItemBuilder().name("Kuchen").id(354).subID(2).getItem(),
                new ItemBuilder().name("Clownfisch").id(349).subID(2).getItem(),
                new ItemBuilder().name("Apfel").id(260).getItem(),
                new ItemBuilder().name("Gebratenes Hammelfleisch").id(424).getItem(),
                new ItemBuilder().name("Ofenkartoffel").id(393).getItem(),
                new ItemBuilder().name("Gebratenes Schweinefleisch").id(320).getItem(),
                new ItemBuilder().name("Keks").id(357).getItem(),
                new ItemBuilder().name("Kugelfisch").id(349).subID(3).getItem(),
                new ItemBuilder().name("Roher Kabeljau").type(Material.RAW_FISH).getItem(),
        };*/
        return new ItemStack[] {
                new ItemBuilder().type(Material.BREAD).getItem(),
                new ItemBuilder().type(Material.COOKED_RABBIT).getItem(),
                new ItemBuilder().type(Material.POTATO).getItem(),
                new ItemBuilder().type(Material.COOKED_BEEF).getItem(),
                new ItemBuilder().type(Material.CARROT).getItem(),
                new ItemBuilder().type(Material.CAKE).getItem(),
                new ItemBuilder().type(Material.TROPICAL_FISH).getItem(),
                new ItemBuilder().type(Material.APPLE).getItem(),
                new ItemBuilder().type(Material.COOKED_MUTTON).getItem(),
                new ItemBuilder().type(Material.BAKED_POTATO).getItem(),
                new ItemBuilder().type(Material.COOKED_PORKCHOP).getItem(),
                new ItemBuilder().type(Material.COOKIE).getItem(),
                new ItemBuilder().type(Material.PUFFERFISH).getItem(),
                new ItemBuilder().type(Material.ENCHANTED_GOLDEN_APPLE).getItem(),
        };
    }
}
