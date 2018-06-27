package me.Firegred.Snowball;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Items {

	public static ItemStack cursedball() {
		ItemStack ball = new ItemStack(Material.SNOW_BALL, 1);
		ItemMeta meta = ball.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Cursed snowball");
		ArrayList<String> lore = new ArrayList<String>();
		lore.clear();
		lore.add(ChatColor.GOLD + "Throw this at your best friends :)");
		meta.setLore(lore);
		ball.setItemMeta(meta);
		return ball;
	}
	public static ItemStack cure() {
		ItemStack ball = new ItemStack(Material.EGG, 1);
		ItemMeta meta = ball.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "The Cure");
		ArrayList<String> lore = new ArrayList<String>();
		lore.clear();
		lore.add(ChatColor.YELLOW + "Throw this at someone who is cursed!");
		meta.setLore(lore);
		ball.setItemMeta(meta);
		return ball;
	}
	public static ItemStack cursedWand() {
		ItemStack ball = new ItemStack(Material.STICK, 1);
		ItemMeta meta = ball.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Cursed Wand");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 9, false);
		ArrayList<String> lore = new ArrayList<String>();
		lore.clear();
		lore.add(ChatColor.GOLD + "Whack this at your best friends :)");
		meta.setLore(lore);
		ball.setItemMeta(meta);
		return ball;
	}
	public static ItemStack cureWand() {
		ItemStack ball = new ItemStack(Material.STICK, 1);
		ItemMeta meta = ball.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Cure Wand");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 9, false);
		ArrayList<String> lore = new ArrayList<String>();
		lore.clear();
		lore.add(ChatColor.GOLD + "Use this on someone who is cursed!");
		meta.setLore(lore);
		ball.setItemMeta(meta);
		return ball;
	}
	
}
