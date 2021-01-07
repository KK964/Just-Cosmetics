package cosmetics.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import cosmetics.Cosmetics;
import cosmetics.RemoveEffectsOnQuit;
import cosmetics.pets.listeners.PetGuiListeners;

public class PetNameCommand implements CommandExecutor {
    
    private Cosmetics plugin;
    public PetNameCommand(Cosmetics b) {
        plugin = b;
    }
    
    public PetGuiListeners PetSpawn = new PetGuiListeners(plugin);
    public static RemoveEffectsOnQuit RemoveEffectsOnQuit = new RemoveEffectsOnQuit();
    
    public HashMap<Player, Entity> currentPet = PetGuiListeners.currentPet;
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            
            if (cmd.getName().equalsIgnoreCase("petname")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Console cannot run this command!");
                    return true;
                }
                Player player = (Player) sender;
                //System.out.println(plugin);
                //System.out.println("test");
                
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "/petname <name>" );
                    return true;
                }
                
                if (args.length == 1) {
                    try {
                        //System.out.println(player);
                        //System.out.println(args[0]);
                        if (plugin.dataPetNames.existsPlayer(player.getUniqueId())) {
                            plugin.dataPetNames.remove(player.getUniqueId());
                        } 
                        plugin.dataPetNames.addPetName(player, player.getUniqueId(), args[0].toString());
                        currentPet.get(player).setCustomName(ChatColor.GOLD + args[0].toString());
                        
                        player.sendMessage(ChatColor.GOLD + "Pet name has been set!");
                        
                        
                    } catch (NumberFormatException exception) { player.sendMessage(ChatColor.RED + "/petname <name>"); }
                    
                    return true;
                }
                
//                else {
//                    player.sendMessage(ChatColor.RED + "/petname <name>");
//                    //return true;
//                }
                
                return true;
            }
    
            return false;
        }
        
    
}