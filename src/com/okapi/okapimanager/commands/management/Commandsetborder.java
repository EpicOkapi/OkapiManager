package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.BorderSettings;

public class Commandsetborder extends BaseCommand{

	public Commandsetborder(OkapiManager instance) {
		super(instance);
	}

	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			int borderLength = 0;
			
			try {
				borderLength = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			BorderSettings settings = plugin.getBorderSettings(player.getWorld().getName());
			Location loc = player.getLocation();
			
			if(borderLength == 0){
				settings.BorderEnabled = false;
			} else {
				settings.BorderEnabled = true;
			}
			
			settings.MinimumX = loc.getBlockX() - borderLength;
			settings.MinimumZ = loc.getBlockZ() - borderLength;
			settings.MaximumX = loc.getBlockX() + borderLength;
			settings.MaximumZ = loc.getBlockZ() + borderLength;
			
			player.sendMessage(ChatColor.YELLOW + "You have set the border " + borderLength + " blocks away from you!");
			player.sendMessage(ChatColor.YELLOW + "");
		}
	}
}
