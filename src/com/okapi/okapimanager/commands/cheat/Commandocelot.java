package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandocelot extends BaseCommand{

	public Commandocelot(OkapiManager instance) {
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
			
			World w = p.getWorld();
			w.spawnEntity(p.getLocation(), EntityType.OCELOT);
			
			for(Entity entity : p.getNearbyEntities(1, 1, 1)){
				if(entity instanceof Ocelot){
					Ocelot ocelot = (Ocelot)entity;
					ocelot.setOwner(p);
				}
			}
			
			sender.sendMessage(ChatColor.YELLOW + "You have spawned a tamed ocelot for " + p.getName() + "!");
			p.sendMessage(ChatColor.YELLOW + "The server has spawned a tamed ocelot for you!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			World w = player.getWorld();
			w.spawnEntity(player.getLocation(), EntityType.OCELOT);
			
			for(Entity entity : player.getNearbyEntities(1, 1, 1)){
				if(entity instanceof Ocelot){
					Ocelot ocelot = (Ocelot)entity;
					ocelot.setOwner(player);
				}
			}
			
			player.sendMessage(ChatColor.YELLOW + "You have spawned a tamed wolf!");
		} else if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			World w = p.getWorld();
			w.spawnEntity(p.getLocation(), EntityType.OCELOT);
			
			for(Entity entity : p.getNearbyEntities(1, 1, 1)){
				if(entity instanceof Ocelot){
					Ocelot ocelot = (Ocelot)entity;
					ocelot.setOwner(p);
				}
			}
			
			player.sendMessage(ChatColor.YELLOW + "You have spawned a tamed ocelot for " + p.getName() + "!");
			p.sendMessage(ChatColor.YELLOW + player.getName() + " has spawned a tamed ocelot for you!");
		}
	}
}
