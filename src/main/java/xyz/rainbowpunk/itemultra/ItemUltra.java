package xyz.rainbowpunk.itemultra;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.rainbowpunk.itemultra.collectiondatabase.CollectionDatabase;
import xyz.rainbowpunk.itemultra.commands.UnobtainiumCommand;
import xyz.rainbowpunk.itemultra.commands.VaultCommand;
import xyz.rainbowpunk.itemultra.vault.Icons;

public class ItemUltra extends JavaPlugin {
    private Icons icons;
    private CollectionDatabase db;

    @Override
    public void onEnable() {
        icons = new Icons(Util.resourceToString("/icons.json"));
        db = new CollectionDatabase();

        getCommand("vault").setExecutor(new VaultCommand(this, icons, db));
        getCommand("unobtainium").setExecutor(new UnobtainiumCommand(icons));
    }

    @Override
    public void onDisable() {

    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}