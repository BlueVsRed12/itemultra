package xyz.rainbowpunk.itemultra.collectiondatabase;

import org.bukkit.Material;

import java.util.*;

public class CollectionDatabase {
    private Map<UUID, PlayerCollects> db;

    private void createPlayer(UUID uuid) {
        db.put(uuid, new PlayerCollects());
    }

    public PlayerCollects getPlayerCollects(UUID uuid) {
        if (db.get(uuid) == null) db.put(uuid, new PlayerCollects());
        return db.get(uuid);
    }
}
