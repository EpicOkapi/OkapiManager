package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandinstantmine extends BaseCommand{
	
	public Commandinstantmine(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.isInstantMine()){
				settings.setInstantMine(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off instantmine!");
			} else {
				settings.setInstantMine(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on instantmine!");
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isGod()){
				settings.setInstantMine(false);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned off your instantmine!");
				player.sendMessage(ChatColor.YELLOW + "You turned off instantmine from " + p.getName() + "!");
			} else {
				settings.setInstantMine(true);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned on your instantmine!");
				player.sendMessage(ChatColor.YELLOW + "You turned on instantmine from " + p.getName() + "!");
			}
		}
	}
}
