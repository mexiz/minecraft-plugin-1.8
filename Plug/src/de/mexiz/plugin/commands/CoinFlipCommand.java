package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinFlipCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		double random = Math.random();
		Player player = (Player) sender;
		if(args.length == 1) {
			if(Bukkit.getPlayer(args[0]) != null) {
			Player player2 = Bukkit.getPlayer(args[0]);
				if (player2 != null) {
					Bukkit.getServer().broadcastMessage("§8[§3mexiz-coinflip§8]§4 " + player.getName() + " §6gegen §4" + player2.getName());
					if(random  < 0.5) {
						Bukkit.getServer().broadcastMessage("§8[§3mexiz-coinflip§8]§6 " + player2.getName() + " hat gewonnen!");
					}else if(random > 0.5) {
						Bukkit.getServer().broadcastMessage("§8[§3mexiz-coinflip§8]§6 " + player.getName() + " hat verloren!");
					}else {
						Bukkit.getServer().broadcastMessage("§8[§3mexiz-coinflip §8]§6 Unentschieden!");
					}
				}
			}else {
				player.sendMessage("§8[§3mexiz§8] §6Bitte benutz nur /flip [name]");
			}
		}
		return false;
	}
}


