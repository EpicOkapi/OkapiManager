package com.okapi.okapimanager.commands.management.jail;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.LocationSettings;

public class Commanddeljail extends BaseCommand{

	public Commanddeljail(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			LocationSettings settings = plugin.getJailSettings(player.getWorld().getName());
			
			if(!settings.locations.containsKey(args[0].toLowerCase())){
				player.sendMessage(ChatColor.RED + "That jail location does not exist!");
				return;
			}
			
			settings.locations.remove(args[0].toLowerCase());
			player.sendMessage(ChatColor.YELLOW + "You have succesfully removed a jail!");
		}
	}
}
