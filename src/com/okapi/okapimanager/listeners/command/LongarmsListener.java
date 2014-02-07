package com.okapi.okapimanager.listeners.command;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.settings.PlayerSettings;

public class LongarmsListener extends CommandListener{
	
	public LongarmsListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onLongArmsInteractEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		PlayerSettings settings = plugin.getPlayerSettings(event.getPlayer());
		
		if(settings.isLongArms()){
			Block block = player.getTargetBlock(null, 30);
			
			if(event.getAction() == Action.RIGHT_CLICK_AIR){
				List<Block> lastBlocks = player.getLastTwoTargetBlocks(null, 100);
				BlockFace blockFace = lastBlocks.get(1).getFace(lastBlocks.get(0));
				Block targetBlock = block.getRelative(blockFace);
				ItemStack itemInHand = player.getItemInHand();
				
				if(targetBlock.getType() == Material.AIR){
					targetBlock.setType(itemInHand.getType());

					if(player.getGameMode() != GameMode.CREATIVE){
						if(itemInHand.getAmount() != 1){
							itemInHand.setAmount(itemInHand.getAmount() - 1);
						} else {
							player.getInventory().remove(itemInHand);
						}
					}
				}
			} else if(event.getAction() == Action.LEFT_CLICK_AIR){
				if(player.getGameMode() == GameMode.CREATIVE){
					block.breakNaturally();
				}
			}
		}
	}
}
