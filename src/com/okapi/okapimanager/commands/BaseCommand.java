package com.okapi.okapimanager.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;

public abstract class BaseCommand implements CommandExecutor{
	protected OkapiManager plugin;
	
	public BaseCommand(OkapiManager instance){
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			
			if(player.hasPermission("okapimanager." + command.getName())){
				Run(player, plugin.getServer(), args);
			} else {
				player.sendMessage(ChatColor.RED + "You dont have permission to use this command!");
			}
		} else {
			Run(sender, plugin.getServer(), args);
		}
		
		return true;
	}
	
	public void Run(Player player, Server server, String[] args){
		player.sendMessage(ChatColor.RED + "You can't run this command in-game!");
	}
	
	public void Run(CommandSender sender, Server server, String[] args){
		sender.sendMessage(ChatColor.RED + "You can't run this command from console!");
	}
	
	public void Run(Player player, Server server, Sign sign){
		this.Run(player, server, new String[] { sign.getLines()[1], sign.getLines()[2], sign.getLines()[3] });
	}
	
	protected String formatMessage(String msg){
		return "[" + ChatColor.GREEN + "OkapiManager" + ChatColor.RESET + "] " + msg;
	}
	
	protected String formatError(String msg){
		return "[" + ChatColor.GREEN + "OkapiManager" + ChatColor.RESET + "] " + ChatColor.RED + msg;
	}
	
	protected String formatList(String[] items){
		String formattedList = "";
		
		if(items.length == 0){
			return formattedList;
		}
		
		for(int i = 0; i < items.length; i++){
			formattedList += items[i];
			
			if(i != (items.length - 1)){
				formattedList += ", ";
			} else {
				formattedList += ".";
			}
		}
		
		return formattedList;
	}
	
	protected String formatList(List<String> items){
		return formatList((String[]) items.toArray());
	}
	
	protected String formatParametersToMessage(String[] items){
		String message = "";
		
		for(int i = 0; i < items.length; i++){
			message += items[i];
			
			if(i != (items.length - 1)){
				message += " ";
			}
		}
		
		return message;
	}
}
