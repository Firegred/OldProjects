package me.Firegred.NoteBlockComposer;

import java.util.ArrayList;

import me.Firegred.NoteBlock.SoundID;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ChestP extends BukkitRunnable {

	private Chest c;
	private ArrayList<Chest> chests;
	private int counter;
	private int position;
	private float volume;
	private int glassposition;
	private int[] positionz = {0,1,2,3,5,6,7,8};
	private boolean nextpage;
	private int specialspace;
	
	public ChestP(Chest chest, ArrayList<Chest> ch, int pos, float v) {
		c = chest;
		chests = ch;
		position = pos;
		counter = 0;
		glassposition = -1;
		specialspace = 0;
		volume = v;
	}
	private void setPlayerBar() {
		Chest ch = chests.get(counter);
		Inventory i = ch.getInventory();
		for(int c = 0; c < CompInv.playSlot.length; c++) {
			i.setItem(CompInv.playSlot[c], CompInv.playbar);
		}
	}
	private void resetPlayerBar(Chest ch) {
		Inventory i = ch.getInventory();
		for(int c = 0; c < CompInv.playSlot.length; c++) {
			i.setItem(CompInv.playSlot[c], CompInv.playbar);
		}
	}
	private void removeOldFollowerGlass() {
		Chest ch = chests.get(counter);
		Inventory i = ch.getInventory();
		if(glassposition >= 0) {
		for(int b =0; b < PlayAPI.chest[glassposition].length; b++) {
			if(i.getItem(PlayAPI.chest[glassposition][b]) != null) {
				if(i.getItem(PlayAPI.chest[glassposition][b]).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar")) {
					i.setItem(PlayAPI.chest[glassposition][b], null);
				}
			}
		}
		}
	}
	private void removeSpecialGlass(Chest c) {
		Chest ch = c;
		Inventory i = ch.getInventory();
		for(int b =0; b < PlayAPI.chest[7].length; b++) {
			if(i.getItem(PlayAPI.chest[7][b]) != null) {
				if(i.getItem(PlayAPI.chest[7][b]).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar")) {
				i.setItem(PlayAPI.chest[7][b], null);
				}
			}
		}
	}
	private void setPosition() {
		Chest ch = chests.get(counter);
		Inventory i = ch.getInventory();
		i.setItem(CompInv.playSlot[position], CompInv.follower);
		for(int b =0; b < PlayAPI.chest[position].length; b++) {
			if(i.getItem(PlayAPI.chest[position][b]) == null || i.getItem(PlayAPI.chest[position][b]).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar")) {
				i.setItem(PlayAPI.chest[position][b], CompInv.glassfollower);
			}
		}
	}
	private void getIDS() {
		Chest ch = chests.get(counter);
		Inventory i = ch.getInventory();
		for(int b =0; b < PlayAPI.chest[position].length; b++) {
			if(i.getItem(PlayAPI.chest[position][b]) != null) {
				if(!i.getItem(PlayAPI.chest[position][b]).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Location bar")) {
				ItemStack it = i.getItem(PlayAPI.chest[position][b]);
				int id = ComposerGUI.getID(it);
				float pitch = ComposerGUI.getPitch(it);
				playSound(id,pitch,ch);
				}
				
			}
		}
	}
	private void playSound(int id, float pitch, Chest p) {
		Bukkit.getWorld(c.getWorld().getName()).playSound(c.getLocation(), SoundID.ID(id), volume, pitch);
		Bukkit.getWorld(p.getWorld().getName()).playEffect(p.getLocation(), Effect.NOTE, 1);
	}
	
	@Override
	public void run() {
		if(position < 8) {
		try {
			setPlayerBar();
			getIDS();
			setPosition();
			removeOldFollowerGlass();
		} catch (java.lang.IllegalStateException e) {
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
		}
		position++;
		glassposition++;
		}
		specialspace++;
		if(position == 8) {
			position = 0;
			glassposition = -1;
			try {
				removeOldFollowerGlass();
			} catch (java.lang.IllegalStateException e) {
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
			}
			//setPlayerBar();
			if(counter+1 < chests.size()) {
				counter++;
			}
			else {
				counter = 0;
			}
			
		}
		if(specialspace == 9) {
			specialspace = 1;
			try {
				if(counter == 0) {
					removeSpecialGlass(chests.get(chests.size()-1));
					if(chests.size() > 1) {
					resetPlayerBar(chests.get(chests.size()-1));
					}
				}
				else {
					removeSpecialGlass(chests.get(counter-1));
					if(chests.size() > 1) {
					resetPlayerBar(chests.get(counter-1));
					}
				}
			} catch (java.lang.IllegalStateException e) {
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
			}
			try {
				if(Bukkit.getOnlinePlayers().size() != 0) {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(PlayAPI.viewer.containsKey(p)) {
				    if(CompInv.isSameName(chests.get(0).getInventory(), PlayAPI.viewer.get(p).getInventory())) {
					if(PlayAPI.viewer.get(p).equals(chests.get(0))) {
					//	if(counter+1 < chests.size()) {
						p.openInventory(chests.get(counter).getInventory());
						PlayAPI.viewer.put(p, chests.get(counter));
					}
				    else if(counter == 0 && PlayAPI.viewer.get(p).equals(chests.get(chests.size()-1))) {
				    	p.openInventory(chests.get(counter).getInventory());
						PlayAPI.viewer.put(p, chests.get(counter));
				    }
					else if(PlayAPI.viewer.get(p).equals(chests.get(counter-1))){
						p.openInventory(chests.get(counter).getInventory());
						PlayAPI.viewer.put(p, chests.get(counter));
					}
				    }
					}
				}
				}
			} catch (java.lang.IllegalStateException e) {
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
			}
            	
            }
			
			

			
		}
		
	}


