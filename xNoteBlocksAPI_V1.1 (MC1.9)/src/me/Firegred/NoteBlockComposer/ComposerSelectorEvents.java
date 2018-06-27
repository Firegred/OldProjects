package me.Firegred.NoteBlockComposer;

import me.Firegred.NoteBlock.Main;
import me.Firegred.NoteBlock.SoundID;

import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ComposerSelectorEvents implements Listener{


	@EventHandler
	public void composeSlotSelect(InventoryClickEvent e){
		try {
			if(CompInv.isComposerChest(e.getInventory())) {
				if(e.getRawSlot() < e.getView().getTopInventory().getSize()) {	
				if(CompInv.isOwner((Player) e.getWhoClicked(), e.getInventory()) || CompInv.canEdit((Player) e.getWhoClicked())) {
					if(CompInv.isComposerSlot(e.getSlot())) {
					Player p = (Player) e.getWhoClicked();
					p.setMetadata("Slot", new FixedMetadataValue(Main.instance, e.getSlot()));
					DoubleChest c1 = (DoubleChest) e.getInventory().getHolder();
					Chest c = (Chest) c1.getLocation().getBlock().getState();
					CompInv.Composer.put(p, c);
					if(e.getClick() == ClickType.LEFT) {
					if(e.getInventory().getItem(e.getSlot()) == null) {
					Main.getComposerSelector().OpenPage1(p);
					}
					else {
						Main.getComposerSelector().OpenOptions(p);
					}
					}
					if(e.getInventory().getItem(e.getSlot()) != null) {
					if(e.getClick() == ClickType.MIDDLE) {
						if(e.getInventory().getItem(e.getSlot()) != null) {
							CompInv.Copy.put(p, e.getInventory().getItem(e.getSlot()));
						}
					}
					if(e.getClick() == ClickType.SHIFT_LEFT) {
						Main.getComposerSelector().OpenPage1(p);
					}
					if(e.getClick() == ClickType.SHIFT_RIGHT) {
						Main.getComposerSelector().OpenPitches(p);
					}
					e.setCancelled(true);
				}
				}
				}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
		} catch (java.lang.IndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
		}
		
	}
	
	@EventHandler
	public void pickingPitches(InventoryClickEvent e) {
		try {
			if(e.getWhoClicked().hasMetadata("Slot")) {
				if(e.getInventory().getName().equals(ChatColor.GOLD + "Note Options")) {
					if(e.getCurrentItem() != null && e.getRawSlot() < e.getView().getTopInventory().getSize()) {
					e.setCancelled(true);
					
					Player p = (Player) e.getWhoClicked();
					int slot = p.getMetadata("Slot").get(0).asInt();
					if(e.getSlot() == 0) {
						Main.getComposerSelector().OpenPage1(p);
					}
					else if(e.getSlot() == 1) {
						Main.getComposerSelector().OpenPitches(p);
					}
					else if(e.getSlot() == 2) {
						CompInv.Composer.get(p).getInventory().setItem(slot, null);
						CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
						p.openInventory(CompInv.Composer.get(p).getInventory());
					}
					else if(e.getSlot() == 8) {
						CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
						p.openInventory(CompInv.Composer.get(p).getInventory());
					}
					}
				}
				
				if(e.getInventory().getName().equals(ChatColor.GOLD + "Select Pitch")) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					int slot = p.getMetadata("Slot").get(0).asInt();
					if(e.getCurrentItem() != null && e.getRawSlot() < e.getView().getTopInventory().getSize()) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Sharp: ")) {
							float number2 = SoundID.getSharp(e.getSlot());
							ComposerGUI.updatePitch(CompInv.Composer.get(p).getInventory(), slot, number2);
							CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
							p.openInventory(CompInv.Composer.get(p).getInventory());
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GREEN+ "Natural: ")) {
							float number2 = SoundID.getNormal(e.getSlot());
							ComposerGUI.updatePitch(CompInv.Composer.get(p).getInventory(), slot, number2);
							CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
							p.openInventory(CompInv.Composer.get(p).getInventory());
						}
						else if(e.getSlot() == 53) {
							CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
							p.openInventory(CompInv.Composer.get(p).getInventory());
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
		} catch(java.lang.NullPointerException e2) {
		} catch (java.lang.IndexOutOfBoundsException e1) {
		}
	}
	
	@EventHandler
	public void pickingPages(InventoryClickEvent e) {
        try {
			if(e.getWhoClicked().hasMetadata("Slot")) {
				Player p = (Player) e.getWhoClicked();
				int slot = p.getMetadata("Slot").get(0).asInt();
				int number = e.getSlot();
				if(e.getRawSlot() > e.getView().getTopInventory().getSize()) {	
				if(e.getInventory().getName().contains(ChatColor.GOLD + "Select a sound")) {
					e.setCancelled(true);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
				}
				if(e.getRawSlot() < e.getView().getTopInventory().getSize()) {	
					if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 8)")) {	
				        e.setCancelled(true);
						Main.getComposerSelector().OpenPage9(p);	
					}
					else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 7)")) {	
				        e.setCancelled(true);
						Main.getComposerSelector().OpenPage8(p);	
					}
					else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 6)")) {	
				        e.setCancelled(true);
						Main.getComposerSelector().OpenPage7(p);	
					}
					else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 5)")) {	
				        e.setCancelled(true);
						Main.getComposerSelector().OpenPage6(p);	
					}
					else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 4)")) {	
				        e.setCancelled(true);
						Main.getComposerSelector().OpenPage5(p);	
					}
					else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 3)")) {	
				        e.setCancelled(true);
						Main.getComposerSelector().OpenPage4(p);	
					}
					else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 2)")) {	
			        e.setCancelled(true);
					Main.getComposerSelector().OpenPage3(p);	
				}
				else if(number == 53 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 1)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage2(p);
					
				}
				else if(number == 52 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 2)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage1(p);
				}
				else if(number == 52 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 3)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage2(p);
				}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 4)") && number == 52) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage3(p);
				}
				else if(number == 52 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 5)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage4(p);
				}
				else if(number == 52 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 6)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage5(p);
				}
				else if(number == 52 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 7)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage6(p);
				}
				else if(number == 52 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 8)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage7(p);
				}
				else if(number == 45 && e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 9)")) {
					e.setCancelled(true);
					Main.getComposerSelector().OpenPage8(p);
				}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 1)")) {
					e.setCancelled(true);
					int number1 = e.getSlot();
					ItemStack it = e.getInventory().getItem(number1);
					if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
						ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
						Main.getComposerSelector().OpenPitches(p);
					}
					else {
						ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
						p.openInventory(CompInv.Composer.get(p).getInventory());
					}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 2)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 53;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 3)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 105;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 4)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 157;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 5)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 209;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 6)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 261;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 7)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 313;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 8)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 365;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
			}
				else if(e.getInventory().getName().equals(ChatColor.GOLD + "Select a sound (page 9)")) {
					e.setCancelled(true);
					int number1 = e.getSlot() + 417;
				ItemStack it = e.getInventory().getItem(e.getSlot());
				if(ComposerGUI.hasEmptySlot(CompInv.Composer.get(p).getInventory(), slot)) {
					ComposerGUI.defineItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1, 0.5f);
					Main.getComposerSelector().OpenPitches(p);
				}
				else {
					ComposerGUI.updateItem(SoundID.ID(number1), it, CompInv.Composer.get(p).getInventory(), slot, number1);
					CompInv.updateInventory(CompInv.Composer.get(p), CompInv.Composer.get(p).getInventory());
					p.openInventory(CompInv.Composer.get(p).getInventory());
				}
				}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
		} catch (java.lang.IndexOutOfBoundsException e1) {
			
			// TODO Auto-generated catch block
		}
        
	}
}
