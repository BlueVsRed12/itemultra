package xyz.rainbowpunk.itemultra.vault;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.ItemUltra;

public class VaultListener implements Listener {
    private ItemUltra plugin;
    private Vault vault;

    private final static int UNCOLLECTED_SLOT = 8;
    private final static int SORTING_SLOT = 8 + 9;
    private final static int SEARCH_SLOT = 8 + 9 * 2;
    private final static int UP_SLOT = 8 + 9 * 3;
    private final static int DOWN_SLOT = 8 + 9 * 5;

    public VaultListener(ItemUltra plugin, Vault vault) {
        this.plugin = plugin;
        this.vault = vault;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer().getUniqueId() != vault.getPlayerUUID()) return;
        unregister();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (!event.getPlayer().getUniqueId().equals(vault.getPlayerUUID())) return;
        unregister();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getUniqueId().equals(vault.getPlayerUUID())) return;
        if (event.getClickedInventory() == null) return;
        putIntoVaultCheck(event); //todo: maybe replace this with some kind of event system?
        clickIconCheck(event);
    }

    private void putIntoVaultCheck(InventoryClickEvent event) {
        if (event.getClickedInventory().getType() != InventoryType.PLAYER) return;
        if (event.getClick() != ClickType.LEFT) return;

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        vault.getPlayerCollects().collect(clickedItem.getType());
        vault.updateDisplay();

        event.setCancelled(true);
    }

    private void clickIconCheck(InventoryClickEvent event) {
        if (event.getClickedInventory().getType() != InventoryType.CHEST) return;
        int slot = event.getSlot();
        switch (slot) {
            case UNCOLLECTED_SLOT: vault.toggleUncollectedVisibility(); break;
            case SORTING_SLOT: vault.changeSorting(event.getClick()); break;
            case UP_SLOT: vault.scrollUp(); break;
            case DOWN_SLOT: vault.scrollDown(); break;
        }
        event.setCancelled(true);
    }

    private void unregister() {
        plugin.unregisterListener(this);
    }
}