package com.okapi.okapimanager.commands.teleport.warps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.LocationSettings;

public class Commandwarp extends BaseCommand{

	public Commandwarp(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			LocationSettings settings = plugin.getWarpSettings(player.getWorld().getName());
			String message = "Warps: ";
				
			for(String str : settings.locations.keySet()){
				message += str + ",";
			}
				
			player.sendMessage(message);
		} else if(args.length == 1){
			LocationSettings settings = plugin.getWarpSettings(player.getWorld().getName());
			Location loc = settings.locations.get(args[0].toLowerCase());
			
			if(loc == null){
				player.sendMessage(ChatColor.RED + "That warp does not exist!");
				return;
			}
			
			player.teleport(loc);
			player.sendMessage(ChatColor.YELLOW + "You teleported to " + args[0].toLowerCase() + "!");
		}
	}
}
