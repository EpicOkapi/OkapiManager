package com.okapi.okapimanager.commands.teleport.home;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandsethome extends BaseCommand{

	public Commandsethome(OkapiManager instance) {
		super(instance);
	}

	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			plugin.getPlayerSettings(player.getName()).setHome(player.getWorld(), player.getLocation());
			
			player.sendMessage(ChatColor.YELLOW + "Your home has been changed to your current location!");
		}
	}
}
