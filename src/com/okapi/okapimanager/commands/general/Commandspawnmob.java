package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.util.api.MobAPI;

public class Commandspawnmob extends BaseCommand{
	
	public Commandspawnmob(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			String msg = ChatColor.YELLOW + "Mob Types: ";
			
			for(EntityType type : EntityType.values()){
				if(type.isAlive() && type.isSpawnable())
					msg += type.name() + " ";
			}
			
			player.sendMessage(msg);
		} else if(args.length == 1){
			World w = player.getWorld();
			EntityType type = MobAPI.StringToEntity(args[0]);
			
			if(!type.isAlive() || !type.isSpawnable() || type == null){
				player.sendMessage(ChatColor.RED + "That type of mob does not exist!");
				return;
			}
			
			Location loc = player.getTargetBlock(null, 100).getLocation();
			loc.setY(loc.getY() + 1);
			
			w.spawnEntity(loc, type);
			player.sendMessage(ChatColor.YELLOW + "You have spawned a " + type.getName() + "!");
		} else if(args.length == 2){
			World w = player.getWorld();
			EntityType type = MobAPI.StringToEntity(args[0]);
			
			if(!type.isAlive() || !type.isSpawnable() || type == null){
				player.sendMessage(ChatColor.RED + "That type of mob does not exist!");
				return;
			}
			
			int amount;
			
			try {
				amount = Integer.valueOf(args[1]);
				int limit = plugin.getMainSettings().getSpawnmobLimit();
				
				if(amount >= limit){
					player.sendMessage(ChatColor.RED + "Amount changed from " + amount + " to " + limit + "!");
					
					amount = limit;
				}
			} catch (Exception ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			Location loc = player.getTargetBlock(null, 100).getLocation();
			loc.setY(loc.getY() + 1);
			
			for(int i = 0; i< amount; i++){
				w.spawnEntity(loc, type);
			}
			
			player.sendMessage(ChatColor.YELLOW + "You have spawned " + amount + type.getName() + "!");
		}
	}
}
