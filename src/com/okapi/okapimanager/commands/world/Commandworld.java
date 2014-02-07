package com.okapi.okapimanager.commands.world;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandworld extends BaseCommand{

	public Commandworld(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.sendMessage(ChatColor.YELLOW + "You are currently in " + player.getWorld().getName() +"!");
		} else if(args.length == 1){
			World w = server.getWorld(args[0]);
			
			if(w == null){
				player.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			player.teleport(w.getSpawnLocation());
			player.sendMessage(ChatColor.YELLOW + "You have been teleported to " + w.getName() +"!");
		}
	}
}
