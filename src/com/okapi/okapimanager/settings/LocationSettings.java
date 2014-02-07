package com.okapi.okapimanager.settings;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.okapi.okapimanager.util.OkapiConfig;

public class LocationSettings extends BaseSettings{
	
	private Logger logger = Logger.getLogger("Minecraft");
	private String world;
	public HashMap<String, Location> locations = new HashMap<String, Location>();

	public LocationSettings(String world){
		this.world = world;
	}
	
	public void LoadSettings(String location){
		File configFile = new File(location + world + ".yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		Set<String> locs = config.getKeys(false);
		
		for(String str : locs){
			Location loc = config.getLocation(str, new Location(Bukkit.getWorld(world), 0, 0, 0, 0, 0));
			
			locations.put(str, loc);
		}
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			logger.warning("An error occurred while loading the border config file!");
		}
	}
	
	public void SaveSettings(String location) {
		File configFile = new File(location + world + ".yml");
		
		if(configFile.exists()){
			configFile.delete();
		}
		
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		for(String str : locations.keySet()){
			config.setLocation(str, locations.get(str));
		}
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			logger.warning("An error occurred while loading the border config file!");
		}
	}
}
