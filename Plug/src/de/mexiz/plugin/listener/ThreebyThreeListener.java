package de.mexiz.plugin.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mexiz.plugin.main.Main;


public class ThreebyThreeListener implements Listener {
	
	  private Main main;  // Objekt von der Main 

	  public ThreebyThreeListener(Main main) {
	    this.main = main;
	  }
	
	
	  @SuppressWarnings("deprecation")
	@EventHandler
	  public void onBlockBroken(BlockBreakEvent evt) {
	    Player player = evt.getPlayer();
	    if (true) {
	    	ItemStack item = player.getItemInHand();
	    	
			
				if( item.hasItemMeta() && item.getItemMeta().hasLore() &&
						(((String)item.getItemMeta().getLore().get(0)).equalsIgnoreCase(this.main.getLore())) && 
						item.getDurability() < item.getType().getMaxDurability()){
					Block block = evt.getBlock();
					item.setDurability((short) (item.getDurability() + 30));
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName("§63x3§4Pickaxe§8-§6Noch:§8-§a" + ((1561 - item.getDurability()))/30 );
					
					item.setItemMeta(itemMeta);
					
					if((1561-item.getDurability()) <= 0 ) {
						
						player.getInventory().remove(item);
						
					}
					
					
					
//					player.sendMessage(String.valueOf(item.getDurability()));
//					player.sendMessage(" Error fix: §e3x3");
					
					
				    Location loc = block.getLocation();
				    double X = loc.getBlockX();
				    double Y = loc.getBlockY();
				    double Z = loc.getBlockZ();
				    if (this.main.getNatural()) {
				      int radius = 3 - 2;
				      double minX = X - radius;
				      double maxX = X + radius + 1.0D;
				      double minY = Y - radius;
				      double maxY = Y + radius + 1.0D;
				      double minZ = Z - radius;
				      double maxZ = Z + radius + 1.0D;
				      for (double x = minX; x < maxX; x += 1.0D) {
				        for (double y = minY; y < maxY; y += 1.0D) {
				          for (double z = minZ; z < maxZ; z += 1.0D){
				            Location location = new Location(block.getWorld(), x, y, z);
				            if (location.getBlock().getType() != Material.BEDROCK &&
				            		location.getBlock().getType() != Material.WATER &&
				            		location.getBlock().getType() != Material.LAVA &&
				            		location.getBlock().getType() != Material.OBSIDIAN)location.getBlock().breakNaturally(item);
				          }
				        }
				      }
				    }
				    
				    
				} else {
					
//					player.sendMessage("Error fix: §dkein 3x3");
					
				}
				
			
			}
	    } 
	  

}