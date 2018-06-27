package me.Firegred.Game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Firegred.Main.Main;



public class MobSpawning extends BukkitRunnable implements Listener {

	private static Main plugin;
	public MobSpawning(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		int ch = r.nextInt(4);
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasMetadata("Game") && !p.hasMetadata("Lobby") && !p.getGameMode().equals(GameMode.SPECTATOR)) {
				p.getInventory().setItem(8, Equipment.setPlayerTracker(p, Equipment.getNearestPlayer(p)));
				p.updateInventory();
				if(ch == 0) {
					 int l1 = r.nextInt(20)-10;
					 int l2 = r.nextInt(20)-10;
					 int x = p.getLocation().getBlockX()+l1;
					 int y = p.getLocation().getBlockY();
					 int z = p.getLocation().getBlockZ()+l2;
					
					 Location l = new Location(Main.w, x, y, z);
					 if(!Main.canSpawn.contains(Main.w.getBlockAt(l).getType())) {
						 for(int i = y; i < 255; i++) {
							 Location tl = new Location(Main.w, x, i, z);
							 if(!Main.canSpawn.contains(Main.w.getBlockAt(tl).getType())) {
								 i++;
							 }
							 else {
						     l = new Location(Main.w, x, i, z);
						     break;
							 }
						 }
					 }
					Sheep s = Main.w.spawn(l, Sheep.class);	
					s.setColor(Main.dye[p.getMetadata("Sheep").get(0).asInt()]);
				}
			}
		}
		
	}
	@EventHandler
	public void spawnmobs(CreatureSpawnEvent e) {
		try {
			if(e.getEntity().getWorld().equals(Main.w)) {
			e.getEntity().getWorld().setTime(0);
			if(!e.getEntityType().equals(EntityType.SHEEP)) {
				e.setCancelled(true);
			}
			}
		} catch (java.lang.NullPointerException e1) {
			// TODO Auto-generated catch block
		}
	}
}
