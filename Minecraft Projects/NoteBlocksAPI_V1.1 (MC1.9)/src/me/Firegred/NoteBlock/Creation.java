package me.Firegred.NoteBlock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.NoteBlock;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;


public class Creation implements Listener{
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public static boolean isFloat(String s) {
	    try { 
	        Float.parseFloat(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	@EventHandler
	public void create(SignChangeEvent event) {
		Player p = event.getPlayer();
		if(p.hasPermission("NoteBlock.create")) {
			String I = event.getLine(0).toString();
			Block b = event.getBlock();
			if(I.equalsIgnoreCase("NoteBlock")) {
				p.sendMessage(ChatColor.GREEN + "NoteBlock Sign created");
				String line = event.getLine(1).toString().trim();
				if(line.length() > 0) {
					String[] parts = event.getLine(1).toString().split(" ");
					String p1 = parts[0];
					String p2 = " ";
					String p3 = " ";
					String p4 = " ";
					if(parts.length == 4) {
						p2 = parts[1];
						p3 = parts[2];
						p4 = parts[3];
					}
					if(parts.length == 3) {
						p2 = parts[1];
						p3 = parts[2];
					}
					if(parts.length == 2) {
						p2 = parts[1];
					}
					boolean copynote = false;
					boolean protect = false;
					float f = 0.0f;
					int id = 1000;
					boolean tune = false;
					if(p1.equalsIgnoreCase("n")) {
						copynote = true;
					}
					else if(p2.equalsIgnoreCase("n")) {
                    	copynote = true;
					}
					else if(p3.equalsIgnoreCase("n")) {
                    	copynote = true;
                    }
					else if(p4.equalsIgnoreCase("n")) {
        				copynote = true;
        			}
					
					if(p1.equalsIgnoreCase("p")) {
						protect = true;
					}
					else if(p2.equalsIgnoreCase("p")) {
						protect = true;
					}
					else if(p3.equalsIgnoreCase("p")) {
						protect = true;
					}
					else if(p4.equalsIgnoreCase("p")) {
						protect = true;
					}
					
					if(isInteger(p1)) {
                        id = Integer.valueOf(p1);
					}
					else if(isInteger(p2)) {
						id = Integer.valueOf(p2);
					}
					else if(isInteger(p3)) {
						id = Integer.valueOf(p3);
					}
					else if(isInteger(p4)) {
						id = Integer.valueOf(p4);
					}
					
					  if(p1.contains("f")) {
						   p1 = p1.split("f")[0];
		             if(Creation.isFloat(p1)) {
						f = Float.parseFloat(p1);
						copynote = false;	
						}
		             }
		             else if(p2.contains("f")) {
						   p2 = p2.split("f")[0];
						if(Creation.isFloat(p2)) {
						f = Float.parseFloat(p2);
						copynote = false;		
						}
		         	}
		             else if(p3.contains("f")) {
		                 p3 = p3.split("f")[0];
		 				
						if(Creation.isFloat(p3)) {
						f = Float.parseFloat(p3);
						copynote = false;	
		                }
		             }
		             else if(p4.contains("f")) {
		                 p4 = p4.split("f")[0];
		 				
						if(isFloat(p4)) {
						f = Float.parseFloat(p4);
						copynote = false;	
		                }
		             }
				
					  if(p1.contains("t")) {
			            	 tune = true;
			             }
			             else if(p2.contains("t")) {
			            	 tune = true;
			             }
			             else if(p3.contains("t")) {
			            	 tune = true;
			             }
			             else if(p4.contains("t")) {
			            	 tune = true;
			             }
					
					
					if(f > 0.5) {
						if(f > 2.0) {
							event.setLine(1, "2.0");
						}
						else {
							event.setLine(1, String.valueOf(f));
						}
					}
					else {
						event.setLine(1, "0.5");
					}
					
					if(id < SoundID.soundLength()) {
						event.setLine(2, String.valueOf(id));
						event.setLine(3, SoundID.ID(id).toString());
					}
					else {
						event.setLine(2, "0");
						event.setLine(3, ChatColor.DARK_RED + "null");
					}
					if(copynote) {
						boolean k = false;
						for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH), b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN)}) {
							if(neighbor.getState() instanceof NoteBlock) {
								k = true;
								NoteBlock n = (NoteBlock) neighbor.getState();
								event.setLine(1, String.valueOf(SoundID.calculateNote(n.getNote().toString(), n.getNote().getOctave())));
								break;
							}
							}
						if(!k) {
						event.setLine(1, "0.5");
						p.sendMessage(ChatColor.RED + "There are no noteblocks nearby");
						}
						
					}
					if(protect) {
						String name = ChatColor.GREEN + "[P]" + ChatColor.GREEN + p.getName();
						event.setLine(0, name);
					}
					else {
						event.setLine(0, ChatColor.GREEN + "NoteBlock");
					}
					if(tune) {
						String t = event.getLine(0);
						 event.setLine(0,ChatColor.RED + "#" + t);
					}	
					
				    
					
				}
				else {
					event.setLine(0, ChatColor.GREEN + "NoteBlock");
					event.setLine(1, "0.5"); //pitch
					event.setLine(2, "0"); //number in array
					event.setLine(3, ChatColor.DARK_RED + "null");
				}
				Sign sign = (Sign) event.getBlock().getState();
				sign.update();
				
			
			}
		}
	}

}
