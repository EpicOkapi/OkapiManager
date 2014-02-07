package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandmute extends BaseCommand{

	public Commandmute(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 0) {
			sender.sendMessage("Enter a name!");
			return;
		}
		
		Player p = server.getPlayer(args[0]);
		
		if(p == null){
			sender.sendMessage(ChatColor.RED + "That player is not online!");
			return;
		}
		
		PlayerSettings settings = plugin.getPlayerSettings(p);
		settings.setMuted(true);
		
		String msg = "Reason: ";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		server.broadcastMessage(ChatColor.RED + p.getName() + " has been muted. " + msg);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			player.sendMessage("Enter a name!");
			return;
		}
		
		Player p = server.getPlayer(args[0]);
		
		if(p == null){
			player.sendMessage(ChatColor.RED + "That player is not online!");
			return;
		}
		
		PlayerSettings settings = plugin.getPlayerSettings(p);
		settings.setMuted(true);
		
		String msg = "Reason: ";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		server.broadcastMessage(ChatColor.RED + p.getName() + " has been muted. " + msg);
	}
}
