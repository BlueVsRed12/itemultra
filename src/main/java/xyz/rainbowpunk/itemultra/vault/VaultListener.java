package xyz.rainbowpunk.itemultra.vault;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.ItemUltra;

public class VaultListener implements Listener {
    private ItemUltra plugin;
    private Vault vault;

    public VaultListener(ItemUltra plugin, Vault vault) {
        this.plugin = plugin;
        this.vault = vault;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer().getUniqueId() != vault.getPlayerUUID()) return;
        plugin.unregisterListener(this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().getUniqueId() != vault.getPlayerUUID()) return;
        putIntoVaultCheck(event);
    }

    private void putIntoVaultCheck(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory().getType() != InventoryType.PLAYER) return;
        if (event.getClick() != ClickType.LEFT) return;

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        vault.getPlayerCollects().collect(clickedItem.getType());
        vault.updateItemDisplay();

        event.setCancelled(true);
    }
}
