package xyz.rainbowpunk.itemultra.vault;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.ItemUltra;
import xyz.rainbowpunk.itemultra.collectiondatabase.PlayerCollects;

import java.util.*;

public class Vault {
    private final ItemUltra plugin;
    private final Icons icons;

    private VaultListener listener;

    private final Inventory inventory;
    private int row;

    private final UUID playerUUID;
    private final PlayerCollects playerCollects;

    public Vault(ItemUltra plugin, Icons icons, UUID playerUUID, PlayerCollects playerCollects) {
        this.plugin = plugin;
        this.icons = icons;

        listener = new VaultListener(plugin,this);

        inventory = Bukkit.createInventory(null, 9 * 6);
        row = 0;

        this.playerUUID = playerUUID;
        this.playerCollects = playerCollects;

        updateItemDisplay();
    }

    public void showToPlayer(Player player) {
        plugin.registerListener(listener);
        player.openInventory(inventory);
    }

    private void goToRow(int row) {
        this.row = row;
    }

    void updateItemDisplay() {
        List<Material> collected = new ArrayList<>(playerCollects.getCollectedMaterials());
        collected.sort(Comparator.comparing(Enum::toString));

        int currentItem = row * 7;
        int row = 0;
        int col = 0;

        inventory.clear();
        insertItems:
        while (row < 6) {
            while (col < 7) {
                if (currentItem >= collected.size()) break insertItems;
                setItem(col, row, new ItemStack(collected.get(currentItem)));
                currentItem++;
                col++;
            }
            col = 0;
            row++;
        }

        while (row < 6) {
            while (col < 7) {
                setItem(col, row, icons.getIcon("empty"));
                col++;
            }
            col = 0;
            row++;
        }
    }

    private void setItem(int x, int y, ItemStack item) {
        inventory.setItem(x + 9*y, item);
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public PlayerCollects getPlayerCollects() {
        return playerCollects;
    }
}
