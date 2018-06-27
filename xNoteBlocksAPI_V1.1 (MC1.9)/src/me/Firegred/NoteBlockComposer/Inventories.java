package me.Firegred.NoteBlockComposer;

import me.Firegred.NoteBlock.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Inventories implements Listener {

	@EventHandler
	public void speedInventory(InventoryClickEvent e) {
		try {
			if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a speed")) {
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				if(e.getRawSlot() < e.getView().getTopInventory().getSize()) {	
				if(e.getSlot() != SpeedSelection.exitslot && !SpeedSelection.isUselessSlot(e.getSlot())) {
					SpeedSelection.selectSpeed(p, e.getInventory(), e.getSlot());
				}
				else if(e.getSlot() == SpeedSelection.exitslot){
					SpeedSelection.Exit(p);
				}
			}
				
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
		}
 	}
	@EventHandler
	public void inventoryClose(InventoryCloseEvent e) {
//		if(CompInv.isComposerChest(e.getInventory())) {
//	       CompInv.Composer.remove((Player) e.getPlayer());
//		}
		removeInventoryData((Player) e.getPlayer());
	}
	public static void removeInventoryData(Player p) {
		if(SpeedSelection.SpeedSelector.containsKey(p)) {
			SpeedSelection.SpeedSelector.remove(p);
		}
//		for(int i = 0; i < Main.InventoryMeta.length; i++) {
//			if(p.hasMetadata(Main.InventoryMeta[i])) {
//				p.removeMetadata(Main.InventoryMeta[i], Main.instance);
//				System.out.println("Removed " + Main.InventoryMeta[i]);
//			}
//		}
	}
}
