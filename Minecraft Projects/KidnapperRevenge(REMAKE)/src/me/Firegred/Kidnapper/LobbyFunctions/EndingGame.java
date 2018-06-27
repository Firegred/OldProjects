package me.Firegred.Kidnapper.LobbyFunctions;

import java.util.ArrayList;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.GameMechanics.Capturing;
import me.Firegred.Kidnapper.GameMechanics.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class EndingGame implements Listener{
	
	public Main plugin;
	
	public EndingGame(Main instance) {
		plugin = instance;
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
	public void mainLobbyTeleport(Player player) {
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
	public void jailteleport(Player player) {
		String clob = Main.path + player.getMetadata("Games").get(0).asString() + ".gamespawn.jail.";
		int x = plugin.getConfig().getInt(clob + "X");
		int y = plugin.getConfig().getInt(clob + "Y");
		int z = plugin.getConfig().getInt(clob + "Z");
		//plugin.saveConfig();
	//	plugin.reloadConfig();
		Location location = new Location(player.getWorld(), x, y, z);
		player.teleport(location);
	}
	
	@EventHandler
	public void respawn(PlayerRespawnEvent event) {
		final Player p = event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if(p.hasMetadata("Games") && !p.hasMetadata("Citizen")) {
					kidnapperlobbyteleport(p);
					//System.out.println("Should work");
				}
				if(p.hasMetadata("Citizen") && p.hasMetadata("Games")) {
					citizenteleport(p);
					ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
					sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					p.getInventory().setItem(0, sword);
					p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (short) 3));
				}
				else if(!p.hasMetadata("Games")){
					mainLobbyTeleport(p);
				}
			}
		}, 10L);
		
	}
	
	
	@EventHandler
	public void move(PlayerMoveEvent event) {
		final Player ccc = event.getPlayer();
		if(ccc.hasMetadata("Games") && ccc.hasMetadata("Start")) {
			final String game = ccc.getMetadata("Games").get(0).asString();
			//String paths22 = Main.path + ccc.getMetadata("Games").get(0).asString() + ".players";
			
			
			
			final String trues = Main.path + ccc.getMetadata("Games").get(0).asString() + ".Ingame";
			//if(plugin.getConfig().getInt(paths22 + ".Citizens") == 0 && plugin.getConfig().getString(trues).equals("true")) {
			if(Game.citizens.get(game).size() == 0 && Game.ingame.get(game) == true) {	
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(ChatColor.YELLOW + "Kidnappers have won game " + ChatColor.GREEN + ccc.getMetadata("Games").get(0).asString());
				//if(GameLobbyTeleport.gamer.contains(p)) {
				//	GameLobbyTeleport.gamer.remove(p);
				//}
			}
			}
			//if(plugin.getConfig().getInt(paths22 + ".Kidnappers") == 0 && plugin.getConfig().getString(trues).equals("true")) {
			if(Game.kidnappers.get(game).size() == 0 && Game.ingame.get(game) == true) {
			for(Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(ChatColor.YELLOW + "Citizens have won game " + ChatColor.GREEN + ccc.getMetadata("Games").get(0).asString());
					//if(GameLobbyTeleport.gamer.contains(p)) {
					//	GameLobbyTeleport.gamer.remove(p);
					//}
				}
			}
			//if(plugin.getConfig().getInt(paths22 + ".Citizens") == 0 && plugin.getConfig().getString(trues).equals("true") || plugin.getConfig().getInt(paths22 + ".Kidnappers") == 0 && plugin.getConfig().getString(trues).equals("true")) {
				if(Game.citizens.get(game).size() == 0 && Game.ingame.get(game) == true || Game.kidnappers.get(game).size() == 0 && Game.ingame.get(game) == true) {
			    Game.ingame.put(game, false);
				//plugin.getConfig().set(trues, "false");
				//plugin.saveConfig();
				//plugin.reloadConfig();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						if(plugin.getConfig().getString(trues).equals("false")) {
						for(Player plo : Bukkit.getOnlinePlayers()) {
							if(plo.hasMetadata("Games")) {
								String pas = Main.path + plo.getMetadata("Games").get(0).asString() + ".players";
								if(plugin.getConfig().getInt(pas + ".number") <= 0) {
									Game.players.get(game).clear();
									Game.kidnappers.get(game).clear();
									Game.citizens.get(game).clear();
									//plugin.getConfig().set(pas + ".number", 0);
									//plugin.getConfig().set(pas + ".Kidnappers", 0);
									//plugin.getConfig().set(pas + ".Citizens", 0);
									//plugin.saveConfig();
									//plugin.reloadConfig();
									mainLobbyTeleport(plo);
								}
							}
						}
					}
					}
						}, 10L);
					
				
				
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.hasMetadata("Games")) {
					if(p.getMetadata("Games").get(0).asString().equals(ccc.getMetadata("Games").get(0).asString())) {
						//final String game = p.getMetadata("Games").get(0).asString();
						mainLobbyTeleport(p);
						if(p.getGameMode().equals(GameMode.SPECTATOR)) {
							p.setGameMode(GameMode.SURVIVAL);
						}
						Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
					    if(board.getObjective(p.getMetadata("Games").get(0).asString()) == null) {
						Objective objective = board.registerNewObjective("Lobby", "stat");
					    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
					    objective.setDisplayName(ChatColor.RED + "Abduction");
					    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
					    number.setScore(Bukkit.getOnlinePlayers().size());
					    p.setScoreboard(board);
					    }
					    else {
					    	Objective objective = board.getObjective("Lobby");
						    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
						    objective.setDisplayName(ChatColor.RED + "Abduction");
						    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
						    number.setScore(Bukkit.getOnlinePlayers().size());
						    p.setScoreboard(board);	
					    }
					    if(p.hasMetadata("Wait")) {
							p.removeMetadata("Wait", plugin);
						}
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
							//p.getWorld().getBlockAt(p.getLocation()).setType(Material.AIR);
							for(Player f : Bukkit.getOnlinePlayers()) {
								f.showPlayer(p);
								//p.removeMetadata("Captured", plugin);
							}
							p.removeMetadata("Chester", plugin);
							final Player cccc = p;
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									mainLobbyTeleport(cccc);
								}
							}, 1L);
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
							Game.citizens.get(game).remove(p);
							//plugin.getConfig().set(paths3 + ".Citizens", m-1);
							//plugin.saveConfig();
							//plugin.reloadConfig();
						}
						if(p.hasMetadata("Kidnapper")) {
							p.removeMetadata("Kidnapper", plugin);
							String paths3 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
							int c = plugin.getConfig().getInt(paths3 + ".Kidnappers");
							Game.kidnappers.get(game).remove(p);
							//plugin.getConfig().set(paths3 + ".Kidnappers", c-1);
							//plugin.saveConfig();
							//plugin.reloadConfig();
							
						}
						if(p.hasMetadata("Start")) {
							p.removeMetadata("Start", plugin);
						}
						if(p.hasMetadata("Captured")) {
							for(Player f : Bukkit.getOnlinePlayers()) {
								f.showPlayer(p);
								//p.removeMetadata("Captured", plugin);
							}
							Player cap = Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString());
							p.showPlayer(Bukkit.getPlayer(p.getMetadata("Captured").get(0).asString()));
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
							for(Player f : Bukkit.getOnlinePlayers()) {
								f.showPlayer(tar);
							}
							tar.removeMetadata("Captured", plugin);
							tar.sendMessage(ChatColor.GREEN + "The kidnapper logged off and you are now free!");
							p.removeMetadata("Capturer", plugin);
							
						}
						if(p.hasMetadata("Games")) {
							
							String paths = Main.path + p.getMetadata("Games").get(0).asString() + ".location";
							
							//System.out.println(paths);
							int x = plugin.getConfig().getInt(paths + ".XLoc");
							int y = plugin.getConfig().getInt(paths + ".YLoc");
							int z = plugin.getConfig().getInt(paths + ".ZLoc");
							Location location = new Location(p.getWorld(), x, y, z);
							//System.out.println(location);
							//System.out.println("This event works");
							String paths2 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
							Game.players.clear();
							Game.citizens.clear();
							Game.kidnappers.clear();
							//plugin.getConfig().set(paths2 + ".number", 0);
							//plugin.getConfig().set(paths2 + ".Kidnappers", 0);
							//plugin.getConfig().set(paths2 + ".Citizens", 0);
							//plugin.saveConfig();
							//plugin.reloadConfig();
							if(Bukkit.getPlayer(p.getName()) != Bukkit.getPlayer(ccc.getName())) {
							p.removeMetadata("Games", plugin);
							}
							else {
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										Player pp = ccc;
										pp.removeMetadata("Games", plugin);
									}
								}, 5L);
							}
							p.getInventory().clear();
							try {
								p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							    p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							    p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							    p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							} catch (java.lang.NullPointerException e) {
								
							}
							
							if(p.getWorld().getBlockAt(location).getType().equals(Material.SIGN_POST)) {
								final Sign sign = (Sign) p.getWorld().getBlockAt(location).getState();
								//System.out.println("Config part works");
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
								
								for(int i = 0; i < 100; i++) {
									for(int c = 0; c < 100; c++) {
										String I = String.valueOf(i);
					                    String C = String.valueOf(c);
										if(sign.getLine(2).equalsIgnoreCase(ChatColor.GREEN + I + ChatColor.GREEN + "/" + ChatColor.GREEN + C)) {
											int o = i-1;
										//	System.out.println("Works!");
											String ok = String.valueOf(o);
											sign.setLine(2, ChatColor.GREEN + "0" + ChatColor.GREEN + "/" + ChatColor.GREEN + C);
											sign.update();
											
												
												
														
												
													sign.setLine(1, ChatColor.DARK_BLUE + "[Join]");
													sign.setLine(3, ChatColor.RED + "[Lobby]");
													sign.update();
													
											
												
											 break;
											}
										
									
								
									}
								}
									}
								
								}, 25L); //
							}
						}
					}
				
					}
				}
			}
			
		}
	}
	
	@EventHandler
	public void death(PlayerDeathEvent event) {
		event.setDeathMessage("");
	}
	
	@EventHandler
	public void jailing(EntityDeathEvent event) {
		if(event.getEntity() instanceof Player) {
		final Player pp = (Player) event.getEntity();
		if(pp.hasMetadata("Kidnapper")) {
			//if(pp.getHealth() - event.getDamage() <= 0) {
			//event.setCancelled(true);
			if(pp.hasMetadata("Capturer")) {
				Player target = Bukkit.getPlayer(pp.getMetadata("Capturer").get(0).asString());
				target.showPlayer(pp);
				for(Player f : Bukkit.getOnlinePlayers()) {
					f.showPlayer(target);
					
				}
				target.removeMetadata("Captured", plugin);
				target.sendMessage(ChatColor.GREEN + "The kidnapper died and you are now free!");
				if(target.hasPotionEffect(PotionEffectType.BLINDNESS)) {
					target.removePotionEffect(PotionEffectType.BLINDNESS);
				}
				pp.removeMetadata("Capturer", plugin);
				
			}
			String game = pp.getMetadata("Games").get(0).asString();
			String paths22 = Main.path + pp.getMetadata("Games").get(0).asString() + ".players";
			//int k = plugin.getConfig().getInt(paths22 + ".Kidnappers");
			//plugin.getConfig().set(paths22 + ".Kidnappers", k-1);
			//plugin.saveConfig();
			//plugin.reloadConfig();
			
			final int aa = Game.players.get(game).size();
			final int max = Game.max.get(game);
			final int min = Game.min.get(game);
			final int kidders = Game.kidnappers.get(game).size();
			final int citters = Game.citizens.get(game).size();
			//final int aa = plugin.getConfig().getInt(paths22 + ".number");
			//final int max = plugin.getConfig().getInt(paths22 + ".Max");
			//final int min = plugin.getConfig().getInt(paths22 + ".Min");
			//final int kidders = plugin.getConfig().getInt(paths22 + ".Kidnappers");
			//final int citters = plugin.getConfig().getInt(paths22 + ".Citizens");
			
			//if(plugin.getConfig().getInt(paths22 + ".Kidnappers") != 0) {
			//jailteleport(pp);
			//}
			pp.sendMessage(ChatColor.RED + "The citizens sent you to justice!");
			pp.setMetadata("killed", new FixedMetadataValue(plugin, pp.getMetadata("Games").get(0).asString()));
			pp.removeMetadata("Kidnapper", plugin);
			pp.removeMetadata("Start", plugin);
			
			for(Player g : Bukkit.getOnlinePlayers()) {
				if(pp.hasMetadata("Games")) {
				if(pp.getMetadata("Games").get(0).asString().equals(g.getMetadata("Games").get(0).asString())) {
					g.sendMessage(ChatColor.GREEN + "Kidnapper " + pp.getName() + ChatColor.GREEN + " has been killed!");
					g.playSound(g.getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);
				}
				}
			}
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            	@Override
            	public void run() {
            		
           
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		    Objective objective = board.getObjective(pp.getMetadata("Games").get(0).asString());
		    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		    objective.setDisplayName(ChatColor.GREEN + pp.getMetadata("Games").get(0).asString());
		    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
		    Score minn = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Min:"));
		    Score maxx = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Max:"));
		    for(Player ps : Bukkit.getOnlinePlayers()) {
		    	if(ps.hasMetadata("Games")) {
		    		if(ps.getMetadata("Games").get(0).asString().equals(pp.getMetadata("Games").get(0).asString())) {
		    			if(!ps.hasMetadata("Start") && !ps.getName().equals(pp.getName())) {
		    			number.setScore(aa);
		    			minn.setScore(min);
		    			maxx.setScore(max);
		    			ps.setScoreboard(board);
		    			}
		    			else if(ps.hasMetadata("Start") && !ps.getName().equals(pp.getName())){
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
            }, 10L);
			
		}
	   
		}
		}
//	}

}
		
