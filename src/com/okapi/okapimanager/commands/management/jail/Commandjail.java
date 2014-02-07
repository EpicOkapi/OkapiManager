package com.okapi.okapimanager.commands.management.jail;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.LocationSettings;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandjail extends BaseCommand{

	public Commandjail(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			LocationSettings settings = plugin.getJailSettings(player.getWorld().getName());
			String message = "Jails: ";
				
			for(String str : settings.locations.keySet()){
				message += str + ",";
			}
				
			player.sendMessage(message);
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings psettings = plugin.getPlayerSettings(p);
			LocationSettings settings = plugin.getJailSettings(player.getWorld().getName());
			Location loc = settings.locations.get(args[0].toLowerCase());
			
			if(loc == null){
				player.sendMessage(ChatColor.RED + "That jail does not exist!");
				return;
			}
			
			p.teleport(loc);
			psettings.setJailed(true);
			psettings.setTeleportingEnabled(false);
			
			p.sendMessage(ChatColor.RED + "You have been jailed by " + player.getName() + "!");
			player.sendMessage(ChatColor.YELLOW + "You have jailed " + p.getName() + "!");
		} else if(args.length >= 2){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings psettings = plugin.getPlayerSettings(p);
			LocationSettings settings = plugin.getJailSettings(player.getWorld().getName());
			Location loc = settings.locations.get(args[0].toLowerCase());
			
			if(loc == null){
				player.sendMessage(ChatColor.RED + "That jail does not exist!");
				return;
			}
			
			p.teleport(loc);
			psettings.setJailed(true);
			psettings.setTeleportingEnabled(false);
			
			String reason = "";
			
			for(int i = 1; i < args.length - 1; i++){
				reason += args[i];
			}
			
			p.sendMessage(ChatColor.RED + "You have been jailed by " + player.getName() + "! Reason: " + reason);
			player.sendMessage(ChatColor.YELLOW + "You have jailed " + p.getName() + "! Reason: " + reason);
		}
	}
}
