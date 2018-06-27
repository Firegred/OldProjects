package me.Firegred.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import me.Firegred.Creations.RegionDefine;
import me.Firegred.Creations.SignCreation;
import me.Firegred.Game.Drop;
import me.Firegred.Game.Enchanting;
import me.Firegred.Game.Ending;
import me.Firegred.Game.MobSpawning;
import me.Firegred.Game.Treez;
import me.Firegred.Lobby.Login;
import me.Firegred.Lobby.Teleports;
import me.Firegred.Lobby.Waiting;
import me.Firegred.mechanics.Basics;
import me.Firegred.mechanics.Chat;
import me.Firegred.mechanics.SignClick;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.scheduler.BukkitTask;


public class Main extends JavaPlugin {

	private static final Logger log = Logger.getLogger("Minecraft");
	public static String[] data = {"Lobby", "Que","Game","Tree","Axe","Boots","Leggings","ChestPlate","Helm","Spectator","Sheep"};
	public static String trees = ChatColor.GRAY + "[" + ChatColor.GREEN + "Tree Wreckers" + ChatColor.GRAY + "] ";
	public static World w;
	public static String games = "Games";
	public static HashMap<Player, Location> pos1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> pos2 = new HashMap<Player, Location>();
	public static HashMap<Player, String> regioner = new HashMap<Player, String>();
	
	public static String throwaway = trees + ChatColor.RED + "You destroyed your ";
	public static String noarmor = trees + ChatColor.RED + "You need to wear armor before throwing this away";
	
	public static ArrayList<Material> canReplace = new ArrayList<Material>();
	public static ArrayList<Material> canDestroy = new ArrayList<Material>();
	public static ArrayList<Material> canSpawn = new ArrayList<Material>();
	public static ArrayList<Material> canDrop = new ArrayList<Material>();
	
	public static ArrayList<ItemStack> OPItems = new ArrayList<ItemStack>();
	
	public static DyeColor[] dye = {DyeColor.BLUE, 
	    DyeColor.GREEN,DyeColor.LIGHT_BLUE,DyeColor.LIME,DyeColor.MAGENTA,DyeColor.ORANGE,
		DyeColor.PINK,DyeColor.PURPLE,DyeColor.CYAN,DyeColor.RED,DyeColor.WHITE,DyeColor.YELLOW, DyeColor.BROWN,DyeColor.BLACK
		,DyeColor.GRAY,DyeColor.SILVER};
	
	private final RegionDefine a1 = new RegionDefine(this);
	private final SignCreation a2 = new SignCreation(this);
	private final Drop a3 = new Drop(this);
	private final Ending a4 = new Ending(this);
	private final MobSpawning a5 = new MobSpawning(this);
	private final Treez a6 = new Treez(this);
	private final Login a7 = new Login(this);
	private final Teleports a8 = new Teleports(this);
	private final Waiting a9 = new Waiting(this);
	private final Basics a10 = new Basics(this);
	private final Chat a11 = new Chat(this);
	private final SignClick a12 = new SignClick(this);
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		File file = new File(getDataFolder() + File.separator + "config.yml");
	    potions();
		pm.registerEvents(a1, this);
		pm.registerEvents(a2, this);
		pm.registerEvents(a3, this);
		pm.registerEvents(a4, this);
		pm.registerEvents(a5, this);
		pm.registerEvents(a6, this);
		pm.registerEvents(a7, this);
		pm.registerEvents(a8, this);
		pm.registerEvents(a9, this);
		pm.registerEvents(a10, this);
		pm.registerEvents(a11, this); 
		pm.registerEvents(a12, this);
		canReplace.add(Material.SAPLING);
        canReplace.add(Material.LOG);
        canReplace.add(Material.LOG_2);
        canReplace.add(Material.LEAVES);
        canReplace.add(Material.LEAVES_2);
        canReplace.add(Material.getMaterial(37));
        canReplace.add(Material.getMaterial(38));
        canReplace.add(Material.getMaterial(31));
        canReplace.add(Material.getMaterial(175));
        canReplace.add(Material.AIR);
        canReplace.add(Material.WOOL);
        
        canDrop.add(Material.LEAVES);
        canDrop.add(Material.LEAVES_2);
        canDrop.add(Material.SAPLING);
        
        canDestroy.add(Material.SAPLING);
        canDestroy.add(Material.LOG);
        canDestroy.add(Material.LOG_2);
        canDestroy.add(Material.LEAVES);
        canDestroy.add(Material.LEAVES_2);
        canDestroy.add(Material.getMaterial(37));
        canDestroy.add(Material.getMaterial(38));
        canDestroy.add(Material.getMaterial(31));
        canDestroy.add(Material.getMaterial(175));
        canDestroy.add(Material.WOOL);
        
        canSpawn.add(Material.AIR);
        canSpawn.add(Material.SAPLING);
        canSpawn.add(Material.getMaterial(37));
        canSpawn.add(Material.getMaterial(38));
        canSpawn.add(Material.getMaterial(31));
        canSpawn.add(Material.getMaterial(175));
        
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			this.getConfig().addDefault("World", "PutWorldNameHere");
			this.getConfig().addDefault("ServerLobby.X", 0);
			this.getConfig().addDefault("ServerLobby.Y", 0);
			this.getConfig().addDefault("ServerLobby.Z", 0);
			  //this.getConfig().addDefault(path, "test");
			//this.getConfig().addDefault("message", "This is my message");
			//this.getConfig().addDefault("message2", "plugin has been reloaded!");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		BukkitTask TaskName = new MobSpawning(this).runTaskTimer(this, 20, 20);
		w = Bukkit.getWorld(this.getConfig().getString("World"));
		log.info("[TreeWreckers] has been enabled!");
	}
	@Override
	public void onDisable() {
		log.info("[TreeWrecker] has been disabled!");
	}
	
	private void potions() {
		   ItemStack flint = new ItemStack(Material.FIREBALL,1);
		   ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 1);
		
		   ItemStack regen1 = new ItemStack(Material.POTION, 1, (short) 8225);
		   ItemStack regen2 = new ItemStack(Material.POTION, 1, (short) 8193);
		   ItemStack swiftness = new ItemStack(Material.POTION, 1, (short) 8194);
		   ItemStack swiftness2 = new ItemStack(Material.POTION, 1, (short) 8226);
		   ItemStack healing1 = new ItemStack(Material.POTION, 1, (short) 8197);
		   ItemStack healing2 = new ItemStack(Material.POTION, 1, (short) 8229);
		   ItemStack nightvision = new ItemStack(Material.POTION, 1, (short) 8198);
		   ItemStack strength1 = new ItemStack(Material.POTION, 1,(short) 8201);
		   ItemStack strength2 = new ItemStack(Material.POTION, 1,(short) 8233);
		   ItemStack leaping1 = new ItemStack(Material.POTION, 1,(short) 8203);
		   ItemStack leaping2 = new ItemStack(Material.POTION, 1,(short) 8235);
		   ItemStack waterbreathing = new ItemStack(Material.POTION, 1,(short) 8205);
		   
		   ItemStack posionS = new ItemStack(Material.POTION, 1,(short) 16388);
		   ItemStack weaknessS = new ItemStack(Material.POTION, 1,(short) 16392);
		   ItemStack slownessS = new ItemStack(Material.POTION, 1,(short) 16394);
		   ItemStack HarmingS = new ItemStack(Material.POTION, 1,(short) 16396);
		   
		   ItemStack Bow = new ItemStack(Material.BOW);
		   Bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		   //ItemStack Arrow = new ItemStack(Material.ARROW);
		   OPItems.add(flint);
		   OPItems.add(apple);
		   OPItems.add(regen1);
		   OPItems.add(regen2);
		   OPItems.add(swiftness);
		   OPItems.add(swiftness2);
		   OPItems.add(healing1);
		   
		   OPItems.add(healing2);
		   OPItems.add(nightvision);
		   OPItems.add(strength1);
		   OPItems.add(strength2);
		   OPItems.add(leaping1);
		   OPItems.add(leaping2);
		   OPItems.add(waterbreathing);
		   OPItems.add(posionS);
		   OPItems.add(weaknessS);
		   OPItems.add(slownessS);
		   OPItems.add(HarmingS);
		   OPItems.add(Bow);
		   
		   System.out.println("Items added");
		   System.out.println(OPItems.size());
		   
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			Location l = p.getLocation();
			if(p.isOp()) {
				if(cmd.getName().equals("TreeWrecker") || cmd.getName().equals("TW")) {
					if(args.length==0) {
						p.sendMessage("/TW setServerLobby");
						p.sendMessage("/TW setLobby <name>");
						p.sendMessage("/TW setspawns <name> <name>");
						p.sendMessage("/TW defineRegion <name>");
						p.sendMessage("/TW defineGround <name>");
						p.sendMessage("/TW reload");
					}
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("reload")) {
							this.reloadConfig();
							p.sendMessage("Tree Wreckers reloaded");
							w = Bukkit.getWorld(this.getConfig().getString("World"));
							
						}
						if(args[0].equalsIgnoreCase("setServerLobby")) {
							
							Teleports.SetServerLobby(l.getBlockX(), l.getBlockY(), l.getBlockZ());
							p.sendMessage("Lobby set!");
						}
					}
					if(args.length == 2) {
					 if(args[0].equalsIgnoreCase("setLobby")) {
						 String name = args[1];
						 if(this.getConfig().get(games + "." + name + ".Sign.X") != null) {
							 Teleports.SetGameLobby(name,l.getBlockX(), l.getBlockY(), l.getBlockZ());
							 p.sendMessage("Lobby for " + name + " has been set");
						 }
						 else {
							p.sendMessage(name + " doesn't exist"); 
						 }
					 }
					 if(args[0].equalsIgnoreCase("defineRegion")) {
						 String name = args[1];
						 if(this.getConfig().get(games + "." + name + ".Sign.X") != null) {
							 regioner.put(p, name);
							 p.sendMessage("Wand for " + name);
							 p.getInventory().addItem(RegionDefine.wand());
						 }
						 else {
							 p.sendMessage(name + " doesn't exist"); 	 
						 }
						 
					 }
					 if(args[0].equalsIgnoreCase("defineGround")) {
						 String name = args[1];
						 if(this.getConfig().get(games + "." + name + ".Sign.X") != null) {
							 regioner.put(p, name);
							 p.sendMessage("Ground Wand for " + name);
							 p.getInventory().addItem(RegionDefine.groundwand());
							 
						 }
						 else {
							 p.sendMessage(name + " doesn't exist"); 	 
						 }
					 }
					 
					}
					if(args.length == 3) {
						if(args[0].equalsIgnoreCase("setspawns")) {
							String name = args[1];
							String name2 = args[2];
							 if(this.getConfig().get(games + "." + name + ".Sign.X") != null) {
								 int x = l.getBlockX();
								 int y = l.getBlockY();
								 int z = l.getBlockZ();
								 this.getConfig().set(games + "." + name + ".Spawns." + name2 +".X", x);
								 this.getConfig().set(games + "." + name + ".Spawns." + name2 +".Y", y);
								 this.getConfig().set(games + "." + name + ".Spawns." + name2 +".Z", z);
								 this.saveConfig();
								 this.reloadConfig();
								 p.sendMessage("Region " + name + " now has a possible spawn of " + name2);
							 }
							 else {
								 p.sendMessage(name + " doesn't exist"); 	 
							 }
						}
					}
				}
				
			}
		}
		
		return false;
	}
	
}
