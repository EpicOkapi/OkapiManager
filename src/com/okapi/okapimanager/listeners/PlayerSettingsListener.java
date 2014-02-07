package com.okapi.okapimanager.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.settings.EconomySettings;
import com.okapi.okapimanager.settings.PlayerSettings;

public class PlayerSettingsListener extends BaseListener {
	
	public PlayerSettingsListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		plugin.setPlayerSettings(event.getPlayer().getName(), new PlayerSettings());
		plugin.getPlayerSettings(event.getPlayer()).load(event.getPlayer().getName());
		
		plugin.setEconomySettings(event.getPlayer().getName(), new EconomySettings());
		plugin.getEconomySettings(event.getPlayer()).load(event.getPlayer().getName());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		plugin.getPlayerSettings(event.getPlayer().getName()).save(event.getPlayer().getName());
		plugin.deletePlayerSettings(event.getPlayer().getName());
		
		plugin.getEconomySettings(event.getPlayer()).save(event.getPlayer().getName());
		plugin.deletePlayerSettings(event.getPlayer().getName());
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		PlayerSettings settings = plugin.getPlayerSettings(player.getName());
		
		settings.setBack(player.getLocation());
		player.sendMessage(ChatColor.YELLOW + "Use /back to teleport back to the location you died!");
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player)event.getEntity();
			PlayerSettings settings = plugin.getPlayerSettings(player.getName());
			
			if(settings.isGod())
				event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if(plugin.getPlayerSettings(player).isInstantMine()){
			block.breakNaturally();
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(plugin.getPlayerSettings(player).isUnlimitedBlocks()){
			player.getInventory().addItem(new ItemStack(event.getBlockPlaced().getType()));
		}
	}
}
