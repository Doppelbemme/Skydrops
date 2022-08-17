package de.doppelbemme.skydrop.command;

import de.doppelbemme.skydrop.util.LootchestUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonSkydrop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by a player.");
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.summonskydrop")) {
            MessageUtil.sendNegativeFeedback(player, "§cYou don´t have permission to use this command.");
            return false;
        }
        if (args.length != 1) {
            MessageUtil.sendNegativeFeedback(player, "§cUsage: §7 /§esummonskydrop §7<§eTier§7>");
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

        MessageUtil.sendPositiveFeedback(player, "§aYou have successfully triggered a Skydrop.");
        LootchestUtil.summonSkydrop(player, tier);
        return true;
    }
}
