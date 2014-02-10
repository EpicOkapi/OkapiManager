package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandme extends BaseCommand{

	public Commandme(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		String msg = ChatColor.YELLOW + formatParametersToMessage(args);
		
		server.broadcastMessage("The server " + msg);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		String msg = ChatColor.YELLOW + formatParametersToMessage(args);
		
		server.broadcastMessage(player.getName() + msg);
	}
}
