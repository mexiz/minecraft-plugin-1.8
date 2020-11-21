package de.mexiz.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.mexiz.plugin.main.Main;

public class TpacceptCommand implements CommandExecutor,Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		
		
	
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p2 = (Player) sender;
		
		if(args.length == 0) {
			if (Main.tpa.get(p2) != null ) {
				
				Player p = Main.tpa.get(p2);
				p.teleport(p2.getLocation());

				
			}


		}
		
		
		return false;
		
	}

}
