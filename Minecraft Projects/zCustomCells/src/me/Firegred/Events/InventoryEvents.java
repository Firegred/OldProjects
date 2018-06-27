package me.Firegred.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Firegred.Cell.Generation.Expansion;
import me.Firegred.Cell.Generation.Measure;
import me.Firegred.main.Main;

public class InventoryEvents implements Listener{

	public Main plugin;
	
	public InventoryEvents(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void upgrade(InventoryClickEvent e) {
		if(e.getWhoClicked().getWorld().getName().equals(Main.world)) {
			if(e.getInventory().getName().contains(ChatColor.RED +"Cell Options")) {
				if(e.getRawSlot() < e.getView().getTopInventory().getSize()) {
					Player p = (Player) e.getWhoClicked();
					e.setCancelled(true);
					if(e.getSlot()==1) {
						Expansion.upgrade(Measure.LENGTH, p);
						p.closeInventory();
					}
					if(e.getSlot()==2) {
						Expansion.upgrade(Measure.WIDTH, p);
						p.closeInventory();
					}
					if(e.getSlot()==3) {
						Expansion.upgrade(Measure.HEIGHT, p);
						p.closeInventory();
					}
				}
				else e.setCancelled(true);
			}
		}
	}
	
}
