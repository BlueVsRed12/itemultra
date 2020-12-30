package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 *  Contains every possible item that can be collected into a player's vault in ItemUltra.
 */
public class Collectables {
    private final Set<Collectable> allCollectables;
    private final Set<Material> excludedMaterials;
    private final Set<Material> nonStockMaterials; // Any `Material` that cannot describe a collectable on its own, such as `Material.POTION`.

    public Collectables() {
        excludedMaterials = new HashSet<>();
        excludedMaterials.add(Material.BARRIER);

        nonStockMaterials = new HashSet<>();
        nonStockMaterials.add(Material.POTION);

        allCollectables = new HashSet<>();
        initializeAllCollectables();
    }

    private void initializeAllCollectables() {
        //todo: allow this to add `PotionCollectable`s. maybe checks based on `isStockMaterial(...)`?
        for (Material material : Material.values()) {
            if (!material.isItem()) continue;
            if (isExcluded(material)) continue;
            allCollectables.add(new MaterialCollectable(material));
        }
    }

    public Set<Collectable> getAllCollectables() {
        return allCollectables;
    }

    public Collectable getCollectableEquivalent(ItemStack item) {
        return allCollectables.stream().filter(collectable -> collectable.matches(item)).findFirst().orElse(null);
    }

    private boolean isStockMaterial(Material material) {
        return nonStockMaterials.stream().anyMatch(material::equals);
    }

    private boolean isExcluded(Material material) {
        return excludedMaterials.stream().anyMatch(material::equals);
    }
}
