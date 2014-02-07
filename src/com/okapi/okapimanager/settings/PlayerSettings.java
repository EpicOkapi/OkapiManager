package com.okapi.okapimanager.settings;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.okapi.okapimanager.util.OkapiConfig;
import com.okapi.okapimanager.util.worldedit.Region;

public class PlayerSettings extends BaseSettings{
	private HashMap<String, Location> Homes = new HashMap<String, Location>();
	private Location Back;
	
	private String LastMessenger;
	
	private double lastCalcAnswer;
	
	private boolean God;
	private boolean UnlimitedBlocks;
	private boolean InstantMine;
	private boolean InstantKill;
	private boolean LongArms;
	
	private boolean Afk;
	private boolean TeleportingEnabled;
	private boolean Jailed;
	private boolean Frozen;
	private boolean Muted;
	
	private boolean isInConversation;
	
	private HashMap<Material, String[]> binds = new HashMap<Material, String[]>();
	
	private Location FirstPosition;
	private Location SecondPosition;
	private Region selection;

	public PlayerSettings(){
		
	}
	
	public void load(String player){
		File configFile = new File("plugins/OkapiManager/Players/" + player + ".yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		Player p = Bukkit.getPlayer(player);
		
		for(World w : p.getServer().getWorlds()){
			Homes.put(w.getName(), config.getLocation("Player.Homes." + w.getName(), null));
		}
		
		God = config.getBoolean("Player.Cheats.God", false);
		UnlimitedBlocks = config.getBoolean("Player.Cheats.UnlimitedBlocks", false);
		InstantMine = config.getBoolean("Player.Cheats.InstantMine", false);
		InstantKill = config.getBoolean("Player.Cheats.InstantKill", false);
		LongArms = config.getBoolean("Player.Cheats.LongArms", false);
		
		TeleportingEnabled = config.getBoolean("Player.Settings.TeleportingEnabled", true);
		Jailed = config.getBoolean("Player.Settings.Jailed", false);
		Frozen = config.getBoolean("Player.Settings.Frozen", false);
		Muted = config.getBoolean("Player.Settings.Muted", false);
	}
	
	public void save(String player){
		File configFile = new File("plugins/OkapiManager/Players/" + player + ".yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		Player p = Bukkit.getPlayer(player);
		
		for(World w : p.getServer().getWorlds()){
			Location home = Homes.get(w.getName());
			
			if(home != null){
				config.setLocation("Player.Homes." + w.getName(), home);
			}
		}
		
		config.set("Player.Cheats.God", God);
		config.set("Player.Cheats.UnlimitedBlocks", UnlimitedBlocks);
		config.set("Player.Cheats.InstantMine", InstantMine);
		config.set("Player.Cheats.InstantKill", InstantKill);
		
		config.set("Player.Settings.TeleportingEnabled", TeleportingEnabled);
		config.set("Player.Settings.Jailed", Jailed);
		config.set("Player.Settings.Frozen", Frozen);
		config.set("Player.Settings.Muted", Muted);
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			return;
		}
	}

	public Location getHome(String world) {
		return Homes.get(world);
	}
	
	public Location getHome(World world) {
		return Homes.get(world.getName());
	}

	public void setHome(String world, Location home) {
		Homes.put(world, home);
	}
	
	public void setHome(World world, Location home) {
		Homes.put(world.getName(), home);
	}
	
	public Location getBack() {
		return Back;
	}

	public void setBack(Location back) {
		Back = back;
	}

	public boolean isGod() {
		return God;
	}

	public void setGod(boolean god) {
		God = god;
	}

	public boolean isUnlimitedBlocks() {
		return UnlimitedBlocks;
	}

	public void setUnlimitedBlocks(boolean unlimitedBlocks) {
		UnlimitedBlocks = unlimitedBlocks;
	}

	public boolean isInstantMine() {
		return InstantMine;
	}

	public void setInstantMine(boolean instantMine) {
		InstantMine = instantMine;
	}

	public boolean isInstantKill() {
		return InstantKill;
	}

	public void setInstantKill(boolean instantKill) {
		InstantKill = instantKill;
	}

	public boolean isMuted() {
		return Muted;
	}

	public void setMuted(boolean muted) {
		Muted = muted;
	}

	public boolean isFrozen() {
		return Frozen;
	}

	public void setFrozen(boolean frozen) {
		Frozen = frozen;
	}

	public boolean isJailed() {
		return Jailed;
	}

	public void setJailed(boolean jailed) {
		Jailed = jailed;
	}

	public boolean isTeleportingEnabled() {
		return TeleportingEnabled;
	}

	public void setTeleportingEnabled(boolean teleportingEnabled) {
		TeleportingEnabled = teleportingEnabled;
	}

	public boolean isAfk() {
		return Afk;
	}

	public void setAfk(boolean afk) {
		Afk = afk;
	}

	public String getLastMessenger() {
		return LastMessenger;
	}

	public void setLastMessenger(String lastMessenger) {
		LastMessenger = lastMessenger;
	}
	
	public HashMap<Material, String[]> getBinds(){
		return binds;
	}

	public boolean isInConversation() {
		return isInConversation;
	}

	public void setInConversation(boolean isInConversation) {
		this.isInConversation = isInConversation;
	}

	public boolean isLongArms() {
		return LongArms;
	}

	public void setLongArms(boolean longArms) {
		LongArms = longArms;
	}

	public double getLastCalcAnswer() {
		return lastCalcAnswer;
	}

	public void setLastCalcAnswer(double lastCalcAnswer) {
		this.lastCalcAnswer = lastCalcAnswer;
	}

	public Location getFirstPosition() {
		return FirstPosition;
	}

	public void setFirstPosition(Location firstPosition) {
		FirstPosition = firstPosition;
		
		if(FirstPosition != null && SecondPosition != null){
			setSelection(new Region(FirstPosition, SecondPosition));
		}
	}

	public Location getSecondPosition() {
		return SecondPosition;
	}

	public void setSecondPosition(Location secondPosition) {
		SecondPosition = secondPosition;
		
		if(FirstPosition != null && SecondPosition != null){
			setSelection(new Region(FirstPosition, SecondPosition));
		}
	}

	public Region getSelection() {
		return selection;
	}

	public void setSelection(Region selection) {
		this.selection = selection;
	}
}
