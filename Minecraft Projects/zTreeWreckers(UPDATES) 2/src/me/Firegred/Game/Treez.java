package me.Firegred.Game;



import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Firegred.Main.Main;

public class Treez implements Listener {
	private static Main plugin;
    @SuppressWarnings("static-access")
	public Treez(Main instance) {
          this.plugin = instance; //now you can access plugin
  }
 
    
    private static void destroyTree(Block b) {
    	Location l = b.getLocation();
    	World w = b.getWorld();
    	int x = l.getBlockX();
    	int z = l.getBlockZ();
    	for(int y = 0; y < 255; y++) {
    		Location k = new Location(w,x,y,z);
            if(w.getBlockAt(k).getType().equals(Material.LOG) || w.getBlockAt(k).getType().equals(Material.LOG_2)) {
            	w.getBlockAt(k).setType(Material.AIR);
            }
    	}
    }
    @EventHandler
    public void TreeDestroy(BlockBreakEvent e) {
    	if(e.getPlayer().getWorld().equals(Main.w)) {
    		e.getBlock().getDrops().clear();
    		if(e.getPlayer().hasMetadata("Game") && e.getPlayer().hasMetadata("Tree")) {
    			Player p = e.getPlayer();
    			if(e.getBlock().getType().equals(Material.LOG) || e.getBlock().getType().equals(Material.LOG_2)) {
    			Main.w.getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
    			int number = e.getPlayer().getMetadata("Tree").get(0).asInt();
    			if(number == 1) {
    		    	p.setMetadata("Tree", new FixedMetadataValue(plugin, 0));
    		    	destroyTree(e.getBlock());
    		    	if(Drop.ChestChance()) {
    		    	 Drop.placeChest(p, e.getBlock());
    		    	}
    		    	
    		    	if(Enchanting.ArmorChanceProtection() && Equipment.hasArmor(p)) {
    		    		Enchanting.EnchantArmor(Enchanting.ChooseRandomArmor(p.getInventory().getArmorContents()));
    		    	}
    		    	if(Enchanting.AxeChanceEfficiency()) {
    		    		Enchanting.enchantAxeEfficiency(p.getItemInHand());
    		    	}
    		    }
    		    else {
    		    	p.setMetadata("Tree", new FixedMetadataValue(plugin, 1));
    		    }
    			}
    			else if(Main.canDestroy.contains(e.getBlock().getType())){
    				e.setCancelled(true);
    				if(Main.canDrop.contains(e.getBlock().getType()) && !p.getItemInHand().getType().equals(Material.SAPLING)) {
    				e.getBlock().breakNaturally();
    				}
    				else {
    				e.getBlock().setType(Material.AIR);
    				}
    				
    			}
    			else if(e.getBlock().getState() instanceof Chest) {
    				e.setCancelled(true);
    			}
    			else {
    				e.setCancelled(true);
    			}
    			
    			
    		}
    	}
    }
    
}
