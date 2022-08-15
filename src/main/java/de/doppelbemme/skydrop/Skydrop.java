package de.doppelbemme.skydrop;

import de.doppelbemme.skydrop.command.SetupItemCommand;
import de.doppelbemme.skydrop.command.TestCommand;
import de.doppelbemme.skydrop.file.FileManager;
import de.doppelbemme.skydrop.listener.PlayerBreakBlockListener;
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
        registerCommands();
        registerListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("setupitem").setExecutor(new SetupItemCommand());
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerBreakBlockListener(), this);
    }
}
