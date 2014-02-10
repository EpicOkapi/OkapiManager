package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandvanish extends BaseCommand{

	public Commandvanish(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			if(!plugin.isVanished(player)){
				for(Player p : server.getOnlinePlayers()){
					if(!p.hasPermission("OkapiManager.vanish.ignore")){
						p.hidePlayer(player);
					}
				}

				plugin.getVanishedPlayers().add(player.getName());
				
				server.broadcastMessage(ChatColor.YELLOW + player.getName() + " went offline!");
				player.sendMessage(ChatColor.YELLOW + "You are now vanished!");
			} else {
				for(Player p : server.getOnlinePlayers()){
					p.showPlayer(player);
				}
				
				plugin.getVanishedPlayers().remove(player.getName());
				
				server.broadcastMessage(ChatColor.YELLOW + player.getName() + " came online!");
				player.sendMessage(ChatColor.YELLOW + "You are now visible!");
			}
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("list")){
				if(!player.hasPermission("OkapiManager.vanish.list")){
					player.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return;
				}
				
				String msg = formatMessage("Vanish Players: " + formatList(plugin.getVanishedPlayers()));
				
				player.sendMessage(msg);
			} else if(args[0].equalsIgnoreCase("fakeoffline") || args[0].equalsIgnoreCase("offline")){
				if(!player.hasPermission("OkapiManager.vanish.fakeoffline")){
					player.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return;
				}
				
				server.broadcastMessage(ChatColor.YELLOW + player.getName() + " went offline!");
			} else if(args[0].equalsIgnoreCase("fakeonline")  || args[0].equalsIgnoreCase("online")){
				if(!player.hasPermission("OkapiManager.vanish.fakeonline")){
					player.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return;
				}
				
				server.broadcastMessage(ChatColor.YELLOW + player.getName() + " came online!");
			}
		}
	}
}
