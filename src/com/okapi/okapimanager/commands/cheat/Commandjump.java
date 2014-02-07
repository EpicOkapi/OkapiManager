package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandjump extends BaseCommand{
	
	public Commandjump(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(player.getTargetBlock(null, 150).getType() == Material.AIR){
			player.sendMessage(ChatColor.RED + "You can't teleport to air!");
			return;
		}
		
		player.teleport(player.getTargetBlock(null, 150).getLocation());
		player.sendMessage(ChatColor.YELLOW + "Wooooosh!");
	}
}
