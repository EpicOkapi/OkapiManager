package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandkickall extends BaseCommand{
	
	public Commandkickall(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 0){
			for(Player p : server.getOnlinePlayers()){
				p.kickPlayer("Everyone has been kicked from the server!");
			}
			
			sender.sendMessage(ChatColor.YELLOW + "You have kicked everyone!");
		} else {
			String kickMsg = "Reason: ";
			
			for(int i = 1; i < args.length; i++){
				kickMsg += args[i];
			}
			
			for(Player p : server.getOnlinePlayers()){
				p.kickPlayer("Everyon has been kick from the server! " + kickMsg);
			}
			
			sender.sendMessage(ChatColor.YELLOW + "You have kicked everyone!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			for(Player p : server.getOnlinePlayers()){
				if(p.getName() != player.getName()){
					p.kickPlayer("Everyone has been kicked from the server!");
				}
			}
			
			player.sendMessage(ChatColor.YELLOW + "You have kicked everyone!");
		} else {
			String kickMsg = "Reason: ";
			
			for(int i = 1; i < args.length; i++){
				kickMsg += args[i];
			}
			
			for(Player p : server.getOnlinePlayers()){
				if(p.getName() != player.getName()){
					p.kickPlayer("Everyon has been kick from the server! " + kickMsg);
				}
			}
			
			player.sendMessage(ChatColor.YELLOW + "You have kicked everyone!");
		}
	}
}
