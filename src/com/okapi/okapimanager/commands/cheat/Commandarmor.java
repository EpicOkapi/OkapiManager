package com.okapi.okapimanager.commands.cheat;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.commands.PlayerNotOnlineException;

public class Commandarmor extends BaseCommand{
	
	public Commandarmor(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.sendMessage(formatMessage("Armor types: Leather, Chainmail, Iron, Gold, Diamond"));
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("clear")){
				player.getInventory().setHelmet(new ItemStack(Material.AIR));
				player.getInventory().setChestplate(new ItemStack(Material.AIR));
				player.getInventory().setLeggings(new ItemStack(Material.AIR));
				player.getInventory().setBoots(new ItemStack(Material.AIR));
				
				player.sendMessage(formatMessage("Your armor has been removed!"));
				
				return;
			}
			
			Material chest = Material.valueOf(args[0].toUpperCase() + "_CHESTPLATE");
			Material boots = Material.valueOf(args[0].toUpperCase() + "_BOOTS");
			Material helmet = Material.valueOf(args[0].toUpperCase() + "_HELMET");
			Material leggings = Material.valueOf(args[0].toUpperCase() + "_LEGGINGS");
			
			if(chest == null){
				player.sendMessage(formatError("Enter an existing type of armor!"));
				return;
			}

			player.getInventory().setHelmet(new ItemStack(helmet, 1));
			player.getInventory().setChestplate(new ItemStack(chest, 1));
			player.getInventory().setLeggings(new ItemStack(leggings, 1));
			player.getInventory().setBoots(new ItemStack(boots, 1));
			
			player.sendMessage(formatMessage("You have been equiped with a " + args[0].toLowerCase() + " armor!"));
		}
	}
}
