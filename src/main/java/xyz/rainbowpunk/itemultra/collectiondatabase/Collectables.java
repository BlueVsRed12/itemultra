package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.bukkit.Material.*;

/**
 *  Contains every possible item that can be collected into a player's vault in ItemUltra.
 */
public class Collectables {
    private final Set<Collectable> allCollectables;
    private final Set<Material> excludedMaterials;
    private final Set<Material> nonStockMaterials; // Any `Material` that cannot describe a collectable on its own, such as `Material.POTION`.

    public Collectables() {
        excludedMaterials = new HashSet<>();
        Collections.addAll(excludedMaterials, AIR,
                BARRIER,
                STRUCTURE_BLOCK,
                STRUCTURE_VOID,
                SPLASH_POTION,
                LINGERING_POTION,
                SPAWNER,
                REPEATING_COMMAND_BLOCK,
                COMMAND_BLOCK,
                COMMAND_BLOCK_MINECART,
                CHAIN_COMMAND_BLOCK,
                PETRIFIED_OAK_SLAB,
                KNOWLEDGE_BOOK,
                JIGSAW,
                FARMLAND,
                GRASS_PATH);

        nonStockMaterials = new HashSet<>();
        nonStockMaterials.add(POTION);

        allCollectables = new HashSet<>();
        initializeAllCollectables();
    }

    private void initializeAllCollectables() {
        int orderId = 0;
        for (Material material : values()) {
            if (!material.isItem() || isExcluded(material)) continue;
            if (isNonStock(material)) {
                orderId = initializeNonStockMaterial(material, orderId);
                continue;
            }
            Collectable collectable = new MaterialCollectable(material, orderId);
            allCollectables.add(collectable);

            orderId++;
        }
    }

    private int initializeNonStockMaterial(Material material, int currentOrderId) {
        switch (material) {
            case POTION: currentOrderId = initializePotions(currentOrderId); break;
        }
        return currentOrderId;
    }

    private int initializePotions(int currentOrderId) {
        // todo
        return currentOrderId;
    }

    public Set<Collectable> getAllCollectables() {
        return allCollectables;
    }

    public Collectable getCollectableEquivalent(ItemStack item) {
        return allCollectables.stream().filter(collectable -> collectable.matches(item)).findFirst().orElse(null);
    }

    private boolean isNonStock(Material material) {
        return nonStockMaterials.contains(material);
    }

    private boolean isExcluded(Material material) {
        if (material.name().contains("_SPAWN_EGG")) return true;
        return excludedMaterials.stream().anyMatch(material::equals);
    }
}
