package xyz.rainbowpunk.itemultra.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.rainbowpunk.itemultra.vault.Icons;

public class UnobtainiumCommand implements CommandExecutor {
    private Icons icons;

    public UnobtainiumCommand(Icons icons) {
        this.icons = icons;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        player.getWorld().dropItem(player.getLocation(), icons.getIcon("unobtainium"));
        return true;
    }
}
