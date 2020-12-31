package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.inventory.ItemStack;

public interface Collectable {
    ItemStack getItem();
    String getDisplayName();
    int getOrderId();
    boolean matches(ItemStack item);
}
