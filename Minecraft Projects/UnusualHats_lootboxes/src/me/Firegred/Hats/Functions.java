package me.Firegred.Hats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.sun.xml.internal.stream.Entity;

public class Functions implements Listener{
	
	public Hats plugin;
	
	public Functions(Hats instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void use(PlayerInteractEvent event) {
		try {
			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Player p = event.getPlayer();
				if(p.getItemInHand() != null) {
			   
				if(p.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.BLUE + "Water-Breathing")) {
					if(p.getInventory().getHelmet() == null) {
					event.setCancelled(true);
					ItemStack item = p.getItemInHand();
					p.getInventory().setHelmet(item);
					p.getInventory().remove(item);
					p.updateInventory();
					p.sendMessage(ChatColor.GREEN + "Have fun diving!");
					}
					else {
						event.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You need your helmet spot free!");
					}
				}
				}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
		}
		

	
	
	@EventHandler
	public void helm(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(event.getCause().equals(DamageCause.DROWNING)) {
				if(p.getInventory().getHelmet() != null) {
					if(p.getInventory().getHelmet().getItemMeta().getDisplayName().contains(ChatColor.BLUE + "Water-Breathing")) {
					event.setCancelled(true);
					}
				}
				
			}
		}
	}

}
