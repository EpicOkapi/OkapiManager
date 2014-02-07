package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandburn extends BaseCommand{

	public Commandburn(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.setFireTicks(1000);
			sender.sendMessage(ChatColor.YELLOW + "You have put " + p.getName() + " on fire!");
		} else if(args.length == 2){
			Player p = server.getPlayer(args[0]);
			int fireticks = 0;
			
			if(p == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			try {
				fireticks = Integer.valueOf(args[0]);
			} catch(NumberFormatException ex){
				sender.sendMessage(ChatColor.RED + "Enter a valid number!");
				return;
			}
			
			p.setFireTicks(fireticks);
			sender.sendMessage(ChatColor.YELLOW + "You have put " + p.getName() + " on fire!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.setFireTicks(1000);
			player.sendMessage(ChatColor.YELLOW + "You have put yourself on fire!");
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.setFireTicks(1000);
			player.sendMessage(ChatColor.YELLOW + "You have put " + p.getName() + " on fire!");
		} else if(args.length == 2){
			Player p = server.getPlayer(args[0]);
			int fireticks = 0;
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			try {
				fireticks = Integer.valueOf(args[0]);
			} catch(NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid number!");
				return;
			}
			
			p.setFireTicks(fireticks);
			player.sendMessage(ChatColor.YELLOW + "You have put " + p.getName() + " on fire!");
		}
	}
}
