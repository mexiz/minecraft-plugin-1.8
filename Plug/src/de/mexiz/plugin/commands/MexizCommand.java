package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MexizCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player && ((Player) sender).getPlayer().hasPermission("mexiz.privat")) {
			Player player = ((Player) sender).getPlayer();
			if(args[0].equalsIgnoreCase("invsee")) {
				if(args.length == 2 && Bukkit.getPlayer(args[1]) != null) {
					Player pinvsee = Bukkit.getPlayer(args[1]);
					player.openInventory(pinvsee.getInventory());
				}else return true;
			}else if(args[0].equalsIgnoreCase("endersee")) {
				if(args.length == 2 && Bukkit.getPlayer(args[1]) != null) {
					Player pendersee = Bukkit.getPlayer(args[1]);
					player.openInventory(pendersee.getEnderChest());
				}else return true;
			}else if(args[0].equalsIgnoreCase("workbench")) {
				if(args.length == 1) {
					player.openWorkbench(null, true);
				}else return true;
			}else if(args[0].equalsIgnoreCase("vanish")) {
				if(args.length == 1) {
					for(Player p : Bukkit.getOnlinePlayers()) {
			            p.hidePlayer(player);
			            }
					Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.GRAY +  " ist" + " §9PASSIV :( ");
				}else return true;
			}else if(args[0].equalsIgnoreCase("unvanish")) {
				if(args.length == 1) {
					for(Player p : Bukkit.getOnlinePlayers()) {
			            p.showPlayer(player);
			            p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 100000000);
			        	}
					Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.GRAY + " ist " + "§4AKTIV");

				}else return true;
			}else if(args[0].equalsIgnoreCase("d")) {
				player.getWorld().setTime(0);
				
			}else if(args[0].equalsIgnoreCase("n")) {
				player.getWorld().setTime(13000);
				
			}else if(args[0].equalsIgnoreCase("w")) {
				player.getWorld().setStorm(false);
				player.getWorld().setThundering(false);
				
			}else return true;

		}		
		return true;
	}
}
