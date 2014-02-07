package com.okapi.okapimanager.commands.teleport.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandhome extends BaseCommand{

	public Commandhome(OkapiManager instance) {
		super(instance);
	}

	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(player.isInsideVehicle()){
				player.leaveVehicle();
			}
			
			Location home = settings.getHome(player.getWorld());
			
			if(home == null){
				player.sendMessage(ChatColor.RED + "You don't have a home yet!");
			} else {
				player.teleport(home, TeleportCause.COMMAND);
				player.sendMessage(ChatColor.YELLOW + "Welcome to your home!");
			}
		} else if(args.length == 1){
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			World w = server.getWorld(args[0].replace("_", " "));
			
			if(w == null){
				player.sendMessage(ChatColor.RED + "That world does not exist!");
				return;
			}
			
			Location home = settings.getHome(w);
			
			if(home == null){
				player.sendMessage(ChatColor.RED + "You don't have a home in that world yet!");
			} else {
				player.teleport(home, TeleportCause.COMMAND);
				player.sendMessage(ChatColor.YELLOW + "Welcome to your home!");
			}
		}
	}
}
