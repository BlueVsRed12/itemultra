package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlayerCollects {
    private Set<Collected> collected;
    
    public PlayerCollects() {
        collected = new HashSet<>();
    }

    public void collect(Collectable collectable) {
        collected.add(new Collected(collectable, Instant.now()));
    }

    public void collect(Collection<Collectable> collectables) {
        collectables.forEach(this::collect);
    }

    public boolean isCollected(Collectable item) {
        return collected.contains(item);
    }

    public Set<Collected> getCollected() {
        return collected;
    }

    public void clearCollected() {
        collected.clear();
    }
}
