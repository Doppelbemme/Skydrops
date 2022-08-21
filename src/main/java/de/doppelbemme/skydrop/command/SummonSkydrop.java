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
            sender.sendMessage(MessageUtil.getConsoleError());
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.summonskydrop")) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getNoPermission());
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
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getTierError());
            return false;
        }

        if (tier < 1 || tier > 3) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getTierError());
            return false;
        }

        MessageUtil.sendPositiveFeedback(player, MessageUtil.getStormSummoned());
        LootchestUtil.summonSkydrop(player, tier);
        return true;
    }
}
