package com.okapi.okapimanager.settings;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.okapi.okapimanager.util.OkapiConfig;

public class BorderSettings extends BaseSettings{
	private final static Logger logger = Logger.getLogger("Minecraft");
	
	public static boolean GlobalBorderEnabled;
	public static String BorderMessage;
	
	public boolean BorderEnabled;
	public int MinimumX;
	public int MaximumX;
	public int MinimumZ;
	public int MaximumZ;
	
	public static void Load(){
		File configFile = new File("plugins/OkapiManager/BorderSettings.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		GlobalBorderEnabled = config.getBoolean("Settings.Global-Border-Enabled", false);
		BorderMessage = config.getString("Settings.Border-Message", "You have reached the border!");
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			logger.warning("An error occurred while loading the border config file!");
		}
	}
	
	public void LoadSettings(String world) {
		File configFile = new File("plugins/OkapiManager/BorderSettings.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		BorderEnabled = config.getBoolean("Worlds." + world + ".Border-Enabled", false);
		MinimumX = config.getInt("Worlds." + world + ".Positions.Minimum-X", 0);
		MaximumX = config.getInt("Worlds." + world + ".Positions.Maximum-X", 0);
		MinimumZ = config.getInt("Worlds." + world + ".Positions.Minimum-Z", 0);
		MaximumZ = config.getInt("Worlds." + world + ".Positions.Maximum-Z", 0);
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			logger.warning("An error occurred while loading the border config file!");
		}
	}

	public void SaveSettings(String world) {
		File configFile = new File("plugins/OkapiManager/BorderSettings.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		config.set("Worlds." + world + ".Border-Enabled", BorderEnabled);
		config.set("Worlds." + world + ".Positions.Minimum-X", MinimumX);
		config.set("Worlds." + world + ".Positions.Maximum-X", MaximumX);
		config.set("Worlds." + world + ".Positions.Minimum-Z", MinimumZ);
		config.set("Worlds." + world + ".Positions.Maximum-Z", MaximumZ);
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			logger.warning("An error occurred while loading the border config file!");
		}
	}
}
