package de.doppelbemme.skydrop.listener;

import de.doppelbemme.skydrop.Skydrop;
import de.doppelbemme.skydrop.inventorys.SkydropBonusInventory;
import de.doppelbemme.skydrop.item.ItemBuilder;
import de.doppelbemme.skydrop.util.LootchestUtil;
import de.doppelbemme.skydrop.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InventoryInteractListener implements Listener {

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() == -999) {
            return;
        }
        if (event.getInventory().getName().contains("§e§lSkyDop Bonus")) {
            int tier;
            ItemStack clickedItem = event.getCurrentItem();
            if (event.getInventory().getName().contains("1")) {
                tier = 1;
            } else if (event.getInventory().getName().contains("2")) {
                tier = 2;
            } else {
                tier = 3;
            }

            if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                event.setCancelled(true);
                return;
            }
            if (clickedItem.getType() != Material.STAINED_GLASS_PANE) {
                event.setCancelled(true);
                return;
            }
            if (SkydropBonusInventory.clickCounter.get(player) >= 5) {
                event.setCancelled(true);
                return;
            }

            int clickedSlot = event.getSlot();
            event.getInventory().setItem(clickedSlot, getRandomItem(player, tier));
            setSurroundingSlots(event.getInventory(), clickedSlot);
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 1);
            SkydropBonusInventory.clickCounter.put(player, SkydropBonusInventory.clickCounter.get(player) + 1);
            int clicks = SkydropBonusInventory.clickCounter.get(player);

            if (clicks >= 5) {
                for (int slot = 0; slot < 54; slot++) {
                    if (event.getInventory().getItem(slot) == null || event.getInventory().getItem(slot).getType() == Material.AIR || event.getInventory().getItem(slot).getType() == Material.STAINED_GLASS_PANE) {
                        event.getInventory().setItem(slot, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).
                                setDisplayName("§a§lGewinne...").
                                addEnchant(Enchantment.ARROW_DAMAGE, 1, true).
                                hideEnchantments(true).
                                build());
                    }
                }
                new BukkitRunnable() {
                    int i = 3;

                    @Override
                    public void run() {

                        if (i > 0) {
                            i = i - 1;
                        } else {
                            SkydropBonusInventory.clickCounter.remove(player);
                            for (ItemStack itemStack : SkydropBonusInventory.lotteryLoot.get(player)) {
                                player.getInventory().addItem(itemStack);
                            }
                            SkydropBonusInventory.lotteryLoot.remove(player);
                            player.closeInventory();
                            MessageUtil.sendPositiveFeedback(player, "§aYour rewards have been added to your inventory.");
                            this.cancel();
                        }

                    }
                }.runTaskTimer(Skydrop.instance, 0, 20);
            }
            event.setCancelled(true);
        }
    }

    public static ItemStack getRandomItem(Player player, int tier) {
        FileConfiguration fileConfiguration;
        if (tier == 1) {
            fileConfiguration = Skydrop.instance.fileManager.tierOneCfg;
        } else if (tier == 2) {
            fileConfiguration = Skydrop.instance.fileManager.tierTwoCfg;
        } else {
            fileConfiguration = Skydrop.instance.fileManager.tierThreeCfg;
        }

        int itemCount = 0;
        for (String itemPath : fileConfiguration.getConfigurationSection("item.").getKeys(false)) {
            itemCount = itemCount + 1;
        }
        if (itemCount == 0) {
            return new ItemBuilder(Material.BARRIER).setDisplayName("§cNo Item set").build();
        }

        int randomNumber = ThreadLocalRandom.current().nextInt(1, itemCount + 1);
        int i = 0;
        for (String itemPath : fileConfiguration.getConfigurationSection("item.").getKeys(false)) {
            i = i + 1;
            if (randomNumber == i) {
                ItemStack itemStack = LootchestUtil.getItemstackFromConfig(fileConfiguration, "item." + itemPath);
                List<ItemStack> currentLoot = SkydropBonusInventory.lotteryLoot.get(player);
                currentLoot.add(itemStack);
                return itemStack;
            }
        }

        return null;
    }

    public static void setSurroundingSlots(Inventory inventory, int slot) {
        //Edge Cases | Verstanden weil Ecke lol
        if (slot == 0) {
            inventory.setItem(slot + 1, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 9, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot == 8) {
            inventory.setItem(slot - 1, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 9, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot == 45) {
            inventory.setItem(slot - 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 1, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot == 53) {
            inventory.setItem(slot - 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot - 1, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot >= 1 && slot <= 7) {
            inventory.setItem(slot - 1, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 1, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot >= 46 && slot <= 52) {
            inventory.setItem(slot - 1, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot - 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 1, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot == 9 || slot == 18 || slot == 27 || slot == 36) {
            inventory.setItem(slot - 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 1, new ItemBuilder(Material.AIR).build());
            return;
        }
        if (slot == 17 || slot == 26 || slot == 35 || slot == 44) {
            inventory.setItem(slot - 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot + 9, new ItemBuilder(Material.AIR).build());
            inventory.setItem(slot - 1, new ItemBuilder(Material.AIR).build());
            return;
        }
        inventory.setItem(slot + 1, new ItemBuilder(Material.AIR).build());
        inventory.setItem(slot - 1, new ItemBuilder(Material.AIR).build());
        inventory.setItem(slot + 9, new ItemBuilder(Material.AIR).build());
        inventory.setItem(slot - 9, new ItemBuilder(Material.AIR).build());
    }
}

