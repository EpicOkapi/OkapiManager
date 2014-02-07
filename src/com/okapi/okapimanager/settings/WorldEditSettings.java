package com.okapi.okapimanager.settings;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;

import com.okapi.okapimanager.util.OkapiConfig;

public class WorldEditSettings {
	private Material wandMaterial;
	private int maxSelectionRange;
	
	public WorldEditSettings(){
		
	}
	
	public void Load(){
		File f = new File("plugins/OkapiManager/worldedit.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(f);
		
		wandMaterial = Material.getMaterial(config.getInt("selection.wand", Material.WOOD_AXE.getId()));
		maxSelectionRange = config.getInt("selection.range");
	}
	
	public void Save(){
		File f = new File("plugins/OkapiManager/worldedit.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(f);
		
		config.set("selection.wand", wandMaterial.getId());
		config.set("selection.range", maxSelectionRange);
		
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Material getWandMaterial() {
		return wandMaterial;
	}

	public void setWandMaterial(Material wandMaterial) {
		this.wandMaterial = wandMaterial;
	}

	public int getMaxSelectionRange() {
		return maxSelectionRange;
	}

	public void setMaxSelectionRange(int maxSelectionRange) {
		this.maxSelectionRange = maxSelectionRange;
	}
}
