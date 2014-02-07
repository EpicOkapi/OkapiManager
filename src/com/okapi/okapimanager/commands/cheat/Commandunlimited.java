package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandunlimited extends BaseCommand{

	public Commandunlimited(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.isUnlimitedBlocks()){
				settings.setUnlimitedBlocks(false);
				player.sendMessage(ChatColor.YELLOW + "You turned off unlimited blocks!");
			} else {
				settings.setUnlimitedBlocks(true);
				player.sendMessage(ChatColor.YELLOW + "You turned on unlimited blocks!");
			}
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(p);
			
			if(settings.isUnlimitedBlocks()){
				settings.setUnlimitedBlocks(false);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned off your unlimited blocks!");
				player.sendMessage(ChatColor.YELLOW + "You turned off unlimited blocks from " + p.getName() + "!");
			} else {
				settings.setUnlimitedBlocks(true);
				p.sendMessage(ChatColor.YELLOW + player.getName() + " has turned on your unlimited blocks!");
				player.sendMessage(ChatColor.YELLOW + "You turned on unlimited blocks from " + p.getName() + "!");
			}
		}
	}
}
