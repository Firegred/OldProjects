package me.Firegred.Lobby;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Firegred.Game.Ending;
import me.Firegred.Game.Equipment;
import me.Firegred.Main.Main;
import me.Firegred.mechanics.SignClick;

public class Login implements Listener{

	private static Main plugin;
	
	 @SuppressWarnings("static-access")
		public Login(Main instance) {
	       this.plugin = instance; 
	 }
	 private static void WelcomeMessage(Player p) {
		
		 p.sendMessage(Main.trees + ChatColor.GREEN + "Welcome " + ChatColor.WHITE + p.getDisplayName() + ChatColor.GREEN + " to Tree Wreckers");
	 }
	 @EventHandler
	 public void logoff(PlayerQuitEvent e) {
		 if(e.getPlayer().hasMetadata("Game")) {
			 SignClick.PlayerLeaveGame(e.getPlayer());
		 }
		 
		 for(String g : Main.data) {
			 if(e.getPlayer().hasMetadata(g)) {
				 e.getPlayer().removeMetadata(g, plugin);
			 }
		 }
	 }
	 
	 @EventHandler
	 public void log(PlayerLoginEvent e) {
		final Player p = e.getPlayer();
		 Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(p.getWorld().equals(Main.w)) {
					p.setMetadata("Lobby", new FixedMetadataValue(plugin, Main.w));
					Teleports.ServerLobbyTeleport(p);
					p.getInventory().clear();
					p.setHealth(20);
					p.setFoodLevel(20);
					p.setLevel(0);
					Ending.removeEffects(p);
					p.setGameMode(GameMode.SURVIVAL);
					Equipment.removeArmor(p);
					WelcomeMessage(p);
				}
				
			}
		},2L);
	 }
}
