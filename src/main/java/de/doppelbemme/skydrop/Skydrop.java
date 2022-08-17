package de.doppelbemme.skydrop;

import de.doppelbemme.skydrop.command.AddItemCommand;
import de.doppelbemme.skydrop.command.GiveGeneratorCommand;
import de.doppelbemme.skydrop.command.SetupItemCommand;
import de.doppelbemme.skydrop.command.SummonSkydrop;
import de.doppelbemme.skydrop.file.FileManager;
import de.doppelbemme.skydrop.listener.PlayerBreakBlockListener;
import de.doppelbemme.skydrop.listener.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skydrop extends JavaPlugin {

    public static Skydrop instance;
    public FileManager fileManager;

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
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerBreakBlockListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
    }
}
