package me.Firegred.Easter;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EggEvents implements Listener{
	
	public Main plugin;
	
	public EggEvents(Main instance) {
		plugin = instance;
	}
	public boolean enable;
	public boolean broadcast;
	public boolean pickup;
    public ArrayList<Player> open = new ArrayList<Player>();
    public ArrayList<Player> openers = new ArrayList<Player>();
	@EventHandler
	public void reward(PlayerLoginEvent event) {
        
		final Player p = event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(plugin.getConfig().get("Easter.rewards") != null) {
					boolean rewards = plugin.getConfig().getBoolean("Easter.rewards");
					if(rewards == true ){
						if(plugin.getConfig().get("Easter.Players." + p.getName()) != null) {
							if(plugin.getConfig().get("Rewards.Players." + p.getName()) == null) {
							    plugin.getConfig().set("Rewards.Players." + p.getName(), "recieved reward");
								plugin.getConfig().options().copyDefaults(true);
								plugin.saveConfig();
								plugin.reloadConfig();
							int num = plugin.getConfig().getInt("Easter.Players." + p.getName());
							if(p.getInventory().firstEmpty() == -1){
								p.sendMessage(ChatColor.RED + "You must have available inventory spots before claiming your easter reward!");
							}
							else {
								ItemStack s = Eggs.chest(p.getName(), num);
								p.getInventory().addItem(s);
								p.updateInventory();
								p.sendMessage(ChatColor.GREEN + "You have recieved a reward chest for participating in the Easter hunt!");
								
							}
							}
						}
					}
				}
			}
		},2L);
	}
	@EventHandler
	public void close(InventoryCloseEvent e) {
		if(e.getInventory().getName().equals(ChatColor.LIGHT_PURPLE + "Easter Rewards")) {
			if(e.getPlayer() instanceof Player) {
			
			Player p = (Player) e.getPlayer();
			if(openers.contains(p)) {
			openers.remove(p);
				p.sendMessage(ChatColor.GREEN + "Your rewards have been placed in your inventory!");
			for(ItemStack i : e.getInventory().getContents()) {
				p.getInventory().addItem(i);
			}
			}
		}
		}
	}
	
	@EventHandler
	public void invrew(InventoryClickEvent e) {
		//if(e.getInventory().getName().equals(ChatColor.LIGHT_PURPLE + "Easter Rewards")) {
			if(e.getWhoClicked() instanceof Player) {
			
				Player p = (Player) e.getWhoClicked();	
				if(openers.contains(p)) {
			e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
			}
			}
		}
//	}
	
	@EventHandler
	public void puton(PlayerInteractEvent e) {
		try {
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				
				Player p = e.getPlayer();
				if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.LIGHT_PURPLE + " Easter Hat")) {
					e.setCancelled(true);
					if(p.getInventory().getHelmet() == null) {
					ItemStack item = p.getInventory().getItemInMainHand();
					p.getInventory().remove(item);
					p.getInventory().setHelmet(item);
					p.updateInventory();
					p.sendMessage(ChatColor.LIGHT_PURPLE + "Happy Easter");
					}
					else {
						p.sendMessage(ChatColor.RED + "You must remove the item from your head!");
					}
				}
				if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.LIGHT_PURPLE + " Easter Rewards:")) {
					e.setCancelled(true);
					if(plugin.getConfig().get("Easter.Players." + p.getName()) != null) {
						if(!open.contains(p)) {
						open.add(p);
						p.sendMessage(ChatColor.GREEN + "You are about to open your reward. Be sure to have space in your inventory!" + ChatColor.RED +"Are you sure you want to open you reward?");
					}
					else {
						open.remove(p);
						if(!openers.contains(p)) {
							openers.add(p);
						}
						Inventory reward = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE + "Easter Rewards");
						for(int i = 0; i < Integer.MAX_VALUE; i++) {
							if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.LIGHT_PURPLE + " Easter Rewards:" + ChatColor.YELLOW + i + " eggs")) {
								int nums = i;
								reward.addItem(Eggs.hat(p.getName()));
								for(int s = 0; s < nums+1; s++) {
									Random rand = new Random();
									int chance = rand.nextInt(101);
									if(chance == 0) {
										reward.addItem(Eggs.key());
									}
									else if(chance<5) {
										reward.addItem(Eggs.foot());
									}
									else if(chance<15) {
										reward.addItem(Eggs.iron());
									}
									else if(chance<30) {
										reward.addItem(Eggs.grass());
									}
									else if(chance<50) {
										reward.addItem(Eggs.goldcarrot());
									}
									else if(chance<70) {
										reward.addItem(Eggs.rabbit());
									}
									else if(chance<85) {
										reward.addItem(Eggs.beet());
									}
									else reward.addItem(Eggs.carrot());
//									if(chance == 1 || chance == 2) {
//										reward.addItem(Eggs.gold());
//									}
//									if(chance == 3 || chance == 4) {
//										reward.addItem(Eggs.iron());
//									}
//									if(chance == 0) {
//										reward.addItem(Eggs.diamond());
//									}
								}
								p.getInventory().remove(p.getInventory().getItemInMainHand());
								p.updateInventory();
								p.openInventory(reward);
								break;
							}
						}
					}
					}
				}
				else if(open.contains(p)) {
					open.remove(p);
				}
				
				}
			
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@EventHandler
	public void throwing(ProjectileLaunchEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(p.getInventory().getItemInMainHand().getItemMeta().equals(Eggs.easter().getItemMeta())) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void login(PlayerLoginEvent event) {
		final Player p = event.getPlayer();
		enable = plugin.getConfig().getBoolean("Easter.enable");
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
//				if(p.hasPermission("Easter.winner")) {
//					p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName());
//				}
				if(enable == true) {
				p.sendMessage(ChatColor.GREEN + "Happy Easter! There's an easter egg hunt going on.");
				}
			}
		},2L);
	}
	@EventHandler
	public void use(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		try {
			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(p.getInventory().getItemInMainHand().getItemMeta().equals(Eggs.easter().getItemMeta())) {
					enable = plugin.getConfig().getBoolean("Easter.enable");
					broadcast = plugin.getConfig().getBoolean("Easter.broadcast");
					pickup = plugin.getConfig().getBoolean("Easter.instantpickup");
					event.setCancelled(true);
					ItemStack i = p.getInventory().getItemInMainHand();
					if(i.getAmount() > 1) {
						p.getInventory().getItemInMainHand().setAmount(i.getAmount()-1);
					}
					else {
					p.getInventory().remove(i);
					}
					p.updateInventory();
					
					if(enable == true) {
					int num = plugin.getConfig().getInt("Easter.Players." + p.getName());
					if(plugin.getConfig().get("Easter.Players." + p.getName()) == null) {
					plugin.getConfig().set("Easter.Players." + p.getName(), 1);
					}
					else {
						
						plugin.getConfig().set("Easter.Players." + p.getName(), num+1);
					}
					p.sendMessage(ChatColor.GREEN +"You found an easter egg!");
					Main.players.put(p.getName(), num+1);
					plugin.getConfig().options().copyDefaults(true);
					plugin.saveConfig();
					plugin.reloadConfig();
					if(broadcast == true) {
						Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + ChatColor.DARK_PURPLE + " has found an Easter Egg!");
					}
					}
					else {
					p.sendMessage(ChatColor.RED + "Easter is over and you feel the egg in your hand magically dissapear.");	
					}
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
		
		
	}
	
	@EventHandler
	public void pickup(PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		if(event.getItem().getItemStack().getItemMeta().equals(Eggs.easter().getItemMeta())) {
		if(plugin.eggs.contains(event.getItem().getItemStack())) {
			plugin.eggs.remove(event.getItem().getItemStack());
			System.out.println("removed egg");
		}
		if(pickup == true) {
			
				enable = plugin.getConfig().getBoolean("Easter.enable");
				broadcast = plugin.getConfig().getBoolean("Easter.broadcast");
				pickup = plugin.getConfig().getBoolean("Easter.instantpickup");
			event.setCancelled(true);
			event.getItem().remove();
			int num = plugin.getConfig().getInt("Easter.Players." + p.getName());
			if(plugin.getConfig().get("Easter.Players." + p.getName()) == null) {
			plugin.getConfig().set("Easter.Players." + p.getName(), 1);
			}
			else {
				
				plugin.getConfig().set("Easter.Players." + p.getName(), num++);
			}
			p.sendMessage(ChatColor.GREEN +"You found an easter egg!");
			Main.players.put(p.getName(), num++);
			plugin.getConfig().options().copyDefaults(true);
			plugin.saveConfig();
			plugin.reloadConfig();
			
			if(broadcast == true) {
				Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + ChatColor.DARK_PURPLE + " has found an Easter Egg!");
			}
			}
			
		}
	}

}
