package de.doppelbemme.skydrop.listener;

import de.doppelbemme.skydrop.util.LocationUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import de.doppelbemme.skydrop.util.ValidatorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreakBlockListener implements Listener {

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (ValidatorUtil.isSetupItem(player.getInventory().getItemInHand())) {
            if (!player.hasPermission("skydrop.action.setup")) {
                MessageUtil.sendNegativeFeedback(player, "§cYou don´t have permission to use this tool.");
                return;
            }
            if (event.getBlock().getType() != Material.CHEST) {
                return;
            }
            if (!LocationUtil.isLocationInConfig(event.getBlock().getLocation())) {
                LocationUtil.saveLocationInConfig(event.getBlock().getLocation());
            } else {
                if (!LocationUtil.isLocationActive(event.getBlock().getLocation())) {
                    LocationUtil.toggleLocationActiveStatus(event.getBlock().getLocation());
                }
            }
            MessageUtil.sendPositiveFeedback(player, "§aChest has been successfully saved.");
            event.setCancelled(true);
            return;
        }

        if (player.isSneaking()) {
            if (!player.hasPermission("skydrop.action.setup")) {
                MessageUtil.sendNegativeFeedback(player, "§cYou don´t have permission to break this skychest.");
                return;
            }
            if (event.getBlock().getType() != Material.CHEST) {
                return;
            }
            if (!LocationUtil.isLocationInConfig(event.getBlock().getLocation())) {
                return;
            }
            if (!LocationUtil.isLocationActive(event.getBlock().getLocation())) {
                return;
            }
            LocationUtil.toggleLocationActiveStatus(event.getBlock().getLocation());
            MessageUtil.sendPositiveFeedback(player, "§aChest has been successfully removed.");
            return;
        }

        if (event.getBlock().getType() == Material.CHEST && LocationUtil.isLocationInConfig(event.getBlock().getLocation())) {
            if (LocationUtil.isLocationActive(event.getBlock().getLocation())) {
                MessageUtil.sendNegativeFeedback(player, "§cIn order to break a skychest you have to be sneaking.");
                event.setCancelled(true);
            }
        }
    }
}
