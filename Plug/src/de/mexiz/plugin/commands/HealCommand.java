package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arg) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			if(player.hasPermission("mexiz.healall") ) {
				if (arg.length == 0) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage("§8[§3mexiz§8] §6Du hast dich gehealt!");
					
				}else if(arg.length == 1) {
					
					Player target = Bukkit.getPlayer(arg[0]);
					if (target != null) {
						
					target.setHealth(20);
					target.setFoodLevel(20);
					target.sendMessage("§8[§3mexiz§8]§c " + player.getName() + " §6hat dich gehealt");
					player.sendMessage("§8[§3mexiz§8]§c " + target.getName() + " §6hat dich gehealt");
					
					}else player.sendMessage("§8[§3mexiz§8] §6Spieler wurde nicht gefunden!");
					
				}
				
			}else player.sendMessage("§8[§3mexiz§8]§6 keine Premissions oder Command falsch");	
		}else sender.sendMessage("§8[§3mexiz§8]§6 Dummkopf!");
		
		return false;
	}
	

	

}
