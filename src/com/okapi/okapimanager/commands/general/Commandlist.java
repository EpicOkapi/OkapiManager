package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandlist extends BaseCommand{
	
	public Commandlist(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		String msg = ChatColor.YELLOW + "Online Players: ";
		
		for(Player p : server.getOnlinePlayers()){
			msg += p.getName();
				
			if(p.getName() != server.getOnlinePlayers()[server.getOnlinePlayers().length - 1].getName()){
				msg += ", ";
			}
		}
		
		msg += "!";
		
		sender.sendMessage(msg);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		String msg = ChatColor.YELLOW + "Online Players: ";
		
		for(Player p : server.getOnlinePlayers()){
			if(!plugin.isVanished(p)){
				msg += p.getName();
				
				if(p.getName() != server.getOnlinePlayers()[server.getOnlinePlayers().length - 1].getName()){
					msg += ", ";
				}
			}
		}
		
		msg += "!";
		
		player.sendMessage(msg);
	}
}
