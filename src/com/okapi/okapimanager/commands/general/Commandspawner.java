package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.util.api.MobAPI;

public class Commandspawner extends BaseCommand{
	
	public Commandspawner(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			String msg = ChatColor.YELLOW + "Mob Types: ";
			
			for(EntityType type : EntityType.values()){
				if(type.isSpawnable())
					msg += type.name() + ", ";
			}
			
			player.sendMessage(msg);
		} else if(args.length == 1){
			Block target = player.getTargetBlock(null, 10);
			
			if(target.getState() instanceof CreatureSpawner){
				CreatureSpawner spawner = (CreatureSpawner)target.getState();
				
				if(args[0].equalsIgnoreCase("delay")){
					player.sendMessage(ChatColor.YELLOW + "Spawner Delay: " + spawner.getDelay());
				} else {
					EntityType type = MobAPI.StringToEntity(args[0]);
					
					if(!type.isSpawnable()){
						player.sendMessage(ChatColor.RED + "You cannot change the spawner type to this!");
						return;
					}
					
					spawner.setSpawnedType(type);
					player.sendMessage(ChatColor.YELLOW + "You changed your spawner type to " + type.name() + "!");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You don't have a mobspawner selected!");
			}
		} else if(args.length == 2){
			Block target = player.getTargetBlock(null, 10);
			
			if(target.getState() instanceof CreatureSpawner){
				CreatureSpawner spawner = (CreatureSpawner)target.getState();
				
				if(args[0].equalsIgnoreCase("delay")){
					int delay = 0;
					
					try {
						delay = Integer.parseInt(args[1]);
					} catch (NumberFormatException ex){
						player.sendMessage(ChatColor.RED + "Enter a valid number!");
						return;
					}
					
					spawner.setDelay(delay);
					player.sendMessage(ChatColor.YELLOW + "Spawner Delay: " + spawner.getDelay());
					
					return;
				}
			} else {
				player.sendMessage(ChatColor.RED + "You don't have a mobspawner selected!");
			}
		}
	}
}
