package com.okapi.okapimanager.commands.management.jail;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandunjail extends BaseCommand{

	public Commandunjail(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			PlayerSettings psettings = plugin.getPlayerSettings(p);
			
			psettings.setJailed(false);
			psettings.setTeleportingEnabled(true);
			p.teleport(p.getWorld().getSpawnLocation());
			
			p.sendMessage(ChatColor.RED + "You have been unjailed by " + player.getName() + "!");
			player.sendMessage(ChatColor.YELLOW + "You have unjailed " + p.getName() + "!");
		}
	}
}
