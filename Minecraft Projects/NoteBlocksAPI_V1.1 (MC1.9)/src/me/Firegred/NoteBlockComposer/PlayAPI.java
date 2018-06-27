package me.Firegred.NoteBlockComposer;

import java.util.ArrayList;
import java.util.HashMap;

import me.Firegred.NoteBlock.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayAPI {

	private static int[] measure1 = {0,9,18,27,36};
	private static int[] measure2 = {1,10,19,28,37};
	private static int[] measure3 = {2,11,20,29,38};
	private static int[] measure4 = {3,12,21,30,39};
	private static int[] measure5 = {5,14,23,32,41};
	private static int[] measure6 = {6,15,24,33,42};
	private static int[] measure7 = {7,16,25,34,43};
	private static int[] measure8 = {8,17,26,35,44};
	public static int[][] chest = new int[8][];
	
	public static HashMap<Chest, ArrayList<Chest>> ChestPlayers = new HashMap<Chest, ArrayList<Chest>>();
	public static HashMap<Chest, BukkitRunnable> player = new HashMap<Chest, BukkitRunnable>();
	public static HashMap<Player, Chest> viewer = new HashMap<Player, Chest>();
	public static ArrayList<Location> playchest = new ArrayList<Location>();
	//public static HashMap<Chest, Integer> counter = new HashMap<Chest, Integer>();
	//public static HashMap<Chest, Integer> speedd = new HashMap<Chest, Integer>();
	//public static HashMap<Chest, Integer> chestposition = new HashMap<Chest, Integer>();
	public static void define() {
		chest[0] = measure1;
		chest[1] = measure2;
		chest[2] = measure3;
		chest[3] = measure4;
		chest[4] = measure5;
		chest[5] = measure6;
		chest[6] = measure7;
		chest[7] = measure8;
	}
	public static void play(int speed, Chest c) {
		//counter.put(c, 0);
		String sss = Main.instance.getConfig().getString("Global Volume");
		float sound = Float.parseFloat(sss);
		player.put(c, new ChestP(c, ChestPlayers.get(c), 0,sound));
		player.get(c).runTaskTimer(Main.instance, calculateSpeed(speed), calculateSpeed(speed));
		setStopBlock(c);
	}
	public static void sendList(CommandSender sender) {
		if(player.size() == 0) {
			sender.sendMessage(ChatColor.GOLD + "No Composer Chests are playing");
		}
		else {
			sender.sendMessage(ChatColor.GOLD + "Chests playing: ");
			for(Chest c : player.keySet()) {
				sender.sendMessage(ChatColor.WHITE + CompInv.getSongName(c.getInventory()) + ChatColor.WHITE + " - " + ChatColor.GREEN + "by " + ChatColor.WHITE + CompInv.getAuthor(c.getInventory()) + " " + ChatColor.YELLOW + "Location: " 
 + ChatColor.GOLD + "World: " + ChatColor.WHITE + c.getBlock().getWorld().getName() + " " + ChatColor.GOLD + "x: " + ChatColor.WHITE + c.getBlock().getLocation().getBlockX() + " " + ChatColor.GOLD + "y: " + ChatColor.WHITE + c.getBlock().getLocation().getBlockY() + " " +
 ChatColor.GOLD + "z: " + ChatColor.WHITE + c.getBlock().getLocation().getBlockZ());
			}
		}
	}
	
	public static void glitchstop(Chest c) {
		for(int y = 0; y <= 256; y++) {
			Location l = new Location(c.getWorld(), c.getBlock().getLocation().getBlockX(),y,c.getBlock().getLocation().getBlockZ());
			if(Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
				Chest t = (Chest)Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState();
				if(CompInv.isComposerChest(t.getInventory())) {
				if(CompInv.isSameName(c.getInventory(), t.getInventory())) {
					t.getInventory().setItem(CompInv.importantSlots[3], CompInv.play);
					for(int i = 0; i < t.getInventory().getSize(); i++) {
						if(t.getInventory().getItem(i) != null) {
						if(t.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar")) {
							t.getInventory().setItem(i, null);
						}
						}
					}
				}
				}
			}
		}
		
	}
	public static void addViewer(Player p, Chest c1) {
		Chest c = c1;
		//try {
		if(playchest.contains(c1.getLocation()) && !CompInv.isHost(c1.getInventory())) {
			for(int y = 0; y < 256; y++) {
				Location l = new Location(c1.getWorld(), c1.getBlock().getLocation().getBlockX(),y,c1.getBlock().getLocation().getBlockZ());
				if(Bukkit.getWorld(c1.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
					Chest t = (Chest) Bukkit.getWorld(c1.getWorld().getName()).getBlockAt(l).getState();
					if(CompInv.isComposerChest(t.getInventory())) {
						if(CompInv.isSameName(c1.getInventory(), t.getInventory())) {
							if(CompInv.isHost(t.getInventory())) {
								c = t;
								break;
							}
						}
					}
					
				}
			}
		}
		viewer.put(p, c);
		//p.openInventory(getChestPlayer(c1));
		
		
		//} catch (java.lang.NullPointerException e) {
			
		//}
		
	}
	public static Inventory getChestPlayer(Chest c1) {
		if(playchest.contains(c1.getLocation())) {
			for(int y = 0; y < 256; y++) {
				Location l = new Location(c1.getWorld(), c1.getBlock().getLocation().getBlockX(),y,c1.getBlock().getLocation().getBlockZ());
				if(Bukkit.getWorld(c1.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
					Chest t = (Chest) Bukkit.getWorld(c1.getWorld().getName()).getBlockAt(l).getState();
					if(CompInv.isComposerChest(t.getInventory())) {
						if(CompInv.isSameName(c1.getInventory(), t.getInventory())) {
							if(CompInv.hasPlayerBar(t.getInventory())) {
                                return t.getInventory();
							}
						}
					}
					
				}
			}
		}
		return null;
	}
	
	public static void stop(Chest c1) {
			Chest c = c1;
			try {
			if(playchest.contains(c1.getLocation()) && !CompInv.isHost(c1.getInventory())) {
				for(int y = 0; y < 256; y++) {
					Location l = new Location(c1.getWorld(), c1.getBlock().getLocation().getBlockX(),y,c1.getBlock().getLocation().getBlockZ());
					if(Bukkit.getWorld(c1.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
						Chest t = (Chest) Bukkit.getWorld(c1.getWorld().getName()).getBlockAt(l).getState();
						if(CompInv.isComposerChest(t.getInventory())) {
							if(CompInv.isSameName(c1.getInventory(), t.getInventory())) {
								if(CompInv.isHost(t.getInventory())) {
									c = t;
									break;
								}
							}
						}
						
					}
				}
			}
			setStartBlock(c);
			player.get(c).cancel();
			player.remove(c);
			for(Chest t : ChestPlayers.get(c)) {
				for(int i = 0; i < t.getInventory().getSize(); i++) {
					if(t.getInventory().getItem(i) != null) {
					if(t.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar")) {
						t.getInventory().setItem(i, null);
					}
					}
				}
			}
			for(Chest ct : ChestPlayers.get(c)) {
				if(playchest.contains(ct)) {
					playchest.remove(ct);
				}
			}
			ChestPlayers.remove(c);
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			PlayAPI.glitchstop(c);
		}
		
	}
	public static void setStopBlock(Chest c) {
		for(int i = 0; i < ChestPlayers.get(c).size(); i++) {
			ChestPlayers.get(c).get(i).getInventory().setItem(CompInv.importantSlots[3], CompInv.stop);
		}
		c.getInventory().setItem(CompInv.importantSlots[3], CompInv.stopHost);
	}
	public static void setStartBlock(Chest c) {
		for(int i = 0; i < ChestPlayers.get(c).size(); i++) {
			ChestPlayers.get(c).get(i).getInventory().setItem(CompInv.importantSlots[3], CompInv.play);
		}
		c.getInventory().setItem(CompInv.importantSlots[3], CompInv.play);
	}
	public static int calculateSpeed(int s) {
		Double ds = (double) s;
		Double d = (double) (20/(ds/60)/2);
		int speed = (int) (d+0.5);
		return speed;
	}
//	public static void setPlayBar() {
//		
//	}
//	public static void reset() {
//		e
//	}
	public static void preparePlay(Chest c) {
		ArrayList<Chest> chests = new ArrayList<Chest>();
		int by = c.getBlock().getLocation().getBlockY();
		for(int y = by; y <= 256; y++) {
			Location l = new Location(c.getWorld(), c.getBlock().getLocation().getBlockX(),y, c.getBlock().getLocation().getBlockZ());
		    if(Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
		    	Chest t = (Chest) Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState();
		    	if(CompInv.isComposerChest(t.getInventory())) {
		    		if(CompInv.isSameName(c.getInventory(), t.getInventory())) {
		    			chests.add(t);
		    			playchest.add(t.getLocation());
		    		}
		    		//check if inventory c and inventory t match
		    		//add the chest t to the hashmap
		    	}
		    }
		}
		for(int y = 0; y < by; y++) {
			Location l = new Location(c.getWorld(), c.getBlock().getLocation().getBlockX(),y, c.getBlock().getLocation().getBlockZ());
		    if(Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState() instanceof Chest) {
		    	Chest t = (Chest) Bukkit.getWorld(c.getWorld().getName()).getBlockAt(l).getState();
		    	if(CompInv.isComposerChest(t.getInventory())) {
		    		if(CompInv.isSameName(c.getInventory(), t.getInventory())) {
		    			chests.add(t);
		    			playchest.add(t.getLocation());
		    		}
		    		//check if inventory c and inventory t match
		    		//add the chest t to the hashmap
		    	}
		    }
		}
		ChestPlayers.put(c, chests);
		
	}
}
