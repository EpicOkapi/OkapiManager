package com.okapi.okapimanager.commands.general;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commanddown extends BaseCommand{

	public Commanddown(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		Location loc = player.getLocation();
		boolean seenSolid = false;
		boolean seenAir = false;
		
		for(int i = loc.getBlockY(); i > 0; i--){
			loc.setY(i);
			Material mat = loc.getBlock().getType();
			
			if(mat != Material.AIR && !seenSolid){
				seenSolid = true;
			} else if(mat == Material.AIR && seenSolid){
				seenAir = true;
			} else if(mat != Material.AIR && seenSolid && seenAir){
				loc.setY(loc.getY() + 1);
				player.teleport(loc);
				
				break;
			}
		}
	}
}
