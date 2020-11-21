package de.mexiz.plugin.listener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import de.mexiz.plugin.main.Main;

public class InventoryCloseListener implements Listener{
	
	public InventoryCloseListener(Main main) {
		this.main = main;

	}
	Main main;
	
	
	@EventHandler
	public void onBackpackClose(InventoryCloseEvent evt) {
		Player p = (Player) evt.getPlayer();
		
		if(p instanceof Player) {
			if(main.getHashMapInvBackpack(p.getUniqueId()) == evt.getInventory()) {
				checkordner();
				
				ArrayList<ItemStack> list = new ArrayList<>();
				String pname = p.getName();
				File file = new File("plugins//mexiz//Backpack//" + pname + ".yml");
				file.delete();
				if (!file.exists()) {
					
					try {
						file.createNewFile();
					} catch (IOException e) {
						Bukkit.getServer().broadcastMessage("IOexeption backpack");
					}
					YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);
					ItemStack[] contents = main.getHashMapInvBackpack(p.getUniqueId()).getContents();
					for (int i = 0; i < contents.length; i++) {
						list.add(contents[i]);

					}
					inv.set("Inventory", list);
					try {
						inv.save(file);
//						Bukkit.getServer().broadcastMessage("saved");
					} catch (IOException e) {		
						Bukkit.getServer().broadcastMessage("fail to save");
					}
				}else {
					
					Bukkit.getServer().broadcastMessage("file existiert net");
					
				}
				
				
			}else if(main.checkHashMapBackpack(p.getUniqueId()) == false) {
				
			}
			if(main.getgemeinsambackpack() == evt.getInventory()) {
				checkordner2();
				ArrayList<ItemStack> list = new ArrayList<>();
				File file = new File("plugins//mexiz//GemeinschaftsBackpack//gemeinschaftkiste.yml");
				file.delete();
				if (!file.exists()) {
					
					try {
						file.createNewFile();
					} catch (IOException e) {
						Bukkit.getServer().broadcastMessage("IOexeption backpack");
					}
					YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);
					ItemStack[] contents = main.getgemeinsambackpack().getContents();
					for (int i = 0; i < contents.length; i++) {
						list.add(contents[i]);
					}
					inv.set("Inventory", list);
					try {
						inv.save(file);
					} catch (IOException e) {		
						Bukkit.getServer().broadcastMessage("fail to save");
					}
				}else {
					
					Bukkit.getServer().broadcastMessage("file existiert net");
				}
			
				
			}
			
			
			
		}
		
		
		
		
		
		
	}
	public void checkordner() {
		File file = new File("plugins//mexiz//Backpack//");
		if(!file.exists()) {
			file.mkdir();
		}
	}
	public void checkordner2() {
		File file = new File("plugins//mexiz//GemeinschaftsBackpack//");
		if(!file.exists()) {
			file.mkdir();
		}
	}
}
