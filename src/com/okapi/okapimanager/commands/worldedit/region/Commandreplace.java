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

public class Commandreplace extends BaseCommand {

	public Commandreplace(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 2){
			PlayerSettings settings = plugin.getPlayerSettings(player);
			Region region = settings.getSelection();
			
			if(region == null){
				player.sendMessage(formatError("You don't have a region selected!"));
				return;
			}
			
			Material matFrom;
			Material matTo;
			
			matFrom = ItemAPI.StringToMaterial(args[0]);
			matTo = ItemAPI.StringToMaterial(args[1]);
			
			if(matFrom == null || matTo == null){
				player.sendMessage(formatError("This item does not exist!"));
				return;
			}
			
			int changed = 0;
			
			for(Block b : region.getBlocks()){
				if(b.getType() == matFrom){
					b.setType(matTo);
					changed++;
				}
			}
			
			player.sendMessage(formatMessage("You changed " + changed + " blocks in your selected region from " + matFrom.toString() + " to " + matTo.toString() + "!"));
		}
	}
}
