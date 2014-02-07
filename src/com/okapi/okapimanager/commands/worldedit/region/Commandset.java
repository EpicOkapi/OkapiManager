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

public class Commandset extends BaseCommand {

	public Commandset(OkapiManager instance) {
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
			
			for(Block b : region.getBlocks()){
				b.setType(mat);
			}
			
			player.sendMessage(formatMessage("You changed " + region.getBlocks().size() + " blocks in your selected region to " + mat.toString() + "!"));
		}
	}
}
