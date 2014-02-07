package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandspawn extends BaseCommand{

	public Commandspawn(OkapiManager instance) {
		super(instance);
	}

	public void Run(Player player, Server server, String[] args){
		if(player.isInsideVehicle()){
			player.leaveVehicle();
		}
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("bed")){
				player.teleport(player.getBedSpawnLocation(), TeleportCause.COMMAND);
			}
		}
		
		player.teleport(player.getWorld().getSpawnLocation(), TeleportCause.COMMAND);
		player.sendMessage(ChatColor.YELLOW + "You teleported to spawn!");
	}
}
