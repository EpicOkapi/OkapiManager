package com.okapi.okapimanager.commands.cheat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandmore extends BaseCommand{
	
	public Commandmore(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		player.getItemInHand().setAmount(player.getItemInHand().getType().getMaxStackSize());
		
		player.sendMessage(ChatColor.YELLOW + "You got a full stack of " + player.getItemInHand().getType().name() + "!");
	}
}
