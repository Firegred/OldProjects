package me.Firegred.Hats;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Hats extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Chests sneeze = new Chests(this);
	private final Drugs drugs = new Drugs(this);
	private final Arrows arrows = new Arrows(this);
	private final Functions function = new Functions(this);
	public final ArrayList<Player> Players = new ArrayList<Player>();
	public final HashMap<Player, Location> loc = new HashMap<Player, Location>();
	public static final String[] effectname = {ChatColor.RED + "Angry Villager", ChatColor.WHITE + "Cloud", 
		ChatColor.GOLD + "Lava Drip", ChatColor.DARK_AQUA + "Water Drip", ChatColor.WHITE + "Enchantment Table",
		 ChatColor.WHITE + "Explode",  ChatColor.WHITE + "FireWork Sparks",
		 ChatColor.DARK_RED + "Flame",  ChatColor.GRAY + "FootStep",
		 ChatColor.LIGHT_PURPLE + "Happy Villager",  ChatColor.LIGHT_PURPLE + "<3",
		 ChatColor.GOLD + "Critical",  ChatColor.GREEN + "Instant Spell",
		 ChatColor.RED + "Large Explode!", ChatColor.DARK_GRAY + "Large Smoke", 
		 ChatColor.RED + "Lava",  ChatColor.AQUA + "Mob Spell",
		 ChatColor.AQUA + "Mob Spell Ambient",  ChatColor.GREEN + "Music Note",
		 ChatColor.LIGHT_PURPLE + "Portal",  ChatColor.RED + "Red Dust",
		 ChatColor.GREEN + "Slime",  ChatColor.WHITE + "Snow Shovel",
		 ChatColor.WHITE + "SnowBall Poof!",  ChatColor.BLUE + "Spell",
		 ChatColor.DARK_BLUE + "Splash",  ChatColor.LIGHT_PURPLE + "Town Aura",
		 ChatColor.GREEN + "Witch Magic"  
		 }; 
    public int id;
	@Override
	public void onEnable()
	{
		log.info("[Unusual Hats] has been enabled!");
        File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			
			this.getConfig().addDefault("Chance", 0.05);
			this.getConfig().addDefault("Chest Chance", 1);
			this.getConfig().addDefault("Key Drop Chance", 0.1);
			this.getConfig().addDefault("Hat Chest Chance", 1.5);
			this.getConfig().addDefault("Armor chest Chance", 2.5);
			this.getConfig().addDefault("Firegred's Rare Armor Chance", 0.5);
			this.getConfig().addDefault("Firegred's Special Helm Chance", 0.3);
			this.getConfig().addDefault("MobSpawner chest chance", 2);
			this.getConfig().addDefault("Mining Chest Chance", 0.01);
			this.getConfig().addDefault("Displacement", 1.85);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this.sneeze, this);
		pm.registerEvents(this.drugs, this);
		pm.registerEvents(this.arrows, this);
		pm.registerEvents(this.function, this);
		
		
		
		
	}

	@Override
	public void onDisable()
	{
		log.info("[Unusual Hats] has been disabled!");
	}
	public static ItemStack hatchest() {
		ItemStack h = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta hm = h.getItemMeta();
		hm.setDisplayName(ChatColor.GOLD + "Hat Chest");
		ArrayList<String> lore = new ArrayList<String>();
 		lore.clear();
 		lore.add(ChatColor.DARK_PURPLE + "Place this to open it!");
 		hm.setLore(lore);
 		h.setItemMeta(hm);
 		return h;
	
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("hatchest")) {
			if(args.length == 1) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
				Player t = Bukkit.getPlayer(args[0]);
				if(t.isOnline()) {
					t.getInventory().addItem(hatchest());
					t.sendMessage(ChatColor.GREEN + "You got a free hat chest!");
					p.sendMessage(ChatColor.GREEN + "Hat chest sent to " + t.getName());
				}
				else {
					p.sendMessage(ChatColor.RED + "That player is not online");
				}
					
				}
			}
			else {
				Player t = Bukkit.getPlayer(args[0]);
				if(t.isOnline()) {
					sender.sendMessage(ChatColor.WHITE + "Hat chest sent to " + t.getName());
				}
			}
		}
		}
		
		if(cmd.getName().equalsIgnoreCase("chancelist")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
					for(String s : this.getConfig().getKeys(false)) {
						p.sendMessage(s + ": " + this.getConfig().getString(s));
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("setdisplace")) {
			if(args.length == 1) {
				double value = Double.parseDouble(args[0]);
				if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
					this.getConfig().set("Displacement", value);
					this.getConfig().options().copyDefaults(true);
					this.saveConfig();
					this.reloadConfig();
					p.sendMessage("Displacement set at " + value);
					Chests.displacement = value;
				}
			}
			else {
				this.getConfig().set("Displacement", value);
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
				sender.sendMessage("Displacement set at " + value);
				Chests.displacement = value;
			}
		}
		}
		if(cmd.getName().equalsIgnoreCase("setMineChance")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
					if(args.length == 1) {
						double chance = Double.parseDouble(args[0]);
						this.getConfig().set("Mining Chest Chance", chance);
						this.getConfig().options().copyDefaults(true);
						this.saveConfig();
						this.reloadConfig();
						p.sendMessage("Mining chance set at " + chance);
					}
					else {
						p.sendMessage("setMineChance <number>");
					}
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("HatReload") && ((Player) sender).hasPermission("Hat.Reload")) {
			this.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Sync Hats 1.2 reloaded");
		}
		if(cmd.getName().equalsIgnoreCase("HatInfo")) {
			sender.sendMessage(ChatColor.DARK_PURPLE + "Sync Hats 1.2" + ChatColor.GOLD + "Coded by Firegred");
		    if(sender instanceof Player) {
		    Player p = (Player) sender;
			if(args.length == 1 && p.isOp()) {
		    	try {
					int i = Integer.parseInt(args[0]);
					if(i < effectname.length && i >= 0) {
					ItemStack hat = new ItemStack(Material.DIAMOND_HELMET, 1);
					ItemMeta hatmeta = hat.getItemMeta();
					hatmeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + effectname[i]);
					hat.setItemMeta(hatmeta);
					p.getInventory().addItem(hat);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					
				}
		    	
		    }
		    }
		}
		if(cmd.getName().equalsIgnoreCase("AddKeys")) {
			if(sender instanceof Player) {
				sender.sendMessage(ChatColor.RED + "Sorry, but you cannot do this command.");
			}
			else {
				if(args.length == 0 || args.length == 1 || args.length > 2) {
					sender.sendMessage(ChatColor.RED + "AddKeys (Player) (Amount)");
				}
				else {
					
					
					
					Player p = Bukkit.getPlayer(args[0]);
				    Integer i = Integer.parseInt(args[1]);
				    if(p.isOnline() && i > 0 && i < 10000) {
				    	ItemStack Key = new ItemStack(Material.EMERALD, i);
						ItemMeta KeyMeta = Key.getItemMeta();
						KeyMeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual Chest Key!");
						ArrayList<String> lore = new ArrayList<String>();
						lore.clear();
						lore.add(ChatColor.GOLD + "This has one use on an " + ChatColor.DARK_PURPLE + "Unusual Chest!");
						KeyMeta.setLore(lore);
						Key.setItemMeta(KeyMeta);
				    	p.getInventory().addItem(Key);
				    }
				}
			}
		}
		return false;
	}
}

