package com.okapi.okapimanager.commands.world;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandweather extends BaseCommand{
	
	public Commandweather(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 0){
			sender.sendMessage(ChatColor.YELLOW + "Weather Types: sun, storm");
		} else if(args.length == 2){
			World w = server.getWorld(args[0]);
			
			if(w == null){
				sender.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			if(args[1].equalsIgnoreCase("sun")){
				w.setStorm(false);
				w.setThundering(false);
			} else if(args[1].equalsIgnoreCase("storm")){
				w.setStorm(true);
			} else {
				sender.sendMessage(ChatColor.RED + "Enter a valid weather type!");
			}
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.sendMessage(ChatColor.YELLOW + "Weather Types: sun, storm");
		} else if(args.length == 1){
			World w = player.getWorld();
			
			if(args[0].equalsIgnoreCase("sun")){
				w.setStorm(false);
				w.setThundering(false);
			} else if(args[0].equalsIgnoreCase("storm")){
				w.setStorm(true);
			} else {
				player.sendMessage(ChatColor.RED + "Enter a valid weather type!");
			}
		} else if(args.length == 2){
			World w = server.getWorld(args[0]);
			
			if(w == null){
				player.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			if(args[1].equalsIgnoreCase("sun")){
				w.setStorm(false);
				w.setThundering(false);
			} else if(args[1].equalsIgnoreCase("storm")){
				w.setStorm(true);
			} else {
				player.sendMessage(ChatColor.RED + "Enter a valid weather type!");
			}
		}
	}
}
