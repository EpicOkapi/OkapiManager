package com.okapi.okapimanager.commands.world;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandpvp extends BaseCommand{
	
	public Commandpvp(OkapiManager instance) {
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
			World w = player.getWorld();
			
			if(w.getPVP() == true){
				player.sendMessage(ChatColor.YELLOW + "Pvp is currently on!");
			} else {
				player.sendMessage(ChatColor.YELLOW + "Pvp is currently off!");
			}
		} else if(args.length == 1){
			World w = player.getWorld();
			
			if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("true")){
				w.setPVP(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on pvp!");
			} else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("false")){
				w.setPVP(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off pvp!");
			}
		} else if(args.length == 2){
			World w = server.getWorld(args[0]);
			
			if(w == null){
				player.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			if(args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")){
				w.setPVP(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on pvp!");
			} else if(args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")){
				w.setPVP(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off pvp!");
			}
		}
	}
}
