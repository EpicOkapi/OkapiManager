package com.okapi.okapimanager.listeners;

import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class SignListener extends BaseListener{
	
	public SignListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Block b = event.getClickedBlock();
			
			if(b.getState() instanceof Sign){
				Sign sign = (Sign)b.getState();
				String[] lines = sign.getLines();
				Server server = player.getServer();
				
				if(lines[0].startsWith("[") && !lines[0].endsWith("]")) return;
				
				String cmdName = (lines[0].replace("[", "").replace("]", "")).toLowerCase();
				
				if(!(plugin.getCommand(cmdName).getExecutor() instanceof BaseCommand)) return;
				
				BaseCommand command = (BaseCommand) plugin.getCommand(cmdName).getExecutor();
				command.Run(player, server, sign);
			}
		}
	}
}
