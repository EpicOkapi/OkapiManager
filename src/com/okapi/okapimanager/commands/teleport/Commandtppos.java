package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandtppos extends BaseCommand{

	public Commandtppos(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 2){
			int x = 0;
			int z = 0;
			
			try {
				x = Integer.valueOf(args[0]);
				z = Integer.valueOf(args[1]);
			} catch(NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid number!");
				return;
			}
			
			player.teleport(player.getWorld().getHighestBlockAt(new Location(player.getWorld(), x, 0, z)).getLocation());
			player.sendMessage(ChatColor.YELLOW + "You have teleported to X: " + x + " Z: " + z);
		} else if(args.length == 3){
			int x = 0;
			int y = 0;
			int z = 0;
			
			try {
				x = Integer.valueOf(args[0]);
				y = Integer.valueOf(args[1]);
				z = Integer.valueOf(args[2]);
			} catch(NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid number!");
				return;
			}
			
			player.teleport(new Location(player.getWorld(), x, y, z));
			player.sendMessage(ChatColor.YELLOW + "You have teleported to X: " + x + " Y: " + y + " Z: " + z);
		}
	}
}
