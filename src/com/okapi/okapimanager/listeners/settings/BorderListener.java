package com.okapi.okapimanager.listeners.settings;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.settings.BorderSettings;

public class BorderListener extends SettingsListener{

	public BorderListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Location loc = event.getTo();
		BorderSettings settings = plugin.getBorderSettings(event.getTo().getWorld().getName());
		
		if(!settings.BorderEnabled){
			return;
		}
		
		if(loc.getX() > settings.MaximumX || loc.getX() < settings.MinimumX ||
				loc.getZ() > settings.MaximumZ || loc.getZ() < settings.MinimumZ){
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + BorderSettings.BorderMessage);
		}
	}
	
	@EventHandler
	public void onVehicleMove(VehicleMoveEvent event){
		Location loc = event.getTo();
		BorderSettings settings = plugin.getBorderSettings(event.getTo().getWorld().getName());
		
		if(!settings.BorderEnabled){
			return;
		}
		
		if(loc.getX() > settings.MaximumX || loc.getX() < settings.MinimumX ||
				loc.getZ() > settings.MaximumZ || loc.getZ() < settings.MinimumZ){
			Vehicle veh = event.getVehicle();
			
			veh.teleport(event.getFrom());
			
			if(veh.getPassenger() instanceof Player){
				((Player)veh.getPassenger()).sendMessage(ChatColor.RED + BorderSettings.BorderMessage);
			}
		}
	}
}
