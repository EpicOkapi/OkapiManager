package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandmessage extends BaseCommand{

	public Commandmessage(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length < 2){
			sender.sendMessage(ChatColor.RED + "Enter a name and a message!");
			return;
		}
		
		Player p = server.getPlayer(args[0]);
		
		if(p == null){
			sender.sendMessage(ChatColor.RED + "That player is not online!");
			return;
		}
		
		String msg = ChatColor.WHITE + "";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		sender.sendMessage(ChatColor.YELLOW + "[You>" + p.getName() + "] " + msg);
		p.sendMessage(ChatColor.YELLOW + "[Server>You] " + msg);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length < 2){
			player.sendMessage(ChatColor.RED + "Enter a name and a message!");
			return;
		}
		
		Player p = server.getPlayer(args[0]);
		
		if(p == null){
			player.sendMessage(ChatColor.RED + "That player is not online!");
			return;
		}
		
		if(p == player){
			player.sendMessage(ChatColor.RED + "You can't send yourself a message");
		}
		
		String msg = ChatColor.WHITE + "";
		
		for(int i = 1; i < args.length; i++){
			msg += args[i] + " ";
		}
		
		player.sendMessage(ChatColor.YELLOW + "[You>" + p.getName() + "] " + msg);
		p.sendMessage(ChatColor.YELLOW + "[" + player.getName() + ">You] " + msg);
		
		PlayerSettings settings = plugin.getPlayerSettings(p);
		settings.setLastMessenger(player.getName());
	}
}
