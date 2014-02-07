package com.okapi.okapimanager.commands.general;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandping extends BaseCommand{

	public Commandping(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		sender.sendMessage("Pong");
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		player.sendMessage("Pong");
	}
}
