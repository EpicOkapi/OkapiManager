package com.okapi.okapimanager.util.worldedit;

import org.bukkit.Location;

public class RegionCalculations {

	public static double getMaxX(Location loc1, Location loc2){
		return Math.max(loc1.getX(), loc2.getX());
	}
	
	public static double getMinX(Location loc1, Location loc2){
		return Math.min(loc1.getX(), loc2.getX());
	}
	
	public static double getMaxY(Location loc1, Location loc2){
		return Math.max(loc1.getY(), loc2.getY());
	}
	
	public static double getMinY(Location loc1, Location loc2){
		return Math.min(loc1.getY(), loc2.getY());
	}
	
	public static double getMaxZ(Location loc1, Location loc2){
		return Math.max(loc1.getZ(), loc2.getZ());
	}
	
	public static double getMinZ(Location loc1, Location loc2){
		return Math.min(loc1.getZ(), loc2.getZ());
	}
}
