package me.Firegred.NoteBlock;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note.Tone;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow.Spigot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interactions implements Listener{
	
    @EventHandler
    public void block(NotePlayEvent event) {
    	Block b = event.getBlock();
    	if(b.getType().equals(Material.NOTE_BLOCK)) {
    		for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH), b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN)}) {
				if(neighbor.getState() instanceof Sign) {
					
				
			
			//	if(block.getRelative(BlockFace.NORTH).getState() instanceof Sign) {
				Sign sign = (Sign) neighbor.getState();
				//System.out.println("SignPots");
				if(sign.getLine(0).equals(ChatColor.GREEN + "NoteBlock") || sign.getLine(0).contains(ChatColor.GREEN + "[P]") || sign.getLine(0).substring(3).equals(ChatColor.GREEN +"NoteBlock")) {
					float number1 = Float.parseFloat(sign.getLine(1).toString());
					int number2 = Integer.parseInt(sign.getLine(2).toString());
					String sss = Main.instance.getConfig().getString("Global Volume");
					float sound = Float.parseFloat(sss);
					//System.out.println("should work");
					sign.getWorld().playSound(sign.getLocation(), SoundID.ID(number2), sound, number1);
					double x = b.getX() + 0.5;
					double y = b.getY() + 1;
					double z = b.getZ() + 0.5;
					Location loc = new Location(b.getWorld(), x, y, z);
					try {
				       b.getWorld().playEffect(loc, Effect.NOTE, 1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					event.setCancelled(true);
					break;
			//	}
				}
				}
			}
		}
		}
		
    	
   
	
	@EventHandler
	public void clicking(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock().getType().equals(Material.NOTE_BLOCK)) {
			//	System.out.println("this is noteblock");
				Block b = event.getClickedBlock();
				for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH), b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN)}) {
					if(neighbor.getState() instanceof Sign) {
						
					
				
				//	if(block.getRelative(BlockFace.NORTH).getState() instanceof Sign) {
					Sign sign = (Sign) neighbor.getState();
					String name = ChatColor.GREEN + p.getName();
					String signame1 = " ";
					
					
					if(sign.getLine(0).contains(ChatColor.GREEN + "[P]")) {
					String signame = sign.getLine(0);
					signame1 = signame.substring(signame.lastIndexOf("]") + 1);
					//System.out.println("SignPots");
					}
					
					if(sign.getLine(0).equals(ChatColor.GREEN + "NoteBlock") || name.contains(signame1) || sign.getLine(0).substring(3).equals(ChatColor.GREEN +"NoteBlock")) {
						if(sign.getLine(0).startsWith(ChatColor.RED + "#")) {
							float num = Float.parseFloat(sign.getLine(1).toString());
							int number2 = Integer.parseInt(sign.getLine(2).toString());
							double x = b.getX() + 0.5;
							double y = b.getY() + 1;
							double z = b.getZ() + 0.5;
							Location loc = new Location(b.getWorld(), x, y, z);
							String sss = Main.instance.getConfig().getString("Global Volume");
							float sound = Float.parseFloat(sss);	
							try {
								b.getWorld().playEffect(loc, Effect.NOTE, 1);
								//ParticleEffect.sendToLocation(ParticleEffect.NOTE, loc, 0, 0, 0, 1, 1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							p.getWorld().playSound(sign.getLocation(), SoundID.ID(number2), sound, num);
							event.setCancelled(true);
						}
					    else if(p.hasPermission("NoteBlock.tune")) {
						float num = Float.parseFloat(sign.getLine(1).toString());
						int number2 = Integer.parseInt(sign.getLine(2).toString());
						double x = b.getX() + 0.5;
						double y = b.getY() + 1;
						double z = b.getZ() + 0.5;
						Location loc = new Location(b.getWorld(), x, y, z);
						String sss = Main.instance.getConfig().getString("Global Volume");
						float sound = Float.parseFloat(sss);
						try {
							b.getWorld().playEffect(loc, Effect.NOTE, 1);
							//ParticleEffect.sendToLocation(ParticleEffect.NOTE, loc, 0, 0, 0, 1, 1);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					//	System.out.println("should work");
						if(num < 2.033) {
						float number1 = (float) (num+0.033);
						String number11 = String.valueOf(number1);
						p.getWorld().playSound(sign.getLocation(), SoundID.ID(number2), sound, number1);
						sign.setLine(1, number11);
						sign.update();
						}
						else {
							float number1 = (float) 0.5;
							String number11 = String.valueOf(number1);
							p.getWorld().playSound(sign.getLocation(), SoundID.ID(number2), sound, number1);
							sign.setLine(1, number11);
							sign.update();
						}
						event.setCancelled(true);
				//	}
						}
					
						else {
							event.setCancelled(true);
						}
				}
					}
			}
			}
		}
	}
}
