package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandfly extends BaseCommand{

	public Commandfly(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			if(player.isFlying()){
				player.setAllowFlight(false);
				player.setFlying(false);
				player.sendMessage(ChatColor.YELLOW + "Your flying has been disabled!");
			} else {
				player.setAllowFlight(true);
				player.setFlying(true);
				player.sendMessage(ChatColor.YELLOW + "Your flying has been enabled!");
			}
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true")){
				player.setAllowFlight(false);
				player.setFlying(false);
				player.sendMessage(ChatColor.YELLOW + "Your flying has been disabled!");
				return;
			} else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false")){
				player.setAllowFlight(true);
				player.setFlying(true);
				player.sendMessage(ChatColor.YELLOW + "Your flying has been enabled!");
				return;
			}
			
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			if(p.isFlying()){
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage(ChatColor.YELLOW + "Your flying has been disabled!");
				player.sendMessage(ChatColor.YELLOW + "You have disabled flying for " + p.getName() + "!");
			} else {
				p.setAllowFlight(true);
				p.setFlying(true);
				p.sendMessage(ChatColor.YELLOW + "Your flying has been enabled!");
				player.sendMessage(ChatColor.YELLOW + "You have enabled flying for " + p.getName() + "!");
			}
		}
	}
}
