package me.Firegred.main;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Firegred.Cell.Generation.Cell;
import me.Firegred.Events.Interact;
import me.Firegred.Events.InventoryEvents;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public static Main m;
	public static HashMap<Player,Location> wait = new HashMap<Player,Location>();
	public static String world;
	@Override
	public void onEnable() {
		m=this;
		world = "CellWorld";
		Bukkit.getPluginManager().registerEvents(new Interact(this), this);
		Bukkit.getPluginManager().registerEvents(new InventoryEvents(this), this);
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			
			this.getConfig().set("Cooldown", 4);
			this.getConfig().set("MaxWidth", 15);
			this.getConfig().set("MaxLength", 15);
			this.getConfig().set("MaxHeight", 15);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		setupEconomy();
		Bukkit.getLogger().info("[Cells] has been enabled!");
	}
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("[Cells] has been disabled!");
	}
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
	return new WorldGen();
	}
	public static Economy economy = null;
	private Boolean setupEconomy()
	    {
	        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	        }
	 
	        return (economy != null);
	    }
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("cell")) {
			int time = this.getConfig().getInt("Cooldown");
			final Player p = (Player) sender;
			if(time!=0) {
				p.sendMessage(ChatColor.RED + "Teleporting you to cell. Do not move for " + ChatColor.YELLOW + time + ChatColor.RED + " seconds.");
			}
			wait.put(p, p.getLocation());
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
				
				@Override
				public void run() {
					if(wait.containsKey(p)) {
					if(!PlayerConfig.hasConfig(p)) {
						p.sendMessage(ChatColor.YELLOW + "You do not have a cell. One has been generated for you!");
						Cell.createCell(p);
					}
					else {
						Cell.teleportToCell(p);
					}
					wait.remove(p);
					}
					
				}
			},time*20L);
			
			
		}
		if(cmd.getName().equalsIgnoreCase("SetTeleport")) {
			Player p = (Player) sender;
			if(p.isOp()) {
				this.getConfig().set("Teleport.x", p.getLocation().getBlockX());
				this.getConfig().set("Teleport.y", p.getLocation().getBlockY());
				this.getConfig().set("Teleport.z", p.getLocation().getBlockZ());
				this.getConfig().set("Teleport.world", p.getWorld().getName());
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Teleport location set at: x: " + p.getLocation().getBlockX() + " y: " + p.getLocation().getBlockY() + " z: " + p.getLocation().getBlockZ());
			}
		}
		if(cmd.getName().equals("SetPlate")) {
			Player p = (Player) sender;
			if(p.isOp()) {
				this.getConfig().set("PressurePlate.x", p.getLocation().getBlockX());
				this.getConfig().set("PressurePlate.y", p.getLocation().getBlockY());
				this.getConfig().set("PressurePlate.z", p.getLocation().getBlockZ());
				this.getConfig().set("PressurePlate.world", p.getWorld().getName());
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Pressure Plate location set at: x: " + p.getLocation().getBlockX() + " y: " + p.getLocation().getBlockY() + " z: " + p.getLocation().getBlockZ());
			}
		}
		return false;
	}
}
