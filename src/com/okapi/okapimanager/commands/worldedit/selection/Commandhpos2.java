package com.okapi.okapimanager.commands.worldedit.selection;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;

public class Commandhpos2 extends BaseCommand{

	public Commandhpos2(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		PlayerSettings settings = plugin.getPlayerSettings(player);
		
		settings.setSecondPosition(player.getTargetBlock(null, plugin.getWorldEditSettings().getMaxSelectionRange()).getLocation());
		
		if(settings.getSelection() == null){
			player.sendMessage(formatMessage("Second position is set!"));
		} else {
			player.sendMessage(formatMessage("Second position is set!(" + settings.getSelection().getBlocks().size() + " blocks)"));
		}
	}
}
