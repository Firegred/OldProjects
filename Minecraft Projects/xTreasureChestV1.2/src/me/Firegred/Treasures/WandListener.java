package me.Firegred.Treasures;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WandListener implements Listener{
	
	public static Main plugin;
	
	public WandListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void settingpoint1s(BlockBreakEvent event) {
		Player p = event.getPlayer();
		try {
			if(!p.getItemInHand().equals(Material.AIR)) {
			if(p.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Treasure Wand") && p.hasPermission("TreasureChests.wand")) {
				event.setCancelled(true);
				Location l = event.getBlock().getLocation();
				Wand.setP1(p, l);
				p.sendMessage(ChatColor.BLUE + "First location set at X: " + ChatColor.GREEN + l.getBlockX() + ChatColor.BLUE + " Y: "
						+ ChatColor.GREEN + l.getBlockY() + ChatColor.BLUE + " Z: " + ChatColor.GREEN + l.getBlockZ());
			}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@EventHandler
	public void settingpoint1(BlockDamageEvent event) {
		Player p = event.getPlayer();
		try {
			if(!p.getItemInHand().equals(Material.AIR)) {
			if(p.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Treasure Wand") && p.hasPermission("TreasureChests.wand")) {
				event.setCancelled(true);
				Location l = event.getBlock().getLocation();
				Wand.setP1(p, l);
				p.sendMessage(ChatColor.BLUE + "First location set at X: " + ChatColor.GREEN + l.getBlockX() + ChatColor.BLUE + " Y: "
						+ ChatColor.GREEN + l.getBlockY() + ChatColor.BLUE + " Z: " + ChatColor.GREEN + l.getBlockZ());
			}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
		}
	}
	
	@EventHandler
	public void settingpoint2(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		
		try {
			if(!p.getItemInHand().equals(Material.AIR)) {
			if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(p.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Treasure Wand") && p.hasPermission("TreasureChests.wand")) {
					event.setCancelled(true);
					Location l = event.getClickedBlock().getLocation();
					Wand.setP2(p, l);
					p.sendMessage(ChatColor.BLUE + "Second location set at X: " + ChatColor.GREEN + l.getBlockX() + ChatColor.BLUE + " Y: "
							+ ChatColor.GREEN + l.getBlockY() + ChatColor.BLUE + " Z: " + ChatColor.GREEN + l.getBlockZ());
				}
			}
			}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
}
