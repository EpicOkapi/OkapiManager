package com.okapi.okapimanager.commands.world;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandtime extends BaseCommand{
	
	public Commandtime(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 0) {
			for(World w : server.getWorlds()){
				sender.sendMessage(ChatColor.YELLOW + w.getName() + ": " + w.getTime());
			}
		} else if(args.length == 1) {
			int time = 0;
			
			try {
				time = Integer.valueOf(args[0]);
				
				for(World w : server.getWorlds()){
					w.setTime(time);
				}
				
				return;
			} catch (NumberFormatException ex){
				
			}
			
			for(World w : server.getWorlds()){
				if(setTime(w, args[0]) == false){
					sender.sendMessage(ChatColor.RED + "That time does not exist!");
					return;
				}
			}
			
			sender.sendMessage(ChatColor.YELLOW + "You changed the time to " + args[0].toLowerCase() + "!");
		} else if(args.length == 2) {
			World world = server.getWorld(args[0]);
			
			if(world == null){
				sender.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			int time = 0;
			
			try {
				time = Integer.valueOf(args[1]);
				
				world.setTime(time);
				
				return;
			} catch (NumberFormatException ex){
				
			}
			
			if(setTime(world, args[1]))
				sender.sendMessage(ChatColor.YELLOW + "You changed the time to " + args[1].toLowerCase() + "!");
			else
				sender.sendMessage(ChatColor.RED + "That time does not exist!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.sendMessage(ChatColor.YELLOW + "The time is " + player.getWorld().getTime() + "!");
		} else if(args.length == 1) {
			int time;
			
			try {
				time = Integer.valueOf(args[0]);
				
				player.getWorld().setTime(time);
				
				return;
			} catch (NumberFormatException ex){
				
			}
			
			if(setTime(player.getWorld(), args[0]))
				player.sendMessage(ChatColor.YELLOW + "You changed the time to " + args[0].toLowerCase() + "!");
			else
				player.sendMessage(ChatColor.RED + "That time does not exist!");
			
		} else if(args.length == 2) {
			World world = server.getWorld(args[0]);
			
			if(world == null){
				player.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			if(setTime(world, args[1]))
				player.sendMessage(ChatColor.YELLOW + "You changed the time to " + args[1].toLowerCase() + "!");
			else
				player.sendMessage(ChatColor.RED + "That time does not exist!");
		}
	}
	
	public boolean setTime(World world, String time){
		if(time.equalsIgnoreCase("day"))
			world.setTime(0);
		else if(time.equalsIgnoreCase("night"))
			world.setTime(14000);
		else if(time.equalsIgnoreCase("noon"))
			world.setTime(6000);
		else
			return false;
		
		return true;
	}
}
