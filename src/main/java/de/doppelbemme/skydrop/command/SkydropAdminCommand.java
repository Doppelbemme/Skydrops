package de.doppelbemme.skydrop.command;

import de.doppelbemme.skydrop.inventorys.SkydropAdminInventory;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkydropAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by a player.");
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.skydropadmin")) {
            MessageUtil.sendNegativeFeedback(player, "§cYou don´t have permission to use this command.");
            return false;
        }
        if (args.length != 0) {
            MessageUtil.sendNegativeFeedback(player, "§cUsage: §7 /§eskydropadmin");
            return false;
        }

        player.openInventory(SkydropAdminInventory.getInventory());
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 1);
        return true;
    }
}
