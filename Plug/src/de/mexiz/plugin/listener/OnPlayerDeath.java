package de.mexiz.plugin.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.mexiz.plugin.main.Main;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerDeath implements Listener {
	public OnPlayerDeath(Main main) {
		this.main = main;
	}
	public Main main;
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		
		Player p = event.getEntity();
		Location loc = p.getLocation();
		checkordner();
		File file = new File("plugins//mexiz//death//" + p.getName() + ".yml");
		file.delete();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			YamlConfiguration dyml = YamlConfiguration.loadConfiguration(file);
			dyml.set("Death" + "."  + ".x" , loc.getX());
			dyml.set("Death"+ "."  + ".y" , loc.getY());
			dyml.set("Death"+ "."  + ".z" , loc.getZ());
			dyml.set("Death" + "."  + ".yaw" , loc.getYaw());
			dyml.set("Death" + "."  + ".pitch" , loc.getPitch());
			dyml.set("Death"+ "."  + ".world" , p.getWorld().getName());
			dyml.set("Death"+ "."  + ".creator" , p.getName());
			try {
				dyml.save(file);
			} catch (IOException e) {		
				Bukkit.getServer().broadcastMessage("§bDEATHLOG fail to save");
			}
		}
	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		if(p.getWorld() == main.getworld2()) {
			Bukkit.getServer().broadcastMessage(ChatColor.RED + "DEATH in world 2");
			World w = p.getWorld();
			Location loc1 = w.getSpawnLocation();
			
			e.setRespawnLocation(loc1);

		}
				
		
		
	}
	public void checkordner() {
		File file = new File("plugins//mexiz//death//");
		if(!file.exists()) {
			file.mkdir();
		}
	}
}

