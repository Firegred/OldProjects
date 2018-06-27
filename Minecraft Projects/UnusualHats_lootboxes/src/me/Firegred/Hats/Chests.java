package me.Firegred.Hats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Chests implements Listener {
	
	public Hats plugin;
	
	public static double displacement;
	public static Enchantment ench1 = Enchantment.PROTECTION_ENVIRONMENTAL;
	public static Enchantment ench2 = Enchantment.PROTECTION_FIRE;
	public static Enchantment ench3 = Enchantment.PROTECTION_FALL;
	public static Enchantment ench4 = Enchantment.PROTECTION_EXPLOSIONS;
	public static Enchantment ench5 = Enchantment.PROTECTION_PROJECTILE;
	public static Enchantment ench6 = Enchantment.OXYGEN;
	public static Enchantment ench7= Enchantment.WATER_WORKER;
	public static Enchantment ench8 = Enchantment.THORNS;
	public static Enchantment ench9 = Enchantment.DAMAGE_ALL;
	public static Enchantment ench10 = Enchantment.DAMAGE_UNDEAD;
	public static Enchantment ench11 = Enchantment.DAMAGE_ARTHROPODS;
	public static Enchantment ench12 = Enchantment.KNOCKBACK;
	public static Enchantment ench13 = Enchantment.FIRE_ASPECT;
	public static Enchantment ench14 = Enchantment.LOOT_BONUS_MOBS;
	public static Enchantment ench15 = Enchantment.DIG_SPEED;
	public static Enchantment ench16 = Enchantment.SILK_TOUCH;
	public static Enchantment ench17 = Enchantment.DURABILITY;
	public static Enchantment ench18 = Enchantment.LOOT_BONUS_BLOCKS;
	public static Enchantment ench19 = Enchantment.ARROW_DAMAGE;
	public static Enchantment ench20 = Enchantment.ARROW_KNOCKBACK;
	public static Enchantment ench21 = Enchantment.ARROW_FIRE;
	public static Enchantment ench22 = Enchantment.ARROW_INFINITE;
	public static Enchantment ench23 = Enchantment.LUCK;
	public static Enchantment ench24 = Enchantment.LURE;

	
	public static Enchantment enchant1 = Enchantment.PROTECTION_ENVIRONMENTAL;
	public static Enchantment enchant2 = Enchantment.PROTECTION_FIRE;
	public static Enchantment enchant3 = Enchantment.PROTECTION_FALL;
	public static Enchantment enchant4 = Enchantment.PROTECTION_EXPLOSIONS;
	public static Enchantment enchant5 = Enchantment.PROTECTION_PROJECTILE;
	public static Enchantment enchant6 = Enchantment.OXYGEN;
	public static Enchantment enchant7 = Enchantment.WATER_WORKER;
	public static Enchantment enchant8 = Enchantment.THORNS;

    
	
	public static ItemStack iron1 = new ItemStack(Material.IRON_HELMET, 1);
	public static ItemStack iron2 = new ItemStack(Material.IRON_CHESTPLATE, 1);
	public static ItemStack iron3 = new ItemStack(Material.IRON_LEGGINGS, 1);
	public static ItemStack iron4 = new ItemStack(Material.IRON_BOOTS, 1);
	public static ItemStack gold1 = new ItemStack(Material.GOLD_HELMET, 1);
	public static ItemStack gold2 = new ItemStack(Material.GOLD_CHESTPLATE, 1);
	public static ItemStack gold3 = new ItemStack(Material.GOLD_LEGGINGS, 1);
	public static ItemStack gold4 = new ItemStack(Material.GOLD_BOOTS, 1);
	public static ItemStack diamond1 = new ItemStack(Material.DIAMOND_HELMET, 1);
	public static ItemStack diamond2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
	public static ItemStack diamond3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
	public static ItemStack diamond4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
	
	
	public static ItemStack glass = new ItemStack(95, 1);
	public static ItemStack glass1 = new ItemStack(95, 1, (short) 1);
	public static ItemStack glass2 = new ItemStack(95, 1, (short) 2);
	public static ItemStack glass3 = new ItemStack(95, 1, (short) 3);
	public static ItemStack glass4 = new ItemStack(95, 1, (short) 4);
	public static ItemStack glass5 = new ItemStack(95, 1, (short) 5);
	public static ItemStack glass6 = new ItemStack(95, 1, (short) 6);
	public static ItemStack glass7 = new ItemStack(95, 1, (short) 7);
	public static ItemStack glass8 = new ItemStack(95, 1, (short) 8);
	public static ItemStack glass9 = new ItemStack(95, 1, (short) 9);
	public static ItemStack glass10 = new ItemStack(95, 1, (short) 10);
	public static ItemStack glass11 = new ItemStack(95, 1, (short) 11);
	public static ItemStack glass12 = new ItemStack(95, 1, (short) 12);
	public static ItemStack glass13 = new ItemStack(95, 1, (short) 13);
	public static ItemStack glass14 = new ItemStack(95, 1, (short) 14);
	public static ItemStack glass15 = new ItemStack(95, 1, (short) 15);
	
	public static ItemStack helm1 = new ItemStack(Material.LEATHER_HELMET, 1);
	public static ItemStack helm2 = new ItemStack(Material.CHAINMAIL_HELMET, 1);
	public static ItemStack helm3 = new ItemStack(Material.IRON_HELMET, 1);
	public static ItemStack helm4 = new ItemStack(Material.GOLD_HELMET, 1);
	public static ItemStack helm5 = new ItemStack(Material.DIAMOND_HELMET, 1);
	
	
	public static final ArrayList<Player> Openers = new ArrayList<Player>();
	public static final HashMap<Player, ItemStack> Loot = new HashMap<Player, ItemStack>();
	public static final HashMap<Player, ChatColor> Meta123 = new HashMap<Player, ChatColor>();
	public static final ParticleEffect a = ParticleEffect.ANGRY_VILLAGER;
	//final ParticleEffect b = ParticleEffect.BUBBLE;
	public static final ParticleEffect c = ParticleEffect.CLOUD;
	//final ParticleEffect d = ParticleEffect.DEPTH_SUSPEND;
	public static final ParticleEffect e = ParticleEffect.DRIP_LAVA;
	public static final ParticleEffect f = ParticleEffect.DRIP_WATER;
	public static final ParticleEffect g = ParticleEffect.ENCHANTMENT_TABLE;
	public static final ParticleEffect h = ParticleEffect.EXPLODE;
	public static final ParticleEffect i = ParticleEffect.FIREWORKS_SPARK;
	public static final ParticleEffect j = ParticleEffect.FLAME;
	public static final ParticleEffect k = ParticleEffect.FOOTSTEP;
	public static final ParticleEffect l = ParticleEffect.HAPPY_VILLAGER;
	public static final ParticleEffect m = ParticleEffect.HEART;
	public static final ParticleEffect n = ParticleEffect.CRIT;
	//public static final ParticleEffect o = ParticleEffect.ICONCRACK;
	public static final ParticleEffect p = ParticleEffect.INSTANT_SPELL;
	public static final ParticleEffect q = ParticleEffect.LARGE_EXPLODE;
	public static final ParticleEffect r = ParticleEffect.LARGE_SMOKE;
	public static final ParticleEffect s = ParticleEffect.LAVA;
	public static final ParticleEffect t = ParticleEffect.MOB_SPELL;
	public static final ParticleEffect u = ParticleEffect.MOB_SPELL_AMBIENT;
	public static final ParticleEffect v = ParticleEffect.NOTE;
	public static final ParticleEffect w = ParticleEffect.PORTAL;
	public static final ParticleEffect x = ParticleEffect.RED_DUST;
	public static final ParticleEffect y = ParticleEffect.SLIME;
	public static final ParticleEffect z = ParticleEffect.SNOW_SHOVEL;
	public static final ParticleEffect aa = ParticleEffect.SNOWBALL_POOF;
	public static final ParticleEffect bb = ParticleEffect.SPELL;
	public static final ParticleEffect cc = ParticleEffect.SPLASH;
	//public static final ParticleEffect dd = ParticleEffect.SUSPEND;
	public static final ParticleEffect ee = ParticleEffect.TOWN_AURA;
	public static final ParticleEffect ff = ParticleEffect.WITCH_MAGIC;
	public static final ParticleEffect[] particles = {a, c, e, f, g, h, i, j, k, l, m, n, p, q, r, s, t, u, v, w, x, y, z, aa, bb, cc, ee, ff};
	
	public static Effect a1 = Effect.VILLAGER_THUNDERCLOUD;
	public static Effect b1 = Effect.CLOUD;
	public static Effect c1 = Effect.LAVADRIP;
	public static Effect d1 = Effect.WATERDRIP;
	public static Effect e1 = Effect.FLYING_GLYPH;
	public static Effect g1 = Effect.EXPLOSION;
	public static Effect h1 = Effect.FIREWORKS_SPARK;
	public static Effect i1 = Effect.FLAME;
	public static Effect j1 = Effect.FOOTSTEP;
	public static Effect k1 = Effect.HAPPY_VILLAGER;
	public static Effect l1 = Effect.HEART;
	public static Effect m1 = Effect.CRIT;
	public static Effect n1 = Effect.INSTANT_SPELL;
	public static Effect o1 = Effect.EXPLOSION_LARGE;
	public static Effect p1 = Effect.LARGE_SMOKE;
	public static Effect q1 = Effect.LAVA_POP;
	public static Effect r1 = Effect.POTION_SWIRL;
	public static Effect s1 = Effect.POTION_SWIRL_TRANSPARENT;
	public static Effect t1 = Effect.NOTE;
	public static Effect u1 = Effect.PORTAL;
	public static Effect v1 = Effect.COLOURED_DUST;
	public static Effect w1 = Effect.SLIME;
	public static Effect x1 = Effect.SNOW_SHOVEL;
	public static Effect y1 = Effect.SNOWBALL_BREAK;
	public static Effect z1 = Effect.SPELL;
	public static Effect aa1 = Effect.SPLASH;
	public static Effect bb1 = Effect.VOID_FOG;
	public static Effect cc1 = Effect.WITCH_MAGIC;
	public static final Effect[] particles2 = {a1, b1, c1, d1, e1, g1, h1, i1, j1, k1, l1, m1, n1, o1, p1, q1, r1, s1, t1, u1, v1, w1, x1, y1, z1, aa1, bb1, cc1};
	
	public static HashMap<Player, Integer> hatter = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> hv = new HashMap<Player, Integer>();
	
	public final ArrayList<Block> chests = new ArrayList<Block>();
	private static HashMap<Player, ItemStack> strange = new HashMap<Player, ItemStack>();
	public Chests(Hats instance) {
		plugin = instance;
		displacement = plugin.getConfig().getDouble("Displacement");
	}
	
	
	@EventHandler
	public void hatclick(InventoryClickEvent e) {
		try {
			if(e.getWhoClicked() instanceof Player) {
				Player p = (Player) e.getWhoClicked();
				if(e.getInventory().getName().contains(ChatColor.DARK_PURPLE + "Select a hat")) {
					e.setCancelled(true);
					if(hatter.containsKey(p)) {
						p.getInventory().addItem(e.getInventory().getItem(e.getSlot()));
						p.sendMessage(ChatColor.GREEN +"Your selected hat has been placed into your inventory");
						hatter.remove(p);
						hv.remove(p);
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block

		}
			
		
	}
	@EventHandler
	public void hatclose(InventoryCloseEvent e) {
		if(e.getPlayer() instanceof Player) {
			Player p = (Player) e.getPlayer();
			if(hatter.containsKey(p)) {
				final ItemStack[] materials = {helm1,helm2,helm3,helm4,helm5};
				final Enchantment[] ench = {Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY};
				final int pr = 5;
				final int d = 3;
				final Random r = new Random();
				int pr1 = r.nextInt(pr)+1;
				int d1 = r.nextInt(d)+1;
				int h = r.nextInt(5);
				int ch1 = r.nextInt(2);
				int ch2 = r.nextInt(2);
				int hatz = r.nextInt(28);
				ItemStack hat = materials[h];
				ItemMeta hm = hat.getItemMeta();
				hm.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + Hats.effectname[hatz]);
				hat.setItemMeta(hm);
				if(!hat.containsEnchantment(ench[0]) || !hat.containsEnchantment(ench[1])) {
				if(ch1 == 1) {
					hat.addUnsafeEnchantment(ench[ch2], pr1);					    
				}
				else  {
					hat.addUnsafeEnchantment(ench[0], pr1);
					hat.addUnsafeEnchantment(ench[1], d1);
				}
				}
				if(ch1 == 1) {
					hat.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);	
					hat.removeEnchantment(Enchantment.DURABILITY);
					hat.addUnsafeEnchantment(ench[ch2], pr1);			
					}
				p.getInventory().addItem(hat);
				p.sendMessage(ChatColor.GREEN + "Since you closed your inventory, a random hat was selected for you");
				hatter.remove(p);
				hv.remove(p);
			}
		}
	}
	
	@EventHandler
	public void hatchestt(BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		try {
			if(e.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Hat Chest")) {
				e.setCancelled(true);
				if(p.getItemInHand().getAmount()-1 <= 0) {
					p.setItemInHand(null);
				}
				else {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
				}
				p.sendMessage(ChatColor.GREEN + "Opening chest");
				final ItemStack[] materials = {helm1,helm2,helm3,helm4,helm5};
				final Enchantment[] ench = {Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY};
				final int pr = 5;
				final int d = 3;
				final Random r = new Random();
				final Inventory i1 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat in 10 seconds");
//			final Inventory i2 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "9 seconds");
//			final Inventory i3 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "8 seconds");
//			final Inventory i4 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "7 seconds");
//			final Inventory i5 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "6 seconds");
//			final Inventory i6 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "5 seconds");
//			final Inventory i7 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "4 seconds");
//			final Inventory i8 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "3 seconds");
//			final Inventory i9 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "2 seconds");
//			final Inventory i10 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "1 seconds");
//			final Inventory i11 = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Select a hat: " + ChatColor.GOLD + "0 seconds");
//			final Inventory[] inventories = {i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11};
				hatter.put(p, 0);
				hv.put(p, 0);
				p.openInventory(i1);
				for(int i = 1; i < 20*11; i++) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						
						@Override
						public void run() {
							try {
								if(hatter.containsKey(p) && p.isOnline()) {
								hv.put(p, hv.get(p)+1);
								int v = hv.get(p);
								
								if(v > 80) {
									if(v%10 == 0) {
										p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									}
								}
								else if(v > 120) {
									if(v%5 == 0) {
										p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									}
								}
								else if(v > 180) {
									if(v%3 == 0) {
										p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									}
								}
								else {
									if(v%20 == 0) {
										p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									}
								}
								if(v == 219) {
									int pr1 = r.nextInt(pr)+1;
									int d1 = r.nextInt(d)+1;
									int h = r.nextInt(5);
									int ch1 = r.nextInt(2);
									int ch2 = r.nextInt(2);
									int hatz = r.nextInt(28);
									ItemStack hat = materials[h];
									ItemMeta hm = hat.getItemMeta();
									hm.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + Hats.effectname[hatz]);
									hat.setItemMeta(hm);
									if(!hat.containsEnchantment(ench[0]) || !hat.containsEnchantment(ench[1])) {
										if(ch1 == 1) {
										 
								    hat.addUnsafeEnchantment(ench[ch2], pr1);				
										    
									}
									else {
										hat.addUnsafeEnchantment(ench[0], pr1);
										hat.addUnsafeEnchantment(ench[1], d1);
									}
									}
									if(ch1 == 1) {
										hat.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);	
										hat.removeEnchantment(Enchantment.DURABILITY);
										hat.addUnsafeEnchantment(ench[ch2], pr1);			
										}
									p.getInventory().addItem(hat);
									p.sendMessage(ChatColor.GREEN + "A random hat was selected for you");
									hatter.remove(p);
									hv.remove(p);
									p.closeInventory();
								}
								else if(v%20 == 0) {
									int c = hatter.get(p);
									hatter.put(p, c+1);
									int c2 = hatter.get(p);
									for(int i = 0; i < 10; i++) {
										int pr1 = r.nextInt(pr)+1;
										int d1 = r.nextInt(d)+1;
										int h = r.nextInt(5);
										int ch1 = r.nextInt(2);
										int ch2 = r.nextInt(2);
										ItemStack hat = materials[h];
										int hatz = r.nextInt(28);
										ItemMeta hm = hat.getItemMeta();
										hm.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + Hats.effectname[hatz]);
										hat.setItemMeta(hm);
										if(!hat.containsEnchantment(ench[0]) || !hat.containsEnchantment(ench[1])) {
											
											if(ch1 == 1) {
										    hat.addUnsafeEnchantment(ench[ch2], pr1);	
										    
										}
										else {
											hat.addUnsafeEnchantment(ench[0], pr1);
											hat.addUnsafeEnchantment(ench[1], d1);
										}
										}
										if(hatter.containsKey(p)) {
											if(ch1 == 1) {
												hat.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);	
												hat.removeEnchantment(Enchantment.DURABILITY);
												hat.addUnsafeEnchantment(ench[ch2], pr1);			
												}
											i1.setItem(i, hat);
										}
									}
									if(c2 == 10) {
										
									}
								}
								else {
								
									for(int i = 0; i < 10; i++) {
										int pr1 = r.nextInt(pr)+1;
										int d1 = r.nextInt(d)+1;
										int h = r.nextInt(5);
										int ch1 = r.nextInt(2);
										int ch2 = r.nextInt(2);
										ItemStack hat = materials[h];
										int hatz = r.nextInt(28);
										ItemMeta hm = hat.getItemMeta();
										hm.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + Hats.effectname[hatz]);
										hat.setItemMeta(hm);
										if(!hat.containsEnchantment(ench[0]) || !hat.containsEnchantment(ench[1])) {
											if(ch1 == 1) {
										    hat.addUnsafeEnchantment(ench[ch2], pr1);					
										}
										else {
											hat.addUnsafeEnchantment(ench[0], pr1);
											hat.addUnsafeEnchantment(ench[1], d1);
										}
										}
										if(hatter.containsKey(p)) {
										if(ch1 == 1) {
										hat.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);	
										hat.removeEnchantment(Enchantment.DURABILITY);
										hat.addUnsafeEnchantment(ench[ch2], pr1);			
										}
										i1.setItem(i, hat);
										}
									}
								}

								}
							} catch (java.lang.ArrayIndexOutOfBoundsException e) {
								// TODO Auto-generated catch block
							}
						}
					}, i);
				}
			}
		} catch (java.lang.NullPointerException e1) {
			// TODO Auto-generated catch block
		}
	}
	
	
	@EventHandler
	public void prevention(PlayerCommandPreprocessEvent e) {
    if(e.getMessage().contains("/pv")) {
    	if(Openers.contains(e.getPlayer())) {
    		e.setCancelled(true);
    		e.getPlayer().sendMessage(ChatColor.RED + "You cannot open pvs while opening unusual chests!");
    	}
    	
    }
}
//	@EventHandler
//	public void move(PlayerMoveEvent e) {
//		Player p = e.getPlayer();
//		if(!strange.containsKey(p)) {
//		if(p.getItemInHand() != null) {
//			if(p.getItemInHand().hasItemMeta()) {
//				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.GOLD + "Strange") && (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("pickaxe") || e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("shovel"))) {
//					strange.put(p, p.getItemInHand());
//				}
//			}
//		}
//		}
//		if(strange.containsKey(p)) {
//			if(p.getItemInHand().hasItemMeta()) {
//				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.GOLD + "Strange") && (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("pickaxe") || e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("shovel"))) {
//					if(p.getItemInHand().getType() != p.getItemInHand().getType()) {
//						strange.put(p, p.getItemInHand());
//					}
//				}
//			}
//		}
//	}
	
	@EventHandler
	public void StrangeDig(BlockBreakEvent event) {
		try {
			Player player = event.getPlayer();
			ArrayList<String> lore = new ArrayList<String>();
			if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.GOLD + "Strange") && (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("pickaxe") || event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("shovel"))) {
				for(String lores : player.getInventory().getItemInHand().getItemMeta().getLore()) {
				lore.add(lores);
				for(int i = 0; i < 10000000; i++) {
					String number = String.valueOf(i);
					if(lores.equals(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + number)) {
						ItemStack item = new ItemStack(player.getInventory().getItemInHand().getType(), player.getInventory().getItemInHand().getAmount());
						ItemMeta itemmeta = item.getItemMeta();
						int c = Integer.parseInt(number);
						String d = String.valueOf(c+1);
						
						//ItemStack newItem = new ItemStack(strange.get(player));
						//ItemMeta newitemmeta = newItem.getItemMeta();
						
						lore.remove(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + number);
					    lore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + d);	
					    
					    itemmeta.setLore(lore);
						itemmeta.setDisplayName(player.getItemInHand().getItemMeta().getDisplayName());
						item.setItemMeta(itemmeta);
						item.addUnsafeEnchantments(player.getInventory().getItemInHand().getEnchantments());
				        player.getInventory().setItemInHand(item);
				        break;
					}
			}
			}
			}
		} catch (java.lang.NullPointerException e) {
			
		}
	}
	@EventHandler
	public void Strangekill(EntityDeathEvent event) {
		
		try {
			final Player killer = event.getEntity().getKiller();
			if(killer instanceof Player) {
				ArrayList<String> lore = new ArrayList<String>();
				for(String lores : killer.getInventory().getItemInHand().getItemMeta().getLore()) {
					lore.add(lores);
				for(int i = 0; i < 1000000; i++) {
				String number = String.valueOf(i);
				if(lores.equals(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + number)) {
					
					ItemStack Swords = new ItemStack(killer.getInventory().getItemInHand().getType(), killer.getInventory().getItemInHand().getAmount());
					ItemMeta SwordMeta = Swords.getItemMeta();
					int c = Integer.parseInt(number);
					String d = String.valueOf(c+1);
					lore.remove(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + number);
				    lore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + d);	
					
					
					SwordMeta.setLore(lore);
					SwordMeta.setDisplayName(killer.getItemInHand().getItemMeta().getDisplayName());
					Swords.setItemMeta(SwordMeta);
					Swords.addUnsafeEnchantments(killer.getInventory().getItemInHand().getEnchantments());
			        killer.getInventory().setItemInHand(Swords);
			       
					//System.out.println("adding kills should work!");
					//System.out.println(d);
					break;
				}
				
				}
				}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
		
		}
		
	}
	
	@EventHandler
	public void DestroyChest(BlockBreakEvent event) {
		try {
			if(event.getBlock().getType().equals(Material.CHEST)) {
				if(event.getBlock().getState() instanceof Chest) {
					 Chest chest = (Chest) event.getBlock().getState();
					 Inventory inv = chest.getInventory();
				for(int i = 0; i < 29; i++) {
					if(chest.getInventory().getName().equals(ChatColor.DARK_PURPLE + "Unusual chest: " + ChatColor.GREEN + "#" + i)) {
						
						
						event.setCancelled(true);
						event.getBlock().setType(Material.AIR);
						ItemStack Box = new ItemStack(Material.CHEST, 1);
						ItemMeta Boxmeta = Box.getItemMeta();
						ArrayList<String> lore = new ArrayList<String>();
						lore.clear();
						lore.add(ChatColor.LIGHT_PURPLE + "Place this chest and open it with a special key!");
						Boxmeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual chest: " + ChatColor.GREEN + "#" + i);
						Boxmeta.setLore(lore);
						Box.setItemMeta(Boxmeta);
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), Box);
						
						
					}
					}
				}
			}
		} catch (java.lang.IllegalStateException e) {
			// TODO Auto-generated catch bloc
		}
	}
	@EventHandler
	public void Place(BlockPlaceEvent event) {
		try {
			Block b = event.getBlock();
			if(event.getBlock().getType().equals(Material.CHEST)) {
			    Chest chest = (Chest) b.getState();
//			int x = chest.getX();
//			int y = chest.getY();
//			int z = chest.getZ();
//			Location location1 = new Location(chest.getWorld(), x+1, y, z);
//			Location location2 = new Location(chest.getWorld(), x-1, y, z);
//			Location location3 = new Location(chest.getWorld(), x, y, z+1);
//			Location location4 = new Location(chest.getWorld(), x, y, z-1);
			    
				if(chest.getInventory().getName().contains(ChatColor.DARK_PURPLE + "Unusual chest: ")) {
					for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH)}) {
						if(neighbor.getState() instanceof Chest) {
			            	 event.setCancelled(true);
			            	// System.out.println("MARCO");
			            	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "unusual chests" + ChatColor.RED + " next to other chests!");
				
				
						}
					}
				}
				else {
				for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH)}) {
					if(neighbor.getState() instanceof Chest) {
						Chest c = (Chest) neighbor.getState();
			             if(event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Unusual chest: ")) {
			            // System.out.println("POLO");
			             event.setCancelled(true);
			        	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "unusual chests" + ChatColor.RED + " next to other chests!");
				    //set cancelled, neighbor was chest next to unusual chest
				
			             }
				
				}
				}
				
				}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
		}
		//}
		
		
	
//			for(int i = 0; i < 29; i++) {
//				if(chest.getInventory().getName().equals(ChatColor.DARK_PURPLE + "Unusual chest: " + ChatColor.GREEN + "#" + i)) {
//			             if(b.getRelative(BlockFace.EAST).getType().equals(Material.CHEST)) {
//			            	 event.setCancelled(true);
//			            	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "unusual chests" + ChatColor.RED + " next to other chests!");
//			             }
//			             if(b.getRelative(BlockFace.NORTH).getType().equals(Material.CHEST)) {
//			            	 event.setCancelled(true);
//			            	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "unusual chests" + ChatColor.RED + " next to other chests!");
//			             }
//			             if(b.getRelative(BlockFace.WEST).getType().equals(Material.CHEST)) {
//			            	 event.setCancelled(true);
//			            	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "unusual chests" + ChatColor.RED + " next to other chests!");
//			             }
//			             if(b.getRelative(BlockFace.SOUTH).getType().equals(Material.CHEST)) {
//			            	 event.setCancelled(true);
//			            	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "unusual chests" + ChatColor.RED + " next to other chests!");
//			             }
//					
//					
//				}
//		}
//		}
//		}
	}
	
	@EventHandler 
	public void claimingloot(InventoryCloseEvent event) {
		if(Loot.containsKey(event.getPlayer())) {
			event.getPlayer().getInventory().addItem(Loot.get(event.getPlayer()));
			Loot.remove(event.getPlayer());
			Player p = (Player) event.getPlayer();
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Your loot has been placed in your inventory!");
			
		}
	}
	@EventHandler
	public void Click(InventoryClickEvent event) {
		for(int i = 0; i < 29; i++) {
		if(event.getInventory().getName().equals(ChatColor.DARK_PURPLE + "Unusual Chest" + ChatColor.GREEN + "#" + i + ChatColor.GOLD + " Loot!")) {
			event.setCancelled(true);
			event.getWhoClicked().closeInventory();
		}
		else if(Loot.containsKey(event.getWhoClicked())) {
			event.setCancelled(true);
			event.getWhoClicked().closeInventory();
		}
	}
	}
	@EventHandler
	public void OpenLock(InventoryOpenEvent event) {
		if(Openers.contains((Player) event.getPlayer())) {
			event.setCancelled(true);
			Player p = (Player) event.getPlayer();
			p.sendMessage(ChatColor.RED + "You are no allowed to open inventories while unboxing an " + ChatColor.DARK_PURPLE + "unusual chest");
		}
	}
	@EventHandler
	public void OpenLoot(final InventoryOpenEvent event) {
		
			final Player p = (Player) event.getPlayer();
			try {
			if(event.getInventory().getType().equals(InventoryType.CHEST)) {
				
						
				
			for(int i = 0; i < 29; i++) {
			if(event.getInventory().getName().equals(ChatColor.DARK_PURPLE + "Unusual chest: " + ChatColor.GREEN + "#" + i)) {
				
				event.setCancelled(true);
				final Chest chest = (Chest) event.getInventory().getHolder();
				int x = chest.getX();
				int y = chest.getY();
				int z = chest.getZ();
				final int ii = i;
				final World world = chest.getWorld();
				final Location location = new Location(chest.getWorld(), x, y, z);
				if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual Chest Key!") && !p.getItemInHand().getType().equals(Material.AIR) && !Openers.contains(p)) {
					if(p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED + "You shouldn't be opening this unusual chest with a full inventory!");
					}
					else {
					int am = p.getItemInHand().getAmount();
					if(am == 1) {
						p.setItemInHand(null);
					}
					else {
					p.getItemInHand().setAmount(am - 1);
					}
					chest.getWorld().getBlockAt(chest.getLocation()).setType(Material.BEDROCK);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						Openers.add(p);
					}
					}, 1L);
					
			         p.sendMessage(ChatColor.GOLD + "Opening Chest in...");
			         
			         Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			        	 @Override
			        	 public void run() {
			        		 p.sendMessage(ChatColor.AQUA + "5");
			        		 p.getWorld().playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 6);
			        		  Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			                 	 @Override
			                 	 public void run() {
			                 		 p.sendMessage(ChatColor.AQUA + "4");
			                 		 p.getWorld().playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 6);
			                 		  Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			                         	 @Override
			                         	 public void run() {
			                         		 p.sendMessage(ChatColor.AQUA + "3");
			                         		 p.getWorld().playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 6);
			                         		  Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			                                 	 @Override
			                                 	 public void run() {
			                                 		 p.sendMessage(ChatColor.AQUA + "2");
			                                 		 p.getWorld().playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 6);
			                                 		  Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			                                         	 @Override
			                                         	 public void run() {
			                                         		 p.sendMessage(ChatColor.AQUA + "1");
			                                         		 p.getWorld().playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 6);
			                                         		  Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			                                                 	 @Override
			                                                 	 public void run() {
			                                                 		 p.sendMessage(ChatColor.AQUA + "Chest Opened!");
			                                                 		 p.getWorld().playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 18);
			                                                 		 world.getBlockAt(location).setType(Material.AIR);
			                                                 		 Openers.remove(p);
			                                                 		 Inventory Opens = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_PURPLE + "Unusual Chest " + ChatColor.GREEN + "#" + ii + ChatColor.GOLD + " Loot!");
			                                                 		 Random items = new Random();
			                                                 		Random colors = new Random();
			                                                 		 int prob = items.nextInt(6);
			                                                 		int col = colors.nextInt(16);
			                                                 		
			                                                 		 ChatColor Aqua = ChatColor.AQUA;
			                                                 		     ChatColor Black = ChatColor.BLACK;
			                                                 		     ChatColor Blue = ChatColor.BLUE;
			                                                 		     ChatColor DarkAqua = ChatColor.DARK_AQUA;
			                                                 		     ChatColor DarkBlue = ChatColor.DARK_BLUE;
			                                                             ChatColor DarkGray = ChatColor.DARK_GRAY;
			                                                             ChatColor DarkGreen = ChatColor.DARK_GREEN;
			                                                             ChatColor DarkPurple = ChatColor.DARK_PURPLE;
			                                                             ChatColor DarkRed = ChatColor.DARK_RED;
			                                                             ChatColor Gold = ChatColor.GOLD;
			                                                             ChatColor Gray = ChatColor.GRAY;
			                                                             ChatColor Green = ChatColor.GREEN;
			                                                             ChatColor LightPurple = ChatColor.LIGHT_PURPLE;
			                                                             ChatColor Red = ChatColor.RED;
			                                                             ChatColor White = ChatColor.WHITE;
			                                                             ChatColor Yellow = ChatColor.YELLOW;
			                                                             
			                                                        
			                                                                if(col == 0) {
				                                                 				Meta123.put(p, Aqua);
				                                                 			}
				                                                 			if(col == 1) {
				                                                 				Meta123.put(p, Black);
				                                                 			}
				                                                 			if(col == 2) {
				                                                 				Meta123.put(p, Blue);
				                                                 			}
				                                                 			if(col == 3) {
				                                                 				Meta123.put(p, DarkAqua);
				                                                 			}
				                                                 			if(col == 4) {
				                                                 				Meta123.put(p, DarkBlue);
				                                                 			}
				                                                 			if(col == 5) {
				                                                 				Meta123.put(p, DarkGray);
				                                                 			}
				                                                 			if(col == 6) {
				                                                 				Meta123.put(p, DarkGreen);
				                                                 			}
				                                                 			if(col == 7) {
				                                                 				Meta123.put(p, DarkPurple);
				                                                 			}
				                                                 			if(col == 8) {
				                                                 				Meta123.put(p, DarkRed);
				                                                 			}
				                                                 			if(col == 9) {
				                                                 				Meta123.put(p, Gold);
				                                                 			}
				                                                 			if(col == 10) {
				                                                 				Meta123.put(p, Gray);
				                                                 			}
				                                                 			if(col == 11) {
				                                                 				Meta123.put(p, Green);
				                                                 			}
				                                                 			if(col == 12) {
				                                                 				Meta123.put(p, LightPurple);
				                                                 			}
				                                                 			if(col == 13) {
				                                                 				Meta123.put(p, Red);
				                                                 			}
				                                                 			if(col == 14) {
				                                                 				Meta123.put(p, White);
				                                                 			}
				                                                 			if(col == 15) {
				                                                 				Meta123.put(p, Yellow);
				                                                 			}
				                                                 	
				                                                 	Random rand = new Random();
				                                                 	int hatchance = rand.nextInt(1000000);
				                                                 	Float hatf = Float.parseFloat(plugin.getConfig().getString("Hat Chest Chance"));
				                                                 	double hatd = hatf * 10000;
				                                                 	if(hatd >=1 && hatd <= 1000000) {
				                                                 		if(hatchance <= hatd) {
				                                                 			Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.GREEN + " has found an unusual helm in their unusual chest!");
				                                                 			Random rand1 = new Random();
				                                                 			Random rand2 = new Random();
				                                                 			Random rand3 = new Random();
				                                                 			ItemStack hats[] = {helm1, helm2, helm3, helm4, helm5};
				                                                 			
				                                                 			int hat1 = rand1.nextInt(5);
				                                                 			int prot = rand2.nextInt(5);
				                                                 			int unbreak = rand3.nextInt(4);
				                                                 		
				                                                			ArrayList<String> lore = new ArrayList<String>();
				                                                			lore.clear();
				                                                			lore.add(ChatColor.DARK_PURPLE + "Renaming will destroy it");
				                                                			
				                                                			ItemStack hat = new ItemStack(hats[hat1]);
				                                                			ItemMeta hatmeta = hat.getItemMeta();
				                                                			hatmeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + Hats.effectname[ii - 1]);
				                                                	        //hatmeta.setLore(lore);
				                                                	        hat.setItemMeta(hatmeta);
				                                                	        if(prot > 0) {
				                                                	        	hat.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, prot);
				                                                	        }
				                                                	        if(unbreak > 0) {
				                                                	        	hat.addEnchantment(Enchantment.DURABILITY, unbreak);
				                                                	        }
				                                                			
				                                                			Opens.addItem(hat);
			                                                 				Loot.put(p, hat);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
				                                                			
				                                                 			
				                                                 			
				                                                 		}
				                                                 	
				                                                 		else if(prob == 0) {
			                                                 			 
			                                                 			 Random i = new Random();
			                                                 			 Random b = new Random();
			                                                 			 Random c = new Random();
			                                                 			 int Meta3 = b.nextInt(5);
			                                                 			 int rewards = c.nextInt(18);
			                                                
			                                                 			 
			                                                 			 int next1 = i.nextInt(10);
			                                                 			 int next2 = i.nextInt(20);
			                                                 			 int next3 = i.nextInt(30);
			                                                 			 int next4 = i.nextInt(40);
			                                                 			 int next5 = i.nextInt(50);
			                                                 			 int next6 = i.nextInt(60);
			                                                 			 ItemStack reward1 = new ItemStack(Material.APPLE, next6 + 1);
			                                                 			 ItemStack reward2 = new ItemStack(Material.BREAD, next6 + 1);
			                                                 			 ItemStack reward3 = new ItemStack(Material.PORK, next5 + 1);
			                                                 			 ItemStack reward4 = new ItemStack(Material.GOLDEN_APPLE, next4 + 1);
			                                                 			 ItemStack reward5 = new ItemStack(Material.GOLDEN_APPLE, next4 + 1, (short)1);
			                                                 			 ItemStack reward6 = new ItemStack(Material.COOKED_FISH, next5 + 1);
			                                                 			 ItemStack reward7 = new ItemStack(Material.CAKE, 1);
			                                                 			 ItemStack reward8 = new ItemStack(Material.COOKIE, next6 + 1);
			                                                 			 ItemStack reward9 = new ItemStack(Material.MELON, next6 + 1);
			                                                 			 ItemStack reward10 = new ItemStack(Material.COOKED_CHICKEN, next5 + 1);
			                                                 			 ItemStack reward11 = new ItemStack(Material.COOKED_BEEF, next5 + 1);
			                                                 			 ItemStack reward12 = new ItemStack(Material.ROTTEN_FLESH, next6 + 1);
			                                                 			 ItemStack reward13 = new ItemStack(Material.CARROT_ITEM, next5 + 1);
			                                                 			 ItemStack reward14 = new ItemStack(Material.BAKED_POTATO, next4 + 1);
			                                                 			 ItemStack reward15 = new ItemStack(Material.POISONOUS_POTATO, next3 + 1);
			                                                 			 ItemStack reward16 = new ItemStack(Material.GOLDEN_CARROT, next4 + 1);
			                                                 			 ItemStack reward17 = new ItemStack(Material.PUMPKIN_PIE, next3 + 1);
			                                                 			 ItemStack reward18 = new ItemStack(Material.SPIDER_EYE, next6 + 1);
			                                                 			 
			                                                 			 ItemMeta meta1 = reward1.getItemMeta();
			                                                 			ItemMeta meta2 = reward2.getItemMeta();
			                                                 			ItemMeta meta3 = reward3.getItemMeta();
			                                                 			ItemMeta meta4 = reward4.getItemMeta();
			                                                 			ItemMeta meta5 = reward5.getItemMeta();
			                                                 			ItemMeta meta6 = reward6.getItemMeta();
			                                                 			ItemMeta meta7 = reward7.getItemMeta();
			                                                 			ItemMeta meta8 = reward8.getItemMeta();
			                                                 			ItemMeta meta9 = reward9.getItemMeta();
			                                                 			ItemMeta meta10 = reward10.getItemMeta();
			                                                 			ItemMeta meta11 = reward11.getItemMeta();
			                                                 			ItemMeta meta12 = reward12.getItemMeta();
			                                                 			ItemMeta meta13 = reward13.getItemMeta();
			                                                 			ItemMeta meta14 = reward14.getItemMeta();
			                                                 			ItemMeta meta15 = reward15.getItemMeta();
			                                                 			ItemMeta meta16 = reward16.getItemMeta();
			                                                 			ItemMeta meta17 = reward17.getItemMeta();
			                                                 			ItemMeta meta18 = reward18.getItemMeta();
			                                                 			ChatColor MetaColor = Meta123.get(p);
			                                                 			if(Meta3 == 0) {
			                                                 				meta1.setDisplayName(MetaColor + "Keeps the Doctors away!");
			                                                 				meta2.setDisplayName(MetaColor + "The food of the poor");
			                                                 				meta3.setDisplayName(MetaColor + "Bacon!");
			                                                 				meta4.setDisplayName(MetaColor + "GOLDEN APPLE. ERMAGAWD");
			                                                 				meta5.setDisplayName(MetaColor + "The apple from the Gods");
			                                                 				meta6.setDisplayName(MetaColor + "This smells... Fishy");
			                                                 				meta7.setDisplayName(MetaColor + "The cake is a lie");
			                                                 				meta8.setDisplayName(MetaColor + "ME WANT COOKIE");
			                                                 				meta9.setDisplayName(MetaColor + "Nice Melons :3");
			                                                 				meta10.setDisplayName(MetaColor + "KFC");
			                                                 				meta11.setDisplayName(MetaColor + "The finest beef");
			                                                 				meta12.setDisplayName(MetaColor + "Mmm... Delicious!");
			                                                 				meta13.setDisplayName(MetaColor + "Good for eyes");
			                                                 				meta14.setDisplayName(MetaColor + "POTATO");
			                                                 				meta15.setDisplayName(MetaColor + "POTATO WITH SWAG");
			                                                 				meta16.setDisplayName(MetaColor + "Le Golden Carrot");
			                                                 				meta17.setDisplayName(MetaColor + "BEST PIE EVER!!!");
			                                                 				meta18.setDisplayName(MetaColor + "Nutritious eyes");
			                                                 				
			                                                 			}
			                                                 			if(Meta3 == 1) {
			                                                 				meta1.setDisplayName(MetaColor + "Used for Apple Juice");
			                                                 				meta2.setDisplayName(MetaColor + "Fancy Bread");
			                                                 				meta3.setDisplayName(MetaColor + "Yummy Pork");
			                                                 				meta4.setDisplayName(MetaColor + "Apple of Power");
			                                                 				meta5.setDisplayName(MetaColor + "Very Delicious Apple");
			                                                 				meta6.setDisplayName(MetaColor + "Firegred's delicious fish");
			                                                 				meta7.setDisplayName(MetaColor + "The cake is a spy");
			                                                 				meta8.setDisplayName(MetaColor + "It's a Cookie!");
			                                                 				meta9.setDisplayName(MetaColor + "I <3 Melons");
			                                                 				meta10.setDisplayName(MetaColor + "PopEyes");
			                                                 				meta11.setDisplayName(MetaColor + "SteakHouse");
			                                                 				meta12.setDisplayName(MetaColor + "Fancy Dead food");
			                                                 				meta13.setDisplayName(MetaColor + "Horrible stabbing weapon");
			                                                 				meta14.setDisplayName(MetaColor + "Epic Potato");
			                                                 				meta15.setDisplayName(MetaColor + "Potato with the force");
			                                                 				meta16.setDisplayName(MetaColor + "Breaks your teeth");
			                                                 				meta17.setDisplayName(MetaColor + "Pumpkin dinner");
			                                                 				meta18.setDisplayName(MetaColor + "Eww. Eyes");
			                                                 				
			                                                 			}
			                                                 			if(Meta3 == 2) {
			                                                 				meta1.setDisplayName(MetaColor + "Apple with wisdom");
			                                                 				meta2.setDisplayName(MetaColor + "Garlic Bread");
			                                                 				meta3.setDisplayName(MetaColor + "Throw this at terrorists");
			                                                 				meta4.setDisplayName(MetaColor + "Apple, with Gold");
			                                                 				meta5.setDisplayName(MetaColor + "SHINY Apple");
			                                                 				meta6.setDisplayName(MetaColor + "British Fish");
			                                                 				meta7.setDisplayName(MetaColor + "The spy is a cake!");
			                                                 				meta8.setDisplayName(MetaColor + "Too much sweets!");
			                                                 				meta9.setDisplayName(MetaColor + "Healthy food");
			                                                 				meta10.setDisplayName(MetaColor + "Los Pollos Hermanos");
			                                                 				meta11.setDisplayName(MetaColor + "Cow Food");
			                                                 				meta12.setDisplayName(MetaColor + "Cannibal food");
			                                                 				meta13.setDisplayName(MetaColor + "Eat this daily!");
			                                                 				meta14.setDisplayName(MetaColor + "Potato of wisdom");
			                                                 				meta15.setDisplayName(MetaColor + "Don't eat me :(");
			                                                 				meta16.setDisplayName(MetaColor + "Gives you carrot powers");
			                                                 				meta17.setDisplayName(MetaColor + "Pumpkin food");
			                                                 				meta18.setDisplayName(MetaColor + "Yum");
			                                                 				
			                                                 			}
			                                                 			if(Meta3 == 3) {
			                                                 				meta1.setDisplayName(MetaColor + "Happy Apple :)");
			                                                 				meta2.setDisplayName(MetaColor + "Rich people's trash");
			                                                 				meta3.setDisplayName(MetaColor + "Piggy food");
			                                                 				meta4.setDisplayName(MetaColor + "Apple that heals you");
			                                                 				meta5.setDisplayName(MetaColor + "Apple with magical powers");
			                                                 				meta6.setDisplayName(MetaColor + "Fish Sticks");
			                                                 				meta7.setDisplayName(MetaColor + "Please Eat Me");
			                                                 				meta8.setDisplayName(MetaColor + "Cook me Cookies");
			                                                 				meta9.setDisplayName(MetaColor + "Reddish food");
			                                                 				meta10.setDisplayName(MetaColor + "HELL YA, CHICKEN");
			                                                 				meta11.setDisplayName(MetaColor + "Ribeye");
			                                                 				meta12.setDisplayName(MetaColor + "Flesh of a Zombie");
			                                                 				meta13.setDisplayName(MetaColor + "I am healthy, eat me");
			                                                 				meta14.setDisplayName(MetaColor + "Potato that knows all");
			                                                 				meta15.setDisplayName(MetaColor + "I have poison");
			                                                 				meta16.setDisplayName(MetaColor + "Harness carrot power!");
			                                                 				meta17.setDisplayName(MetaColor + "Came from a Pumpkin");
			                                                 				meta18.setDisplayName(MetaColor + "British cooking");
			                                                 				
			                                                 			}
			                                                 			if(Meta3 == 4) {
			                                                 				meta1.setDisplayName(MetaColor + "Sad Apple :(");
			                                                 				meta2.setDisplayName(MetaColor + "1791 French Bread");
			                                                 				meta3.setDisplayName(MetaColor + "American Cooking");
			                                                 				meta4.setDisplayName(MetaColor + "Expensive Apple");
			                                                 				meta5.setDisplayName(MetaColor + "Herobrine's apple");
			                                                 				meta6.setDisplayName(MetaColor + "Salmon");
			                                                 				meta7.setDisplayName(MetaColor + "Mr. CakeFabulous");
			                                                 				meta8.setDisplayName(MetaColor + "Keep away from Cookie Monster");
			                                                 				meta9.setDisplayName(MetaColor + "Melon Slices!");
			                                                 				meta10.setDisplayName(MetaColor + "ChickenFingers");
			                                                 				meta11.setDisplayName(MetaColor + "Filet Mignon");
			                                                 				meta12.setDisplayName(MetaColor + "North Korean Food");
			                                                 				meta13.setDisplayName(MetaColor + "Super Carrot!");
			                                                 				meta14.setDisplayName(MetaColor + "Super Potato!");
			                                                 				meta15.setDisplayName(MetaColor + "Super Poison Potato!");
			                                                 				meta16.setDisplayName(MetaColor + "Super Amazing Carrot!");
			                                                 				meta17.setDisplayName(MetaColor + "R.I.P Mr. Pumpkin");
			                                                 				meta18.setDisplayName(MetaColor + "North Korean SnackFood");
			                                                 				
			                                                 			}
			                                                 			reward1.setItemMeta(meta1);
			                                                 			reward2.setItemMeta(meta2);
			                                                 			reward3.setItemMeta(meta3);
			                                                 			reward4.setItemMeta(meta4);
			                                                 			reward5.setItemMeta(meta5);
			                                                 			reward6.setItemMeta(meta6);
			                                                 			reward7.setItemMeta(meta7);
			                                                 			reward8.setItemMeta(meta8);
			                                                 			reward9.setItemMeta(meta9);
			                                                 			reward10.setItemMeta(meta10);
			                                                 			reward11.setItemMeta(meta11);
			                                                 			reward12.setItemMeta(meta12);
			                                                 			reward13.setItemMeta(meta13);
			                                                 			reward14.setItemMeta(meta14);
			                                                 			reward15.setItemMeta(meta15);
			                                                 			reward16.setItemMeta(meta16);
			                                                 			reward17.setItemMeta(meta17);
			                                                 			reward18.setItemMeta(meta18);
			                                                 			
			                                                 			if(rewards == 0) {
			                                                 	
			                                                 				Opens.addItem(reward1);
			                                                 				Loot.put(p, reward1);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 1) {
			                                                 				Opens.addItem(reward2);
			                                                 				Loot.put(p, reward2);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 2) {
			                                                 				Opens.addItem(reward3);
			                                                 				Loot.put(p, reward3);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 3) {
			                                                 				Opens.addItem(reward4);
			                                                 				Loot.put(p, reward4);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 4) {
			                                                 				Opens.addItem(reward5);
			                                                 				Loot.put(p, reward5);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 5) {
			                                                 				Opens.addItem(reward6);
			                                                 				Loot.put(p, reward6);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 6) {
			                                                 				Opens.addItem(reward7);
			                                                 				Loot.put(p, reward7);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 7) {
			                                                 				Opens.addItem(reward8);
			                                                 				Loot.put(p, reward8);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 8) {
			                                                 				Opens.addItem(reward9);
			                                                 				Loot.put(p, reward9);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 9) {
			                                                 				Opens.addItem(reward10);
			                                                 				Loot.put(p, reward10);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 10) {
			                                                 				Opens.addItem(reward11);
			                                                 				Loot.put(p, reward11);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 11) {
			                                                 				Opens.addItem(reward12);
			                                                 				Loot.put(p, reward12);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 12) {
			                                                 				Opens.addItem(reward13);
			                                                 				Loot.put(p, reward13);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 13) {
			                                                 				Opens.addItem(reward14);
			                                                 				Loot.put(p, reward14);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 14) {
			                                                 				Opens.addItem(reward15);
			                                                 				Loot.put(p, reward15);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 15) {
			                                                 				Opens.addItem(reward16);
			                                                 				Loot.put(p, reward16);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 16) {
			                                                 				Opens.addItem(reward17);
			                                                 				Loot.put(p, reward17);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
			                                                 			if(rewards == 17) {
			                                                 				Opens.addItem(reward18);
			                                                 				Loot.put(p, reward18);
			                                                 				p.openInventory(Opens);
			                                                 				Meta123.remove(p);
			                                                 			}
				                                                 	}
				                                                 	else if(prob == 1) {
				                                                 		Random Arrows = new Random();
				                                                 		Random Prize = new Random();
				                                                 		Random numb = new Random();
				                                                 		
				                                                 		int Arro = Arrows.nextInt(350) + 100;
				                                                 		String Arro2 = String.valueOf(Arro);
				                                                 		
				                                                 		int Pri = Prize.nextInt(4);
				                                                 		int next1 = numb.nextInt(10);
				                                                 		int next2 = numb.nextInt(20);
				                                                 		int next3 = numb.nextInt(30);
				                                                 		int next4 = numb.nextInt(40);
				                                                 		int next5 = numb.nextInt(50);
				                                                 		int next6 = numb.nextInt(60);
				                                                 		ItemStack GBow = new ItemStack(Material.BOW, 1);
				                                                 		ItemMeta GBowMeta = GBow.getItemMeta();
				                                                 		GBowMeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual Bow: " + Hats.effectname[ii - 1]);
				                                                 	    ArrayList<String> GBowLore = new ArrayList<String>();
				                                                 	    GBowLore.clear();
				                                                 	    GBowLore.add(ChatColor.GREEN + "Shots left: " + ChatColor.YELLOW + Arro2);
				                                                 		GBowMeta.setLore(GBowLore);
				                                                 	    GBow.setItemMeta(GBowMeta);
				                                                 		
				                                                 		Opens.addItem(GBow);
		                                                 				Loot.put(p, GBow);
		                                                 				p.openInventory(Opens);
		                                                 				Meta123.remove(p);
		                                                 				
				                                                 		if(ii == 1) {
				                                                 			//ItemStack reward2 = new ItemStack(Material.MONSTER_EGG, next2 + 1, (short)120);
				                                                 			
				                                                 			ItemStack reward3 = new ItemStack(Material.IRON_AXE, 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.COAL, next6 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.STICK, 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.BOW, 1);
				                                                 			
				                                                 			
				                                                 			
				                                                 		}
				                                                 		if(ii == 2) {
				                                                 			ItemStack reward2 = new ItemStack(Material.QUARTZ, next2 + 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.FEATHER, 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.BONE, 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.SUGAR, next4 + 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.PAPER, next3 + 1);
				                                                 		}
				                                                 		if(ii == 3) {
				                                                 			ItemStack reward2 = new ItemStack(Material.GLOWSTONE_DUST, next6+1);
				                                                 			ItemStack reward3 = new ItemStack(Material.APPLE, next2 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.FIRE, next6 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.EGG, next5 + 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.FLINT, 1);
				                                                 			
				                                                 			
				                                                 		}
				                                                 		if(ii == 4) {
				                                                 			ItemStack reward2 = new ItemStack(Material.DIAMOND, 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.APPLE, next2 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.SPONGE, next6 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.SNOW_BALL, next6 + 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.MONSTER_EGG, next2 + 1, (short)94);                                 
				                                                 			
				                                                 		}
				                                                 		if(ii == 5) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 6) {
				                                                 			ItemStack reward2 = new ItemStack(Material.SLIME_BALL, next2 + 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.SULPHUR, next6 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.STICK, next6 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.IRON_INGOT, 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.MONSTER_EGG, next2 + 1, (short)50);
				                                                 			
				                                                 			
				                                                 			
				                                                 		}
				                                                 		if(ii == 7) {
				                                                 			ItemStack reward2 = new ItemStack(Material.GOLD_AXE, 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.FIREWORK, next4 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.BOW, 1);
				                                                 			
				                                                 			
				                                                 		}
				                                                 		if(ii == 8) {
				                                                 			ItemStack reward2 = new ItemStack(Material.BLAZE_ROD, 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.FIRE, next6 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.APPLE, next2 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.FISHING_ROD, 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.BLAZE_POWDER, 1);
				                                                 		}
				                                                 		if(ii == 9) {
				                                                 			ItemStack reward2 = new ItemStack(Material.IRON_SWORD, 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.CLAY_BALL, next6 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.GHAST_TEAR, next2 + 1);
				                                                 			
				                                                 			
				      		                                            
				                                                 		
				                                                 		}
				                                                 		if(ii == 10) {
				                                                 			ItemStack reward2 = new ItemStack(Material.MONSTER_EGG, next2 + 1, (short)120);
				                                                 			ItemStack reward3 = new ItemStack(Material.IRON_AXE, 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.GOLD_INGOT, next3 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.EMERALD_BLOCK, next3 + 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.BOW, 1);
				                                                 		}
				                                                 		if(ii == 11) {
				                                                 			ItemStack reward2 = new ItemStack(Material.DIAMOND, next3 + 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.WATCH, 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.REDSTONE, next3 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.IRON_AXE, 1);
				                                                 			ItemStack reward6 = new ItemStack(Material.FISHING_ROD, 1);
				                                                 			
				                                                 		}
				                                                 		if(ii == 12) {
				                                                 			ItemStack reward2 = new ItemStack(Material.GOLD_AXE, 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.CLAY_BRICK, next3 + 1);
				                                                 			//ItemStack reward4 = new ItemStack(Material.)
				                                                 		}
				                                                 		if(ii == 13) {
				                                                 			ItemStack reward2 = new ItemStack(Material.REDSTONE_TORCH_ON, next6 + 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.SLIME_BALL, next3 + 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.STICK, 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.APPLE, next2 + 1);
				                                                 		}
				                                                 		if(ii == 14) {
				                                                 			ItemStack reward2 = new ItemStack(Material.LAVA, next6 + 1);
				                                                 			ItemStack reward3 = new ItemStack(Material.NETHER_STAR, 1);
				                                                 			ItemStack reward4 = new ItemStack(Material.APPLE, next2 + 1);
				                                                 			ItemStack reward5 = new ItemStack(Material.BLAZE_ROD, 1);
				                                                 			
				                                                 		}   
				                                                 		if(ii == 15) {
				                                                 			ItemStack reward2 = new ItemStack(Material.STICK, 1);
				                                                 		}
				                                                 		if(ii == 16) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 17) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 18) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 19) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 20) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 21) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 22) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 23) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 24) {
				                                                 			
				                                              
				                                                 		}
				                                                 		if(ii == 25) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 26) {
				                                                 			
				                                                 		}
				                                                 		if(ii == 27) {
				                                                 			
				                                                 		}
				                                                 	}
				                                                 	else if(prob == 2) {
				                                                 		//Random itemtype = new Random();
				                                                 		
				                                                 		Random unbreaking = new Random();
				                                                 		Random enchants1 = new Random();
				                                                 		Random enchants2 = new Random();
				                                                 		Random enchantID = new Random();
				                                                 		Random enchantID2 = new Random();
				                                                 		Random itemType = new Random();
				                                                 		Random itemder = new Random();
				                                                 		
				                                                 		//int itema = itemtype.nextInt(5);
				                                                 		
				                                                 		int itemMaterial = itemType.nextInt(5);
				                                                 		int itemKind = itemder.nextInt(4);
				                                                 		
				                                                 		int efficiency = enchants1.nextInt(8);
				                                                 		int silktouch = enchants1.nextInt(4);
				                                                 		int Fortune = enchants1.nextInt(6);
				                                                 		
				                                                 		int sharp1 = enchants1.nextInt(8);
				                                                 		int smite1 = enchants1.nextInt(8);
				                                                 		int bane1 = enchants1.nextInt(8);
				                                                 		int knockback1 = enchants1.nextInt(5);
				                                                 		int fireaspect1 = enchants1.nextInt(5);
				                                                 		int looting1 = enchants1.nextInt(6);
				                                                 		
				                                                 		int sharp2 = enchants2.nextInt(8);
				                                                 		int smite2 = enchants2.nextInt(8);
				                                                 		int bane2 = enchants2.nextInt(8);
				                                                 		int knockback2 = enchants2.nextInt(5);
				                                                 		int fireaspect2 = enchants2.nextInt(5);
				                                                 		int looting2 = enchants2.nextInt(6);
				                                                 		
				                                                 		int Axe = enchantID.nextInt(6);
				                                                 		
				                                                 		int enchantsword1 = 16 + enchantID.nextInt(6);
				                                                 		int enchantsword2 = 16 + enchantID2.nextInt(6);
				                                                 		int enchantsword22;
				                                                 		if(enchantsword1 == enchantsword2) {
				                                                 			if(enchantsword2 == 21) {
				                                                 			  enchantsword22 = enchantsword2 - 1;
				                                                 			}
				                                                 			else {
				                                                 				
				                                                 			
				                                                 			enchantsword22 = enchantsword2 + 1;
				                                                 			}
				                                                 		}
				                                                 		else {
				                                                 			enchantsword22 = enchantsword2;
				                                                 		}
				                                                 		
				                                                 		
				                                                 		int unbreakingtype = unbreaking.nextInt(5);
				                                                 		
				                                                 		int utilityenchant = 32 + enchantID.nextInt(3);
				                                                 		ItemStack WSword = new ItemStack(Material.WOOD_SWORD, 1);
				                                                 		ItemStack WAxe = new ItemStack(Material.WOOD_AXE, 1);
				                                                 		ItemStack WShovel = new ItemStack(Material.WOOD_SPADE, 1);
				                                                 		ItemStack WPickaxe = new ItemStack(Material.WOOD_PICKAXE, 1);
				                                                 		
				                                                 		ItemStack SSword = new ItemStack(Material.STONE_SWORD, 1);
				                                                 		ItemStack SAxe = new ItemStack(Material.STONE_AXE, 1);
				                                                 		ItemStack SShovel = new ItemStack(Material.STONE_SPADE, 1);
				                                                 		ItemStack SPickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
				                                                 		
				                                                 		ItemStack ISword = new ItemStack(Material.IRON_SWORD, 1);
				                                                 		ItemStack IAxe = new ItemStack(Material.IRON_AXE, 1);
				                                                 		ItemStack IShovel = new ItemStack(Material.IRON_SPADE, 1);
				                                                 		ItemStack IPickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
				                                                 		
				                                                 		ItemStack GSword = new ItemStack(Material.GOLD_SWORD, 1);
				                                                 		ItemStack GAxe = new ItemStack(Material.GOLD_AXE, 1);
				                                                 		ItemStack GShovel = new ItemStack(Material.GOLD_SPADE, 1);
				                                                 		ItemStack GPickaxe = new ItemStack(Material.GOLD_PICKAXE, 1);
				                                                 		
				                                                 		ItemStack DSword = new ItemStack(Material.DIAMOND_SWORD, 1);
				                                                 		ItemStack DAxe = new ItemStack(Material.DIAMOND_AXE, 1);
				                                                 		ItemStack DShovel = new ItemStack(Material.DIAMOND_SPADE, 1);
				                                                 		ItemStack DPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1); 
				                                                 		
				                                                 		if(unbreakingtype != 0) {
				                                                 		WSword.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		SSword.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		ISword.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		GSword.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		DSword.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		
				                                                 		WAxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		SAxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		IAxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		GAxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		DAxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		
				                                                 		WShovel.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		SShovel.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		IShovel.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		GShovel.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		DShovel.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		
				                                                 		WPickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		SPickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		IPickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		GPickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		DPickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingtype);
				                                                 		}
//				                                                 		WSword.getItemMeta().setDisplayName(Meta123 + "Wooden Sword");
//				                                                 		SSword.getItemMeta().setDisplayName(Meta123 + "Stone Sword");
//				                                                 		ISword.getItemMeta().setDisplayName(Meta123 + "Iron Sword");
//				                                                 		GSword.getItemMeta().setDisplayName(Meta123 + "Golden Sword");
//				                                                 		DSword.getItemMeta().setDisplayName(Meta123 + "Diamond Sword");
//				                                                 		
//				                                                 		WAxe.getItemMeta().setDisplayName(Meta123 + "Wooden Axe");
//				                                                 		SAxe.getItemMeta().setDisplayName(Meta123 + "Stone Axe");
//				                                                 		IAxe.getItemMeta().setDisplayName(Meta123 + "Iron Axe");
//				                                                 		GAxe.getItemMeta().setDisplayName(Meta123 + "Golden Axe");
//				                                                 		DAxe.getItemMeta().setDisplayName(Meta123 + "Diamond Axe");
//				                                                 		
//				                                                 		WShovel.getItemMeta().setDisplayName(Meta123 + "Wooden Shovel");
//				                                                 		SShovel.getItemMeta().setDisplayName(Meta123 + "Stone Shovel");
//				                                                 		IShovel.getItemMeta().setDisplayName(Meta123 + "Iron Shovel");
//				                                                 		GShovel.getItemMeta().setDisplayName(Meta123 + "Golden Shovel");
//				                                                 		DShovel.getItemMeta().setDisplayName(Meta123 + "Diamond Shovel");
//				                                                 		
//				                                                 		WPickaxe.getItemMeta().setDisplayName(Meta123 + "Wooden Pickaxe");
//				                                                 		SPickaxe.getItemMeta().setDisplayName(Meta123 + "Stone Pickaxe");
//				                                                 		IPickaxe.getItemMeta().setDisplayName(Meta123 + "Iron Pickaxe");
//				                                                 		GPickaxe.getItemMeta().setDisplayName(Meta123 + "Golden Pickaxe");
//				                                                 		DPickaxe.getItemMeta().setDisplayName(Meta123 + "Diamond Pickaxe");
				                                                 		
				                                                 		ItemMeta WSwordMeta = WSword.getItemMeta();
				                                                 		ArrayList<String> WSwordLore = new ArrayList<String>();
				                                                 		WSwordLore.clear();
				                                                 		WSwordMeta.setDisplayName(ChatColor.GOLD + "Strange Wooden sword");
				                                                 		WSwordLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		WSwordMeta.setLore(WSwordLore);
				                                                 		WSword.setItemMeta(WSwordMeta);
				                                                 		
				                                                 		ItemMeta SSwordMeta = SSword.getItemMeta();
				                                                 		ArrayList<String> SSwordLore = new ArrayList<String>();
				                                                 		SSwordLore.clear();
				                                                 		SSwordMeta.setDisplayName(ChatColor.GOLD + "Strange Stone sword");
				                                                 		SSwordLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		SSwordMeta.setLore(SSwordLore);
				                                                 		SSword.setItemMeta(SSwordMeta);
				                                                 		
				                                                 		ItemMeta ISwordMeta = ISword.getItemMeta();
				                                                 		ArrayList<String> ISwordLore = new ArrayList<String>();
				                                                 		ISwordLore.clear();
				                                                 		ISwordMeta.setDisplayName(ChatColor.GOLD + "Strange Iron sword");
				                                                 		ISwordLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		ISwordMeta.setLore(ISwordLore);
				                                                 		ISword.setItemMeta(ISwordMeta);
				                                                 		
				                                                 		ItemMeta GSwordMeta = GSword.getItemMeta();
				                                                 		ArrayList<String> GSwordLore = new ArrayList<String>();
				                                                 		GSwordLore.clear();
				                                                 		GSwordMeta.setDisplayName(ChatColor.GOLD + "Strange Golden sword");
				                                                 		GSwordLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		GSwordMeta.setLore(GSwordLore);
				                                                 		GSword.setItemMeta(GSwordMeta);
				                                                 		
				                                                 		ItemMeta DSwordMeta = DSword.getItemMeta();
				                                                 		ArrayList<String> DSwordLore = new ArrayList<String>();
				                                                 		DSwordLore.clear();
				                                                 		DSwordMeta.setDisplayName(ChatColor.GOLD + "Strange Diamond sword");
				                                                 		DSwordLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		DSwordMeta.setLore(DSwordLore);
				                                                 		DSword.setItemMeta(DSwordMeta);
				                                                 		
				                                                 		ItemMeta WAxeMeta = WAxe.getItemMeta();
				                                                 		ArrayList<String> WAxeLore = new ArrayList<String>();
				                                                 		WAxeLore.clear();
				                                                 		WAxeMeta.setDisplayName(ChatColor.GOLD + "Strange Wooden axe");
				                                                 		WAxeLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		//WAxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		WAxeMeta.setLore(WAxeLore);
				                                                 		WAxe.setItemMeta(WAxeMeta);
				                                                 		
				                                                 		ItemMeta SAxeMeta = SAxe.getItemMeta();
				                                                 		ArrayList<String> SAxeLore = new ArrayList<String>();
				                                                 		SAxeLore.clear();
				                                                 		SAxeMeta.setDisplayName(ChatColor.GOLD + "Strange Stone axe");
				                                                 		SAxeLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		//SAxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		SAxeMeta.setLore(SAxeLore);
				                                                 		SAxe.setItemMeta(SAxeMeta);
				                                                 		
				                                                 		ItemMeta IAxeMeta = IAxe.getItemMeta();
				                                                 		ArrayList<String> IAxeLore = new ArrayList<String>();
				                                                 		IAxeLore.clear();
				                                                 		IAxeMeta.setDisplayName(ChatColor.GOLD + "Strange Iron axe");
				                                                 		IAxeLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		//IAxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		IAxeMeta.setLore(IAxeLore);
				                                                 		IAxe.setItemMeta(IAxeMeta);
				                                                 		
				                                                 		ItemMeta GAxeMeta = GAxe.getItemMeta();
				                                                 		ArrayList<String> GAxeLore = new ArrayList<String>();
				                                                 		GAxeLore.clear();
				                                                 		GAxeMeta.setDisplayName(ChatColor.GOLD + "Strange Golden axe");
				                                                 		GAxeLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		//GAxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		GAxeMeta.setLore(GAxeLore);
				                                                 		GAxe.setItemMeta(GAxeMeta);
				                                                 		
				                                                 		ItemMeta DAxeMeta = DAxe.getItemMeta();
				                                                 		ArrayList<String> DAxeLore = new ArrayList<String>();
				                                                 		DAxeLore.clear();
				                                                 		DAxeMeta.setDisplayName(ChatColor.GOLD + "Strange Diamond axe");
				                                                 		DAxeLore.add(ChatColor.GREEN + "Entity Kills: " + ChatColor.YELLOW + "0");
				                                                 		//DAxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		DAxeMeta.setLore(DAxeLore);
				                                                 		DAxe.setItemMeta(DAxeMeta);
				                                                 		
				                                                 		ItemMeta WShovelMeta = WShovel.getItemMeta();
				                                                 		ArrayList<String> WShovelLore = new ArrayList<String>();
				                                                 		WShovelLore.clear();
				                                                 		WShovelMeta.setDisplayName(ChatColor.GOLD + "Strange Wooden shovel");
				                                                 		WShovelLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		WShovelMeta.setLore(WShovelLore);
				                                                 		WShovel.setItemMeta(WShovelMeta);
				                                                 		
				                                                 		ItemMeta SShovelMeta = SShovel.getItemMeta();
				                                                 		ArrayList<String> SShovelLore = new ArrayList<String>();
				                                                 		SShovelLore.clear();
				                                                 		SShovelMeta.setDisplayName(ChatColor.GOLD + "Strange Stone shovel");
				                                                 		SShovelLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		SShovelMeta.setLore(SShovelLore);
				                                                 		SShovel.setItemMeta(SShovelMeta);
				                                                 		
				                                                 		ItemMeta IShovelMeta = IShovel.getItemMeta();
				                                                 		ArrayList<String> IShovelLore = new ArrayList<String>();
				                                                 		IShovelLore.clear();
				                                                 		IShovelMeta.setDisplayName(ChatColor.GOLD + "Strange Iron shovel");
				                                                 		IShovelLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		IShovelMeta.setLore(IShovelLore);
				                                                 		IShovel.setItemMeta(IShovelMeta);
				                                                 		
				                                                 		ItemMeta GShovelMeta = GShovel.getItemMeta();
				                                                 		ArrayList<String> GShovelLore = new ArrayList<String>();
				                                                 		GShovelLore.clear();
				                                                 		GShovelMeta.setDisplayName(ChatColor.GOLD + "Strange Golden shovel");
				                                                 		GShovelLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		GShovelMeta.setLore(GShovelLore);
				                                                 		GShovel.setItemMeta(GShovelMeta);
				                                                 		
				                                                 		ItemMeta DShovelMeta = DShovel.getItemMeta();
				                                                 		ArrayList<String> DShovelLore = new ArrayList<String>();
				                                                 		DShovelLore.clear();
				                                                 		DShovelMeta.setDisplayName(ChatColor.GOLD + "Strange Diamond shovel");
				                                                 		DShovelLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		DShovelMeta.setLore(DShovelLore);
				                                                 		DShovel.setItemMeta(DShovelMeta);
				                                                 		
				                                                 		ItemMeta WPickaxeMeta = WPickaxe.getItemMeta();
				                                                 		ArrayList<String> WPickaxeLore = new ArrayList<String>();
				                                                 		WPickaxeLore.clear();
				                                                 		WPickaxeMeta.setDisplayName(ChatColor.GOLD + "Strange Wooden pickaxe");
				                                                 		WPickaxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		WPickaxeMeta.setLore(WPickaxeLore);
				                                                 		WPickaxe.setItemMeta(WPickaxeMeta);
				                                                 		
				                                                 		ItemMeta SPickaxeMeta = SPickaxe.getItemMeta();
				                                                 		ArrayList<String> SPickaxeLore = new ArrayList<String>();
				                                                 		SPickaxeLore.clear();
				                                                 		SPickaxeMeta.setDisplayName(ChatColor.GOLD + "Strange Stone pickaxe");
				                                                 		SPickaxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		SPickaxeMeta.setLore(SPickaxeLore);
				                                                 		SPickaxe.setItemMeta(SPickaxeMeta);
				                                                 		
				                                                 		ItemMeta IPickaxeMeta = IPickaxe.getItemMeta();
				                                                 		ArrayList<String> IPickaxeLore = new ArrayList<String>();
				                                                 		IPickaxeLore.clear();
				                                                 		IPickaxeMeta.setDisplayName(ChatColor.GOLD + "Strange Iron pickaxe");
				                                                 		IPickaxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		IPickaxeMeta.setLore(IPickaxeLore);
				                                                 		IPickaxe.setItemMeta(IPickaxeMeta);
				                                                 		
				                                                 		ItemMeta GPickaxeMeta = GPickaxe.getItemMeta();
				                                                 		ArrayList<String> GPickaxeLore = new ArrayList<String>();
				                                                 		GPickaxeLore.clear();
				                                                 		GPickaxeMeta.setDisplayName(ChatColor.GOLD + "Strange Golden pickaxe");
				                                                 		GPickaxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		GPickaxeMeta.setLore(GPickaxeLore);
				                                                 		GPickaxe.setItemMeta(GPickaxeMeta);
				                                                 		
				                                                 		ItemMeta DPickaxeMeta = DPickaxe.getItemMeta();
				                                                 		ArrayList<String> DPickaxeLore = new ArrayList<String>();
				                                                 		DPickaxeLore.clear();
				                                                 		DPickaxeMeta.setDisplayName(ChatColor.GOLD + "Strange Diamond pickaxe");
				                                                 		DPickaxeLore.add(ChatColor.GREEN + "Blocks destroyed: " + ChatColor.YELLOW + "0");
				                                                 		DPickaxeMeta.setLore(DPickaxeLore);
				                                                 		DPickaxe.setItemMeta(DPickaxeMeta);
				                                                 		
				                                                 		
				                                                 		
				                                                 		
				                                                 		
				                                                 		
				                                                 		
				                                            
				                                                 			
				                                                 				if(enchantsword1 == 16 && sharp1 != 0) {
				                                                 				WSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
				                                                 				SSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
				                                                 				ISword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
				                                                 				GSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
				                                                 				DSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
				                                                 				}
				                                                 				if(enchantsword1 == 17 && smite1 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 				}
				                                                 				if(enchantsword1 == 18 && bane1 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 				}
				                                                 				if(enchantsword1 == 19 && knockback1 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback1);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback1);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback1);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback1);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback1);
				                                                 				}
				                                                 				if(enchantsword1 == 20 && fireaspect1 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect1);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect1);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect1);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect1);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect1);
				                                                 				}
				                                                 				if(enchantsword1 == 21 && looting1 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting1);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting1);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting1);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting1);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting1);
				                                                 				}
				                                                 				if(enchantsword22 == 16 && sharp2 != 0) {
					                                                 				WSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp2);
					                                                 				SSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp2);
					                                                 				ISword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp2);
					                                                 				GSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp2);
					                                                 				DSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp2);
					                                                 				}
				                                                 				
				                                                 				if(enchantsword22 == 17 && smite2 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite2);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite2);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite2);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite2);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite2);
				                                                 				}
				                                                 				if(enchantsword22 == 18 && bane2 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane2);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane2);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane2);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane2);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane2);
				                                                 				}
				                                                 				if(enchantsword22 == 19 && knockback2 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback2);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback2);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback2);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback2);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback2);
				                                                 				}
				                                                 				if(enchantsword22 == 20 && fireaspect2 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect2);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect2);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect2);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect2);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, fireaspect2);
				                                                 				}
				                                                 				if(enchantsword22 == 21 && looting2 != 0) {
				                                                 					WSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting2);
				                                                 					SSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting2);
				                                                 					ISword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting2);
				                                                 					GSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting2);
				                                                 					DSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, looting2);
				                                                 				
				                                                 			}
				                                                 			
				                                                 				if(utilityenchant == 32 && efficiency != 0) {
				                                                 					WShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					SShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					IShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					GShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					DShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					
				                                                 					WPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					SPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					IPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					GPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					DPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					
				                                                 				}
				                                                 				if(utilityenchant == 33 && silktouch != 0) {
				                                                 					WShovel.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					SShovel.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					IShovel.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					GShovel.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					DShovel.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					
				                                                 					WPickaxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					SPickaxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					IPickaxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					GPickaxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					DPickaxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 				}
				                                                 				if(utilityenchant == 34 && Fortune != 0) {
				                                                 					WShovel.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					SShovel.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					IShovel.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					GShovel.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					DShovel.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					
				                                                 					WPickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					SPickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					IPickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					GPickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					DPickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 			
				                                                 				
				                                                 			}
				                                                 		
				                                                 				if(Axe == 0 && efficiency != 0) {
				                                                 					WAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					SAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					IAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					GAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 					DAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
				                                                 				
				                                                 				}
				                                                 				if(Axe == 1 && silktouch != 0) {
				                                                 					WAxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					SAxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					IAxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					GAxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 					DAxe.addUnsafeEnchantment(Enchantment.SILK_TOUCH, silktouch);
				                                                 				}
				                                                 				if(Axe == 2 && Fortune != 0) {
				                                                 					WAxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					SAxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					IAxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					GAxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 					DAxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Fortune);
				                                                 				}
				                                                 				if(Axe == 3 && sharp1 != 0) {
				                                                 					WAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
					                                                 				SAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
					                                                 				IAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
					                                                 				GAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
					                                                 				DAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharp1);
				                                                 				}
				                                                 				if(Axe == 4 && smite1 != 0) {
				                                                 					WAxe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					SAxe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					IAxe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					GAxe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 					DAxe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, smite1);
				                                                 				}
				                                                 				if(Axe == 5 && bane1 != 0) {
				                                                 					WAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					SAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					IAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					GAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 					DAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, bane1);
				                                                 			
				                                                 			}
				                                                 		if(itemMaterial == 0) {
				                                                 			if(itemKind == 0) {
				                                                 				Opens.addItem(WSword);
				                                                 				Loot.put(p, WSword);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 1) {
				                                                 				Opens.addItem(WAxe);
				                                                 				Loot.put(p, WAxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 2) {
				                                                 				Opens.addItem(WShovel);
				                                                 				Loot.put(p, WShovel);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 3) {
				                                                 				Opens.addItem(WPickaxe);
				                                                 				Loot.put(p, WPickaxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 		}
				                                                 		if(itemMaterial == 1) {
				                                                 			if(itemKind == 0) {
				                                                 				Opens.addItem(SSword);
				                                                 				Loot.put(p, SSword);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 1) {
				                                                 				Opens.addItem(SAxe);
				                                                 				Loot.put(p, SAxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 2) {
				                                                 				Opens.addItem(SShovel);
				                                                 				Loot.put(p, SShovel);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 3) {
				                                                 				Opens.addItem(SPickaxe);
				                                                 				Loot.put(p, SPickaxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 		}
				                                                 		if(itemMaterial == 2) {
				                                                 			if(itemKind == 0) {
				                                                 				Opens.addItem(ISword);
				                                                 				Loot.put(p, ISword);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 1) {
				                                                 				Opens.addItem(IAxe);
				                                                 				Loot.put(p, IAxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 2) {
				                                                 				Opens.addItem(IShovel);
				                                                 				Loot.put(p, IShovel);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 3) {
				                                                 				Opens.addItem(IPickaxe);
				                                                 				Loot.put(p, IPickaxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 		}
				                                                 		if(itemMaterial == 3) {
				                                                 			if(itemKind == 0) {
				                                                 				Opens.addItem(GSword);
				                                                 				Loot.put(p, GSword);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 1) {
				                                                 				Opens.addItem(GAxe);
				                                                 				Loot.put(p, GAxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 2) {
				                                                 				Opens.addItem(GShovel);
				                                                 				Loot.put(p, GShovel);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 3) {
				                                                 				Opens.addItem(GPickaxe);
				                                                 				Loot.put(p, GPickaxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 		}
				                                                 		if(itemMaterial == 4) {
				                                                 			if(itemKind == 0) {
				                                                 				Opens.addItem(DSword);
				                                                 				Loot.put(p, DSword);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 1) {
				                                                 				Opens.addItem(DAxe);
				                                                 				Loot.put(p, DAxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 2) {
				                                                 				Opens.addItem(DShovel);
				                                                 				Loot.put(p, DShovel);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 			if(itemKind == 3) {
				                                                 				Opens.addItem(DPickaxe);
				                                                 				Loot.put(p, DPickaxe);
				                                                 				p.openInventory(Opens);
				                                                 				Meta123.remove(p);
				                                                 			}
				                                                 		}	
				                                                 		
				                                                 	
				                                                 	}
				                                                 	else if(prob == 3) {
				                                                 		Random rand1 = new Random();
				                                                 		ItemStack stack[] = {glass, glass1, glass2, glass3, glass4, glass5, glass6, glass7, glass8, glass9, glass10, glass11, glass12, glass13, glass14, glass15};
				                                                 		
				                                                 		int chance = rand1.nextInt(16);
				                                                 		
				                                                 		ItemStack glass = new ItemStack(stack[chance]);
				                                                 		ItemMeta glassmeta = glass.getItemMeta();
				                                                 		glassmeta.setDisplayName(ChatColor.BLUE + "Water-Breathing" + Meta123.get(p) + " Helmet");
				                                                 		glass.setItemMeta(glassmeta);
				                                                 		
				                                                 		Opens.addItem(glass);
		                                                 				Loot.put(p, glass);
		                                                 				p.openInventory(Opens);
		                                                 				Meta123.remove(p);
				                                                 		
				                                                 	}
				                                                 	else if(prob == 4) {
				                                                 		Random rand1 = new Random();
				                                                 		Random rand2 = new Random();
				                                                 		Random rand3 = new Random();
				                                                 		Random rand4 = new Random();
				                                                 		Random rand5 = new Random();
				                                                 		Random rand6 = new Random();
				                                                 		Random rand7 = new Random();
				                                                 		
				                                                 		int armorn = rand1.nextInt(12);
				                                                 		int enchant = rand2.nextInt(8);
				                                                 		int enchants2 = rand3.nextInt(8);
				                                                 		int unbreak = rand4.nextInt(3);
				                                                 		int level1 = rand5.nextInt(4);
				                                                 		int level2 = rand6.nextInt(4);
				                                                 		
				                                                 		ItemStack armors[] = {gold1, gold2, gold3, gold4, iron1, iron2, iron3, iron4, diamond1, diamond2, diamond3, diamond4};
				                                                 		Enchantment enchants[] = {enchant1, enchant2, enchant3, enchant4, enchant5, enchant6, enchant7, enchant8};
				                                                 		
				                                                 		ItemStack armor = new ItemStack(armors[armorn]);
				                                                 		armor.addUnsafeEnchantment(Enchantment.DURABILITY, unbreak+1);
				                                                 		if(enchant != enchants2) {
				                                                 		armor.addUnsafeEnchantment(enchants[enchant], level1+1);
				                                                 		armor.addUnsafeEnchantment(enchants[enchants2], level2+1);
				                                                 		}
				                                                 		else {
				                                                 			armor.addUnsafeEnchantment(enchants[enchant], level1+1);
				                                                 			armor.addUnsafeEnchantment(enchants[0], level2+1);
				                                                 		}
				                                                 		Opens.addItem(armor);
		                                                 				Loot.put(p, armor);
		                                                 				p.openInventory(Opens);
		                                                 				Meta123.remove(p);
				                                                 		
				                                                 	}
				                                                 	else if(prob == 5) {
				                                                 		Random rand1 = new Random();
				                                                 		Random rand2 = new Random();
				                                                 		Enchantment enchants[] = {ench1, ench2, ench3, ench4, ench5, ench6, ench7, ench8, ench9, ench10, ench11, ench12, ench13, ench14, ench15, ench16, ench17, ench18, ench19, ench20, ench21,ench22, ench23, ench24};
				                                                 		int enchant = rand1.nextInt(24);
				                                                 		int level = rand2.nextInt(6);
				                                                 		
				                                                 		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
				                                                 		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
				                                                 		meta.addStoredEnchant(enchants[enchant], level+1, true);
				                                                 		book.setItemMeta(meta);
				                                                 		
				                                                 		Opens.addItem(book);
		                                                 				Loot.put(p, book);
		                                                 				p.openInventory(Opens);
		                                                 				Meta123.remove(p);
				                                                 		
				                                                 	}
				                                                 	else if(prob == 20) {
                                                                	Random itemamount = new Random();
                                                                	Random loot = new Random();
                                                                	int loots = loot.nextInt(4);
                                                               
                                                                	int item1 = itemamount.nextInt(10);
                                                                	int item2 = itemamount.nextInt(20);
                                                                	int item3 = itemamount.nextInt(30);
                                                                	int item4 = itemamount.nextInt(40);
                                                                	int item5 = itemamount.nextInt(50);
                                                                	int item6 = itemamount.nextInt(60);
                                                                	ItemStack Weed = new ItemStack(31, item3 + 1, (short) 1);
                                                                	ItemStack Cocaine = new ItemStack(Material.SUGAR, item2 + 1);
                                                                	ItemStack LSD = new ItemStack(171, item2 + 1, (short) 1);
                                                                	ItemStack Meth = new ItemStack(Material.CLAY_BALL, item3 + 1);
                                                                	ItemStack Shrooms = new ItemStack(39, item3 + 1);
                                                                	ItemStack Roofie = new ItemStack(Material.SEEDS, item3 + 1);
                                                                	ItemStack Beer = new ItemStack(Material.POTION, item6 + 1);
                                                                	
                                                                	
                                                                	
                                                                	ItemMeta Weedmeta = Weed.getItemMeta();
                                                                	ArrayList<String> Weedlore = new ArrayList<String>();
                                                                	Weedlore.clear();
                                                                	Weedlore.add(ChatColor.GOLD + "Awww yeah...");
                                                                	Weedmeta.setDisplayName(ChatColor.DARK_GREEN + "Weed");
                                                                	Weedmeta.setLore(Weedlore);
                                                                	Weed.setItemMeta(Weedmeta);
                                                                	
                                                                	ItemMeta Cocainemeta = Cocaine.getItemMeta();
                                                                	ArrayList<String> CocaineLore = new ArrayList<String>();
                                                                	CocaineLore.clear();
                                                                	CocaineLore.add(ChatColor.DARK_PURPLE + "Gives you the rush!");
                                                                	Cocainemeta.setDisplayName(ChatColor.YELLOW + "Cocaine");
                                                                	Cocainemeta.setLore(CocaineLore);
                                                                	Cocaine.setItemMeta(Cocainemeta);
                                                                	
                                                                	ItemMeta LSDmeta = LSD.getItemMeta();
                                                                	ArrayList<String> LSDLore = new ArrayList<String>();
                                                                	LSDLore.clear();
                                                                	LSDLore.add(ChatColor.DARK_PURPLE + "Take a trip");
                                                                	LSDmeta.setDisplayName(ChatColor.GOLD + "LSD");
                                                                	LSDmeta.setLore(LSDLore);
                                                                	LSD.setItemMeta(LSDmeta);
                                                                	
                                                                	ItemMeta RoofieMeta = Roofie.getItemMeta();
                                                                	ArrayList<String> RoofieLore = new ArrayList<String>();
                                                                	RoofieLore.clear();
                                                                	RoofieLore.add(ChatColor.GREEN + "Put this in someones food or drink!");
                                                                	RoofieMeta.setDisplayName(ChatColor.RED+ "Roofie");
                                                                	RoofieMeta.setLore(RoofieLore);
                                                                	Roofie.setItemMeta(RoofieMeta);
                                                                	
                                                                	
                                                                	
                                                                	
                                                                	if(loots == 0) {
                                                                	Opens.addItem(Weed);
	                                                 				Loot.put(p, Weed);
	                                                 				p.openInventory(Opens);
	                                                 				Meta123.remove(p);	
                                                                	}
                                                                	if(loots == 1) {
                                                                		Opens.addItem(Cocaine);
    	                                                 				Loot.put(p, Cocaine);
    	                                                 				p.openInventory(Opens);
    	                                                 				Meta123.remove(p);	
                                                                	}
                                                                	if(loots == 2) {
                                                                		Opens.addItem(LSD);
    	                                                 				Loot.put(p, LSD);
    	                                                 				p.openInventory(Opens);
    	                                                 				Meta123.remove(p);
                                                                	}
                                                                	if(loots == 3) {
                                                                		Opens.addItem(Roofie);
    	                                                 				Loot.put(p, Roofie);
    	                                                 				p.openInventory(Opens);
    	                                                 				Meta123.remove(p);
                                                                	}
				                                                 	}
                                                                
                                                                
				                                                 	
			                                                 	
			                                                 			
			                                                 			
			                                                 			
			                                         
			                                                 			
			                                                 			
			                                                 			
			                                                 			
			                                                 			
			                                                 			
			                                                 			 
			                                                 		      
			                                                 			 
			                                                 			 
			                                                 			 
			                                                 			 
			                                                 			
			                                                 			
			                                                 		 
			                                    
			                                            
			                                                 		 
				                                                 	}
			                                                 	 }
			                                                  }, 20L);
			                                         	 }
			                                          }, 20L);
			                                 	 }
			                                  }, 20L);
			                         	 }
			                          }, 20L);
			                 	 }
			                  }, 20L);
			        	 }
			         }, 20L);
				}
				}
				
				else if(Openers.contains(p)) {
					p.sendMessage(ChatColor.RED + "You can only open one " + ChatColor.DARK_PURPLE + "unusual chest " + ChatColor.RED + "at a time!");
				}
				else {
				p.sendMessage(ChatColor.RED + "You need a key to open this chest!");
				}
				
			}
			
			}
				
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			p.sendMessage(ChatColor.RED + "You need a key to open this chest!");
		}
	} 
	
	@EventHandler
	public void anvil(InventoryClickEvent e) {
		if(e.getInventory() instanceof AnvilInventory) {
			AnvilInventory a = (AnvilInventory) e.getInventory();
			Player p = (Player) e.getWhoClicked();
			InventoryView view = e.getView();
			int rawSlot = e.getRawSlot();
			 
			// compare the raw slot with the inventory view to make sure we are talking about the upper inventory
			if(rawSlot == view.convertSlot(rawSlot)){
			/*
			slot 0 = left item slot
			slot 1 = right item slot
			slot 2 = result item slot
			 
			see if the player clicked in the result item slot of the anvil inventory
			*/
			if(rawSlot == 2){
			if(a.getItem(0) != null) {
			if(a.getItem(0).getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Unusual") || a.getItem(0).getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Strange")) { 
				ItemStack k = e.getCurrentItem();
				ItemMeta kk = k.getItemMeta();
				kk.setDisplayName(a.getItem(0).getItemMeta().getDisplayName());
				k.setItemMeta(kk);
				a.clear();
				p.closeInventory();
				e.setCancelled(true);
				p.getInventory().addItem(k);
				p.sendMessage(ChatColor.GREEN + "Your unusual/strange item was repaired/enchanted!");
				p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
			}
			}
			if(a.getItem(1) != null) {
			if(a.getItem(1).getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Unusual: ")) {
				ItemStack k = e.getCurrentItem();
				ItemMeta kk = k.getItemMeta();
				kk.setDisplayName(a.getItem(0).getItemMeta().getDisplayName());
				k.setItemMeta(kk);
				a.clear();
				p.closeInventory();
				e.setCancelled(true);
				p.getInventory().addItem(k);
				p.sendMessage(ChatColor.GREEN + "Your unusual hat was repaired/enchanted!");
				p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
			}
			}
			}
		}
			}
		}
	
	
	@EventHandler
	public void rrr(PlayerMoveEvent event) {
		try {
			final ParticleEffect a = ParticleEffect.ANGRY_VILLAGER;
			//final ParticleEffect b = ParticleEffect.BUBBLE;
			final ParticleEffect c = ParticleEffect.CLOUD;
			//final ParticleEffect d = ParticleEffect.DEPTH_SUSPEND;
			final ParticleEffect e = ParticleEffect.DRIP_LAVA;
			final ParticleEffect f = ParticleEffect.DRIP_WATER;
			final ParticleEffect g = ParticleEffect.ENCHANTMENT_TABLE;
			final ParticleEffect h = ParticleEffect.EXPLODE;
			final ParticleEffect i = ParticleEffect.FIREWORKS_SPARK;
			final ParticleEffect j = ParticleEffect.FLAME;
			final ParticleEffect k = ParticleEffect.FOOTSTEP;
			final ParticleEffect l = ParticleEffect.HAPPY_VILLAGER;
			final ParticleEffect m = ParticleEffect.HEART;
			final ParticleEffect n = ParticleEffect.CRIT;
			//final ParticleEffect o = ParticleEffect.ICONCRACK;
			final ParticleEffect p = ParticleEffect.INSTANT_SPELL;
			final ParticleEffect q = ParticleEffect.LARGE_EXPLODE;
			final ParticleEffect r = ParticleEffect.LARGE_SMOKE;
			final ParticleEffect s = ParticleEffect.LAVA;
			final ParticleEffect t = ParticleEffect.MOB_SPELL;
			final ParticleEffect u = ParticleEffect.MOB_SPELL_AMBIENT;
			final ParticleEffect v = ParticleEffect.NOTE;
			final ParticleEffect w = ParticleEffect.PORTAL;
			final ParticleEffect x = ParticleEffect.RED_DUST;
			final ParticleEffect y = ParticleEffect.SLIME;
			final ParticleEffect z = ParticleEffect.SNOW_SHOVEL;
			final ParticleEffect aa = ParticleEffect.SNOWBALL_POOF;
			final ParticleEffect bb = ParticleEffect.SPELL;
			final ParticleEffect cc = ParticleEffect.SPLASH;
			//final ParticleEffect dd = ParticleEffect.SUSPEND;
			final ParticleEffect ee = ParticleEffect.TOWN_AURA;
			final ParticleEffect ff = ParticleEffect.WITCH_MAGIC;
			final ParticleEffect[] particles = {a, c, e, f, g, h, i, j, k, l, m, n, p, q, r, s, t, u, v, w, x, y, z, aa, bb, cc, ee, ff};
			Player player = event.getPlayer();
			double x1 = player.getLocation().getX();
			double y1 = player.getLocation().getY();
			double z1 = player.getLocation().getZ();
			final Location location = new Location(player.getWorld(), x1, y1+displacement, z1);
//			if(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Crate Key!")) {
//				ArrayList<String> lore = new ArrayList<String>();
//				ItemStack Key = player.getInventory().getItemInHand();
//				ItemMeta meta = Key.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual Chest Key!");
//				lore.clear();
//				lore.add(ChatColor.GOLD + "This has one use on an " + ChatColor.DARK_PURPLE + "Unusual Chest!");
//				meta.setLore(lore);
//				Key.setItemMeta(meta);
//				player.getInventory().setItemInHand(Key);
//				
//			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Huge EXPLOSION")) {
				ItemStack helm = player.getInventory().getHelmet();
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical");
				helm.setItemMeta(meta);
				player.getInventory().setHelmet(helm);
				
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Angry Villager")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.VILLAGER_THUNDERCLOUD, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
//			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Bubbles")) {
//				try {
//					ParticleEffect.sendToLocation(b, location, 0, 1, 0,
//			                0, 1);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					
//					
//				}
//			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Cloud")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.CLOUD, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
//			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "Depth Suspend")) {
//				try {
//					ParticleEffect.sendToLocation(d, location, 0, 1, 0,
//			                0, 1);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					
//					
//				}
//			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Lava Drip")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.LAVADRIP, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_AQUA + "Water Drip")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.WATERDRIP, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Enchantment Table")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.FLYING_GLYPH, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Explode")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.EXPLOSION, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "FireWork Sparks")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.FIREWORKS_SPARK, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_RED + "Flame")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.FLAME, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "FootStep")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.FOOTSTEP, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Happy Villager")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.HAPPY_VILLAGER, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "<3")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.HEART, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.CRIT, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_GRAY + "Large Smoke")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.LARGE_SMOKE, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Lava")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.LAVA_POP, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.POTION_SWIRL, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell Ambient")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.POTION_SWIRL_TRANSPARENT, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Music Note")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.NOTE, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Portal")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.PORTAL, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Red Dust")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.COLOURED_DUST, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Slime")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.SLIME, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Snow Shovel")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.SNOW_SHOVEL, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "SnowBall Poof!")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.SNOWBALL_BREAK, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Spell")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.SPELL, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_BLUE + "Splash")) {
				try {
					
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.SPLASH, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
//			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Suspend")) {
//				try {
//					ParticleEffect.sendToLocation(dd, location, 0, 1, 0,
//			                0, 1);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					
//					
//				}
//			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Town Aura")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.VOID_FOG, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Witch Magic")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.WITCH_MAGIC, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
//			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLACK + "Icon Crack")) {
//				try {
//					ParticleEffect.sendToLocation(o, location, 0, 1, 0,
//			                0, 1);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					
//					
//				}
//			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Instant Spell")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.INSTANT_SPELL, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
			if(player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Large Explode!")) {
				try {
					Bukkit.getWorld(player.getWorld().getName()).playEffect(location, Effect.EXPLOSION_LARGE, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					
				}
			}
		} catch (java.lang.NullPointerException e3) {
			// TODO Auto-generated catch block
			
		}
	    
		
		
	}
	
	@EventHandler
	public void chestpick(BlockBreakEvent event) {
		Random rand = new Random();
		int chance = rand.nextInt(1000000);
		Float chance1 = Float.parseFloat(plugin.getConfig().getString("Mining Chest Chance"));
		double chance12 = chance1 * 10000;
		if(chance12 >= 1 && chance12 <= 1000000) {
			if(chance <= chance12) {
				Random number = new Random();
				int numb = number.nextInt(28);
				ItemStack Box = new ItemStack(Material.CHEST, 1);
				ItemMeta Boxmeta = Box.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				lore.clear();
				lore.add(ChatColor.LIGHT_PURPLE + "Place this chest and open it with a special key!");
				Boxmeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual chest: " + ChatColor.GREEN + "#" + (numb+1));
				Boxmeta.setLore(lore);
				Box.setItemMeta(Boxmeta);
				Location loc = event.getBlock().getLocation();
				Bukkit.getWorld(event.getBlock().getWorld().getName()).dropItemNaturally(loc, Box);
			}
		}
	}
	
	@EventHandler
	public void entitydeath(EntityDeathEvent event) {
		
		
		
		
		Random rand = new Random();
		int helms = rand.nextInt(5);
		int sneeze6 = rand.nextInt(1000000);
		//int asthma = rand.nextInt(100);
		//int asthma2 = rand.nextInt(1000);
	//	int asthma3 = rand.nextInt(10000);
	//	int asthma4 = rand.nextInt(100000);
	//	int asthma5 = rand.nextInt(1000000);
		int Crate = rand.nextInt(1000000);
		
		//int sneeze123 = Integer.parseInt(plugin.getConfig().getString("Sneeze"));
		Float sneeze12 = Float.parseFloat(plugin.getConfig().getString("Chance"));
		//int asthma123 = Integer.parseInt(plugin.getConfig().getString("Asthma"));
		Float Crate12 = Float.parseFloat(plugin.getConfig().getString("Chest Chance"));
		double sneeze123 = sneeze12 * 10000;
		double Crate123 = Crate12 * 10000;
		if(Crate123 >= 1 && Crate123 <= 1000000) {
			if(Crate <= Crate123) {
				Random number = new Random();
				int numb = number.nextInt(28);
				ItemStack Box = new ItemStack(Material.CHEST, 1);
				ItemMeta Boxmeta = Box.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				lore.clear();
				lore.add(ChatColor.LIGHT_PURPLE + "Place this chest and open it with a special key!");
				Boxmeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual chest: " + ChatColor.GREEN + "#" + (numb+1));
				Boxmeta.setLore(lore);
				Box.setItemMeta(Boxmeta);
				
				event.getDrops().add(Box);
			}
		}
		if(sneeze123 >= 1 && sneeze123 <= 1000000) {
			if(sneeze6 <= sneeze123) {
		if(helms == 0) {
			ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
			Random rand2 = new Random();
			Random rand3 = new Random();
			Random rand4 = new Random();
			int helms2 = rand2.nextInt(28);
			int prot = rand3.nextInt(5);
			int unbreaking = rand4.nextInt(4);
			
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			lore.add(ChatColor.DARK_PURPLE + "Renaming will destroy it");
			helm.getItemMeta().setLore(lore);
			if(prot == 1) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if(prot == 2) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			if(prot == 3) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if(prot == 4) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			if(unbreaking == 1) {
				helm.addEnchantment(Enchantment.DURABILITY, 1);
			}
			if(unbreaking == 2) {
				helm.addEnchantment(Enchantment.DURABILITY, 2);
			}
			if(unbreaking == 3) {
				helm.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(helms2 == 0) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Angry Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 1) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Bubbles");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 1) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Cloud");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 3) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "Depth Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 2) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Lava Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 3) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_AQUA + "Water Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 4) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Enchantment Table");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 5) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Explode");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 6) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "FireWork Sparks");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 7) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_RED + "Flame");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 8) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "FootStep");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 9) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Happy Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 10) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "<3");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 11) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 12) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_GRAY + "Large Smoke");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 13) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Lava");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 14) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 15) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell Ambient");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 16) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Music Note");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 17) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Portal");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 18) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Red Dust");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 19) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Slime");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 20) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Snow Shovel");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 21) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "SnowBall Poof!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 22) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 23) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_BLUE + "Splash");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 26) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 24) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Town Aura");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 25) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Witch Magic");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 29) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLACK + "Icon Crack");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 26) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Instant Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 27) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Large Explode!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			
			
		}
		if(helms == 1) {
			ItemStack helm = new ItemStack(Material.IRON_HELMET, 1);
			Random rand2 = new Random();
			Random rand3 = new Random();
			Random rand4 = new Random();
			int helms2 = rand2.nextInt(28);
			int prot = rand3.nextInt(5);
			int unbreaking = rand4.nextInt(4);
			
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			lore.add(ChatColor.DARK_PURPLE + "Renaming will destroy it");
			helm.getItemMeta().setLore(lore);
			if(prot == 1) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if(prot == 2) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			if(prot == 3) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if(prot == 4) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			if(unbreaking == 1) {
				helm.addEnchantment(Enchantment.DURABILITY, 1);
			}
			if(unbreaking == 2) {
				helm.addEnchantment(Enchantment.DURABILITY, 2);
			}
			if(unbreaking == 3) {
				helm.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(helms2 == 0) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Angry Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 1) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Bubbles");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 1) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Cloud");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 3) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "Depth Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 2) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Lava Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 3) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_AQUA + "Water Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 4) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Enchantment Table");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 5) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Explode");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 6) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "FireWork Sparks");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 7) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_RED + "Flame");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 8) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "FootStep");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 9) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Happy Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 10) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "<3");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 11) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 12) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_GRAY + "Large Smoke");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 13) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Lava");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 14) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 15) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell Ambient");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 16) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Music Note");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 17) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Portal");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 18) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Red Dust");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 19) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Slime");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 20) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Snow Shovel");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 21) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "SnowBall Poof!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 22) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 23) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_BLUE + "Splash");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 26) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 24) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Town Aura");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 25) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Witch Magic");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 29) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLACK + "Icon Crack");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 26) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Instant Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 27) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Large Explode!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
		}
		if(helms == 2) {
			ItemStack helm = new ItemStack(Material.GOLD_HELMET, 1);
			Random rand2 = new Random();
			Random rand3 = new Random();
			Random rand4 = new Random();
			int helms2 = rand2.nextInt(28);
			int prot = rand3.nextInt(5);
			int unbreaking = rand4.nextInt(4);
			
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			lore.add(ChatColor.DARK_PURPLE + "Renaming will destroy it");
			helm.getItemMeta().setLore(lore);
			if(prot == 1) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if(prot == 2) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			if(prot == 3) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if(prot == 4) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			if(unbreaking == 1) {
				helm.addEnchantment(Enchantment.DURABILITY, 1);
			}
			if(unbreaking == 2) {
				helm.addEnchantment(Enchantment.DURABILITY, 2);
			}
			if(unbreaking == 3) {
				helm.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(helms2 == 0) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Angry Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 1) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Bubbles");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 1) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Cloud");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 3) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "Depth Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 2) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Lava Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 3) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_AQUA + "Water Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 4) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Enchantment Table");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 5) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Explode");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 6) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "FireWork Sparks");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 7) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_RED + "Flame");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 8) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "FootStep");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 9) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Happy Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 10) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "<3");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 11) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 12) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_GRAY + "Large Smoke");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 13) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Lava");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 14) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 15) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell Ambient");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 16) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Music Note");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 17) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Portal");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 18) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Red Dust");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 19) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Slime");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 20) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Snow Shovel");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 21) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "SnowBall Poof!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 22) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 23) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_BLUE + "Splash");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 26) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 24) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Town Aura");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 25) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Witch Magic");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 29) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLACK + "Icon Crack");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 26) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Instant Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 27) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Large Explode!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
		}
		if(helms == 3) {
			ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
			Random rand2 = new Random();
			Random rand3 = new Random();
			Random rand4 = new Random();
			int helms2 = rand2.nextInt(28);
			int prot = rand3.nextInt(5);
			int unbreaking = rand4.nextInt(4);
			
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			lore.add(ChatColor.DARK_PURPLE + "Renaming will destroy it");
			helm.getItemMeta().setLore(lore);
			if(prot == 1) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if(prot == 2) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			if(prot == 3) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if(prot == 4) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			if(unbreaking == 1) {
				helm.addEnchantment(Enchantment.DURABILITY, 1);
			}
			if(unbreaking == 2) {
				helm.addEnchantment(Enchantment.DURABILITY, 2);
			}
			if(unbreaking == 3) {
				helm.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(helms2 == 0) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Angry Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 1) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Bubbles");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 1) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Cloud");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 3) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "Depth Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 2) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Lava Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 3) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_AQUA + "Water Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 4) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Enchantment Table");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 5) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Explode");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 6) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "FireWork Sparks");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 7) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_RED + "Flame");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 8) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "FootStep");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 9) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Happy Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 10) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "<3");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 11) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 12) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_GRAY + "Large Smoke");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 13) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Lava");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 14) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 15) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell Ambient");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 16) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Music Note");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 17) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Portal");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 18) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Red Dust");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 19) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Slime");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 20) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Snow Shovel");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 21) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "SnowBall Poof!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 22) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 23) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_BLUE + "Splash");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 26) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 24) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Town Aura");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 25) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Witch Magic");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 29) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLACK + "Icon Crack");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 26) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Instant Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 27) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Large Explode!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
		}
		if(helms == 4) {
			ItemStack helm = new ItemStack(Material.CHAINMAIL_HELMET, 1);
			Random rand2 = new Random();
			Random rand3 = new Random();
			Random rand4 = new Random();
			int helms2 = rand2.nextInt(28);
			int prot = rand3.nextInt(5);
			int unbreaking = rand4.nextInt(4);
			
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			lore.add(ChatColor.DARK_PURPLE + "Renaming will destroy it");
			helm.getItemMeta().setLore(lore);
			if(prot == 1) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if(prot == 2) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			if(prot == 3) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if(prot == 4) {
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			if(unbreaking == 1) {
				helm.addEnchantment(Enchantment.DURABILITY, 1);
			}
			if(unbreaking == 2) {
				helm.addEnchantment(Enchantment.DURABILITY, 2);
			}
			if(unbreaking == 3) {
				helm.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(helms2 == 0) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Angry Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 1) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Bubbles");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 1) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Cloud");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 3) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "Depth Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 2) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Lava Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 3) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_AQUA + "Water Drip");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 4) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Enchantment Table");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 5) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Explode");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 6) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "FireWork Sparks");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 7) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_RED + "Flame");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 8) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GRAY + "FootStep");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 9) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Happy Villager");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 10) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "<3");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 11) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Critical");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 12) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_GRAY + "Large Smoke");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 13) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Lava");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 14) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 15) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.AQUA + "Mob Spell Ambient");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 16) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Music Note");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 17) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Portal");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 18) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Red Dust");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 19) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Slime");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 20) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "Snow Shovel");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 21) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.WHITE + "SnowBall Poof!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 22) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLUE + "Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 23) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.DARK_BLUE + "Splash");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 26) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GOLD + "Suspend");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 24) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.LIGHT_PURPLE + "Town Aura");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 25) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Witch Magic");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
//			if(helms2 == 29) {
//				ItemMeta meta = helm.getItemMeta();
//				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.BLACK + "Icon Crack");
//				helm.setItemMeta(meta);
//				event.getDrops().add(helm);
//			}
			if(helms2 == 26) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.GREEN + "Instant Spell");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
			if(helms2 == 27) {
				ItemMeta meta = helm.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Unusual: " + ChatColor.RED + "Large Explode!");
				helm.setItemMeta(meta);
				event.getDrops().add(helm);
			}
		}
	}
		}
	}
}
