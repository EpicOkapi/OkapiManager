package com.okapi.okapimanager.listeners.command;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.listeners.BaseListener;

public class LockdownListener extends BaseListener{

	public LockdownListener(OkapiManager instance) {
		super(instance);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(plugin.getMainSettings().isLockDown()){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(plugin.getMainSettings().isLockDown()){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onCommand(AsyncPlayerChatEvent event){
		if(plugin.getMainSettings().isLockDown()){
			event.setCancelled(true);
		}
	}
}
