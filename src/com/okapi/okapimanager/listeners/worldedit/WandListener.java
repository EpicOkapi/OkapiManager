package com.okapi.okapimanager.listeners.worldedit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.listeners.BaseListener;
import com.okapi.okapimanager.settings.PlayerSettings;

public class WandListener extends BaseListener{

	public WandListener(OkapiManager instance) {
		super(instance);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if(player.getItemInHand().getType() == plugin.getWorldEditSettings().getWandMaterial()){
			PlayerSettings settings = plugin.getPlayerSettings(player);
			
			if(event.getAction() == Action.LEFT_CLICK_BLOCK){
				settings.setFirstPosition(event.getClickedBlock().getLocation());
				
				if(settings.getSelection() == null){
					player.sendMessage("First position is set!");
				} else {
					player.sendMessage("First position is set!(" + settings.getSelection().getBlocks().size() + " blocks)");
				}
				
				event.setCancelled(true);
			} else if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
				settings.setSecondPosition(event.getClickedBlock().getLocation());
				
				if(settings.getSelection() == null){
					player.sendMessage("Second position is set!");
				} else {
					player.sendMessage("Second position is set!(" + settings.getSelection().getBlocks().size() + " blocks)");
				}
				
				event.setCancelled(true);
			}
		}
	}
}
