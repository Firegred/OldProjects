package me.Firegred.NoteBlockComposer;

import java.util.ArrayList;
import java.util.List;

import me.Firegred.NoteBlock.SoundID;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ComposerGUI {

	private static String[] sharp = {"█ ","█"," █ "," █ ","█ "," █ ","█"," █ "," █ ","█ "," █"};
	//private static String[] natural = {" ▀"," ▀"," ▀","▀"," ▀" ," ▀","▀"," ▀"," ▀"," ▀","▀"," ▀"," ▀","▀"};                          
	private static String[] natural = {"  ▀"," ▀","▀","▀","▀" ,"▀","▀"," ▀"," ▀"," ▀","▀","▀","▀","▀"};    
//	public static void setItemSlot(Sound s, ItemStack item, int slot, Inventory i) {
//		i.setItem(slot, item);
//	}
	public static boolean hasEmptySlot(Inventory i, int slot) {
		if(i.getItem(slot) != null) {
			return false;
		}
		return true;
	}
	
	public static void updatePitch(Inventory i, int slot, float pitch) {
		ItemStack item = i.getItem(slot).clone();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "ID: " + ChatColor.WHITE + getID(item) + " " + ChatColor.YELLOW + "Pitch: " + ChatColor.WHITE + pitch);
		List<String> lore = meta.getLore();
		StringBuilder sb = new StringBuilder();
		 for(int x = 0; x < sharp.length; x++) {
		    	if(SoundID.sharps[x] == pitch) {
		    		sb.append(ChatColor.GREEN + sharp[x]);
		    	}
		    	else {
		    		sb.append(ChatColor.DARK_GRAY + sharp[x]);
		    	}
		    }
		    lore.set(1,sb.toString());
		    sb.setLength(0);
		    for(int x = 0; x < natural.length; x++) {
		    	if(SoundID.naturals[x] == pitch) {
		    		sb.append(ChatColor.GREEN + natural[x]);
		    	}
		    	else {
		    		sb.append(ChatColor.WHITE + natural[x]);
		    	}	
		    }
		    lore.set(2,sb.toString());
		    sb.setLength(0);
		    meta.setLore(lore);
		    item.setItemMeta(meta);
		    item.setData(i.getItem(slot).getData());
		    i.setItem(slot, item);
		}
	
	public static void updateItem(Sound s, ItemStack k, Inventory i, int slot, int ID) {
	    ItemStack sound = k.clone();
		ItemMeta soundmeta = sound.getItemMeta();
		ItemStack item = i.getItem(slot);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		lore.set(0, ChatColor.GOLD + "Sound: " + ChatColor.BLUE + s.toString());
		soundmeta.setDisplayName(ChatColor.GREEN + "ID: " + ChatColor.WHITE + ID + " " + ChatColor.YELLOW + "Pitch: " + ChatColor.WHITE + getPitch(item));
		soundmeta.setLore(lore);
		sound.setItemMeta(soundmeta);
		//item.setItemMeta(meta);
		//item.setType(sound.getType());
		//item.setData(sound.getData());
		i.setItem(slot, sound);
		
	}
	public static int getID(ItemStack item) {
		String[] args = item.getItemMeta().getDisplayName().split(" ");
		int id = Integer.parseInt(args[1].substring(2));
		return id;
	}
	public static float getPitch(ItemStack item) {
		String[] args = item.getItemMeta().getDisplayName().split(" ");
		float pitch = Float.parseFloat(args[3].substring(2));
		return pitch;
	}
	
	public static void defineItem(Sound s, ItemStack sound, Inventory i, int slot, int ID, float pitch) {
		ItemStack item = sound.clone();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "ID: " + ChatColor.WHITE + ID + " " +ChatColor.YELLOW + "Pitch: " + ChatColor.WHITE + pitch);
		ArrayList<String> lore = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		lore.add(ChatColor.GOLD + "Sound: " + ChatColor.BLUE + s.toString());
	    for(int x = 0; x < sharp.length; x++) {
	    	if(SoundID.sharps[x] == pitch) {
	    		sb.append(ChatColor.GREEN + sharp[x]);
	    	}
	    	else {
	    		sb.append(ChatColor.DARK_GRAY + sharp[x]);
	    	}
	    }
	    lore.add(sb.toString());
	    sb.setLength(0);
	    for(int x = 0; x < natural.length; x++) {
	    	if(SoundID.naturals[x] == pitch) {
	    		sb.append(ChatColor.GREEN + natural[x]);
	    	}
	    	else {
	    		sb.append(ChatColor.WHITE + natural[x]);
	    	}	
	    }
	    lore.add(sb.toString());
	    //lore.add(ChatColor.YELLOW + SoundID.NotessharpString[0]);
	    sb.setLength(0);
	    meta.setLore(lore);
	    item.setItemMeta(meta);
	    i.setItem(slot, item);
	}
}
