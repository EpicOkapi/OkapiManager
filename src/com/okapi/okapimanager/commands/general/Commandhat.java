package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandhat extends BaseCommand{

	public Commandhat(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.getInventory().getHelmet().setType(Material.AIR);
			player.sendMessage(ChatColor.RED + "Your hat has been reset!");
		} else if(args.length == 1){
			Material mat;
			
			try {
				mat = Material.getMaterial(Integer.parseInt(args[0]));
			} catch (NumberFormatException ex){
				mat = Material.matchMaterial(args[0]);
			}
			
			player.getInventory().getHelmet().setType(mat);
			player.sendMessage(ChatColor.YELLOW + "You changed your hat to " + mat.name().toLowerCase() + "!");
		}
	}
}
