package de.doppelbemme.skydrop.command;


import de.doppelbemme.skydrop.util.GeneratorUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveGeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.getConsoleError());
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skydrop.command.givegen")) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getNoPermission());
            return false;
        }
        if (args.length != 2) {
            MessageUtil.sendNegativeFeedback(player, "§cUsage: §7/§egivegenerator §7<§ePlayer§7> <§eTier§7>");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getOffline());
            return false;
        }

        int tier = 0;
        try {
            tier = Integer.parseInt(args[1]);
        } catch (Exception exception) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getTierError());
            return false;
        }

        if (tier < 1 || tier > 3) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getTierError());
            return false;
        }

        if (target.getInventory().firstEmpty() == -1) {
            MessageUtil.sendNegativeFeedback(player, MessageUtil.getInventorySpace());
            return false;
        }

        target.getInventory().addItem(GeneratorUtil.getGenerator(tier));
        if (player != target) {
            MessageUtil.sendPositiveFeedback(player, "§aA stormgenerator §etier " + tier + " §ahas been given to §e" + target.getName() + "§a.");
        }
        MessageUtil.sendPositiveFeedback(target, MessageUtil.getGeneratorRecived());
        return false;
    }
}
