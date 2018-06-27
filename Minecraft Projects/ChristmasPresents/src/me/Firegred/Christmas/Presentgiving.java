package me.Firegred.Christmas;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Presentgiving implements Listener {
	
	public Main plugin;
	
	public Presentgiving(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void playerjoin(final PlayerJoinEvent event) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				
		
				
		if(plugin.getConfig().getString("Christmasgifts").equals("true")) {
			if(plugin.getConfig().getString("Players." + event.getPlayer().getName()) == null) {
				plugin.getConfig().set("Players." + event.getPlayer().getName(), "yes");
				plugin.getConfig().options().copyDefaults(true);
				plugin.saveConfig();
				plugin.reloadConfig();	
				Player p = event.getPlayer();
				p.sendMessage(ChatColor.GREEN + "Happy Holidays! Here's a present!");
				
				ItemStack present = new ItemStack(Material.ENDER_CHEST, 1);
				ItemMeta presentMeta = present.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				presentMeta.setDisplayName(ChatColor.GREEN + "Present for: " + ChatColor.YELLOW + p.getName());
				lore.add(ChatColor.GOLD + "Right-Click to open!");
				presentMeta.setLore(lore);
				present.setItemMeta(presentMeta);
				p.getInventory().addItem(present);
			}
		}
			}
		}, 25L);
	}

}
