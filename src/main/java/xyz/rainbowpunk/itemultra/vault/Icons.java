package xyz.rainbowpunk.itemultra.vault;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import xyz.rainbowpunk.itemultra.Util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Icons {
    private final Map<String, ItemStack> icons;

    public Icons(String json) {
        icons = new HashMap<>();
        createIcons(json);
    }

    public ItemStack getIcon(String key) {
        return icons.get(key);
    }

    private void createIcons(String json) {
        JsonElement root = new JsonParser().parse(json);
        JsonArray jsonIconArray = root.getAsJsonArray();
        for (JsonElement jsonIcon : jsonIconArray) {
            createIcon(jsonIcon.getAsJsonObject());
        }
    }

    private void createIcon(JsonObject object) {
        String key = object.getAsJsonPrimitive("key").getAsString();

        Material material = Material.matchMaterial(object.getAsJsonPrimitive("item").getAsString());
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        String title = object.getAsJsonPrimitive("title").getAsString();
        title = color(title); // make sure minecraft colors work
        meta.setDisplayName(title);

        if (object.has("lore")) {
            List<String> lore = new ArrayList<>();
            JsonArray jsonLoreArray = object.getAsJsonArray("lore");
            for (JsonElement jsonLoreLine : jsonLoreArray) {
                lore.add(color(jsonLoreLine.getAsString()));
            }
            meta.setLore(lore);
        }

        item.setItemMeta(meta);

        icons.put(key, item);
    }

    private String color(String string) {
        return Util.translateColor(string);
    }
}
