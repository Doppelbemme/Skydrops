package de.doppelbemme.skydrop.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack ItemStack) {
        itemStack = ItemStack;
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount, byte durability) {
        itemStack = new ItemStack(material, amount, durability);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLore(List loreList) {
        itemMeta.setLore(loreList);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        itemMeta.addItemFlags(itemFlag);
        return this;
    }

    public ItemBuilder hideEnchantments(boolean hideEnchantments) {
        if (hideEnchantments) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(true);
        return this;
    }

    public ItemBuilder hideUnbreakable(boolean hideUnbreakable) {
        if (hideUnbreakable) {
            itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}