package de.mexiz.plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitScheduler;

import de.mexiz.plugin.main.Main;

public class SleepListener implements Listener{

	public SleepListener(Main main) {this.main = main;}
	public Main main;
	
	@EventHandler
	public void onBed(PlayerBedEnterEvent e) {
		
		Player p = e.getPlayer();

		if(e.getBedEnterResult() == BedEnterResult.OK) {
			
			main.setimBett(true);
			Bukkit.getServer().broadcastMessage("§c" + p.getName() + " §3befindet sich in seiner Schlafstätte!");	
			Bukkit.getServer().broadcastMessage("§3Mit ihm befinden sich (§c" + main.getimBett() +" §3von §c" + Bukkit.getOnlinePlayers().size() +"§3) Spielern im Bett!" );
	        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncDelayedTask(main, new Runnable() {
	            @Override
	            public void run() {
	            	if(e.isCancelled()) {
	            		return;
	            		
	            	}else {
	            		p.getWorld().setTime(0);
	            		p.getWorld().setThundering(false);
	            		p.getWorld().setStorm(false);
	            		p.setBedSpawnLocation(e.getBed().getLocation());
	            		Bukkit.getServer().broadcastMessage("§cGuten Morgen!");	
	            	}
	            }
	        }, 100);
			
			
		}else {
			e.setCancelled(true);
			return;
		}
		
		
		
		
		
	}
	@EventHandler
	public void onBed(PlayerBedLeaveEvent e) {
		main.setimBett(false);
	}
}
