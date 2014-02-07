package com.okapi.okapimanager.commands.teleport.warps;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.LocationSettings;

public class Commanddelwarp extends BaseCommand{

	public Commanddelwarp(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			LocationSettings settings = plugin.getWarpSettings(player.getWorld().getName());
			
			if(!settings.locations.containsKey(args[0].toLowerCase())){
				player.sendMessage(ChatColor.RED + "That warp does not exist!");
				return;
			}
			
			settings.locations.remove(args[0].toLowerCase());
			player.sendMessage(ChatColor.YELLOW + "You have succesfully removed a warp!");
		}
	}
}
