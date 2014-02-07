package com.okapi.okapimanager.commands.economy;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.EconomySettings;

public class Commandpay extends BaseCommand{

	public Commandpay(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				sender.sendMessage(ChatColor.RED + "That player is not online");
				return;
			}
			
			EconomySettings settings = plugin.getEconomySettings(p);
			
			sender.sendMessage(ChatColor.YELLOW + "Balance: " + settings.getMoney());
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			EconomySettings settings = plugin.getEconomySettings(player);
			
			player.sendMessage(ChatColor.YELLOW + "Balance: " + settings.getMoney());
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online");
				return;
			}
			
			EconomySettings settings = plugin.getEconomySettings(p);
			
			player.sendMessage(ChatColor.YELLOW + "Balance: " + settings.getMoney());
		}
	}
}
