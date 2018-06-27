package me.Firegred.Kidnapper.PlayerFunctions;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.GameMechanics.Game;

public class PlayerBasicInteractions implements Listener{
	
	public Main plugin;
	
	public PlayerBasicInteractions(Main instance) {
		plugin = instance;
	}
	public void mainLobbyTeleport(Player player) {
		String lob = Main.lobpath;
		if(Game.mainlobbyTeleport.get(lob).equals(null)) {
		int x = plugin.getConfig().getInt(lob + "X");
		int y = plugin.getConfig().getInt(lob + "Y");
		int z = plugin.getConfig().getInt(lob + "Z");
		Location location = new Location(player.getWorld(), x, y, z);
		player.teleport(location);
		Game.mainlobbyTeleport.put(lob, location);
		}
		else {
			player.teleport(Game.mainlobbyTeleport.get(lob));
		}
		
	}
	@EventHandler
	public void noUproot(PlayerInteractEvent event)
	{
	    if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL) {
	        event.setCancelled(true);
	    }
	}
	@EventHandler
	public void weather(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void spawning(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void die(EntityDeathEvent event) {
		event.getDrops().clear();
	}
	@EventHandler
	public void Breaks(BlockBreakEvent event) {
		if(!event.getPlayer().hasMetadata("Build")) {
			if(!event.getPlayer().hasMetadata("Sign")) {
			event.setCancelled(true);
			}
			
		}
	}
	@EventHandler
	public void prace(BlockPlaceEvent event) {
	if(!event.getPlayer().hasMetadata("Build")) {
		event.getPlayer().updateInventory();
		try {
			if(!event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Captured Sack")) {
			   event.setCancelled(true);
}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			if(!event.getPlayer().hasMetadata("Build")) {
				event.setCancelled(true);
			}
		}
	}
	}
    @EventHandler
    public void pr(BlockDamageEvent event) {
    	if(!event.getPlayer().hasMetadata("Build")) {
			event.setCancelled(true);
	}
    }
	@EventHandler
	public void d(BlockBurnEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void drops(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void click(InventoryClickEvent event) {
		if(!event.getWhoClicked().hasMetadata("Build")) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void damages(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(event.getCause().equals(DamageCause.FALL)) {
				event.setCancelled(true);
			}
			if(event.getCause().equals(DamageCause.VOID)) {
				if(!p.hasMetadata("Games") || !p.hasMetadata("Citizen") || !p.hasMetadata("Kidnapper")) {
					mainLobbyTeleport(p);
					event.setCancelled(true);
				}
			}
			if(event.getCause().equals(DamageCause.FIRE)) {
				event.setCancelled(true);
			}
			if(event.getCause().equals(DamageCause.LAVA)) {
				event.setCancelled(true);
			}
			if(event.getCause().equals(DamageCause.SUFFOCATION)) {
				event.setCancelled(true);
			}
			if(event.getCause().equals(DamageCause.DROWNING)) {
				event.setCancelled(true);
			}
			
			
		}
	}
	@EventHandler
	public void damaging(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) {
			event.setCancelled(true);
		}
		
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(!p.hasMetadata("Start")) {
				event.setCancelled(true);
			}
			if(!p.hasMetadata("Games")) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void hunger(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void pick(PlayerPickupItemEvent event) {
		if(!event.getPlayer().hasMetadata("Build")) {
		   // 
			event.setCancelled(true);
		    }
		//}
	}
}