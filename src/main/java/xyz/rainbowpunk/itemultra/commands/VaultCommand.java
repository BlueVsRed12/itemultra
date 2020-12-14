package xyz.rainbowpunk.itemultra.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.rainbowpunk.itemultra.vault.Icons;

public class VaultCommand implements CommandExecutor {
    private Icons icons;

    public VaultCommand(Icons icons) {
        this.icons = icons;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(null, 9*6, "The Vault");
//        for (int i = 0; i < 6; i++)
//            setItem(inventory, 7, i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
//        setItem(inventory, 0, 8, icons.convertItem(new ItemStack(Material.STONE), "SORT_ID"));

        player.openInventory(inventory);

        return true;
    }

    private void setItem(Inventory inventory, int x, int y, ItemStack item) {
        inventory.setItem(x + 9*y, item);
    }
}
