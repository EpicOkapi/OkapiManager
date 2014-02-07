package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandeat extends BaseCommand{
	
	public Commandeat(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.setFoodLevel(20);
			
			sender.sendMessage("You feed " + p.getName() + "!");
			p.sendMessage("You have been feed by the server!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.setFoodLevel(20);
			player.sendMessage(ChatColor.YELLOW + "You feed yourself!");
		} else if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.setFoodLevel(20);
			
			player.sendMessage("You feed " + p.getName() + "!");
			p.sendMessage("You have been feed by " + player.getName() + "!");
		}
	}
}
