package me.Firegred.NoteBlockComposer;

import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CompInv {

	public static int[] importantSlots = {4, 13,22,31,40,49};
	public static int[] playSlot = {45,46,47,48,50,51,52,53};
	
	private static ItemStack identifier = new ItemStack(Material.DIAMOND_BLOCK);
	private static ItemStack name = new ItemStack(160,1,(short)2);
	private static ItemStack page = new ItemStack(Material.LAPIS_BLOCK);
	
	public static ItemStack play = new ItemStack(Material.EMERALD_BLOCK);
	public static ItemStack stop = new ItemStack(Material.REDSTONE_BLOCK);
	public static ItemStack stopHost = new ItemStack(Material.REDSTONE_BLOCK);
	public static ItemStack playbar = new ItemStack(160,1,(short)7);
	public static ItemStack nopage = new ItemStack(160, 1, (short) 14);
	public static ItemStack follower = new ItemStack(Material.EMERALD_BLOCK);
	public static ItemStack glassfollower = new ItemStack(160, 1, (short)5);
	
	private static ItemStack upArrow = new ItemStack(Material.SKULL_ITEM,1,(short)3);
	private static ItemStack downArrow = new ItemStack(Material.SKULL_ITEM,1,(short)3);
	
	
	private static ItemMeta identifiermeta = identifier.getItemMeta();
	private static ItemMeta namemeta = name.getItemMeta();
	private static ItemMeta pagemeta = page.getItemMeta();
	private static ItemMeta playmeta = play.getItemMeta();
	private static ItemMeta playbarmeta = playbar.getItemMeta();
	
	private static ItemMeta stopHostmeta = stopHost.getItemMeta();
	private static ItemMeta stopmeta = stop.getItemMeta();
	private static ItemMeta followerMeta = follower.getItemMeta();
	private static ItemMeta glassfollowerMeta = glassfollower.getItemMeta();
	
	private static ItemMeta nopagemeta = nopage.getItemMeta();
	private static SkullMeta upArrowmeta = (SkullMeta)upArrow.getItemMeta();
	private static SkullMeta downArrowmeta = (SkullMeta) downArrow.getItemMeta();
	
	public static HashMap<Player, Chest> Composer = new HashMap<Player, Chest>();
	public static HashMap<Player, ItemStack> Copy = new HashMap<Player, ItemStack>();
	public static HashMap<Player, HashMap<Integer, ItemStack>> ChestCopy = new HashMap<Player, HashMap<Integer, ItemStack>>();	
	public static void definePlayStuff() {
		nopagemeta.setDisplayName(ChatColor.RED + "No page exists");
		playbarmeta.setDisplayName(ChatColor.GRAY + "Play bar");
		playmeta.setDisplayName(ChatColor.GREEN+ "Play Song");
		stopmeta.setDisplayName(ChatColor.RED + "Stop Song");
		stopHostmeta.setDisplayName(ChatColor.RED + "Stop Song (Host)");
		followerMeta.setDisplayName(ChatColor.GREEN + "Playing...");
		glassfollowerMeta.setDisplayName(ChatColor.GREEN + "Location bar");
		
		nopage.setItemMeta(nopagemeta); 
		playbar.setItemMeta(playbarmeta);
		play.setItemMeta(playmeta);
		stop.setItemMeta(stopmeta);
		follower.setItemMeta(followerMeta);
		glassfollower.setItemMeta(glassfollowerMeta);
		stopHost.setItemMeta(stopHostmeta);

		
	}
	public static void copyInventory(Player p, Inventory i) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		for(int x = 0; x < i.getSize(); x++) {
			if(isComposerSlot(x) && i.getItem(x) != null) {
				items.put(x, i.getItem(x));
			}
		}
		ChestCopy.put(p, items);
	}
	public static void pasteInventory(Player p, Inventory i) {
		HashMap<Integer, ItemStack> items = ChestCopy.get(p);
		for(int x = 0; x < i.getSize(); x++) {
			if(isComposerSlot(x)) {
				i.setItem(x,items.get(x));
			}
		}
	}
	public static void removeInventoryCopy(Player p) {
       ChestCopy.remove(p);
	}
	public static boolean hasChestCopier(Player p) {
		if(ChestCopy.containsKey(p)) {
			return true;
		}
		return false;
	}
	public static String getAuthor(Inventory i) {
		String[] args = i.getItem(importantSlots[1]).getItemMeta().getDisplayName().split(" ");
		String owner = args[1].substring(2);
		return owner;
	}
	
	public static void createStandardInventory(Inventory i,String songname, String playername, int pagen, int speed) {
	     identifiermeta.setDisplayName(ChatColor.GREEN + "NoteBlockSong: " + ChatColor.YELLOW + songname);
	     namemeta.setDisplayName(ChatColor.GREEN + "Owner: " + ChatColor.YELLOW + playername);
		 pagemeta.setDisplayName(ChatColor.GREEN + "Speed: " + ChatColor.WHITE + speed + " " + ChatColor.YELLOW + "Page: " + ChatColor.WHITE + pagen );
		 nopagemeta.setDisplayName(ChatColor.RED + "No page exists");
		 playmeta.setDisplayName(ChatColor.GREEN+ "Play Song");
		 playbarmeta.setDisplayName(ChatColor.GRAY + "Play bar");
		 
		 identifier.setItemMeta(identifiermeta);
		 name.setItemMeta(namemeta);
		 page.setItemMeta(pagemeta);
		 nopage.setItemMeta(nopagemeta); 
		 play.setItemMeta(playmeta);
		 playbar.setItemMeta(playbarmeta);
		 
		 i.setItem(importantSlots[0], identifier);
		 i.setItem(importantSlots[1], name);
		 i.setItem(importantSlots[2], nopage);
		 i.setItem(importantSlots[3], play);
		 i.setItem(importantSlots[4], nopage);
		 i.setItem(importantSlots[5], page); 
		 for(int c = 0; c < playSlot.length; c++) {
			 i.setItem(playSlot[c], playbar);
		 }
	}
	public static void moveInventory(Inventory host, Inventory target) {
		for(int x = 0; x < target.getSize(); x++) {
			if(isComposerSlot(x)) {
				host.setItem(x, target.getItem(x));
			}
		}
	}
	public static void deleteChest(Inventory i, Location l, Chest c) {
		World w = l.getWorld();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		if(c.getBlock().getRelative(BlockFace.UP).getState() instanceof Chest) {
			Chest t = (Chest) c.getBlock().getRelative(BlockFace.UP).getState();
			if(isComposerChest(t.getInventory())) {
				if(isSameName(i, t.getInventory())) {
				for(int yt = y+1; yt <= 256; yt++) {
					Location l2 = new Location(w,x,yt,z);
					if(Bukkit.getWorld(w.getName()).getBlockAt(l2).getState() instanceof Chest) {
						Chest t2 = (Chest) Bukkit.getWorld(w.getName()).getBlockAt(l2).getState();
						if(isComposerChest(t2.getInventory())) {
							if(isSameName(i, t2.getInventory())) {
								Location l3 = new Location(w,x,yt-1,z);
								Chest t3 = (Chest) Bukkit.getWorld(w.getName()).getBlockAt(l3).getState();
								moveInventory(t3.getInventory(), t2.getInventory());
								if(t2.getBlock().getRelative(BlockFace.UP).getState() instanceof Chest) {
									Chest t4 = (Chest) t2.getBlock().getRelative(BlockFace.UP).getState();
									if(!CompInv.isComposerChest(t4.getInventory())) {
										t2.getInventory().clear();
										if(PlayAPI.playchest.contains(t2)) {
											PlayAPI.playchest.remove(t2);
										}
										break;
									}
								}
								else {
									if(PlayAPI.playchest.contains(t2)) {
										PlayAPI.playchest.remove(t2);
									}
									t2.getInventory().clear();
									break;
								}
							}
					}
					
						
					}
				}
				}
				else {
					i.clear();
				}
			}
			else {
				i.clear();
			}
			
		}
		else {
		i.clear();
		}
	}
	public static boolean hasItem(Inventory i, int slot) {
		if(i.getItem(slot) != null) {
			if(i.getItem(slot).hasItemMeta()) {
				return true;
			}
		}
		return false;
	}
	public static boolean hasPlayerBar(Inventory inv) {
		for(int i = 0; i < playSlot.length; i++) {
			if(hasItem(inv, playSlot[i])) {
			if(inv.getItem(playSlot[i]).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Playing...")) {
	            return true;
			    }
			}
		}
		return false;
	}
    public static boolean isHost(Inventory i) {
		if(i.getItem(importantSlots[3]) != null) {
    	  if(hasItem(i, importantSlots[3])) {
			if(i.getItem(importantSlots[3]).getItemMeta().getDisplayName().equals(ChatColor.RED + "Stop Song (Host)")) {
			return true;
		}
    	  }
		}
		return false;
	}
	public static boolean isPlayerSlot(int slot) {
		if(slot == importantSlots[3]) {
			return true;
		}
		return false;
	}
	public static boolean isPlayButton(Inventory inv) {
		if(hasItem(inv, importantSlots[3])) {
		if(inv.getItem(importantSlots[3]).getItemMeta().getDisplayName().equals(ChatColor.GREEN+ "Play Song")) {
			return true;
		}
		}
		return false;
	}
	public static boolean isStopbutton(Inventory inv) {
		if(hasItem(inv, importantSlots[3])) {
		if(inv.getItem(importantSlots[3]).getItemMeta().getDisplayName().startsWith(ChatColor.RED + "Stop Song")) {
			return true;
		}
		}
		return false;
	}
	
	public static boolean isInvEmpty(Inventory inv) {
		for(ItemStack item : inv.getContents()) {
			if(item != null) {
				return false;
			}
		}
		return true;
	}
	public static boolean isComposer(Player p) {
		if(Composer.containsKey(p)) {
			return true;
		}
		return false;
	}
	public static boolean isComposerSlot(int i) {
		for(int x = 0; x < importantSlots.length; x++) {
			if(i == importantSlots[x]) {
				return false;
			}
		}
		for(int y = 0; y < playSlot.length; y++) {
			if(i == playSlot[y]) {
				return false;
			}
		}
		return true;
	}
	public static void UpdateSpeed(Chest c, int speed) {
		Inventory inv = c.getInventory();
		Block b = c.getBlock();
		for(int y = 0; y <= 256; y++) {
             Location l = new Location(b.getWorld(), b.getLocation().getBlockX(), y, b.getLocation().getBlockZ());
             if(Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
            	 Chest t = (Chest)Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState();
            	    if(isComposerChest(t.getInventory())) {
            			 if(isSameName(inv, t.getInventory())) {
            				 pagemeta.setDisplayName(ChatColor.GREEN + "Speed: " + ChatColor.WHITE + speed + " " + ChatColor.YELLOW + "Page: " + ChatColor.WHITE + getPageNumber(t.getInventory()));
            				 page.setItemMeta(pagemeta);
            				 t.getInventory().setItem(importantSlots[5], page);
            		}
            	 }
             }
		}
		
	}
	public static void defineItems() {
		 nopagemeta.setDisplayName(ChatColor.RED + "No page exists");	
		 nopage.setItemMeta(nopagemeta); 
	}
	public static int getBPM(Inventory i) {
		String name = i.getItem(importantSlots[5]).getItemMeta().getDisplayName();
		String[] args = name.split(" ");
		String number = args[1];
		int bpm = Integer.parseInt(number.substring(2));
		return bpm;
	}
		
	public static boolean isOwner(Player p, Inventory i) {
		if(hasItem(i, importantSlots[1])) {
		String[] args = i.getItem(importantSlots[1]).getItemMeta().getDisplayName().split(" ");
		String owner = args[1].substring(2);
		if(owner.equals(p.getName())) {
			return true;
		}
		}
		return false;
	}
		
	public static boolean isPageSlot(int i) {
		if(importantSlots[2] == i || importantSlots[4] == i) {
			return true;
		}
		return false;
	}
	public static boolean isUpPage(int i) {
		if(importantSlots[2] == i) {
			return true;
		}
		return false;
	}
	public static boolean isDownPage(int i) {
		if(importantSlots[4] == i) {
			return true;
		}
		return false;
	}
	public static boolean isSpeedSelector(int i) {
		if(importantSlots[5] == i) {
			return true;
		}
		return false;
	}
	public static void pasteItem(int slot, Inventory i,Player p) {
		ItemStack item = Copy.get(p);
		i.setItem(slot, item);
	}
	public static void nextPageUP(Player p,Chest c) {
		Inventory i = c.getInventory();
		if(c.getBlock().getRelative(BlockFace.UP).getState() instanceof Chest) {
		Chest t = (Chest)c.getBlock().getRelative(BlockFace.UP).getState();
		Inventory tINV = t.getInventory();
		if(i.getItem(importantSlots[2]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Create page ")) {
			String[] args = i.getItem(importantSlots[2]).getItemMeta().getDisplayName().split(" ");
			String page = args[2].substring(2);
			createStandardInventory(tINV, getSongName(i), p.getName(), Integer.valueOf(page), getBPM(i));
			updateInventory(t, t.getInventory());
			p.openInventory(tINV);
		}
		else if(i.getItem(importantSlots[2]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Go to page ")) {
			//String[] args = i.getItem(importantSlots[2]).getItemMeta().getDisplayName().split(" ");
			//String page = args[2].substring(2);
			//createStandardInventory(i, getSongName(i), p.getName(), Integer.valueOf(page));
			updateInventory(t, t.getInventory());
			p.openInventory(tINV);
		}}
	}
	public static void nextPageDOWN(Player p, Chest c) {
		Inventory i = c.getInventory();
		if(c.getBlock().getRelative(BlockFace.DOWN).getState() instanceof Chest) {
		Chest t = (Chest)c.getBlock().getRelative(BlockFace.DOWN).getState();
		Inventory tINV = t.getInventory();
		if(i.getItem(importantSlots[4]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Create page ")) {
			String[] args = i.getItem(importantSlots[4]).getItemMeta().getDisplayName().split(" ");
			String page = args[2].substring(2);
			createStandardInventory(tINV, getSongName(i), p.getName(), Integer.valueOf(page), getBPM(i));
			updateInventory(t, t.getInventory());
			p.openInventory(tINV);
		}
		else if(i.getItem(importantSlots[4]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Go to page ")) {
			//String[] args = i.getItem(importantSlots[2]).getItemMeta().getDisplayName().split(" ");
			//String page = args[2].substring(2);
			//createStandardInventory(i, getSongName(i), p.getName(), Integer.valueOf(page));
			updateInventory(t, t.getInventory());
			p.openInventory(tINV);
		}
		}
	}
	public static String getSongName(Inventory i) {
		String[] args = i.getItem(importantSlots[0]).getItemMeta().getDisplayName().split(" ");
		String song = args[1].substring(2);
		return song;
	}

	public static int getPageNumber(Inventory i) {
		String name = i.getItem(importantSlots[5]).getItemMeta().getDisplayName();
		String[] args = name.split(" ");
		String number = args[3];
		int page = Integer.parseInt(number.substring(2));
		return page;
	}
	public static boolean isSameName(Inventory host, Inventory target) {
		    String name1 = host.getItem(importantSlots[0]).getItemMeta().getDisplayName();
	 		String name2 = target.getItem(importantSlots[0]).getItemMeta().getDisplayName();
			String[] args1 = name1.split(" ");
			String[] args2 = name2.split(" ");
			
			String hostname = args1[1].substring(2);
			String targetname = args2[1].substring(2);
			if(hostname.equals(targetname)) {
				return true;
			}
			else {
				return false;
			}
			 
			
		
	}
	
	public static boolean canEdit(Player p) {
		if(p.hasPermission("NoteBlock.composer.playother")) {
			return true;
		}
		return false;
	}
	public static boolean canPlay(Player p) {
		if(p.hasPermission("NoteBlock.composer.editother")) {
			return true;
		}
		return false;
	}
	public static boolean canDelete(Player p) {
		if(p.hasPermission("NoteBlock.composer.deleteother")) {
			return true;
		}
		return false;
	}
	
	public static boolean isComposerChest(Inventory i) {
		if(hasItem(i, importantSlots[0])) {
 		String name = i.getItem(importantSlots[0]).getItemMeta().getDisplayName();
		//String[] args = name.split(" ");
		//String arg1 = args[0];
		if(name.startsWith(ChatColor.GREEN + "NoteBlockSong: ")) {
			return true;
		}
		  }
		  else {
		  return false;
		  }
		return false;
	}
	public static void removeLocationbars(Inventory i) {
		for(int x = 0; x < i.getSize(); x++) {
			if(i.getItem(x) != null) {
			if(i.getItem(x).hasItemMeta()) {
			   if(i.getItem(x).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar"))	{
				   i.setItem(x, null);
			   }
			}
			}
		}
	}
	public static void syncLocationBar(Inventory i) {
		for(int x = 0; x < playSlot.length; x++) {
			for(int y = 0; y < PlayAPI.chest[x].length; y++) {
				if(!i.getItem(playSlot[x]).getItemMeta().equals(ChatColor.GREEN + "Playing...")) {
					if(i.getItem(PlayAPI.chest[x][y]) != null) {
						if(i.getItem(PlayAPI.chest[x][y]).hasItemMeta()) {
							if(i.getItem(PlayAPI.chest[x][y]).getItemMeta().equals(ChatColor.GREEN + "Location bar")) {
							         i.setItem(PlayAPI.chest[x][y], null);
							}
						}
 					}
				}
			}
		}
	}
	public static void updateInventory(Chest c,Inventory i) {
		 for(int e = 0; e < playSlot.length; e++) {
			 i.setItem(playSlot[e], playbar);
		 }
		 if(isPlayButton(i)) {
			 removeLocationbars(i);
		 }
//		 if(isStopbutton(i)) {
//			 syncLocationBar(i);
//		 }
		if(c.getBlock().getRelative(BlockFace.UP).getState() instanceof Chest) {
		    Chest t = (Chest)c.getBlock().getRelative(BlockFace.UP).getState();
		    if(isInvEmpty(t.getInventory())) {
		    	if(t.getInventory().getSize() > 27) {
		    		if(!c.getInventory().getItem(importantSlots[2]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Create page ")) {
		    		upArrowmeta.setOwner("MHF_ArrowUp");
		    		upArrowmeta.setDisplayName(ChatColor.GREEN + "Create page " + ChatColor.YELLOW + String.valueOf((getPageNumber(i)+1)));
		    		upArrow.setItemMeta(upArrowmeta);
		    	    i.setItem(importantSlots[2], upArrow);
		    		}
		    	}
		    		else {
		    			i.setItem(importantSlots[2], nopage);
		    		}
		    	}
		    else if(isComposerChest(t.getInventory())) {
		    	if(isSameName(c.getInventory(), t.getInventory())) {
		    		if(!c.getInventory().getItem(importantSlots[2]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Go to page ")) {
		    		upArrowmeta.setOwner("MHF_ArrowUp");
		    		upArrowmeta.setDisplayName(ChatColor.GREEN + "Go to page " + ChatColor.YELLOW + getPageNumber(t.getInventory()));
	    			upArrow.setItemMeta(upArrowmeta);
	    			i.setItem(importantSlots[2], upArrow);
		    		}
	    		}
		    	else {
		    		i.setItem(importantSlots[2], nopage);
		    	}
		    }
		    else {
		    	i.setItem(importantSlots[2], nopage);
		    }
		    
		}
		else {
			i.setItem(importantSlots[2], nopage);	
		}
		if(c.getBlock().getRelative(BlockFace.DOWN).getState() instanceof Chest) {
		    Chest t = (Chest)c.getBlock().getRelative(BlockFace.DOWN).getState();
		    if(isInvEmpty(t.getInventory())) {
		    	if(getPageNumber(i) > 1) { 
		    	if(t.getInventory().getSize() > 27) {
		    		if(!c.getInventory().getItem(importantSlots[4]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Create page ")) {
		    		downArrowmeta.setOwner("MHF_ArrowDown");
		    		downArrowmeta.setDisplayName(ChatColor.GREEN + "Create page " + ChatColor.YELLOW + String.valueOf((getPageNumber(i)-1)));
		    		
		    		downArrow.setItemMeta(downArrowmeta);
		    	    i.setItem(importantSlots[4], downArrow);
		    		}
		    	}
		    	
		    		else {
		    			i.setItem(importantSlots[4], nopage);
		    		}
		    	}
		    	else {
		    		i.setItem(importantSlots[4], nopage);
		    	}
		    }
		    else if(isComposerChest(t.getInventory())) {
	    		if(isSameName(c.getInventory(), t.getInventory())) {
	    			if(!c.getInventory().getItem(importantSlots[4]).getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "Go to page ")) {
	    			downArrowmeta.setOwner("MHF_ArrowDown");
	    			downArrowmeta.setDisplayName(ChatColor.GREEN + "Go to page " + ChatColor.YELLOW + getPageNumber(t.getInventory()));
	    			
	    			downArrow.setItemMeta(downArrowmeta);
	    			i.setItem(importantSlots[4], downArrow);
	    			}
	    		}
		    }
		    else {
		    	i.setItem(importantSlots[4], nopage);
		    }
		}
		else {
			i.setItem(importantSlots[4], nopage);
		}
	}
	
	public static boolean isImportantSlot(int number) {
		for(int i = 0; i < importantSlots.length; i++) {
			if(importantSlots[i] == number) {
				return true;
			}

		}
		return false;
	}
	public static boolean isPlaySlot(int number) {
		for(int i = 0; i < playSlot.length; i++) {
			if(playSlot[i] == number) {
				return true;
			}

		}
		return false;
	}
}
