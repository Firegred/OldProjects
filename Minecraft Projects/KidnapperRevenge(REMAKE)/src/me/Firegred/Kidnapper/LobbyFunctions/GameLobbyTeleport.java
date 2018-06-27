package me.Firegred.Kidnapper.LobbyFunctions;

//import java.awt.Color;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.GameMechanics.Capturing;
import me.Firegred.Kidnapper.GameMechanics.Game;

public class GameLobbyTeleport implements Listener{
	
	public Main plugin;
	public static int taskid;
	public GameLobbyTeleport(Main instance) {
		plugin = instance;
	}
	public void citizenteleport(Player player) {
		String game = player.getMetadata("Games").get(0).asString();
		if(Game.citizenteleport.get(game)== null) {
		String lobber = Main.path + player.getMetadata("Citizen").get(0).asString() + ".gamespawn.Citizens.";
		int x = plugin.getConfig().getInt(lobber + "X");
		int y = plugin.getConfig().getInt(lobber + "Y");
		int z = plugin.getConfig().getInt(lobber + "Z");
		Location location = new Location (player.getWorld(), x, y, z);
		Game.citizenteleport.put(game, location);
		player.teleport(location);
		}
		else {
		player.teleport(Game.citizenteleport.get(game));
		}
	}
	public void kidnapperlobbyteleport(Player player) {
		String game = player.getMetadata("Games").get(0).asString();
		if(Game.kidnapperlobbyteleport.get(game)== null) {
		String lobber = Main.path + player.getMetadata("Games").get(0).asString() + ".gamespawn.Kidnappers.";
		int x = plugin.getConfig().getInt(lobber + "lobX");
		int y = plugin.getConfig().getInt(lobber + "lobY");
		int z = plugin.getConfig().getInt(lobber + "lobZ");
		Location location = new Location (player.getWorld(), x, y, z);
		Game.kidnapperlobbyteleport.put(game, location);
		player.teleport(location);
		}
		else {
		player.teleport(Game.kidnapperlobbyteleport.get(game));
		}
	}
	public void kidnapperspawnteleport(Player player) {
		String game = player.getMetadata("Games").get(0).asString();
		if(Game.kidnapperspawnteleport.get(game)== null) {
		String lobber = Main.path + player.getMetadata("Games").get(0).asString() + ".gamespawn.Kidnappers.";
		int x = plugin.getConfig().getInt(lobber + "spawnX");
		int y = plugin.getConfig().getInt(lobber + "spawnY");
		int z = plugin.getConfig().getInt(lobber + "spawnZ");
		Location location = new Location (player.getWorld(), x, y, z);
		player.teleport(location);
		Game.kidnapperspawnteleport.put(game, location);
		}
		else {
			player.teleport(Game.kidnapperspawnteleport.get(game));
		}
	}
	
	public void mainlobbyteleport(Player player) {
		String lob = Main.lobpath;
		if(Game.mainlobbyTeleport.get(lob)== null) {
		int x = plugin.getConfig().getInt(lob + "X");
		int y = plugin.getConfig().getInt(lob + "Y");
		int z = plugin.getConfig().getInt(lob + "Z");
		Location location = new Location(player.getWorld(), x, y, z);
		player.teleport(location);
		Game.mainlobbyTeleport.put(lob, location);
		}
		else {
			player.teleport(Game.mainlobbyTeleport.get(lob));
		}
		
	}
	
	public void lobbyteleport(Player player) {
		String game = player.getMetadata("Games").get(0).asString();
		
		if(Game.players.get(game) == null) {
			Game.players.put(game, new ArrayList<Player>());
		}
		
		if(Game.max.get(game)== null) {
			String paths22 = Main.path + player.getMetadata("Games").get(0).asString() + ".players";
			final int max = plugin.getConfig().getInt(paths22 + ".Max");
			Game.max.put(game, max);
		}
		if(Game.min.get(game)== null) {
			String paths22 = Main.path + player.getMetadata("Games").get(0).asString() + ".players";
			final int min = plugin.getConfig().getInt(paths22 + ".Min");
			Game.min.put(game, min);
		}
		if(Game.ingame.get(game)== null) {
			Game.ingame.put(game, false);
		}
		if(Game.citizenteleport.get(game)== null) {
			String lobber = Main.path + player.getMetadata("Games").get(0).asString() + ".gamespawn.Citizens.";
			int x = plugin.getConfig().getInt(lobber + "X");
			int y = plugin.getConfig().getInt(lobber + "Y");
			int z = plugin.getConfig().getInt(lobber + "Z");
			Location location = new Location (player.getWorld(), x, y, z);
			Game.citizenteleport.put(game, location);
		}
		if(Game.kidnapperlobbyteleport.get(game)== null) {
			String lobber = Main.path + player.getMetadata("Games").get(0).asString() + ".gamespawn.Kidnappers.";
			int x = plugin.getConfig().getInt(lobber + "lobX");
			int y = plugin.getConfig().getInt(lobber + "lobY");
			int z = plugin.getConfig().getInt(lobber + "lobZ");
			Location location = new Location (player.getWorld(), x, y, z);
			Game.kidnapperlobbyteleport.put(game, location);
		}
		if(Game.kidnapperspawnteleport.get(game)== null) {
			String lobber = Main.path + player.getMetadata("Games").get(0).asString() + ".gamespawn.Kidnappers.";
			int x = plugin.getConfig().getInt(lobber + "spawnX");
			int y = plugin.getConfig().getInt(lobber + "spawnY");
			int z = plugin.getConfig().getInt(lobber + "spawnZ");
			Location location = new Location (player.getWorld(), x, y, z);
			Game.kidnapperspawnteleport.put(game, location);
		}
		if(Game.lobbyteleport.get(game)== null) {
		String lobbys = Main.path + player.getMetadata("Games").get(0).asString() + ".lobby";
		int x = plugin.getConfig().getInt(lobbys + ".X");
		int y = plugin.getConfig().getInt(lobbys + ".Y");
		int z = plugin.getConfig().getInt(lobbys + ".Z");
		Location location = new Location(player.getWorld(), x, y, z);
		player.teleport(location);
		Game.lobbyteleport.put(game, location);
		}
		else {
			player.teleport(Game.lobbyteleport.get(game));
		}
		
	}
	@EventHandler
	public void Checking(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(p.hasMetadata("Games")) {
			String path = Main.path + p.getMetadata("Games").get(0).asString() + ".Ingame";
			if(!p.hasMetadata("Start") && plugin.getConfig().getString(path).equals("true")) {
				p.setMetadata("Start", new FixedMetadataValue(plugin, "yes"));
				if(p.hasMetadata("Kidnapper")) {
					kidnapperlobbyteleport(p);
					p.sendMessage(ChatColor.GOLD + "You are a " + ChatColor.RED + "Kidnapper.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "You will be teleported into the game in 20 seconds!");
					
					ItemStack sack = new ItemStack(Material.CHEST, 1);
					ItemMeta sackmeta = sack.getItemMeta();
					ArrayList<String> sacklore = new ArrayList<String>();
					sacklore.clear();
					sacklore.add(ChatColor.GREEN + "Kidnap citizens with a Left-Click!");
					sackmeta.setDisplayName(ChatColor.DARK_GREEN + "Sack");
				    sackmeta.setLore(sacklore);
					sack.setItemMeta(sackmeta);
					p.getInventory().setItem(0, sack);
					
					ItemStack stick = new ItemStack(Material.STICK, 1);
					ItemMeta stickmeta = stick.getItemMeta();
					ArrayList<String> sticklore = new ArrayList<String>();
					sticklore.clear();
					sticklore.add(ChatColor.GREEN + "Hit citizens away!");
					stickmeta.setDisplayName(ChatColor.AQUA + "Baton");
					stickmeta.setLore(sticklore);
					stick.setItemMeta(stickmeta);
					stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
					p.getInventory().setItem(1, stick);
					
					ItemStack helm = new ItemStack(Material.WOOL, 1, (short) 7);
					ItemStack chestbody = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
					ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
					ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
					LeatherArmorMeta chestmeta = (LeatherArmorMeta) chestbody.getItemMeta();
					LeatherArmorMeta legmeta = (LeatherArmorMeta) leggings.getItemMeta();
					LeatherArmorMeta bootsmeta = (LeatherArmorMeta) boots.getItemMeta();
					
					chestmeta.setColor(Color.GRAY);
					legmeta.setColor(Color.GRAY);
					bootsmeta.setColor(Color.GRAY);
					
					chestbody.setItemMeta(chestmeta);
					leggings.setItemMeta(legmeta);
					boots.setItemMeta(bootsmeta);
				    chestbody.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				    leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				    chestbody.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				    leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				    boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					
					p.getInventory().setHelmet(helm);
					p.getInventory().setChestplate(chestbody);
					p.getInventory().setLeggings(leggings);
					p.getInventory().setBoots(boots);
					
					
					final Player ps = p;
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
                            
							kidnapperspawnteleport(ps);
							
						}
							
						
					}, 20*20L);
				}
				else if(p.hasMetadata("Citizen")) {
					citizenteleport(p);
					p.sendMessage(ChatColor.GOLD + "You are a " + ChatColor.YELLOW + "Citizen.");
					ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
					sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					p.getInventory().setItem(0, sword);
					p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (short) 3));
				}
			}
		}
	}
	
	@EventHandler
	public void Teleporting(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock().getType().equals(Material.SIGN_POST) || event.getClickedBlock().getType().equals(Material.SIGN)) {
			final Sign sign = (Sign) event.getClickedBlock().getState();
			//System.out.println("test");
			if(sign.getLine(0).equals(ChatColor.RED + "Didn't teleport?")) {
				if(p.hasMetadata("Citizen") && p.hasMetadata("Start")) {
					citizenteleport(p);
				}
				if(p.hasMetadata("Kidnapper") && p.hasMetadata("Start")) {
					kidnapperspawnteleport(p);
				}
			}
			
			if(sign.getLine(1).contains(ChatColor.DARK_BLUE + "[Join]")) {
				//System.out.println("lobby teleport should work?");
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						if(p.hasMetadata("Games") && !p.hasMetadata("Wait"))  {
							final String game = p.getMetadata("Games").get(0).asString();
							String path = Main.path + sign.getLine(0).toString() + ".Ingame";
							p.setMetadata("Wait", new FixedMetadataValue(plugin, "yes"));
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.removeMetadata("Wait", plugin);								}
							}, 30L);
							//if(plugin.getConfig().getString(path).equalsIgnoreCase("true")) {
								if(Game.ingame.get(game) == true) {
							    sign.setLine(3, ChatColor.DARK_RED + "[Ingame]");
								sign.setLine(1, ChatColor.RED + "[NotJoinable]"); 
								sign.update();
						}
							//}
							else {
							 
								if(!p.getGameMode().equals(GameMode.SURVIVAL)) {
									p.setGameMode(GameMode.SURVIVAL);
								}
								if(p.hasPotionEffect(PotionEffectType.SLOW)) {
							    	p.removePotionEffect(PotionEffectType.SLOW);
							    }
						    
							if(p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
								p.removePotionEffect(PotionEffectType.BLINDNESS);
							}
							
							if(p.hasMetadata("Savior")) {
								p.removeMetadata("Savior", plugin);
							}
							if(Capturing.chestloc.containsKey(p)) {
								  Location location = Capturing.chestloc.get(p);
								  p.getWorld().getBlockAt(location).setType(Material.AIR);
								  Capturing.chestloc.remove(p);
								  p.getWorld().getBlockAt(location).removeMetadata("Capture", plugin);
							  }
							if(p.hasMetadata("Chester")) {
								for(Player f : Bukkit.getOnlinePlayers()) {
									f.showPlayer(p);
									//p.removeMetadata("Captured", plugin);
								}
								//p.getWorld().getBlockAt(p.getLocation()).setType(Material.AIR);
								p.removeMetadata("Chester", plugin);
							}
							
							if(p.hasMetadata("killed")) {
								p.removeMetadata("killed", plugin);
							}
							
							if(p.hasMetadata("Chest")) {
								p.removeMetadata("Chest", plugin);
							}
							if(p.hasMetadata("Chest1")) {
								p.removeMetadata("Chest1", plugin);
							}
							if(p.hasMetadata("Chest2")) {
								p.removeMetadata("Chest2", plugin);
							}
							
							if(p.hasMetadata("Sign")) {
								p.removeMetadata("Sign", plugin);
							}
							if(p.hasMetadata("Build")) {
								p.removeMetadata("Build", plugin);
							}
							if(p.hasMetadata("Citizen")) {
								p.removeMetadata("Citizen", plugin);
								String paths3 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
								int m = plugin.getConfig().getInt(paths3 + ".Citizens");
								plugin.getConfig().set(paths3 + ".Citizens", m-1);
								plugin.saveConfig();
								plugin.reloadConfig();
								
							}
							if(p.hasMetadata("Kidnapper")) {
								p.removeMetadata("Kidnapper", plugin);
								String paths3 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
								int c = plugin.getConfig().getInt(paths3 + ".Kidnappers");
								plugin.getConfig().set(paths3 + ".Kidnappers", c-1);
								plugin.saveConfig();
								plugin.reloadConfig();
								
							}
							if(p.hasMetadata("Start")) {
								p.removeMetadata("Start", plugin);
							}
							if(p.hasMetadata("Captured")) {
								for(Player k : Bukkit.getOnlinePlayers()) {
									k.showPlayer(p);
									//p.removeMetadata("Captured", plugin);
								}
								Player cap = Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString());
								p.showPlayer(Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString()));
								 ItemStack sack = new ItemStack(Material.CHEST, 1);
								ItemMeta sackmeta = sack.getItemMeta();
									ArrayList<String> sacklore = new ArrayList<String>();
									sacklore.clear();
									sacklore.add(ChatColor.GREEN + "Kidnap citizens with a Left-Click!");
									sackmeta.setDisplayName(ChatColor.DARK_GREEN + "Sack");
								    sackmeta.setLore(sacklore);
									sack.setItemMeta(sackmeta);
									cap.getInventory().setItem(0, sack);
									if(cap.hasPotionEffect(PotionEffectType.SLOW)) {
								    	cap.removePotionEffect(PotionEffectType.SLOW);
								    }
							    p.removeMetadata("Captured", plugin);
							    cap.removeMetadata("Capturer", plugin);
							    cap.sendMessage(ChatColor.RED + "Your citizen logged off!");
							    
							   
							}
							if(p.hasMetadata("Capturer")) {
								Player tar = Bukkit.getPlayer(p.getMetadata("Capturer").get(0).asString());
								tar.showPlayer(p);
								for(Player k : Bukkit.getOnlinePlayers()) {
									k.showPlayer(tar);
								}
								tar.removeMetadata("Captured", plugin);
								tar.sendMessage(ChatColor.GREEN + "The kidnapper logged off and you are now free!");
								p.removeMetadata("Capturer", plugin);
								if(tar.hasPotionEffect(PotionEffectType.BLINDNESS)) {
									tar.removePotionEffect(PotionEffectType.BLINDNESS);
								}
								
							}
							
							if(p.getMetadata("Games").get(0).asString().equals(sign.getLine(0).toString())) {
							lobbyteleport(p);
							final String paths = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
							if(Game.kidnappers.get(game) == null) {
								Game.kidnappers.put(game, new ArrayList<Player>());
							}
							if(Game.citizens.get(game) == null) {
								Game.citizens.put(game, new ArrayList<Player>());
							}
							if(Game.players.get(game)== null) {
								Game.players.put(game, new ArrayList<Player>());
								Game.players.get(game).add(p);
							}
							else {
								Game.players.get(game).add(p);
							}
							
							//plugin.getConfig().set(paths + ".number", plugin.getConfig().getInt(paths + ".number") + 1);
							
							
							int a = Game.players.get(game).size();
							//int a = plugin.getConfig().getInt(paths + ".number");
							//final int max = plugin.getConfig().getInt(paths + ".Max");
							//final int min = plugin.getConfig().getInt(paths + ".Min");
							
							final int max = Game.max.get(game);
							final int min = Game.min.get(game);
							int k = Game.kidnappers.get(game).size();
							int c = Game.citizens.get(game).size();
							//int k = plugin.getConfig().getInt(paths + ".Kidnappers");
							//int c = plugin.getConfig().getInt(paths + ".Citizens");
							//plugin.saveConfig();
							//plugin.reloadConfig();
								
							final int n = a;
							 Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
							   if(board.getObjective(sign.getLine(0).toString()) == null) {
								   Objective objective = board.registerNewObjective(sign.getLine(0).toString(), "stat");
								   objective.setDisplaySlot(DisplaySlot.SIDEBAR);
								    objective.setDisplayName(ChatColor.GREEN + sign.getLine(0).toString());
								    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
								    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
								    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
								    for(Player ps : Bukkit.getOnlinePlayers()) {
								    	if(ps.hasMetadata("Games")) {
								    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
								    			number.setScore(n);
								    			minn.setScore(min);
								    			maxx.setScore(max);
								    			ps.setScoreboard(board);
								    			
								    		}
								    	}
								    }
							   }
							   else {
								   Objective objective = board.getObjective(sign.getLine(0).toString());
								   objective.setDisplaySlot(DisplaySlot.SIDEBAR);
								    objective.setDisplayName(ChatColor.GREEN + sign.getLine(0).toString());
								    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
								    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
								    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
								    for(Player ps : Bukkit.getOnlinePlayers()) {
								    	if(ps.hasMetadata("Games")) {
								    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
								    			number.setScore(n);
								    			minn.setScore(min);
								    			maxx.setScore(max);
								    			ps.setScoreboard(board);
								    			
								    		}
								    	}
								    }
							   }
							    
							if(p.hasMetadata("Citizen")) {
								p.removeMetadata("Citizen", plugin);
							}
							if(p.hasMetadata("Kidnapper")) {
								p.removeMetadata("Kidnapper", plugin);
							}
	                        if(c == 0 || k * 2 > c) {
								if(!p.hasMetadata("Citizen") && !p.hasMetadata("Kidnapper")) {
	                        	p.setMetadata("Citizen", new FixedMetadataValue(plugin, p.getMetadata("Games").get(0).asString()));
	                        	if(Game.citizens.get(game)== null) {
	                        		Game.citizens.put(game, new ArrayList<Player>());
	                        		Game.citizens.get(game).add(p);
	                        	}
	                        	else {
	                        	Game.citizens.get(game).add(p);
	                        	}
	                        	//plugin.getConfig().set(paths + ".Citizens", c + 1);
								//plugin.saveConfig();
								//plugin.reloadConfig();
								}
							}
	                        else if(k * 2 <= c) {
	                        	if(!p.hasMetadata("Kidnapper") && !p.hasMetadata("Citizen")) {
	                        	p.setMetadata("Kidnapper", new FixedMetadataValue(plugin, p.getMetadata("Games").get(0).asString()));
	                        	if(Game.kidnappers.get(game)== null) {
	                        		Game.kidnappers.put(game, new ArrayList<Player>());
	                        		Game.kidnappers.get(game).add(p);
	                        	}
	                        	else {
	                        	Game.kidnappers.get(game).add(p);
	                        	}
	                        	//plugin.getConfig().set(paths + ".Kidnappers", k + 1);
	                        	//plugin.saveConfig();
								//plugin.reloadConfig();
	                        	}
	                        }
							if(n == max) {
								sign.setLine(1, ChatColor.DARK_RED + "[Full]");
								sign.update();
									
							}
							if(n == min) {
								//p.sendMessage(ChatColor.GREEN + "Game " + p.getMetadata("Games").get(0).asString() + ChatColor.GREEN + " will start in " + ChatColor.YELLOW + "2 minutes");
								for(Player pa : Bukkit.getOnlinePlayers()) {
									if(pa.hasMetadata("Games") && pa.getMetadata("Games").get(0).asString().equals(sign.getLine(0).toString())) {
										pa.sendMessage(ChatColor.GREEN + "Game " + p.getMetadata("Games").get(0).asString() + ChatColor.GREEN + " will start in " + ChatColor.YELLOW + "30 seconds!");
										//pa.setLevel(60);
									}
								}
										Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
											@Override
											public void run() {
												//int n1 = plugin.getConfig().getInt(paths + ".number");
												int n1 = Game.players.get(game).size();
												for(Player pa : Bukkit.getOnlinePlayers()) {
													if(pa.hasMetadata("Games") && pa.getMetadata("Games").get(0).asString().equals(sign.getLine(0).toString())) {
												
												
												if(!(n1 >= min)) {
													pa.sendMessage(ChatColor.RED + "Game doesn't have enough people!");
												}
												if(n1 >= min) {
											        pa.sendMessage(ChatColor.GREEN + "Game starts in " + ChatColor.YELLOW + "20 seconds");
												}
													}
												}
												if(n1 >= min) {
											        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
											        	@Override
											        	public void run() {
											        		//int n1 = plugin.getConfig().getInt(paths + ".number");
											        		int n1 = Game.players.get(game).size();
											        		for(Player pa : Bukkit.getOnlinePlayers()) {
																if(pa.hasMetadata("Games") && pa.getMetadata("Games").get(0).asString().equals(sign.getLine(0).toString())) {
											        	    
											        		
											        		if(!(n1 >= min)) {
																pa.sendMessage(ChatColor.RED + "Game doesn't have enough people!");
															}
															if(n1 >= min) {
														        pa.sendMessage(ChatColor.GREEN + "Game starts in " + ChatColor.YELLOW + "10 seconds");
															}
																}
											        		}
															if(n1 >= min) {
														        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
														        	@Override
														        	public void run() {
														        		//int n1 = plugin.getConfig().getInt(paths + ".number");
														        		int n1 = Game.players.get(game).size();
														        		for(Player pa : Bukkit.getOnlinePlayers()) {
																			if(pa.hasMetadata("Games") && pa.getMetadata("Games").get(0).asString().equals(sign.getLine(0).toString())) {
														        		
																				
														        		if(!(n1 >= min)) {
																			pa.sendMessage(ChatColor.RED + "Game doesn't have enough people!");
																		}
																		if(n1 >= min) {
																	        pa.sendMessage(ChatColor.GREEN + "Game starts in " + ChatColor.YELLOW + "5 seconds");
																		}
																			}
														        		}
														        		if(n1 >= min) {
																	        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
																	        	@Override
																	        	public void run() {
																	        		
																	        		//int n1 = plugin.getConfig().getInt(paths + ".number");
																	        		int n1 = Game.players.get(game).size();
																	        		if(n1 >= min) {
																	        			String nn1 = String.valueOf(n1);
																	        			sign.setLine(3, ChatColor.DARK_RED + "[Ingame]");
																						sign.setLine(1, ChatColor.RED + "[NotJoinable]");
																						sign.setLine(2, ChatColor.GREEN + nn1 + ChatColor.GREEN + "/" + ChatColor.GREEN + max);
																						//sign.setLine(2, ChatColor.GREEN + B2 + ChatColor.GREEN + "/" + ChatColor.GREEN + I);
																						sign.update();
																	        		}
																	        		for(Player pa : Bukkit.getOnlinePlayers()) {
																						if(pa.hasMetadata("Games")) {
																						 if(pa.getMetadata("Games").get(0).asString().equals(sign.getLine(0).toString())) {
																						
																
																					
																	        		if(!(n1 >= min)) {
																						pa.sendMessage(ChatColor.RED + "Game doesn't have enough people!");
																					}
																					if(n1 >= min) {
																						int k = plugin.getConfig().getInt(paths + ".Kidnappers");
																						int c = plugin.getConfig().getInt(paths + ".Citizens");
																						pa.setMetadata("Start", new FixedMetadataValue(plugin, "yes"));
																						pa.setHealth(20);
																						pa.setGameMode(GameMode.SURVIVAL);
																						pa.sendMessage(ChatColor.GREEN + "Game has started!");
																						Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
																					    if(board.getObjective(sign.getLine(0).toString()) == null) {
																						Objective objective = board.registerNewObjective(sign.getLine(0).toString(), "stat");
																					    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
																					    objective.setDisplayName(ChatColor.GREEN + sign.getLine(0).toString());
																					    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
																					    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
																					    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
																					 
																					    Score kidnappers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Kidnappers:"));
																						Score citizens = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Citizens:"));
																					    number.setScore(n1);
																					    kidnappers.setScore(k);
																					    citizens.setScore(c);
																					    minn.setScore(min);
																						maxx.setScore(max);
																						pa.setScoreboard(board);
																					    }
																					    else {
																					    	Objective objective = board.getObjective(sign.getLine(0).toString());
																						    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
																						    objective.setDisplayName(ChatColor.GREEN + sign.getLine(0).toString());
																						    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
																						    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
																						    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
																						 
																						    Score kidnappers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Kidnappers:"));
																							Score citizens = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Citizens:"));
																						    number.setScore(n1);
																						    kidnappers.setScore(k);
																						    citizens.setScore(c);
																						    minn.setScore(min);
																							maxx.setScore(max);
																							pa.setScoreboard(board);
																					    }
																					    			
																					    		
																					    	
																					     Game.ingame.put(game, false);
																						//plugin.getConfig().set(Main.path + sign.getLine(0).toString() + ".Ingame", "true");
																						//plugin.saveConfig();
																						//plugin.reloadConfig();
									
																						if(pa.hasMetadata("Citizen")) {
																							citizenteleport(pa);
																							pa.sendMessage(ChatColor.GOLD + "You are a " + ChatColor.YELLOW + "Citizen.");
																							ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
																							sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
																							pa.getInventory().setItem(0, sword);
																							pa.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (short) 3));
																							//Bukkit.getScheduler().cancelTask(taskid);
																						}
																						else if(pa.hasMetadata("Kidnapper")) {
																							kidnapperlobbyteleport(pa);
																							pa.sendMessage(ChatColor.GOLD + "You are a " + ChatColor.RED + "Kidnapper.");
																							pa.sendMessage(ChatColor.LIGHT_PURPLE + "You will be teleported into the game in 20 seconds!");
																							
																							ItemStack sack = new ItemStack(Material.CHEST, 1);
																							ItemMeta sackmeta = sack.getItemMeta();
																							ArrayList<String> sacklore = new ArrayList<String>();
																							sacklore.clear();
																							sacklore.add(ChatColor.GREEN + "Kidnap citizens with a Left-Click!");
																							sackmeta.setDisplayName(ChatColor.DARK_GREEN + "Sack");
																						    sackmeta.setLore(sacklore);
																							sack.setItemMeta(sackmeta);
																							pa.getInventory().setItem(0, sack);
																							
																							ItemStack stick = new ItemStack(Material.STICK, 1);
																							ItemMeta stickmeta = stick.getItemMeta();
																							ArrayList<String> sticklore = new ArrayList<String>();
																							sticklore.clear();
																							sticklore.add(ChatColor.GREEN + "Hit citizens away!");
																							stickmeta.setDisplayName(ChatColor.AQUA + "Baton");
																							stickmeta.setLore(sticklore);
																							stick.setItemMeta(stickmeta);
																							stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
																							pa.getInventory().setItem(1, stick);
																							
																							ItemStack helm = new ItemStack(Material.WOOL, 1, (short) 7);
																							ItemStack chestbody = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
																							ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
																							ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
																							LeatherArmorMeta chestmeta = (LeatherArmorMeta) chestbody.getItemMeta();
																							LeatherArmorMeta legmeta = (LeatherArmorMeta) leggings.getItemMeta();
																							LeatherArmorMeta bootsmeta = (LeatherArmorMeta) boots.getItemMeta();
																							
																							chestmeta.setColor(Color.GRAY);
																							legmeta.setColor(Color.GRAY);
																							bootsmeta.setColor(Color.GRAY);
																							
																							chestbody.setItemMeta(chestmeta);
																							leggings.setItemMeta(legmeta);
																							boots.setItemMeta(bootsmeta);
																						    chestbody.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
																						    leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
																						    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
																						    chestbody.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
																						    leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
																						    boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
																							
																							pa.getInventory().setHelmet(helm);
																							pa.getInventory().setChestplate(chestbody);
																							pa.getInventory().setLeggings(leggings);
																							pa.getInventory().setBoots(boots);
																							
																							
																							final Player ps = pa;
																							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
																								@Override
																								public void run() {
					                                                                                
																									kidnapperspawnteleport(ps);
																									
																								}
																									
																								
																							}, 20*20L);
																								
																							
																						}
																						
																					}
																						}
																	        		}
																					}
																	        	
																	        	}
																	        }, 5*20L);
																		}
																	        
														        }
														        	
														        }, 5*20L);
															}
											        		}
														        	
											        	
											        }, 10*20L);
												
															}
																
											}
										}, 10*20L);
										
													
													
										
										
										
										
								
							
										}
								}
							}
						
						
					}
					}
				}, 2L);
				
			}
			
			
			if(sign.getLine(2).equalsIgnoreCase(ChatColor.GREEN + "to leave game")) {
				//System.out.println("Leave Game should work?");
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						if(p.hasMetadata("Games") && !p.hasMetadata("Start") && !p.hasMetadata("Wait")) {
							final String game = p.getMetadata("Games").get(0).asString();
							String pathsa = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
							int aa = Game.players.get(game).size();
							//int aa = plugin.getConfig().getInt(pathsa + ".number");
							//final int max = plugin.getConfig().getInt(pathsa + ".Max");
							//final int min = plugin.getConfig().getInt(pathsa + ".Min");
							//int kidders = plugin.getConfig().getInt(pathsa + ".Kidnappers");
							//int citters = plugin.getConfig().getInt(pathsa + ".Citizens");
							final int max = Game.max.get(game);
							final int min = Game.min.get(game);
							int kidders = Game.kidnappers.get(game).size();
							int citters = Game.citizens.get(game).size();
							p.setMetadata("Wait", new FixedMetadataValue(plugin, "yes"));
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.removeMetadata("Wait", plugin);								}
							}, 2L);
							Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
							if(board.getObjective(sign.getLine(0).toString()) == null) {
							Objective objective = board.registerNewObjective(sign.getLine(0).toString(), "stat");
						    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
						    objective.setDisplayName(ChatColor.GREEN + sign.getLine(0).toString());
						    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
						    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
						    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
						    for(Player ps : Bukkit.getOnlinePlayers()) {
						    	if(ps.hasMetadata("Games")) {
						    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString()) && !ps.getName().equals(Bukkit.getPlayer(p.getName()))) {
						    			if(!ps.hasMetadata("Start")) {
						    			number.setScore(aa);
						    			minn.setScore(min);
						    			maxx.setScore(max);
						    			ps.setScoreboard(board);
						    			}
						    			else if(ps.hasMetadata("Start") && !ps.getName().equals(Bukkit.getPlayer(p.getName()))){
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
								Objective objective = board.getObjective(sign.getLine(0).toString());
							    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
							    objective.setDisplayName(ChatColor.GREEN + sign.getLine(0).toString());
							    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
							    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
							    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
							    for(Player ps : Bukkit.getOnlinePlayers()) {
							    	if(ps.hasMetadata("Games")) {
							    		if(ps.getMetadata("Games").get(0).asString().equals(p.getMetadata("Games").get(0).asString())) {
							    			if(!ps.hasMetadata("Start") && !ps.getName().equals(Bukkit.getPlayer(p.getName()))) {
							    			number.setScore(aa);
							    			minn.setScore(min);
							    			maxx.setScore(max);
							    			ps.setScoreboard(board);
							    			}
							    			else if(ps.hasMetadata("Start") && !ps.getName().equals(Bukkit.getPlayer(p.getName()))){
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
							
						    
						    Scoreboard boardd = Bukkit.getScoreboardManager().getNewScoreboard();
						    if(board.getObjective("Lobby") == null) {
						    Objective objectivee = boardd.registerNewObjective("Lobby", "stat");
						    objectivee.setDisplaySlot(DisplaySlot.SIDEBAR);
						    objectivee.setDisplayName(ChatColor.RED + "Abduction");
						    Score numbere = objectivee.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
						    numbere.setScore(Bukkit.getOnlinePlayers().size());
						    p.setScoreboard(boardd);
						    }
						    else {
						    	Objective objectivee = boardd.getObjective("Lobby");
							    objectivee.setDisplaySlot(DisplaySlot.SIDEBAR);
							    objectivee.setDisplayName(ChatColor.RED + "Abduction");
							    Score numbere = objectivee.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
							    numbere.setScore(Bukkit.getOnlinePlayers().size());
							    p.setScoreboard(boardd);
						    }
						    
							String paths3 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
							 // if(GameLobbyTeleport.gamer.contains(p)) {
								//	GameLobbyTeleport.gamer.remove(p);
								//}
							if(p.hasPotionEffect(PotionEffectType.SLOW)) {
						    	p.removePotionEffect(PotionEffectType.SLOW);
						    }
							  if(p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
									p.removePotionEffect(PotionEffectType.BLINDNESS);
								}
							  
							  if(Capturing.chestloc.containsKey(p)) {
								  Location location = Capturing.chestloc.get(p);
								  p.getWorld().getBlockAt(location).setType(Material.AIR);
								  Capturing.chestloc.remove(p);
								  p.getWorld().getBlockAt(location).removeMetadata("Capture", plugin);
							  }
							  
							  if(p.hasMetadata("Chester")) {
								  for(Player f : Bukkit.getOnlinePlayers()) {
										f.showPlayer(p);
										//p.removeMetadata("Captured", plugin);
									}
								  //p.getWorld().getBlockAt(p.getLocation()).setType(Material.AIR);
									p.removeMetadata("Chester", plugin);
							  }
							  if(p.hasMetadata("killed")) {
									p.removeMetadata("killed", plugin);
								}
							  if(p.hasMetadata("Citizen")) {
									p.removeMetadata("Citizen", plugin);
									
									//int m = plugin.getConfig().getInt(paths3 + ".Citizens");
									Game.citizens.get(game).remove(p);
									//plugin.getConfig().set(paths3 + ".Citizens", m-1);
									//plugin.saveConfig();
									//plugin.reloadConfig();
								}
								if(p.hasMetadata("Kidnapper")) {
									p.removeMetadata("Kidnapper", plugin);
									//int c = plugin.getConfig().getInt(paths3 + ".Kidnappers");
									Game.kidnappers.get(game).remove(p);
									//plugin.getConfig().set(paths3 + ".Kidnappers", c-1);
									
									//plugin.saveConfig();
									//plugin.reloadConfig();
									
								}
								if(p.hasMetadata("Start")) {
									p.removeMetadata("Start", plugin);
								}
								if(p.hasMetadata("Captured")) {
									for(Player k : Bukkit.getOnlinePlayers()) {
										k.showPlayer(p);
										//p.removeMetadata("Captured", plugin);
									}
									Player cap = Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString());
									p.showPlayer(Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString()));
									   ItemStack sack = new ItemStack(Material.CHEST, 1);
										ItemMeta sackmeta = sack.getItemMeta();
										ArrayList<String> sacklore = new ArrayList<String>();
										sacklore.clear();
										sacklore.add(ChatColor.GREEN + "Kidnap citizens with a Left-Click!");
										sackmeta.setDisplayName(ChatColor.DARK_GREEN + "Sack");
									    sackmeta.setLore(sacklore);
										sack.setItemMeta(sackmeta);
										cap.getInventory().setItem(0, sack);
										if(cap.hasPotionEffect(PotionEffectType.SLOW)) {
									    	cap.removePotionEffect(PotionEffectType.SLOW);
									    }
								    p.removeMetadata("Captured", plugin);
								    cap.removeMetadata("Capturer", plugin);
								    cap.sendMessage(ChatColor.RED + "Your citizen logged off!");
								    
								 
								    
								}
								if(p.hasMetadata("Capturer")) {
									Player tar = Bukkit.getPlayer(p.getMetadata("Capturer").get(0).asString());
									tar.showPlayer(p);
									for(Player k : Bukkit.getOnlinePlayers()) {
										k.showPlayer(tar);
									}
									tar.removeMetadata("Captured", plugin);
									tar.sendMessage(ChatColor.GREEN + "The kidnapper logged off and you are now free!");
									p.removeMetadata("Capturer", plugin);
									if(tar.hasPotionEffect(PotionEffectType.BLINDNESS)) {
										tar.removePotionEffect(PotionEffectType.BLINDNESS);
									}
									
								}
								String paths = Main.path + p.getMetadata("Games").get(0).asString() + ".location";
								
								//System.out.println(paths);
								int x = plugin.getConfig().getInt(paths + ".XLoc");
								int y = plugin.getConfig().getInt(paths + ".YLoc");
								int z = plugin.getConfig().getInt(paths + ".ZLoc");
								Location location = new Location(p.getWorld(), x, y, z);
								//System.out.println(location);
								//System.out.println("This event works");
								String paths2 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
								
								//int a = plugin.getConfig().getInt(paths2 + ".number");
								int a = Game.players.get(game).size();
							    Game.players.get(game).remove(p);
								//plugin.getConfig().set(paths2 + ".number", a-1);
					
								//plugin.saveConfig();
								//plugin.reloadConfig();
								
//								int n = a+1;
//								if(n >= min && n <= max) {
//									p.sendMessage(ChatColor.GREEN + "Game " + p.getMetadata("Games").get(0).asString() + ChatColor.GREEN + " will start in " + ChatColor.YELLOW + "2 minutes");
//									for(Player pa : Bukkit.getOnlinePlayers()) {
//										if(pa.getMetadata("Games").get(0).asString() == p.getMetadata("Games").get(0).asString()) {
//											pa.sendMessage(ChatColor.GREEN + "Game " + p.getMetadata("Games").get(0).asString() + ChatColor.GREEN + " will start in " + ChatColor.YELLOW + "2 minutes");
//										}
//									}
//								}
								final String trues = Main.path + p.getMetadata("Games").get(0).asString() + ".Ingame";
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										p.removeMetadata("Games", plugin);
									}
								}, 5L);
								
								mainlobbyteleport(p);
								p.getInventory().clear();
								try {
									p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
								    p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
								    p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
								    p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
								} catch (java.lang.NullPointerException e) {
									
								}
								if(p.getWorld().getBlockAt(location).getType().equals(Material.SIGN_POST)) {
									Sign sign = (Sign) p.getWorld().getBlockAt(location).getState();
									//System.out.println("Config part works");
									for(int i = 0; i < 100; i++) {
										for(int c = 0; c < 100; c++) {
											String I = String.valueOf(i);
						                    String C = String.valueOf(c);
											if(sign.getLine(2).equalsIgnoreCase(ChatColor.GREEN + I + ChatColor.GREEN + "/" + ChatColor.GREEN + C)) {
												int o = i-1;
											//	System.out.println("Works!");
												String ok = String.valueOf(o);
												sign.setLine(2, ChatColor.GREEN + ok + ChatColor.GREEN + "/" + ChatColor.GREEN + C);
												sign.update();
												
											
								
												//if(o != c && plugin.getConfig().getString(trues).equals("false")) {
													if(o != c && Game.ingame.get(game) == false) {
												    if(sign.getLine(3).equalsIgnoreCase(ChatColor.RED + "[Lobby]")) {
													sign.setLine(1, ChatColor.DARK_BLUE + "[Join]");
													sign.update();
													}
													if(o == 0) {
														
												
													sign.setLine(1, ChatColor.DARK_BLUE + "[Join]");
													sign.setLine(3, ChatColor.RED + "[Lobby]");
													sign.update();
													}
													
												}
												break;
												
											}
										}
									}
								}
							
							
							
						}
					}
				}, 2L);
			}
	}

}
}
}