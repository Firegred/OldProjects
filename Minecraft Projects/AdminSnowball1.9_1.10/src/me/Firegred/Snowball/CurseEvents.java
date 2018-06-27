package me.Firegred.Snowball;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class CurseEvents implements Listener {

	public static Main plugin;
	
	public static ArrayList<String> leavers = new ArrayList<String>();
	public static ArrayList<String> rude = new ArrayList<String>();
	
	public CurseEvents(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void login(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
//		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//			
//			@Override
//			public void run() {
				if(plugin.customConfig.get(p.getName()) != null) {
					CursedAPI.getCursed().put(p, new Cursed(p, 9));
					CursedAPI.getCursed().get(p).runTaskTimer(plugin,1L,1L);
					plugin.customConfig.set(p.getName(), null);
					plugin.saveFrozenPlayersYml(plugin.customConfig, plugin.customYml);
				}
				if(leavers.contains(p.getName())) {
					if(rude.contains(p.getName())) {
						CursedAPI.getCursed().put(p, new Cursed(p, 3));
						CursedAPI.getCursed().get(p).runTaskTimer(plugin,1L,1L);
						rude.remove(p.getName());
					}
					else {
						CursedAPI.getCursed().put(p, new Cursed(p, 2));
						CursedAPI.getCursed().get(p).runTaskTimer(plugin,1L,1L);
					}
					leavers.remove(p.getName());
					PotionEffect pot = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1000);
					p.addPotionEffect(pot);
				}
//				
//			}
//		},1L);
	}
	
	@EventHandler
	public void playerinteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand() != null) {
			if(p.getInventory().getItemInMainHand().hasItemMeta()) {
				if(e.getRightClicked() instanceof Player) {
					Player target = (Player) e.getRightClicked();
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Cursed Wand")) {
					if(p.hasPermission("CursedSnowball.wand.use")) {
					if(!CursedAPI.getCursed().containsKey(target)) {
//						if(p.isOp()) {
//						p.setMetadata("List", new FixedMetadataValue(plugin, "1"));
//						}
//						else {
//						p.setMetadata("List", new FixedMetadataValue(plugin, "0"));	
//						}
						if(target.isOp()) {
						CursedAPI.getCursed().put(target, new Cursed(target,1));
						}
						else {
						CursedAPI.getCursed().put(target, new Cursed(target,0));	
						}
						CursedAPI.getCursed().get(target).runTaskTimer(plugin,1L,1L);
						PotionEffect pot = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1000);
						target.addPotionEffect(pot);
						//p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1f, 0.5f);
				    	target.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 0.5f, 0.5f);
				    	p.sendMessage(ChatColor.RED + "You cursed " + ChatColor.YELLOW + target.getName());
					}
					}
					}
					if(p.hasPermission("CursedSnowball.wand.use")) {
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Cure Wand")) {
					if(CursedAPI.getCursed().containsKey(target)) {
						CursedAPI.getCursed().get(target).cancel();
						CursedAPI.getCursed().remove(target);
						target.removePotionEffect(PotionEffectType.BLINDNESS);
						for(int i = 0; i < 100; i++) {
							target.sendMessage(" ");
						}
						target.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1.0f);
						p.sendMessage(ChatColor.GREEN + "You cured " + ChatColor.YELLOW + target.getName());
					}
				}
				}
				}
			}
		}
	}
	
	@EventHandler
	public void loggingOff(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(CursedAPI.getCursed().containsKey(p)) {
			leavers.add(p.getName());
			if(CursedAPI.getCursed().get(p).isTalking()) {
				rude.add(p.getName());
			}
			CursedAPI.getCursed().get(p).cancel();
			CursedAPI.getCursed().remove(p);
		}
	}
	
	@EventHandler
	public void dead(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if(CursedAPI.getCursed().containsKey(p)) {
			CursedAPI.getCursed().get(p).clearMessage();
			CursedAPI.getCursed().get(p).defineSpaces();
			if(CursedAPI.getCursed().get(p).isTalking()) {
				CursedAPI.getCursed().get(p).setTalking(false);
				CursedAPI.getCursed().get(p).setDeadInterruption(true);
			}
			else {
				CursedAPI.getCursed().get(p).setRespawn(true);
			}
		}
		
	}
	
	@EventHandler
	public void respawning(PlayerRespawnEvent e) {
		final Player p = e.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(CursedAPI.getCursed().containsKey(p)) {
					if(CursedAPI.getCursed().get(p).isDeadInterruption()) {
						CursedAPI.getCursed().get(p).setDeadInterruption(false);
						CursedAPI.getCursed().get(p).setList(6);
						CursedAPI.getCursed().get(p).chooseRandomPhrase();
						CursedAPI.getCursed().get(p).setTalking(true);
					}
					else if(CursedAPI.getCursed().get(p).isRespawn()) {
						CursedAPI.getCursed().get(p).setRespawn(false);
						CursedAPI.getCursed().get(p).setList(5);
						CursedAPI.getCursed().get(p).chooseRandomPhrase();
						CursedAPI.getCursed().get(p).setTalking(true);
					}
					PotionEffect pot = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1000);
					p.addPotionEffect(pot);
					
			}
		}
		},1L);
	}
	
	@EventHandler
	public void commands(PlayerCommandPreprocessEvent e) {
		if(CursedAPI.getCursed().containsKey(e.getPlayer())) {
			e.setCancelled(true);
			if(!CursedAPI.getCursed().get(e.getPlayer()).isTalking()) {
				CursedAPI.getCursed().get(e.getPlayer()).commandsCounter();
				if(CursedAPI.getCursed().get(e.getPlayer()).getCommands() == 5) {
					CursedAPI.getCursed().get(e.getPlayer()).setList(4);
					CursedAPI.getCursed().get(e.getPlayer()).chooseRandomPhrase();
					CursedAPI.getCursed().get(e.getPlayer()).setTalking(true);
				}
			}
		}
	}
	
	@EventHandler 
	public void droppingstuff(PlayerDropItemEvent e) {
		if(CursedAPI.getCursed().containsKey(e.getPlayer())) {
			e.setCancelled(true);
			if(!CursedAPI.getCursed().get(e.getPlayer()).isTalking()) {
				CursedAPI.getCursed().get(e.getPlayer()).throwingCounter();
				if(CursedAPI.getCursed().get(e.getPlayer()).getThrowing() == 10) {
					CursedAPI.getCursed().get(e.getPlayer()).setList(7);
					CursedAPI.getCursed().get(e.getPlayer()).chooseRandomPhrase();
					CursedAPI.getCursed().get(e.getPlayer()).setTalking(true);
				}
			}
		}
	}
	
	@EventHandler
	public void interactions(PlayerInteractEvent e) {
		if(CursedAPI.getCursed().containsKey(e.getPlayer())) {
			e.setCancelled(true);
			if(!CursedAPI.getCursed().get(e.getPlayer()).isTalking()) {
				CursedAPI.getCursed().get(e.getPlayer()).interactCounter();
				if(CursedAPI.getCursed().get(e.getPlayer()).getInteraction() == 25) {
					CursedAPI.getCursed().get(e.getPlayer()).setList(10);
					CursedAPI.getCursed().get(e.getPlayer()).chooseRandomPhrase();
					CursedAPI.getCursed().get(e.getPlayer()).setTalking(true);
				}
			}
		}
	}
	
	@EventHandler
	public void moving(PlayerMoveEvent e) {
		if(CursedAPI.getCursed().containsKey(e.getPlayer())) {
			if(e.getFrom().getBlockX() != e.getTo().getBlockX()) {
				e.getPlayer().teleport(e.getFrom());
			}
			if(e.getFrom().getBlockY() != e.getTo().getBlockY()) {
				e.getPlayer().teleport(e.getFrom());
			}
			if(e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
				e.getPlayer().teleport(e.getFrom());
			}
			if(!CursedAPI.getCursed().get(e.getPlayer()).isTalking()) {
				CursedAPI.getCursed().get(e.getPlayer()).moveCounter();
				if(CursedAPI.getCursed().get(e.getPlayer()).getMoves() == 20) {
					CursedAPI.getCursed().get(e.getPlayer()).setList(11);
					CursedAPI.getCursed().get(e.getPlayer()).chooseRandomPhrase();
					CursedAPI.getCursed().get(e.getPlayer()).setTalking(true);
				}
			}
		}
	}
	
	@EventHandler
	public void throwingCurse(ProjectileLaunchEvent e) {
		if(e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			if(p.getInventory().getItemInMainHand() != null) {
				if(p.getInventory().getItemInMainHand().hasItemMeta()) {
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Cursed snowball")) {
						if(p.hasPermission("CursedSnowball.ball.use")) 
						e.getEntity().setMetadata("Curse", new FixedMetadataValue(plugin, "yes"));
					}
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "The Cure")) {
						if(p.hasPermission("CursedSnowball.egg.use")) 
						e.getEntity().setMetadata("Cure", new FixedMetadataValue(plugin, "yes"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void throwCurse(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
		if(e.getDamager() instanceof Snowball) {
			Snowball ball = (Snowball) e.getDamager();
			if(ball.hasMetadata("Curse")) {
				if(!CursedAPI.getCursed().containsKey(p)) {
//					if(p.isOp()) {
//					p.setMetadata("List", new FixedMetadataValue(plugin, "1"));
//					}
//					else {
//					p.setMetadata("List", new FixedMetadataValue(plugin, "0"));	
//					}
					if(p.isOp()) {
					CursedAPI.getCursed().put(p, new Cursed(p,1));
					}
					else {
					CursedAPI.getCursed().put(p, new Cursed(p,0));	
					}
					CursedAPI.getCursed().get(p).runTaskTimer(plugin,1L,1L);
					PotionEffect pot = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1000);
					p.addPotionEffect(pot);
					//p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1f, 0.5f);
			    	p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1f, 0.5f);
				}
			}
		}
		if(e.getDamager() instanceof Egg) {
			Egg ball = (Egg) e.getDamager();
			if(ball.hasMetadata("Cure")) {
				if(CursedAPI.getCursed().containsKey(p)) {
					CursedAPI.getCursed().get(p).cancel();
					CursedAPI.getCursed().remove(p);
					p.removePotionEffect(PotionEffectType.BLINDNESS);
					for(int i = 0; i < 100; i++) {
						p.sendMessage(" ");
					}
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
				}
			}
		}
		}
	}
}
