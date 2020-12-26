package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerCollects {
    private Map<Material, Instant> collected;
    
    public PlayerCollects() {
        collected = new HashMap<>();
    }

    public void collect(Material item) {
        collected.put(item, Instant.now());
    }

    public boolean isCollected(Material item) {
        return collected.containsKey(item);
    }

    public Set<Material> getCollectedMaterials() {
        return collected.keySet();
    }
}
