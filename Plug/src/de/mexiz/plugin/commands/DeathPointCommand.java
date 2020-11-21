package de.mexiz.plugin.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.mexiz.plugin.main.Main;
import net.md_5.bungee.api.ChatColor;

public class DeathPointCommand implements CommandExecutor{	
	public DeathPointCommand(Main main) {this.main = main;}
	public Main main;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			File file = new File("plugins//mexiz//death//" + p.getName() + ".yml");
			if(file.exists()) {
				YamlConfiguration log = YamlConfiguration.loadConfiguration(file);
				World w = Bukkit.getWorld((String)log.get( "Death" + ".world"));
				float x = log.getInt("Death" + ".x");
				float y =log.getInt( "Death" + ".y");
				float z = log.getInt( "Death" + ".z");
				
				p.sendMessage("§8[§3mexiz§8]§6 Du bist in der Welt: "+ ChatColor.DARK_AQUA+ w.getName() +  " §6bei x: "
				+ ChatColor.DARK_AQUA + x + "§6 y: "+ ChatColor.DARK_AQUA + y + " §6z: "+ ChatColor.DARK_AQUA + z +" §6gestorben!");

				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("tp") && p.hasPermission("mexiz.deathtp")) {
						World world = Bukkit.getWorld((String)log.get( "Death" + ".world"));
						if(world == main.getworld2()) return false;
						float yaw = log.getInt("Death" +".yaw");
						float pitch =log.getInt("Death" + ".pitch");
						Location loc = new Location(world, x ,y ,z ,yaw ,pitch );
					p.teleport(loc);
					}
				}else return true;
				
			}else {
				p.sendMessage("§8[§3mexiz§8]§6 Du bist nicht gestorben!");
				return false;
			}
		}
		return false;
	}

}
