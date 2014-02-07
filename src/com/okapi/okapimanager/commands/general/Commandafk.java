package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandafk extends BaseCommand{

	public Commandafk(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		PlayerSettings settings = plugin.getPlayerSettings(player);
		
		if(settings.isAfk()){
			settings.setAfk(false);
			player.sendMessage(ChatColor.YELLOW + "You are not afk anymore!");
		} else {
			settings.setAfk(true);
			player.sendMessage(ChatColor.YELLOW + "You are now afk!");
		}
	}
}
