package de.doppelbemme.skydrop.command;

import de.doppelbemme.skydrop.util.LootchestUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class AddItemCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by a player.");
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.additem")) {
            MessageUtil.sendNegativeFeedback(player, "§cYou don´t have permission to use this command.");
            return false;
        }
        if (args.length != 3) {
            MessageUtil.sendNegativeFeedback(player, "§cUsage: §7 /§eadditem §7<§eTier§7> <§eDropchance§7> <§eName§7>");
            return false;
        }

        int tier = 0;
        try {
            tier = Integer.parseInt(args[0]);
        } catch (Exception exception) {
            MessageUtil.sendNegativeFeedback(player, "§cPlease enter a valid tier.");
            return false;
        }

        if (tier < 1 || tier > 3) {
            MessageUtil.sendNegativeFeedback(player, "§cPlease enter a valid tier.");
            return false;
        }

        int chance = 0;
        try {
            chance = Integer.parseInt(args[1]);
        } catch (Exception exception) {
            MessageUtil.sendNegativeFeedback(player, "§cPlease enter a valid dropchance.");
            return false;
        }

        String name = args[2].toLowerCase(Locale.ROOT);

        if (chance < 0 || chance > 100) {
            MessageUtil.sendNegativeFeedback(player, "§cPlease enter a valid dropchance.");
            return false;
        }

        if (player.getInventory().getItemInHand() == null || player.getInventory().getItemInHand().getType() == Material.AIR) {
            MessageUtil.sendNegativeFeedback(player, "§cYou need to hold a item to use this command.");
            return false;
        }

        if (!player.getInventory().getItemInHand().hasItemMeta()) {
            MessageUtil.sendNegativeFeedback(player, "§cCould not find any metadata for this item.");
            return false;
        }

        LootchestUtil.saveItemstackToConfig(player, player.getInventory().getItemInHand(), tier, name, chance);
        MessageUtil.sendPositiveFeedback(player, "§aItem has been successfully saved.");
        return true;
    }
}
