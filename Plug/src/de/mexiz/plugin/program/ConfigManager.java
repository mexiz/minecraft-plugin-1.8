package de.mexiz.plugin.program;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.mexiz.plugin.main.Main;

public class ConfigManager {
	
	protected Main main;
	protected FileConfiguration config;
	private File file;
	
	public ConfigManager(Main main, String fileName) {
		this.main = main;
		this.file = new File(main.getDataFolder(), fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();

				
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);	
		
		
	}
	
	public void saveconfig() {
		try {
			config.save(file);
		}catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
