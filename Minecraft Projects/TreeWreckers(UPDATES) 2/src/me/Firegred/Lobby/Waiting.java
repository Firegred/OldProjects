package me.Firegred.Lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import me.Firegred.Game.Equipment;
import me.Firegred.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

public class Waiting implements Listener{

	public static Main plugin;
	
	
	
	public Waiting(Main instance) {
		this.plugin = instance;
	}
	
	public static int treepercent = 20;
	public static HashMap<String, Countdown> games = new HashMap<String, Countdown>();
	
	public static boolean ifMax(String game) {
		int on = 0;
		int max = plugin.getConfig().getInt(Main.games + "." + game + ".Max");
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasMetadata("Game")) {
				if(p.getMetadata("Game").get(0).asString().equals(game)) {
					on++;
				}
			}
		}
		if(on == max) {
			return true;
		}
		else {
		return false;
		}
	}
	public static boolean ifEnough(String game) {
		int on = 0;
		int min = plugin.getConfig().getInt(Main.games + "." + game + ".Min");
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasMetadata("Game")) {
				if(p.getMetadata("Game").get(0).asString().equals(game)) {
					on++;
				}
			}
		}
		if(on >= min) {
			return true;
		}
		else {
		return false;
		}
	}
	public static void generateTrees(String name) {
		int x1=plugin.getConfig().getInt(Main.games + "." + name + ".Region.X1");
		int y1=plugin.getConfig().getInt(Main.games + "." + name + ".Region.Y1");
		int z1=plugin.getConfig().getInt(Main.games + "." + name + ".Region.Z1");
		
		int x2=plugin.getConfig().getInt(Main.games + "." + name + ".Region.X2");
		int y2=plugin.getConfig().getInt(Main.games + "." + name + ".Region.Y2");
		int z2=plugin.getConfig().getInt(Main.games + "." + name + ".Region.Z2");
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasMetadata("Game")) {
				if(p.getMetadata("Game").get(0).asString().equals(name)) {
					p.sendMessage(Main.trees + ChatColor.RED + "Map is being generated. Please be patient..");
				}
			}
		}
		treegenerate(name,x1,y1,z1,x2,y2,z2);
	}
	
	public static void countdown(final String game) {
 
		games.put(game, new Countdown(game));
		games.get(game).runTaskTimer(plugin, 20L, 20L);
		
		
//		for(int i = 1; i <= 30; i++) {
//    	    final int k = i;
//			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//			
//			@Override
//			public void run() {
//				if(ifEnough(game)) {
//				for(Player p : Bukkit.getOnlinePlayers()) {
//					if(p.hasMetadata("Game")) {
//						if(p.getMetadata("Game").get(0).asString().equals(game)) {
//							//if(p.getLevel() == (k-1)) {
//							p.setLevel(30-k);
//							if(k >= 20) {
//							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
//							}
//						//}
//						}
//					}
//				}
//				if(k==30) {
//					Waiting.generateTrees(game);
//					Waiting.startGame(game);
//					Waiting.setIngame(game);
//				}
//				
//				}
//				else {
//					for(Player p : Bukkit.getOnlinePlayers()) {
//						if(p.hasMetadata("Game")) {
//							if(p.getMetadata("Game").get(0).asString().equals(game)) {
//								p.setLevel(0);
//							}
//						}
//					}
//				}
//				
//			}
//		},20L*i);
//       }
	}

	private static void treegenerate(String name, int x1, int y1, int z1, int x2, int y2, int z2) {
//		int x1 = m.getConfig().getInt("Loot." + name + ".x1");
//		int y1 = m.getConfig().getInt("Loot." + name + ".y1");
//		int z1 = m.getConfig().getInt("Loot." + name + ".z1");
//		
//		int x2 = m.getConfig().getInt("Loot." + name + ".x2");
//		int y2 = m.getConfig().getInt("Loot." + name + ".y2");
//		int z2 = m.getConfig().getInt("Loot." + name + ".z2");
//		Random random = new Random();
		int x = 0;
		int y = 0;
		int z = 0;
		
		int px1 =0, px2=0, py1=0,py2=0,pz1=0,pz2 = 0;
		if(x1>=x2) {
			//x = random.nextInt(Math.abs(x1-x2)+1)+x2;
			px1=x1;
			px2=x2;
		}
		else if(x2>x1) {
			//x = random.nextInt(Math.abs(x2-x1)+1)+x1;
			px1=x2;
			px2=x1;
		}
		if(y1>=y2) {
			//y = random.nextInt(Math.abs(y1-y2)+1)+y2;
			py1=y1;
			py2=y2;
		}
		else if(y2>y1) {
			//y = random.nextInt(Math.abs(y2-y1)+1)+y1;
			py1=y2;
			py2=y1;
		}
		if(z1>=z2) {
			//z = random.nextInt(Math.abs(z1-z2)+1)+z2;
			pz1=z1;
			pz2=z2;
		}
		else if(z2>z1) {
			//z = random.nextInt(Math.abs(z2-z1)+1)+z1;
			pz1=z2;
			pz2=z1;
		}
		Random r = new Random();
		for(int xx = px2; xx <= px1; xx++) {
			//for(int yy = py2; yy <= py1; yy++) {
				for(int zz = pz2; zz <= pz1; zz++) {
					int ch = r.nextInt(100);
					if(ch < treepercent) {
						for(int yy = py2; yy <= py1; yy++) {
						Location ll = new Location(Main.w, xx, yy, zz);
						Location ll2 = new Location(Main.w, xx, yy+1, zz);
						if(Main.w.getBlockAt(ll).getType().equals(Material.GRASS) || Main.w.getBlockAt(ll).getType().equals(Material.DIRT)) {
							 if(Main.canReplace.contains(Main.w.getBlockAt(ll2).getType())) {
						     Main.w.generateTree(ll2, TreeType.BIRCH);
							 }
						}
						}
					}
				//}
			}
		}
	}
	

	   public static void setIngame(String name) {
			int x = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.X");
			int y = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Y");
			int z = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Z");
			Location l = new Location(Main.w, x,y,z);
			Sign s = (Sign) Main.w.getBlockAt(l).getState();
			s.setLine(1,ChatColor.RED + "[Ingame]");
			s.update();
		}

	
	public static void startGame(String name) {
		ArrayList<Location> l = new ArrayList<Location>();
		for(String key : plugin.getConfig().getConfigurationSection(Main.games + "." + name + ".Spawns").getKeys(false)){
			 int x = plugin.getConfig().getInt(Main.games + "." + name + ".Spawns." + key +".X");
			 int y = plugin.getConfig().getInt(Main.games + "." + name + ".Spawns." + key +".Y");
			 int z = plugin.getConfig().getInt(Main.games + "." + name + ".Spawns." + key +".Z");
			 World ww = Main.w;
			 Location k = new Location(ww, x,y,z);
			 l.add(k);
		}
		Random r = new Random();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasMetadata("Game")) {
				int ch = r.nextInt(l.size());
				if(p.getMetadata("Game").get(0).asString().equals(name)) {
					if(!Main.w.getBlockAt(l.get(ch)).getType().equals(Material.AIR)) {
						Main.w.getBlockAt(l.get(ch)).setType(Material.AIR);
						Main.w.getBlockAt(l.get(ch).getBlockX(), l.get(ch).getBlockY()+1, l.get(ch).getBlockZ()).setType(Material.AIR);
					}
					p.teleport(l.get(ch));
					l.remove(ch);
					p.removeMetadata("Lobby", plugin);
					p.setMetadata("Tree", new FixedMetadataValue(plugin, 0));
					p.setMetadata("Axe", new FixedMetadataValue(plugin, 0));
					p.setMetadata("Helm", new FixedMetadataValue(plugin, 0));
					p.setMetadata("Leggings", new FixedMetadataValue(plugin, 0));
					p.setMetadata("ChestPlate", new FixedMetadataValue(plugin, 0));
					p.setMetadata("Boots", new FixedMetadataValue(plugin, 0));
					p.setMetadata("Sheep", new FixedMetadataValue(plugin, ch));
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(Main.trees + ChatColor.GREEN + "The game has started. Have fun!");
					p.getInventory().clear();
					p.getInventory().addItem(Equipment.StarterAxe());
					p.getInventory().setItem(8, Equipment.PlayerTracker());
				}
			}
		}
	   games.get(name).cancel();
	   games.remove(name);
	}
	
	public static boolean ifMin(String game) {
		int on = 0;
		int min = plugin.getConfig().getInt(Main.games + "." + game + ".Min");
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasMetadata("Game")) {
				if(p.getMetadata("Game").get(0).asString().equals(game)) {
					on++;
				}
			}
		}
		if(on == min) {
			return true;
		}
		else {
		return false;
		}
	}
}
