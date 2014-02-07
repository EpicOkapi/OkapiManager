package com.okapi.okapimanager.commands.general;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandtop extends BaseCommand{

	public Commandtop(OkapiManager instance) {
		super(instance);
	}

	public void Run(Player player, Server server, String[] args){
		Location loc = player.getLocation();
		
		for(int i = 255; i > 0; i--){
			loc.setY(i);
			
			if(loc.getBlock().getType() != Material.AIR){
				if(loc.getBlock().getType() == Material.TORCH){
					continue;
				}
				
				loc.setY(i + 1);
				player.setVelocity(new Vector(0,0,0));
				player.teleport(loc);
				break;
			}
		}
	}
}
