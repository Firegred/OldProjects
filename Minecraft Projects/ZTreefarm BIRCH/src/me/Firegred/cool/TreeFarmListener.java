package me.Firegred.cool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

public class TreeFarmListener implements Listener{

	public TreeFarm plugin;
	public final static HashMap<String, Location> t = new HashMap<String, Location>();
	//public static final HashMap<String, Double> = new HashMap<plugin.nice>
	public TreeFarmListener(TreeFarm instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void BlockBrea(BlockBreakEvent event) {
		Block block = event.getBlock();
		double x1 = block.getLocation().getX();
		double y2 = block.getLocation().getY();
		double z3 = block.getLocation().getZ();
		String x = new Double(x1).toString();
		String y = new Double(y2).toString();
		String z = new Double(z3).toString();
		//if(t.containsValue(block.getLocation())) {
			//event.setCancelled(true);
			//event.getPlayer().sendMessage("This is a TreeFarm!");
		for(int i = 0; i < TreeFarm.x5.size(); i++) {
			for(int j = 0; j < TreeFarm.y5.size(); j++) {
				for(int k = 0; k < TreeFarm.z5.size(); k++) {
					if(i == j && i == k) {
						if(TreeFarm.x5.get(i).equals(block.getX()) && TreeFarm.y5.get(j).equals(block.getY()) && TreeFarm.z5.get(k).equals(block.getZ())) {
							if(block.getType().equals(Material.LOG)) {
								event.setCancelled(true);
								block.setTypeIdAndData(Material.SAPLING.getId(), TreeSpecies.BIRCH.getData(), true);
								block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.LOG, 1));
								event.getPlayer().sendMessage("[TreeFarm] TreeFarm sapling newly planted!");
							}
							if(!block.getType().equals(Material.SAPLING) && !block.getType().equals(Material.LOG)) {
								event.setCancelled(true);
								block.setTypeIdAndData(Material.SAPLING.getId(), TreeSpecies.BIRCH.getData(), true);
								
							}
							else {
							event.setCancelled(true);
							event.getPlayer().sendMessage("[TreeFarm] This is a TreeFarm!");
							}
						}
						
					}
				}
			}
		}
		//}
		//if(TreeFarm.x5.contains(block.getX()) && TreeFarm.y5.contains(block.getY()) && TreeFarm.z5.contains(block.getZ())) {
		//	event.setCancelled(true);
		//	event.getPlayer().sendMessage("This is a TreeFarm!");
		//}
	}
	@EventHandler
	public void BlockClick(BlockDamageEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		int x1 = event.getBlock().getX();
		int y2 = event.getBlock().getY();
		int z3 = event.getBlock().getZ();
		
		String x = new Integer(x1).toString();
		String y = new Integer(y2).toString();
		String z = new Integer(z3).toString();
		//String[] location = {x, y, z};
		
		
		
		if(plugin.enab.contains(player)) {
			player.sendMessage("[TreeFarm] Tree Farm " + plugin.nice.get(player) + " has been set!");
			//plugin.getConfig().addDefault(plugin.nice.get(player) + "X", x);
			//plugin.getConfig().addDefault(plugin.nice.get(player) + "Y", y);
			//plugin.getConfig().addDefault(plugin.nice.get(player) + "Z", z);
			//plugin.getConfig().addDefault(plugin.nice.get(player), block.getLocation());
			//plugin.getConfig().addDefault(plugin.path, plugin.nice.get(player) + "x");
			//plugin.getConfig().addDefault(plugin.path, plugin.nice.get(player) + "y");
			//plugin.getConfig().addDefault(plugin.path, plugin.nice.get(player) + "z");
			plugin.getConfig().addDefault("x" + plugin.nice.get(player), x1);
			plugin.getConfig().addDefault("y" + plugin.nice.get(player), y2);
			plugin.getConfig().addDefault("z" + plugin.nice.get(player), z3);
			TreeFarm.x5.add(x1);
			TreeFarm.y5.add(y2);
			TreeFarm.z5.add(z3);
			//t.put(plugin.nice.get(player), block.getLocation());
		
			plugin.getConfig().options().copyDefaults(true);
			//plugin.getConfig().addDefault(plugin.path, block.getLocation());
			plugin.enab.remove(player);
			plugin.nice.remove(player);
			plugin.saveConfig();
			plugin.reloadConfig();
			
			block.setTypeIdAndData(Material.SAPLING.getId(), TreeSpecies.BIRCH.getData(), true);
		}
		//if(t.containsValue(block.getLocation())) {
		//	event.setCancelled(true);
		//	player.sendMessage("This is a TreeFarm!");
		//}
		//if()
		//if(plugin.getConfig().getList("TreeFarm.name").contains(block) && block.getType().equals(Material.SAPLING)) {
		//	event.setCancelled(true);
		//	player.sendMessage("[FreeFarm]You are not allowed to destroy the tree farm!");
		//}
	    
	}
}
