package me.Firegred.cool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeFarm extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private final TreeFarmListener Tree = new TreeFarmListener(this);
	public String locs;
	public final HashMap<Player, String> nice = new HashMap<Player, String>();
	public final ArrayList<Player> enab = new ArrayList<Player>();
	public final static ArrayList<Integer> x5 = new ArrayList<Integer>();
	public final static ArrayList<Integer> y5 = new ArrayList<Integer>();
	public final static ArrayList<Integer> z5 = new ArrayList<Integer>();
	public String path = "TreeFarm";
	@Override
	public void onEnable() {
		log.info("[TreeFarm] has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this.Tree, this);
		File file = new File(getDataFolder() + File.separator + "config.yml");
		for(String s : this.getConfig().getKeys(false)) {
			if(s.startsWith("x")) {
				x5.add(this.getConfig().getInt(s));
			}
			if(s.startsWith("y")) {
				y5.add(this.getConfig().getInt(s));
			}
			if(s.startsWith("z")) {
				z5.add(this.getConfig().getInt(s));
			}
		}
		//this.getConfig().options().copyDefaults(true);
		//this.saveConfig();
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			  
			  //this.getConfig().addDefault(path, "test");
			  
			
			
	
			  
			//this.getConfig().addDefault("message", "This is my message");
			//this.getConfig().addDefault("message2", "plugin has been reloaded!");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
			
	}
	@Override
	public void onDisable() {
		log.info("[TreeFarm] has been disabled!");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("TreeFarmSet") && ((Player) sender).hasPermission("TreeFarm.Set")) {
			if(args.length == 0 || args.length > 2) {
				sender.sendMessage("Usage: /TreeFarmSet <TreeFarmName>");
				
			}
			if(args.length == 1) {
				//TreeFarmName = args[0];
				sender.sendMessage("[TreeFarm]" + " Now click a block to set it as a TreeFarm!");
				nice.put((Player) sender, args[0]);
				enab.add((Player) sender);
				
				
			}
		}
		if(cmd.getName().equals("TreeFarmList") && ((Player) sender).hasPermission("TreeFarm.List")) {
			sender.sendMessage(getConfig().getKeys(false).toString());
			if(x5.isEmpty() || y5.isEmpty() || z5.isEmpty()) {
				//System.out.println("I am Empty!");
				
			}
			else {
				//System.out.println("I am not Empty!");
				//System.out.println(x5.toString());
				//System.out.println(y5.toString());
				//System.out.println(z5.toString());
			}
			//getConfig().getKeys(false).contains(")
			//sender.sendMessage(getConfig().getKeys(true).toString());
		}
		if(cmd.getName().equals("TreeFarmRemove") && ((Player) sender).hasPermission("TreeFarm.Remove")) {
			if(args.length == 0 || args.length > 2) {
				sender.sendMessage("Usage: /TreeFarmRemove <TreeFarmName>");
			}
			if(args.length == 1) {
				this.getConfig().set("x" + args[0], null);
				this.getConfig().set("y" + args[0], null);
				this.getConfig().set("z" + args[0], null);
				sender.sendMessage("[TreeFarm]TreeFarm " + args[0] + " has been removed!");
				x5.clear();
				y5.clear();
				z5.clear();
				for(String s : this.getConfig().getKeys(false)) {
					if(s.startsWith("x")) {
						x5.add(this.getConfig().getInt(s));
					}
					if(s.startsWith("y")) {
						y5.add(this.getConfig().getInt(s));
					}
					if(s.startsWith("z")) {
						z5.add(this.getConfig().getInt(s));
					}
				}
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				this.reloadConfig();
			}
		}
		if(cmd.getName().equals("TreeFarmInfo")) {
			sender.sendMessage("[TreeFarm] " + ChatColor.GOLD + "V: 1.0" + ChatColor.BLUE + " Coded by Firegred");
		}
		
		//if(cmd.getName().equalsIgnoreCase("tutorialreload")) {
		//	this.reloadConfig();
	//		sender.sendMessage(this.getConfig().getString("message2"));
	//	}
		return false;
		
	}

}
