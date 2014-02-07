package com.okapi.okapimanager.commands.cheat;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandrepair extends BaseCommand{

	public Commandrepair(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.getItemInHand().setDurability(player.getItemInHand().getType().getMaxDurability());
			player.sendMessage(formatMessage("Your item has been repaired!"));
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("all")){
				for(ItemStack item : player.getInventory().getContents()){
					item.setDurability(item.getType().getMaxDurability());
				}
				
				player.sendMessage(formatMessage("All items in your inventory have been repaired!"));
			}
		}
		
	}
}
