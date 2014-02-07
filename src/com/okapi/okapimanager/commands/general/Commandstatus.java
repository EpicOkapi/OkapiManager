package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandstatus extends BaseCommand{

	public Commandstatus(OkapiManager instance) {
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
			
			Status(sender, p);
		}
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			Status(player, player);
		} else if(args.length == 1){
			Player p = server.getPlayer(args[0]);
			
			if(p == null){
				player.sendMessage(ChatColor.RED + "That player is not online!");
				return;
			}
			
			Status(player, p);
		}
	}
	
	private void Status(CommandSender receiver, Player status){
		PlayerSettings settings = plugin.getPlayerSettings(status);
		receiver.sendMessage(ChatColor.GREEN + "--- Status of " + status.getName() + " ---");
		receiver.sendMessage(ChatColor.YELLOW + "Afk: " + settings.isAfk());
		receiver.sendMessage(ChatColor.YELLOW + "Teleporting enabled: " + settings.isTeleportingEnabled());
		receiver.sendMessage(ChatColor.YELLOW + "Jailed: " + settings.isJailed());
		receiver.sendMessage(ChatColor.YELLOW + "Frozen: " + settings.isFrozen());
		receiver.sendMessage(ChatColor.YELLOW + "Muted: " + settings.isMuted());
		receiver.sendMessage(ChatColor.YELLOW + "Gamemode: " + status.getGameMode().toString().toLowerCase());
		receiver.sendMessage(ChatColor.YELLOW + "Godmode: " + settings.isGod());
		receiver.sendMessage(ChatColor.YELLOW + "Instant Kill: " + settings.isInstantKill());
		receiver.sendMessage(ChatColor.YELLOW + "Instant Mine: " + settings.isInstantMine());
		receiver.sendMessage(ChatColor.YELLOW + "Unlimited Blocks: " + settings.isUnlimitedBlocks());
	}
}
