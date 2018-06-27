package me.Firegred.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;


import me.Firegred.Lobby.Teleports;
import me.Firegred.Lobby.Waiting;
import me.Firegred.Main.Main;

public class SignClick implements Listener {
public static Main plugin;
	
	public SignClick(Main instance) {
		this.plugin = instance;
	}
	
	private static boolean joinGame(Sign s) {
		if(s.getLine(1).equals(ChatColor.GREEN + "[Join]")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void signidentify(Player p,Sign s) {
		if(s.getLine(1).equals(ChatColor.GREEN + "[Join]")) {
			String name = s.getLine(0).toString();
			p.setMetadata("Game", new FixedMetadataValue(plugin, s.getLine(0).toString()));
			Teleports.GameLobbyTeleport(p, name);
			OnServer(p, s);
			p.sendMessage(Main.trees + ChatColor.GREEN + "You joined game " + ChatColor.RED + name);
		}
		else if(s.getLine(1).equals(ChatColor.RED + "[Ingame]")) {
			p.sendMessage(Main.trees + ChatColor.RED + "This game is in session");
		}
		else if(s.getLine(1).equals(ChatColor.GREEN + "[Leave Game]") && p.hasMetadata("Game")) {
			Teleports.ServerLobbyTeleport(p);
			p.sendMessage(Main.trees + ChatColor.GREEN + "You left game the game");
			OnServerNO(p);
			p.removeMetadata("Game", plugin);
			
			
			
		}
	}
	
	public static void OnServerNO(Player p) {
		int on = 0;
		String name = p.getMetadata("Game").get(0).asString();
		int max = plugin.getConfig().getInt(Main.games + "." + name + ".Max");
		for(Player c : Bukkit.getOnlinePlayers()) {
			if(c.hasMetadata("Game")) {
				if(c.getMetadata("Game").get(0).asString().equals(name) && !c.getName().equals(p.getName())) {
					on++;
				}
			}
		}
		int x = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.X");
		int y = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Y");
		int z = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Z");
		Location l = new Location(Main.w, x,y,z);
		Sign s = (Sign) Main.w.getBlockAt(l).getState();
		s.setLine(2, on + " / " + max);
		s.update();
	}
	
	public static void OnServer(Player p, Sign s) {
		int on = 0;
		String name = p.getMetadata("Game").get(0).asString();
		int max = plugin.getConfig().getInt(Main.games + "." + name + ".Max");
		for(Player c : Bukkit.getOnlinePlayers()) {
			if(c.hasMetadata("Game")) {
				if(c.getMetadata("Game").get(0).asString().equals(name)) {
					on++;
				}
			}
		}
		s.setLine(2, on + " / " + max);
		s.update();
	}
	public static void PlayerLeaveGame(Player p) {
		int on = 0;
		String name = p.getMetadata("Game").get(0).asString();
		int max = plugin.getConfig().getInt(Main.games + "." + name + ".Max");
		for(Player c : Bukkit.getOnlinePlayers()) {
			if(c.hasMetadata("Game") && c != p) {
				if(c.getMetadata("Game").get(0).asString().equals(name) && !c.getName().equals(p.getName())) {
					on++;
				}
			}
		}
		int x = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.X");
		int y = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Y");
		int z = plugin.getConfig().getInt(Main.games + "." + name + ".Sign.Z");
		Location l = new Location(Main.w, x,y,z);
		Sign s = (Sign) Main.w.getBlockAt(l).getState();
		s.setLine(2, on + " / " + max);
		s.update();
	}
	@EventHandler
	public void signz(PlayerInteractEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(e.getClickedBlock().getType().equals(Material.SIGN_POST) || e.getClickedBlock().getType().equals(Material.SIGN)) {
					Sign s = (Sign) e.getClickedBlock().getState();
					String name = s.getLine(0);
					signidentify(e.getPlayer(), s);
					if(joinGame(s)) {
					if(Waiting.ifMax(name)) {
						Waiting.generateTrees(name);
						Waiting.startGame(name);
						Waiting.setIngame(name);
					}
					else if(Waiting.ifMin(name)) {
						Waiting.countdown(name);
					}
					}
					
				}
			}
		}
		
	}
}
