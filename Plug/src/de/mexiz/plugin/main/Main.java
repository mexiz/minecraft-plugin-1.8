package de.mexiz.plugin.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.mexiz.plugin.commands.BackpackCommand;
import de.mexiz.plugin.commands.CoinFlipCommand;
import de.mexiz.plugin.commands.DeathPointCommand;
import de.mexiz.plugin.commands.GemeinschaftBackPackCommand;
import de.mexiz.plugin.commands.HealCommand;
import de.mexiz.plugin.commands.MexizCommand;
import de.mexiz.plugin.commands.ThreebyThreeCommand;
import de.mexiz.plugin.commands.TpaCommand;
import de.mexiz.plugin.commands.TpacceptCommand;
import de.mexiz.plugin.commands.TrashCommand;
import de.mexiz.plugin.commands.WarpCommand;
import de.mexiz.plugin.commands.WorldCommand;
import de.mexiz.plugin.commands.gmCommand;
import de.mexiz.plugin.listener.InventoryCloseListener;
import de.mexiz.plugin.listener.JoinListener;
import de.mexiz.plugin.listener.LeaveListener;
import de.mexiz.plugin.listener.OnPlayerDeath;
import de.mexiz.plugin.listener.PortalListener;
import de.mexiz.plugin.listener.SleepListener;
import de.mexiz.plugin.listener.ThreebyThreeListener;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
// WORLD
	public World world2;
	public World mainworld;
	public World mainnether;
	public World mainend;
//------------------------------------------------------------
// SleepListener
	public int imBett;
//------------------------------------------------------------
// Teleport
	public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	public static Plugin instance;
//------------------------------------------------------------
// 3x3
	public Logger log = null;
	private String lore = "�3�o eine Spitzhacke vom Meister";
	private int radius = 3;
	private boolean credit;
	private boolean natural;
	//TEST
//------------------------------------------------------------
// Backpack
	public HashMap<UUID, Inventory> backpack = new HashMap<UUID, Inventory>();
	public Inventory gemeinschaftsbackpack;
//------------------------------------------------------------
// ONENABLE
	public void onEnable() {

// Backpack
		for(Player pBackpack : Bukkit.getOnlinePlayers())backpackload(pBackpack);

//------------------------------------------------------------
		if(!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		createWorld();
		loadConfig();
// Commands
		getCommand("trash").setExecutor(new TrashCommand());
		getCommand("g").setExecutor(new GemeinschaftBackPackCommand(this));
		getCommand("flip").setExecutor(new CoinFlipCommand());
		getCommand("world").setExecutor(new WorldCommand(this));
		getCommand("worlds").setExecutor(new WorldCommand(this));
		getCommand("warp").setExecutor(new WarpCommand(this));
		getCommand("setwarp").setExecutor(new WarpCommand(this));
		getCommand("warps").setExecutor(new WarpCommand(this));
		getCommand("delwarp").setExecutor(new WarpCommand(this));
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("tpa").setExecutor(new TpaCommand());
		getCommand("tpaccept").setExecutor(new TpacceptCommand());
		getCommand("gm").setExecutor(new gmCommand());
		getCommand("threebythree").setExecutor(new ThreebyThreeCommand(this));
		getCommand("bp").setExecutor(new BackpackCommand(this));
		getCommand("b").setExecutor(new BackpackCommand(this));
		getCommand("deathpoint").setExecutor(new DeathPointCommand(this));
		getCommand("mexiz").setExecutor(new MexizCommand());
		getCommand("m").setExecutor(new MexizCommand());

//------------------------------------------------------------
//EventListeners
		PluginManager pluginmanager = Bukkit.getPluginManager();
		pluginmanager.registerEvents(new PortalListener(this), this);
		pluginmanager.registerEvents(new OnPlayerDeath(this), this);
//JoinListener
		pluginmanager.registerEvents(new JoinListener(this), this);
		pluginmanager.registerEvents(new LeaveListener(), this);
		pluginmanager.registerEvents(new SleepListener(this), this);
//InventoryCloseListener
		pluginmanager.registerEvents(new InventoryCloseListener(this), this);
// 3x3
		getServer().getPluginManager().registerEvents(new ThreebyThreeListener(this), this);

//------------------------------------------------------------
		this.log = Logger.getLogger("Minecraft");
// 3x3
		setupConfigValues();
//------------------------------------------------------------
		recipesetter();
		Bukkit.getConsoleSender().sendMessage("�8|-----------------------------------------------|");
		Bukkit.getConsoleSender().sendMessage("�b|------mexiz------------------------------------|");
		Bukkit.getConsoleSender().sendMessage("�6|-----------mexiz-------------------------------|");
		Bukkit.getConsoleSender().sendMessage("�5|----------------mexiz--------------------------|");
		Bukkit.getConsoleSender().sendMessage("�4|---------------------mexiz---------------------|");
		Bukkit.getConsoleSender().sendMessage("�5|--------------------------mexiz----------------|");
		Bukkit.getConsoleSender().sendMessage("�6|-------------------------------mexiz-----------|");
		Bukkit.getConsoleSender().sendMessage("�b|------------------------------------mexiz------|");
		Bukkit.getConsoleSender().sendMessage("�8|_______________________________________________|");

	}
//------------------------------------------------------------
// ONDISABLE
	public void onDisable() {
	}
//------------------------------------------------------------
// 3x3
	private void setupConfigValues() {
		this.radius = 10;
		this.credit = true;
		this.natural = true;
		this.lore = "�3�o eine Spitzhacke vom Meister";
	}
	@SuppressWarnings("deprecation")
	private void recipesetter() {

		ItemStack bomer = new ItemStack(Material.DIAMOND_PICKAXE);
		bomer.setDurability((short) 11);
		ItemMeta bomerMeta = bomer.getItemMeta();
		List<String> fore = new ArrayList<String>();
		fore.add(lore);
		bomerMeta.setLore(fore);
		bomerMeta.setDisplayName("�63x3�4Pickaxe�8-�6Noch:�8-�a" + ((1561 - bomer.getDurability())) / 30);
		bomer.setItemMeta(bomerMeta);

		ShapedRecipe bomerRecipe = new ShapedRecipe(bomer);
		bomerRecipe.shape("MMM", "AFA", "AFA");
		bomerRecipe.setIngredient('M', Material.DIAMOND_BLOCK);
		bomerRecipe.setIngredient('F', Material.STICK);

		Bukkit.addRecipe(bomerRecipe);

	}
	public boolean isPickaxe(ItemStack item) {

		ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
		if (item.getType() == pickaxe.getType()) {
			return true;
		}
		return false;
	}
	public String getLore() {
		return this.lore;
	}
	public int getRadius() {
		return this.radius;
	}
	public boolean getCredit() {
		return this.credit;
	}
	public boolean getNatural() {
		return this.natural;
	}
//------------------------------------------------------------
// Backpack and all together backpack
	public Boolean checkHashMapBackpack(UUID p) {
		if (backpack.get(p) != null)return true;
		return false;
	}
	public void setHashMapBackpack(UUID p, Inventory inv) {
		this.backpack.put(p, inv);

	}
	public HashMap<UUID, Inventory> getHashMapBackpack() {
		return this.backpack;

	}
	public Inventory getHashMapInvBackpack(UUID p) {
		return this.backpack.get(p);

	}
	public void backpackload(Player a) {
		//BackPack
		if(!this.checkHashMapBackpack(a.getUniqueId())) {
			Inventory inv = Bukkit.getServer().createInventory(a, InventoryType.CHEST , "�6BackPack von �c" + a.getName());
			this.setHashMapBackpack(a.getUniqueId(), inv);
		}
		File file = new File("plugins//mexiz//Backpack//" + a.getName() + ".yml" );
		if(file.exists()) {
			YamlConfiguration inv1 = YamlConfiguration.loadConfiguration(file);
			Inventory lol = this.backpack.get(a.getUniqueId());

			lol.clear();
			ItemStack[] contents = lol.getContents();
			List<?> list = inv1.getList("Inventory");
			for (int i = 0; i < list.size(); i++) {
				contents[i] = (ItemStack) list.get(i);
			}
			lol.setContents(contents);
			this.setHashMapBackpack(a.getUniqueId(), lol);
		}
		//Gemeinschaftskiste
		if(this.getgemeinsambackpack() == null) {
			Inventory inv = Bukkit.getServer().createInventory(null, 54 , ChatColor.GOLD + "GemeinschaftsKiste");
			this.setgemeinsambackpack(inv);
		}
		File file2 = new File("plugins//mexiz//GemeinschaftsBackpack//gemeinschaftkiste.yml");
		if(file2.exists()) {
			YamlConfiguration inv2 = YamlConfiguration.loadConfiguration(file2);
			Inventory lol2 = this.getgemeinsambackpack();
			lol2.clear();
			ItemStack[] contents1 = lol2.getContents();
			List<?> list2 = inv2.getList("Inventory");
			for (int i = 0; i < list2.size(); i++) {
				contents1[i] = (ItemStack) list2.get(i);
			}
			lol2.setContents(contents1);
			this.setgemeinsambackpack(lol2);
		}


	}
	public void setgemeinsambackpack(Inventory inv) {
		this.gemeinschaftsbackpack = inv;
	}
	public Inventory getgemeinsambackpack() {
		return this.gemeinschaftsbackpack;
	}
//------------------------------------------------------------
// Worlds
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	public void createWorld() {
		WorldCreator c = new WorldCreator("world2");
		c.generateStructures(false);
		world2 = c.createWorld();
		mainworld = Bukkit.getServer().getWorlds().get(0);
		mainnether = Bukkit.getServer().getWorlds().get(1);
		mainend = Bukkit.getServer().getWorlds().get(2);

	}
	public World getworld2() {
		return world2;
	}
	public World getmainWorld() {
		return mainworld;
	}
	public World getmainnether() {
		return mainnether;
	}
	public World getmainend() {
		return mainend;
	}
//------------------------------------------------------------
// SleepListener
	public void setimBett(Boolean plusoderminus1) {
		if(plusoderminus1) {
			//TRUE == plus
			this.imBett++;
		}else {
			//FALSE == minus
			this.imBett--;
		}
	}
	public int getimBett() {
		return this.imBett;
	}
//------------------------------------------------------------
}
