package com.okapi.okapimanager.settings;

import java.io.File;
import java.io.IOException;

import com.okapi.okapimanager.util.OkapiConfig;

public class EconomySettings extends BaseSettings{
	private double Money;
	
	public EconomySettings(){
		
	}
	
	public EconomySettings(double money) {
		Money = money;
	}
	
	public void load(String player){
		File configFile = new File("plugins/OkapiManager/EconomyDatabase.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		Money = config.getDouble(player, 0.0);
	}
	
	public void save(String player){
		File configFile = new File("plugins/OkapiManager/EconomyDatabase.yml");
		OkapiConfig config = OkapiConfig.loadConfiguration(configFile);
		
		config.set(player, Money);
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			return;
		}
	}

	public double getMoney(){
		return Money;
	}
	
	public void Add(double money){
		Money += money;
	}
	
	public boolean Subtract(double money){
		if((Money - money) <= 0){
			Money -= money;
			
			return true;
		}
		
		return false;
	}
	
	public void Multiply(double amount){
		Money *= amount;
	}
	
	public boolean Divide(double amount){
		if(amount != 0){
			Money /= amount;
			return true;
		}
		
		return false;
	}
	
	public void Set(double money){
		this.Money = money;
	}
	
	public void Reset(){
		Money = 0.0D;
	}
	
	public boolean hasEnough(double money){
		if(money <= Money){
			return true;
		}
		
		return false;
	}
	
	public boolean hasOver(double money){
		if(Money > money){
			return true;
		}
		
		return false;
	}
	
	public boolean hasUnder(double money){
		if(Money > money){
			return true;
		}
		
		return false;
	}
	
	public boolean equals(double money){
		if(Money == money){
			return true;
		}
		
		return false;
	}
}
