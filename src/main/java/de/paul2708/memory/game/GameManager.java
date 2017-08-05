package de.paul2708.memory.game;

import de.paul2708.memory.Memory;
import de.paul2708.memory.theme.ClassicTheme;
import de.paul2708.memory.theme.FoodTheme;
import de.paul2708.memory.theme.Theme;
import de.paul2708.memory.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 23.04.2016.
 */
public class GameManager {

    private List<Game> games;
    private List<Theme> themes;

    private Queue queue;

    private boolean joinPerBlock;
    private Material material;
    private byte subId;

    public GameManager() {
        this.games = new ArrayList<>();
        this.themes = new ArrayList<>();

        this.queue = new Queue();

        this.joinPerBlock = Memory.getConfigFile().canJoinPerBlock();
        String sId = Memory.getConfigFile().getBlockId();
        if (sId.contains(":")) {
            this.material = Material.getMaterial(Integer.valueOf(sId.split(":")[0]));
            this.subId = Byte.valueOf(sId.split(":")[1]);
        } else {
            this.material = Material.getMaterial(Integer.valueOf(sId));
            this.subId = (byte) 0;
        }

        // Themes
        themes.add(new ClassicTheme());
        themes.add(new FoodTheme());
    }

    public Theme getRandomTheme() {
        return themes.get(Util.random.nextInt(themes.size()));
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

    public void createGame(Player first, Player second) {
        Game game = new Game(first, second);
        games.add(game);
    }

    public void deleteGame(Game game) {
        games.remove(game);
    }

    public Queue getQueue() {
        return queue;
    }

    public boolean canJoinPerBlock() {
        return joinPerBlock;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getSubId() {
        return subId;
    }
}
