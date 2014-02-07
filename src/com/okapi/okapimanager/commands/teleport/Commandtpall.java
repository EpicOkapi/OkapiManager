package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandtpall  extends BaseCommand{

	public Commandtpall(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		for(Player p : server.getOnlinePlayers()){
			if(p == player){
				continue;
			}
			
			if(p.isInsideVehicle()){
				p.leaveVehicle();
			}
			
			p.teleport(player, TeleportCause.COMMAND);	
			p.sendMessage(ChatColor.YELLOW + "You have been teleported to " + player.getName() + "!");
		}
		
		player.sendMessage(ChatColor.YELLOW + "You have teleported everyone on the server to you!");
	}
}
