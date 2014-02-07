package com.okapi.okapimanager.commands.world;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Biome;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandbiome extends BaseCommand{
	
	public Commandbiome(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.sendMessage(ChatColor.YELLOW + "You are currently in a " + player.getWorld().getBiome(player.getLocation().getBlockX(), player.getLocation().getBlockZ()).name().toLowerCase() + " biome!");
		} else if(args.length == 1) {
			Biome biome = Biome.valueOf(args[0].toUpperCase());
			
			if(biome == null){
				player.sendMessage(ChatColor.RED + "Enter a valid biome type!");
			}
			
			player.getWorld().setBiome(player.getLocation().getBlockX(), player.getLocation().getBlockZ(), biome);
			player.sendMessage(ChatColor.YELLOW + "You changed this biome to " + biome.name().toLowerCase() + "!");
		}
	}
}