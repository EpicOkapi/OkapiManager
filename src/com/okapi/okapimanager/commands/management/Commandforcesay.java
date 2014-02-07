package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandforcesay  extends BaseCommand{
	
	public Commandforcesay(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			
		}
		
		if(args.length == 1){
			player.sendMessage(ChatColor.RED + "Enter your message!");
		}
		
		Player p = server.getPlayer(args[0]);
		
		if(p == null){
			player.sendMessage(ChatColor.RED + "That player is not online!");
			return;
		}
		
		String msg = "";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		p.chat(msg);
	}
}
