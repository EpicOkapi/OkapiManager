package com.okapi.okapimanager.commands.management;

import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandfireball extends BaseCommand{
	
	public Commandfireball(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		Fireball fireball = (Fireball)player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREBALL);
		fireball.setDirection(player.getLocation().getDirection());
	}
}
