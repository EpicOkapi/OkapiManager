package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandclearchat extends BaseCommand{

	public Commandclearchat(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		for(int i = 0; i < 15; i++){
			server.broadcastMessage("");
		}
		
		server.broadcastMessage(ChatColor.DARK_GREEN + "The chat has been cleared!");
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		for(int i = 0; i < 15; i++){
			server.broadcastMessage("");
		}
		
		server.broadcastMessage(ChatColor.DARK_GREEN + "The chat has been cleared!");
	}
}
