package com.okapi.okapimanager.commands.worldedit.selection;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandwand extends BaseCommand{

	public Commandwand(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		Material mat = plugin.getWorldEditSettings().getWandMaterial();
		
		player.getInventory().addItem(new ItemStack(mat));
		player.sendMessage(formatMessage("You gained a wand!"));
	}
}
