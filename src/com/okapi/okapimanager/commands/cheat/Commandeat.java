package com.okapi.okapimanager.commands.cheat;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandeat extends BaseCommand{
	
	public Commandeat(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				sender.sendMessage(formatError("That player is not online!"));
				return;
			}
			
			p.setFoodLevel(20);
			
			sender.sendMessage(formatMessage("You fed " + p.getName() + "!"));
			p.sendMessage(formatMessage("You have been fed by the server!"));
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.setFoodLevel(20);
			player.sendMessage(formatMessage("You have fed yourself!"));
		} else if(args.length == 1) {
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(formatError("That player is not online!"));
				return;
			}
			
			p.setFoodLevel(20);
			
			player.sendMessage(formatMessage("You fed " + p.getName() + "!"));
			p.sendMessage(formatMessage("You have been fed by " + player.getName() + "!"));
		}
	}
}
