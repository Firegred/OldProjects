package me.Firegred.Kidnapper.Signs;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.LobbyFunctions.MainLobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SignBreak implements Listener{
	
	public Main plugin;
	
	public SignBreak(Main instance) {
		plugin = instance;
	}
	
	public void mainLobbyTeleport(Player player) {
		String lob = Main.lobpath;
		int x = plugin.getConfig().getInt(lob + "X");
		int y = plugin.getConfig().getInt(lob + "Y");
		int z = plugin.getConfig().getInt(lob + "Z");
		Location location = new Location(player.getWorld(), x, y, z);
		player.teleport(location);
		
	
	}
	
	@EventHandler
	public void SignBreaking(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.hasMetadata("Sign")) {
			if(event.getBlock().getState() instanceof Sign) {
				try {
					Sign sign = (Sign) event.getBlock().getState();
					String sir = sign.getLine(0).toString();
					String paths = Main.path + sign.getLine(0).toString() + ".location";
					int x = plugin.getConfig().getInt(paths + ".XLoc");
					int y = plugin.getConfig().getInt(paths + ".YLoc");
					int z = plugin.getConfig().getInt(paths + ".ZLoc");
					Location location = new Location(p.getWorld(), x, y, z);
					//plugin.getConfig().set(Main.path + sir, null);
					plugin.getConfig().getConfigurationSection(Main.path).set(sir, null);
					p.sendMessage(ChatColor.YELLOW + "You removed a game " + ChatColor.RED + sir);
					p.getWorld().getBlockAt(location).setType(Material.AIR);
					p.removeMetadata("Sign", plugin);
					plugin.saveConfig();
					plugin.reloadConfig();
					for(Player c : Bukkit.getOnlinePlayers()) {
						if(c.hasMetadata("Games")) {
							if(c.getMetadata("Games").get(0).asString().equals(sir)) {
								c.removeMetadata("Games", plugin);
								mainLobbyTeleport(c);
								c.getInventory().clear();
								try {
									c.getInventory().getHelmet().setType(Material.AIR);
									c.getInventory().getLeggings().setType(Material.AIR);
									c.getInventory().getChestplate().setType(Material.AIR);
									c.getInventory().getBoots().setType(Material.AIR);
								} catch (java.lang.NullPointerException e) {
							
								}
							//	c.sendMessage(ChatColor.RED + "Game has been terminated!");
								c.kickPlayer(ChatColor.RED + "Game has been terminated!");
								
							}
						}
					}
				} catch (java.lang.IllegalArgumentException e) {
					p.removeMetadata("Sign", plugin);
					p.sendMessage(ChatColor.RED + "That is not a valid sign!");
				}
					
				
			}
			else {
				p.removeMetadata("Sign", plugin);
				p.sendMessage(ChatColor.RED + "That is not a sign!");
			}
		}
		
		else if(event.getBlock().getType().equals(Material.SIGN_POST) && !p.hasMetadata("Sign")|| event.getBlock().getType().equals(Material.SIGN) && !p.hasMetadata("Sign")) {
       
		
        Sign sign = (Sign) event.getBlock().getState();
        String paths = Main.path + sign.getLine(0).toString() + ".location";
			int x = plugin.getConfig().getInt(paths + ".XLoc");
			int y = plugin.getConfig().getInt(paths + ".YLoc");
			int z = plugin.getConfig().getInt(paths + ".ZLoc");
			Location location = new Location(p.getWorld(), x, y, z);
				for(int i = 0; i < 100; i++) {
					for(int c = 0; c < 100; c++) {
						String I = String.valueOf(i);
	                    String C = String.valueOf(c);
						if(sign.getLine(2).equalsIgnoreCase(ChatColor.GREEN + I + ChatColor.GREEN + "/" + ChatColor.GREEN + C)) {
							int o = i-1;
							//System.out.println("Works!");
							event.setCancelled(true);
							//p.sendMessage(ChatColor.RED + "You must use a special command to get rid of the sign!");
					
	}

}
				}
			}
		}
	
}
