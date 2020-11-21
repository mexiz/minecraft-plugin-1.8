package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.mexiz.plugin.main.Main;
import net.md_5.bungee.api.ChatColor;

public class GemeinschaftBackPackCommand implements CommandExecutor {
	
	
	 private Main main;
	 
	 public GemeinschaftBackPackCommand(Main main) {this.main = main;}
	 public static Inventory bp;
	 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0 ) {
				if(true) {
					if(main.getgemeinsambackpack() != null) {
						Inventory inv = main.getgemeinsambackpack();
						p.openInventory(inv);
					}else {	
						Inventory inv = Bukkit.getServer().createInventory(null, 54 , ChatColor.GOLD + "GemeinschaftsKiste");
						main.setgemeinsambackpack(inv);
						p.openInventory(inv);
					}		
				}
			}
				
		}
		return false;
	}

}
