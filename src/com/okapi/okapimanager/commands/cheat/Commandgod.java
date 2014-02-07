package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandgod extends BaseCommand{
	
	public Commandgod(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.isGod()){
				settings.setGod(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off godmode!");
			} else {
				settings.setGod(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on godmode!");
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isGod()){
				settings.setGod(false);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned off your godmode!");
				player.sendMessage(ChatColor.YELLOW + "You turned off godmode from " + p.getName() + "!");
			} else {
				settings.setGod(true);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned on your godmode!");
				player.sendMessage(ChatColor.YELLOW + "You turned on godmode from " + p.getName() + "!");
			}
		}
	}
}
