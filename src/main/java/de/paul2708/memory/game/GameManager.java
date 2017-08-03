package de.paul2708.memory.game;

import de.paul2708.memory.theme.Theme;
import de.paul2708.memory.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Paul on 23.04.2016.
 */
public class GameManager {

    private static GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    private List<Game> games = new ArrayList<>();
    private List<Theme> themes = new ArrayList<>();

    private Random random = new Random();

    public void initializeThemes() {
        ItemStack[] basic = new ItemStack[] {
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
        };
        ItemStack[] food = new ItemStack[] {
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
        };

        themes.add(new Theme(0, "Basic", basic));
        themes.add(new Theme(1, "Nahrung", food));
    }

    public Theme getRandomTheme() {
        return themes.get(random.nextInt(themes.size()));
    }

    public Game getGame(Player p) {
        for(Game all : games) {
            for(Player gPlayer : all.getPlayers()) {
                if(gPlayer.getName().equals(p.getName())) {
                    return all;
                }
            }
        }

        return null;
    }

    public int getGames() {
        return games.size();
    }

    public List<Player> getPlayers() {
        List<Player> all = new ArrayList<>();
        for(Game g : games) {
            all.addAll(g.getPlayers());
        }

        return all;
    }

    public void createGame(Player first, Player second) {
        Game game = new Game(first, second);
        addGame(game);
    }

    public void deleteGame(Game game) {
        removeGame(game);
        game = null;
    }

    private void addGame(Game game) {
        games.add(game);
    }

    private void removeGame(Game game) {
        games.remove(game);
    }

}
