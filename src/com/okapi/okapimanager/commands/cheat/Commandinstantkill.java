package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandinstantkill extends BaseCommand{
	
	public Commandinstantkill(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.isInstantKill()){
				settings.setInstantKill(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off instantkill!");
			} else {
				settings.setInstantKill(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on instantkill!");
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isInstantKill()){
				settings.setInstantKill(false);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned off your instantkill!");
				player.sendMessage(ChatColor.YELLOW + "You turned off instantkill from " + p.getName() + "!");
			} else {
				settings.setInstantKill(true);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned on your instantkill!");
				player.sendMessage(ChatColor.YELLOW + "You turned on instantkill from " + p.getName() + "!");
			}
		}
	}
}
