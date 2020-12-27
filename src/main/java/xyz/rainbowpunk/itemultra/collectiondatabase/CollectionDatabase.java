package xyz.rainbowpunk.itemultra.collectiondatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CollectionDatabase {
    private Map<UUID, PlayerCollects> db;

    public CollectionDatabase() {
        db = new HashMap<>();
    }

    public PlayerCollects getPlayerCollects(UUID uuid) {
        if (db.get(uuid) == null) createPlayer(uuid);
        return db.get(uuid);
    }

    private void createPlayer(UUID uuid) {
        db.put(uuid, new PlayerCollects());
    }
}
