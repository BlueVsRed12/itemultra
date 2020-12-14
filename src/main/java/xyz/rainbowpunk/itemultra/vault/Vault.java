package xyz.rainbowpunk.itemultra.vault;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Vault implements Listener {
    private Inventory inventory;
    private int row;

    private List<Material> collected;

    public Vault(Set<Material> collected) {
        inventory = Bukkit.createInventory(null, 9 * 6);
        row = 0;

        this.collected = new ArrayList<>(collected);
        this.collected.sort(Comparator.comparing(Enum::toString));
    }

    public void showToPlayer(Player player) {
        player.openInventory(inventory);
    }

    private void goToRow(int row) {
        this.row = row;
    }

    private void updateItemDisplay() {
        int currentItem = row * 7;
        int row = 0;
        int col = 0;

        insertItems:
        while (row < 6) {
            while (col < 7) {
                if (currentItem >= collected.size()) break insertItems;
                col++;
            }
            col = 0;
            row++;
        }

        while (row < 6) {
            while (col < 7) {
                col++;
            }
            col = 0;
            row++;
        }

    }

    private void setItem(Inventory inventory, int x, int y, ItemStack item) {
        inventory.setItem(x + 9*y, item);
    }
}
