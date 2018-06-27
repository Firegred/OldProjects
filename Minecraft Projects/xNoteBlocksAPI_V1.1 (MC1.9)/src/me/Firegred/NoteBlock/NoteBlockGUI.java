package me.Firegred.NoteBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class NoteBlockGUI implements Listener{
	
	
	@EventHandler
	public void Open(PlayerInteractEvent event) {
		
		Player p = event.getPlayer();
		
		if(p.hasPermission("NoteBlock.open")) {
			if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(event.getClickedBlock().getType().equals(Material.SIGN_POST) || event.getClickedBlock().getType().equals(Material.SIGN) || event.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
					if(event.getClickedBlock().getState() instanceof Sign) {
					Sign sign = (Sign) event.getClickedBlock().getState();
					String name = ChatColor.GREEN + p.getName();
					String signame1 = " ";
					if(sign.getLine(0).contains(ChatColor.GREEN + "[P]")) {
					String signame = sign.getLine(0);
					signame1 = signame.substring(signame.lastIndexOf("]") + 1);
					//System.out.println("SignPots");
					}
					//System.out.println(signame1);
					if(sign.getLine(0).equals(ChatColor.GREEN + "NoteBlock") || name.contains(signame1) || sign.getLine(0).substring(3).equals(ChatColor.GREEN +"NoteBlock")) {
					event.setCancelled(true);
					Main.getSoundInventory().soundSelector(Main.instance, p, sign);
					Main.getSoundInventory().OpenPage1(p);
					
					//p.openInventory(gui);
					}
					}
				}
			}
		}
	}

}
