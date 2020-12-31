package xyz.rainbowpunk.itemultra.collectiondatabase;

import java.time.Instant;

/**
 *  A `Collected` represents a collectable, in the context of a player's collectables collection.
 *  Contains extra information unique to its place in a player's collection, such as
 *  a timestamp of when it was collected.
 */
public class Collected {
    private final Instant timeCollected;
    private final Collectable collectable;

    public Collected(Collectable collectable, Instant timeCollected) {
        this.collectable = collectable;
        this.timeCollected = timeCollected;
    }

    public Collectable getCollectable() {
        return collectable;
    }

    public Instant getTimeCollected() {
        return timeCollected;
    }
}
