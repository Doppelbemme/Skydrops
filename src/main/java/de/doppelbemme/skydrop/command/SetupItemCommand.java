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
            sender.sendMessage(MessageUtil.getConsoleError());
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.setupitem")) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getNoPermission());
            return false;
        }
        if (args.length != 0) {
            MessageUtil.sendNegativeFeedback(player, "§cUsage: §7 /§esetupitem");
            return false;
        }

        if (player.getInventory().firstEmpty() == -1) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getInventorySpace());
            return false;
        }

        MessageUtil.sendPositiveFeedback(player, MessageUtil.getSetupTool());
        player.getInventory().addItem(ItemUtil.getSetupItem());
        return false;
    }
}
