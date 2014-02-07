package com.okapi.okapimanager.commands.management;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;

public class Commandplugin extends BaseCommand{
	
	public Commandplugin(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		PluginManager pm = server.getPluginManager();
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("enable")){
				Plugin[] plugins = pm.getPlugins();
				
				for(Plugin plugin : plugins){
					if(plugin.getName().equalsIgnoreCase(args[1])){
						if(!pm.isPluginEnabled(plugin)){
							pm.enablePlugin(plugin);
							player.sendMessage(plugin.getName() + " has been enabled!");
						} else {
							player.sendMessage(ChatColor.RED + "This plugin is already enabled!");
						}
					}
				}
			} else if(args[0].equalsIgnoreCase("disable")){
				Plugin[] plugins = pm.getPlugins();
				
				for(Plugin plugin : plugins){
					if(plugin.getName().equalsIgnoreCase(args[1])){
						if(pm.isPluginEnabled(plugin)){
							pm.disablePlugin(plugin);
							player.sendMessage(plugin.getName() + " has been disabled!");
						} else {
							player.sendMessage(ChatColor.RED + "This plugin is already disabled!");
						}
					}
				}
			} else if(args[0].equalsIgnoreCase("reload")){
				Plugin[] plugins = pm.getPlugins();
				
				for(Plugin plugin : plugins){
					if(plugin.getName().equalsIgnoreCase(args[1])){
						if(pm.isPluginEnabled(plugin)){
							pm.disablePlugin(plugin);
							pm.enablePlugin(plugin);
							player.sendMessage(plugin.getName() + " has been reloaded!");
						} else {
							player.sendMessage(ChatColor.RED + "This plugin is disabled, Enable it before you reload!");
						}
					}
				}
			}
		}
	}
}
