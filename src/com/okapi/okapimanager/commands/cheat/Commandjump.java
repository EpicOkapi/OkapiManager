package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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
			player.sendMessage(formatMessage("You have to teleport to a block!"));
			return;
		}
		
		Location jumpLocation = player.getTargetBlock(null, 150).getLocation().add(0, 1, 0);
		
		player.teleport(jumpLocation);
		player.sendMessage(ChatColor.YELLOW + "Wooooosh!");
	}
}
