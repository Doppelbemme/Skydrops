package de.doppelbemme.skydrop.inventorys;

import de.doppelbemme.skydrop.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SkydropBonusInventory {

    public static HashMap<Player, Integer> clickCounter = new HashMap<>();
    public static HashMap<Player, List<ItemStack>> lotteryLoot = new HashMap<>();

    public static Inventory getInventory(int tier) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "§e§lSkyDop Bonus " + tier);

        //TODO: 0-15
        for (int i = 0; i < 54; i++) {
            int durability = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) durability).
                    setDisplayName("§a§lKlicken...").
                    addEnchant(Enchantment.ARROW_INFINITE, 1, true).
                    addItemFlag(ItemFlag.HIDE_ENCHANTS).
                    build());
        }

        return inventory;
    }

}
