package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandexperience extends BaseCommand{
	
	public Commandexperience(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.giveExp(1000);
			player.sendMessage(ChatColor.YELLOW + "You gained 1000 experience!");
		} else if(args.length == 1) {
			int amount;
			
			try {
				amount = Integer.valueOf(args[0]);
			} catch(NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			player.giveExp(amount);
			player.sendMessage(ChatColor.YELLOW + "You gained " + amount + " experience!");
		} else if(args.length == 2){
			Player p = server.getPlayer(args[0]);
			int amount;
			
			if(p == null) {
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			try {
				amount = Integer.valueOf(args[1]);
			} catch(NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			p.giveExp(amount);
			p.sendMessage(ChatColor.YELLOW + "You gained " + amount + " experience!");
		}
	}
}
