package me.Firegred.Kidnapper;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Chat implements Listener {
	
	public Main plugin;
	
	public Chat(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void Chats(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player p = event.getPlayer();
		event.setCancelled(true);
		if(!p.isOp() && message.startsWith("/")) {
			p.sendMessage(ChatColor.WHITE + "Unknown Command!");
		}
		else if(!message.startsWith("/")){
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(p.hasMetadata("Admin")) {
					player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Admin" + ChatColor.WHITE + "]" + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.GRAY + message);	
				}
				
				else if(p.isOp()) {
					player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "OP" + ChatColor.WHITE + "]" + ChatColor.DARK_RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.GRAY + message);	
				}
				else {
				player.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.GRAY + ": " + ChatColor.GRAY + message);
				}
			}
			System.out.println(ChatColor.GRAY + p.getName() + ChatColor.GRAY + ": " + ChatColor.GRAY + message);
		}
		
	}
	@EventHandler
	public void Blocker(PlayerCommandPreprocessEvent event) {
		String message = event.getMessage();
		Player p = event.getPlayer();
		if(!p.isOp() && message.startsWith("/")) {
			if(!p.hasMetadata("Admin")) {
			event.setCancelled(true);
			p.sendMessage(ChatColor.WHITE + "Unkown Command!");
			}
		}
		if(message.equalsIgnoreCase("/stop")) {
			event.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Never Ever use this command!");
		}
		
	}

}
