package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandroll extends BaseCommand{

	public Commandroll(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			Roll(player, 0, 100);
		} else if(args.length == 1){
			int max = 0;
			
			try {
				max = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			Roll(player, 0, max);
		} else if(args.length == 2){
			int min = 0, max = 0;
			
			try {
				max = Integer.parseInt(args[1]);
				min = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			if(min > max){
				player.sendMessage(ChatColor.RED + "Your minimum roll can't be higher as your maximum roll!");
				return;
			}
			
			Roll(player, min, max);
		}
	}
	
	void Roll(Player player, int min, int max){
		int roll = min + (int)(Math.random() * ((max - min) + 1));
		
		for(Entity entity : player.getNearbyEntities(20, 20, 20)){
			if(entity instanceof Player){
				Player p = (Player)entity;
				
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has just rolled " + roll + " between " + min + "-" + max);
			}
		}
		
		player.sendMessage(ChatColor.YELLOW + player.getName() + " has just rolled " + roll + " between " + min + "-" + max);
	}
}
