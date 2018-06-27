package me.Firegred.Christmas;

import java.util.ArrayList;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Gifts implements Listener{
	
	public Main plugin;
	
	public static final ArrayList<String> cduser = new ArrayList<String>();
	//public static final ArrayList<Player> snow = new ArrayList<Player>();
	
	public static final ParticleEffect effect1 = ParticleEffect.NOTE;
	public static final ParticleEffect effect2 = ParticleEffect.ENCHANTMENT_TABLE;
	public static final ParticleEffect effect3 = ParticleEffect.DRIP_WATER;
	public static final ParticleEffect effect4 = ParticleEffect.HEART;
	public static final ParticleEffect effect5 = ParticleEffect.CLOUD;
	public static final ParticleEffect effect6 = ParticleEffect.HAPPY_VILLAGER;
	public static final ParticleEffect effect7 = ParticleEffect.TOWN_AURA;
	public static final ParticleEffect effect8 = ParticleEffect.FLAME;
	public static final ParticleEffect effect9 = ParticleEffect.PORTAL;
	public static final ParticleEffect effect10 = ParticleEffect.RED_DUST;
	public static final ParticleEffect effect11 = ParticleEffect.ANGRY_VILLAGER;
	public static final ParticleEffect effect12 = ParticleEffect.LARGE_SMOKE;
    public static final ParticleEffect particles[] = {effect1, effect2, effect3, effect4, effect5, effect6, effect7, effect8, effect9, effect10, effect11, effect12};
	
    public static final ParticleEffect effecter = ParticleEffect.FIREWORKS_SPARK;
    
    public static final PotionEffect pot1 = new PotionEffect(PotionEffectType.SPEED, 20*120, 1);
    public static final PotionEffect pot2 = new PotionEffect(PotionEffectType.NIGHT_VISION, 20*120, 0);
    public static final PotionEffect pot3 = new PotionEffect(PotionEffectType.WATER_BREATHING, 20*120, 0);
    public static final PotionEffect pot4 = new PotionEffect(PotionEffectType.REGENERATION, 20*120, 0);
    public static final PotionEffect pot5 = new PotionEffect(PotionEffectType.JUMP, 20*120, 1);
    public static final PotionEffect pot6 = new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*120, 0);
    public static final PotionEffect pot7 = new PotionEffect(PotionEffectType.ABSORPTION, 20*120, 0);
    public static final PotionEffect pot8 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*120, 0);
    public static final PotionEffect pot9 = new PotionEffect(PotionEffectType.SATURATION, 20*120, 0);
    public static final PotionEffect pot10 = new PotionEffect(PotionEffectType.FAST_DIGGING, 20*120, 0);
    public static final PotionEffect pot11 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*120, 0);
    public static final PotionEffect pot12 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*120, 0);
    public static final PotionEffect effects[] = {pot1, pot2, pot3, pot4, pot5, pot6, pot7, pot8, pot9, pot10, pot11, pot12};
    
	public Gifts(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent event) {
		try {
			if(event.getDamager() instanceof Player) {
				if(!(event.getEntity() instanceof Player)) {
					Player p = (Player) event.getDamager();
					if(p.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "Holiday hatchet")) {
						if(!(event.getEntity() instanceof Snowman)) {
							event.getEntity().getWorld().spawn(event.getEntity().getLocation(), Snowman.class);
							event.getEntity().remove();
							
						}
					}
				}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@EventHandler
	public void particles4(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		try {
			if(p.getItemInHand().getType().equals(Material.SNOW_BALL)) {
			if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "SnowFlake Maker")) {
			     if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {    
				event.setCancelled(true);
			         p.updateInventory();
							double x = p.getLocation().getX();
							double y = p.getLocation().getY() + 1;
							double z = p.getLocation().getZ();
							final Location location1 = new Location(p.getWorld(), x, y, z);
							final Location location2 = new Location(p.getWorld(), x, y, z-1);
							final Location location3 = new Location(p.getWorld(), x, y, z+1);
							final Location location4 = new Location(p.getWorld(), x-1, y, z);
							final Location location5 = new Location(p.getWorld(), x-1, y, z+1);
							final Location location6 = new Location(p.getWorld(), x-1, y, z-1);
							final Location location7 = new Location(p.getWorld(), x+1, y, z);
							final Location location8 = new Location(p.getWorld(), x+1, y, z-1);
							final Location location9 = new Location(p.getWorld(), x+1, y, z+1);
							try {
								ParticleEffect.sendToLocation(effecter, location1, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location2, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location3, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location4, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location5, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location6, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location7, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location8, 0, 1, 0,
						                0, 1);
								ParticleEffect.sendToLocation(effecter, location9, 0, 1, 0,
						                0, 1);
							} catch (Exception e1) {
							
						}
						}
					}
					}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
		}
	

		
	
	
		
				
	
	
	@EventHandler
	public void discs(PlayerInteractEvent event) {
		
		try {
			if(event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Holiday trait")) {
				if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					event.setCancelled(true);
				    final Player p = event.getPlayer();
					if(!cduser.contains(p.getName())) {
						cduser.add(p.getName());
						p.sendMessage(ChatColor.GREEN + "Holiday disc has been activated. Enjoy the effects!");
						for(int i = 1; i < 13; i++) {
							if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Holiday trait #" + ChatColor.GREEN + i + ChatColor.GOLD + " : " + Presentrest.names2[i-1])) {
								final int ccc = i;
								p.addPotionEffect(effects[i-1]);
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										cduser.remove(p.getName());
									}
								}, 20*180);
								for(int c = 0; c < 480; c++) {
									
									
									Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
										@Override
										public void run() {
											if(p.isOnline() && cduser.contains(p.getName())) {
												double x = p.getLocation().getX();
												double y = p.getLocation().getY() + 1;
												double z = p.getLocation().getZ();
												Location location = new Location(p.getWorld(), x, y, z);
												try {
													ParticleEffect.sendToLocation(particles[ccc-1], location, 0, 1, 0,
											                0, 1);
												} catch (Exception e1) {
											}
											}
											
										}
									}, 5L*c);
								}
							}
							
						}
					}
					else {
						p.sendMessage(ChatColor.RED + "Error: You can only use discs once every 3 minutes");
					}
				}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	}

}
