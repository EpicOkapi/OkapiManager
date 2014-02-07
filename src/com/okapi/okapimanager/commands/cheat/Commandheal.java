package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandheal extends BaseCommand{
	
	public Commandheal(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.setHealth(20);
			player.sendMessage(ChatColor.YELLOW + "You healed yourself!");
		} else if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.setHealth(20);
			
			player.sendMessage("You healed " + p.getName() + "!");
			p.sendMessage("You have been healed by " + player.getName() + "!");
		}
	}
}
