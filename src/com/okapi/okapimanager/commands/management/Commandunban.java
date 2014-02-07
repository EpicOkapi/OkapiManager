package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandunban extends BaseCommand{
	public Commandunban(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1) {
			OfflinePlayer p = server.getPlayer(args[0]);
			
			server.getBannedPlayers().remove(p);
			
			sender.sendMessage(ChatColor.YELLOW + "You have unbanned " + p.getName() + "!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1) {
			OfflinePlayer p = server.getPlayer(args[0]);
			
			server.getBannedPlayers().remove(p);
			
			player.sendMessage(ChatColor.YELLOW + "You have unbanned " + p.getName() + "!");
		}
	}
}
