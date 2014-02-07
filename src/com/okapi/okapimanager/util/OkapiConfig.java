package com.okapi.okapimanager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class OkapiConfig extends YamlConfiguration{

	@Override
	public boolean getBoolean(String path, boolean def){
		if(contains(path)){
			return (Boolean)get(path);
		} else {
			set(path, def);
			return def;
		}
	}
	
	@Override
	public String getString(String path, String def){
		if(contains(path)){
			return (String)get(path);
		} else {
			set(path, def);
			return def;
		}
	}
	
	@Override
	public int getInt(String path, int def){
		if(contains(path)){
			return (Integer)get(path);
		} else {
			set(path, def);
			return def;
		}
	}
	
	public Location getLocation(String path, Location def){
		if(contains(path)){
			String world = (String)get(path + ".world");
			World w = Bukkit.getServer().getWorld(world);
			int x = (Integer)get(path + ".x");
			int y = (Integer)get(path + ".y");
			int z = (Integer)get(path + ".z");
			Location loc = new Location(w, x, y, z);
		
			return loc;
		} else {
			if(def == null){
				return null;
			} else {
				set(path + ".world", def.getWorld().getName());
				set(path + ".x", def.getBlockX());
				set(path + ".y", def.getBlockY());
				set(path + ".z", def.getBlockZ());
				return def;
			}
		}
	}
	
	public void setLocation(String path, Location loc) {
		set(path + ".world", loc.getWorld().getName());
		set(path + ".x", loc.getBlockX());
		set(path + ".y", loc.getBlockY());
		set(path + ".z", loc.getBlockZ());
	}
	
	public static OkapiConfig loadConfiguration(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        OkapiConfig config = new OkapiConfig();

        try {
            config.load(file);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file , ex);
        }

        return config;
    }
}
