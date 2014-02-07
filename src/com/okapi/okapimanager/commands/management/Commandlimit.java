package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandlimit extends BaseCommand{
	
	public Commandlimit(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 2){
			int limit = 0;
			
			try {
				limit = Integer.parseInt(args[1]);
			} catch (NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid number!");
				return;
			}
			
			if(SetLimit(player.getWorld(), args[0], limit)){
				
			} else {
				player.sendMessage(ChatColor.RED + "Enter a valid limit!");
			}
		}
	}
	
	private boolean SetLimit(World w, String limitObject, int limit){
		if(limitObject.equalsIgnoreCase("mob") || limitObject.equalsIgnoreCase("mobs")){
			w.setMonsterSpawnLimit(limit);
		} else if(limitObject.equalsIgnoreCase("animal") || limitObject.equalsIgnoreCase("animals")){
			w.setAnimalSpawnLimit(limit);
		} else if(limitObject.equalsIgnoreCase("water") || limitObject.equalsIgnoreCase("watermob") ||
				limitObject.equalsIgnoreCase("wateranimal")){
			w.setWaterAnimalSpawnLimit(limit);
		} else {
			return false;
		}
		
		return true;
	}
}
