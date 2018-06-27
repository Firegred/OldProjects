package me.Firegred.Treasures;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Wand {

	private static HashMap<Player, Location> p1 = new HashMap<Player, Location>();
	private static HashMap<Player, Location> p2 = new HashMap<Player, Location>();
	public static void giveWand(Player p) {
		ItemStack wand = ItemUtill.createItem(Material.STONE_AXE, ChatColor.YELLOW + "Treasure Wand", ChatColor.GREEN + "Use this to set treasure regions");
		p.getInventory().addItem(wand);
		p.updateInventory();
	}
	
	public static void setP1(Player p, Location l) {
		p1.put(p, l);
	}
	public static void setP2(Player p, Location l) {
		p2.put(p, l);
	}
	public static boolean hasP1(Player p) {
		if(p1.containsKey(p)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean hasP2(Player p) {
		if(p2.containsKey(p)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void clearPoints(Player p) {
		p1.remove(p);
		p2.remove(p);
}
	public static void removeRegion(String g, Main m) {
		m.getConfig().set("Loot." + g, null);
		m.saveConfig();
		m.reloadConfig();
	}
	public static void resetRegion(String g, Main m, Player p) {
		m.getConfig().set("Loot." + g + ".x1", p1.get(p).getBlockX());
		m.getConfig().set("Loot." + g + ".y1", p1.get(p).getBlockY());
		m.getConfig().set("Loot." + g + ".z1", p1.get(p).getBlockZ());
		m.getConfig().set("Loot." + g + ".x2", p2.get(p).getBlockX());
		m.getConfig().set("Loot." + g + ".y2", p2.get(p).getBlockY());
		m.getConfig().set("Loot." + g + ".z2", p2.get(p).getBlockZ());
		m.getConfig().set("Loot." + g + ".world", p.getWorld().getName());
		m.saveConfig();
		m.reloadConfig();
	}
	public static void setRegion(String g, Main m, Player p) {
		m.getConfig().set("Loot." + g + ".x1", p1.get(p).getBlockX());
		m.getConfig().set("Loot." + g + ".y1", p1.get(p).getBlockY());
		m.getConfig().set("Loot." + g + ".z1", p1.get(p).getBlockZ());
		m.getConfig().set("Loot." + g + ".x2", p2.get(p).getBlockX());
		m.getConfig().set("Loot." + g + ".y2", p2.get(p).getBlockY());
		m.getConfig().set("Loot." + g + ".z2", p2.get(p).getBlockZ());
		m.getConfig().set("Loot." + g + ".world", p.getWorld().getName());
		m.getConfig().set("Loot." + g + ".time", 0);
		m.getConfig().set("Loot." + g + ".enabled", false);
		m.getConfig().set("Loot." + g + ".minLoot", 0);
		m.getConfig().set("Loot." + g + ".maxLoot", 0);
		m.getConfig().set("Loot." + g + ".onlyAir", false);
		m.getConfig().set("Loot." + g + ".maxChest", value);
		m.saveConfig();
		m.reloadConfig();
	}
	
}
