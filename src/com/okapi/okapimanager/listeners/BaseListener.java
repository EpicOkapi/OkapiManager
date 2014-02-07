package com.okapi.okapimanager.listeners;

import org.bukkit.event.Listener;

import com.okapi.okapimanager.OkapiManager;

public class BaseListener implements Listener {
	protected OkapiManager plugin;
	
	public BaseListener(OkapiManager instance){
		plugin = instance;
	}
}
