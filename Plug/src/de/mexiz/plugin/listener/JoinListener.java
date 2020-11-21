package de.mexiz.plugin.listener;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import de.mexiz.plugin.main.Main;
import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {
	

	public JoinListener(Main main){
		this.main = main;
		
	}
	Main main;
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(ChatColor.GOLD + event.getPlayer().getName() + ChatColor.GRAY + " ist " + "§4AKTIV");
		Player a = event.getPlayer();
		if(!main.checkHashMapBackpack(a.getUniqueId())) {
			Inventory inv = Bukkit.getServer().createInventory(a, InventoryType.CHEST , "§6BackPack von §c" + a.getName());
			((Main) main).setHashMapBackpack(a.getUniqueId(), inv);
		}
		File file = new File("plugins//mexiz//Backpack//" + a.getName() + ".yml" );
		if(file.exists()) {
			YamlConfiguration inv1 = YamlConfiguration.loadConfiguration(file);
			Inventory lol = main.backpack.get(a.getUniqueId());

			lol.clear();
			ItemStack[] contents = lol.getContents();
			List<?> list = inv1.getList("Inventory");
			for (int i = 0; i < list.size(); i++) {	
				contents[i] = (ItemStack) list.get(i);
			}
			lol.setContents(contents);
			main.setHashMapBackpack(a.getUniqueId(), lol);
		}

		for(Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 100000000);
            }

			
			plugin.getConfig().set("JOINIP." + a.getUniqueId() + "." + a.getName() + ".IP" , String.valueOf(a.getAddress().getAddress()));
			plugin.saveConfig();
			
	}
}
