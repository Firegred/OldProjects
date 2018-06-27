package me.Firegred.Easter;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Eggs {

	
	public static ItemStack easter() {
		ItemStack item = new ItemStack(Material.EGG, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Egg");
		lore.add(ChatColor.GOLD + "Right-Click to increase your egg-find count");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack diamond() {
		ItemStack item = new ItemStack(Material.DIAMOND, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Diamond");
		lore.add(ChatColor.GOLD + "Happy Easter!");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack key() {
		ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.GREEN + "Supply Crate Key!");
		lore.add(ChatColor.GOLD + "Go to " + ChatColor.YELLOW + "/warp crates " + ChatColor.GOLD + "to use this!");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 8);
		return item;
	}
	public static ItemStack carrot() {
		ItemStack item = new ItemStack(Material.CARROT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Carrot");
		lore.add(ChatColor.GOLD + "Happy Easter! :3");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack beet() {
		ItemStack item = new ItemStack(Material.BEETROOT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Beetroot");
		lore.add(ChatColor.GOLD + "Happy Easter! :D");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack rabbit() {
		ItemStack item = new ItemStack(Material.COOKED_RABBIT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Bunny's Children");
		lore.add(ChatColor.RED + "Happy Easter! >:3");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack foot() {
		ItemStack item = new ItemStack(Material.RABBIT_FOOT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Bunny's Foot");
		lore.add(ChatColor.GOLD + "The Easter Bunny called. It wants its foot back :(");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack goldcarrot() {
		ItemStack item = new ItemStack(Material.GOLDEN_CARROT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.GOLD + "GOLD " + ChatColor.LIGHT_PURPLE + " Easter Carrot");
		lore.add(ChatColor.GOLD + "Happy Easter! $$$");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack grass() {
		ItemStack item = new ItemStack(Material.GRASS, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.GREEN + "Easter Rabbit's Grass");
		lore.add(ChatColor.LIGHT_PURPLE + "Happy Easter! <3");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack gold() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Gold Ingot");
		lore.add(ChatColor.GOLD + "Happy Easter!");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack iron() {
		ItemStack item = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Iron Ingot");
		lore.add(ChatColor.GOLD + "Happy Easter! ^_^");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack chest(String Player, int number) {
		String p = Player;
		int n = number;
		ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.RED + p + ChatColor.RED + "'s" + ChatColor.LIGHT_PURPLE + " Easter Rewards:" + ChatColor.YELLOW + n + " eggs");
		lore.add(ChatColor.GOLD + "Right-Click to claim reward!");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack specialchest(String Player, int number) {
		String p = Player;
		int n = number;
		ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.RED + p + ChatColor.RED + "'s" + ChatColor.LIGHT_PURPLE + " Special Easter Rewards:" + ChatColor.YELLOW + n + " eggs");
		lore.add(ChatColor.GOLD + "Right-Click to claim reward!");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
	public static ItemStack hat(String s) {
		ItemStack item = new ItemStack(Material.WOOL, 1, (short) 6);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		itemMeta.setDisplayName(ChatColor.RED + s + ChatColor.RED + "'s" + ChatColor.LIGHT_PURPLE + " Easter Hat");
		lore.add(ChatColor.GOLD + "Happy Easter!");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
}
