package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialCollectable implements Collectable {
    private Material material;

    public MaterialCollectable(Material material) {
        this.material = material;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(material);
    }

    @Override
    public boolean matches(ItemStack item) {
        return item.getType().equals(material);
    }
}
