package com.okapi.okapimanager.util.api;

import java.util.HashMap;

import org.bukkit.entity.EntityType;

public class MobAPI {
	private static HashMap<String, EntityType> creatures;
	
	public static void Load(){
		creatures = new HashMap<String, EntityType>();

		creatures.put("mooshroom", EntityType.MUSHROOM_COW);
		creatures.put("golem", EntityType.IRON_GOLEM);
		creatures.put("dragon", EntityType.ENDER_DRAGON);
		creatures.put("dog", EntityType.WOLF);
		creatures.put("cat", EntityType.OCELOT);
		creatures.put("pigman", EntityType.PIG_ZOMBIE);
		creatures.put("zombiepigman", EntityType.PIG_ZOMBIE);
		
		creatures.put("tnt", EntityType.PRIMED_TNT);
		creatures.put("experience", EntityType.EXPERIENCE_ORB);
		creatures.put("xp", EntityType.EXPERIENCE_ORB);
		creatures.put("hook", EntityType.FISHING_HOOK);
	}
	
	public static EntityType StringToEntity(String str){
		EntityType entity = EntityType.valueOf(str.toUpperCase());
		
		if(entity != null){
			return entity;
		}
		
		entity = EntityType.valueOf(str.toLowerCase().replace("_", ""));
		
		if(entity != null){
			return entity;
		}
		
		if(creatures.containsKey(str.toLowerCase())){
			return creatures.get(str.toLowerCase());
		}
		
		return null;
	}
}
