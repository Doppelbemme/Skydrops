package de.doppelbemme.skydrop.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;

public class FileManager {

    File generatorFile = new File("plugins/SkyDrop", "generator.yml");
    public FileConfiguration generatorCfg = YamlConfiguration.loadConfiguration(generatorFile);

    File locationFile = new File("plugins/SkyDrop", "locations.yml");
    public FileConfiguration locationCfg = YamlConfiguration.loadConfiguration(locationFile);

    File tierOneFile = new File("plugins/SkyDrop/loot", "tier1.yml");
    public FileConfiguration tierOneCfg = YamlConfiguration.loadConfiguration(tierOneFile);

    File tierTwoFile = new File("plugins/SkyDrop/loot", "tier2.yml");
    public FileConfiguration tierTwoCfg = YamlConfiguration.loadConfiguration(tierTwoFile);

    File tierThreeFile = new File("plugins/SkyDrop/loot", "tier3.yml");
    public FileConfiguration tierThreeCfg = YamlConfiguration.loadConfiguration(tierThreeFile);

    File messageFile = new File("plugins/SkyDrop", "messages.yml");
    public FileConfiguration messageCfg = YamlConfiguration.loadConfiguration(messageFile);

    public void saveLocationFile() {
        try {
            locationCfg.save(locationFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setupLocationFile() {
        if (locationCfg.contains("count")) {
            return;
        }
        locationCfg.options().copyDefaults(true);
        locationCfg.addDefault("count", 0);
        saveLocationFile();
    }

    public void saveGeneratorFile() {
        try {
            generatorCfg.save(generatorFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setupGeneratorFile() {
        if (generatorCfg.contains("skydrop.tier1")) {
            return;
        }
        generatorCfg.options().copyDefaults(true);

        //tier 1 skydrop
        generatorCfg.addDefault("skydrop.tier1.material", "NETHER_STAR");
        generatorCfg.addDefault("skydrop.tier1.subid", 0);
        generatorCfg.addDefault("skydrop.tier1.amount", 1);
        generatorCfg.addDefault("skydrop.tier1.displayName", "Storm Generator");
        generatorCfg.addDefault("skydrop.tier1.lore", Arrays.asList("Lore1", "Lore2"));
        generatorCfg.addDefault("skydrop.tier1.enchantments", Arrays.asList("ARROW_DAMAGE:1", "ARROW_INFINITE:1"));
        generatorCfg.addDefault("skydrop.tier1.hideEnchantments", false);
        generatorCfg.addDefault("skydrop.tier1.unbreakable", false);
        generatorCfg.addDefault("skydrop.tier1.hideUnbreakable", false);
        //tier 2 skydrop
        generatorCfg.addDefault("skydrop.tier2.material", "NETHER_STAR");
        generatorCfg.addDefault("skydrop.tier2.subid", 0);
        generatorCfg.addDefault("skydrop.tier2.amount", 1);
        generatorCfg.addDefault("skydrop.tier2.displayName", "Super Storm Generator");
        generatorCfg.addDefault("skydrop.tier2.lore", Arrays.asList("Lore1", "Lore2"));
        generatorCfg.addDefault("skydrop.tier2.enchantments", Arrays.asList("ARROW_DAMAGE:1", "ARROW_INFINITE:1"));
        generatorCfg.addDefault("skydrop.tier2.hideEnchantments", false);
        generatorCfg.addDefault("skydrop.tier2.unbreakable", false);
        generatorCfg.addDefault("skydrop.tier2.hideUnbreakable", false);
        //tier 3 skydrop
        generatorCfg.addDefault("skydrop.tier3.material", "NETHER_STAR");
        generatorCfg.addDefault("skydrop.tier3.subid", 0);
        generatorCfg.addDefault("skydrop.tier3.amount", 1);
        generatorCfg.addDefault("skydrop.tier3.displayName", "Immortal Storm Generator");
        generatorCfg.addDefault("skydrop.tier3.lore", Arrays.asList("Lore1", "Lore2"));
        generatorCfg.addDefault("skydrop.tier3.enchantments", Arrays.asList("ARROW_DAMAGE:1", "ARROW_INFINITE:1"));
        generatorCfg.addDefault("skydrop.tier3.hideEnchantments", false);
        generatorCfg.addDefault("skydrop.tier3.unbreakable", false);
        generatorCfg.addDefault("skydrop.tier3.hideUnbreakable", false);
        saveGeneratorFile();
    }

    public void saveTierOneFile() {
        try {
            tierOneCfg.save(tierOneFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void saveTierTwoFile() {
        try {
            tierTwoCfg.save(tierTwoFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void saveTierThreeFile() {
        try {
            tierThreeCfg.save(tierThreeFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setupTierOneFile() {
        if (tierOneCfg.contains("item.")) {
            return;
        }
        tierOneCfg.options().copyDefaults(true);
        tierOneCfg.addDefault("item.example.material", "DIRT");
        tierOneCfg.addDefault("item.example.subid", 0);
        tierOneCfg.addDefault("item.example.amount", 1);
        tierOneCfg.addDefault("item.example.displayName", "Example");
        tierOneCfg.addDefault("item.example.lore", Arrays.asList("Lore1", "Lore2"));
        tierOneCfg.addDefault("item.example.enchantments", Arrays.asList("ARROW_DAMAGE:1", "ARROW_INFINITE:1"));
        tierOneCfg.addDefault("item.example.hideEnchantments", false);
        tierOneCfg.addDefault("item.example.unbreakable", false);
        tierOneCfg.addDefault("item.example.hideUnbreakable", false);
        tierOneCfg.addDefault("item.example.dropchance", 100);

        saveTierOneFile();
    }

    public void setupTierTwoFile() {
        if (tierTwoCfg.contains("item.")) {
            return;
        }
        tierOneCfg.options().copyDefaults(true);
        tierOneCfg.addDefault("item.example.material", "DIRT");
        tierOneCfg.addDefault("item.example.subid", 0);
        tierOneCfg.addDefault("item.example.amount", 1);
        tierOneCfg.addDefault("item.example.displayName", "Example");
        tierOneCfg.addDefault("item.example.lore", Arrays.asList("Lore1", "Lore2"));
        tierOneCfg.addDefault("item.example.enchantments", Arrays.asList("ARROW_DAMAGE:1", "ARROW_INFINITE:1"));
        tierOneCfg.addDefault("item.example.hideEnchantments", false);
        tierOneCfg.addDefault("item.example.unbreakable", false);
        tierOneCfg.addDefault("item.example.hideUnbreakable", false);
        tierOneCfg.addDefault("item.example.dropchance", 100);
        saveTierTwoFile();
    }

    public void setupTierThreeFile() {
        if (tierThreeCfg.contains("item.")) {
            return;
        }
        tierThreeCfg.options().copyDefaults(true);
        tierThreeCfg.addDefault("item.example.material", "DIRT");
        tierThreeCfg.addDefault("item.example.subid", 0);
        tierThreeCfg.addDefault("item.example.amount", 1);
        tierThreeCfg.addDefault("item.example.displayName", "Example");
        tierThreeCfg.addDefault("item.example.lore", Arrays.asList("Lore1", "Lore2"));
        tierThreeCfg.addDefault("item.example.enchantments", Arrays.asList("ARROW_DAMAGE:1", "ARROW_INFINITE:1"));
        tierThreeCfg.addDefault("item.example.hideEnchantments", false);
        tierThreeCfg.addDefault("item.example.unbreakable", false);
        tierThreeCfg.addDefault("item.example.hideUnbreakable", false);
        tierThreeCfg.addDefault("item.example.dropchance", 100);

        saveTierThreeFile();
    }

    public void saveMessageFile() {
        try {
            messageCfg.save(messageFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setupMessageFile() {
        if (messageCfg.contains("message.")) {
            return;
        }
        messageCfg.options().copyDefaults(true);
        messageCfg.addDefault("message.backend.console", "&cThis command may only be used by a player.");
        messageCfg.addDefault("message.backend.tier", "&cPlease enter a valid tier.");
        messageCfg.addDefault("message.backend.dropchance", "&cPlease enter a valid dropchance.");
        messageCfg.addDefault("message.backend.holditem", "&cYou need to hold a item to use this command.");
        messageCfg.addDefault("message.backend.itemmeta", "&cCould not find any metadata for this item.");
        messageCfg.addDefault("message.backend.itemsaved", "&aItem has been successfully saved.");
        messageCfg.addDefault("message.backend.setuptool", "&aYou have received the setup tool.");
        messageCfg.addDefault("message.backend.deactivated", "&aSkyDrops have been disabled sucessfully.");
        messageCfg.addDefault("message.backend.activated", "&aSkyDrops have been enabled sucessfully.");
        messageCfg.addDefault("message.backend.chestremoved", "&aChest has been successfully removed.");
        messageCfg.addDefault("message.backend.chestsaved", "&aChest has been successfully saved.");
        messageCfg.addDefault("message.backend.chestsneak","&cIn order to break a skychest you need to be sneaking.");

        messageCfg.addDefault("message.frontend.noperms", "&cYou don´t have permission to use this command.");
        messageCfg.addDefault("message.frontend.offline", "&cThis player is not online.");
        messageCfg.addDefault("message.frontend.inventoryspace", "&cThis player doesn´t has free inventory space.");
        messageCfg.addDefault("message.frontend.generatorrecived", "&aA storgenerator has been added to your inventory.");
        messageCfg.addDefault("message.frontend.stormsummoned", "&aYou have successfully triggered a Skydrop.");
        messageCfg.addDefault("message.frontend.lotteryrewards", "&aYour rewards have been added to your inventory.");
        messageCfg.addDefault("message.frontend.itemrecived", "&aItem has been added to your inventory.");
        messageCfg.addDefault("message.frontend.chestlooted", "&c&lAlready looted!");
        messageCfg.addDefault("message.frontend.skydropcooldown", "&cYou recently summoned a skydrop. Please wait a moment.");
        messageCfg.addDefault("message.frontend.tier1byplayer", " &7&lrestocked all skychests!");
        messageCfg.addDefault("message.frontend.tier2byplayer", " &7&lrestocked all skychests &c&lwith extra loot&7&l!");
        messageCfg.addDefault("message.frontend.tier3byplayer", " &7&lrestocked all skychests &5&lwith special loot&7&l!");
        messageCfg.addDefault("message.frontend.tier1bysystem", "&7&lAll skychests have been restocked!");
        messageCfg.addDefault("message.frontend.tier2bysystem", "&7&lAll skychests have been restocked &c&lwith extra loot&7&l!");
        messageCfg.addDefault("message.frontend.tier3bysystem", "&7&lAll skychests have been restocked &5&lspecial extra loot&5&l!");
        messageCfg.addDefault("message.frontend.skydropdisabled", "&cThis feature is currently disabled.");
        messageCfg.addDefault("message.frontend.lotteryin","&eItem lottery In &f");

        saveMessageFile();
    }

}
