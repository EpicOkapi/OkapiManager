package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandserverinfo extends BaseCommand{
	
	public Commandserverinfo(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		long freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024L;
		long maxMem = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
		long totalMem = Runtime.getRuntime().totalMemory() / 1024L / 1024L;
		long usageMem = freeMem + totalMem;
		
		int chunkCount = 0;
		int entityCount = 0;
		
		for(World w : server.getWorlds()){
			chunkCount += w.getLoadedChunks().length;
			entityCount += w.getEntities().size();
		}
		
		sender.sendMessage(ChatColor.YELLOW + "Memory Usage: " + usageMem + "/" + maxMem);
		sender.sendMessage(ChatColor.YELLOW + "Chunks loaded: " + chunkCount);
		sender.sendMessage(ChatColor.YELLOW + "Entities: " + entityCount);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		long freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024L;
		long maxMem = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
		long totalMem = Runtime.getRuntime().totalMemory() / 1024L / 1024L;
		long usageMem = freeMem + totalMem;
		
		int chunkCount = 0;
		int entityCount = 0;
		int livingEntityCount = 0;
		
		World w = player.getWorld();
		
		chunkCount = w.getLoadedChunks().length;
		entityCount = w.getEntities().size();
		
		for(Entity ent : w.getEntities()){
			if(ent instanceof LivingEntity){
				livingEntityCount++;
			}
		}
		
		player.sendMessage(ChatColor.YELLOW + "Memory Usage: " + usageMem + "/" + maxMem);
		player.sendMessage(ChatColor.YELLOW + "Chunks loaded: " + chunkCount);
		player.sendMessage(ChatColor.YELLOW + "Entities: " + entityCount);
		player.sendMessage(ChatColor.YELLOW + "Living Entities: " + livingEntityCount);
	}
}
