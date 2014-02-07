package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandlastonline extends BaseCommand{

	public Commandlastonline(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1){
			OfflinePlayer p = server.getOfflinePlayer(args[0]);
			
			sender.sendMessage(ChatColor.YELLOW + "This player was last seen on " + p.getLastPlayed());
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			OfflinePlayer p = server.getOfflinePlayer(args[0]);
			
			player.sendMessage(ChatColor.YELLOW + "This player was last seen on " + p.getLastPlayed());
		}
	}
}
