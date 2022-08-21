package de.doppelbemme.skydrop.inventorys;

import de.doppelbemme.skydrop.Skydrop;
import de.doppelbemme.skydrop.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class SkydropAdminInventory {

    public static Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 5 * 9, "§e§lSkyDrop Admin");

        for (int i = 0; i < 5 * 9; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).build());
        }

        inventory.setItem(13, new ItemBuilder(Material.NETHER_STAR).
                setDisplayName("§e§lSkydrop Administration").
                build());

        inventory.setItem(28, new ItemBuilder(Material.IRON_INGOT).
                setDisplayName("§b§lSkyDrop Tier 1").
                setLore(Arrays.asList("§1", "§eLinksklick §7- Stormgenerator Item erhalten", "§eRechtsklick §7- Skydrop auslösen")).
                build());

        inventory.setItem(30, new ItemBuilder(Material.GOLD_INGOT).
                setDisplayName("§b§lSkyDrop Tier 2").
                setLore(Arrays.asList("§1", "§eLinksklick §7- Stormgenerator Item erhalten", "§eRechtsklick §7- Skydrop auslösen")).
                build());

        inventory.setItem(32, new ItemBuilder(Material.DIAMOND).
                setDisplayName("§b§lSkyDrop Tier 3").
                setLore(Arrays.asList("§1", "§eLinksklick §7- Stormgenerator Item erhalten", "§eRechtsklick §7- Skydrop auslösen")).
                build());

        if (Skydrop.enabled) {
            inventory.setItem(34, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).
                    setDisplayName("§a§lSkydrop Aktiviert").
                    build());
        } else {
            inventory.setItem(34, new ItemBuilder(Material.INK_SACK, 1, (byte) 1).
                    setDisplayName("§c§lSkydrop Deaktiviert").
                    build());
        }

        return inventory;
    }

}
