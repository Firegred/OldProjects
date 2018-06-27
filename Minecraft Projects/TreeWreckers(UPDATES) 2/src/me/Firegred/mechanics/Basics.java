package me.Firegred.mechanics;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.Firegred.Lobby.Teleports;
import me.Firegred.Main.Main;

public class Basics implements Listener{
	
	public static Main plugin;
	
	public Basics(Main instance) {
		plugin = instance;
	}

	@EventHandler
	public void clicking(InventoryClickEvent e) {
	 if(e.getInventory().getHolder() instanceof Player) {
		 Player p = (Player) e.getInventory().getHolder();
		 if(p.getWorld().equals(Main.w)) {
			 if(p.hasMetadata("Game")) {
				 if(e.getSlot() == 8) {
					 e.setCancelled(true);
				 }
			 }
		 }
	 }
	}
	@EventHandler
	public void drop(PlayerDropItemEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
		    
			
		    if(e.getItemDrop().getItemStack().getType().equals(Material.GLASS_BOTTLE)) {
				e.getItemDrop().remove();
			}
		    else if(!Main.OPItems.contains(e.getItemDrop().getItemStack())){
		    	e.setCancelled(true);
		    }
			
			}
		
		
	}
	@EventHandler
	public void feed(FoodLevelChangeEvent e) {
		if(e.getEntity().getWorld().equals(Main.w)) {
			e.setCancelled(true);
			e.setFoodLevel(20);
		}
	}
	@EventHandler
	public void build(BlockBreakEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
			if(e.getPlayer().hasMetadata("Lobby") && !e.getPlayer().isOp()) {
			e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void build2(BlockPlaceEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
			if(e.getPlayer().hasMetadata("Lobby") && !e.getPlayer().isOp()) {
			e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void damage(EntityDamageEvent e) {
		if(e.getEntity().getWorld().equals(Main.w)) {
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(p.hasMetadata("Lobby")) {
					e.setCancelled(true);
				}
				if(e.getCause().equals(DamageCause.FALL)) {
					e.setCancelled(true);
				}
				if(e.getCause().equals(DamageCause.VOID)) {
					e.setCancelled(true);
					Teleports.ServerLobbyTeleport(p);
				}
			}
		}
	}
	@EventHandler
	public void time(WeatherChangeEvent e) {
		if(e.getWorld().equals(Main.w)) {
			e.setCancelled(true);
		}
	}
}
