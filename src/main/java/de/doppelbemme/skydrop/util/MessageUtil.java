package de.doppelbemme.skydrop.util;

import org.bukkit.Sound;
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

}
