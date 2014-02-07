package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandhelpop extends BaseCommand{

	public Commandhelpop(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		String msg = ChatColor.YELLOW + "Help request from " + player.getName() + ": ";
		
		for(int i = 0; i < args.length; i++){
			msg += args + " ";
		}
		
		boolean foundOP = false;
		
		for(Player p : server.getOnlinePlayers()){
			if(p.isOp()){
				player.sendMessage(msg);
				foundOP = true;
			}
		}
		
		if(!foundOP){
			player.sendMessage(ChatColor.RED + "No OP is currently online to help!");
		} else {
			player.sendMessage(ChatColor.YELLOW + "Your request has been send!");
		}
	}
}
