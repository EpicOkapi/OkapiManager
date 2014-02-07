package com.okapi.okapimanager.commands.general;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandworkbench extends BaseCommand{

	public Commandworkbench(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		player.openWorkbench(player.getLocation(), true);
	}
}
