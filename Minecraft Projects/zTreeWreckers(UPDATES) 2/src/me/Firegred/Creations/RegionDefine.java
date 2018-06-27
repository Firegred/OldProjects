package me.Firegred.Creations;

import me.Firegred.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RegionDefine implements Listener {

	public static Main plugin;
	
	public RegionDefine(Main instance) {
		this.plugin = instance;
	}
	public static ItemStack wand() {
		ItemStack w = new ItemStack(Material.IRON_AXE);
		ItemMeta wm = w.getItemMeta();
		wm.setDisplayName(ChatColor.RED + "Creator Wand");
		w.setItemMeta(wm);
		return w;
	}
	public static ItemStack groundwand() {
		ItemStack w = new ItemStack(Material.IRON_AXE);
		ItemMeta wm = w.getItemMeta();
		wm.setDisplayName(ChatColor.RED + "Ground Wand");
		w.setItemMeta(wm);
		return w;
	}
	public static void groundselection(Player p, Block b) {
		Location l = b.getLocation();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		if(!plugin.pos1.containsKey(p)) {
			plugin.pos1.put(p, l);
			p.sendMessage(ChatColor.GREEN + "First position set at: " + ChatColor.BLUE + x + " " + y + " " + z);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Ground.X1", x);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Ground.Y1", y);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Ground.Z1", z);
		}
		else if(!plugin.pos2.containsKey(p)) {
			plugin.pos2.put(p, l);
			p.sendMessage(ChatColor.GREEN + "Second position set at: " + ChatColor.BLUE + x + " " + y + " " + z);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Ground.X2", x);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Ground.Y2", y);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Ground.Z2", z);
			plugin.saveConfig();
			plugin.reloadConfig();
			plugin.pos1.remove(p);
			plugin.pos2.remove(p);
			plugin.regioner.remove(p);
			p.sendMessage(ChatColor.GREEN + "The ground has been created!");
			p.setItemInHand(null);
		}
	}
	public static void selection(Player p, Block b) {
		Location l = b.getLocation();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		if(!plugin.pos1.containsKey(p)) {
			plugin.pos1.put(p, l);
			p.sendMessage(ChatColor.GREEN + "First position set at: " + ChatColor.BLUE + x + " " + y + " " + z);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Region.X1", x);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Region.Y1", y);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Region.Z1", z);
		}
		else if(!plugin.pos2.containsKey(p)) {
			plugin.pos2.put(p, l);
			p.sendMessage(ChatColor.GREEN + "Second position set at: " + ChatColor.BLUE + x + " " + y + " " + z);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Region.X2", x);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Region.Y2", y);
			plugin.getConfig().set(Main.games + "." + plugin.regioner.get(p) + ".Region.Z2", z);
			plugin.saveConfig();
			plugin.reloadConfig();
			plugin.pos1.remove(p);
			plugin.pos2.remove(p);
			plugin.regioner.remove(p);
			p.sendMessage(ChatColor.GREEN + "The region has been created!");
			p.setItemInHand(null);
		}
	}
	
	@EventHandler
	public void selector(BlockDamageEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
		if(e.getPlayer().getItemInHand().equals(wand())) {
			e.setCancelled(true);
			selection(e.getPlayer(), e.getBlock());
		}
		else if(e.getPlayer().getItemInHand().equals(groundwand())) {
			e.setCancelled(true);
			groundselection(e.getPlayer(), e.getBlock());
		}
		}
	}
	@EventHandler
	public void selector2(BlockBreakEvent e) {
		if(e.getPlayer().getWorld().equals(Main.w)) {
		if(e.getPlayer().getItemInHand().equals(wand())) {
			e.setCancelled(true);
			selection(e.getPlayer(), e.getBlock());
		}
		else if(e.getPlayer().getItemInHand().equals(groundwand())) {
			e.setCancelled(true);
			groundselection(e.getPlayer(), e.getBlock());
		}
		}
	}
}
