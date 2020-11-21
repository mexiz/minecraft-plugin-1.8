package de.mexiz.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import de.mexiz.plugin.main.Main;

public class BackpackCommand implements CommandExecutor {

	private Main main;

	public BackpackCommand(Main main) {
		this.main = main;
	}

	public static Inventory bp;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (main.checkHashMapBackpack(p.getUniqueId())) {
					p.openInventory(main.backpack.get(p.getUniqueId()));
				} else {
					Inventory inv = Bukkit.getServer().createInventory(p, InventoryType.CHEST,
							"§6BackPack von §c" + p.getName());
					((Main) main).setHashMapBackpack(p.getUniqueId(), inv);
					p.openInventory(main.backpack.get(p.getUniqueId()));
				}

			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("clear")) {
					Inventory inv1 = main.backpack.get(p.getUniqueId());
					if (main.checkHashMapBackpack(p.getUniqueId())) {
						inv1.clear();
						main.setHashMapBackpack(p.getUniqueId(), inv1);
						p.sendMessage("§8[§3mexiz§8] §6Das BackPack wurde §4geclear");
					} else {
						p.sendMessage("§8[§3mexiz§8] §6Du hast kein BackPack");
					}
				} else
					p.sendMessage("§8[§3mexiz§8] §6Bitte benutze §4/bp clear");
			}
			return false;

		}
		return false;
	}

}
