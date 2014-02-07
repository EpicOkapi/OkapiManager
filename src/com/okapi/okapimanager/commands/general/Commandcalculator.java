package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandcalculator extends BaseCommand{
	
	public Commandcalculator(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 3){
			PlayerSettings settings = plugin.getPlayerSettings(player);
			double firstInteger = 0;
			double secondInteger = 0;
			double answer = 0;
			
			try {
				if(args[0].equalsIgnoreCase("ans")){
					firstInteger = settings.getLastCalcAnswer();
				} else {
					firstInteger = Double.valueOf(args[0]);
				}
				
				if(args[2].equalsIgnoreCase("ans")){
					secondInteger = settings.getLastCalcAnswer();
				} else {
					secondInteger = Double.valueOf(args[2]);
				}
			} catch(NumberFormatException e){
				player.sendMessage(ChatColor.RED + "Enter a valid integer!");
				return;
			}
			
			if(args[1].equals("+")){
				answer = firstInteger + secondInteger;
			} else if(args[1].equals("-")){
				answer = firstInteger - secondInteger;
			} else if(args[1].equals("/")){
				if(secondInteger != 0){ 
					answer = firstInteger / secondInteger;
				} else {
					player.sendMessage(ChatColor.RED + "You can't divide by zero!");
					return;
				}
			} else if(args[1].equals("*") || args[1].equals("x")){
				answer = firstInteger * secondInteger;
			} else {
				player.sendMessage(ChatColor.RED + "That operator does not exist!");
				return;
			}
			
			player.sendMessage("The answer to " + firstInteger + " " + args[1] + " " + secondInteger + " = " + answer);
			settings.setLastCalcAnswer(answer);
		}
	}
}
