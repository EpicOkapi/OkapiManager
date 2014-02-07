package com.okapi.okapimanager.commands.general;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandup extends BaseCommand{

	public Commandup(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		
		Location loc = player.getLocation();
		boolean seenSolid = false;
		
		for(int i = loc.getBlockY() + 2; i < 128; i++){
			loc.setY(i);
			
			if((loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.TORCH) && !seenSolid){
				seenSolid = true;
			} else if(loc.getBlock().getType() == Material.AIR && seenSolid){
				player.teleport(loc);
				break;
			}
		}
	}
}
