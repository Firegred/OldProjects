package me.Firegred.Kidnapper.GameMechanics;

import me.Firegred.Kidnapper.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Citizen implements Listener{
	
	public Main plugin;
	
	public Citizen(Main instance) {
		plugin = instance;
	}

	@EventHandler
	public void teamss(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
		Player p = (Player) event.getEntity();
		if(p.hasMetadata("Start")) {
		if(p.hasMetadata("Citizen")) {
		if(event.getDamager() instanceof Player) {
		Player team = (Player) event.getDamager();
		if(team.hasMetadata("Citizen")) {
		if(p.getMetadata("Citizen").get(0).asString().equals(team.getMetadata("Citizen").get(0).asString())) {
			event.setCancelled(true);
			if(!p.hasMetadata("Captured")) {
			team.sendMessage(ChatColor.RED + "You can't hurt eachother!");
			}
		}
		}
		}
		}
		
	}
		else {
			event.setCancelled(true);
		}
		}
	}

}
