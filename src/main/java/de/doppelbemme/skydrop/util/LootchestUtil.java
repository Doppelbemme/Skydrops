package de.doppelbemme.skydrop.util;

import de.doppelbemme.skydrop.Skydrop;
import de.doppelbemme.skydrop.inventorys.SkydropBonusInventory;
import de.doppelbemme.skydrop.item.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.tozymc.spigot.api.title.TitleApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LootchestUtil {

    public static void saveItemstackToConfig(Player player, ItemStack itemStack, int tier, String name, int dropchance) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        if (!itemStack.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        String material;
        int subid;
        int amount;
        String displayName;
        List<String> loreList = new ArrayList<>();
        List<String> enchantmentList = new ArrayList<>();
        boolean hideEnchantments = false;
        boolean isUnbreakable = false;
        boolean hideUnbreakable = false;

        material = itemStack.getType().toString();
        subid = itemStack.getDurability();
        amount = itemStack.getAmount();
        if (itemMeta.hasDisplayName()) {
            displayName = itemMeta.getDisplayName().replaceAll("§", "&");
        } else {
            displayName = itemStack.getType().toString();
        }

        if (itemMeta.hasLore()) {
            for (String loreString : itemMeta.getLore()) {
                loreList.add(loreString.replaceAll("§", "&"));
            }
        }
        if (itemMeta.hasEnchants()) {
            for (Enchantment enchantment : itemMeta.getEnchants().keySet()) {
                int enchantLevel = itemStack.getEnchantmentLevel(enchantment);
                enchantmentList.add(enchantment.getName() + ":" + enchantLevel);
            }
        }
        if (itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
            hideEnchantments = true;
        }
        if (itemMeta.spigot().isUnbreakable()) {
            isUnbreakable = true;
        }
        if (itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
            hideUnbreakable = true;
        }

        FileConfiguration fileConfiguration;
        if (tier == 1) {
            fileConfiguration = Skydrop.instance.fileManager.tierOneCfg;
        } else if (tier == 2) {
            fileConfiguration = Skydrop.instance.fileManager.tierTwoCfg;
        } else {
            fileConfiguration = Skydrop.instance.fileManager.tierThreeCfg;
        }

        fileConfiguration.set("item." + name + ".material", material);
        fileConfiguration.set("item." + name + ".subid", subid);
        fileConfiguration.set("item." + name + ".amount", amount);
        fileConfiguration.set("item." + name + ".displayName", displayName);
        fileConfiguration.set("item." + name + ".lore", loreList);
        fileConfiguration.set("item." + name + ".enchantments", enchantmentList);
        fileConfiguration.set("item." + name + ".hideEnchantments", hideEnchantments);
        fileConfiguration.set("item." + name + ".unbreakable", isUnbreakable);
        fileConfiguration.set("item." + name + ".hideUnbreakable", hideUnbreakable);
        fileConfiguration.set("item." + name + ".dropchance", dropchance);

        if (tier == 1) {
            Skydrop.instance.fileManager.saveTierOneFile();
        } else if (tier == 2) {
            Skydrop.instance.fileManager.saveTierTwoFile();
        } else {
            Skydrop.instance.fileManager.saveTierThreeFile();
        }
    }

    public static void summonSkydrop(Player player, int tier) {
        if (!Skydrop.instance.fileManager.locationCfg.contains("skychest.")) {
            player.sendMessage("§cAn error occured. Skydrop could not be summoned.");
            return;
        }

        for (Player currentPlayer : Bukkit.getOnlinePlayers()) {
            currentPlayer.playSound(currentPlayer.getLocation(), Sound.AMBIENCE_THUNDER, 5, 1);
            if (tier == 1) {
                TitleApi.sendTitle(currentPlayer, "§6§lSky Drops!", "§f§l" + player.getName() + " §7§lrestocked all skychests!", 20, 80, 20);
            } else if (tier == 2) {
                TitleApi.sendTitle(currentPlayer, "§b§lSUPER Sky Drops!", "§f§l" + player.getName() + " §7§lrestocked all skychests §c§lwith extra loot§7§l!", 20, 80, 20);
            } else {
                TitleApi.sendTitle(currentPlayer, "§5§lIMMORTAL Sky Drops!", "§f§l" + player.getName() + " §7§lrestocked all skychests §5§lwith special loot§7§l!", 20, 80, 20);
            }
        }
        sendParticle(player);
        new BukkitRunnable() {
            int i = 5;

            @Override
            public void run() {
                if (i > 0) {
                    MessageUtil.sendPositiveFeedback(player, MessageUtil.getLotteryIn() + i + "s");
                    i = i - 1;
                    return;
                } else {
                    SkydropBonusInventory.clickCounter.put(player, 0);
                    SkydropBonusInventory.lotteryLoot.put(player, new ArrayList());
                    player.openInventory(SkydropBonusInventory.getInventory(tier));
                    this.cancel();
                }
            }
        }.runTaskTimer(Skydrop.instance, 0, 20);

        for (String path : Skydrop.instance.fileManager.locationCfg.getConfigurationSection("skychest.").getKeys(false)) {
            if (LocationUtil.isLocationActive(LocationUtil.getLocationFromConfig(Integer.parseInt(path)))) {
                Location location = LocationUtil.getLocationFromConfig(Integer.parseInt(path));
                location.getWorld().strikeLightningEffect(location);
                Chest chest = (Chest) LocationUtil.getLocationFromConfig(Integer.parseInt(path)).getBlock().getState();
                chest.getInventory().clear();
                FileConfiguration fileConfiguration;

                if (tier == 1) {
                    fileConfiguration = Skydrop.instance.fileManager.tierOneCfg;
                } else if (tier == 2) {
                    fileConfiguration = Skydrop.instance.fileManager.tierTwoCfg;
                } else {
                    fileConfiguration = Skydrop.instance.fileManager.tierThreeCfg;
                }

                for (String itemPath : fileConfiguration.getConfigurationSection("item.").getKeys(false)) {
                    int randomNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                    if (randomNumber <= fileConfiguration.getInt("item." + itemPath + ".dropchance")) {
                        int randomSlot = ThreadLocalRandom.current().nextInt(0, 26 + 1);
                        if (chest.getInventory().firstEmpty() == -1) {
                            continue;
                        }
                        if (chest.getInventory().getItem(randomSlot) == null || chest.getInventory().getItem(randomSlot).getType() == Material.AIR) {
                            chest.getInventory().setItem(randomSlot, getItemstackFromConfig(fileConfiguration, "item." + itemPath));
                        } else {
                            chest.getInventory().setItem(chest.getInventory().firstEmpty(), getItemstackFromConfig(fileConfiguration, "item." + itemPath));
                        }
                    }
                }
            }
        }
    }

    public static void summonSkydrop(int tier) {
        if (!Skydrop.instance.fileManager.locationCfg.contains("skychest.")) {
            Bukkit.getLogger().warning("§cAn error occured. Skydrop could not be summoned.");
            return;
        }

        for (Player currentPlayer : Bukkit.getOnlinePlayers()) {
            currentPlayer.playSound(currentPlayer.getLocation(), Sound.AMBIENCE_THUNDER, 5, 1);
            if (tier == 1) {
                TitleApi.sendTitle(currentPlayer, "§6§lSky Drops!", "§7§lRestocked all skychests!", 20, 80, 20);
            } else if (tier == 2) {
                TitleApi.sendTitle(currentPlayer, "§b§lSUPER Sky Drops!", "§7§lRestocked all skychests §c§lwith extra loot§7§l!", 20, 80, 20);
            } else {
                TitleApi.sendTitle(currentPlayer, "§5§lIMMORTAL Sky Drops!", "§7§lRestocked all skychests §5§lwith special loot§7§l!", 20, 80, 20);
            }
        }

        for (String path : Skydrop.instance.fileManager.locationCfg.getConfigurationSection("skychest.").getKeys(false)) {
            if (LocationUtil.isLocationActive(LocationUtil.getLocationFromConfig(Integer.parseInt(path)))) {
                Location location = LocationUtil.getLocationFromConfig(Integer.parseInt(path));
                location.getWorld().strikeLightningEffect(location);
                Chest chest = (Chest) LocationUtil.getLocationFromConfig(Integer.parseInt(path)).getBlock().getState();
                chest.getInventory().clear();
                FileConfiguration fileConfiguration;

                if (tier == 1) {
                    fileConfiguration = Skydrop.instance.fileManager.tierOneCfg;
                } else if (tier == 2) {
                    fileConfiguration = Skydrop.instance.fileManager.tierTwoCfg;
                } else {
                    fileConfiguration = Skydrop.instance.fileManager.tierThreeCfg;
                }

                for (String itemPath : fileConfiguration.getConfigurationSection("item.").getKeys(false)) {
                    int randomNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                    if (randomNumber <= fileConfiguration.getInt("item." + itemPath + ".dropchance")) {
                        int randomSlot = ThreadLocalRandom.current().nextInt(0, 26 + 1);
                        if (chest.getInventory().firstEmpty() == -1) {
                            continue;
                        }
                        if (chest.getInventory().getItem(randomSlot) == null || chest.getInventory().getItem(randomSlot).getType() == Material.AIR) {
                            chest.getInventory().setItem(randomSlot, getItemstackFromConfig(fileConfiguration, "item." + itemPath));
                        } else {
                            chest.getInventory().setItem(chest.getInventory().firstEmpty(), getItemstackFromConfig(fileConfiguration, "item." + itemPath));
                        }
                    }
                }
            }
        }
    }

    public static ItemStack getItemstackFromConfig(FileConfiguration fileConfiguration, String itemPath) {
        Material material = Material.getMaterial(fileConfiguration.getString(itemPath + ".material"));
        int amount = fileConfiguration.getInt(itemPath + ".amount");
        Byte subid = (byte) fileConfiguration.getInt(itemPath + ".subid");
        String displayName = ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString(itemPath + ".displayName"));
        int index = 0;
        List<String> lore = (List<String>) fileConfiguration.getList(itemPath + ".lore");
        List<String> enchantments = (List<String>) fileConfiguration.getList(itemPath + ".enchantments");
        boolean hideEnchantments = fileConfiguration.getBoolean(itemPath + ".hideEnchantments");
        boolean unbreakable = fileConfiguration.getBoolean(itemPath + ".unbreakable");
        boolean hideUnbreakable = fileConfiguration.getBoolean(itemPath + ".hideUnbreakable");
        ItemStack itemStack = new ItemStack(material, amount, subid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (!lore.isEmpty()) {
            List<String> placeholder = new ArrayList<>();
            for (String loreString : lore) {
                placeholder.add(ChatColor.translateAlternateColorCodes('&', loreString));
            }
            itemMeta.setLore(placeholder);
        }
        if (!enchantments.isEmpty()) {
            for (String enchantmentString : enchantments) {
                String[] enchantmentList = enchantmentString.split(":");
                itemMeta.addEnchant(Enchantment.getByName(enchantmentList[0]), Integer.parseInt(enchantmentList[1]), true);
            }
        }
        if (hideEnchantments) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (unbreakable) {
            itemMeta.spigot().setUnbreakable(true);
        }
        if (hideUnbreakable) {
            itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void sendParticle(Player player) {
        new BukkitRunnable() {
            Location location = player.getLocation();
            double t = 0;
            double r = 1;
            double up = 0.2;

            @Override
            public void run() {
                t = t + Math.PI / 8;
                double x = r * Math.cos(t);
                double y = up * t;
                double z = r * Math.sin(t);
                location.add(x, y, z);
                location.getWorld().playEffect(location, Effect.HAPPY_VILLAGER, 1);
                location.subtract(x, y, z);
                if (t > Math.PI * 4) {
                    this.cancel();
                }
            }

        }.runTaskTimer(Skydrop.instance, 0, 1);
    }

    public static void consumeItemstack(ItemStack itemStack, Player player) {
        if (itemStack.getAmount() == 1) {
            int activeSlot = player.getInventory().getHeldItemSlot();
            player.getInventory().setItem(activeSlot, new ItemBuilder(Material.AIR).build());
            return;
        }
        itemStack.setAmount(itemStack.getAmount() - 1);
    }
}

