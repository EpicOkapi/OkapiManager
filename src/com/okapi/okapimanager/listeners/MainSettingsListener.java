package com.okapi.okapimanager.listeners;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import com.okapi.okapimanager.OkapiManager;

public class MainSettingsListener extends BaseListener{
	
	public MainSettingsListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		if(event.getEntityType() == EntityType.ENDER_DRAGON){
			EnderDragon dragon = (EnderDragon)event.getEntity();
			int xpPerPlayer = event.getDroppedExp() / dragon.getKiller().getWorld().getPlayers().size();
			
			for(Player player : dragon.getKiller().getWorld().getPlayers()){
				player.giveExp(xpPerPlayer);
			}
			
			event.setDroppedExp(0);
		}
	}
}
