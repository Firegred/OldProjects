package me.Firegred.Creations;

import me.Firegred.Main.Main;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignCreation implements Listener {

	public static Main plugin;
	
	public SignCreation(Main instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void sign(SignChangeEvent e) {
		if(e.getBlock().getWorld().equals(Main.w)) {
			if(e.getPlayer().isOp()) {
				
				if(e.getLine(0).equals("LeaveGame")) {
					Sign sign = (Sign) e.getBlock().getState();
					e.setLine(1, ChatColor.GREEN + "[Leave Game]");
					sign.update();
				}
				if(e.getLine(0).equals("TreeWrecker")) {
					for(int x = 0; x < 100; x++) {
						for(int y = 0; y < 100; y++) {
							if(e.getLine(2).equals("Max: " + x + " Min: " + y)) {
								String name = e.getLine(1).toString();
								//String II = e.getLine(1);
								//String III = e.getLine(2);
								//String IIII = e.getLine(3);
								e.setLine(0, e.getLine(1).toString());
								e.setLine(1, ChatColor.GREEN + "[Join]");
								e.setLine(2, "0 / " + String.valueOf(x));
								e.setLine(3, ChatColor.GREEN + "[Lobby]");
								Sign sign = (Sign) e.getBlock().getState();
								sign.update();
								
								Location l = sign.getLocation();
								
								plugin.getConfig().set(Main.games + "." + name + ".Sign.X",l.getBlockX());
								plugin.getConfig().set(Main.games + "." + name + ".Sign.Y",l.getBlockY());
								plugin.getConfig().set(Main.games + "." + name + ".Sign.Z",l.getBlockZ());
							    plugin.getConfig().set(Main.games + "." + name + ".Max", x);
							    plugin.getConfig().set(Main.games + "." + name + ".Min", y);
							    plugin.saveConfig();
							    plugin.reloadConfig();
							    e.getPlayer().sendMessage(ChatColor.GREEN + "Game " + ChatColor.RED + name + ChatColor.GREEN + " was created!");
							    break;
							}
						}
					}
				}
			}
		}
	}
}
