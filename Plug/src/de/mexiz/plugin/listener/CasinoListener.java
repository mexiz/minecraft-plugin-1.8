package de.mexiz.plugin.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.mexiz.plugin.main.Main;
import net.md_5.bungee.api.ChatColor;

public class CasinoListener implements Listener{
	
	public CasinoListener(Main main) {
		this.main = main;
	}
	Main main;
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
//		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.BARREL) {
//			p.sendMessage(ChatColor.RED + "" + main.casinoblock);
//			p.sendMessage(ChatColor.GREEN+ "" + b);
//		}
		
	}

}
