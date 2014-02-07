package com.okapi.okapimanager.commands.worldedit.selection;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;
import com.okapi.okapimanager.util.worldedit.Direction;

public class Commandexpand extends BaseCommand{

	public Commandexpand(OkapiManager instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			Direction direction = Direction.valueOf(args[0].toUpperCase());
			PlayerSettings settings = plugin.getPlayerSettings(player);
			
			if(direction == null){
				player.sendMessage(formatError("Enter a valid expansion type!"));
			}
			
			if(Expand(settings, direction)){
				player.sendMessage(formatMessage("You succesfully expanded your selection!"));
			} else {
				player.sendMessage(formatError("Enter a valid expansion type!"));
			}
		}
	}
	
	public boolean Expand(PlayerSettings settings, Direction direction){
		if(direction == Direction.UP){
			if(settings.getFirstPosition().getY() > settings.getSecondPosition().getY()){
				settings.getFirstPosition().setY(settings.getFirstPosition().getY() + 1);
			} else {
				settings.getSecondPosition().setY(settings.getSecondPosition().getY() + 1);
			}
		} else if(direction == Direction.DOWN){
			if(settings.getFirstPosition().getY() < settings.getSecondPosition().getY()){
				settings.getFirstPosition().setY(settings.getFirstPosition().getY() - 1);
			} else {
				settings.getSecondPosition().setY(settings.getSecondPosition().getY() - 1);
			}
		} else {
			return false;
		}
		
		return true;
	}
}
