package com.okapi.okapimanager.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.settings.PlayerSettings;

public class JailListener extends BaseListener {
	public JailListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		PlayerSettings settings = plugin.getPlayerSettings(e.getPlayer());
		
		if(settings.isJailed()){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		PlayerSettings settings = plugin.getPlayerSettings(e.getPlayer());
		
		if(settings.isJailed()){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
		PlayerSettings settings = plugin.getPlayerSettings(e.getPlayer());
		
		if(settings.isJailed()){
			e.setCancelled(true);
		}
	}
}
