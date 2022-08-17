package de.doppelbemme.skydrop.listener;

import de.doppelbemme.skydrop.util.LocationUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.tozymc.spigot.api.title.TitleApi;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.CHEST) {
                if (!LocationUtil.isLocationInConfig(event.getClickedBlock().getLocation())) {
                    return;
                }
                if (!LocationUtil.isLocationActive(event.getClickedBlock().getLocation())) {
                    return;
                }
                Chest clickedChest = (Chest) event.getClickedBlock().getState();
                boolean isChestEmpty = true;
                for (ItemStack itemStack : clickedChest.getInventory().getContents()) {
                    if (itemStack == null) {
                        continue;
                    }
                    if (itemStack.getType() == Material.AIR) {
                        continue;
                    }
                    isChestEmpty = false;
                    break;
                }
                if (isChestEmpty) {
                    TitleApi.sendTitle(event.getPlayer(), "§c§lAlready looted!", "", 10, 20, 10);
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 5, 1);
                    event.setCancelled(true);
                }
            }
        }
    }
}
