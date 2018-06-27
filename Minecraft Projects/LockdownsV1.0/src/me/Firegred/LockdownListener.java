package me.Firegred;



import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;

public class LockdownListener implements Listener{

@SuppressWarnings("unused")
private static Lockdowns plugin;
	
	public LockdownListener(Lockdowns instance) {
		plugin = instance;
	}
	public static void selection(Player p, Block b) {
		Location l = b.getLocation();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		if(!plugin.pos1.containsKey(p)) {
			plugin.pos1.put(p, l);
			p.sendMessage(ChatColor.GREEN + "First position set at: " + ChatColor.BLUE + x + " " + y + " " + z);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".X1", x);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".Y1", y);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".Z1", z);
		}
		else if(!plugin.pos2.containsKey(p)) {
			plugin.pos2.put(p, l);
			p.sendMessage(ChatColor.GREEN + "Second position set at: " + ChatColor.BLUE + x + " " + y + " " + z);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".X2", x);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".Y2", y);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".Z2", z);
			plugin.getConfig().set(plugin.pname.get(p) + ".regions." + plugin.pregion.get(p) + ".world", b.getWorld().getName());
			plugin.saveConfig();
			plugin.reloadConfig();
			plugin.pos1.remove(p);
			plugin.pos2.remove(p);
			plugin.pname.remove(p);
			plugin.pregion.remove(p);
			p.sendMessage(ChatColor.GREEN + "The region has been created!");
			p.setItemInHand(null);
		}
	}
	
	@EventHandler
	public void selector(BlockDamageEvent e) {
		if(e.getPlayer().getItemInHand().equals(plugin.wand())) {
			e.setCancelled(true);
			selection(e.getPlayer(), e.getBlock());
		}
	}
	@EventHandler
	public void selector2(BlockBreakEvent e) {
		if(e.getPlayer().getItemInHand().equals(plugin.wand())) {
			e.setCancelled(true);
			selection(e.getPlayer(), e.getBlock());
		}
	}
}
