package me.Firegred.Kidnapper.PlayerFunctions;

import java.util.ArrayList;

import me.Firegred.Kidnapper.*;
import me.Firegred.Kidnapper.GameMechanics.Capturing;
import me.Firegred.Kidnapper.GameMechanics.Game;
import me.Firegred.Kidnapper.LobbyFunctions.GameLobbyTeleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerSignLogoff implements Listener {
	
	public Main plugin;
	
	public PlayerSignLogoff(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void SignChanges(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		//if(GameLobbyTeleport.gamer.contains(p)) {
		//	GameLobbyTeleport.gamer.remove(p);
		//}
		if(p.hasMetadata("Wait")) {
			p.removeMetadata("Wait", plugin);
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
		if(p.hasMetadata("Games")) {
			final String game = p.getMetadata("Games").get(0).asString();
			if(p.hasMetadata("Citizen")) {
				p.removeMetadata("Citizen", plugin);
				String paths3 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
				Game.citizens.get(game).remove(p);
				//int m = plugin.getConfig().getInt(paths3 + ".Citizens");
				
				//plugin.getConfig().set(paths3 + ".Citizens", m-1);
				//plugin.saveConfig();
				//plugin.reloadConfig();
			}
			if(p.hasMetadata("Kidnapper")) {
				p.removeMetadata("Kidnapper", plugin);
				String paths3 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
				//int c = plugin.getConfig().getInt(paths3 + ".Kidnappers");
				Game.kidnappers.get(game).remove(p);
				//plugin.getConfig().set(paths3 + ".Kidnappers", c-1);
				//plugin.saveConfig();
				//plugin.reloadConfig();
				
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
			Game.players.get(game).remove(p);
			//plugin.getConfig().set(paths2 + ".number", plugin.getConfig().getInt(paths2 + ".number") - 1);
			//plugin.saveConfig();
			//plugin.reloadConfig();
			//final String trues = Main.path + p.getMetadata("Games").get(0).asString() + ".Ingame";
			final boolean trues = Game.ingame.get(game);
			if(Game.players.get(game).size() <= 0) {
				Game.ingame.put(game, false);
				//plugin.getConfig().set(trues, "false");
				Game.players.clear();
				//plugin.getConfig().set(paths2 + ".number", "0");
				//plugin.saveConfig();
				//plugin.reloadConfig();
			}
			String pathsa = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
			
			int aa = Game.players.get(game).size();
			final int max = Game.max.get(game);
			final int min = Game.min.get(game);
			int kidders = Game.kidnappers.get(game).size();
			int citters = Game.citizens.get(game).size();
			
			//int aa = plugin.getConfig().getInt(pathsa + ".number");
			//final int max = plugin.getConfig().getInt(pathsa + ".Max");
			//final int min = plugin.getConfig().getInt(pathsa + ".Min");
			//int kidders = plugin.getConfig().getInt(pathsa + ".Kidnappers");
			//int citters = plugin.getConfig().getInt(pathsa + ".Citizens");
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			if(board.getObjective("Lobby") == null) {
		    	
		    	Objective objective = board.registerNewObjective("Lobby", "stat");
		    	objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			    objective.setDisplayName(ChatColor.RED + "Abduction");
			    for(Player s : Bukkit.getOnlinePlayers()) {
			    if(!s.hasMetadata("Games")) {
			    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
			    number.setScore(Bukkit.getOnlinePlayers().size());
			    s.setScoreboard(board);
			    }
			    }
	        }
		    else {
			Objective objective = board.getObjective("Lobby");
		    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		    objective.setDisplayName(ChatColor.RED + "Abduction");
		    for(Player s : Bukkit.getOnlinePlayers()) {
			    if(!s.hasMetadata("Games")) {
			    Score number = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players:"));
			    number.setScore(Bukkit.getOnlinePlayers().size());
			    s.setScoreboard(board);
			    }
			    }
	        
		    
		    }
			
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
			p.removeMetadata("Games", plugin);
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
							sign.setLine(2, ChatColor.GREEN + String.valueOf(Game.players.size()) + ChatColor.GREEN + "/" + ChatColor.GREEN + C);
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
	}


