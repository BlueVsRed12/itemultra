package xyz.rainbowpunk.itemultra;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.rainbowpunk.itemultra.collectiondatabase.Collectables;
import xyz.rainbowpunk.itemultra.collectiondatabase.CollectionDatabase;
import xyz.rainbowpunk.itemultra.commands.CollectEverythingCommand;
import xyz.rainbowpunk.itemultra.commands.EmptyCollectsCommand;
import xyz.rainbowpunk.itemultra.commands.UnobtainiumCommand;
import xyz.rainbowpunk.itemultra.commands.VaultCommand;
import xyz.rainbowpunk.itemultra.vault.Icons;

public class ItemUltra extends JavaPlugin {
    private Icons icons;

    private Collectables collectables;
    private CollectionDatabase db;

    @Override
    public void onEnable() {
        icons = new Icons(Util.resourceToString("/icons.json"));

        collectables = new Collectables();
        db = new CollectionDatabase();

        getCommand("collecteverything").setExecutor(new CollectEverythingCommand(collectables, db));
        getCommand("emptycollects").setExecutor(new EmptyCollectsCommand(collectables, db));
        getCommand("vault").setExecutor(new VaultCommand(this, icons, db));
        getCommand("unobtainium").setExecutor(new UnobtainiumCommand(icons));
    }

    @Override
    public void onDisable() {
    }

    public Collectables getCollectables() {
        return collectables;
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}