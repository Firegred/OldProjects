package me.Firegred.NoteBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.NoteBlock;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class fastplace implements Listener{
	
	public Main plugin;
	
	public fastplace(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void signz(SignChangeEvent e) {
		if(Main.place.containsKey(e.getPlayer()) || Main.specialplacer.containsKey(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void places(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(Main.placer.contains(p)) {
			if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				if(event.getClickedBlock().getState() instanceof Sign) {
					Sign sign = (Sign) event.getClickedBlock().getState();
					String signame1 = " ";
					if(sign.getLine(0).contains(ChatColor.GREEN + "[P]")) {
						String signame = sign.getLine(0);
						signame1 = signame.substring(signame.lastIndexOf("]") + 1);
						//System.out.println("SignPots");
					}
					String name = ChatColor.GREEN + p.getName();
					if(sign.getLine(0).contains(ChatColor.GREEN + "NoteBlock") || name.contains(signame1)) {
						event.setCancelled(true);
						p.sendMessage(ChatColor.GREEN + "NoteBlock sign selected!");
						Main.place.put(p, event.getClickedBlock());
						Main.placer.remove(p);
					}
					else {
						event.setCancelled(true);
						p.sendMessage(ChatColor.RED + "That is not a valid sign!");
						Main.placer.remove(p);
					}
				}
				else {
					event.setCancelled(true);
					p.sendMessage(ChatColor.RED + "That is not a valid block");
					Main.placer.remove(p);
				}
			}
		}
	}
	@EventHandler
	public void placing(BlockPlaceEvent event) {
		final Block b = event.getBlockPlaced();
		final Player p = event.getPlayer();
		final Location l = b.getLocation();
		if(Main.place.containsKey(p) || Main.specialplacer.containsKey(p)) {
			if(p.getInventory().getItemInHand().getType().equals(Material.SIGN)) {
//				if(!p.getGameMode().equals(GameMode.CREATIVE)) {
//				if(p.getInventory().getItemInHand().getAmount() >= 2) {
//					ItemStack item = p.getInventory().getItemInHand();
//					item.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
//					p.getInventory().setItemInHand(item);
//					
//				}
//				if(p.getInventory().getItemInHand().getAmount() == 1) {
//					p.getInventory().remove(p.getInventory().getItemInHand());
//				}
//				}
				//p.getWorld().getBlockAt(b.getLocation()).setType(Material.SIGN);
			   // if(p.getWorld().getBlockAt(b.getLocation()).getType().equals(Material.SIGN)) {
			//	Bukkit.getWorld(l.getWorld().getName()).getBlockAt(l).setType(Material.SIGN);
				if(b.getState() instanceof Sign) {			
				            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								
								@Override
								public void run() {
									  Block b = Bukkit.getWorld(l.getWorld().getName()).getBlockAt(l);
									  Sign sign = (Sign) b.getState();
									  if(Main.place.containsKey(p)) {
									  Sign bb = (Sign) Main.place.get(p).getState();
									  sign.setLine(0, bb.getLine(0).toString());
									  sign.setLine(1, bb.getLine(1).toString());
									  sign.setLine(2, bb.getLine(2).toString());
									  sign.setLine(3, bb.getLine(3).toString());
									  }
									  if(Main.specialplacer.containsKey(p)) {
										  sign.setLine(0, Main.specialplacer.get(p)[0]);
										  sign.setLine(2, Main.specialplacer.get(p)[2]);
										  sign.setLine(3, Main.specialplacer.get(p)[3]);  
										  if(Main.specialplacer.get(p)[1].equals("CopyNote")) {
											  boolean k = false;
												for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH), b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN)}) {
													if(neighbor.getState() instanceof NoteBlock) {
														k = true;
														NoteBlock n = (NoteBlock) neighbor.getState();
														sign.setLine(1, String.valueOf(SoundID.calculateNote(n.getNote().toString(), n.getNote().getOctave())));
														break;
													}
													}
												if(!k) {
												sign.setLine(1, "0.5");
												p.sendMessage(ChatColor.RED + "There are no noteblocks nearby");
												}
										  }
										  else {
											  sign.setLine(1, Main.specialplacer.get(p)[1]);
										  }

										 
										  
									  }
									  sign.update();
									  p.sendMessage(ChatColor.GREEN + "Sign placed!");
									
								}
							},2L);
					      
						//}
							
				}
				
			   //event.setCancelled(true);
			}
		}
	}

}
