package de.doppelbemme.skydrop.util;

import org.bukkit.inventory.ItemStack;


public class ValidatorUtil {

    public static boolean isSetupItem(ItemStack itemStack) {
        return itemStack.isSimilar(ItemUtil.getSetupItem());
    }

}
