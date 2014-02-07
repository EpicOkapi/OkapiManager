package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandbroadcast extends BaseCommand{

	public Commandbroadcast(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		String msg = ChatColor.WHITE + " ";
		
		for(int i = 0; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		server.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_PURPLE + "BROADCAST" + ChatColor.WHITE + "] " + msg);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		String msg = ChatColor.WHITE+ " ";
		
		for(int i = 0; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		server.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_PURPLE + "BROADCAST" + ChatColor.WHITE + "] " + msg);
	}
}
