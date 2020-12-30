package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.inventory.ItemStack;

public interface Collectable {
    ItemStack getItem();
    boolean matches(ItemStack item);
}
