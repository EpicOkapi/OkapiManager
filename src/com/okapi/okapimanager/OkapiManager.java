package com.okapi.okapimanager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.okapi.okapimanager.commands.cheat.*;
import com.okapi.okapimanager.commands.economy.*;
import com.okapi.okapimanager.commands.general.*;
import com.okapi.okapimanager.commands.management.*;
import com.okapi.okapimanager.commands.management.jail.*;
import com.okapi.okapimanager.commands.teleport.*;
import com.okapi.okapimanager.commands.teleport.home.*;
import com.okapi.okapimanager.commands.teleport.warps.*;
import com.okapi.okapimanager.commands.world.*;
import com.okapi.okapimanager.commands.worldedit.generation.*;
import com.okapi.okapimanager.commands.worldedit.region.*;
import com.okapi.okapimanager.commands.worldedit.selection.*;
import com.okapi.okapimanager.listeners.*;
import com.okapi.okapimanager.listeners.command.*;
import com.okapi.okapimanager.listeners.settings.*;
import com.okapi.okapimanager.listeners.worldedit.*;
import com.okapi.okapimanager.settings.*;
import com.okapi.okapimanager.util.Conversation;
import com.okapi.okapimanager.util.Wordlist;
import com.okapi.okapimanager.util.api.ItemAPI;
import com.okapi.okapimanager.util.api.MobAPI;

public class OkapiManager extends JavaPlugin{
	public static final File MainFolder = new File("plugins/OkapiManager/");
	
	private MainSettings mainSettings = new MainSettings();
	private WorldEditSettings worldEditSettings = new WorldEditSettings();
	private final HashMap<String, PlayerSettings> playerSettings = new HashMap<String, PlayerSettings>();
	private final HashMap<String, EconomySettings> economySettings = new HashMap<String, EconomySettings>();
	private final HashMap<String, BorderSettings> borderSettings = new HashMap<String, BorderSettings>();
	
	private final HashMap<String, LocationSettings> jailSettings = new HashMap<String, LocationSettings>();
	private final HashMap<String, LocationSettings> warpSettings = new HashMap<String, LocationSettings>();
	
	private final List<String> vanishedPlayers = new ArrayList<String>();
	private final List<Conversation> conversations = new ArrayList<Conversation>();
	
	private final Wordlist wordfilter = new Wordlist();
	
	@Override
	public void onDisable() {
		SaveSettings();
	}

	@Override
	public void onEnable() {
		MainFolder.mkdir();
		
		LoadSettings();
		RegisterEvents();
		RegisterCommands();
	}
	
	public void LoadSettings(){
		mainSettings.Load();
		BorderSettings.Load();
		
		MobAPI.Load();
		ItemAPI.Load();
		
		worldEditSettings.Load();
		wordfilter.Load(MainFolder.getPath() + "/wordfilter");
		
		for(World w : getServer().getWorlds()){
			borderSettings.put(w.getName(), new BorderSettings());
			borderSettings.get(w.getName()).LoadSettings(w.getName());
			
			warpSettings.put(w.getName(), new LocationSettings(w.getName()));
			warpSettings.get(w.getName()).LoadSettings("plugins/OkapiManager/Warps/");
			
			jailSettings.put(w.getName(), new LocationSettings(w.getName()));
			jailSettings.get(w.getName()).LoadSettings("plugins/OkapiManager/Jails/");
		}
		
		for(Player p : getServer().getOnlinePlayers()){
			playerSettings.put(p.getName(), new PlayerSettings());
			playerSettings.get(p.getName()).load(p.getName());
			
			economySettings.put(p.getName(), new EconomySettings());
			economySettings.get(p.getName()).load(p.getName());
		}
	}
	
	public void SaveSettings(){
		worldEditSettings.Save();
		
		for(World w : getServer().getWorlds()){
			borderSettings.get(w.getName()).SaveSettings(w.getName());
			borderSettings.remove(w.getName());
			
			warpSettings.get(w.getName()).SaveSettings("plugins/OkapiManager/Warps/");
			warpSettings.remove(w.getName());
			
			jailSettings.get(w.getName()).SaveSettings("plugins/OkapiManager/Jails/");
			jailSettings.remove(w.getName());
		}
		
		for(Player p : getServer().getOnlinePlayers()){
			playerSettings.get(p.getName()).save(p.getName());
			playerSettings.remove(p.getName());
			
			economySettings.get(p.getName()).save(p.getName());
			economySettings.remove(p.getName());
		}
	}
	
	public void RegisterEvents(){
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new MainSettingsListener(this), this);
		pm.registerEvents(new PlayerSettingsListener(this), this);
		pm.registerEvents(new ChatListener(this), this);
		pm.registerEvents(new SignListener(this), this);
		pm.registerEvents(new JailListener(this), this);
		pm.registerEvents(new ConversationListener(this), this);
		
		//Commands
		pm.registerEvents(new BindListener(this), this);
		pm.registerEvents(new LongarmsListener(this), this);
		pm.registerEvents(new LockdownListener(this), this);
		
		//WorldEdit
		pm.registerEvents(new WandListener(this), this);
		
		Plugin vanishPlugin = Bukkit.getPluginManager().getPlugin("VanishNoPacket");
		
		if(vanishPlugin == null || !vanishPlugin.isEnabled()){
			pm.registerEvents(new VanishListener(this), this);
		}
		
		if(BorderSettings.GlobalBorderEnabled){
			pm.registerEvents(new BorderListener(this), this);
		}
	}
	
	public BorderSettings getBorderSettings(String world){
		return borderSettings.get(world);
	}
	
	public PlayerSettings getPlayerSettings(String player){
		return playerSettings.get(player);
	}
	
	public PlayerSettings getPlayerSettings(Player player){
		return playerSettings.get(player.getName());
	}
	
	public void setPlayerSettings(String player, PlayerSettings settings){
		if(playerSettings.containsKey(player)){
			playerSettings.remove(player);
		}
		
		playerSettings.put(player, settings);
	}
	
	public void deletePlayerSettings(String player){
		if(playerSettings.containsKey(player)){
			playerSettings.remove(player);
		}
	}
	
	public EconomySettings getEconomySettings(String player){
		return economySettings.get(player);
	}
	
	public EconomySettings getEconomySettings(Player player){
		return economySettings.get(player.getName());
	}
	
	public void setEconomySettings(String player, EconomySettings settings){
		if(economySettings.containsKey(player)){
			economySettings.remove(player);
		}
		
		economySettings.put(player, settings);
	}
	
	public void deleteEconomySettings(String player){
		if(economySettings.containsKey(player)){
			economySettings.remove(player);
		}
	}
	
	public boolean hasPermission(Player p, String perm){
		if(p.hasPermission(perm) || p.isOp()){
			return true;
		}
		
		return false;
	}
	
	public LocationSettings getWarpSettings(String w){
		return warpSettings.get(w);
	}
	
	public LocationSettings getJailSettings(String w){
		return jailSettings.get(w);
	}
	
	public List<Conversation> getConversations(){
		return conversations;
	}
	
	public Wordlist getWordfilter(){
		return wordfilter;
	}
	
	public boolean isVanished(Player player){
		return isVanished(player.getName());
	}
	
	public boolean isVanished(String player){
		return vanishedPlayers.contains(player);
	}

	public List<String> getVanishedPlayers() {
		return vanishedPlayers;
	}
	
	public void RegisterCommands(){
		//Cheat
		getCommand("anvil").setExecutor(new Commandanvil(this));
		getCommand("armor").setExecutor(new Commandarmor(this));
		getCommand("break").setExecutor(new Commandbreak(this));
		getCommand("eat").setExecutor(new Commandeat(this));
		getCommand("enchant").setExecutor(new Commandenchant(this));
		getCommand("experience").setExecutor(new Commandexperience(this));
		getCommand("fly").setExecutor(new Commandfly(this));
		getCommand("flyspeed").setExecutor(new Commandflyspeed(this));
		getCommand("god").setExecutor(new Commandgod(this));
		getCommand("heal").setExecutor(new Commandheal(this));
		getCommand("instantkill").setExecutor(new Commandinstantkill(this));
		getCommand("instantmine").setExecutor(new Commandinstantmine(this));
		getCommand("item").setExecutor(new Commanditem(this));
		getCommand("jump").setExecutor(new Commandjump(this));
		getCommand("longarms").setExecutor(new Commandlongarms(this));
		getCommand("more").setExecutor(new Commandmore(this));
		getCommand("ocelot").setExecutor(new Commandocelot(this));
		getCommand("repair").setExecutor(new Commandrepair(this));
		getCommand("unlimited").setExecutor(new Commandunlimited(this));
		getCommand("wolf").setExecutor(new Commandwolf(this));
		
		//Economy
		getCommand("balance").setExecutor(new Commandbalance(this));
		getCommand("pay").setExecutor(new Commandpay(this));
		
		//General
		getCommand("afk").setExecutor(new Commandafk(this));
		getCommand("bind").setExecutor(new Commandbind(this));
		getCommand("calculator").setExecutor(new Commandcalculator(this));
		getCommand("clearinventory").setExecutor(new Commandclearinventory(this));
		getCommand("compass").setExecutor(new Commandcompass(this));
		getCommand("conversation").setExecutor(new Commandconversation(this));
		getCommand("down").setExecutor(new Commanddown(this));
		getCommand("explode").setExecutor(new Commandexplode(this));
		getCommand("getpos").setExecutor(new Commandgetpos(this));
		getCommand("hat").setExecutor(new Commandhat(this));
		getCommand("helpop").setExecutor(new Commandhelpop(this));
		getCommand("kill").setExecutor(new Commandkill(this));
		getCommand("lastonline").setExecutor(new Commandlastonline(this));
		getCommand("list").setExecutor(new Commandlist(this));
		getCommand("me").setExecutor(new Commandme(this));
		getCommand("message").setExecutor(new Commandmessage(this));
		getCommand("near").setExecutor(new Commandnear(this));
		getCommand("ping").setExecutor(new Commandping(this));
		getCommand("recipe").setExecutor(new Commandrecipe(this));
		getCommand("reply").setExecutor(new Commandreply(this));
		getCommand("roll").setExecutor(new Commandroll(this));
		getCommand("spawner").setExecutor(new Commandspawner(this));
		getCommand("spawnmob").setExecutor(new Commandspawnmob(this));
		getCommand("status").setExecutor(new Commandstatus(this));
		getCommand("top").setExecutor(new Commandtop(this));
		//getCommand("up").setExecutor(new Commandup(this));
		getCommand("workbench").setExecutor(new Commandworkbench(this));
		
		//Management
		getCommand("ban").setExecutor(new Commandban(this));
		getCommand("banip").setExecutor(new Commandbanip(this));
		getCommand("broadcast").setExecutor(new Commandbroadcast(this));
		getCommand("burn").setExecutor(new Commandburn(this));
		getCommand("clearchat").setExecutor(new Commandclearchat(this));
		getCommand("fireball").setExecutor(new Commandfireball(this));
		getCommand("forcesay").setExecutor(new Commandforcesay(this));
		getCommand("gamemode").setExecutor(new Commandgamemode(this));
		getCommand("kick").setExecutor(new Commandkick(this));
		getCommand("kickall").setExecutor(new Commandkickall(this));
		getCommand("lock").setExecutor(new Commandlock(this));
		getCommand("mute").setExecutor(new Commandmute(this));
		getCommand("plugin").setExecutor(new Commandplugin(this));
		getCommand("serverinfo").setExecutor(new Commandserverinfo(this));
		getCommand("setspawn").setExecutor(new Commandsetspawn(this));
		getCommand("setborder").setExecutor(new Commandsetborder(this));
		getCommand("sudo").setExecutor(new Commandsudo(this));
		getCommand("unban").setExecutor(new Commandunban(this));
		getCommand("unbanip").setExecutor(new Commandunbanip(this));
		getCommand("unmute").setExecutor(new Commandunmute(this));
		getCommand("whitelist").setExecutor(new Commandwhitelist(this));
		
		//Jail
		getCommand("jail").setExecutor(new Commandjail(this));
		getCommand("unjail").setExecutor(new Commandunjail(this));
		getCommand("setjail").setExecutor(new Commandsetjail(this));
		getCommand("deljail").setExecutor(new Commanddeljail(this));
		
		//Teleport
		getCommand("delwarp").setExecutor(new Commanddelwarp(this));
		getCommand("setwarp").setExecutor(new Commandsetwarp(this));
		getCommand("warp").setExecutor(new Commandwarp(this));
		getCommand("back").setExecutor(new Commandback(this));
		getCommand("home").setExecutor(new Commandhome(this));
		getCommand("sethome").setExecutor(new Commandsethome(this));
		getCommand("spawn").setExecutor(new Commandspawn(this));
		getCommand("tp").setExecutor(new Commandtp(this));
		getCommand("tpall").setExecutor(new Commandtpall(this));
		getCommand("tphere").setExecutor(new Commandtphere(this));
		getCommand("tppos").setExecutor(new Commandtppos(this));
		getCommand("tptoggle").setExecutor(new Commandtptoggle(this));
		
		//World
		getCommand("biome").setExecutor(new Commandbiome(this));
		getCommand("chunk").setExecutor(new Commandchunk(this));
		getCommand("pvp").setExecutor(new Commandpvp(this));
		getCommand("time").setExecutor(new Commandtime(this));
		getCommand("weather").setExecutor(new Commandweather(this));
		getCommand("world").setExecutor(new Commandworld(this));
		
		//Worldedit
		getCommand("count").setExecutor(new Commandcount(this));
		getCommand("pos1").setExecutor(new Commandpos1(this));
		getCommand("pos2").setExecutor(new Commandpos2(this));
		getCommand("hpos1").setExecutor(new Commandhpos1(this));
		getCommand("hpos2").setExecutor(new Commandhpos2(this));
		getCommand("wand").setExecutor(new Commandwand(this));
		getCommand("expand").setExecutor(new Commandexpand(this));
		
		getCommand("floor").setExecutor(new Commandfloor(this));
		getCommand("outline").setExecutor(new Commandoutline(this));
		getCommand("overlay").setExecutor(new Commandoverlay(this));
		getCommand("replace").setExecutor(new Commandreplace(this));
		getCommand("set").setExecutor(new Commandset(this));
		getCommand("walls").setExecutor(new Commandwalls(this));
		
		getCommand("cube").setExecutor(new Commandcube(this));
		
		//Special Commands
		Plugin vanishPlugin = Bukkit.getPluginManager().getPlugin("VanishNoPacket");
		
		if(vanishPlugin == null || !vanishPlugin.isEnabled()){
			getCommand("vanish").setExecutor(new Commandvanish(this));
		}
	}

	public WorldEditSettings getWorldEditSettings() {
		return worldEditSettings;
	}
	
	public MainSettings getMainSettings(){
		return mainSettings;
	}
}
