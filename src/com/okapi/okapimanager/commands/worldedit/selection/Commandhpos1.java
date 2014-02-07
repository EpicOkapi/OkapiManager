package com.okapi.okapimanager.commands.worldedit.selection;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandhpos1 extends BaseCommand{

	public Commandhpos1(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		PlayerSettings settings = plugin.getPlayerSettings(player);
		
		settings.setFirstPosition(player.getTargetBlock(null, plugin.getWorldEditSettings().getMaxSelectionRange()).getLocation());
		
		if(settings.getSelection() == null){
			player.sendMessage(formatMessage("First position is set!"));
		} else {
			player.sendMessage(formatMessage("First position is set!(" + settings.getSelection().getBlocks().size() + " blocks)"));
		}
	}
}
