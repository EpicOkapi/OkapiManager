package com.okapi.okapimanager.util.worldedit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Region {
	private World world;
	
	private double maxX;
	private double minX;
	
	private double maxY;
	private double minY;
	
	private double maxZ;
	private double minZ;

	public Region(Location loc1, Location loc2){
		this.world = loc1.getWorld();
		
		this.maxX = RegionCalculations.getMaxX(loc1, loc2);
		this.minX = RegionCalculations.getMinX(loc1, loc2);
		
		this.maxY = RegionCalculations.getMaxY(loc1, loc2);
		this.minY = RegionCalculations.getMinY(loc1, loc2);
		
		this.maxZ = RegionCalculations.getMaxZ(loc1, loc2);
		this.minZ = RegionCalculations.getMinZ(loc1, loc2);
	}
	
	public List<Block> getBlocks(){
		List<Block> blocks = new ArrayList<Block>();
		
		for(int x = (int)minX; x <= maxX; x++){
			for(int y = (int)minY; y <= maxY; y++){
				for(int z = (int)minZ; z <= maxZ; z++){
					blocks.add(world.getBlockAt(x, y, z));
				}
			}
		}
		
		return blocks;
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(double maxZ) {
		this.maxZ = maxZ;
	}

	public double getMinZ() {
		return minZ;
	}

	public void setMinZ(double minZ) {
		this.minZ = minZ;
	}
}
