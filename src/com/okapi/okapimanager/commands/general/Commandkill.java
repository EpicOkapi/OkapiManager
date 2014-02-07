package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandkill extends BaseCommand{

	public Commandkill(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.damage(20);
			p.sendMessage(ChatColor.YELLOW + "You got killed by the server!");
			sender.sendMessage(ChatColor.YELLOW + "You have killed " + p.getName() + "!");
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.damage(20);
			player.sendMessage(ChatColor.YELLOW + "You have commited suicide!");
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(!player.hasPermission("okapimanager.kill.other")){
				player.sendMessage(ChatColor.RED + "You don't have permissions to do that!");
				return;
			}
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			p.damage(20);
			p.sendMessage(ChatColor.YELLOW + "You got killed by " + player.getName() + "!");
			player.sendMessage(ChatColor.YELLOW + "You have killed " + p.getName() + "!");
		}
	}
}
