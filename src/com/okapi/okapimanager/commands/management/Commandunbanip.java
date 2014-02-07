package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandunbanip extends BaseCommand{
	public Commandunbanip(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1){
			server.unbanIP(args[0]);
			sender.sendMessage(ChatColor.RED + "You have unbanned " + args[0] + "!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			server.unbanIP(args[0]);
			player.sendMessage(ChatColor.RED + "You have unbanned " + args[0] + "!");
		}
	}
}
