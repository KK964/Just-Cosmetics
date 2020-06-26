package cosmetics;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;



public class CosmeticGui implements Listener {
    public Inventory inv;
    
    public String Skull(Player player) {
        String playername = player.getName();
        return playername;
    }
    
    @SuppressWarnings("deprecation")
    public ItemStack getPlayerHead(String player) {
    
        boolean isNewVersion = Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
    
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack playerskull = new ItemStack(type, 1);
        
        if (!isNewVersion)
            playerskull.setDurability((short) 3);
        
        SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        meta.setOwner(player);
        
        playerskull.setItemMeta(meta);
        
        return playerskull;
    }
    
    public void ExampleGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 45, ChatColor.GOLD + ""  
                + ChatColor.BOLD + "Cosmetics");
        //inv.getViewers().get(0).sendMessage("Hi");
        //inv.setItem(40, getPlayerHead(inv.getViewers().get(0).getName()));
        // Put the items into the inventory
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        inv.setItem(10, createGuiItem(Material.PARROT_SPAWN_EGG, ChatColor.GOLD + "Pets"));
        inv.setItem(12, createGuiItem(Material.CARVED_PUMPKIN, ChatColor.GOLD + "Disguises"));
        inv.setItem(14, createGuiItem(Material.STICKY_PISTON, ChatColor.GOLD + "Gadgets"));
        inv.setItem(16, createGuiItem(Material.FIREWORK_ROCKET, ChatColor.GOLD + "Particles"));
        
        //inv.setItem(40, createGuiItem(getPlayerHead(inv.getViewers().get(0).getName()).getType(), "Remove Pet"));
        inv.setItem(41, createGuiItem(Material.ARROW, "Next"));
        
        //inv.setItem(53, createGuiItem(Material.CARROT, "Test Slot"));
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

}