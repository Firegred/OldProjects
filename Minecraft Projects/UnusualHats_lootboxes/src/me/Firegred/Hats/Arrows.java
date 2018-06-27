package me.Firegred.Hats;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Arrows implements Listener{
	
	public Hats plugin;
	public static int fred = 0;
	public static HashMap<Arrow, Integer> tid = new HashMap<Arrow, Integer>();
	public Arrows(Hats instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void arrowlaunch(ProjectileLaunchEvent event) {
		try {
			if(event.getEntity() instanceof Arrow) {
				if(event.getEntity().getShooter() instanceof Player) {
				final Arrow arrow = (Arrow)event.getEntity();
				final Player player = (Player)event.getEntity().getShooter();
				if(player.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Unusual Bow: ")) {
					for(int i = 0; i < 28; i++) {
						if(player.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Unusual Bow: " + Hats.effectname[i])) {
							final int x = i;
							
								
								ArrayList<String> lore = new ArrayList<String>();
								for(String lores : player.getInventory().getItemInHand().getItemMeta().getLore()) {
									lore.add(lores);
									for(int c = 0; c < 500; c++) {
									int number = c;
								    if(lores.equals(ChatColor.GREEN + "Shots left: " + ChatColor.YELLOW + number) && c >= 2) {
									
								    ItemStack GBow = player.getItemInHand();
			                 		ItemMeta GBowMeta = GBow.getItemMeta();
			                 		GBowMeta.setDisplayName(player.getItemInHand().getItemMeta().getDisplayName());
			                 	    ArrayList<String> GBowLore = new ArrayList<String>();
			                 	    GBowLore.clear();
			                 	    int bownumber = Integer.valueOf(number);
			                 	    GBowLore.add(ChatColor.GREEN + "Shots left: " + ChatColor.YELLOW + (bownumber - 1));
			                 		GBowMeta.setLore(GBowLore);
			                 	    GBow.setItemMeta(GBowMeta);
			                 	    player.getInventory().setItemInHand(GBow);
			             
			                 	    break;
								}
								if(lores.equals(ChatColor.GREEN + "Shots left: " + ChatColor.YELLOW + number) && c == 1) {
									player.setItemInHand(null);
									player.sendMessage(ChatColor.YELLOW + "Your bow ran out of shots and turned into pixie dust!");
									player.getWorld().playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
									ItemStack pixedust = new ItemStack(Material.SUGAR, 1);
									ItemMeta pixedustmeta = pixedust.getItemMeta();
									pixedustmeta.setDisplayName(ChatColor.YELLOW + "Pixie dust");
									pixedust.setItemMeta(pixedustmeta);
								
									for(int cc = 0; cc < 35; cc++) {
										player.getWorld().dropItemNaturally(player.getLocation(), pixedust);
										
									}
			                 	   
									break;
							}
							
								
						
								}
						}
							for(int i2 = 0; i2 < 800; i2++) {
									Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

									@Override
									public void run() {
										if(arrow.isValid()) {
										try {
											Bukkit.getWorld(player.getWorld().getName()).playEffect(arrow.getLocation(), Chests.particles2[x], 1);
										} catch (Exception e1) {
										//if(fred == 200) {
										//	Bukkit.getServer().getScheduler().cancelTask(tid);
										//	fred = 0;
										//}
									}
										
										}
										
								}
									
							}, 2L + (2*i2));
						}
					}
				
				
				
			}
			}
			}
}
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			
		}
	
	}


}
