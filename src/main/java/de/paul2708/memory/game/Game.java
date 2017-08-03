package de.paul2708.memory.game;

import de.paul2708.memory.Memory;
import de.paul2708.memory.theme.Theme;
import de.paul2708.memory.util.Constants;
import de.paul2708.memory.util.ItemBuilder;
import de.paul2708.memory.util.ItemManager;
import de.paul2708.memory.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Paul on 23.04.2016.
 */
public class Game {

    private Player first, second;
    private Player turn;
    private Theme theme;
    private Inventory inventory;
    private boolean shuffle;
    private boolean clickable;

    private List<Card> cards;

    private HashMap<Player, Card> stacks;

    private int firstScore, secondScore;

    protected Game(Player first, Player second) {
        this.first = first;
        this.second = second;

        this.turn = getRandomTurn();
        this.theme = GameManager.getInstance().getRandomTheme();

        createInventory();

        this.shuffle = true;
        this.clickable = false;

        this.cards = new ArrayList<>();

        this.stacks = new HashMap<>();
        stacks.put(first, null);
        stacks.put(second, null);

        this.firstScore = 0;
        this.secondScore = 0;

        first.sendMessage(Constants.TAG + "Du spielst nun gegen " + Util.getName(second) + "§7!");
        second.sendMessage(Constants.TAG + "Du spielst nun gegen " + Util.getName(first) + "§7!");

        first.openInventory(inventory);
        second.openInventory(inventory);

        shuffle(true);
        startShuffleAnimation();
    }

    public void sendMessage(String message) {
        first.sendMessage(message);
        second.sendMessage(message);
    }

    public void setTurn(Player p) {
        turn = getOpponent(p);
    }

    /*
	 * Inventory
	 */
    public void changeInventory(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    public void changeTitle(String title) {
        Inventory temp = inventory;
        this.inventory = Bukkit.createInventory(null, 54, title);

        for (int i = 0; i < temp.getSize(); i++) {
            ItemStack item = temp.getItem(i);
            if(item != null && item.getType() != Material.AIR) {
                inventory.setItem(i, item);
            }
        }

        for (Player p : getPlayers()) p.openInventory(inventory);
    }

    /*
	 * Winning
	 */
    public void win(Player p) {
        setClickable(false);

        if(p == null) {
            sendMessage(Constants.TAG + "§eUnentschieden! §7- §eNiemand hat gewonnen.");
        } else {
            String pairs = getFirst().getName().equalsIgnoreCase(p.getName()) ? firstScore + "" : secondScore + "";
            sendMessage(Constants.TAG + Util.getName(p) + " §ahat gewonnen! §7(" + pairs + " Paare)");
        }

        // Task
        Bukkit.getScheduler().cancelTask(taskShuffle);

        // Inventory
        if(first != null && first.getOpenInventory() != null) first.closeInventory();
        if(second != null && second.getOpenInventory() != null) second.closeInventory();
        ItemManager.clearInventory(first);
        ItemManager.clearInventory(second);

        // TODO: Stats einbinden
        GameManager.getInstance().deleteGame(this);
    }

    public void givePair(Player p) {
        if(first.getName().equalsIgnoreCase(p.getName())) {
            firstScore++;
        } else {
            secondScore++;
        }
    }

    public int getScore(Player p) {
        if(first.getName().equalsIgnoreCase(p.getName())) {
            return firstScore;
        } else {
            return secondScore;
        }
    }

    public int checkWin() {
        int maxPairs = 14;
        if(firstScore + secondScore < maxPairs) return 0;
        if(firstScore > secondScore) return 1;
        if(firstScore < secondScore) return 2;
        return 3;
    }

    /*
	 * Intro
	 */
    public void skipIntro() {
        shuffle(false);
        for (Player p : getPlayers()) {
            p.getInventory().setItem(13, new ItemBuilder().type(Material.AIR).getItem());
        }
    }

    /*
	 * Cards
	 */
    public void addCard(Player p, Card card) {
        stacks.put(p, card);
    }

    public boolean hasOneCard(Player p) {
        return stacks.get(p) != null;
    }

    public void clearStack(Player p) {
        stacks.put(p, null);
    }

    public boolean hasOtherCard(Player p, Card solo) {
        return stacks.get(p).isPair(solo);
    }

    public Card getCard(Player p) {
        return stacks.get(p);
    }

    public Card getCardBySlot(int slot) {
        for (Card card : cards) {
            if(card.getSlot() == slot) {
                return card;
            }
        }

        return null;
    }

    /*
	 * Setter
	 */
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    /*
	 * Getter
	 */
    public Player getOpponent(Player p) {
        return p.getName().equalsIgnoreCase(first.getName()) ? second : first;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public boolean isClickable() {
        return clickable;
    }

    public Player getTurn() {
        return turn;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getFirst() {
        return first;
    }

    public Player getSecond() {
        return second;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        if(first.isOnline()) players.add(first);
        if(second.isOnline()) players.add(second);
        return players;
    }

    /*
	 * Private Methoden
	 */
    private int i, slot, temp;
    private int taskShuffle;

    private void shuffle(boolean setup) {
        // Setup cards
        if(setup) {
            int index = 0;
            temp = 0;
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if(item.getTypeId() == 160 && item.getDurability() == (short) 0) {
                    cards.add(new Card(i, theme.getPairs().get(index)));
                    index++;
                }
            }
        } else {
            swap(true);
            // Debug
            // Collections.shuffle(cards);
            shuffle = false;
            clickable = true;
            changeTitle(Util.getName(first) + " 0 : 0 " + Util.getName(second));

            sendMessage("Gespieltes Theme: " + theme.getName());
            sendMessage(Constants.TAG + Util.getName(turn) + " §adarf anfangen!");
            Bukkit.getScheduler().cancelTask(taskShuffle);
        }
    }

    private void startShuffleAnimation() {
        // TODO: Sounds fixen
        i = 0;
        // Starting animation
        ItemStack back = Constants.COVER;
        taskShuffle = Bukkit.getScheduler().scheduleSyncRepeatingTask(Memory.getInstance(), new Runnable() {

            @Override
            public void run() {
                // Show row
                if(i == 0) for (int i = 10; i < 38; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                if(i == 1) for (int i = 11; i < 39; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                if(i == 2)  for (int i = 12; i < 40; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                if(i == 3)  for (int i = 13; i < 41; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                if(i == 4) for (int i = 14; i < 42; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                if(i == 5)  for (int i = 15; i < 43; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                if(i == 6)  for (int i = 16; i < 44; i+=9) inventory.setItem(i, getCardBySlot(i).getItem());
                // Pause: 2 Seconds
                if(i == 10) for (int i = 16; i < 44; i+=9) inventory.setItem(i, back);
                if(i == 11) for (int i = 15; i < 43; i+=9) inventory.setItem(i, back);
                if(i == 12)  for (int i = 14; i < 42; i+=9) inventory.setItem(i, back);
                if(i == 13)  for (int i = 13; i < 41; i+=9) inventory.setItem(i, back);
                if(i == 14) for (int i = 12; i < 40; i+=9) inventory.setItem(i, back);
                if(i == 15)  for (int i = 11; i < 39; i+=9) inventory.setItem(i, back);
                if(i == 16)  for (int i = 10; i < 38; i+=9) inventory.setItem(i, back);

                // Swap cards
                if(i == 18)  swap(false);
                if(i == 20)  swap(true);
                if(i == 22)  swap(false);
                if(i == 24)  swap(true);
                // Cards to center
                if(i == 26) {
                    inventory.clear();
                    setBorder(inventory);
                    for (int i = 20; i < 25; i++) inventory.setItem(i, back);
                    for (int i = 29; i < 34; i++) inventory.setItem(i, back);
                    for (int i = 38; i < 43; i++) inventory.setItem(i, back);
                }
                if(i == 28) {
                    inventory.clear();
                    setBorder(inventory);
                    for (int i = 30; i < 33; i++) inventory.setItem(i, back);
                    for (int i = 39; i < 42; i++) inventory.setItem(i, back);
                }
                if(i == 30) {
                    inventory.clear();
                    setBorder(inventory);
                }
                // Give cards
                if(i >= 31) {
                    inventory.setItem(cards.get(temp).getSlot(), cards.get(temp).getItem());
                    temp++;
                }
                // Stop
                if(temp == cards.size()) {
                    shuffle(false);
                    Bukkit.getScheduler().cancelTask(taskShuffle);
                }
                i++;
                for (Player p : getPlayers()) p.playSound(p.getLocation(), Sound.BURP, 1f, 1f);
            }
        }, 20L, 3L);
    }

    private void swap(boolean back) {
        if(back) {
            for (Card card : cards) {
                inventory.setItem(card.getSlot(), Constants.COVER);
            }
        } else {
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if(item.getDurability() == (short) 0 && item.getTypeId() == 160) {
                    inventory.setItem(i, getCardBySlot(i).getItem());
                }
            }
        }
    }

    private void setBorder(Inventory inv) {
        for (int i = 0; i < 9; i++) inventory.setItem(i, Constants.BORDER);
        for (int i = 45; i < 54; i++) inventory.setItem(i, Constants.BORDER);
        for (int i = 0; i < 46; i+=9) inventory.setItem(i, Constants.BORDER);
        for (int i = 8; i < 54; i+=9) inventory.setItem(i, Constants.BORDER);
    }

    private void createInventory() {
        // Main
        this.inventory = Bukkit.createInventory(null, 54, "Es wird gemischelt...");

        setBorder(inventory);

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if(item == null || item.getType() == null || item.getType() == Material.AIR) {
                inventory.setItem(i, Constants.COVER);
            }
        }

        for (Player p : getPlayers()) {
            p.getInventory().setItem(22, new ItemBuilder().name("Spiel abbrechen").type(Material.BARRIER).getItem());
            p.getInventory().setItem(13, new ItemBuilder().name("Intro überspringen?").type(Material.BLAZE_POWDER).
                    description("§7Bereit").description("§7Bereit").getItem());
        }
    }

    private Player getRandomTurn() {
        return new Random().nextBoolean() ? first : second;
    }
}