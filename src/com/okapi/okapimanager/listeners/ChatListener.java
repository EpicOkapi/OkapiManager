package com.okapi.okapimanager.listeners;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.settings.PlayerSettings;
import com.okapi.okapimanager.util.Wordlist;

public class ChatListener extends BaseListener{
	private Logger logger = Logger.getLogger("Minecraft");
	
	public ChatListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
		if(plugin.getMainSettings().isShowCommandInConsole()){
			logger.info(e.getPlayer().getName() + " has used " + e.getMessage() + "!");
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		PlayerSettings settings = plugin.getPlayerSettings(e.getPlayer());
		
		if(settings.isMuted()){
			e.getPlayer().sendMessage(ChatColor.RED + "You are muted!");
			
			e.setCancelled(true);
		}
		
		Wordlist filter = plugin.getWordfilter();
		
		for(String str : filter.get()){
			if(e.getMessage().toLowerCase().contains(str.toLowerCase())){
				e.getMessage().replace(str, "****");
			}
		}
	}
}
