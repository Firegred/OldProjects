package me.Firegred.Treasures;

import org.bukkit.inventory.ItemStack;

public class Items {

	public static boolean hasTreasure(String region, Main m) {
		if(m.getConfig().get("Loot." + region + ".Treasure") == null) {
			return false;
		}
		else{
			return true;
		}
	}
	public static void MaxMinCorrection(String name, Main m) {
		int number = 0;
		for(String g : m.getConfig().getConfigurationSection("Loot." + name + ".Treasure").getKeys(false)) {
			number++;
		}
		int max = m.getConfig().getInt("Loot." + name + ".maxLoot");
		int min = m.getConfig().getInt("Loot." + name + ".minLoot");
		if(max > number) {
			m.getConfig().set("Loot." + name + ".maxLoot", max-1);
		}
		if(min > number) {
			m.getConfig().set("Loot." + name + ".minLoot", min-1);
		}
		//if(max == 0 || min == 0) {
		//	m.getConfig().set("Loot." + name + ".enabled", false);
		//}
		m.saveConfig();
		m.reloadConfig();
	}
	
	
	public static int ItemCount(String name, Main m) {
		int number = 0;
		for(String g : m.getConfig().getConfigurationSection("Loot." + name + ".Treasure").getKeys(false)) {
			number++;
		}
		//System.out.println(number);
		return number;
	}
	
	public static void addItem(String name, String region, ItemStack stack, Main m) {
		m.getConfig().set("Loot." + region + ".Treasure." + name + ".item", stack);
		m.getConfig().set("Loot." + region + ".Treasure." + name + ".min", 1);
		m.getConfig().set("Loot." + region + ".Treasure." + name + ".max", 1);
		m.saveConfig();
		m.reloadConfig();
	}
	public static void removeItem(String name, String region, Main m) {
		m.getConfig().set("Loot." + region + ".Treasure." + name, null);
		m.saveConfig();
		m.reloadConfig();
	}
	public static void setMax(String name, String region, Integer num,Main m) {
		m.getConfig().set("Loot." + region + ".Treasure." + name + ".max", num);
		m.saveConfig();
		m.reloadConfig();
	}
	public static void setMin(String name, String region, Integer num,Main m) {
		m.getConfig().set("Loot." + region + ".Treasure." + name + ".min", num);
		m.saveConfig();
		m.reloadConfig();
	}
	public static boolean hasMin(String name, String region, Integer value, Main m) {
		if(value <=m.getConfig().getInt("Loot." + region + ".Treasure." + name + ".max")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean hasMax(String name, String region, Integer value, Main m) {
		if(value >=m.getConfig().getInt("Loot." + region + ".Treasure." + name + ".min")) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean itemExists(String name, String region, Main m) {
		if(m.getConfig().get("Loot." + region + ".Treasure." + name) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
