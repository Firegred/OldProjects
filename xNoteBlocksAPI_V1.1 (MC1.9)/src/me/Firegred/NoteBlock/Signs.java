package me.Firegred.NoteBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class Signs implements Listener{ 
	
	@EventHandler
	public void clicking(InventoryClickEvent event) {
		try {
			if(event.getWhoClicked() instanceof Player) {
			Player p = (Player) event.getWhoClicked();
			if(event.getInventory().getName().equals(ChatColor.GOLD + "Advanced Pitch Options")) {
				event.setCancelled(true);
				int number = event.getSlot();
				if(number == 53 && event.getRawSlot() < event.getView().getTopInventory().getSize()) {
					p.closeInventory();
					Main.getSoundInventory().OpenPage4(p);
				}
				else if(event.getCurrentItem() != null && event.getRawSlot() < event.getView().getTopInventory().getSize()) {
				if(event.getCurrentItem().getType().equals(Material.WOOL)) {
					if(p.hasMetadata("SignX")) {
						//ItemStack item = event.getCurrentItem();
//					int x = p.getMetadata("SignX").get(0).asInt();
//					int y = p.getMetadata("SignY").get(0).asInt();
//					int z = p.getMetadata("SignZ").get(0).asInt();
//					Location location = new Location(p.getWorld(), x, y, z);
						Block block = Main.s.getSelectSign(Main.instance, p);
						event.setCancelled(true);
						if(block.getState() instanceof Sign) {
							if(event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Sharp: ")) {
							event.setCancelled(true);
							
							//String flat = event.getCurrentItem().getItemMeta().getDisplayName();
							//String flat1 = flat.substring(flat.lastIndexOf(" ") + 1);
							//String flat2 = ChatColor.BLACK + flat1;
							Sign sign = (Sign) block.getState();
							
							float number2 = SoundID.getSharp(event.getSlot());;
							String flat = String.valueOf(number2);
							sign.setLine(1, flat);
							sign.update();
							p.closeInventory();
							p.sendMessage(ChatColor.GREEN + "Pitch " + number2 + ChatColor.GREEN + " has been set.");
							}
							if(event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GREEN+ "Natural: ")) {
								event.setCancelled(true);
								
								//String flat = event.getCurrentItem().getItemMeta().getDisplayName();
							//	String flat1 = flat.substring(flat.lastIndexOf(" ") + 1);
								//String flat2 = ChatColor.BLACK + flat1;
								Sign sign = (Sign) block.getState();
								//String flat = String.valueOf(NotesNormal[event.getSlot()]);
								float number2 = SoundID.getNormal(event.getSlot());
								String flat = String.valueOf(number2);
								sign.setLine(1, flat);
								sign.update();
								p.closeInventory();
								p.sendMessage(ChatColor.GREEN + "Pitch " + flat + ChatColor.GREEN + " has been set.");
								}
						}
					}
				}
				}
			}
			
			if(event.getInventory().getName().contains(ChatColor.GOLD + "Pick a sound")) {
			    if(event.getRawSlot() > event.getView().getTopInventory().getSize()) {
			    	event.setCancelled(true);
			    }
			    else if(event.getClick().equals(ClickType.RIGHT) || event.getClick().equals(ClickType.MIDDLE)) {
					event.setCancelled(true);
				}
				else {
				event.setCancelled(true);
				int number = event.getSlot();
				String number2 = String.valueOf(number);
				
				if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 8)")) {
					Main.getSoundInventory().OpenPage9(p);
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 7)")) {
					Main.getSoundInventory().OpenPage8(p);
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 6)")) {
					Main.getSoundInventory().OpenPage7(p);
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 5)")) {
					Main.getSoundInventory().OpenPage6(p);
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 4)")) {
					Main.getSoundInventory().OpenPage5(p);	
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 3)")) {
//				gui4 = Bukkit.createInventory(null, 45, ChatColor.GOLD + "Pick a sound (page 4)");
//				int x = p.getMetadata("SignX").get(0).asInt();
//				int y = p.getMetadata("SignY").get(0).asInt();
//				int z = p.getMetadata("SignZ").get(0).asInt();
//				Location location = new Location(p.getWorld(), x, y, z);
					Block block = Main.s.getSelectSign(Main.instance, p);
					if(block.getState() instanceof Sign) {
						Sign sign = (Sign) block.getState();
						String name = ChatColor.GREEN + p.getName();
						String signame1 = " ";
						if(sign.getLine(0).contains(ChatColor.GREEN + "[P]")) {
						String signame = sign.getLine(0).toString();
						signame1 = signame.substring(signame.lastIndexOf("]") + 1);
						//System.out.println("SignPots");
						}
//					if(name.contains(signame1)) {
//						ItemStack protect = new ItemStack(Material.FIREBALL, 1);
//						ItemMeta protectMeta = protect.getItemMeta();
//						protectMeta.setDisplayName(ChatColor.GOLD + "Unprotect this sign/noteblock");
//						protect.setItemMeta(protectMeta);
//						gui4.setItem(36, protect);
//					}
//					else {
//						ItemStack protect = new ItemStack(Material.FIREBALL, 1);
//						ItemMeta protectMeta = protect.getItemMeta();
//						protectMeta.setDisplayName(ChatColor.GOLD + "Protect this sign/noteblock");
//						protect.setItemMeta(protectMeta);
//						gui4.setItem(36, protect);
//					}

					Main.getSoundInventory().OpenPage4(p);
					
				}
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 2)")) {	

					Main.getSoundInventory().OpenPage3(p);	
				}
				else if(number == 53 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 1)")) {

					Main.getSoundInventory().OpenPage2(p);
					
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 2)")) {

					Main.getSoundInventory().OpenPage1(p);
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 3)")) {
					Main.getSoundInventory().OpenPage2(p);
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 4)")) {
					Main.getSoundInventory().OpenPage3(p);
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 5)")) {
					Main.getSoundInventory().OpenPage4(p);
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 6)")) {
					Main.getSoundInventory().OpenPage5(p);
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 7)")) {
					Main.getSoundInventory().OpenPage6(p);
				}
				else if(number == 52 && event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 8)")) {
					Main.getSoundInventory().OpenPage7(p);
				}
				else if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)") && number == 49) {
					Main.getSoundInventory().OpenPage8(p);
				}
				else if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)") && number == 45) {
					p.closeInventory();
					p.sendMessage(ChatColor.GOLD + "NoteBlockPlus V" + ChatColor.RED + Main.version + ChatColor.GOLD + " - Coded by Firegred");
					
				}
				else if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)") && number >= 50) {
					event.setCancelled(true);
				}
				else if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)") && number == 46) {
				    Main.getSoundInventory().OpenPitches(p);
				}
				else if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)") && number == 48) {
					 Block block = Main.getSoundInventory().getSelectSign(Main.instance, p);
						if(block.getState() instanceof Sign) {
							Sign sign = (Sign) block.getState();
							if(sign.getLine(0).startsWith(ChatColor.RED + "#")) {
								String line = sign.getLine(0).substring(3);
								//System.out.println(line);
								sign.setLine(0, line);
								p.sendMessage(ChatColor.GREEN + "The nearby noteblocks are no longer tune locked");
							}
							else {
								String line = sign.getLine(0);
								sign.setLine(0, ChatColor.RED + "#" + line);
								p.sendMessage(ChatColor.GREEN + "The nearby noteblocks are now tune locked");
							}
							
							sign.update();
							p.closeInventory();
						}
				}
				else if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)") && number == 47) {
				    Block block = Main.getSoundInventory().getSelectSign(Main.instance, p);
					if(block.getState() instanceof Sign) {
						Sign sign = (Sign) block.getState();
						String line = sign.getLine(0);
						String name = ChatColor.GREEN + p.getName();
						String signame1 = " ";
						if(sign.getLine(0).contains(ChatColor.GREEN + "[P]")) {
						String signame = sign.getLine(0);
						signame1 = signame.substring(signame.lastIndexOf("]") + 1);
						//System.out.println("SignPots");
						}
						String tunelock = "";
						StringBuilder sb = new StringBuilder();
						if(line.startsWith(ChatColor.RED + "#")) {
							tunelock = ChatColor.RED + "#";
						}
						if(name.contains(signame1)) {
							sign.setLine(0, tunelock + ChatColor.GREEN + "NoteBlock");
							p.sendMessage(ChatColor.GREEN + "You made this noteblock/sign public!");
							sign.update();
							p.closeInventory();
						}
						else {
							sign.setLine(0, tunelock + ChatColor.GREEN + "[P]" + ChatColor.GREEN + p.getName());
							p.sendMessage(ChatColor.GREEN + "You made this noteblock/sign private to yourself!");
							sign.update();
							p.closeInventory();
						}
					}
				}
				else if(p.hasMetadata("SignX")) {
					Block block = Main.getSoundInventory().getSelectSign(Main.instance, p);
					if(block.getState() instanceof Sign) {
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 1)")) {
					Sign sign = (Sign) block.getState();
					sign.setLine(2, number2);
					sign.setLine(3, SoundID.ID(number).toString());
					sign.update();
					p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number).toString() + ChatColor.GOLD + " has been selected");
					p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 2)")) {
					int number1 = event.getSlot() + 53;
					String number22 = String.valueOf(number1);
					Sign sign = (Sign) block.getState();
					sign.setLine(2, number22);
					sign.setLine(3, SoundID.ID(number1).toString());
					sign.update();
					p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
					p.closeInventory();	
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 3)")) {
						int number1 = event.getSlot() + 105;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 4)")) {
						int number1 = event.getSlot() + 157;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 5)")) {
						int number1 = event.getSlot() + 209;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 6)")) {
						int number1 = event.getSlot() + 261;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 7)")) {
						int number1 = event.getSlot() + 313;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 8)")) {
						int number1 = event.getSlot() + 365;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					if(event.getInventory().getName().equals(ChatColor.GOLD + "Pick a sound (page 9)")) {
						int number1 = event.getSlot() + 417;
						String number22 = String.valueOf(number1);
						Sign sign = (Sign) block.getState();
						sign.setLine(2, number22);
						sign.setLine(3, SoundID.ID(number1).toString());
						sign.update();
						p.sendMessage(ChatColor.GOLD + "Sound " + ChatColor.BLUE + SoundID.ID(number1).toString() + ChatColor.GOLD + " has been selected");
						p.closeInventory();
					}
					}
					
					}
					
				}
			}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e ) {
			// TODO Auto-generated catch block
		}
	}
}


