package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.bukkit.plugin.Plugin;

import de.mexiz.plugin.main.Main;
import de.mexiz.plugin.program.ConfigManager;
import net.md_5.bungee.api.ChatColor;

public class WarpCommand extends ConfigManager implements CommandExecutor{
	
	public WarpCommand(Main main) {
		super(main, "warps.yml");
	}

//	private Main main ;
//	private Plugin plugin = Main.getPlugin(Main.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.getWorld()==main.getworld2()) {
				player.sendMessage("§8[§3mexiz§8]§6 Auf der Welt: §b"+ player.getWorld().getName() + " §6sind keine Warps möglich!");
				return false;
			}
			if(cmd.getName().equalsIgnoreCase("warp")) {
				if(args.length == 1) {
					if(config.isSet(args[0].toString())){
						float yaw = config.getInt(args[0].toString() + ".yaw");
						float pitch =config.getInt( args[0].toString() + ".pitch");
						float x = config.getInt( args[0].toString() + ".x");
						float y =config.getInt( args[0].toString() + ".y");
						float z = config.getInt( args[0].toString() + ".z");
						World world = Bukkit.getWorld((String)config.get(args[0].toString() + ".world"));
						Location loc = new Location(world, x,y,z,yaw,pitch);
						player.teleport(loc);
						player.sendMessage("§8[§3mexiz§8]§6 Du wurdest gewarpt!");
					}else {
						player.sendMessage("§8[§3mexiz§8]§6 Warpname existiert nicht");
					}
				}else {
					player.sendMessage("§8[§3mexiz§8]§6 Warpname eingeben mit /warp [name]");
				}
			}
			if(cmd.getName().equalsIgnoreCase("setwarp")) {
				if(player.hasPermission("mexiz.setwarp")) {
					if(args.length == 1) {
						if (!config.isSet(args[0].toString())) {
							Location loc = player.getLocation();
							config.set(args[0].toString() + "."  + ".x" , loc.getX());
							config.set(args[0].toString() + "."  + ".y" , loc.getY());
							config.set(args[0].toString() + "."  + ".z" , loc.getZ());
							config.set(args[0].toString() + "."  + ".yaw" , loc.getYaw());
							config.set(args[0].toString() + "."  + ".pitch" , loc.getPitch());
							config.set(args[0].toString() + "."  + ".world" , player.getWorld().getName());
							config.set(args[0].toString() + "."  + ".creator" , player.getName());
							saveconfig();
							player.sendMessage("§8[§3mexiz§8]§6 Warp " + ChatColor.RED + args[0].toString() +  "§6 wurde erstellt!");
						}else {
							player.sendMessage("§8[§3mexiz§8]§6 Der Warpname existiert schon!");
						}
					}else {
						player.sendMessage("§8[§3mexiz§8]§6 Bitte gib /setwarp [name] ein!");
					}
				}	
			}
			if(cmd.getName().equalsIgnoreCase("warps")) {
					StringBuilder sb = new StringBuilder();
					for ( String s : config.getKeys(false)){
						
						sb.append("§5" +s + "§6 (§4" + config.get(s + ".world")+ "§6)" + "§6, ");
					}
					player.sendMessage("§8[§3mexiz§8]§6 " + " Warps: §8" + sb.toString());
				
			}
			if(cmd.getName().equalsIgnoreCase("delwarp")) {
				if(args.length == 1) {
					if (config.isSet(args[0].toString())) {
						config.set(args[0].toString(), null);
						saveconfig();
						player.sendMessage("§8[§3mexiz§8]§6 Warp " + ChatColor.RED + args[0].toString() +  "§6 wurde gelöscht!");
					}else {
						player.sendMessage("§8[§3mexiz§8]§6 " + "Warpname existiert nicht benutze bitte /delwarp [name]!");
					}
				}else {
					player.sendMessage("§8[§3mexiz§8]§6 " + "Bitte gib den WarpNamen an mit /delwarp [name]!");
				}
			}
		}
		return false;
	}

}
