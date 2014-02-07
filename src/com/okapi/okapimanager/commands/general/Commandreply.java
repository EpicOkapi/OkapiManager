package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandreply extends BaseCommand{

	public Commandreply(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		PlayerSettings settings = plugin.getPlayerSettings(player);
		String pname = settings.getLastMessenger();
		
		if(pname != null){
			player.sendMessage(ChatColor.RED + "You receive send a message yet!");
			return;
		}
		
		Player p = server.getPlayer(pname);
		
		if(p == null){
			player.sendMessage(ChatColor.RED + "That player is not online!");
			return;
		}
		
		String msg = ChatColor.WHITE + "";
		
		for(int i = 0; i < args.length; i++){
			msg += args[1] + " ";
		}
		
		player.sendMessage(ChatColor.YELLOW + "[You>" + p.getName() + "] " + msg);
		p.sendMessage(ChatColor.YELLOW + "[" + player.getName() + ">You] " + msg);
	}
}
