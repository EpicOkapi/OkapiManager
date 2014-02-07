package com.okapi.okapimanager.commands.general;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.okapi.okapimanager.OkapiManager;
import com.okapi.okapimanager.commands.BaseCommand;
import com.okapi.okapimanager.util.api.ItemAPI;

public class Commandrecipe extends BaseCommand{

	public Commandrecipe(OkapiManager instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 1){
			ItemStack stack = ItemAPI.StringToItemstack(args[0]);
			
			if(stack == null){
				player.sendMessage(formatError("That item does not exist!"));
			}
			
			List<Recipe> recipes = plugin.getServer().getRecipesFor(stack);
			
			if(recipes.size() == 0){
				player.sendMessage(formatError("No recipes available for this item!"));
				return;
			}
			
			Recipe recipe = recipes.get(0);
			
			if(recipe instanceof ShapelessRecipe){
				ShapelessRecipe srecipe = (ShapelessRecipe)recipe;
				List<ItemStack> ingredients = srecipe.getIngredientList();
				
				player.sendMessage(ChatColor.GREEN + "-- Shapeless Recipe --");
				
				for(ItemStack item : ingredients){
					player.sendMessage(ChatColor.WHITE + item.getType().toString().toLowerCase());
				}
			} else if(recipe instanceof ShapedRecipe){
				ShapedRecipe srecipe = (ShapedRecipe)recipe;
				Map<Character, ItemStack> ingredients = srecipe.getIngredientMap();
				
				player.sendMessage(ChatColor.GREEN + "-- Shaped Recipe --");
				player.sendMessage(ChatColor.YELLOW + "Shape: ");
				player.sendMessage(srecipe.getShape());
				
				player.sendMessage(ChatColor.YELLOW + "Values: ");
				
				for(Character ch : ingredients.keySet()){
					player.sendMessage(ChatColor.YELLOW + ch.toString() + ": " + ChatColor.WHITE + ingredients.get(ch).getType().toString().toLowerCase());
				}
				
			} else {
				player.sendMessage(ChatColor.YELLOW + "Not available yet!");
			}
		}
	}
}
