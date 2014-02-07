package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandtphere extends BaseCommand{

	public Commandtphere(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player does not exist!");
				return;
			}
			
			if(p.isInsideVehicle()){
				p.leaveVehicle();
			}
			
			if(p == player){
				p.sendMessage(ChatColor.RED + "You can't teleport to yourself!");
				return;
			}
			
			p.teleport(player, TeleportCause.COMMAND);
			
			p.sendMessage(ChatColor.YELLOW + "You have been teleported to " + player.getName() + "!");
			player.sendMessage(ChatColor.YELLOW + p.getName() + " has been teleported to you!");
		}
	}
}
