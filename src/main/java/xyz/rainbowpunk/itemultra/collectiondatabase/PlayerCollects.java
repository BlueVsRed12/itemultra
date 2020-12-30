package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class PlayerCollects {
    private Set<Material> collected;
    
    public PlayerCollects() {
        collected = new HashSet<>();
    }

    public void collect(Material item) {
        collected.add(item);
    }

    public boolean isCollected(Material item) {
        return collected.contains(item);
    }

    public Set<Material> getCollectedMaterials() {
        return collected;
    }
}
