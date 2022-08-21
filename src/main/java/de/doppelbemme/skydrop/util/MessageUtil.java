package de.doppelbemme.skydrop.util;

import de.doppelbemme.skydrop.Skydrop;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendNegativeFeedback(Player player, String message) {
        player.sendMessage(message);
        player.playSound(player.getLocation(), Sound.NOTE_BASS, 5, 1);
    }

    public static void sendPositiveFeedback(Player player, String message) {
        player.sendMessage(message);
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 1);
    }

    private static FileConfiguration fileConfiguration = Skydrop.instance.fileManager.messageCfg;

    public static String getConsoleError() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.console"));
    }

    public static String getTierError() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.tier"));
    }

    public static String getDropchanceError() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.dropchance"));
    }

    public static String getHoldItemError() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.holditem"));
    }

    public static String getItemMetaError() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.itemmeta"));
    }

    public static String getItemSaved() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.itemsaved"));
    }

    public static String getSetupTool() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.setuptool"));
    }

    public static String getDeactivated() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.deactivated"));
    }

    public static String getActivated() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.activated"));
    }

    public static String getChestRemoved() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.chestremoved"));
    }

    public static String getChestSaved() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.chestsaved"));
    }

    public static String getNoPermission() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.noperms"));
    }

    public static String getOffline() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.offline"));
    }

    public static String getInventorySpace() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.inventoryspace"));
    }

    public static String getGeneratorRecived() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.generatorrecived"));
    }

    public static String getStormSummoned() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.stormsummoned"));
    }

    public static String getLotteryRewards() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.lotteryrewards"));
    }

    public static String getItemRecived() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.itemrecived"));
    }

    public static String getChestLooted() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.chestlooted"));
    }

    public static String getSkydropCooldown() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.skydropcooldown"));
    }

    public static String getTier1ByPlayer() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.tier1byplayer"));
    }

    public static String getTier2ByPlayer() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.tier2byplayer"));
    }

    public static String getTier3ByPlayer() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.tier3byplayer"));
    }

    public static String getTier1BySystem() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.tier1bystem"));
    }

    public static String getTier2BySystem() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.tier2bystem"));
    }

    public static String getTier3BySystem() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.tier3bystem"));
    }

    public static String getSkydropDisabled() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.skydropdisabled"));
    }

    public static String getChestSneak() {
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.backend.chestsneak"));
    }

    public static String getLotteryIn(){
        return ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("message.frontend.lotteryin"));
    }

}
