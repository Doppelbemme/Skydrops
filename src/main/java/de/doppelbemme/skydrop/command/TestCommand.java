package de.doppelbemme.skydrop.command;

import de.doppelbemme.skydrop.util.GeneratorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.getInventory().addItem(GeneratorUtil.getGenerator(Integer.parseInt(args[0])));
        return false;
    }
}
