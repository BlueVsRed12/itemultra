package xyz.rainbowpunk.itemultra.vault;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.ItemUltra;
import xyz.rainbowpunk.itemultra.Util;
import xyz.rainbowpunk.itemultra.collectiondatabase.Collectable;
import xyz.rainbowpunk.itemultra.collectiondatabase.PlayerCollects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

enum Sort {
    ALPHABETICALLY,
    TIMESTAMP,
    ID
}

public class Vault {
    private final ItemUltra plugin;
    private final Icons icons;

    private VaultListener listener;

    private final Inventory inventory;
    private int row;
    private boolean showUncollected;
    private Sort sortingBy;
    private boolean sortDescending;

    private final UUID playerUUID;
    private final PlayerCollects playerCollects;

    public Vault(ItemUltra plugin, Icons icons, UUID playerUUID, PlayerCollects playerCollects) {
        this.plugin = plugin;
        this.icons = icons;

        listener = new VaultListener(plugin, this);

        inventory = Bukkit.createInventory(null, 9 * 6, Util.translateColor("&8&o|||| Item Vault"));
        row = 0;
        showUncollected = true;
        sortingBy = Sort.ALPHABETICALLY;
        sortDescending = false;

        this.playerUUID = playerUUID;
        this.playerCollects = playerCollects;

        updateDisplay();
    }

    public void showToPlayer(Player player) {
        plugin.registerListener(listener);
        player.openInventory(inventory);
    }

    public void toggleUncollectedVisibility() {
        showUncollected = !showUncollected;
        updateDisplay();
    }

    public void changeSorting(ClickType clickType) {
        if (clickType == ClickType.LEFT) cycleSorting();
        else if (clickType == ClickType.RIGHT) toggleDescending();
    }

    private void toggleDescending() {
        sortDescending = !sortDescending;
        updateDisplay();
    }

    private void cycleSorting() {
        switch (sortingBy) {
            case ID: sortingBy = Sort.ALPHABETICALLY; break;
            case ALPHABETICALLY: sortingBy = Sort.TIMESTAMP; break;
            case TIMESTAMP: sortingBy = Sort.ID; break;
        }
        updateDisplay();
    }

    public void scrollUp() {
        if (row > 0) row--;
        updateDisplay();
    }

    public void scrollDown() {
        row++;
        updateDisplay();
    }

    void updateDisplay() {
        updateItemDisplay();
        updateControlDisplay();
    }

    private void updateItemDisplay() {
        List<ItemStack> items = new ArrayList<>();
        playerCollects.getCollected().forEach(collected -> items.add(collected.getCollectable().getItem()));
        items.sort(Comparator.comparing(a -> a.getType().name()));

        int currentItem = row * 7;
        int row = 0;
        int col = 0;

        insertItems:
        while (row < 6) {
            while (col < 7) {
                if (currentItem >= items.size()) break insertItems;
                setItem(col, row, items.get(currentItem));
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

    private void updateControlDisplay() {
        setItem(8, 0, showUncollected ?
                icons.getIcon("show_uncollected")
                : icons.getIcon("hide_uncollected"));

        ItemStack sortIcon = icons.getIcon("empty");
        if (!sortDescending) {
            switch (sortingBy) {
                case ALPHABETICALLY: sortIcon = icons.getIcon("sort_alphabetically"); break;
                case TIMESTAMP: sortIcon = icons.getIcon("sort_time"); break;
                case ID: sortIcon = icons.getIcon("sort_id"); break;
            }
        } else {
            switch (sortingBy) {
                case ALPHABETICALLY: sortIcon = icons.getIcon("sort_alphabetically_descending"); break;
                case TIMESTAMP: sortIcon = icons.getIcon("sort_time_descending"); break;
                case ID: sortIcon = icons.getIcon("sort_id_descending"); break;
            }
        }
        setItem(8, 1, sortIcon);

        setItem(8, 2, icons.getIcon("search"));
        setItem(8, 3, icons.getIcon((row > 0) ? "up" : "empty")); // Placeholder row navigation
        setItem(8, 4, icons.getIcon("empty"));  // Placeholder row navigation
        setItem(8, 5, icons.getIcon("down"));   // Placeholder row navigation
    }

    private void setItem(int x, int y, ItemStack item) {
        inventory.setItem(x + 9 * y, item);
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public PlayerCollects getPlayerCollects() {
        return playerCollects;
    }
}