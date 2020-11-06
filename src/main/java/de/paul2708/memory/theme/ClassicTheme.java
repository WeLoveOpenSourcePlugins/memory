package de.paul2708.memory.theme;

import de.paul2708.memory.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Paul on 03.08.2017.
 */
public class ClassicTheme implements Theme {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "Classic";
    }

    @Override
    public ItemStack[] getPairs() {
        /*return new ItemStack[] {
                new ItemBuilder().name("Rosa Farbstoff").id(351).subID(13).getItem(),
                new ItemBuilder().name("Grauer Farbstoff").id(351).subID(8).getItem(),
                new ItemBuilder().name("Roter Farbstoff").id(351).subID(1).getItem(),
                new ItemBuilder().name("Schwarzpulver").type(Material.SULPHUR).getItem(),
                new ItemBuilder().name("Glowstonestaub").type(Material.GLOWSTONE_DUST).getItem(),
                new ItemBuilder().name("Grüner Farbstoff").id(351).subID(2).getItem(),
                new ItemBuilder().name("Feuerstein").type(Material.FLINT).getItem(),
                new ItemBuilder().name("Hellgrüner Farbstoff").id(351).subID(10).getItem(),
                new ItemBuilder().name("Hellblauer Farbstoff").id(351).subID(12).getItem(),
                new ItemBuilder().name("Oranger Farbstoff").id(351).subID(14).getItem(),
                new ItemBuilder().name("Hellrosa Farbstoff").id(351).subID(9).getItem(),
                new ItemBuilder().name("Lila Farbstoff").id(351).subID(5).getItem(),
                new ItemBuilder().name("Lapis Lazuli").id(351).subID(4).getItem(),
                new ItemBuilder().name("Zucker").type(Material.SUGAR).getItem(),
        };*/
        return new ItemStack[] {
                new ItemBuilder().type(Material.MAGENTA_DYE).getItem(),
                new ItemBuilder().type(Material.GRAY_DYE).getItem(),
                new ItemBuilder().type(Material.RED_DYE).getItem(),
                new ItemBuilder().type(Material.GUNPOWDER).getItem(),
                new ItemBuilder().type(Material.GLOWSTONE_DUST).getItem(),
                new ItemBuilder().type(Material.GREEN_DYE).getItem(),
                new ItemBuilder().type(Material.FLINT).getItem(),
                new ItemBuilder().type(Material.LIME_DYE).getItem(),
                new ItemBuilder().type(Material.LIGHT_BLUE_DYE).getItem(),
                new ItemBuilder().type(Material.ORANGE_DYE).getItem(),
                new ItemBuilder().type(Material.PINK_DYE).getItem(),
                new ItemBuilder().type(Material.PURPLE_DYE).getItem(),
                new ItemBuilder().type(Material.LAPIS_LAZULI).getItem(),
                new ItemBuilder().type(Material.SUGAR).getItem(),
        };
    }
}
