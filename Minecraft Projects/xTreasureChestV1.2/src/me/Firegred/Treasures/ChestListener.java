package me.Firegred.Treasures;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChestListener implements Listener{

	private static Main plugin;
	
	public ChestListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void invChest(InventoryClickEvent event) {
		if(event.getInventory().getName().contains(ChatColor.GREEN + "Treasure ")) {
			event.setCancelled(true);
		}
	}
	
}
