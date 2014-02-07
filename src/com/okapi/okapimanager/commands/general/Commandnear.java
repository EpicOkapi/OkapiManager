package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandnear extends BaseCommand{

	public Commandnear(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			
		} else if(args.length == 1){
			int length = 0;
			
			try{
				length = Integer.valueOf(args[0]);
			} catch (NumberFormatException ex) {
				player.sendMessage(ChatColor.RED + "Enter a valid number!");
				return;
			}
			
			String msg = ChatColor.YELLOW + "Players: ";
			
			for(Entity e : player.getNearbyEntities(length, length, length)){
				if(e instanceof Player){
					msg += ((Player)e).getName() + " ";
				}
			}
			
			player.sendMessage(msg);
		}
	}
}
