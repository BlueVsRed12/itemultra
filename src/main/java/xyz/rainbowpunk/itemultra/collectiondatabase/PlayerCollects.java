package xyz.rainbowpunk.itemultra.collectiondatabase;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlayerCollects {
    private Set<Collected> collectedSet;
    private Set<Collectable> collectableSet; // Used to allow an O(1) computation when checking if the player has collected something already.
    
    public PlayerCollects() {
        collectedSet = new HashSet<>();
        collectableSet = new HashSet<>();
    }

    public void collect(Collectable collectable) {
        if (isCollected(collectable)) return;
        collectedSet.add(new Collected(collectable, Instant.now()));
        collectableSet.add(collectable);
    }

    public void collect(Collection<Collectable> collectables) {
        collectables.forEach(this::collect);
    }

    public boolean isCollected(Collectable collectable) {
        return collectableSet.contains(collectable);
    }

    public Set<Collected> getCollectedSet() {
        return collectedSet;
    }

    public void clearCollected() {
        collectedSet.clear();
        collectableSet.clear();
    }
}
