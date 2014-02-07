package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandlongarms extends BaseCommand{
	
	public Commandlongarms(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.isGod()){
				settings.setLongArms(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off longarms!");
			} else {
				settings.setLongArms(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on longarms!");
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isLongArms()){
				settings.setLongArms(false);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned off your longarms!");
				player.sendMessage(ChatColor.YELLOW + "You turned off longarms for " + p.getName() + "!");
			} else {
				settings.setLongArms(true);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned on your longarms!");
				player.sendMessage(ChatColor.YELLOW + "You turned on godmode for " + p.getName() + "!");
			}
		}
	}
}
