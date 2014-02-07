package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandenchant extends BaseCommand{
	
	public Commandenchant(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0) {
			String msg = ChatColor.YELLOW + "Sword Enchantments: ";
			
			for(int i = 0; i < Enchantment.values().length; i++){
				Enchantment type = Enchantment.values()[i];
				
				if(i == Enchantment.values().length - 1){
					msg += type.getName().toLowerCase();
				} else {
					msg += type.getName().toLowerCase() + ", ";
				}
			}
			
			player.sendMessage(msg);
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("table")){
				player.openEnchanting(player.getLocation(), true);
				
				return;
			}
			
			Enchantment ench = Enchantment.getByName(args[0].toUpperCase());
			int level = ench.getMaxLevel();
			
			if(!ench.canEnchantItem(player.getItemInHand())){
				player.sendMessage(ChatColor.RED + "You can't enchant that item with that enchant!");
				return;
			}
			
			player.getItemInHand().addEnchantment(ench, level);
			player.sendMessage(ChatColor.YELLOW + "You enchanted your item with " + ench.getName() + "!");
		} else if(args.length == 2) {
			Enchantment ench = Enchantment.getByName(args[0].toUpperCase());
			int level;
			
			try{
				level = Integer.valueOf(args[1]);
			} catch(NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid level!");
				return;
			}
			
			if(level > ench.getMaxLevel())
				level = ench.getMaxLevel();
			
			if(!ench.canEnchantItem(player.getItemInHand())){
				player.sendMessage(ChatColor.RED + "You can't enchant that item with that enchant!");
				return;
			}
			
			player.getItemInHand().addEnchantment(ench, level);
			player.sendMessage(ChatColor.YELLOW + "You enchanted your item with " + ench.getName() + "!");
		}
	}
}
