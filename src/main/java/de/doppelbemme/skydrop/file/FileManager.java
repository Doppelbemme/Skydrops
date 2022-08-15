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
}
