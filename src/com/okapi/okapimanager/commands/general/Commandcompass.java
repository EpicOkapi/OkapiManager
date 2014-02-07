package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandcompass extends BaseCommand{

	public Commandcompass(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		player.sendMessage(ChatColor.YELLOW + "Comming soon");
	}
}
