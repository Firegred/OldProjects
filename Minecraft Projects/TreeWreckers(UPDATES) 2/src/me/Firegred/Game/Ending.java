package me.Firegred.Game;

import java.util.HashMap;
import java.util.Random;

import me.Firegred.Lobby.Teleports;
import me.Firegred.Lobby.Waiting;
import me.Firegred.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;

public class Ending implements Listener{
	
	public static Main plugin;
	
	public Ending(Main instance) {
		plugin = instance;
	}
	
	public void quitGame(Player p) {
		for(String g : Main.data) {
			 if(p.hasMetadata(g)) {
				 p.removeMetadata(g, plugin);
			 }
		 }
		p.setMetadata("Lobby", new FixedMetadataValue(plugin, Main.w));
	}
	
	public void destroyStuff(String name) {
		int x1 = plugin.getConfig().getInt(Main.games + "." + name + ".Region.X1");
		int y1 = plugin.getConfig().getInt(Main.games + "." + name + ".Region.Y1");
	    int z1 = plugin.getConfig().getInt(Main.games + "." + name + ".Region.Z1");
	    
	    int x2 = plugin.getConfig().getInt(Main.games + "." + name + ".Region.X2");
		int y2 = plugin.getConfig().getInt(Main.games + "." + name + ".Region.Y2");
	    int z2 = plugin.getConfig().getInt(Main.games + "." + name + ".Region.Z2");
	    treeDestroy(name, x1,y1,z1,x2,y2,z2);
	    
	}
	public static void resetGame(String name) {
		int x = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.X");
		int y = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Y");
		int z = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Z");
		int max = plugin.getConfig().getInt(Main.games + "." + name + ".Max");
		Location l = new Location(Main.w, x,y,z);
		Sign s = (Sign) Main.w.getBlockAt(l).getState();
		s.setLine(1, ChatColor.GREEN + "[Join]");
		s.setLine(2, "0" + " / " + max);
		s.update();
	}
	private static void treeDestroy(String name, int x1, int y1, int z1, int x2, int y2, int z2) {
//		int x1 = m.getConfig().getInt("Loot." + name + ".x1");
//		int y1 = m.getConfig().getInt("Loot." + name + ".y1");
//		int z1 = m.getConfig().getInt("Loot." + name + ".z1");
//		
//		int x2 = m.getConfig().getInt("Loot." + name + ".x2");
//		int y2 = m.getConfig().getInt("Loot." + name + ".y2");
//		int z2 = m.getConfig().getInt("Loot." + name + ".z2");
//		Random random = new Random();
		int x = 0;
		int y = 0;
		int z = 0;
		
		int px1 =0, px2=0, py1=0,py2=0,pz1=0,pz2 = 0;
		if(x1>=x2) {
			//x = random.nextInt(Math.abs(x1-x2)+1)+x2;
			px1=x1;
			px2=x2;
		}
		else if(x2>x1) {
			//x = random.nextInt(Math.abs(x2-x1)+1)+x1;
			px1=x2;
			px2=x1;
		}
		if(y1>=y2) {
			//y = random.nextInt(Math.abs(y1-y2)+1)+y2;
			py1=y1;
			py2=y2;
		}
		else if(y2>y1) {
			//y = random.nextInt(Math.abs(y2-y1)+1)+y1;
			py1=y2;
			py2=y1;
		}
		if(z1>=z2) {
			//z = random.nextInt(Math.abs(z1-z2)+1)+z2;
			pz1=z1;
			pz2=z2;
		}
		else if(z2>z1) {
			//z = random.nextInt(Math.abs(z2-z1)+1)+z1;
			pz1=z2;
			pz2=z1;
		}
		HashMap<Location, Entity> sheeps = new HashMap<Location, Entity>();
		for(Entity i : Main.w.getEntities()) {
			int xs = i.getLocation().getBlockX();
			int ys = i.getLocation().getBlockY();
			int zs = i.getLocation().getBlockZ();
			Location l = new Location(Main.w,xs,ys,zs);
			sheeps.put(l, i);
		}
		
		for(int xx = px2; xx <= px1; xx++) {
			for(int yy = py2; yy <= py1; yy++) {
				for(int zz = pz2; zz <= pz1; zz++) {
					Location ll = new Location(Main.w, xx, yy, zz);
					if(sheeps.containsKey(ll)) {
						sheeps.get(ll).remove();
					}
					if(!ll.getBlock().getType().equals(Material.AIR)) {
						if(Main.canReplace.contains(Main.w.getBlockAt(ll).getType())) {
						Main.w.getBlockAt(ll).setType(Material.AIR);
						}
						else if(ll.getBlock().getState() instanceof Chest) {
						Main.w.getBlockAt(ll).setType(Material.AIR);	
						}
						if(ll.getBlock().getType().equals(Material.DIRT)) {
						Location ll2 = new Location(Main.w, xx, yy+1,zz);
						if(Main.w.getBlockAt(ll2).getType().equals(Material.AIR) || Main.canReplace.contains(Main.w.getBlockAt(ll2).getType())) {
						Main.w.getBlockAt(ll).setType(Material.GRASS);
						}
						}
					}
					
					
				}
			}
		}
		sheeps.clear();
	}
//	@EventHandler
//	public void respawn(PlayerRespawnEvent e) {
//	   if(e.getPlayer().hasMetadata("Spectator")) {
//		  final Player p = e.getPlayer();
//		   Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//			
//			@Override
//			public void run() {
//			 p.teleport(Bukkit.getPlayer(p.getMetadata("Spectator").get(0).asString()));
//             p.sendMessage(Main.trees + ChatColor.GREEN + "You are now spectating");
//			}
//		},2L);
//	   }
//	}
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
			Player p = e.getPlayer();
			if(p.hasMetadata("Game") && !p.hasMetadata("Lobby")) {
				final String g = p.getMetadata("Game").get(0).asString();
				String winner = "cool";
				int on = 0;
				boolean ending = false;
				//p.setMetadata("Spectator", new FixedMetadataValue(plugin, p.getKiller().getName()));
				for(Player c : Bukkit.getOnlinePlayers()) {
					if(c.hasMetadata("Game")) {
						if(c.getMetadata("Game").get(0).asString().equals(g)) {
							c.sendMessage(Main.trees + ChatColor.RED + p.getName() + ChatColor.GOLD + " left");
							if(!c.equals(p)&& !c.getGameMode().equals(GameMode.SPECTATOR)) {
								on++;
								}
							
						}
					}
				}
				for(Player c : Bukkit.getOnlinePlayers()) {
					if(c.hasMetadata("Game")) {
						if(c.getMetadata("Game").get(0).asString().equals(g)) {
							if(!c.equals(p) && on == 1 && !c.getGameMode().equals(GameMode.SPECTATOR)) {
								winner = c.getName();
							}
						}
					}
				}
				if(on == 1) {
				ending = true;
				}
				for(Player c : Bukkit.getOnlinePlayers()) {
					if(c.hasMetadata("Game")) {
						if(c.getMetadata("Game").get(0).asString().equals(g)) {
							if(on == 1) {
							   quitGame(c);
							   c.getInventory().clear();
							   c.setHealth(20);
							   c.setFoodLevel(20);
							   c.setLevel(0);
							   removeEffects(c);
							   Equipment.removeArmor(c);
							   c.setGameMode(GameMode.SURVIVAL);
							   c.sendMessage(Main.trees + ChatColor.YELLOW + winner + ChatColor.GREEN + " has won the game!");
							   c.setMetadata("Lobby", new FixedMetadataValue(plugin, Main.w));
							   Teleports.ServerLobbyTeleport(c); 
							}
							else {
								c.sendMessage(Main.trees + ChatColor.RED + on + ChatColor.GREEN +" players remain!");
							}
							
						}
					}
				}
				if(ending == true) {
					resetGame(g);
					destroyStuff(g);
				}
			}
		}
	}
	
	public static void removeEffects(Player p) {
		for (PotionEffect effect : p.getActivePotionEffects()) {
	        p.removePotionEffect(effect.getType());
		}
	}
	@EventHandler
	public void death(EntityDamageEvent e) {
		if(e.getEntity().getWorld().equals(Main.w) && e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getHealth()-e.getFinalDamage() <= 0) {
				e.setCancelled(true);
				if(p.hasMetadata("Game") && !p.hasMetadata("Lobby")) {
				final String g = p.getMetadata("Game").get(0).asString();
				String winner = "cool";
				int on = 0;
				boolean ending = false;
				for(Player c : Bukkit.getOnlinePlayers()) {
					if(c.hasMetadata("Game")) {
						if(c.getMetadata("Game").get(0).asString().equals(g)) {
							c.sendMessage(Main.trees + ChatColor.RED + p.getName() + ChatColor.GOLD + " was slain! ");
							if(!c.equals(p) && !c.getGameMode().equals(GameMode.SPECTATOR)) {
							on++;
							}
							
						}
					}
				}
				for(Player c : Bukkit.getOnlinePlayers()) {
					if(c.hasMetadata("Game")) {
						if(c.getMetadata("Game").get(0).asString().equals(g)) {
							if(!c.equals(p) && on == 1 && !c.getGameMode().equals(GameMode.SPECTATOR)) {
								winner = c.getName();
							}
						}
					}
				}
				if(on == 1) {
					ending = true;
				}
				for(Player c : Bukkit.getOnlinePlayers()) {
					if(c.hasMetadata("Game")) {
						if(c.getMetadata("Game").get(0).asString().equals(g)) {
							if(on == 1) {
							   quitGame(c);
							   c.getInventory().clear();
							   c.setHealth(20);
							   c.setFoodLevel(20);
							   c.setLevel(0);
							   removeEffects(c);
							   Equipment.removeArmor(c);
							   c.setGameMode(GameMode.SURVIVAL);
							   c.sendMessage(Main.trees + ChatColor.YELLOW + winner + ChatColor.GREEN + " has won the game!");
							   c.setMetadata("Lobby", new FixedMetadataValue(plugin, Main.w));
							   Teleports.ServerLobbyTeleport(c); 
							}
							else {
								c.sendMessage(Main.trees + ChatColor.RED + on + ChatColor.GREEN +" players remain!");
							}
							
						}
					}
				}
				if(ending == true) {
					resetGame(g);
					destroyStuff(g);
				}
				else {
					p.setGameMode(GameMode.SPECTATOR);
					p.teleport(Bukkit.getPlayer(p.getMetadata("Spectator").get(0).asString()));
		             p.sendMessage(Main.trees + ChatColor.GREEN + "You are now spectating");
				}
				
				
			}
		}
		}
	}

}
