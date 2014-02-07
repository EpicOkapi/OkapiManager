package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandexplode extends BaseCommand{

	public Commandexplode(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.getWorld().createExplosion(player.getLocation(), 5.0f);
		} else if(args.length == 1){
			try {
				float explosionWidth = Float.valueOf(args[0]);
				player.getWorld().createExplosion(player.getLocation(), explosionWidth);
				
				return;
			} catch (NumberFormatException ex){
				// Nothing
			}
			
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.getWorld().createExplosion(p.getLocation(), 5.0f);
		} else if(args.length == 2){
			try {
				float explosionWidth = Float.valueOf(args[1]);
				Player p = server.getPlayer(args[0]);
				
				if(p == null){
					player.sendMessage(ChatColor.RED + "That player is not online!");
					return;
				}
				
				p.getWorld().createExplosion(player.getLocation(), explosionWidth);
				
				return;
			} catch (NumberFormatException ex){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
			}
		}
	}
}
