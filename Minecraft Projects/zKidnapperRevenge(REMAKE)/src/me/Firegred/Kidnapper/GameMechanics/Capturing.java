package me.Firegred.Kidnapper.GameMechanics;

import java.util.ArrayList;
import java.util.HashMap;

import me.Firegred.Kidnapper.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Capturing implements Listener {
	
	public Main plugin;
	public static final HashMap<Player, Location> chestloc = new HashMap<Player, Location>();
	public Capturing(Main instance) {
		plugin = instance;
	}
		
	
	@EventHandler
	public void interuption(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(p.hasMetadata("Savior")) {
			p.removeMetadata("Savior", plugin);
			p.sendMessage(ChatColor.RED + "Your moving disrupted the rescuing process!");
			p.removeMetadata("Wait", plugin);
		}
	}
	
	@EventHandler
	public void rescuing(InventoryOpenEvent event) {
		final Player p = (Player) event.getPlayer();
		if(event.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_GREEN + "Captured Sack")) {
			if(p.hasMetadata("Savior")) {
				event.setCancelled(true);
			}
			else {
			final Chest chest = (Chest) event.getInventory().getHolder();
			int x = chest.getX();
			int y = chest.getY();
			int z = chest.getZ();
			final World world = chest.getWorld();
			final Location location = new Location(chest.getWorld(), x, y, z);
			System.out.println("event works");
			event.setCancelled(true);
			if(p.getWorld().getBlockAt(location).hasMetadata("Capture")) {
		   // for(Player s : Bukkit.getOnlinePlayers()) {
			//    if(Capturing.chestloc.containsKey(s)) {
		    	
			//	 if(location == Capturing.chestloc.get(s)) {
				  // System.out.println("Should work?");
					//if(p.hasMetadata("Citizen")) {
						//event.setCancelled(true);
						if(p.hasMetadata("Kidnapper")) {
							//event.setCancelled(true);
							p.sendMessage(ChatColor.RED + "You don't save citizens! You kidnap them!");
						}
						final Player kk = Bukkit.getPlayer(p.getWorld().getBlockAt(location).getMetadata("Capture").get(0).asString());
						if(kk.hasMetadata("Chester") && p.hasMetadata("Citizen") && !p.hasMetadata("Wait")) {
						p.setMetadata("Wait", new FixedMetadataValue(plugin, "yes"));
						p.sendMessage(ChatColor.RED + "You are now attempting to save " + ChatColor.YELLOW + kk.getName());
						p.sendMessage(ChatColor.RED + "This process will take " + ChatColor.YELLOW + "3 seconds");
						p.sendMessage(ChatColor.RED + "Moving will disrupt the process!");
						kk.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " is attempting to save you!");
						p.setMetadata("Savior", new FixedMetadataValue(plugin, "1"));
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								if(p.hasMetadata("Savior")) {
									
															Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
																@Override
																public void run() {
																	if(p.hasMetadata("Savior")) {
																		p.removeMetadata("Savior", plugin);
																		kk.removeMetadata("Chester", plugin);
																		if(kk.hasPotionEffect(PotionEffectType.BLINDNESS)) {
																			kk.removePotionEffect(PotionEffectType.BLINDNESS);
																		}
																		if(p.hasMetadata("Wait")) {
																			p.removeMetadata("Wait", plugin);
																		}
																		kk.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has saved you!");
																		p.sendMessage(ChatColor.GREEN + "You have saved " + ChatColor.YELLOW + kk.getName());
																		String path = Main.path + kk.getMetadata("Games").get(0).asString() + ".players";
																		kk.setMetadata("Citizen", new FixedMetadataValue(plugin, kk.getMetadata("Games").get(0).asString()));
																		kk.getWorld().getBlockAt(Capturing.chestloc.get(kk)).setType(Material.AIR);
																		kk.getWorld().getBlockAt(location).removeMetadata("Capture", plugin);
																		Capturing.chestloc.remove(kk);
																		for(Player cccccc : Bukkit.getOnlinePlayers()) {
																			cccccc.showPlayer(kk);
																		}
																		for(Player s : Bukkit.getOnlinePlayers()) {
																			if(s.hasMetadata("Games")) {
																				if(s.getMetadata("Games").get(0).asString().equals(kk.getMetadata("Games").get(0).asString())) {
																					s.sendMessage(ChatColor.RED + kk.getName() + ChatColor.GOLD + " was saved by " + ChatColor.RED + p.getName());
																				}
																				if(s.hasMetadata("Kidnapper")) {
																					s.playSound(s.getLocation(), Sound.COW_HURT, 1, 1);
																				}
																				if(s.hasMetadata("Citizen")) {
																					s.playSound(s.getLocation(), Sound.CAT_MEOW, 1, 1);
																				}
																			}
																		}
																		final String game = p.getMetadata("Game").get(0).asString();
																		Game.citizens.get(game).add(kk);
																		//int cit = plugin.getConfig().getInt(path + ".Citizens");
																		//int citt = cit + 1;
																	    //plugin.getConfig().set(path + ".Citizens", citt);
																	    //plugin.saveConfig();
																	   // plugin.reloadConfig();
																	    String paths22 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
																	    int aa = Game.players.get(game).size();
																		
																	    final int max = Game.max.get(game);
																		final int min = Game.min.get(game);
																		int kidders = Game.kidnappers.get(game).size();
																		int citters = Game.citizens.get(game).size();
																	    
																	    // int aa = plugin.getConfig().getInt(paths22 + ".number");
																		//final int max = plugin.getConfig().getInt(paths22 + ".Max");
																		//final int min = plugin.getConfig().getInt(paths22 + ".Min");
																		//int kidders = plugin.getConfig().getInt(paths22 + ".Kidnappers");
																		//int citters = plugin.getConfig().getInt(paths22 + ".Citizens");
																		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
																	   
																		if(board.getObjective(p.getMetadata("Games").get(0).asString()) == null) {
																		Objective objective = board.registerNewObjective(p.getMetadata("Games").get(0).asString(), "stat");
																	    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
																	    objective.setDisplayName(ChatColor.GREEN + p.getMetadata("Games").get(0).asString());
																	    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
																	    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
																	    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
																	    for(Player ps : Bukkit.getOnlinePlayers()) {
																	    	if(ps.hasMetadata("Games")) {
																	    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
																	    			if(!ps.hasMetadata("Start") && !ps.getName().equals(p.getName())) {
																	    			number.setScore(aa);
																	    			minn.setScore(min);
																	    			maxx.setScore(max);
																	    			ps.setScoreboard(board);
																	    			}
																	    			else if(ps.hasMetadata("Start") && !ps.getName().equals(p.getName())){
																	    				Score kidnappers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Kidnappers:"));
																					    Score citizens = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Citizens:"));
																	    				number.setScore(aa);
																	    				kidnappers.setScore(kidders);
																	    				citizens.setScore(citters);
																		    			minn.setScore(min);
																		    			maxx.setScore(max);
																		    			ps.setScoreboard(board);
																	    			}
																	    		}
																	    	}
																	    }  
																	    
																		}
																		else {
																			Objective objective = board.getObjective(p.getMetadata("Games").get(0).asString());
																		    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
																		    objective.setDisplayName(ChatColor.GREEN + p.getMetadata("Games").get(0).asString());
																		    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
																		    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
																		    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
																		    for(Player ps : Bukkit.getOnlinePlayers()) {
																		    	if(ps.hasMetadata("Games")) {
																		    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
																		    			if(!ps.hasMetadata("Start") && !ps.getName().equals(p.getName())) {
																		    			number.setScore(aa);
																		    			minn.setScore(min);
																		    			maxx.setScore(max);
																		    			ps.setScoreboard(board);
																		    			}
																		    			else if(ps.hasMetadata("Start") && !ps.getName().equals(p.getName())){
																		    				Score kidnappers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Kidnappers:"));
																						    Score citizens = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Citizens:"));
																		    				number.setScore(aa);
																		    				kidnappers.setScore(kidders);
																		    				citizens.setScore(citters);
																			    			minn.setScore(min);
																			    			maxx.setScore(max);
																			    			ps.setScoreboard(board);
																		    			}
																		    		}
																		    	}
																		    }  
																		}
																		
																	}
																		
																	else {
																		kk.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.RED + " couldn't save you!");
																	}
																}
															}, 20L);
														}
															
														else {
															kk.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " couldn't save you!");
														}
													}
												}, 20L);
											}
												
			
					
					
					//else {
					//	event.setCancelled(true);
					//}
				}
			}
		}
		    
		    
		}
		//}
	
	
	@EventHandler
	public void placingCapturedChest(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlock();
		if(p.hasMetadata("Capturer")) {
			if(p.hasMetadata("Games")) {
				if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Captured Sack")) {
					String path1 = Main.path + p.getMetadata("Games").get(0).asString() + ".Chestarea.";
					int xx = event.getBlockPlaced().getX();
					int yy = event.getBlockPlaced().getY();
					int zz = event.getBlockPlaced().getZ();
					int yyy = event.getBlockPlaced().getY() - 1;
					Location blockloc = new Location(p.getWorld(), xx, yy, zz);
					Location locloc = new Location(p.getWorld(), xx, yyy, zz);
					if(!p.getWorld().getBlockAt(locloc).getType().equals(Material.SPONGE)) {
						event.setCancelled(true);
						p.updateInventory();
						p.sendMessage(ChatColor.RED + "Place this at your spawn! (On the Sponge!)");
					}
					else {
					//	if(event.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.WOOL)) {
						//for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH)}) {
						//	if(neighbor.getState() instanceof Chest) {
				         //   	 event.setCancelled(true);
				            	// System.out.println("MARCO");
				         //   	 event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place " + ChatColor.DARK_PURPLE + "sacks" + ChatColor.RED + " next to other sacks!");
				            	 //break;
						//	}
							//else if(!(neighbor.getState() instanceof Chest)){
					
					int x1 = plugin.getConfig().getInt(path1 + "X1");
					int x2 = plugin.getConfig().getInt(path1 + "X2");
					int y1 = plugin.getConfig().getInt(path1 + "Y1");
					int y2 = plugin.getConfig().getInt(path1 + "Y2");
					int z1 = plugin.getConfig().getInt(path1 + "Z1");
					int z2 = plugin.getConfig().getInt(path1 + "Z2");
					System.out.println("This works");
					int i = 0;
					int ii = 0;
					int l = 0;
					int ll = 0;
					if(x1 > x2) {
						i = x2;
						ii = x1;
					}
					if(x2 > x1) {
						i = x1;
						ii = x2;
					}
					if(z1 > z2) {
						l = z2;
						ll = z1;
					}
					if(z2 > z1) {
						l = z1;
						ll = z2;
					}
				//	event.setCancelled(true);
					//for(int o = i; o <= ii; o++) {
					//	for(int c = l; c <= ll; c++) {
					//		Location location = new Location(p.getWorld(), o, y2, c);
					//		if(blockloc == location) {
								    String path = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
								    final String game = p.getMetadata("Games").get(0).asString();
								   // int cit = plugin.getConfig().getInt(path + ".Citizens");
								   // int citt = cit - 1;
								    //plugin.getConfig().set(path + ".Citizens", citt);
								    //plugin.saveConfig();
								    //plugin.reloadConfig();
								    
								    Player citizen = Bukkit.getPlayer(p.getMetadata("Capturer").get(0).asString());
								    citizen.sendMessage(ChatColor.RED + "You have been fully captured! Wait for a citizen to save you!");
								    citizen.showPlayer(p);
								    p.removeMetadata("Capturer",plugin);
								    citizen.removeMetadata("Captured", plugin);
								    citizen.teleport(blockloc);
								    citizen.setMetadata("Chester", new FixedMetadataValue(plugin, citizen.getMetadata("Citizen").get(0).asString()));
								    chestloc.put(citizen, blockloc);
								    citizen.sendBlockChange(blockloc, Material.AIR, (byte) 0);
								    citizen.removeMetadata("Citizen", plugin);
								    Game.citizens.get(game).remove(citizen);
								    citizen.getWorld().getBlockAt(blockloc).setMetadata("Capture", new FixedMetadataValue(plugin, citizen.getName()));
								    
								    ItemStack sack = new ItemStack(Material.CHEST, 1);
									ItemMeta sackmeta = sack.getItemMeta();
									ArrayList<String> sacklore = new ArrayList<String>();
									sacklore.clear();
									sacklore.add(ChatColor.GREEN + "Kidnap citizens with a Left-Click!");
									sackmeta.setDisplayName(ChatColor.DARK_GREEN + "Sack");
								    sackmeta.setLore(sacklore);
									sack.setItemMeta(sackmeta);
									p.getInventory().setItem(0, sack);
								    if(p.hasPotionEffect(PotionEffectType.SLOW)) {
								    	p.removePotionEffect(PotionEffectType.SLOW);
								    }
									
									
								    for(Player s : Bukkit.getOnlinePlayers()) {
								    	if(s.hasMetadata("Games")) {
								    	if(s.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
								    		s.sendMessage(ChatColor.GOLD + citizen.getName() + ChatColor.RED + " was fully captured by a kidnapper!");
								    		if(s.hasMetadata("Kidnapper")) {
								    			s.playSound(s.getLocation(), Sound.LEVEL_UP, 1, 1);
								    		}
								    		if(s.hasMetadata("Citizen")) {
								    			s.playSound(s.getLocation(), Sound.PIG_DEATH, 1, 1);
								    		}
								    	}
								    	
								    }
								    }
								    String paths22 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
								  //  int aa = plugin.getConfig().getInt(paths22 + ".number");
									//final int max = plugin.getConfig().getInt(paths22 + ".Max");
									//final int min = plugin.getConfig().getInt(paths22 + ".Min");
									//int kidders = plugin.getConfig().getInt(paths22 + ".Kidnappers");
									//int citters = plugin.getConfig().getInt(paths22 + ".Citizens");
								    int aa = Game.players.get(game).size();
								    final int max = Game.max.get(game);
									final int min = Game.min.get(game);
									int kidders = Game.kidnappers.get(game).size();
									int citters = Game.citizens.get(game).size();
									Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
								    if(board.getObjective(p.getMetadata("Games").get(0).asString()) == null) {
									Objective objective = board.registerNewObjective(p.getMetadata("Games").get(0).asString(), "stat");
								    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
								    objective.setDisplayName(ChatColor.GREEN + p.getMetadata("Games").get(0).asString());
								    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
								    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
								    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
								    for(Player ps : Bukkit.getOnlinePlayers()) {
								    	if(ps.hasMetadata("Games")) {
								    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
								    			if(!ps.hasMetadata("Start") && !ps.getName().equals(p.getName())) {
								    			number.setScore(aa);
								    			minn.setScore(min);
								    			maxx.setScore(max);
								    			ps.setScoreboard(board);
								    			}
								    			else if(ps.hasMetadata("Start") && !ps.getName().equals(p.getName())){
								    				Score kidnappers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Kidnappers:"));
												    Score citizens = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Citizens:"));
								    				number.setScore(aa);
								    				kidnappers.setScore(kidders);
								    				citizens.setScore(citters);
									    			minn.setScore(min);
									    			maxx.setScore(max);
									    			ps.setScoreboard(board);
								    			}
								    		}
								    	}
								    }  
								    //break;
							//}
							
								
							//}
					
						//}
					//break;
					}
								    else {
								    	Objective objective = board.getObjective(p.getMetadata("Games").get(0).asString());
									    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
									    objective.setDisplayName(ChatColor.GREEN + p.getMetadata("Games").get(0).asString());
									    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
									    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
									    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
									    for(Player ps : Bukkit.getOnlinePlayers()) {
									    	if(ps.hasMetadata("Games")) {
									    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
									    			if(!ps.hasMetadata("Start") && !ps.getName().equals(p.getName())) {
									    			number.setScore(aa);
									    			minn.setScore(min);
									    			maxx.setScore(max);
									    			ps.setScoreboard(board);
									    			}
									    			else if(ps.hasMetadata("Start") && !ps.getName().equals(p.getName())){
									    				Score kidnappers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Kidnappers:"));
													    Score citizens = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Citizens:"));
									    				number.setScore(aa);
									    				kidnappers.setScore(kidders);
									    				citizens.setScore(citters);
										    			minn.setScore(min);
										    			maxx.setScore(max);
										    			ps.setScoreboard(board);
									    			}
									    		}
									    	}
									    }  
								    }
					}
					
						}
					//}
				
			
				//	else{
				//		p.sendMessage(ChatColor.RED + "You can only place this at your spawn!");
				//		event.setCancelled(true);
				//	}
				   
				}
			//	else {
				//	p.sendMessage(ChatColor.RED + "You can only place this at your spawn!");
				//	event.setCancelled(true);
				//}
			//}
			
		//}
	}
	}

}
