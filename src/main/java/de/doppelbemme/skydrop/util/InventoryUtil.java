package de.doppelbemme.skydrop.util;

import de.doppelbemme.skydrop.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtil {

    public static Inventory getCustomiseInventory(ItemStack itemStack) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 4, "§e§lCustomise Inventory");
        inventory.setItem(4, itemStack);
        inventory.setItem(20, new ItemBuilder(Material.PAPER).setDisplayName("§b§lDisplayname").build());
        inventory.setItem(22, new ItemBuilder(Material.SIGN).setDisplayName("§b§lLore").build());
        inventory.setItem(24, new ItemBuilder(Material.COMMAND).setDisplayName("§b§lOptions").build());
        return inventory;
    }

}
