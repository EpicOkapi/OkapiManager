package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandtptoggle extends BaseCommand{

	public Commandtptoggle(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			PlayerSettings settings = plugin.getPlayerSettings(player);
			
			if(settings.isTeleportingEnabled()){
				settings.setTeleportingEnabled(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off teleporing!");
			} else {
				settings.setTeleportingEnabled(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on teleporing!");
			}
		} else if(args.length == 1){
			if(!player.hasPermission("okapimanager.tptoggle.other")){
				player.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return;
			}
			
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isTeleportingEnabled()){
				settings.setTeleportingEnabled(false);
				p.sendMessage(player.getName() + " has turned off your teleporting!");
				player.sendMessage(ChatColor.YELLOW + "You turned off teleporing from " + p.getName() + "!");
			} else {
				settings.setTeleportingEnabled(true);
				p.sendMessage(player.getName() + " has turned on your teleporting!");
				player.sendMessage(ChatColor.YELLOW + "You turned on teleporing from " + p.getName() + "!");
			}
		}
	}
}
