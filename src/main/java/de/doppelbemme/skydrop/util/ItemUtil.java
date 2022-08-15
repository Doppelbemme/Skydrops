package de.doppelbemme.skydrop.util;

import de.doppelbemme.skydrop.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {

    public static ItemStack getSetupItem() {
        return new ItemBuilder(Material.CARROT_STICK).
                setDisplayName("§e§lSetup Tool").
                setLore("§eLinksklick §7- Kiste hinzuzufügen").
                addEnchant(Enchantment.ARROW_INFINITE, 1, true).
                setUnbreakable(true).
                hideEnchantments(true).
                hideUnbreakable(true).
                build();
    }

}
