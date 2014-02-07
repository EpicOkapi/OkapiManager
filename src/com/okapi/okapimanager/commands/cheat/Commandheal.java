package com.okapi.okapimanager.commands.cheat;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandheal extends BaseCommand{
	
	public Commandheal(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.setHealth(20.0);
			player.sendMessage(formatMessage("You have been healed!"));
		} else if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(formatError("That player is not online!"));
				return;
			}
			
			p.setHealth(20.0);
			
			player.sendMessage(formatMessage("You have healed " + p.getName() + "!"));
			p.sendMessage(formatMessage("You have been healed by " + player.getName() + "!"));
		}
	}
}
