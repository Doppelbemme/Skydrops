package de.doppelbemme.skydrop.util;

import de.doppelbemme.skydrop.Skydrop;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GeneratorUtil {

    private static final FileConfiguration fileConfiguration = Skydrop.instance.fileManager.generatorCfg;

    private static String getConfigString(int tier, String category) {
        if (tier > 3) {
            return null;
        }
        Bukkit.broadcastMessage("skydrop.tier" + tier + "." + category.toLowerCase());
        return "skydrop.tier" + tier + "." + category;
    }

    public static ItemStack getGenerator(int tier) {
        Material material = Material.getMaterial(fileConfiguration.getString(getConfigString(tier, "material")));
        int amount = fileConfiguration.getInt(getConfigString(tier, "amount"));
        Byte subid = (byte) fileConfiguration.getInt(getConfigString(tier, "subid"));
        String displayName = ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString(getConfigString(tier, "displayName")));
        int index = 0;
        List<String> lore = (List<String>) fileConfiguration.getList(getConfigString(tier, "lore"));
        List<String> enchantments = (List<String>) fileConfiguration.getList(getConfigString(tier, "enchantments"));
        boolean hideEnchantments = fileConfiguration.getBoolean(getConfigString(tier, "hideEnchantments"));
        boolean unbreakable = fileConfiguration.getBoolean(getConfigString(tier, "unbreakable"));
        boolean hideUnbreakable = fileConfiguration.getBoolean(getConfigString(tier, "hideUnbreakable"));
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

}
