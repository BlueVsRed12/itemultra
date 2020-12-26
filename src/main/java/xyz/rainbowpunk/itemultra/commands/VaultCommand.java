package xyz.rainbowpunk.itemultra.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.collectiondatabase.CollectionDatabase;
import xyz.rainbowpunk.itemultra.vault.Icons;
import xyz.rainbowpunk.itemultra.vault.Vault;

public class VaultCommand implements CommandExecutor {
    private CollectionDatabase db;

    private Icons icons;

    public VaultCommand(Icons icons, CollectionDatabase db) {
        this.db = db;

        this.icons = icons;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        Vault vault = new Vault(icons, db.getPlayerCollects(player.getUniqueId()).getCollectedMaterials());
        vault.showToPlayer(player);

        return true;
    }

    private void setItem(Inventory inventory, int x, int y, ItemStack item) {
        inventory.setItem(x + 9*y, item);
    }
}
