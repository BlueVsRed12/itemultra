package xyz.rainbowpunk.itemultra.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.rainbowpunk.itemultra.collectiondatabase.Collectables;
import xyz.rainbowpunk.itemultra.collectiondatabase.CollectionDatabase;

public class EmptyCollectsCommand implements CommandExecutor {
    private CollectionDatabase db;
    private Collectables collectables;

    public EmptyCollectsCommand(Collectables collectables, CollectionDatabase db) {
        this.collectables = collectables;
        this.db = db;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        db.getPlayerCollects(player.getUniqueId()).getCollected().clear();
        return true;
    }
}
