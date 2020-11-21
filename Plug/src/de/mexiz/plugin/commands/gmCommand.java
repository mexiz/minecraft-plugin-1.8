package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("mexiz.gmuse")) {
	
				if(args.length == 1 ) {
					
					if(args[0].equalsIgnoreCase("1")) {
						
						player.setGameMode(GameMode.CREATIVE);
						player.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Creative");
						
					}else if(args[0].equalsIgnoreCase("2")) {
						
						player.setGameMode(GameMode.ADVENTURE);
						player.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Adventure");
						
					}else if(args[0].equalsIgnoreCase("3")) {
						
						player.setGameMode(GameMode.SPECTATOR);	
						player.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Spectator");
					}else if(args[0].equalsIgnoreCase("0")) {
						
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Survival");
						
					}else {
						player.sendMessage("§8[§3mexiz§8] §6Bitte benutze: §4/gm §8<§40,1,2,3§8> <§4Player§8>§4!!");
					}

				}else if (args.length == 2) {
					
					Player target = Bukkit.getPlayer(args[1]);
					if (target != null) {
						
						if(args[0].equalsIgnoreCase("1")) {
							
							target.setGameMode(GameMode.CREATIVE);
							target.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Creative§6 für §4" + target.getName());
							
						}else if(args[0].equalsIgnoreCase("2")) {
							
							target.setGameMode(GameMode.ADVENTURE);
							target.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Adventure§6 für §4" + target.getName());
							
						}else if(args[0].equalsIgnoreCase("3")) {
							
							target.setGameMode(GameMode.SPECTATOR);
							target.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Spectator§6 für §4" + target.getName());
						}else if(args[0].equalsIgnoreCase("0")) {
							
							target.setGameMode(GameMode.SURVIVAL);
							target.sendMessage("§8[§3mexiz§8] §6Gamemode: §4Survival§6 für §4" + target.getName());
							
						}else {
							player.sendMessage("§8[§3mexiz§8] §6Bitte benutze: §4/gm §8<§40,1,2,3§8> <§4Player§8>§4!!");
						}
					}else player.sendMessage("§8[§3mexiz§8]" +"§4Spieler§6 wurde nicht gefunden!");
					
					
					
					
				}else {
					
					player.sendMessage("§8[§3mexiz§8] " + "§6Bitte benutze: §4/gm §8<§40,1,2,3§8> <§4Player§8>§4!!");
					
				}
				
				
			}
			
			
			
			
			
			
			
		}
		return false;
	}
	
	

}
