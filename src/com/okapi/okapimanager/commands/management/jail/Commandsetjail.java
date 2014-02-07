package com.okapi.okapimanager.commands.management.jail;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.LocationSettings;

public class Commandsetjail extends BaseCommand{

	public Commandsetjail(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			LocationSettings settings = plugin.getJailSettings(player.getWorld().getName());
			Location loc = player.getLocation();
			
			if(settings.locations.containsKey(args[0].toLowerCase())){
				if(!player.hasPermission("okapimanager.setjail.override")){
					player.sendMessage(ChatColor.RED + "That warp already exists!");
					return;
				}
			}
			
			settings.locations.put(args[0].toLowerCase(), loc);
			player.sendMessage(ChatColor.YELLOW + "You have succesfully added a jail location!");
		}
	}
}
