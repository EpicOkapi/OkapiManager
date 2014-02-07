package com.okapi.okapimanager.listeners.command;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.settings.PlayerSettings;

public class BindListener extends CommandListener{

	public BindListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onBindPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		PlayerSettings settings = plugin.getPlayerSettings(player);
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Material mat = player.getItemInHand().getType();
			
			if(settings.getBinds().containsKey(mat)){
				String[] command = settings.getBinds().get(mat);
				String playerCommand = "/" + command[0] + " ";
				
				for(int i = 1; i < command.length; i++){
					playerCommand += command[i] + " ";
				}
				
				player.chat(playerCommand);
				
				if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
					event.setCancelled(true);
				}
			}
		}
	}
}
