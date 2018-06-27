package me.Firegred.Hats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;





import net.minecraft.server.v1_8_R1.EnumParticle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.avaje.ebeaninternal.server.deploy.BeanDescriptor.EntityType;

public class Drugs implements Listener {
	
	public Hats plugin;
	public static final HashMap<Player, Integer> Coke = new HashMap<Player, Integer>();
	public static final ArrayList<Player> lsd = new ArrayList<Player>();
	public static final ArrayList<Player> roof = new ArrayList<Player>();
	public static final HashMap<Player, ItemStack> items = new HashMap<Player, ItemStack>();
	
	
	public Drugs(Hats instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void close(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		if(roof.contains(player)) {
			roof.remove(player);
		}
	}
	
	@EventHandler
	public void opens(InventoryClickEvent event) {
		Player p =  (Player) event.getWhoClicked();
		if(roof.contains(p) && !event.getInventory().getName().equals(ChatColor.GOLD + "Pick the food item!")) {
			event.setCancelled(true);
		}
		if(roof.contains(p) && event.getInventory().getName().equals(ChatColor.GOLD + "Pick the food item!")) {
			
			items.put(p, event.getCurrentItem());
			for(ItemStack g : p.getInventory().getContents()) {
				
			
			if(p.getInventory().contains(items.get(p))) {
			    if(g.getAmount() == 1) {
			        p.getInventory().remove(g);
			    }
			    else {
			       p.getInventory().removeItem(g);
			       g.setAmount(g.getAmount() - 1);
			       p.getInventory().addItem(g);
			       
			       
			      
			       
			       
			    }
				ItemStack Item = new ItemStack(items.get(p).getType(), 1);
				Item.getItemMeta().setDisplayName(event.getCurrentItem().getItemMeta().getDisplayName() + "\\u222");
				items.remove(p);
				p.getInventory().addItem(Item);
				System.out.println("Test");
				if(p.getItemInHand().getAmount() == 1) {
					p.getInventory().remove(p.getItemInHand());
				}
				else {
				p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
				p.closeInventory();
				roof.remove(p);
				
				break;
				}
			}
			}
			
		}
	}
	@EventHandler
	public void trip(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(lsd.contains(player)) {
			
			
			
			
							
		
				
				
			
			
		}
	}
	
	@EventHandler
	public void dr(PlayerInteractEvent event) {
		try {
			final Player player = event.getPlayer();
			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Weed")) {
				event.setCancelled(true);
				ItemStack item = player.getInventory().getItemInHand();
				int am = player.getInventory().getItemInHand().getAmount();
				if(am == 1) {
					player.getInventory().remove(item);
				}
				else {
					item.setAmount(am - 1);
					
				}
				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120*20, 2));
			}
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Cocaine")) {
				event.setCancelled(true);
				ItemStack item = player.getInventory().getItemInHand();
				int am = player.getInventory().getItemInHand().getAmount();
				if(am == 1) {
					player.getInventory().remove(item);
				}
				else {
					item.setAmount(am - 1);
					
				}
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 150*20, 3));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 150*20, 0));
				if(!Coke.containsKey(player)) {
					Coke.put(player, 1);
					System.out.println("Adding player works!");
				}
				else if(Coke.containsKey(player)) {
					Coke.put(player, Coke.get(player) + 1);
					System.out.println("Adding 2 player works!");
					if(Coke.get(player) == 2) {
						player.sendMessage(ChatColor.RED + "If you take another dose, you'll die from an overdose!");
						
					}
					else if(Coke.get(player) == 3) {
					     player.damage(100);
					     player.sendMessage(ChatColor.RED + "You died from an overdose!");
					     Coke.remove(player);
					}	
				}
				
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						if(Coke.containsKey(player)) {
							Coke.remove(player);
							player.sendMessage(ChatColor.GREEN + "You are now able to take more doses!");
						}
					}
				}, 150*20L);
				
			}
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Roofie")) {
	    		 Inventory rawr = Bukkit.getServer().createInventory(null, 45, ChatColor.GOLD + "Pick the food item!");
	    		 roof.add(player);
	    		 for(ItemStack g : player.getInventory().getContents()) {
	    			if(g.getTypeId() != 0) {
	    			 rawr.addItem(g);
	    			}
	    		 }
	    		 player.openInventory(rawr);
	    		 
	    	}
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "LSD")) {
				if(!lsd.contains(player)) {
					event.setCancelled(true);
					lsd.add(player);
					player.sendMessage(ChatColor.GREEN + "Enjoy the 10 minute trip");
					ItemStack item = player.getInventory().getItemInHand();
					int am = player.getInventory().getItemInHand().getAmount();
					
					
					if(am == 1) {
						player.getInventory().remove(item);
					}
					else {
						item.setAmount(am - 1);
						
					}
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							if(lsd.contains(player)) {
								lsd.remove(player);
								player.sendMessage(ChatColor.GREEN + "Your trip is over!");
							}
						}
					}, 10*60*20L);
			}
				else {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED + "You're already on an LSD trip!");
				}
			}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
		
		}
	}
	@EventHandler
	public void Weeds(BlockPlaceEvent event) {
		try {
			Player player = event.getPlayer();
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Weed")) {
				event.setCancelled(true);
			}
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "LSD")) {
				event.setCancelled(true);
			}
			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED+ "Roofie")) {
				event.setCancelled(true);
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
		
		}
	
	}
}
