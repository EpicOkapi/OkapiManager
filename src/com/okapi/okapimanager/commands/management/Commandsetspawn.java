package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandsetspawn extends BaseCommand{

	public Commandsetspawn(OkapiManager instance) {
		super(instance);
	}
	
	public void Run(Player player, Server server, String[] args){
		Location loc = player.getLocation();
		World w = player.getWorld();
		
		w.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		
		player.sendMessage(ChatColor.YELLOW + "You have succesfully changed the spawn!");
	}
}
