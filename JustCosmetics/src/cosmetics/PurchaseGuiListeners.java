package cosmetics;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import cosmetics.PurchaseConstructor;

public class PurchaseGuiListeners implements Listener {

    public PurchaseGui purchasegui = Cosmetics.purchasegui;
    public PurchaseConstructor PurchaseConstructor = Cosmetics.PurchaseConstructor;
    public HashMap<Player, String> purchaseItem = Cosmetics.purchaseItem;
    public HashMap<Player, Integer> purchasePrice = Cosmetics.purchasePrice;
    
    private Cosmetics plugin;

    public PurchaseGuiListeners(Cosmetics b) {
        plugin = b;
    }
    
    //////
    //Clicking Inside the main Main Gui
    @EventHandler()
    public void onPurchaseGuiClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(purchasegui.inv))
            return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        
        event.setCancelled(true);
        
        Player player = (Player) event.getWhoClicked();
        plugin.dataSlime.getSlime(player.getUniqueId()); //Dont need this line
        
        if (event.getSlot() == 20) {
            PurchaseConstructor.purchaseFinal(player, purchaseItem.get(player), purchasePrice.get(player));
            
            purchaseItem.remove(player);
            purchasePrice.remove(player);
            
            player.closeInventory();
        }
        
        if (event.getSlot() == 24) {
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "Transaction Cancelled");
            
            purchaseItem.remove(player);
            purchasePrice.remove(player);
        }

    }
    
    
}