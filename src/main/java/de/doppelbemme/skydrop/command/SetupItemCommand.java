package de.doppelbemme.skydrop.command;

import de.doppelbemme.skydrop.util.ItemUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by a player.");
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.setupitem")) {
            MessageUtil.sendNegativeFeedback(player, "§cYou don´t have permission to use this command.");
            return false;
        }
        if (args.length != 0) {
            MessageUtil.sendNegativeFeedback(player, "§cUsage: §7 /§esetupitem");
            return false;
        }

        if (player.getInventory().firstEmpty() == -1) {
            MessageUtil.sendNegativeFeedback(player, "§cYou don´t have free inventory space.");
            return false;
        }

        MessageUtil.sendPositiveFeedback(player, "§aYou have received the setup tool.");
        player.getInventory().addItem(ItemUtil.getSetupItem());
        return false;
    }
}
