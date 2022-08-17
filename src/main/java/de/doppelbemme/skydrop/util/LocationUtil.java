package de.doppelbemme.skydrop.util;

import de.doppelbemme.skydrop.Skydrop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationUtil {

    public static FileConfiguration fileConfiguration = Skydrop.instance.fileManager.locationCfg;

    public static boolean isLocationInConfig(Location location) {
        if (!fileConfiguration.contains("skychest.")) {
            return false;
        }

        for (String path : Skydrop.instance.fileManager.locationCfg.getConfigurationSection("skychest.").getKeys(false)) {
            if (location.equals(getLocationFromConfig(Integer.parseInt(path)))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLocationActive(Location location) {
        if (!fileConfiguration.contains("skychest.")) {
            return false;
        }
        for (String path : Skydrop.instance.fileManager.locationCfg.getConfigurationSection("skychest.").getKeys(false)) {
            if (location.equals(getLocationFromConfig(Integer.parseInt(path)))) {
                if (fileConfiguration.getBoolean("skychest." + path + ".active")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void toggleLocationActiveStatus(Location location) {
        if (!fileConfiguration.contains("skychest.")) {
            return;
        }
        for (String path : Skydrop.instance.fileManager.locationCfg.getConfigurationSection("skychest.").getKeys(false)) {
            if (location.equals(getLocationFromConfig(Integer.parseInt(path)))) {
                if (fileConfiguration.getBoolean("skychest." + path + ".active")) {
                    fileConfiguration.set("skychest." + path + ".active", false);
                } else {
                    fileConfiguration.set("skychest." + path + ".active", true);
                }
                Skydrop.instance.fileManager.saveLocationFile();
                return;
            }
        }
    }

    public static void saveLocationInConfig(Location location) {
        int chestCount = fileConfiguration.getInt("count");
        int newChestCount = chestCount + 1;
        fileConfiguration.set("count", newChestCount);

        fileConfiguration.set("skychest." + newChestCount + ".world", location.getWorld().getName());
        fileConfiguration.set("skychest." + newChestCount + ".x", location.getBlockX());
        fileConfiguration.set("skychest." + newChestCount + ".y", location.getBlockY());
        fileConfiguration.set("skychest." + newChestCount + ".z", location.getBlockZ());
        fileConfiguration.set("skychest." + newChestCount + ".active", true);
        Skydrop.instance.fileManager.saveLocationFile();
    }

    public static Location getLocationFromConfig(int chestCount) {
        World world = Bukkit.getWorld(fileConfiguration.getString("skychest." + chestCount + ".world"));
        int x = fileConfiguration.getInt("skychest." + chestCount + ".x");
        int y = fileConfiguration.getInt("skychest." + chestCount + ".y");
        int z = fileConfiguration.getInt("skychest." + chestCount + ".z");

        return new Location(world, x, y, z);
    }

}
