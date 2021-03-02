package de.paul2708.memory.game;

import de.paul2708.memory.Memory;
import de.paul2708.memory.profile.Profile;
import de.paul2708.memory.theme.Theme;
import de.paul2708.memory.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by Paul on 23.04.2016.
 */
public class Game {

    private Player first;
    private Player second;

    private List<Profile> profiles;
    private Player turn;

    private Theme theme;
    private List<Card> cards;

    private Inventory inventory;

    private boolean clickAble;
    private boolean update;

    public Game(Player first, Player second) {
        this.first = first;
        this.second = second;

        this.profiles = Arrays.asList(new Profile(first), new Profile(second));
        this.turn = getRandomTurn();

        // Theme
        this.theme = Memory.getGameManager().getRandomTheme();

        // Cards
        this.cards = new ArrayList<>();
        for (int i = 0; i < theme.getPairs().length; i++) {
            cards.add(new Card(-1, theme.getPairs()[i]));
        }
        for (int i = 0; i < theme.getPairs().length; i++) {
            cards.add(new Card(-1, theme.getPairs()[i]));
        }

        Collections.shuffle(cards);

        // Inventory
        createInventory();

        first.sendMessage(Memory.getMessageFile().getMessage("game.against", second.getName()));
        second.sendMessage(Memory.getMessageFile().getMessage("game.against", first.getName()));

        sendMessage(Memory.getMessageFile().getMessage("game.first_turn", first.getName(), theme.getName()));

        // Open inventory
        first.openInventory(inventory);
        second.openInventory(inventory);

        setClickAble(true);
    }

    public void sendMessage(String message) {
        first.sendMessage(message);
        second.sendMessage(message);
    }

    public void setTurn(Player p) {
        turn = getOpponent(p);
    }

    // Inventory
    public void changeItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    public void updateTitle() {
        setUpdate(true);

        Inventory temp = inventory;
        this.inventory = Bukkit.createInventory(null, 54, "§6" + first.getName() + " " + getProfile(first).getScore() + " : " +
                getProfile(second).getScore() + " " + second.getName());

        for (int i = 0; i < temp.getSize(); i++) {
            ItemStack item = temp.getItem(i);
            if (item != null && item.getType() != Material.AIR) {
                inventory.setItem(i, item);
            }
        }

        for (Player p : getPlayers()) {
            p.openInventory(inventory);
        }

        setUpdate(false);
    }

    public void win(Player player) {
        if (player == null) {
            sendMessage(Memory.getMessageFile().getMessage("result.draw"));
        } else {
            String score = getProfile(player).getScore() + "";
            sendMessage(Memory.getMessageFile().getMessage("result.win", player.getName(), score));
        }

        Memory.getGameManager().deleteGame(this);

        // Inventory
        if(first != null) {
            first.closeInventory();
        }
        if(second != null) {
            second.closeInventory();
        }
    }

    public boolean checkWin() {
        int maxPairs = 14;

        if(getProfile(first).getScore() + getProfile(second).getScore() < maxPairs) {
            return false;
        }
        if (getProfile(first).getScore() > getProfile(second).getScore()) {
           win(first);
            return true;
        }
        if (getProfile(first).getScore() < getProfile(second).getScore()) {
            win(second);
            return true;
        }

        win(null);
        return true;
    }

    public Card getCardBySlot(int slot) {
        for (Card card : cards) {
            if (card.getSlot() == slot) {
                return card;
            }
        }

        return null;
    }

    public Player getOpponent(Player p) {
        return p.getName().equalsIgnoreCase(first.getName()) ? second : first;
    }

    public Player getTurn() {
        return turn;
    }

    public Inventory getInventory() {
        return inventory;
    }


    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        if (first.isOnline()) {
            players.add(first);
        }
        if (second.isOnline()) {
            players.add(second);
        }

        return players;
    }

    public void setClickAble(boolean clickAble) {
        this.clickAble = clickAble;
    }

    public boolean isClickAble() {
        return clickAble;
    }

    public Profile getProfile(Player player) {
        for (Profile profile : profiles) {
            if (player.getUniqueId().equals(profile.getPlayer().getUniqueId())) {
                return profile;
            }
        }

        return null;
    }

    // Inventory
    private void createInventory() {
        this.inventory = Bukkit.createInventory(null, 54, "§6" + first.getName() + " 0 : 0 " + second.getName());

        setBorder();

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR) {
                inventory.setItem(i, Constants.COVER);

                for (Card card : cards) {
                    if (card.getSlot() == -1) {
                        card.setSlot(i);
                        break;
                    }
                }
            }
        }
    }

    private void setBorder() {
        for (int i = 0; i < 9; i++) inventory.setItem(i, Constants.BORDER);
        for (int i = 45; i < 54; i++) inventory.setItem(i, Constants.BORDER);
        for (int i = 0; i < 46; i+=9) inventory.setItem(i, Constants.BORDER);
        for (int i = 8; i < 54; i+=9) inventory.setItem(i, Constants.BORDER);
    }

    private Player getRandomTurn() {
        return new Random().nextBoolean() ? first : second;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isUpdate() {
        return update;
    }
}