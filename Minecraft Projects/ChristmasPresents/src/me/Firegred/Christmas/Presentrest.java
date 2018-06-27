package me.Firegred.Christmas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class Presentrest implements Listener{
	
	public Main plugin;
	public static final ArrayList<Player> openers = new ArrayList<Player>();
	public static final ArrayList<Player> looters = new ArrayList<Player>();
	public static final HashMap<Player, ItemStack> loot = new HashMap<Player, ItemStack>();
	public static final HashMap<Player, ItemStack> loot2 = new HashMap<Player, ItemStack>();
	public static final HashMap<Player, ItemStack> loot3 = new HashMap<Player, ItemStack>();
	public static final HashMap<Player, ItemStack> loot4 = new HashMap<Player, ItemStack>();
	public static final HashMap<Player, ItemStack> chest = new HashMap<Player, ItemStack>();
	public static final ItemStack hatchet1 = new ItemStack(Material.STONE_AXE, 1);
	public static final ItemStack hatchet2 = new ItemStack(Material.IRON_AXE, 1);
	public static final ItemStack hatchet3 = new ItemStack(Material.GOLD_AXE, 1);
	public static final ItemStack hatchet4 = new ItemStack(Material.DIAMOND_AXE, 1);
	
	public static final ItemStack cd1 = new ItemStack(2256, 1);
	public static final ItemStack cd2 = new ItemStack(2257, 1);
	public static final ItemStack cd3 = new ItemStack(2258, 1);
	public static final ItemStack cd4 = new ItemStack(2259, 1);
	public static final ItemStack cd5 = new ItemStack(2260, 1);
	public static final ItemStack cd6 = new ItemStack(2261, 1);
	public static final ItemStack cd7 = new ItemStack(2262, 1);
	public static final ItemStack cd8 = new ItemStack(2263, 1);
	public static final ItemStack cd9 = new ItemStack(2264, 1);
	public static final ItemStack cd10 = new ItemStack(2265, 1);
	public static final ItemStack cd11 = new ItemStack(2266, 1);
	public static final ItemStack cd12 = new ItemStack(2267, 1);
	
	public static String happy = new String(ChatColor.YELLOW + "Happy");
	public static String see = new String(ChatColor.DARK_GREEN + "Knowledgeable");
	public static String instinctive  = new String(ChatColor.GOLD + "Distinctive");
	public static String jolly = new String(ChatColor.RED + "Jolly");
	public static String energetic = new String(ChatColor.GREEN + "Energetic");
	public static String loveable = new String(ChatColor.LIGHT_PURPLE + "Loveable");
	public static String ambitious = new String(ChatColor.LIGHT_PURPLE + "Ambitious");
	public static String vigorous  = new String(ChatColor.GRAY + "Vigorous ");
	public static String determined = new String(ChatColor.AQUA + "Determined");
	public static String efficient = new String(ChatColor.GREEN + "Efficient");
	public static String naughty = new String(ChatColor.GRAY + "Naughty");
	public static String resistant = new String(ChatColor.GRAY + "Resistant");
	
	static String names2[] = {happy, see, instinctive , jolly, energetic, loveable, ambitious, vigorous, determined, efficient, naughty, resistant}; 
	
	public Presentrest(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void security(PlayerInteractEvent event) {
		try {
			if(openers.contains(event.getPlayer())) {
			if(!event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "Present for: ")) {
				event.setCancelled(true);
			}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@EventHandler
	public void chests(InventoryOpenEvent event) {
		if(openers.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void throwing(PlayerDropItemEvent event) {
		if(openers.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void close(InventoryCloseEvent event) {
		if(event.getPlayer() instanceof Player) {
		if(looters.contains(event.getPlayer())) {
			Player p = (Player) event.getPlayer();
			p.sendMessage(ChatColor.GREEN + "Your presents have been placed in your inventory. Happy Holidays!");
			p.getInventory().remove(chest.get(p));
			p.getInventory().addItem(loot.get(p));
			p.getInventory().addItem(loot2.get(p));
			p.getInventory().addItem(loot3.get(p));
			p.getInventory().addItem(loot4.get(p));
			
			looters.remove(p);
			loot.remove(p);
			loot2.remove(p);
			loot3.remove(p);
			loot4.remove(p);
			chest.remove(p);
			
		}
	}
	}
	@EventHandler
	public void inventories(InventoryClickEvent event) {
		if(event.getWhoClicked() instanceof Player) {
		if(openers.contains(event.getWhoClicked())) {
			event.setCancelled(true);
		}
			if(looters.contains(event.getWhoClicked())) {
			Player p = (Player) event.getWhoClicked();
			if(event.getInventory().getTitle().equals(ChatColor.GREEN + "Happy Holidays!")) {
				event.setCancelled(true);
				p.closeInventory();
				
				
			}
		}
		}
	}
	
	@EventHandler
	public void leave2(PlayerQuitEvent event) {
		if(openers.contains(event.getPlayer())) {
			openers.remove(event.getPlayer());
		}
		if(looters.contains(event.getPlayer())) {
			looters.remove(event.getPlayer());
		}
		if(loot.containsKey(event.getPlayer())) {
			loot.remove(event.getPlayer());
		}
		if(loot2.containsKey(event.getPlayer())) {
			loot2.remove(event.getPlayer());
		}
		if(loot3.containsKey(event.getPlayer())) {
			loot3.remove(event.getPlayer());
		}
		if(loot4.containsKey(event.getPlayer())) {
			loot4.remove(event.getPlayer());
		}
		if(chest.containsKey(event.getPlayer())) {
			chest.remove(event.getPlayer());
		}
		if(Gifts.cduser.contains(event.getPlayer().getName())) {
			for(PotionEffect effect : event.getPlayer().getActivePotionEffects())
			{
			    event.getPlayer().removePotionEffect(effect.getType());
			}
		}
		
	}
	@EventHandler
	public void leave(PlayerDeathEvent event) {
		if(openers.contains(event.getEntity())) {
			openers.remove(event.getEntity());
		}
		if(looters.contains(event.getEntity())) {
			looters.remove(event.getEntity());
		}
		if(loot.containsKey(event.getEntity())) {
			loot.remove(event.getEntity());
		}
		if(loot2.containsKey(event.getEntity())) {
			loot2.remove(event.getEntity());
		}
		if(loot3.containsKey(event.getEntity())) {
			loot3.remove(event.getEntity());
		}
		if(loot4.containsKey(event.getEntity())) {
			loot4.remove(event.getEntity());
		}
		if(chest.containsKey(event.getEntity())) {
			chest.remove(event.getEntity());
		}
	}
	
	@EventHandler
	public void open(PlayerInteractEvent event) {
		try {
			if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "Present for: ")) {
			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				final Player p = event.getPlayer();
				if(openers.contains(p)) {
					openers.remove(p);
					chest.remove(p);
					p.sendMessage(ChatColor.RED + "Present opening has been cancelled!");
				}
				
				else if(!openers.contains(p)) {
					final ArrayList<ItemStack> items = new ArrayList<ItemStack>();
					openers.add(p);
					chest.put(p, p.getItemInHand());
					p.sendMessage(ChatColor.GREEN + "Present is opening in 6 seconds. Right click anything with the present to cancel this");
			           
				    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						if(openers.contains(p)) {
							p.sendMessage(ChatColor.GREEN + "Opening in:");
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									if(openers.contains(p)) {
									p.sendMessage(ChatColor.GREEN + "3");
									p.playSound(p.getLocation(), Sound.NOTE_PIANO, 2, 2);
									Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
										@Override
										public void run() {
											if(openers.contains(p)) {
											p.sendMessage(ChatColor.GREEN + "2");
											p.playSound(p.getLocation(), Sound.NOTE_PIANO, 2, 2);
											Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
												@Override
												public void run() {
													if(openers.contains(p)) {
													p.sendMessage(ChatColor.GREEN + "1");
													p.playSound(p.getLocation(), Sound.NOTE_PIANO, 2, 2);
													Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
														@Override
														public void run() {
															if(openers.contains(p)) {
															p.sendMessage(ChatColor.GREEN + "Opened!");
															openers.remove(p);
															if(!looters.contains(p)) {
																looters.add(p);
															}
															p.playSound(p.getLocation(), Sound.NOTE_PIANO, 2, 2);
															Inventory presents = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Happy Holidays!");
															
															ItemStack snowflake = new ItemStack(Material.SNOW_BALL, 1);
															ItemMeta snowflakeMeta = snowflake.getItemMeta();
															ArrayList<String> lore = new ArrayList<String>();
															snowflakeMeta.setDisplayName(ChatColor.GREEN + "SnowFlake Maker");
															lore.add(ChatColor.YELLOW + "Let it snow");
															snowflakeMeta.setLore(lore);
															snowflake.setItemMeta(snowflakeMeta);
															presents.addItem(snowflake);
															loot.put(p, snowflake);
								
															
															ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
															SkullMeta headMeta = (SkullMeta) head.getItemMeta();
															headMeta.setDisplayName(p.getName() + "'s head");
															headMeta.setOwner(p.getName());
															head.setItemMeta(headMeta);
															presents.addItem(head);
															loot2.put(p, head);
															
															
															Random rand = new Random();
															Random rand2 = new Random();
															Random rand3 = new Random();
															Random rand4 = new Random();
															Random rand5 = new Random();
															
															int hatchets = rand.nextInt(4);
															int unbreak = rand2.nextInt(4);
															int eff = rand3.nextInt(5);
															int sharp = rand4.nextInt(4);
															
															ItemStack hatchet[] = {hatchet1, hatchet2, hatchet3, hatchet4};
															int unbreaking[] = {1, 2, 3, 4};
															int efficiency[] = {0, 1, 2, 3, 4};
															int sharpness[] = {0, 1, 2, 3};
															
															ItemStack realhatchet = new ItemStack(Material.STONE_AXE, 1);
															ItemMeta hatchetMeta = realhatchet.getItemMeta();
															ArrayList<String> hatchetlore = new ArrayList<String>();
															hatchetMeta.setDisplayName(ChatColor.GREEN + "Holiday hatchet");
															hatchetlore.add(ChatColor.RED + "Happy Holidays!");
															hatchetMeta.setLore(hatchetlore);
															realhatchet.setItemMeta(hatchetMeta);
															
															realhatchet.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, unbreaking[unbreak]);
															realhatchet.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DIG_SPEED, efficiency[eff]);
															//realhatchet.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, sharpness[sharp]);
															presents.addItem(realhatchet);
															loot3.put(p, realhatchet);
															
															int cdrand = rand5.nextInt(12);
															ItemStack cds[] = {cd1, cd2, cd3, cd4, cd5, cd6, cd7, cd8, cd9, cd10, cd11, cd12};
															String names[] = {happy, see, instinctive , jolly, energetic, loveable, ambitious, vigorous, determined, efficient, naughty, resistant}; 
															
															ItemStack cd = new ItemStack(cds[cdrand]);
															ItemMeta cdmeta = cd.getItemMeta();
															ArrayList<String> cdlore = new ArrayList<String>();
					                                        int magic = cdrand + 1;
					                                        String magic2 = String.valueOf(magic);
					
															cdlore.add(ChatColor.GREEN + "Happy Holidays from Sync!");
															cdmeta.setDisplayName(ChatColor.GOLD + "Holiday trait #" + ChatColor.GREEN + magic + ChatColor.GOLD + " : " + names[cdrand]);
															cdmeta.setLore(cdlore);
															cd.setItemMeta(cdmeta);
															presents.addItem(cd);
															loot4.put(p, cd);
															
															p.openInventory(presents);
															
															}
														}
														}, 20L);
												}
												}
												}, 20L);
										}
										}
										}, 20L);
								}
								}
								}, 20L);
							}
						}
					}
				
				, 20L*6);
			}

}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
		}
		
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void presntuse(BlockPlaceEvent event) {
		try {
			if(event.getPlayer().getItemInHand().getType().equals(Material.ENDER_CHEST)) {
			if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "Present for: ")) {
				event.setCancelled(true);
				event.getPlayer().updateInventory();
			}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	}

}
