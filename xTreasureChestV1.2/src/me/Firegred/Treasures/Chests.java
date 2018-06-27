package me.Firegred.Treasures;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Chests {

	public static void OpenTreasure(Player p, String name, Main m) {
		Inventory in = Bukkit.getServer().createInventory(null, 54, ChatColor.GREEN + "Treasure " + ChatColor.YELLOW + name);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		itemx(name, m, items);
		for(int i = 0; i < items.size(); i++) {
			in.setItem(i, items.get(i));
		}
		ItemStack enableIcon = new ItemStack(enableIcon(name, m));
		ItemStack time = new ItemStack(time(name, m));
		ItemStack min = new ItemStack(min(name, m));
		ItemStack max = new ItemStack(max(name, m));
		ItemStack air = new ItemStack(air(name, m));
		in.setItem(49, air);
		in.setItem(50, enableIcon);
		in.setItem(51, time);
		in.setItem(52, min);
		in.setItem(53, max);
		items.clear();
		p.openInventory(in);
		
		
	}	
	private static ItemStack enableIcon(String name, Main m) {
		boolean enabled = m.getConfig().getBoolean("Loot." + name + ".enabled");
		if(enabled == true) {
			ItemStack t = new ItemStack(Material.EMERALD_BLOCK, 1);
			ItemMeta met = t.getItemMeta();
			met.setDisplayName(ChatColor.YELLOW + "Treasure enabled: " + ChatColor.GREEN + "true");
			t.setItemMeta(met);
			return t;
		}
		else {
			ItemStack t = new ItemStack(Material.REDSTONE_BLOCK, 1);
			ItemMeta met = t.getItemMeta();
			met.setDisplayName(ChatColor.YELLOW + "Treasure enabled: " + ChatColor.RED + "false");
			t.setItemMeta(met);
			return t;
		}
		
		
	}
	
	private static ItemStack air(String name, Main m) {
		ItemStack t = new ItemStack(Material.GLASS, 1);
		ItemMeta met = t.getItemMeta();
		boolean bool = m.getConfig().getBoolean("Loot." + name + ".onlyAir");
		if(bool == true) {
		met.setDisplayName(ChatColor.YELLOW + "Only replace air: " + ChatColor.GREEN + "true");
		t.setItemMeta(met);
		return t;
		}
		else {
		met.setDisplayName(ChatColor.YELLOW + "Only replace air: " + ChatColor.RED + "false");
		t.setItemMeta(met);
		return t;
		}
		
	}
	private static ItemStack override(String name, Main m) {
		ItemStack t = new ItemStack(Material.PUMPKIN, 1);
		ItemMeta met = t.getItemMeta();
		int time = m.getConfig().getInt("Loot." + name + ".time");
		met.setDisplayName(ChatColor.GREEN + "Time: " + ChatColor.YELLOW + time + ChatColor.GREEN + " seconds");
		t.setItemMeta(met);
		return t;
	}
	private static ItemStack time(String name, Main m) {
		ItemStack t = new ItemStack(Material.COMPASS, 1);
		ItemMeta met = t.getItemMeta();
		int time = m.getConfig().getInt("Loot." + name + ".time");
		met.setDisplayName(ChatColor.GREEN + "Time: " + ChatColor.YELLOW + time + ChatColor.GREEN + " seconds");
		t.setItemMeta(met);
		return t;
	}
	
	private static ItemStack max(String name, Main m) {
		ItemStack t = new ItemStack(Material.EMERALD, 1);
		ItemMeta met = t.getItemMeta();
		int max = m.getConfig().getInt("Loot." + name + ".maxLoot");
		met.setDisplayName(ChatColor.GREEN + "Max Loot: " + ChatColor.YELLOW + max);
		t.setItemMeta(met);
		return t;
		
	}
	private static ItemStack min(String name, Main m) {
		ItemStack t = new ItemStack(Material.EMERALD, 1);
		ItemMeta met = t.getItemMeta();
		int min = m.getConfig().getInt("Loot." + name + ".minLoot");
		met.setDisplayName(ChatColor.GREEN + "Min Loot: " + ChatColor.YELLOW + min);
		t.setItemMeta(met);
		return t;
		
	}
	private static void itemx(String name, Main m, ArrayList<ItemStack> s) {
		for(String g : m.getConfig().getConfigurationSection("Loot." + name + ".Treasure").getKeys(false)) {
					ItemStack item = m.getConfig().getItemStack("Loot." + name + ".Treasure." + g + ".item").clone();
					ItemMeta meta = item.getItemMeta();
					int min = m.getConfig().getInt("Loot." + name + ".Treasure." + g + ".min");
					int max = m.getConfig().getInt("Loot." + name + ".Treasure." + g + ".max");
					if(item.getItemMeta().hasLore()) {
						ArrayList<String> gg = (ArrayList<String>) item.getItemMeta().getLore();
						gg.add(ChatColor.GREEN + "Item name: " + ChatColor.YELLOW + g);
						gg.add(ChatColor.GREEN + "max number: " + ChatColor.YELLOW + max);
						gg.add(ChatColor.GREEN + "min number: " + ChatColor.YELLOW + min);
						meta.setLore(gg);
						item.setItemMeta(meta);
						gg.clear();
					}
					else {
						ArrayList<String> gg = new ArrayList<String>();
						gg.add(ChatColor.GREEN + "Item name: " + ChatColor.YELLOW + g);
						gg.add(ChatColor.GREEN + "max number: " + ChatColor.YELLOW + max);
						gg.add(ChatColor.GREEN + "min number: " + ChatColor.YELLOW + min);
						meta.setLore(gg);
						item.setItemMeta(meta);
						gg.clear();
					}
					item.setAmount(1);
					s.add(item);
					
					
				
			
		}
		
		}
		
	}

