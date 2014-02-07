package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandflyspeed extends BaseCommand{

	public Commandflyspeed(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			float speed = 0;
			
			try {
				speed = Float.valueOf(args[0]);
				
				if(speed > 1.0){
					speed = 1.0F; 
				}
			} catch (NumberFormatException e){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			player.setFlySpeed(speed);
		}
	}
}
