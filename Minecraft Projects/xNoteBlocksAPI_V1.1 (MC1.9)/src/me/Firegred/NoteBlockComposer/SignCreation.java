package me.Firegred.NoteBlockComposer;



import me.Firegred.NoteBlock.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SignCreation implements Listener{
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		for(int i = 0; i < Main.MetaData.length; i++) {
			if(e.getPlayer().hasMetadata(Main.MetaData[i])) {
				e.getPlayer().removeMetadata(Main.MetaData[i], Main.instance);
			}
		}
	}
	
	@EventHandler
	public void copyChest(BlockDamageEvent e) {
		Block b = e.getBlock();
		if(e.getPlayer().hasMetadata("Copy")) {
			Player p = e.getPlayer();
			if(b.getState() instanceof Chest) {
				Chest c = (Chest) b.getState();
				if(CompInv.isComposerChest(c.getInventory())) {
					if(CompInv.isOwner(p, c.getInventory()) || CompInv.canEdit(p)) {
						CompInv.copyInventory(p, c.getInventory());
						p.sendMessage(ChatColor.GREEN + "Composer Chest copied!");
						p.removeMetadata("Copy", Main.instance);
								
					}
					else {
						p.removeMetadata("Copy", Main.instance);
						p.sendMessage(ChatColor.RED + "You must own the Composer Chest to copy it");
					}
				}
				else {
					p.removeMetadata("Copy", Main.instance);
					p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
				}
			}
			else {
				p.removeMetadata("Copy", Main.instance);
				p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
			}
		}
		else if(CompInv.hasChestCopier(e.getPlayer())) {
			Player p = e.getPlayer();
			if(b.getState() instanceof Chest) {
				Chest c = (Chest) b.getState();
				if(CompInv.isComposerChest(c.getInventory())) {
					if(CompInv.isOwner(p, c.getInventory()) || CompInv.canEdit(p)) {
						CompInv.pasteInventory(p, c.getInventory());
						CompInv.removeInventoryCopy(p);
						p.sendMessage(ChatColor.GREEN + "Inventory Pasted!");
					}
					else {
						CompInv.removeInventoryCopy(p);
						p.sendMessage(ChatColor.RED + "You must own the Composer Chest to paste");
					}
				}
				else {
					CompInv.removeInventoryCopy(p);
					p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
				}
			}
			else {
				CompInv.removeInventoryCopy(p);
				p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
			}
		}
	}
	@EventHandler
	public void copyChestDamage(BlockBreakEvent e) {
		Block b = e.getBlock();
		if(e.getPlayer().hasMetadata("Copy")) {
			Player p = e.getPlayer();
			if(b.getState() instanceof Chest) {
				Chest c = (Chest) b.getState();
				if(CompInv.isComposerChest(c.getInventory())) {
					if(CompInv.isOwner(p, c.getInventory()) || CompInv.canEdit(p)) {
						p.removeMetadata("Copy", Main.instance);
						CompInv.copyInventory(p, c.getInventory());
						p.sendMessage(ChatColor.GREEN + "Composer Chest copied!");
					}
					else {
						p.removeMetadata("Copy", Main.instance);
						p.sendMessage(ChatColor.RED + "You must own the Composer Chest to copy it");
					}
				}
				else {
					p.removeMetadata("Copy", Main.instance);
					p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
				}
			}
			else {
				p.removeMetadata("Copy", Main.instance);
				p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
			}
		}
		else if(CompInv.hasChestCopier(e.getPlayer())) {
			Player p = e.getPlayer();
			if(b.getState() instanceof Chest) {
				Chest c = (Chest) b.getState();
				if(CompInv.isComposerChest(c.getInventory())) {
					if(CompInv.isOwner(p, c.getInventory()) || CompInv.canEdit(p)) {
						CompInv.pasteInventory(p, c.getInventory());
						CompInv.removeInventoryCopy(p);
						p.sendMessage(ChatColor.GREEN + "Inventory Pasted!");
					}
					else {
						CompInv.removeInventoryCopy(p);
						p.sendMessage(ChatColor.RED + "You must own the Composer Chest to paste");
					}
				}
				else {
					CompInv.removeInventoryCopy(p);
					p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
				}
			}
			else {
				CompInv.removeInventoryCopy(p);
				p.sendMessage(ChatColor.RED + "This is not a Composer Chest");
			}
		}
	}
	@EventHandler
	public void createChest(BlockDamageEvent e) {
		Block b = e.getBlock();
		if(e.getPlayer().hasMetadata("Create")) {
		if(b.getState() instanceof Chest) {
			e.setCancelled(true);
			Chest c = (Chest) b.getState();
			if(CompInv.isInvEmpty(c.getInventory())) {
				if(c.getInventory().getSize() > 27) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "Noteblock composer " + ChatColor.YELLOW + e.getPlayer().getMetadata("Create").get(0).asString() + ChatColor.GREEN + " has been created!");
				CompInv.createStandardInventory(c.getInventory(), e.getPlayer().getMetadata("Create").get(0).asString(), e.getPlayer().getName(), 1, 120);
				e.getPlayer().removeMetadata("Create", Main.instance);
				}
				else {
				e.getPlayer().removeMetadata("Create", Main.instance);
				e.getPlayer().sendMessage(ChatColor.RED + "Chest needs to be bigger!");
				}
			}
			else {
				e.getPlayer().removeMetadata("Create", Main.instance);
				e.getPlayer().sendMessage(ChatColor.RED + "Chest must be empty!");
			}
		}
		else {
			e.getPlayer().removeMetadata("Create", Main.instance);
			e.getPlayer().sendMessage(ChatColor.RED + "Block must be a Chest!");	
		}
	}
	}
	@EventHandler
	public void createChestdamage(BlockBreakEvent e) {
		Block b = e.getBlock();
		final Player p = e.getPlayer();
		if(e.getPlayer().hasMetadata("Create")) {
		if(b.getState() instanceof Chest) {
			e.setCancelled(true);
			Chest c = (Chest) b.getState();
			if(CompInv.isInvEmpty(c.getInventory())) {
				if(c.getInventory().getSize() > 27) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "Noteblock composer " + ChatColor.YELLOW + e.getPlayer().getMetadata("Create").get(0).asString() + ChatColor.GREEN + " has been created!");
				CompInv.createStandardInventory(c.getInventory(), e.getPlayer().getMetadata("Create").get(0).asString(), e.getPlayer().getName(),1,120);
				p.removeMetadata("Create", Main.instance);
				}
				else {
				e.getPlayer().removeMetadata("Create", Main.instance);
				e.getPlayer().sendMessage(ChatColor.RED + "Chest needs to be bigger!");
				}
			}
			else {
				e.getPlayer().removeMetadata("Create", Main.instance);
				e.getPlayer().sendMessage(ChatColor.RED + "Chest must be empty!");
			}
		}
		else {
			e.getPlayer().removeMetadata("Create", Main.instance);
			e.getPlayer().sendMessage(ChatColor.RED + "Block must be a Chest!");	
		}
	}
	}
	

}
