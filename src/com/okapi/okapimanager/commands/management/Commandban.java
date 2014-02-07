package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandban extends BaseCommand{
	public Commandban(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 0) {
			sender.sendMessage("Enter a name!");
			return;
		}
		
		OfflinePlayer p = server.getPlayer(args[0]);
		
		String msg = "Reason: ";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		if(server.getPlayer(args[0]) != null){
			server.getPlayer(args[0]).kickPlayer(msg);
		}
		
		server.getBannedPlayers().add(p);
		server.broadcastMessage(ChatColor.RED + p.getName() + " has been banned from the server. " + msg);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.sendMessage("Enter a name!");
			return;
		}
		
		OfflinePlayer p = server.getPlayer(args[0]);
		
		String msg = "Reason: ";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		if(server.getPlayer(args[0]) != null){
			server.getPlayer(args[0]).kickPlayer(msg);
		}
		
		server.getBannedPlayers().add(p);
		server.broadcastMessage(ChatColor.RED + p.getName() + " has been banned from the server. " + msg);
	}
}
