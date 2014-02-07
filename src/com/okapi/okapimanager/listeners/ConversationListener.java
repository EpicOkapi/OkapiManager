package com.okapi.okapimanager.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.util.Conversation;

public class ConversationListener extends BaseListener {
	
	public ConversationListener(OkapiManager instance){
		super(instance);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		for(Conversation conv : plugin.getConversations()){
			if(conv.Contains(e.getPlayer())){
				conv.RemovePlayer(e.getPlayer());
				
				for(String pname : conv.getPlayers()){
					Player p = plugin.getServer().getPlayer(pname);
					
					p.sendMessage(ChatColor.YELLOW + e.getPlayer().getName() + " has left the conversation!");
				}
				
				break;
			}
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		for(Conversation conv : plugin.getConversations()){
			if(conv.Contains(e.getPlayer())){
				for(String pname : conv.getPlayers()){
					Player p = plugin.getServer().getPlayer(pname);
					
					p.sendMessage(ChatColor.GREEN + "[Conversation]" + ChatColor.YELLOW + "[" + e.getPlayer().getName() + "] " + e.getMessage());
				}
				
				e.setCancelled(true);
				
				break;
			}
		}
	}
}
