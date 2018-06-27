package me.Firegred.Kidnapper;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class CaptureArea implements Listener{
	
	public Main plugin;
	
	public CaptureArea(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void selects(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.hasMetadata("Chest")) {
			if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Wand for game")) {
			if(!p.hasMetadata("Chest1")) {
			
				Block block = event.getBlock();
				p.setMetadata("Chest1", new FixedMetadataValue(plugin, p.getMetadata("Chest").get(0).asString()));
				String path1 = Main.path + p.getMetadata("Chest").get(0).asString() + ".Chestarea.";
				plugin.getConfig().set(path1 + "X1", block.getLocation().getBlockX());
				plugin.getConfig().set(path1 + "Y1", block.getLocation().getBlockY());
				plugin.getConfig().set(path1 + "Z1", block.getLocation().getBlockZ());
				String x = plugin.getConfig().getString(path1 + "X1");
				String y = plugin.getConfig().getString(path1 + "Y1");
				String z = plugin.getConfig().getString(path1 + "Z1");
				plugin.saveConfig();
				plugin.reloadConfig();
				p.sendMessage(ChatColor.DARK_PURPLE + "First position at " + ChatColor.DARK_PURPLE + x + ChatColor.DARK_PURPLE + y + ChatColor.DARK_PURPLE + z);
				event.setCancelled(true);
			}
			
			else if(p.hasMetadata("Chest1") && !p.hasMetadata("Chest2")){
				Block block = event.getBlock();
				p.setMetadata("Chest2", new FixedMetadataValue(plugin, p.getMetadata("Chest").get(0).asString()));
				String path1 = Main.path + p.getMetadata("Chest").get(0).asString() + ".Chestarea.";
				plugin.getConfig().set(path1 + "X2", block.getLocation().getBlockX());
				plugin.getConfig().set(path1 + "Y2", plugin.getConfig().getInt(path1 + "Y1") + 1);
				plugin.getConfig().set(path1 + "Z2", block.getLocation().getBlockZ());
				plugin.saveConfig();
				plugin.reloadConfig();
				int x1 = plugin.getConfig().getInt(path1 + "X1");
				int x2 = plugin.getConfig().getInt(path1 + "X2");
				int y1 = plugin.getConfig().getInt(path1 + "Y1");
				int y2 = plugin.getConfig().getInt(path1 + "Y2");
				int z1 = plugin.getConfig().getInt(path1 + "Z1");
				int z2 = plugin.getConfig().getInt(path1 + "Z2");
				p.sendMessage(ChatColor.GREEN + "Capture area for " + ChatColor.RED + p.getMetadata("Chest").get(0).asString() + ChatColor.GREEN + " has been established!");
				p.removeMetadata("Chest", plugin);
				p.removeMetadata("Chest1", plugin);
				p.removeMetadata("Chest2", plugin);
				event.setCancelled(true);
				p.getInventory().clear();
				int i = 0;
				int ii = 0;
				int l = 0;
				int ll = 0;
				if(x1 > x2) {
					i = x2;
					ii = x1;
				}
				if(x2 > x1) {
					i = x1;
					ii = x2;
				}
				if(z1 > z2) {
					l = z2;
					ll = z1;
				}
				if(z2 > z1) {
					l = z1;
					ll = z2;
				}
				for(int o = i; o <= ii; o++) {
					for(int c = l; c <= ll; c++) {
						Location location = new Location(p.getWorld(), o, y2, c);
						p.getWorld().getBlockAt(location).setType(Material.WOOL);
					}
				}
				
				
			}
			}
		}
	}

}
