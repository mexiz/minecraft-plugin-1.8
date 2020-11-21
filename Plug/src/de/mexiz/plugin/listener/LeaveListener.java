package de.mexiz.plugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class LeaveListener implements Listener {
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.GOLD + player.getName() + ChatColor.GRAY +  " ist" + " §9PASSIV :( ");
		
		
		
		
	}
	
}
