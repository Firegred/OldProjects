package me.Firegred.Treasures;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtill {
 
public static ItemStack createItem(Material material, String displayname, String lore) {
ItemStack item = new ItemStack(material);
ItemMeta meta = item.getItemMeta();
meta.setDisplayName(displayname);
ArrayList<String> Lore = new ArrayList<String>();
Lore.add(lore);
meta.setLore(Lore);
 
 
item.setItemMeta(meta);
return item;
}
 
public static ItemStack createItem(Material material, int amount, String displayname, String lore) {
ItemStack item = new ItemStack(material, amount);
ItemMeta meta = item.getItemMeta();
meta.setDisplayName(displayname);
ArrayList<String> Lore = new ArrayList<String>();
Lore.add(lore);
meta.setLore(Lore);
 
 
item.setItemMeta(meta);
return item;
}
 
public static ItemStack createItem(Material material, String displayname) {
ItemStack item = new ItemStack(material);
ItemMeta meta = item.getItemMeta();
meta.setDisplayName(displayname);
item.setItemMeta(meta);
return item;
}
 
}