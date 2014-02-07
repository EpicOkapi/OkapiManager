package com.okapi.okapimanager.settings;

import java.io.File;
import java.io.IOException;

import com.okapi.okapimanager.util.OkapiConfig;

public class MainSettings {
	private boolean ShowCommandInConsole;
	private boolean EconomyEnabled;
	
	private int SpawnmobLimit;
	
	private boolean CreeperProtection;
	private boolean ExplosionProtection;
	private boolean FireProtection;

	private boolean ShareEnderXp;
	
	private boolean LockDown;
	
	public void Load(){
		File configFile = new File("plugins/OkapiManager/MainSettings.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		ShowCommandInConsole = config.getBoolean("Console.Show-Commands", false);
		
		SpawnmobLimit = config.getInt("Spawnmob-Limit", 10);
		
		ShareEnderXp = config.getBoolean("Share-Enderdragon-Xp", false);
		
		CreeperProtection = config.getBoolean("Protection.Creeper-Explosion", false);
		ExplosionProtection = config.getBoolean("Protection.Explosion", false);
		FireProtection = config.getBoolean("Protection.Fire", false);
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			//Do nothing
		}
	}
	
	public boolean isEconomyEnabled() {
		return EconomyEnabled;
	}

	public void setEconomyEnabled(boolean economyEnabled) {
		EconomyEnabled = economyEnabled;
	}

	public int getSpawnmobLimit() {
		return SpawnmobLimit;
	}

	public void setSpawnmobLimit(int spawnmobLimit) {
		SpawnmobLimit = spawnmobLimit;
	}

	public boolean isShareEnderXp() {
		return ShareEnderXp;
	}

	public void setShareEnderXp(boolean shareEnderXp) {
		ShareEnderXp = shareEnderXp;
	}

	public boolean isLockDown() {
		return LockDown;
	}

	public void setLockDown(boolean lockDown) {
		LockDown = lockDown;
	}

	public boolean isShowCommandInConsole() {
		return ShowCommandInConsole;
	}

	public boolean isCreeperProtection() {
		return CreeperProtection;
	}

	public boolean isExplosionProtection() {
		return ExplosionProtection;
	}

	public boolean isFireProtection() {
		return FireProtection;
	}
}
