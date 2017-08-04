package de.paul2708.memory.profile;

import de.paul2708.memory.game.Card;
import org.bukkit.entity.Player;

/**
 * Created by Paul on 04.08.2017.
 */
public class Profile {

    private Player player;

    private Card lastCard;
    private int score;

    public Profile(Player player) {
        this.player = player;
    }

    public void setLastCard(Card card) {
        this.lastCard = card;
    }

    public boolean hasCard() {
        return lastCard != null;
    }

    public void addScore() {
        score++;
    }

    public Card getLastCard() {
        return lastCard;
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
