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
            sender.sendMessage(MessageUtil.getConsoleError());
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.additem")) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getNoPermission());
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
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getTierError());
            return false;
        }

        if (tier < 1 || tier > 3) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getTierError());
            return false;
        }

        int chance = 0;
        try {
            chance = Integer.parseInt(args[1]);
        } catch (Exception exception) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getDropchanceError());
            return false;
        }

        String name = args[2].toLowerCase(Locale.ROOT);

        if (chance < 0 || chance > 100) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getDropchanceError());
            return false;
        }

        if (player.getInventory().getItemInHand() == null || player.getInventory().getItemInHand().getType() == Material.AIR) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getHoldItemError());
            return false;
        }

        if (!player.getInventory().getItemInHand().hasItemMeta()) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getHoldItemError());
            return false;
        }

        LootchestUtil.saveItemstackToConfig(player, player.getInventory().getItemInHand(), tier, name, chance);
        MessageUtil.sendPositiveFeedback(player, MessageUtil.getItemSaved());
        return true;
    }
}
