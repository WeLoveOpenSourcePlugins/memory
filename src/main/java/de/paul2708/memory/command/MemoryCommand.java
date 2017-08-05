package de.paul2708.memory.command;

import de.paul2708.memory.Memory;
import de.paul2708.memory.game.Queue;
import de.paul2708.memory.util.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Paul on 05.08.2017.
 */
public class MemoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Queue queue = Memory.getGameManager().getQueue();

            if (queue.contains(player)) {
                queue.remove(player);
                player.sendMessage(Memory.getMessageFile().getMessage("queue.leave"));
            } else {
                queue.add(player);

                if (queue.getSize() == 2) {
                    Player first = queue.getPlayer(0);

                    Memory.getGameManager().createGame(first, player);

                    queue.clear();
                    return true;
                }

                player.sendMessage(Memory.getMessageFile().getMessage("queue.added"));
            }
        } else {
            Memory.getInstance().log(Constants.TAG + "§cYou cannot run this command from console.");
        }

        return true;
    }
}
