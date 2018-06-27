package me.Firegred.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Firegred.main.PlayerConfig;

public class Inv {

	//event.getRawSlot() < event.getView().getTopInventory().getSize()
	public static Inventory createGUI(Player p) {
		Inventory i = Bukkit.createInventory(null, 9, ChatColor.RED +"Cell Options");
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		int length = f.getInt("Size.length");
		int width = f.getInt("Size.width");
		int height = f.getInt("Size.height");
		
		int lengthcost = f.getInt("Cost.Lupgrade");
		int widthcost = f.getInt("Cost.Wupgrade");
		int heightcost = f.getInt("Cost.Hupgrade");
		
		ItemStack i1 = Specifications(p, length, width, height);
		ItemStack i2 = Length(p,lengthcost);
		ItemStack i3 = Width(p,widthcost);
		ItemStack i4 = Height(p,heightcost);
		
		i.addItem(i1);
		i.addItem(i2);
		i.addItem(i3);
		i.addItem(i4);
		return i;
	}
	
	private static ItemStack Specifications(Player p, int length, int width, int height) {
		ItemStack wool = new ItemStack(Material.WOOL, 1);
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Cell Size: " + ChatColor.WHITE + length + "x" + width + "x" + height);
		wool.setItemMeta(meta);
		return wool;
	}
	private static ItemStack Width(Player p, int cost) {
		ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Upgrade Width: " + ChatColor.YELLOW + "$" + ChatColor.YELLOW + cost);
		wool.setItemMeta(meta);
		return wool;
	}
	private static ItemStack Length(Player p, int cost) {
		ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Upgrade Length: " + ChatColor.YELLOW + "$" + ChatColor.YELLOW + cost);
		wool.setItemMeta(meta);
		return wool;
	}
	private static ItemStack Height(Player p, int cost) {
		ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 11);
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Upgrade Height: " + ChatColor.YELLOW + "$" + ChatColor.YELLOW + cost);
		wool.setItemMeta(meta);
		return wool;
	}
	
}
