package xyz.rainbowpunk.itemultra.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.ItemUltra;
import xyz.rainbowpunk.itemultra.collectiondatabase.CollectionDatabase;
import xyz.rainbowpunk.itemultra.vault.Icons;
import xyz.rainbowpunk.itemultra.vault.Vault;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VaultCommand implements CommandExecutor {
    private ItemUltra plugin;
    private Icons icons;

    private CollectionDatabase db;
    private Map<UUID, Vault> vaultMap;

    public VaultCommand(ItemUltra plugin, Icons icons, CollectionDatabase db) {
        this.plugin = plugin;
        this.icons = icons;

        this.db = db;
        vaultMap = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        Vault vault = getPlayerVault(player.getUniqueId());
        vault.showToPlayer(player);

        return true;
    }

    private Vault getPlayerVault(UUID uuid) {
        if (vaultMap.get(uuid) == null)
            vaultMap.put(uuid, new Vault(plugin, icons, uuid, db.getPlayerCollects(uuid)));
        return vaultMap.get(uuid);
    }
}
