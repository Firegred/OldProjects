package me.Firegred.Game;

import java.util.ArrayList;
import java.util.Random;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import me.Firegred.Main.Main;

public class Drop implements Listener{

	private static Main plugin;
	
	@SuppressWarnings("static-access")
	public Drop(Main instance) {
        this.plugin = instance; //now you can access plugin
        
        
}
	
	public static int chest = 10;
	public static int bones = 35;
	
	public static ItemStack chooseOPitem(Player p) {
		Random r = new Random();
	    int ch = r.nextInt(Main.OPItems.size());
	    if(Main.OPItems.get(ch).getType().equals(Material.BOW)) {
	    	p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
	    }
		return Main.OPItems.get(ch);
	}
	
	public static boolean BonesChance() {
		Random r = new Random();
		int ch = r.nextInt(100);
		if(ch < bones) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean ChestChance() {
		Random r = new Random();
		int ch = r.nextInt(100);
		if(ch < chest) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void dropBones(Player p) {
		if(p.hasMetadata("Game")) {
			p.sendMessage(ChatColor.GREEN + "You salvaged some of the sheep's bones!");
			p.getInventory().addItem(Equipment.bones());
			p.updateInventory();
		}
	}
	
	@EventHandler
	public void preventItemDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity().getWorld().equals(Main.w)) {
			if(e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				if(p.hasMetadata("Game")) {
					if(p.getItemInHand() != null) {
						p.setItemInHand(p.getItemInHand());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void Sheeps(EntityDeathEvent e) {
		if(e.getEntity().getWorld().equals(Main.w)) {
		try {
			if(e.getEntity() instanceof Sheep) {
				e.getDrops().clear();
				e.setDroppedExp(0);
				if(e.getEntity().getKiller().hasMetadata("Game")) {
				Player p = e.getEntity().getKiller();
				if(p.getItemInHand() != null) {
					p.setItemInHand(p.getItemInHand());
				}
			    if(BonesChance()) {
				 	dropBones(p);
				}
			    if(Enchanting.ArmorChanceProtection() && Equipment.hasArmor(p)) {
			    	Enchanting.EnchantArmor(Enchanting.ChooseRandomArmor(p.getInventory().getArmorContents()));
			    }
			    if(Enchanting.AxeChanceSharpness()) {
			    	Enchanting.enchantAxeSharpness(p.getItemInHand());
			    }
			    }
			}
		} catch (java.lang.NullPointerException e1) {
			// TODO Auto-generated catch block
		}
		}
	}
	
	@EventHandler
	public void destroyChest(BlockBreakEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
			if(e.getPlayer().hasMetadata("Game")) {
			if(e.getBlock().getType().equals(Material.CHEST)) {
				e.setCancelled(true);
				e.getPlayer().getWorld().getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
			}
		}
		}
	}
	
	public static void placeChest(final Player p, final Block b) {
		if(p.hasMetadata("Game")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
					int i = Equipment.chooseItem(p);
					final Location l = b.getLocation();
					final World w = p.getWorld();
					ItemStack item = new ItemStack(Material.STICK);
					boolean dropping = true;
					switch (i) {
					case 1:
						if(!Equipment.hasMaxAxe(p)) {
						item = Equipment.upgradeAxe(p,plugin);
			            dropping = true;
						}
						else {
                        item = chooseOPitem(p);
                        dropping = true;
						}
						break;
					case 2:
						if(!Equipment.hasMaxHelm(p)) {
						item = Equipment.upgradeHelmet(p,plugin);
						dropping = false;
						}
						else {
						item = chooseOPitem(p);
						dropping = true;
						}
						break;
					case 3:
						if(!Equipment.hasMaxChestPlate(p)) {
						item = Equipment.upgradeChestPlate(p,plugin);
						dropping = false;
						}
						else {
						item = chooseOPitem(p);
						dropping = true;
						}
						break;
					case 4:
						if(!Equipment.hasMaxLeggings(p)) {
						item = Equipment.upgradeLeggings(p,plugin);
						dropping = false;
						}
						else {
						item = chooseOPitem(p);
						dropping = true;
						}
						break;
					case 5:
						if(!Equipment.hasMaxBoots(p)) {
						item = Equipment.upgradeBoots(p,plugin);
						dropping = false;
						}
						else {
						item = chooseOPitem(p);
						dropping = true;
						}
						break;

					default:
						break;
					}
					final ItemStack drop = item;
					if(dropping) {
					p.sendMessage(Main.trees + ChatColor.GREEN + "The tree dropped a chest for you!");
					w.getBlockAt(l).setType(Material.CHEST);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						
						@Override
						public void run() {
							if(w.getBlockAt(l).getState() instanceof Chest) {
								Chest chest = (Chest) w.getBlockAt(l).getState();
								chest.getInventory().addItem(drop);
								
							}
							else {
								Main.w.dropItemNaturally(l, drop);
							}
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									if(w.getBlockAt(l).getState() instanceof Chest) {
										w.getBlockAt(l).setType(Material.AIR);
									}
								}
							},20*10L);
							
						}
					}, 2L);
					}
					else if(!dropping) {
						p.sendMessage(Main.trees + ChatColor.GREEN + "The tree upgraded your armor!");
						switch(i) {
						case 2: {
							p.getInventory().setHelmet(item);
							break;
						}
						case 3: {
							p.getInventory().setChestplate(item);
							break;
						}
						case 4: {
							p.getInventory().setLeggings(item);
							break;
						}
						case 5: {
							p.getInventory().setBoots(item);
							break;
						}
						default: {
							break;
						}
						}
					}
					
				}
			},1L);
		
			
			
		}
	}
	
}
