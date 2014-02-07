package com.okapi.okapimanager.util.api;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class ItemAPI {
	private static HashMap<String, Material> materials;
	
	public static void Load(){
		materials = new HashMap<String, Material>();

		materials.put("stairs", Material.WOOD_STAIRS);
		materials.put("birchstairs", Material.BIRCH_WOOD_STAIRS);
		materials.put("junglestairs", Material.JUNGLE_WOOD_STAIRS);
		materials.put("darkstairs", Material.SPRUCE_WOOD_STAIRS);
		materials.put("netherstairs", Material.NETHER_BRICK_STAIRS);
		materials.put("stonestairs", Material.SMOOTH_STAIRS);
		materials.put("slab", Material.STEP);
		materials.put("redstonetorch", Material.REDSTONE_TORCH_OFF);
		materials.put("redstonelamp", Material.REDSTONE_LAMP_OFF);
		
		materials.put("bottle", Material.GLASS_BOTTLE);
		materials.put("pearl", Material.ENDER_PEARL);
		materials.put("spawner", Material.MOB_SPAWNER);
		materials.put("lily", Material.WATER_LILY);
		materials.put("frame", Material.ITEM_FRAME);
		materials.put("bed", Material.BED_BLOCK);
		materials.put("bookcase", Material.BOOKSHELF);
		materials.put("repeater", Material.DIODE);
		materials.put("portal", Material.ENDER_PORTAL);
		materials.put("endereye", Material.EYE_OF_ENDER);
		materials.put("lantern", Material.JACK_O_LANTERN);
		materials.put("note", Material.NOTE_BLOCK);
		
		materials.put("beef", Material.COOKED_BEEF);
		materials.put("steak", Material.COOKED_BEEF);
		materials.put("chicken", Material.COOKED_CHICKEN);
		
	}
	
	public static ItemStack StringToItemstack(String str){
		String[] ids = str.split(":");
		ItemStack stack = new ItemStack(StringToMaterial(str));
		
		if(ids.length == 1){
			return stack;
		}
		
		if(ids.length == 2){
			byte b;
			
			try {
				b = Byte.parseByte(ids[1]);
			} catch (Exception ex){
				return stack;
			}
			
			MaterialData data = new MaterialData(StringToMaterial(str), b);
			
			return data.toItemStack();
		}
		
		return null;
	}
	
	public static Material StringToMaterial(String str){
		try {
			int id = Integer.parseInt(str);
			
			return Material.getMaterial(id);
		} catch (NumberFormatException ex){
			
		}
		
		Material mat = Material.getMaterial(str.toUpperCase());
		
		if(mat != null){
			return mat;
		}
		
		mat = Material.getMaterial(str.replace("_", "").toUpperCase());
		
		if(materials.containsKey(str.toLowerCase())){
			return materials.get(str.toLowerCase());
		}
		
		return null;
	}
}
