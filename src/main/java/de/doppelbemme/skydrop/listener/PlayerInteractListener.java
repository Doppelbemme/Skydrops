package de.doppelbemme.skydrop.listener;

import de.doppelbemme.skydrop.inventorys.SkydropBonusInventory;
import de.doppelbemme.skydrop.util.GeneratorUtil;
import de.doppelbemme.skydrop.util.LocationUtil;
import de.doppelbemme.skydrop.util.LootchestUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.tozymc.spigot.api.title.TitleApi;

import java.util.concurrent.TimeUnit;

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

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getPlayer().getInventory().getItemInHand().isSimilar(GeneratorUtil.getGenerator(1))
                    || event.getPlayer().getInventory().getItemInHand().isSimilar(GeneratorUtil.getGenerator(2))
                    || event.getPlayer().getInventory().getItemInHand().isSimilar(GeneratorUtil.getGenerator(3))) {

                if (SkydropBonusInventory.cooldown.containsKey(event.getPlayer())) {
                    if (SkydropBonusInventory.cooldown.get(event.getPlayer()) > System.currentTimeMillis()) {
                        MessageUtil.sendNegativeFeedback(event.getPlayer(), "§cYou recently summoned a skydrop. Please wait a moment.");
                        return;
                    }
                }
                SkydropBonusInventory.cooldown.put(event.getPlayer(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15));
            }
            if (event.getPlayer().getInventory().getItemInHand().isSimilar(GeneratorUtil.getGenerator(1))) {
                LootchestUtil.summonSkydrop(event.getPlayer(), 1);
                LootchestUtil.consumeItemstack(event.getPlayer().getInventory().getItemInHand(), event.getPlayer());
                return;
            }
            if (event.getPlayer().getInventory().getItemInHand().isSimilar(GeneratorUtil.getGenerator(2))) {
                LootchestUtil.summonSkydrop(event.getPlayer(), 2);
                LootchestUtil.consumeItemstack(event.getPlayer().getInventory().getItemInHand(), event.getPlayer());
                return;
            }
            if (event.getPlayer().getInventory().getItemInHand().isSimilar(GeneratorUtil.getGenerator(3))) {
                LootchestUtil.summonSkydrop(event.getPlayer(), 3);
                LootchestUtil.consumeItemstack(event.getPlayer().getInventory().getItemInHand(), event.getPlayer());
            }
        }
    }
}
