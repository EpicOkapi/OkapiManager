package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.util.api.ItemAPI;

public class Commandclearinventory extends BaseCommand{

	public Commandclearinventory(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.getInventory().clear();
			player.sendMessage(ChatColor.YELLOW + "You have cleared your inventory!");
		} else if(args.length == 1){
			Material mat = ItemAPI.StringToMaterial(args[0]);
			
			if(mat != null){
				for(ItemStack item : player.getInventory().getContents()){
					if(item.getType() == mat){
						player.getInventory().remove(item);
					}
				}
			}
			
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			if(!player.hasPermission("okapimanager.clearinventory.other")){
				player.sendMessage("You can't clear the inventory of this player!");
				return;
			}
			
			p.getInventory().clear();
			p.sendMessage(ChatColor.YELLOW + "Your inventory has been cleared by " + player.getName() + "!");
			player.sendMessage(ChatColor.YELLOW + "You have cleared the inventory of " + p.getName() + "!");
		}
	}
}
