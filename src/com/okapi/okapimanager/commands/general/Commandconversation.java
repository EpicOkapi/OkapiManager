package com.okapi.okapimanager.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.settings.PlayerSettings;
import com.okapi.okapimanager.util.Conversation;

public class Commandconversation extends BaseCommand{

	public Commandconversation(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			Conversation conv = getPlayerConversation(player);
			
			if(conv == null){
				player.sendMessage(ChatColor.RED + "You are not in a conversation!");
				return;
			}
			
			PlayerSettings settings = plugin.getPlayerSettings(player);
			
			if(settings.isInConversation()){
				settings.setInConversation(false);
				player.sendMessage(ChatColor.GREEN + "You are now talking in public chat!");
			} else {
				settings.setInConversation(true);
				player.sendMessage(ChatColor.GREEN + "You are now talking in a conversation chat!");
			}
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("create")){
				if(getPlayerConversation(player) != null){
					player.sendMessage(ChatColor.RED + "You are already in a conversation!");
					return;
				}
				
				plugin.getConversations().add(new Conversation(player.getName()));
				
				player.sendMessage(ChatColor.YELLOW + "You have created a new conversation!");
			} else if(args[0].equalsIgnoreCase("admin")){
				Conversation conv = getPlayerConversation(player);
				
				if(conv == null){
					player.sendMessage(ChatColor.RED + "You are not in a conversation!");
					return;
				}
				
				player.sendMessage(ChatColor.YELLOW + "Admin: " + conv.getAdmin());
			} else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("disband")){
				Conversation conv = getPlayerConversation(player);
				
				if(conv == null){
					player.sendMessage(ChatColor.RED + "You are not in a conversation!");
					return;
				}
				
				if(!conv.isAdmin(player)){
					player.sendMessage(ChatColor.RED + "You are not the admin of this conversation!");
					return;
				}
				
				for(String pname : conv.getPlayers()){
					Player p = server.getPlayer(pname);
					
					p.sendMessage(ChatColor.YELLOW + "Your conversation has been disbanded!");
				}
				
				plugin.getConversations().remove(conv);
				player.sendMessage(ChatColor.YELLOW + "You have disbanded your conversation!");
			} else if(args[0].equalsIgnoreCase("leave")){
				Conversation conv = getPlayerConversation(player);
				
				if(conv == null){
					player.sendMessage(ChatColor.RED + "You are not in a conversation!");
					return;
				}
				
				if(conv.isAdmin(player)){
					player.sendMessage(ChatColor.RED + "You are the admina and can't leave the conversation, you need to disband it!");
					return;
				}
				
				conv.RemovePlayer(player);
				
				for(String pname : conv.getPlayers()){
					Player p = plugin.getServer().getPlayer(pname);
					
					p.sendMessage(ChatColor.YELLOW + player.getName() + " has left the conversation!");
				}
			}
		} else if(args.length == 2){
			if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("invite")){
				Conversation conv = getPlayerConversation(player);
				
				if(conv == null){
					player.sendMessage(ChatColor.RED + "You are not in a conversation!");
					return;
				}
				
				if(!conv.isAdmin(player)){
					player.sendMessage(ChatColor.RED + "You are not the admin of this conversation!");
					return;
				}
				
				Player p = server.getPlayer(args[0]);
				
				if(p == null){
					player.sendMessage(ChatColor.RED + "That player is not online!");
					return;
				}
				
				conv.AddPlayer(p);
				
				for(String pname : conv.getPlayers()){
					Player pl = plugin.getServer().getPlayer(pname);
					
					pl.sendMessage(ChatColor.YELLOW + p.getName() + " has joined the conversation!");
				}
			}
		}
	}
	
	private Conversation getPlayerConversation(Player player){
		for(Conversation conv : plugin.getConversations()){
			if(conv.Contains(player)){
				return conv;
			}
		}
		
		return null;
	}
}
