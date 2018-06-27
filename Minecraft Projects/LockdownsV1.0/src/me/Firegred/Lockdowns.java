package me.Firegred;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lockdowns extends JavaPlugin implements Listener{


	private static final Logger log = Logger.getLogger("Minecraft");
//	private static Configuration c;
//	private static Lockdowns l;
	private final LockdownListener w = new LockdownListener(this);
	public static HashMap<Player, Location> pos1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> pos2 = new HashMap<Player, Location>();
	public HashMap<Player, String> pname = new HashMap<Player, String>();
	public HashMap<Player, String> pregion = new HashMap<Player, String>();
	@Override
	public void onEnable() {
		log.info("[Lockdown] has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this.w, this);
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			this.reloadConfig();
		}
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable() {
		log.info("[Lockdown] has been disabled!");
	}
	public ItemStack wand() {
		ItemStack w = new ItemStack(Material.IRON_AXE);
		ItemMeta wm = w.getItemMeta();
		wm.setDisplayName(ChatColor.RED + "Lockdown Wand");
		w.setItemMeta(wm);
		return w;
	}
	public static String realmessage(String m) {
		return ChatColor.translateAlternateColorCodes('$', m);
	}
	public static void generateBlocks(int x1, int x2, int y1, int y2, int z1, int z2, String world, boolean bool) {
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
		for(int xx = px2; xx <= px1; xx++) {
			for(int yy = py2; yy <= py1; yy++) {
				for(int zz = pz2; zz <= pz1; zz++) {
					Location ll = new Location(Bukkit.getWorld(world), xx, yy, zz);
					if(bool == false) {
						if(Bukkit.getWorld(world).getBlockAt(ll).getType().equals(Material.AIR))
						Bukkit.getWorld(world).getBlockAt(ll).setTypeId(101);
					}
					else {
						if(Bukkit.getWorld(world).getBlockAt(ll).getTypeId()==101)
						Bukkit.getWorld(world).getBlockAt(ll).setTypeId(0);	
					}
				}
			}
		}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("Lockdowninfo")) {
			sender.sendMessage(ChatColor.GOLD + "Lockdown V1.0 coded by Firegred");
		}
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Lockdownreload")) {
				this.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "PrisonLockdown reloaded!");
			}
			if(cmd.getName().equalsIgnoreCase("Lockdown") && p.hasPermission("Lockdown.use")) {
				if(args.length == 1) {
					String name = args[0];
                    if(this.getConfig().getString(name + ".messagesON") != null) {						
						boolean bool = this.getConfig().getBoolean(name + ".ON");
						for(String key : this.getConfig().getConfigurationSection(name + ".regions").getKeys(false)){
							 int x1 = this.getConfig().getInt(name + ".regions." + key + ".X1");
							 int x2 = this.getConfig().getInt(name + ".regions." + key + ".X2");
							 int y1 = this.getConfig().getInt(name + ".regions." + key + ".Y1");
							 int y2 = this.getConfig().getInt(name + ".regions." + key + ".Y2");
							 int z1 = this.getConfig().getInt(name + ".regions." + key + ".Z1");
							 int z2 = this.getConfig().getInt(name + ".regions." + key + ".Z2");
							 String w = this.getConfig().getString(name + ".regions." + key + ".world");
							 generateBlocks(x1, x2, y1, y2, z1, z2, w, bool);
						}
						
						if(bool == false) {
						String message = this.getConfig().getString(name + ".messagesON");
					    String m = realmessage(message);
					    this.getConfig().set(name + ".ON", true);
					    this.saveConfig();
					    this.reloadConfig();
						if(this.getConfig().getBoolean(name + ".broadcast")) {
					    Bukkit.broadcastMessage(m);
						}
						}
						else {
							String message = this.getConfig().getString(name + ".messagesOFF");
						    String m = realmessage(message);
						    this.getConfig().set(name + ".ON", false);
						    this.saveConfig();
						    this.reloadConfig();
						    if(this.getConfig().getBoolean(name + ".broadcast")) {
							    Bukkit.broadcastMessage(m);
							}
							
						}
						
						
						
					}
					else {
						p.sendMessage(ChatColor.RED + "This lockdown region doesn't exist");
					}
				}
				else {
					p.sendMessage(ChatColor.RED + "/Lockdown <name>");
				}
			}
			if(p.isOp() || p.hasPermission("Lockdown.modify")) {
			
			if(cmd.getName().equalsIgnoreCase("lockdownONmessage")) {
				
			}
			if(cmd.getName().equalsIgnoreCase("lockdownOFFmessage")) {
				
			}
			if(cmd.getName().equalsIgnoreCase("Lockdowncreate")) {
				
				if(args.length == 1) {
					String name = args[0];
					this.getConfig().set(name + ".messagesON", "Lockdown " + name + " activated");
					this.getConfig().set(name + ".messagesOFF", "Lockdown " + name + " deactivated");
					this.getConfig().set(name + ".ON", false);
					this.getConfig().set(name + ".broadcast", true);
					this.saveConfig();
					this.reloadConfig();
					p.sendMessage(ChatColor.GREEN + "Lockdown " + ChatColor.YELLOW + name + ChatColor.GREEN + " created");
				}
				else {
					p.sendMessage("Lockdowncreate <name>");
				}
			}
			if(cmd.getName().equalsIgnoreCase("Lockdownbroadcast")) {
				if(args.length == 2) {
					String name = args[0];
					String bool = args[1];
					if(this.getConfig().getString(name + ".messagesON") != null) {
						if(bool.equals("true")) {
							this.getConfig().set(name + ".broadcast",true);
							this.saveConfig();
							this.reloadConfig();
							p.sendMessage(ChatColor.GREEN + "The lockdown " + ChatColor.YELLOW + name + ChatColor.GREEN+ " will now broadcast messages!");
						}
						if(bool.equals("false")) {
							this.getConfig().set(name + ".broadcast",false);
							this.saveConfig();
							this.reloadConfig();
							p.sendMessage(ChatColor.GREEN + "The lockdown " + ChatColor.YELLOW + name + ChatColor.GREEN+ " will not broadcast messages!");
						}
					}
				}
				else {
					p.sendMessage("Lockdownbroadcast <name> <true/false>");
				}
			}
			if(cmd.getName().equalsIgnoreCase("Lockdowncreateregion")) {
				if(args.length == 2) {
					String name = args[0];
					String region = args[1];
					if(this.getConfig().getString(name + ".messagesON") != null) {
						 p.sendMessage(ChatColor.GREEN + "Select two blocks to set region " + ChatColor.YELLOW + region);
						 p.getInventory().addItem(wand());
						 pname.put(p, name);
						 pregion.put(p, region);
					}
					else {
						p.sendMessage(ChatColor.RED + "The region " + ChatColor.YELLOW + name + ChatColor.RED + " doesn't exist");
					}
				}
				else {
					p.sendMessage("Lockdowncreateregion <name> <subregion>");	
				}
			}
		   if(cmd.getName().equalsIgnoreCase("Lockdownlist")) {
			   if(args.length == 1) {
				   String name = args[0];
				   if(this.getConfig().getString(name + ".messagesON") != null) {
					   p.sendMessage(ChatColor.GREEN + "regions for " + ChatColor.YELLOW + name + ChatColor.GREEN + ":");
					   for(String g : getConfig().getConfigurationSection(name + ".regions").getKeys(false)) {
						   p.sendMessage(ChatColor.YELLOW + g);
					   }
				   }
				   else {
					   p.sendMessage(ChatColor.RED + "The region " + ChatColor.YELLOW + name + ChatColor.RED + " doesn't exist");   
				   }
			   }
			   else {
				   p.sendMessage("Lockdownlist <name>");
			   }
		   }
		   if(cmd.getName().equalsIgnoreCase("Lockdowndeleteregion")) {
			   if(args.length == 2) {
					String name = args[0];
					String region = args[1];
					if(this.getConfig().getString(name + ".messagesON") != null) {
						 p.sendMessage(ChatColor.GREEN + "The region " + ChatColor.YELLOW + region + ChatColor.GREEN + " for the lockdown " + ChatColor.YELLOW + name + ChatColor.RED + " has been deleted");
						 getConfig().set(name + ".regions." + region, null);
						 saveConfig();
						 reloadConfig();
					}
					else {
						p.sendMessage(ChatColor.RED + "The region " + ChatColor.YELLOW + name + ChatColor.RED + " doesn't exist");
					}
				}
				else {
					p.sendMessage("Lockdowndeleteregion <name> <subregion>");	
				}
		   }
		}
		}
		return false;
		
	}
	
	
	
}
