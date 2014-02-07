package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandwhitelist extends BaseCommand{

	public Commandwhitelist(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 0){
			sender.sendMessage(ChatColor.YELLOW + "Whitelist enabled: " + server.hasWhitelist());
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("reload")){
				server.reloadWhitelist();
				sender.sendMessage(ChatColor.YELLOW + "You have reloaded the whitelist!");
			} else if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("enable")){
				server.setWhitelist(true);
				sender.sendMessage(ChatColor.YELLOW + "You turned on the whitelist!");
			} else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("false") ||  args[0].equalsIgnoreCase("disable")){
				server.setWhitelist(false);
				sender.sendMessage(ChatColor.YELLOW + "You turned off the whitelist!");
			} else if(args[0].equalsIgnoreCase("toggle")){
				if(!server.hasWhitelist()){
					server.setWhitelist(true);
					sender.sendMessage(ChatColor.YELLOW + "You turned on the whitelist!");
				} else {
					server.setWhitelist(false);
					sender.sendMessage(ChatColor.YELLOW + "You turned off the whitelist!");
				}
			}
		} else if(args.length == 2){
			if(args[0].equalsIgnoreCase("add")){
				OfflinePlayer p = server.getOfflinePlayer(args[1]);
				
				server.getWhitelistedPlayers().add(p);
				sender.sendMessage(ChatColor.YELLOW + "You have added " + p.getName() + " to the whitelist!");
			} else if(args[0].equalsIgnoreCase("remove")){
				OfflinePlayer p = server.getOfflinePlayer(args[1]);
				
				server.getWhitelistedPlayers().remove(p);
				sender.sendMessage(ChatColor.YELLOW + "You have removed " + p.getName() + " from the whitelist!");
			}
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.sendMessage(ChatColor.YELLOW + "Whitelist enabled: " + server.hasWhitelist());
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("reload")){
				server.reloadWhitelist();
				player.sendMessage(ChatColor.YELLOW + "You have reloaded the whitelist!");
			} else if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("enable")){
				server.setWhitelist(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on the whitelist!");
			} else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("false") ||  args[0].equalsIgnoreCase("disable")){
				server.setWhitelist(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off the whitelist!");
			} else if(args[0].equalsIgnoreCase("toggle")){
				if(!server.hasWhitelist()){
					server.setWhitelist(true);
					player.sendMessage(ChatColor.YELLOW + "You turned on the whitelist!");
				} else {
					server.setWhitelist(false);
					player.sendMessage(ChatColor.YELLOW + "You turned off the whitelist!");
				}
			}
		} else if(args.length == 2){
			if(args[0].equalsIgnoreCase("add")){
				OfflinePlayer p = server.getOfflinePlayer(args[1]);
				
				server.getWhitelistedPlayers().add(p);
				player.sendMessage(ChatColor.YELLOW + "You have added " + p.getName() + " to the whitelist!");
			} else if(args[0].equalsIgnoreCase("remove")){
				OfflinePlayer p = server.getOfflinePlayer(args[1]);
				
				server.getWhitelistedPlayers().remove(p);
				player.sendMessage(ChatColor.YELLOW + "You have removed " + p.getName() + " from the whitelist!");
			}
		}
	}
}
