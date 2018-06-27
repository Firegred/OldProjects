package me.Firegred.Treasures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DropChest extends BukkitRunnable{
  
	public static HashMap<String, Boolean> enable = new HashMap<String, Boolean>();
	private static HashMap<String, Integer> timer = new HashMap<String, Integer>();
	private static HashMap<String, Integer> clock = new HashMap<String, Integer>();
	//private static HashMap<String, ArrayList<ItemStack>> items = new HashMap<String, ArrayList<ItemStack>>();
	private static Main plugin;
	public DropChest(Main plugin) {
		this.plugin = plugin;
	}
	
	public static void enableChests(Main m, String g) {
		if(m.getConfig().getBoolean("Loot." + g + ".enabled") == true) {
			int time = m.getConfig().getInt("Loot." + g + ".time");
			setRegionTime(g, time);
			enable.put(g, true);
		}
		
	}
	
	public static void disableChests(Main m, String g) {
		if(m.getConfig().getBoolean("Loot." + g + ".enabled") == false) {
			removeRegionTime(g);
			enable.put(g, false);
		}
	}
	
	public static void bootUpChests(Main m) {
		for(String g : m.getConfig().getConfigurationSection("Loot").getKeys(false)) {	
			if(m.getConfig().getBoolean("Loot." + g + ".enabled") == true) {
				int time = m.getConfig().getInt("Loot." + g + ".time");
				enableChests(m, g);
				
			}
			}
			
		}
	
	
	private static void dropItem(final String name, final Main m) {
		//if(!items.isEmpty()) {
			
		Bukkit.getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
			
			@Override
			public void run() {
				setChestLocation(name, m);
				
			}
		}, 3L);
		
		
			
	//	}
	}
	
	private static void setChestLocation(String name, Main m) {
		int x1 = m.getConfig().getInt("Loot." + name + ".x1");
		int y1 = m.getConfig().getInt("Loot." + name + ".y1");
		int z1 = m.getConfig().getInt("Loot." + name + ".z1");
		
		int x2 = m.getConfig().getInt("Loot." + name + ".x2");
		int y2 = m.getConfig().getInt("Loot." + name + ".y2");
		int z2 = m.getConfig().getInt("Loot." + name + ".z2");
		Random random = new Random();
		int x = 0;
		int y = 0;
		int z = 0;
		
		int px1 =0, px2=0, py1=0,py2=0,pz1=0,pz2 = 0;
		if(x1>=x2) {
			x = random.nextInt(Math.abs(x1-x2)+1)+x2;
			px1=x1;
			px2=x2;
		}
		else if(x2>x1) {
			x = random.nextInt(Math.abs(x2-x1)+1)+x1;
			px1=x2;
			px2=x1;
		}
		if(y1>=y2) {
			y = random.nextInt(Math.abs(y1-y2)+1)+y2;
			py1=y1;
			py2=y2;
		}
		else if(y2>y1) {
			y = random.nextInt(Math.abs(y2-y1)+1)+y1;
			py1=y2;
			py2=y1;
		}
		if(z1>=z2) {
			z = random.nextInt(Math.abs(z1-z2)+1)+z2;
			pz1=z1;
			pz2=z2;
		}
		else if(z2>z1) {
			z = random.nextInt(Math.abs(z2-z1)+1)+z1;
			pz1=z2;
			pz2=z1;
		}
		String g = m.getConfig().getString("Loot." + name + ".world");
		if(LootDefine.isOnlyAir(name, m)) {
			ArrayList<Location> l = new ArrayList<Location>();
			for(int xx = px2; xx <= px1; xx++) {
				for(int yy = py2; yy <= py1; yy++) {
					for(int zz = pz2; zz <= pz1; zz++) {
						Location ll = new Location(Bukkit.getWorld(g), xx, yy, zz);
						if(Bukkit.getWorld(g).getBlockAt(ll).isEmpty()) {
							l.add(ll);
						}
					}
				}
			}
			if(!l.isEmpty()) {
			int pic = random.nextInt(l.size());
			Location loc = l.get(pic);
			Bukkit.getWorld(g).getBlockAt(loc).setType(Material.CHEST);
			addItems(name, m, g, loc);
			}
		}
		else {
		
		Location loc = new Location(Bukkit.getWorld(g), x, y, z);
		Bukkit.getWorld(g).getBlockAt(loc).setType(Material.CHEST);
		//System.out.println(x + " " + y + " " + z);
		addItems(name, m, g, loc);
		}
	}
	private static void addItems(String name, Main m, String world, Location l) {
		if(Bukkit.getWorld(world).getBlockAt(l).getState() instanceof Chest) {
			//System.out.println("adding items to chest should work");
			Chest chest = (Chest) Bukkit.getWorld(world).getBlockAt(l).getState();
			int min = m.getConfig().getInt("Loot." + name + ".minLoot");
			int max = m.getConfig().getInt("Loot." + name + ".maxLoot");
			Random r = new Random();
			int amount = r.nextInt(max-min+1)+min;
			if(amount != 0) {
			HashMap<String, ItemStack> items = new HashMap<String, ItemStack>();
			HashMap<ItemStack, Integer> min1 = new HashMap<ItemStack, Integer>();
			HashMap<ItemStack, Integer> max1 = new HashMap<ItemStack, Integer>();
			ArrayList<ItemStack> values = new ArrayList<ItemStack>();
			for(String g : m.getConfig().getConfigurationSection("Loot." + name + ".Treasure").getKeys(false)) {
				//adding the min and max integers for configuration. do this tomorrow
				
				ItemStack item = m.getConfig().getItemStack("Loot." + name + ".Treasure." + g + ".item").clone();
				items.put(g, item);
				int minn = m.getConfig().getInt("Loot." + name + ".Treasure." + g + ".min");
				int maxx = m.getConfig().getInt("Loot." + name + ".Treasure." + g + ".max");
				min1.put(item, minn);
				max1.put(item, maxx);
				
				
				
			}
			List<String> ll = new ArrayList<String>(items.keySet());
			Collections.shuffle(ll);
			for(String f : ll) {
				values.add(items.get(f));
			}
//			for(int i = 0; i < amount; i++) {
//				Random rr = new Random();
//				int numb = rr.nextInt(items.size());
//				if(!values.contains(numb)) {
//					values.add(numb);
//				}
//				if(values.size() == amount) {
//					break;
//				}
//			}
			
		
		for(int i = 0; i < amount; i++) {
			ItemStack it = values.get(i);
			Random rr = new Random();
			int minn = min1.get(it);
			int maxx = max1.get(it);
			int amountt = rr.nextInt(maxx-minn+1)+minn;
			it.setAmount(amountt);
			chest.getInventory().addItem(it);
			
		}
		}
		}
	}
	

//	public static void runDropChest(Main m) {
//		final Main mm = m;
//		Bukkit.getScheduler().scheduleSyncRepeatingTask(m, new Runnable() {
//			
//			@Override
//			public void run() {
//				if(!timer.isEmpty()) {
//					for(String s : timer.keySet()) {
//					   System.out.println("test");
//						if(LootDefine.isEnabled(s, mm)) {
//						  int c = clock.get(s);
//						  c++;
//						  clock.put(s, c);
//						  if(clock.get(s)%timer.get(s) == 0) {
//							  clock.put(s, 0);
//							  dropItem(s, mm);
//						  }
//					   }
//					}
//				}
//			}
//		}, 20L, 20L);
//	}
	
	
	
	
	public static void setRegionTime(String name, int time) {
		timer.put(name, time);
		clock.put(name, 0);
		//enable.put(name, true);
	}
	public static void removeRegionTime(String name) {
		timer.remove(name);
		clock.remove(name);
		//enable.put(name, false);
	}
	public static boolean hasTimer(String name) {
		if(timer.containsKey(name)) {
			return true;
		}
		else {
			return false;
		}
	}
//	public static void setRegionItems(String name, ArrayList<ItemStack> s) {
//		items.put(name, s);
//	}
//	public static void removeRegionItems(String name, ItemStack s) {
//		items.get(name).remove(s);
//	}
//	public static void removeRegionItemsAll(String name) {
//		items.remove(name);
//	}

	@Override
	public void run() {
		if(!timer.isEmpty()) {
			for(String s : timer.keySet()) {
			    if(enable.containsKey(s)) {
				//System.out.println("test");
				if(LootDefine.isEnabled(s, plugin)) {
				  int c = clock.get(s);
				  c++;
				  clock.put(s, c);
				  if(clock.get(s)%timer.get(s) == 0) {
					  clock.put(s, 0);
					  dropItem(s, plugin);
				  }
			   }
			}
			}
		
	}
	}
	
	
}
