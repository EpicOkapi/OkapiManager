package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandtp extends BaseCommand{

	public Commandtp(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(!plugin.getPlayerSettings(p).isTeleportingEnabled()){
				player.sendMessage(ChatColor.RED + "That player has teleporting disabled!");
				return;
			}
			
			Tp(player, p);
		} else if(args.length == 2){
			Player p = server.getPlayer(args[0]);
			Player p2 = server.getPlayer(args[1]);
			
			if(!plugin.getPlayerSettings(p).isTeleportingEnabled() || !plugin.getPlayerSettings(p2).isTeleportingEnabled()){
				player.sendMessage(ChatColor.RED + "One of the players has teleporting disabled!");
				return;
			}
			
			if(Tp(p, p2) == true){
				if(player != p && player != p2){
					player.sendMessage(ChatColor.YELLOW + "You have teleported " + p.getName() + " to " + p2.getName() + "!");
				}
			}
		}
	}
	
	private boolean Tp(Player p, Player p2){
		if(p.isInsideVehicle()){
			p.leaveVehicle();
		}
		
		if(p == p2){
			p.sendMessage(ChatColor.RED + "You can't teleport to yourself!");
			return false;
		}
		
		p.teleport(p2, TeleportCause.COMMAND);
		
		p.sendMessage(ChatColor.YELLOW + "You have been teleported to " + p2.getName() + "!");
		
		if(!plugin.isVanished(p))
			p2.sendMessage(ChatColor.YELLOW + p.getName() + " has been teleported to you!");
		
		return true;
	}
}
