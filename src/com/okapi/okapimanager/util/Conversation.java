package com.okapi.okapimanager.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Conversation {
	private String admin;
	private final List<String> players = new ArrayList<String>();
	
	public Conversation(String admin){
		this.admin = admin;
	}
	
	public void AddPlayer(String player){
		players.add(player);
	}
	
	public void AddPlayer(Player player){
		players.add(player.getName());
	}
	
	public void RemovePlayer(String player){
		players.remove(player);
	}
	
	public void RemovePlayer(Player player){
		players.remove(player.getName());
	}
	
	public int GetPlayerCount(){
		return players.size();
	}
	
	public boolean Contains(String player){
		return players.contains(player);
	}
	
	public boolean Contains(Player player){
		return players.contains(player.getName());
	}
	
	public String getAdmin(){
		return admin;
	}
	
	public void setAdmin(String player){
		admin = player;
	}
	
	public void setAdmin(Player player){
		admin = player.getName();
	}
	
	public boolean isAdmin(Player player){
		if(admin.equalsIgnoreCase(player.getName())){
			return true;
		}
		
		return false;
	}
	
	public boolean isAdmin(String player){
		if(admin.equalsIgnoreCase(player)){
			return true;
		}
		
		return false;
	}
	
	public String[] getPlayers(){
		return (String[]) players.toArray();
	}
}
