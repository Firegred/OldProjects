package me.Firegred.NoteBlockComposer;

import java.util.HashMap;

import me.Firegred.NoteBlock.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class SpeedSelection {

    public static ItemStack speed1 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed2 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed3 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed4 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed5 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed6 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed7 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed8 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed9 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed10 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed11 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed12 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed13 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed14 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed15 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed16 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed17 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed18 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed19 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed20 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed21 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed22 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed23 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed24 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed25 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed26 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed27 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed28 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed29 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed30 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed31 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed32 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed33 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed34 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed35 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed36 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed37 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed38 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed39 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed40 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speed41 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK);
    public static ItemStack[] items = {speed1,speed2,speed3,speed4,speed5,speed6,speed7,speed8,speed9,speed10,speed11,speed12,
    	speed13,speed14,speed15,speed16,speed17,speed18,speed19,speed20,speed21,speed22,speed23,speed24,speed25,speed26,speed27,speed28
    	,speed29,speed30,speed31,speed32,speed33,speed34,speed35,speed36,speed37,speed38,speed39,speed40,speed41};
    
    
    public static ItemMeta speed1meta = speed1.getItemMeta();
    public static ItemMeta speed2meta = speed2.getItemMeta();
    public static ItemMeta speed3meta = speed3.getItemMeta();
    public static ItemMeta speed4meta = speed4.getItemMeta();
    public static ItemMeta speed5meta = speed5.getItemMeta();
    public static ItemMeta speed6meta = speed6.getItemMeta();
    public static ItemMeta speed7meta = speed7.getItemMeta();
    public static ItemMeta speed8meta = speed8.getItemMeta();
    public static ItemMeta speed9meta = speed9.getItemMeta();
    public static ItemMeta speed10meta = speed10.getItemMeta();
    public static ItemMeta speed11meta = speed11.getItemMeta();
    public static ItemMeta speed12meta = speed12.getItemMeta();
    public static ItemMeta speed13meta = speed13.getItemMeta();
    public static ItemMeta speed14meta = speed14.getItemMeta();
    public static ItemMeta speed15meta = speed15.getItemMeta();
    public static ItemMeta speed16meta = speed16.getItemMeta();
    public static ItemMeta speed17meta = speed17.getItemMeta();
    public static ItemMeta speed18meta = speed18.getItemMeta();
    public static ItemMeta speed19meta = speed19.getItemMeta();
    public static ItemMeta speed20meta = speed20.getItemMeta();
    public static ItemMeta speed21meta = speed21.getItemMeta();
    public static ItemMeta speed22meta = speed22.getItemMeta();
    public static ItemMeta speed23meta = speed23.getItemMeta();
    public static ItemMeta speed24meta = speed24.getItemMeta();
    public static ItemMeta speed25meta = speed25.getItemMeta();
    public static ItemMeta speed26meta = speed26.getItemMeta();
    public static ItemMeta speed27meta = speed27.getItemMeta();
    public static ItemMeta speed28meta = speed28.getItemMeta();
    public static ItemMeta speed29meta = speed29.getItemMeta();
    public static ItemMeta speed30meta = speed30.getItemMeta();
    public static ItemMeta speed31meta = speed31.getItemMeta();
    public static ItemMeta speed32meta = speed32.getItemMeta();
    public static ItemMeta speed33meta = speed33.getItemMeta();
    public static ItemMeta speed34meta = speed34.getItemMeta();
    public static ItemMeta speed35meta = speed35.getItemMeta();
    public static ItemMeta speed36meta = speed36.getItemMeta();
    public static ItemMeta speed37meta = speed37.getItemMeta();
    public static ItemMeta speed38meta = speed38.getItemMeta();
    public static ItemMeta speed39meta = speed39.getItemMeta();
    public static ItemMeta speed40meta = speed40.getItemMeta();
    public static ItemMeta speed41meta = speed41.getItemMeta();
    public static ItemMeta exitmeta = exit.getItemMeta();
    public static ItemMeta[] meta = {speed1meta,speed2meta,speed3meta,speed4meta,speed5meta,speed6meta,speed7meta,speed8meta,speed9meta,speed10meta,speed11meta,speed12meta,
    	speed13meta,speed14meta,speed15meta,speed16meta,speed17meta,speed18meta,speed19meta,speed20meta,speed21meta,speed22meta,speed23meta,speed24meta,speed25meta,speed26meta,speed27meta,speed28meta
    	,speed29meta,speed30meta,speed31meta,speed32meta,speed33meta,speed34meta,speed35meta,speed36meta,speed37meta,speed38meta,speed39meta,speed40meta,speed41meta};
    
    public static Inventory speed = Bukkit.createInventory(null, 45, ChatColor.GOLD + "Select a speed");
    
    public static int[] uselessslots = {42,43};
    public static int exitslot = 44;
    
    public static ItemStack speedextra1 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemStack speedextra2 = new ItemStack(Material.NOTE_BLOCK);
    public static ItemMeta speedextra1Meta = speedextra1.getItemMeta();
    public static ItemMeta speedextra2Meta = speedextra2.getItemMeta();
    
    public static HashMap<Player, Chest> SpeedSelector = new HashMap<Player, Chest>();
    
    public static void define() {
    	for(int x = 0; x < items.length; x++) {
    	    meta[x].setDisplayName(ChatColor.GREEN + "Speed: " + ChatColor.YELLOW + (40+(x*5)) +  " " + ChatColor.YELLOW + "bpm");
    	    items[x].setItemMeta(meta[x]);
    	    speed.setItem(x, items[x]);
    	}
    	speedextra1Meta.setDisplayName(ChatColor.GREEN + "Speed: " + ChatColor.YELLOW + 300 +  " " + ChatColor.YELLOW + "bpm");
    	speedextra1.setItemMeta(speedextra1Meta);
    	speedextra2Meta.setDisplayName(ChatColor.GREEN + "Speed: " + ChatColor.YELLOW + 360 +  " " + ChatColor.YELLOW + "bpm");
    	speedextra2.setItemMeta(speedextra2Meta);
    	speed.addItem(speedextra1);
    	//speed.addItem(speedextra2);
    	exitmeta.setDisplayName(ChatColor.RED + "Back to Composer");
    	exit.setItemMeta(exitmeta);
    	speed.setItem(exitslot, exit);
    	
    	
    	
    	
    	
    	
    }
    public static void OpenSpeedInventory(Player p, Chest c) {
    	p.openInventory(speed);
    	SpeedSelector.put(p, c);
    	//p.setMetadata("Speed", new FixedMetadataValue(Main.instance, c));
    	
    }  
    public static void Exit(Player p) {
    		Chest c = SpeedSelector.get(p);
    		p.openInventory(c.getInventory());
        	SpeedSelector.remove(p);
    }
    public static boolean isUselessSlot(int slot) {
    	for(int i = 0; i < uselessslots.length; i++) {
    		if(slot == uselessslots[i]) {
    			return true;
    		}
    	}
    	return false;
    }
    public static void selectSpeed(Player p, Inventory i, int slot) {
    	String[] args = i.getItem(slot).getItemMeta().getDisplayName().split(" ");
    	int number = Integer.parseInt(args[1].substring(2));
    	Chest c = SpeedSelector.get(p);
    	CompInv.UpdateSpeed(c, number);
    	p.openInventory(c.getInventory());
    	SpeedSelector.remove(p);
    	
    	
    }
}
