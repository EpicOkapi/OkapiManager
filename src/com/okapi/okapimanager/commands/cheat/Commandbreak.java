package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandbreak extends BaseCommand{

	public Commandbreak(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		Block block = player.getTargetBlock(null, 100);
		
		if(block.getType() == Material.BEDROCK){
			if(!player.hasPermission("okapimanager.cheats.break.bedrock")){
				player.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return;
			}
		}
		
		block.breakNaturally();
	}
}
