package me.Firegred.Kidnapper.GameMechanics;

import java.util.ArrayList;
import java.util.Random;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.LobbyFunctions.GameLobbyTeleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Kidnapper implements Listener{

	public Main plugin;
	
	public Kidnapper(Main instance) {
		plugin = instance;
	}
	
	
//	@EventHandler
//	public void jailed(PlayerRespawnEvent event) {
//		Player p = event.getPlayer();
//		if(p.hasMetadata("killed")) {
//			jailteleport(p);
//			p.sendMessage(ChatColor.RED + "The citizens sent you to justice!");
//			//p.removeMetadata("Kidnapper", plugin);
//		}
//	}
	
	
	@EventHandler
	public void teams(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
		Player p = (Player) event.getEntity();
		if(p.hasMetadata("Start")) {
		if(p.hasMetadata("Kidnapper")) {
		if(event.getDamager() instanceof Player) {
		Player team = (Player) event.getDamager();
		if(team.hasMetadata("Kidnapper")) {
		if(p.getMetadata("Kidnapper").get(0).asString().equals(team.getMetadata("Kidnapper").get(0).asString())) {
			event.setCancelled(true);
			team.sendMessage(ChatColor.RED + "You can't hurt eachother!");
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
	
	
	@EventHandler
	public void playerkidnap(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
		Player tar = (Player) event.getEntity();
		
		if(tar.hasMetadata("Citizen")) {
			if(event.getDamager() instanceof Player) {
				final Player p = (Player) event.getDamager();
				if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Sack") && p.hasMetadata("Kidnapper")) {
					    Random chance = new Random();
					    int chances = chance.nextInt(4);
					    event.setCancelled(true);
					    if(chances == 0 || chances == 2 || chances == 3) {
					    	p.sendMessage(ChatColor.RED + "You failed to kidnap the citizen!");
					    	p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
					    	tar.playSound(tar.getLocation(), Sound.VILLAGER_HIT, 1, 1);
					    	tar.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.RED + " tried to capture you!");
					    }
					    if(chances == 1 && !p.hasMetadata("Wait")) {
					//if(tar.hasMetadata("Citizen")) {
					    	p.setMetadata("Wait", new FixedMetadataValue(plugin, "yes"));
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.removeMetadata("Wait", plugin);								}
							}, 30L);
					    tar.setMetadata("Captured", new FixedMetadataValue(plugin, p.getName()));
						p.setMetadata("Capturer", new FixedMetadataValue(plugin, tar.getName()));
						tar.sendMessage(ChatColor.RED + "You were captured by a kidnapper!");
						tar.hidePlayer(p);
						if(!tar.getGameMode().equals(GameMode.SPECTATOR)) {
							tar.setGameMode(GameMode.SPECTATOR);
						}
						
						ItemStack sack = new ItemStack(Material.CHEST, 1);
						ItemMeta sackmeta = sack.getItemMeta();
						ArrayList<String> sacklore = new ArrayList<String>();
						sacklore.clear();
						sacklore.add(ChatColor.RED + tar.getName() + ChatColor.GREEN + " is in this sack!");
						sackmeta.setDisplayName(ChatColor.DARK_GREEN + "Captured Sack");
					    sackmeta.setLore(sacklore);
						sack.setItemMeta(sackmeta);
						p.getInventory().setItem(0, sack);
						tar.teleport(p.getLocation());
						tar.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300000, 2));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30000000, 0));
						for(Player s : Bukkit.getOnlinePlayers()) {
							if(s.hasMetadata("Games")) {
							if(tar.getMetadata("Games").get(0).asString().equals(s.getMetadata("Games").get(0).asString())) {
								s.sendMessage(ChatColor.GOLD + tar.getName() + ChatColor.RED + " was kidnapped!");
								s.hidePlayer(tar);
								if(s.hasMetadata("Kidnapper")) {
									s.playSound(s.getLocation(), Sound.DIG_GRASS, 1, 1);
								}
								if(s.hasMetadata("Citizen")) {
									s.playSound(s.getLocation(), Sound.CAT_HIT, 1, 1);
								}
								
						//	}
						}
					    
							}
						}
					}
				}
		}}
		}
	}
}
