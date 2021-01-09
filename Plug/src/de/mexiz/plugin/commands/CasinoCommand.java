package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import de.mexiz.plugin.main.Main;
import net.minecraft.server.v1_16_R2.BlockBase.BlockData;

public class CasinoCommand implements CommandExecutor{
	
	public CasinoCommand (Main main) {
		
		this.main = main;
	}
	Main main;
	private Plugin plugin = Main.getPlugin(Main.class);
	test test = new test();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
//			player.sendMessage("[mexiz] " + player.getTargetBlock(null, 200));
			if (player.getTargetBlock(null, 200).getBlockData().getMaterial() == Material.BARREL ) {
				player.sendMessage("Barrel erkannt.");
				Block b = player.getTargetBlock(null, 200);
				b.setMetadata("owner", new FixedMetadataValue(plugin, test));
				
				
				if(b.getMetadata("owner").equals(test)) {
					player.sendMessage("yes");
					
				}
				
				
				
				
			} 
		}
		
		
		return false;
	}
}
