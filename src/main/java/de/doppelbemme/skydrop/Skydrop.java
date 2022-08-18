package de.doppelbemme.skydrop;

import de.doppelbemme.skydrop.command.*;
import de.doppelbemme.skydrop.file.FileManager;
import de.doppelbemme.skydrop.listener.InventoryCloseListener;
import de.doppelbemme.skydrop.listener.InventoryInteractListener;
import de.doppelbemme.skydrop.listener.PlayerBreakBlockListener;
import de.doppelbemme.skydrop.listener.PlayerInteractListener;
import de.doppelbemme.skydrop.util.LootchestUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public final class Skydrop extends JavaPlugin {

    public static Skydrop instance;
    public FileManager fileManager;
    public static boolean enabled = true;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager();
        fileManager.saveGeneratorFile();
        fileManager.setupGeneratorFile();
        fileManager.saveLocationFile();
        fileManager.setupLocationFile();
        fileManager.saveTierOneFile();
        fileManager.setupTierOneFile();
        fileManager.saveTierTwoFile();
        fileManager.setupTierTwoFile();
        fileManager.saveTierThreeFile();
        fileManager.setupTierThreeFile();
        registerCommands();
        registerListener();
        startSkydropTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("givegenerator").setExecutor(new GiveGeneratorCommand());
        getCommand("setupitem").setExecutor(new SetupItemCommand());
        getCommand("additem").setExecutor(new AddItemCommand());
        getCommand("summonskydrop").setExecutor(new SummonSkydrop());
        getCommand("skydropadmin").setExecutor(new SkydropAdminCommand());
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerBreakBlockListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new InventoryInteractListener(), this);
        pluginManager.registerEvents(new InventoryCloseListener(), this);
    }

    private void startSkydropTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Skydrop.instance, new Runnable() {
            @Override
            public void run() {
                int randomSlot = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                int tier;
                if (randomSlot <= 80) {
                    tier = 1;
                } else if (randomSlot <= 95) {
                    tier = 2;
                } else {
                    tier = 3;
                }
                LootchestUtil.summonSkydrop(tier);
            }
        }, 20 * TimeUnit.MINUTES.toSeconds(30), 20 * TimeUnit.HOURS.toSeconds(1));
    }

}
