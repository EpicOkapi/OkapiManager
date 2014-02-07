package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandbind extends BaseCommand{

	public Commandbind(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		PlayerSettings settings = plugin.getPlayerSettings(player);
		Material mat = player.getItemInHand().getType();
		
		if(args.length == 0){
			if(settings.getBinds().containsKey(mat)){
				settings.getBinds().remove(mat);
				player.sendMessage(ChatColor.YELLOW + "You unbound the command from this item!");
			} else {
				player.sendMessage(ChatColor.RED + "You don't have a command bound to this item!");
			}
			
			return;
		}
		
		if(settings.getBinds().containsKey(mat)){
			player.sendMessage(ChatColor.RED + "You already bound a command to this item!");
			return;
		}
		
		settings.getBinds().put(mat, args);
		player.sendMessage(ChatColor.YELLOW + "You bound " + args[0].toLowerCase() + " to " + mat.toString().toLowerCase() + "!");
	}
}
