package me.Firegred.mechanics;


import me.Firegred.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class Chat implements Listener{
	
	public static Main plugin;
	
	public Chat(Main instance) {
		plugin = instance;
	}
	@EventHandler
	public void Blocker(PlayerCommandPreprocessEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
		Player p = e.getPlayer();
		if(e.getMessage().startsWith("/")) {
			if(!p.isOp()) {
				e.setCancelled(true);
				p.sendMessage(Main.trees + ChatColor.WHITE + "Unknown command");
			}
		}
		}
	}
	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
			Player p = e.getPlayer();
			e.setCancelled(true);
			for(Player c : Bukkit.getOnlinePlayers()) {
				if(p.hasMetadata("Lobby")) {
					if(c.hasMetadata("Lobby")) {
					if(p.isOp()) {
						c.sendMessage(ChatColor.RED + p.getName() + ChatColor.RED + ": " + ChatColor.GRAY + e.getMessage());
					}
					else {
						c.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.GRAY + ": " + ChatColor.GRAY + e.getMessage());
					}
					}
				}
				else if(p.hasMetadata("Game")) {
					if(c.hasMetadata("Game")) {
						if(p.getMetadata("Game").get(0).asString().equals(c.getMetadata("Game").get(0).asString())) {
						c.sendMessage(ChatColor.RED + "[Game] " + ChatColor.GRAY + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
						}
					}
					
				}
			}
		}
	}
	

}
