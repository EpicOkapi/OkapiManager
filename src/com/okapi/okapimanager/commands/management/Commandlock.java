package com.okapi.okapimanager.commands.management;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandlock extends BaseCommand{

	public Commandlock(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(plugin.getMainSettings().isLockDown()){
			plugin.getMainSettings().setLockDown(false);
			player.sendMessage(formatMessage("You stopped the lockdown!"));
		} else {
			plugin.getMainSettings().setLockDown(true);
			player.sendMessage(formatMessage("You started a lockdown!"));
		}
	}
	
	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(plugin.getMainSettings().isLockDown()){
			plugin.getMainSettings().setLockDown(false);
			sender.sendMessage(formatMessage("You stopped the lockdown!"));
		} else {
			plugin.getMainSettings().setLockDown(true);
			sender.sendMessage(formatMessage("You started a lockdown!"));
		}
	}
}
