package de.mexiz.plugin.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.mexiz.plugin.main.Main;

public class WorldCommand implements CommandExecutor {
	public WorldCommand(Main main) {
		this.main = main;
		
	}
	Main main;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player) {
			if(args.length == 1 && cmd.getName().equalsIgnoreCase("world")) {
				Player player = (Player) sender;
				checkordnerworld();
				if(args[0].equalsIgnoreCase("2")) {
					
					World w = (World) main.getworld2();
					if(player.getWorld().equals(w)) {
						player.sendMessage("§8[§3mexiz§8]§6 Du befindest dich bereits auf der Welt!");
						return false;
					}
// INVENTORY SPEICHERN	in WELT 1-----------------------------------------------------------------------------------------------------	
					ArrayList<ItemStack> list2 = new ArrayList<>();
					File file2 = new File("plugins//mexiz//WorldInventory//World1//" + player.getName() + ".yml");
					file2.delete();
					checkordnerworld();
					if (!file2.exists()) {
						try {
							file2.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						YamlConfiguration inv = YamlConfiguration.loadConfiguration(file2);
						ItemStack[] contents = player.getInventory().getContents();
						player.getInventory().clear();
						for (int i = 0; i < contents.length; i++) {
							list2.add(contents[i]);
						}
						
						inv.set("Inventory", list2);
						inv.set("XPLVL", player.getLevel());
						player.setLevel(0);
						try {
							inv.save(file2);
						} catch (IOException e) {		
							Bukkit.getServer().broadcastMessage("fail to save");
						}
						
// INVENTORY LADEN	von welt 2	------------------------------------------------------------------------------------------------------	
						File fileworld2 = new File("plugins//mexiz//WorldInventory//World2//" + player.getName() + ".yml");
						if(fileworld2.exists()) {
							YamlConfiguration invworld2 = YamlConfiguration.loadConfiguration(fileworld2);
							player.getInventory().clear();
							ItemStack[] contentsworld2 = player.getInventory().getContents();
							List<?> listworld2 = invworld2.getList("Inventory");
							for (int i = 0; i < listworld2.size(); i++) {	
								contentsworld2[i] = (ItemStack) listworld2.get(i);
							}

							player.getInventory().setContents(contentsworld2);
							player.setLevel(0);
							player.setLevel(invworld2.getInt("XPLVL"));
							
						}else {
							
							
						}
						
					}else {
						
						Bukkit.getServer().broadcastMessage("file2 existiert net");
					}
					
					
					player.setGameMode(GameMode.CREATIVE);
//					Bukkit.getServer().broadcastMessage("KURZ VOR WARP2:" + player.getLevel());
					player.teleport(w.getSpawnLocation());
					player.sendMessage("§8[§3mexiz§8]§6 Du wurdest in die Welt §b" + w.getName() + "§6 teleportiert!");
					
					
					
					
					
					
					
					
// WORLD 111 ----------------------------------------------------------------------------------------------------------------
					
					
			
				}else if(args[0].equalsIgnoreCase("1")) {
					World w = (World) main.getmainWorld();
					
					if(player.getWorld().equals(w)) {
						player.sendMessage("§8[§3mexiz§8]§6 Du befindest dich bereits auf der Welt!");
						return false;
					}
					
// INVENTORY SPEICHERN in welt 2---------------------------------------------------------------------------------------------

					
					ArrayList<ItemStack> list = new ArrayList<>();
					checkordnerworld();
					File file = new File("plugins//mexiz//WorldInventory//World2//" + player.getName() + ".yml");
					file.delete();
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);
						ItemStack[] contents = player.getInventory().getContents();
						player.getInventory().clear();
						for (int i = 0; i < contents.length; i++) {
							list.add(contents[i]);
						}
						inv.set("Inventory", list);
						inv.set("XPLVL", player.getLevel());
						player.setLevel(0);
						try {
							inv.save(file);
						} catch (IOException e) {		
							Bukkit.getServer().broadcastMessage("world2 fail to save");
						}
		
	
// INVENTORY LADEN	WELT 1---------------------------------------------------------------------------------
						
						File fileworld1 = new File("plugins//mexiz//WorldInventory//World1//" + player.getName() + ".yml");
						player.setLevel(0);
						if(fileworld1.exists()) {
							YamlConfiguration invworld1 = YamlConfiguration.loadConfiguration(fileworld1);
							player.getInventory().clear();
							ItemStack[] contentsworld1 = player.getInventory().getContents();
							List<?> listworld1 = invworld1.getList("Inventory");
							for (int i = 0; i < listworld1.size(); i++) {	
								contentsworld1[i] = (ItemStack) listworld1.get(i);
							}
							player.getInventory().setContents(contentsworld1);
							player.setLevel(0);
							player.setLevel(invworld1.getInt("XPLVL"));
						}else {
							
						}
					}else {
						
						Bukkit.getServer().broadcastMessage("file existiert net");
					}
					
					
					
					player.setGameMode(GameMode.SURVIVAL);
//					Bukkit.getServer().broadcastMessage("KURZ VOR WARP ZU 1: " + player.getLevel());
					player.teleport(w.getSpawnLocation());
					player.sendMessage("§8[§3mexiz§8]§6 Du wurdest in die Welt §b" + w.getName() + "§6 teleportiert!");
					
					
				}

			}
			if(args.length == 0 && cmd.getName().equalsIgnoreCase("worlds")) {
				
				
				 
			}
			
			
		}return false;
	}
	public void checkordnerworld() {
		File file = new File("plugins//mexiz//WorldInventory//World2//");
		if(!file.exists()) {
			file.mkdir();
		}
		File file1 = new File("plugins//mexiz//WorldInventory//World1//");
		if(!file1.exists()) {
			file1.mkdir();
		}
	}
}
