package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mexiz.plugin.main.Main;
import net.md_5.bungee.api.ChatColor;

public class TpaCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if(args.length == 1 && Bukkit.getPlayer(args[0]) != null) {
			Player p2 = Bukkit.getPlayer(args[0]);
			if(p2 != null) {
				
				Main.tpa.put(p2, p);
				p.sendMessage("§8[§3mexiz§8]§c " + ChatColor.GOLD + "Du hast eine TPA-Anfrage an " + ChatColor.RED + p.getName() + ChatColor.GOLD + 
						" geschickt!");

				p2.sendMessage("§8[§3mexiz§8]§c " + ChatColor.GOLD + "Du hast eine TPA-Anfrage von " + ChatColor.RED + p.getName() + ChatColor.GOLD +
						" mit /tpaccept nimmst du sie an!");
			}
			
			
	
			
			
		}else {
			
			p.sendMessage("§8[§3mexiz§8]§c " + ChatColor.DARK_RED + "Name richtig angeben");
		}
		
	
		return true;
	}

}
