package de.paul2708.memory.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Paul on 23.04.2016.
 */
public class Queue {

    private List<UUID> uuid;

    public Queue() {
        this.uuid = new ArrayList<>();
    }

    public void add(Player p) {
        uuid.add(p.getUniqueId());
    }

    public void remove(Player p) {
        uuid.remove(p.getUniqueId());
    }

    public boolean contains(Player p) {
        return uuid.contains(p.getUniqueId());
    }

    public void clear() {
        uuid.clear();
    }

    public Player getPlayer(int index) {
        return Bukkit.getPlayer(uuid.get(index));
    }

    public int getSize() {
        return uuid.size();
    }

}
