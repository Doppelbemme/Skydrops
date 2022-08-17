package de.doppelbemme.skydrop.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryInteractListener implements Listener {

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase("§e§lCustomise Inventory")) {
            ItemStack clickedItem = event.getWhoClicked().getItemOnCursor();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                return;
            }
            //new ItemBuilder(Material.PAPER).setDisplayName("§b§lDisplayname").build()
            if (clickedItem.getType() == Material.PAPER && clickedItem.hasItemMeta() && clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§b§lDisplayname")) {

            }
        }
    }

}
