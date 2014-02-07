package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandgamemode extends BaseCommand{
	
	public Commandgamemode(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			if(player.getGameMode() == GameMode.CREATIVE){
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.YELLOW + "You changed your gamemode to survival!");
			} else {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.YELLOW + "You changed your gamemode to creative!");
			}
		} else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")){
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.YELLOW + "You changed your gamemode to survival!");
			} else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")){
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.YELLOW + "You changed your gamemode to creative!");
			} else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")){
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(ChatColor.YELLOW + "You changed your gamemode to adventure!");
			}
		} else if(args.length == 2) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			if(args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("0")){
				p.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.YELLOW + "You changed " + p.getName() + "'s gamemode to survival");
				p.sendMessage(ChatColor.YELLOW + "Your gamemode got changed to survival!");
			} else if(args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("1")){
				p.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.YELLOW + "You changed " + p.getName() + "'s gamemode to creative");
				p.sendMessage(ChatColor.YELLOW + "Your gamemode got changed to creativel!");
			} else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")){
				p.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(ChatColor.YELLOW + "You changed " + p.getName() + "'s gamemode to adventure");
				p.sendMessage(ChatColor.YELLOW + "Your gamemode got changed to adventure!");
			}
		}
	}
}
