package com.okapi.okapimanager.commands.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandback extends BaseCommand{

	public Commandback(OkapiManager instance) {
		super(instance);
	}

	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.getBack() == null){
				player.sendMessage(ChatColor.RED + "You don't have a back location yet!");
			} else {
				player.teleport(settings.getBack(), TeleportCause.COMMAND);
				player.sendMessage(ChatColor.YELLOW + "You have teleported back!");
			}
		}
	}
}
