package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

public class MaterialCollectable implements Collectable {
    private final Material material;
    private final int idOrder;
    private final String displayName;

    public MaterialCollectable(Material material, int idOrder) {
        this.material = material;
        String[] words = material.name().split("_");

        this.idOrder = idOrder;

        StringBuilder displayNameBuilder = new StringBuilder();
        for (String word : words) {
            displayNameBuilder.append(word.toUpperCase().replace(word.substring(1), word.substring(1).toLowerCase()));
        }
        displayName = displayNameBuilder.toString();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(material);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int getOrderId() {
        return idOrder;
    }

    @Override
    public boolean matches(ItemStack item) {
        return item.getType().equals(material);
    }
}
