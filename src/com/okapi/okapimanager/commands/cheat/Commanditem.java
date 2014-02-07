package com.okapi.okapimanager.commands.cheat;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.util.api.ItemAPI;

public class Commanditem extends BaseCommand{
	
	public Commanditem(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			ItemStack stack = ItemAPI.StringToItemstack(args[0]);		
			
			if(stack == null){
				player.sendMessage(formatError("That item does not exist!"));
				return;
			}
			
			stack.setAmount(stack.getType().getMaxStackSize());
			
			player.getInventory().addItem(stack);
			player.sendMessage(formatMessage("You spawned " + stack.getAmount() + " of " + stack.getType().name().toLowerCase() + "!"));
		} else if(args.length == 2){
			int amount;
			
			try {
				amount = Integer.parseInt(args[1]);
			} catch(NumberFormatException ex){
				player.sendMessage(formatError("Enter a valid amount!"));
				return;
			}
			
			ItemStack stack = ItemAPI.StringToItemstack(args[0]);		
			
			if(stack == null){
				player.sendMessage(formatError("That item does not exist!"));
				return;
			}
			
			stack.setAmount(amount);
			
			player.getInventory().addItem(stack);
			player.sendMessage(formatMessage("You spawned " + stack.getAmount() + " of " + stack.getType().name().toLowerCase() + "!"));
		}
	}
	
	public void spawnItem(Player player, MaterialData mat, int amount){
		player.getInventory().addItem(mat.toItemStack(amount));
		player.sendMessage(formatMessage("You spawned " + amount + " of " + mat.toString() + "!"));
	}
}
