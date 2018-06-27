package me.Firegred.Easter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {
	private final Logger log = Logger.getLogger("Minecraft");
	private EggEvents egger = new EggEvents(this);
	public int time;
	public int limit;
	public boolean enable;
	public boolean broadcast;
	public boolean pickup;
	
	public String world = getConfig().getString("Easter.dropWorld");
	public ArrayList<ItemStack> eggs = new ArrayList<ItemStack>();
	public static HashMap<String, Integer> players =new HashMap<String, Integer>();
	public Random random;
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		log.info("[Sync Easter] has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(egger, this);
		
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			this.getConfig().addDefault("Easter.Timer", 60);
			this.getConfig().addDefault("Easter.Limit", 10);
			this.getConfig().addDefault("Easter.enable", false);
			this.getConfig().addDefault("Easter.broadcast", false);
			this.getConfig().addDefault("Easter.instantpickup", false);
			this.getConfig().addDefault("Easter.dropWorld", "world");
			this.getConfig().addDefault("Easter.Players.EasterBunny", 10);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			time = getConfig().getInt("Easter.Timer");
			limit = getConfig().getInt("Easter.Limit");
			enable = getConfig().getBoolean("Easter.enable");
			broadcast = getConfig().getBoolean("Easter.broadcast");
			pickup = getConfig().getBoolean("Easter.instantpickup");
		}
		try {
			for(String key : this.getConfig().getConfigurationSection("Easter.Players").getKeys(false)){
				//System.out.println(config.getString("derp."+key+".MAX"));
				int k = this.getConfig().getInt("Easter.Players." + key);
				players.put(key, k);
			}
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		time = getConfig().getInt("Easter.Timer");
		limit = getConfig().getInt("Easter.Limit");
		enable = getConfig().getBoolean("Easter.enable");
		broadcast = getConfig().getBoolean("Easter.broadcast");
		pickup = getConfig().getBoolean("Easter.instantpickup");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				if(enable == true) {
				time = getConfig().getInt("Easter.Timer");
				limit = getConfig().getInt("Easter.Limit");
				enable = getConfig().getBoolean("Easter.enable");
				broadcast = getConfig().getBoolean("Easter.broadcast");
				pickup = getConfig().getBoolean("Easter.instantpickup");
				world = getConfig().getString("Easter.dropWorld");
					dropEgg();
					
				
				
			}
			}
		},1L, 20L*(time));
		
			
			
			
	}
	@Override
	public void onDisable() {
		log.info("[Sync Easter] has been disabled!");
	}
	public void dropEgg() {
		ItemStack egg = Eggs.easter();
		int x1 = getConfig().getInt("Easter.Pos1.X");
		int y1 = getConfig().getInt("Easter.Pos1.Y");
		int z1 = getConfig().getInt("Easter.Pos1.Z");
		
		int x2 = getConfig().getInt("Easter.Pos2.X");
		int y2 = getConfig().getInt("Easter.Pos2.Y");
		int z2 = getConfig().getInt("Easter.Pos2.Z");
		random = new Random();
		int x = 0;
		int y = 0;
		int z = 0;
		if(x1>=x2) {
			x = random.nextInt(Math.abs(x1-x2)+1)+x2;
		}
		else if(x2>x1) {
			x = random.nextInt(Math.abs(x2-x1)+1)+x1;
		}
		if(y1>=y2) {
			y = random.nextInt(Math.abs(y1-y2)+1)+y2;
		}
		else if(y2>y1) {
			y = random.nextInt(Math.abs(y2-y1)+1)+y1;
		}
		if(z1>=z2) {
			z = random.nextInt(Math.abs(z1-z2)+1)+z2;
		}
		else if(z2>z1) {
			z = random.nextInt(Math.abs(z2-z1)+1)+z1;
		}
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		Bukkit.getWorld(world).dropItemNaturally(loc, egg);
		eggs.add(egg);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			time = getConfig().getInt("Easter.Timer");
			limit = getConfig().getInt("Easter.Limit");
			enable = getConfig().getBoolean("Easter.enable");
			broadcast = getConfig().getBoolean("Easter.broadcast");
			pickup = getConfig().getBoolean("Easter.instantpickup");
			if(cmd.getName().equalsIgnoreCase("JumpI")) {
				if(p.hasPermission("Easter.winner")) {
					if(p.hasPotionEffect(PotionEffectType.JUMP)) {
						p.removePotionEffect(PotionEffectType.JUMP);
						p.sendMessage(ChatColor.LIGHT_PURPLE + "You no longer have bunny jumping abilities");
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0));
						p.sendMessage(ChatColor.LIGHT_PURPLE + "You have bunny jumping abilities!");
					}
				}
				else {
					p.sendMessage(ChatColor.RED + "Sorry, the Easter Bunny doesn't let you use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("EasterEggTotal")) {
				String message = ChatColor.LIGHT_PURPLE + "Top Easter Egg Finders:";
				if(players.size() < 5) {
				List<Entry<String, Integer>> greatest = findGreatest(players, players.size());
				int nn = 0;
				p.sendMessage(message);
				for (Entry<String, Integer> entry : greatest)
		        {
		           nn++;
		           p.sendMessage(ChatColor.GREEN + entry.getKey() + ChatColor.GREEN + ": " + ChatColor.YELLOW + entry.getValue());
		        }
				}
				else {
					List<Entry<String, Integer>> greatest = findGreatest(players, 5);	
					int nn = 0;
					for (Entry<String, Integer> entry : greatest)
			        {
			           nn++;
			           p.sendMessage(ChatColor.GREEN + entry.getKey() + ChatColor.GREEN + ":" + ChatColor.YELLOW + entry.getValue());
			        }
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("EasterEggCount")) {
				if(this.getConfig().get("Easter.Players." + p.getName()) == null) {
					p.sendMessage(ChatColor.RED + "You haven't collected any eggs!");
				}
				else {
					int eggs = this.getConfig().getInt("Easter.Players." + p.getName());
					p.sendMessage(ChatColor.GREEN + "You have collected " + ChatColor.YELLOW + eggs + ChatColor.GREEN + " eggs");
				}
			}
			else if(cmd.getName().equalsIgnoreCase("easterEggs")) {
				p.sendMessage(ChatColor.GREEN + "There are " + ChatColor.RED + eggs.size() + ChatColor.GREEN + " easter eggs that are in existance");
			}
			if(p.isOp()) {
				int x = p.getLocation().getBlockX();
				int y = p.getLocation().getBlockY();
				int z = p.getLocation().getBlockZ();
		if(cmd.getName().equalsIgnoreCase("easterpos1")) {
			this.getConfig().set("Easter.Pos1.X", x);
			this.getConfig().set("Easter.Pos1.Y", y);
			this.getConfig().set("Easter.Pos1.Z", z);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			this.reloadConfig();
			p.sendMessage(ChatColor.GREEN + "First Position set at X: " + x + " Y: " + y + " Z: " + z);
		}
		else if(cmd.getName().equalsIgnoreCase("easterpos2")) {
			this.getConfig().set("Easter.Pos2.X", x);
			this.getConfig().set("Easter.Pos2.Y", y);
			this.getConfig().set("Easter.Pos2.Z", z);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			this.reloadConfig();
			p.sendMessage(ChatColor.GREEN + "Second Position set at X: " + x + " Y: " + y + " Z: " + z);
		}
		else if(cmd.getName().equalsIgnoreCase("easterreload")) {
			this.reloadConfig();
			p.sendMessage(ChatColor.GREEN + "Sync Easter reloaded!");
		}
		else if(cmd.getName().equalsIgnoreCase("eastertoggleEnable")) {
			String message = ChatColor.GREEN + "Sync Easter has been ";
			if(enable == true) {
				
				this.getConfig().set("Easter.enable", false);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.RED + "Disabled");
			}
			else {
				
				this.getConfig().set("Easter.enable", true);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.YELLOW + "Enabled");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("eastertoggleBroadcast")) {
			String message = ChatColor.GREEN + "Sync Easter broadcast has been ";
			if(broadcast == true) {
				
				this.getConfig().set("Easter.broadcast", false);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.RED + "Disabled");
			}
			else {
				
				this.getConfig().set("Easter.broadcast", true);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.YELLOW + "Enabled");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("eastertogglepickup")) {
			String message = ChatColor.GREEN + "Sync Easter Instant Pickup has been";
			if(broadcast == true) {
				
				this.getConfig().set("Easter.instantpickup", false);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.RED + "Disabled");
			}
			else {
				
				this.getConfig().set("Easter.instantpickup", true);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.YELLOW + "Enabled");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("eastersetTimer")) {
			if(args.length == 1) {
				int time = Integer.parseInt(args[0]);
				this.getConfig().set("Easter.Timer", time);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Sync Easter Drop timer set at " + ChatColor.RED + time + " seconds");
			}
			else {
				p.sendMessage("/eastersetTimer <seconds>");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("eastersetLimit")) {
			if(args.length == 1) {
				int limit = Integer.parseInt(args[0]);
				this.getConfig().set("Easter.Limit", limit);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Sync Easter Drop Limit set at " + ChatColor.RED + limit + " eggs");
			}
			else {
				p.sendMessage("/eastersetLimit <number>");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("eastersetworld")) {
			if(args.length == 1) {
				String world = args[0];
				this.getConfig().set("Easter.dropWorld", world);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Sync Easter Drop World set at " + ChatColor.RED + world);
			}
			else {
				p.sendMessage("/eastersetworld <world>");
			}	
		}
		else if(cmd.getName().equalsIgnoreCase("eastertime")) {
			p.sendMessage(ChatColor.GREEN + "Sync Easter Drop Timer set at " + ChatColor.RED + time);
		}
		else if(cmd.getName().equalsIgnoreCase("easterlimit")) {
			p.sendMessage(ChatColor.GREEN + "Sync Easter Drop Limit set at " + ChatColor.RED + limit);
		}
		else if(cmd.getName().equalsIgnoreCase("easterrewardbox")) {
			if(args.length == 1) {
				int num = Integer.parseInt(args[0]);
				p.getInventory().addItem(new ItemStack(Eggs.chest(p.getName(), num)));
				p.updateInventory();
				p.sendMessage(ChatColor.RED + "Special OP");
			}
		}
		
		else if(cmd.getName().equalsIgnoreCase("eastertoggleRewards")) {
			String message = ChatColor.GREEN + "Sync Easter Rewards has been";
			if(this.getConfig().get("Easter.rewards") == null) {
				this.getConfig().set("Easter.rewards", true);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.YELLOW + "Enabled");
			}
			else {
				boolean rewards = this.getConfig().getBoolean("Easter.rewards");
				if(rewards == true) {
				this.getConfig().set("Easter.rewards", false);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.RED + "Disabled");
			}
			else {
				
				this.getConfig().set("Easter.rewards", true);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(message + ChatColor.YELLOW + "Enabled");
			}
		}
			}
		}
		}
		
		return false;
		
	}
	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
    findGreatest(Map<K, V> map, int n)
{
    Comparator<? super Entry<K, V>> comparator = 
        new Comparator<Entry<K, V>>()
    {
        @Override
        public int compare(Entry<K, V> e0, Entry<K, V> e1)
        {
            V v0 = e0.getValue();
            V v1 = e1.getValue();
            return v0.compareTo(v1);
        }
    };
    PriorityQueue<Entry<K, V>> highest = 
        new PriorityQueue<Entry<K,V>>(n, comparator);
    for (Entry<K, V> entry : map.entrySet())
    {
        highest.offer(entry);
        while (highest.size() > n)
        {
            highest.poll();
        }
    }

    List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
    while (highest.size() > 0)
    {
        result.add(highest.poll());
    }
    return result;
}

}
