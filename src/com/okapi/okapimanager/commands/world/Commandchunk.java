package com.okapi.okapimanager.commands.world;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandchunk extends BaseCommand{
	
	public Commandchunk(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			Chunk chunk = player.getLocation().getChunk();
			long worldSeed = player.getWorld().getSeed();
	        int x = chunk.getX();
	        int z = chunk.getZ();
	        boolean slimeChunk;

	        Random random = new Random(worldSeed + 
	          x * x * 4987142 + 
	          x * 5947611 + 
	          z * z * 4392871L + 
	          z * 389711 ^ 0x3AD8025F);
	        
	        if(random.nextInt(10) == 0){
	        	slimeChunk = true;
	        } else {
	        	slimeChunk = false;
	        }
			
			player.sendMessage(ChatColor.YELLOW + "Chunk X: " + chunk.getX() + " Z: " + chunk.getZ() + " Entities: " + chunk.getEntities().length + " Slimes: " + slimeChunk);
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("reload")){
				Chunk chunk = player.getLocation().getChunk();
				chunk.unload();
				chunk.load();
				
				player.sendMessage(ChatColor.YELLOW + "Chunk reloaded!");
			} else if(args[0].equalsIgnoreCase("regenerate")){
				Chunk chunk = player.getLocation().getChunk();
				World w = chunk.getWorld();
				
				w.regenerateChunk(chunk.getX(), chunk.getZ());
			}
		}
	}
}
