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
import xyz.rainbowpunk.itemultra.collectiondatabase.Collectable;
import xyz.rainbowpunk.itemultra.collectiondatabase.Collectables;

public class VaultListener implements Listener {
    private ItemUltra plugin;
    // todo: should really not be handing collection work in a VaultListener. need to hand this off to an events api or something in the future.
    private Collectables collectables;
    private Vault vault;

    private final static int UNCOLLECTED_SLOT = 8;
    private final static int SORTING_SLOT = 8 + 9;
    private final static int SEARCH_SLOT = 8 + 9 * 2;
    private final static int UP_SLOT = 8 + 9 * 3;
    private final static int DOWN_SLOT = 8 + 9 * 5;

    public VaultListener(ItemUltra plugin, Vault vault) {
        this.plugin = plugin;
        collectables = plugin.getCollectables();
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
        event.setCancelled(true);

        if (event.getClick() != ClickType.LEFT) return;

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        Collectable collectable = collectables.getCollectableEquivalent(clickedItem);
        if (collectable == null) return;

        vault.getPlayerCollects().collect(collectable);
        vault.updateDisplay();
    }

    private void clickIconCheck(InventoryClickEvent event) {
        if (event.getClickedInventory().getType() != InventoryType.CHEST) return;
        event.setCancelled(true);

        int slot = event.getSlot();
        switch (slot) {
            case UNCOLLECTED_SLOT: vault.toggleUncollectedVisibility(); break;
            case SORTING_SLOT: vault.changeSorting(event.getClick()); break;
            case UP_SLOT: vault.scrollUp(); break;
            case DOWN_SLOT: vault.scrollDown(); break;
        }
    }

    private void unregister() {
        plugin.unregisterListener(this);
    }
}