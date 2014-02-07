package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandgetpos extends BaseCommand{

	public Commandgetpos(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			Location loc = player.getLocation();
			player.sendMessage(ChatColor.YELLOW + "X: " + loc.getBlockX() + " Y: " + loc.getBlockY() + " Z: " + loc.getBlockZ());
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			Location loc = p.getLocation();
			player.sendMessage(ChatColor.YELLOW + "X: " + loc.getBlockX() + " Y: " + loc.getBlockY() + " Z: " + loc.getBlockZ());
		}
	}
}
