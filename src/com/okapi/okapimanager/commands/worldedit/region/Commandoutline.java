package com.okapi.okapimanager.commands.worldedit.region;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;
import com.okapi.okapimanager.util.api.ItemAPI;
import com.okapi.okapimanager.util.worldedit.Region;

public class Commandoutline extends BaseCommand {

	public Commandoutline(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			PlayerSettings settings = plugin.getPlayerSettings(player);
			Region region = settings.getSelection();
			
			if(region == null){
				player.sendMessage(formatError("You don't have a region selected!"));
				return;
			}
			
			Material mat = ItemAPI.StringToMaterial(args[0]);
			
			if(mat == null){
				player.sendMessage(formatError("This item does not exist!"));
				return;
			}
			
			int changed = 0;
			
			for(Block b : region.getBlocks()){
				if(b.getX() == region.getMaxX() || b.getX() == region.getMinX() ||
						b.getZ() == region.getMaxZ() || b.getZ() == region.getMinZ() ||
						b.getY() == region.getMaxY() || b.getY() == region.getMinY()){
					b.setType(mat);
					changed++;
				}
			}
			
			player.sendMessage(formatMessage("You changed " + changed + " blocks in your selected region to " + mat.toString() + "!"));
		}
	}
}
