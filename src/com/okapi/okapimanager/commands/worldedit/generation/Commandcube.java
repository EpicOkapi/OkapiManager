package com.okapi.okapimanager.commands.worldedit.generation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.util.api.ItemAPI;
import com.okapi.okapimanager.util.worldedit.Region;

public class Commandcube extends BaseCommand {

	public Commandcube(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 2){
			int radius;
			
			try {
				radius = Integer.getInteger(args[0]);
			} catch (NumberFormatException ex){
				player.sendMessage(formatError("Enter a valid integer!"));
				return;
			}
			
			Material mat = ItemAPI.StringToMaterial(args[1]);
			
			if(mat == null){
				player.sendMessage(formatError("That material does not exist!"));
				return;
			}
			
			Location pLoc = player.getLocation();
			Location firstLoc = new Location(pLoc.getWorld(),
					pLoc.getX() - radius, pLoc.getY() - radius, pLoc.getZ() - radius);
			Location secondLoc = new Location(pLoc.getWorld(),
					pLoc.getX() - radius, pLoc.getY() - radius, pLoc.getZ() - radius);
			
			Region region = new Region(firstLoc, secondLoc);
			
			for(Block b : region.getBlocks()){
				b.setType(mat);
			}
		}
	}
}
