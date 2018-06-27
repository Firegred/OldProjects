package me.Firegred.Christmas;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Presentgiving present = new Presentgiving(this);
	private final Presentrest rest = new Presentrest(this);
	private final Gifts gift = new Gifts(this);
	
	@Override
	public void onEnable() {
		log.info("[ChristmasPresents] has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this.present, this);
		pm.registerEvents(this.rest, this);
		pm.registerEvents(this.gift, this);
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			this.getConfig().addDefault("Christmasgifts", "false");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		
			
	}
	@Override
	public void onDisable() {
		log.info("[ChristmasPresents] has been disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("givepresent")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
					if(args.length == 0 || args.length >= 2) {
						p.sendMessage(ChatColor.RED + "/givepresent (Player name)");
					}
					else {
						if(Bukkit.getOfflinePlayer(args[0]).isOnline()) {
							Player s = Bukkit.getPlayer(args[0]);
							p.sendMessage(ChatColor.GREEN + "Present sent!");
							s.sendMessage(ChatColor.GREEN + "An Operator has given you a present. Happy Holidays!");
							
							ItemStack present = new ItemStack(Material.ENDER_CHEST, 1);
							ItemMeta presentMeta = present.getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							presentMeta.setDisplayName(ChatColor.GREEN + "Present for: " + ChatColor.YELLOW + s.getName());
							lore.add(ChatColor.GOLD + "Right-Click to open!");
							presentMeta.setLore(lore);
							present.setItemMeta(presentMeta);
							s.getInventory().addItem(present);
						}
						else {
							p.sendMessage(ChatColor.RED + "Error: Player Offline");
						}
					}
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("togglechristmaspresents")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
					if(this.getConfig().getString("Christmasgifts").equals("false")) {
					p.sendMessage(ChatColor.GREEN + "Christmasgifts are now enabled!");
					this.getConfig().set("Christmasgifts", "true");
					this.getConfig().options().copyDefaults(true);
					this.saveConfig();
					this.reloadConfig();
					}
					else if(this.getConfig().getString("Christmasgifts").equals("true")) {
						p.sendMessage(ChatColor.GREEN + "Christmasgifts are now disabled!");
						this.getConfig().set("Christmasgifts", "false");
						this.getConfig().options().copyDefaults(true);
						this.saveConfig();
						this.reloadConfig();	
					}
					
				}
				
			}
		}
		return false;
	}
}
