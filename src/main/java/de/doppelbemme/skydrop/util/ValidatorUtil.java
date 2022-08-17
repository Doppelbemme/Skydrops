package de.doppelbemme.skydrop.util;

import org.bukkit.inventory.ItemStack;

import javax.swing.text.html.parser.Entity;

public class ValidatorUtil {

    public static boolean isSetupItem(ItemStack itemStack) {
        return itemStack.isSimilar(ItemUtil.getSetupItem());
    }

}
