package me.Firegred.Kidnapper.GameMechanics;

import me.Firegred.Kidnapper.Main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Captured implements Listener{
	
	public Main plugin;
	
	public Captured(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void NoMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(p.hasMetadata("killed")) {
			if(!p.getGameMode().equals(GameMode.SPECTATOR)) {
				p.setGameMode(GameMode.SPECTATOR);
			}
		}
		
		if(p.hasMetadata("Chester")) {
			if(!p.getGameMode().equals(GameMode.SPECTATOR)) {
				p.setGameMode(GameMode.SPECTATOR);
			}
			//event.setCancelled(true);
			//p.teleport(Capturing.chestloc.get(p));
		}
		
		if(p.hasMetadata("Captured")) {
			event.setCancelled(true);
			p.teleport(Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString()).getLocation());
			if(!p.getGameMode().equals(GameMode.SPECTATOR)) {
				p.setGameMode(GameMode.SPECTATOR);
			}
		}
		if(p.hasMetadata("Capturer")) {
			Player tar = Bukkit.getPlayer(p.getMetadata("Capturer").get(0).asString());
			tar.teleport(p.getLocation());
		}
		//if(p.get)
	}
	
	@EventHandler
	public void attacks(EntityDamageByEntityEvent event) {
	    if(event.getDamager() instanceof Player) {
	    	Player p = (Player) event.getDamager();
	    	if(p.hasMetadata("Captured") || p.hasMetadata("Chester")) {
	    		event.setCancelled(true);
	    	}
	    }
		
		if(event.getEntity() instanceof Player) {
	    	Player p = (Player) event.getEntity();
	    	if(p.hasMetadata("Captured") || p.hasMetadata("Chester")) {
	    		if(event.getDamager().hasMetadata("Citizen")) {
				Player capt = Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString());
				capt.damage(event.getDamage());
				event.setCancelled(true);
				
	    		}
	    		if(event.getDamager().hasMetadata("Kidnapper")) {
	    			event.setCancelled(true);
	    		}
	    	}
	    }
	}
	@EventHandler
	public void Damaging(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
		Player p = (Player) event.getEntity();
		if(p.hasMetadata("Captured") || p.hasMetadata("Chester")) {
			if(event.getCause().equals(DamageCause.FALL)) {
				event.setCancelled(true);
			}
				
		
		}
		}
	}

	@EventHandler
	public void inter(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(p.hasMetadata("Captured") || p.hasMetadata("Chester")) {
			event.setCancelled(true);
		}
	}
}
