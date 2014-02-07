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
			
			if(settings.isLongArms()){
				settings.setLongArms(false);
				player.sendMessage(formatMessage("Longarms has been deactivated!"));
			} else {
				settings.setLongArms(true);
				player.sendMessage(formatMessage("Longarms has been activated!"));
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(formatError("That player is not online!"));
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isLongArms()){
				settings.setLongArms(false);
				p.sendMessage(formatMessage("Longarms has been deactivated for you by " + player.getName() + "!"));
				player.sendMessage(ChatColor.YELLOW + "You deactivated longarms for " + p.getName() + "!");
			} else {
				settings.setLongArms(true);
				p.sendMessage(formatMessage("Longarms has been activated for you by " + player.getName() + "!"));
				player.sendMessage(ChatColor.YELLOW + "You activated longarms for " + p.getName() + "!");
			}
		}
	}
}
