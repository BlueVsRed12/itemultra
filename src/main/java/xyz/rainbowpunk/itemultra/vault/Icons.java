package xyz.rainbowpunk.itemultra.vault;

import com.google.gson.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import xyz.rainbowpunk.itemultra.Util;

import java.util.Map;

public class Icons {
    private Map<String, ItemStack> icons;

    public Icons(String json) {
        Gson gson = new Gson();
        JsonElement root = gson.toJsonTree(json);
        JsonArray jsonIconArray = root.getAsJsonArray();
        for (JsonElement jsonIcon : jsonIconArray) {

        }
    }

    private ItemStack createIcon(JsonObject object) {
        JsonPrimitive key = object.getAsJsonPrimitive("key");

        Material material = Material.getMaterial(object.getAsJsonPrimitive("item").toString());
        String title = object.getAsJsonPrimitive("title").toString();
        title = color(title); // make sure minecraft colors work

        if (object.has(""));
        return null; // todo: don't forget about this
    }

    private String color(String string) {
        string = Util.translateColor('`', string);
        string = string.replace("&", "  ");
        return string;
    }
}
