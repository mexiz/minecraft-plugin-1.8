package de.mexiz.plugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mexiz.plugin.main.Main;

public class ThreebyThreeCommand implements CommandExecutor {

	 private Main main;
	 
	 public ThreebyThreeCommand(Main main) {this.main = main;}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("threebythree")) || (cmd.getName().equalsIgnoreCase("threebythreeby"))) {
		      if ((sender instanceof Player)) {
		        Player player = (Player)sender;
		        ItemStack item = player.getItemInHand();
		        if (args.length == 1) {
		          if (args[0].equalsIgnoreCase("enchant")) {
		            if (player.hasPermission("mexiz.threebythreeuse")) {
					
		              if (this.main.isPickaxe(item)) {
		
		                ItemMeta meta = item.getItemMeta();
		                meta.setDisplayName("§63x3§4Pickaxe§8-§6Noch:§8-§a" + ((1561 - item.getDurability()))/30 );
		                List<String> lore = new ArrayList<String>();
		                lore.add(this.main.getLore());
		                meta.setLore(lore);
		                item.setItemMeta(meta);
		                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "§8[§3mexiz§8]§c &aErfolgreich &3Enchanted"));
		              } else {
		                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "§8[§3mexiz§8]§c &4Keine Dia Spitzaxt."));
		              }
		            } else {
		              player.sendMessage(ChatColor.translateAlternateColorCodes('&', "§8[§3mexiz§8]§c &4Keine Premission!"));
		            }
		            return true;
		          }
		        } else { 
		        	if (this.main.getCredit()) {
//		            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Plugin by: &eMexiz&a."));
		        	}
		        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "§8[§3mexiz§8]§c &cBenutze /threebythree enchant"));
		            return true;}
		         
		      }
		      else {
		        sender.sendMessage("Cannot execute that command as console.");
		        return true;
		      }
		    }
			return false;
	}

}
