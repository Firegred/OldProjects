package me.Firegred.NoteBlockComposer;

import java.util.ArrayList;
import java.util.List;

import me.Firegred.NoteBlock.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class ChestFunctions implements Listener{

	
	  @EventHandler
	    public void onEntityExplode(EntityExplodeEvent event) {
	        if (event.isCancelled()) return;
	 
	        List<Block> blockListCopy = new ArrayList<Block>();
	        blockListCopy.addAll(event.blockList());
	        for (Block block : blockListCopy) {
	            if (block.getState() instanceof Chest) {
	            	Chest c = (Chest) block.getState();
	            	if(CompInv.isComposerChest(c.getInventory())) {
	            	   event.blockList().remove(block);
	            	}
	            }
	        }
	        }
	@EventHandler
	public void openingComposer(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		if(e.getClickedBlock().getState() instanceof Chest) {
			Chest c = (Chest) e.getClickedBlock().getState();
			if(CompInv.isComposerChest(c.getInventory())) {
				CompInv.updateInventory(c, c.getInventory());
				if(CompInv.isStopbutton(c.getInventory()) && PlayAPI.playchest.contains(c.getLocation())) {
					final Player p = e.getPlayer();
					final Chest chest = c;
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
						
						@Override
						public void run() {
							PlayAPI.addViewer(p, chest);
							
						}
					},10L);
			}	
			}
		}
	}
	
	@EventHandler
	public void destroyChest(BlockBreakEvent e) {
		if(e.getBlock().getState() instanceof Chest) {
			Chest c = (Chest) e.getBlock().getState();	
			if(CompInv.isComposerChest(c.getInventory())) {
				if(e.getPlayer().hasMetadata("Delete")) {
					e.setCancelled(true);
					Player p = e.getPlayer();
					if(CompInv.isOwner(p, c.getInventory()) || CompInv.canDelete(p)) {
						if(!CompInv.isStopbutton(c.getInventory())) {
						CompInv.deleteChest(c.getInventory(), c.getLocation(), c);
						p.sendMessage(ChatColor.GREEN + "Composer Chest deleted");
						p.removeMetadata("Delete", Main.instance);
						}
						else {
							p.sendMessage(ChatColor.RED + "You cannot delete Composer Chests while they're playing!");
							p.removeMetadata("Delete", Main.instance);	
						}
					}
					else {
						p.sendMessage(ChatColor.RED + "You can only delete Composer Chests belonging to you");
						p.removeMetadata("Delete", Main.instance);
					}
				}
				else {
				e.setCancelled(true);
				//if(!e.getPlayer().hasMetadata("Copy") || !e.getPlayer().hasMetadata("Create")) {
				//e.getPlayer().sendMessage(ChatColor.RED + "You can't destroy Composer Chests");
				//}
				}
			}
		}
		else if(e.getPlayer().hasMetadata("Delete")) {
			e.getPlayer().sendMessage(ChatColor.RED + "This is not a Composer Chest");
			e.getPlayer().removeMetadata("Delete", Main.instance);	
		}
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e) {
		if(PlayAPI.viewer.containsKey(e.getPlayer())) {
			PlayAPI.viewer.remove(e.getPlayer());
		}
	}
	@EventHandler
	public void composerchest(InventoryClickEvent e) {
		try {
			if(CompInv.isComposerChest(e.getInventory())) {
				Player p = (Player)e.getWhoClicked();
			    if(CompInv.Composer.containsKey(p)) {
			    	CompInv.Composer.remove(p);
			    }
				e.setCancelled(true);
				boolean edit = false;
				boolean play = false;
				

					DoubleChest c1 = (DoubleChest) e.getInventory().getHolder();
					Chest c = (Chest) c1.getLocation().getBlock().getState();
				if(CompInv.isOwner(p, e.getInventory()) || CompInv.canEdit(p) || CompInv.canPlay(p)) {
					CompInv.Composer.put(p, c);
				}
				if(CompInv.isOwner(p, e.getInventory()) || CompInv.canEdit(p)) {
					edit = true;
				}			
				if(CompInv.isOwner(p, e.getInventory()) || CompInv.canPlay(p)) {
					play = true;
				}			
				if(e.getRawSlot() < e.getView().getTopInventory().getSize()) {
				if(CompInv.isComposerSlot(e.getSlot()) && edit) {
						if(e.getClick() == ClickType.RIGHT) {
						if(CompInv.Copy.containsKey(p)) {
							e.getInventory().setItem(e.getSlot(), CompInv.Copy.get(p));
						}
						}
					}
					if(CompInv.isImportantSlot(e.getSlot())) {
						if(CompInv.isUpPage(e.getSlot()) && (edit||play)) {
							CompInv.nextPageUP((Player)e.getWhoClicked(), c);
						}
						else if(CompInv.isDownPage(e.getSlot()) && (edit||play)) {
							CompInv.nextPageDOWN((Player)e.getWhoClicked(), c);
						}
						else if(CompInv.isSpeedSelector(e.getSlot()) && edit) {
							SpeedSelection.OpenSpeedInventory(p, c);
						}
					}
					if(CompInv.isPlayerSlot(e.getSlot()) && play) {
						if(CompInv.isPlayButton(e.getInventory())) {
							PlayAPI.preparePlay(c);
							PlayAPI.play(CompInv.getBPM(e.getInventory()), c);
							PlayAPI.viewer.put(p, c);
						}
						else if(CompInv.isStopbutton(e.getInventory())) {
//						if(!PlayAPI.ChestPlayers.containsKey(c) ||  !PlayAPI.ChestPlayers.containsValue(c)) {
//							PlayAPI.glitchstop(c);
//						}
							PlayAPI.stop(c);
							CompInv.updateInventory(c, e.getInventory());
							if(PlayAPI.viewer.containsKey(p)) {
								PlayAPI.viewer.remove(p);
							
						}
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
		}
	}
}

