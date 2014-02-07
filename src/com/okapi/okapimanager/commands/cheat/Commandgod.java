package com.okapi.okapimanager.commands.cheat;

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
				player.sendMessage(formatMessage("Godmode has been deactivated!"));
			} else {
				settings.setGod(true);
				player.sendMessage(formatMessage("Godmode has been activated!"));
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(formatError("That player is not online!"));
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isGod()){
				settings.setGod(false);
				p.sendMessage(formatMessage(player.getName() + " has activated godmode for you!"));
				player.sendMessage(formatMessage("You deactivated godmode for " + p.getName() + "!"));
			} else {
				settings.setGod(true);
				p.sendMessage(formatMessage(player.getName() + " has activated godmode for you!"));
				player.sendMessage(formatMessage("You activated godmode for " + p.getName() + "!"));
			}
		}
	}
}
