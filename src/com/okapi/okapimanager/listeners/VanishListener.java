package com.okapi.okapimanager.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.okapi.okapimanager.OkapiManager;

public class VanishListener extends BaseListener{
	
	public VanishListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onVanishJoin(PlayerJoinEvent event){
		for(String str : plugin.getVanishedPlayers()){
			Player p = plugin.getServer().getPlayer(str);
			
			event.getPlayer().hidePlayer(p);
		}
	}
	
	@EventHandler
	public void onVanishMobTarget(EntityTargetEvent event){
		if(event.getTarget() instanceof Player){
			Player p = (Player)event.getTarget();
			
			if(plugin.getVanishedPlayers().contains(p.getName())){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onVanishPickup(PlayerPickupItemEvent event){
		if(plugin.getVanishedPlayers().contains(event.getPlayer().getName())){
			event.setCancelled(true);
		}
	}
}
