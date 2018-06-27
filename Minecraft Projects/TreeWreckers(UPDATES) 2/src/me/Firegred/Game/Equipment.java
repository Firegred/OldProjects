package me.Firegred.Game;

import java.util.Random;

import me.Firegred.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Equipment implements Listener{
	private static Main plugin;
	public Equipment(Main instance) {
          this.plugin = instance; //now you can access plugin
  }

	private static Material[] axes = {Material.GOLD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.DIAMOND_AXE, Material.SHEARS};
	private static Material[] head = {Material.LEATHER_HELMET, Material.GOLD_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET};
	private static Material[] chest = {Material.LEATHER_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE};
	private static Material[] leg = {Material.LEATHER_LEGGINGS, Material.GOLD_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.DIAMOND_LEGGINGS};
	private static Material[] boot  = {Material.LEATHER_BOOTS, Material.GOLD_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS};
	public static Material[][] materials  = {axes, head, chest, leg, boot};
	
	
	
	public static ItemStack PlayerTracker() {
		ItemStack t = new ItemStack(Material.COMPASS);
		ItemMeta tm = t.getItemMeta();
		tm.setDisplayName(ChatColor.WHITE + "Closest Player: ");
		t.setItemMeta(tm);
		return t;
	}
	public static ItemStack setPlayerTracker(Player p, Player target) {
		ItemStack t = new ItemStack(Material.COMPASS);
		ItemMeta tm = t.getItemMeta();
		tm.setDisplayName(ChatColor.WHITE + "Closest Player: " + ChatColor.RED + target.getName());
		t.setItemMeta(tm);
		p.setCompassTarget(target.getLocation());
		return t;
	}
	public static Player getNearestPlayer(Player checkNear) {
	    Player nearest = null;
	    for (Player p : checkNear.getWorld().getPlayers()) {
	        if(p.hasMetadata("Game") && !p.equals(checkNear)) {
	        if(p.getMetadata("Game").get(0).asString().equals(checkNear.getMetadata("Game").get(0).asString())) {
	    	if(p.getGameMode().equals(GameMode.SURVIVAL)) {
	        if (nearest == null) nearest = p;
	        else if (p.getLocation().distance(checkNear.getLocation()) < nearest.getLocation().distance(checkNear.getLocation())) nearest = p;
	    }
	        }
	        }
	    }
	    return nearest;
	}
	
	public static boolean hasArmor(Player p ){
		if(p.getInventory().getHelmet() == null) {
			if(p.getInventory().getChestplate() == null) {
				
				if(p.getInventory().getLeggings() == null) {
					if(p.getInventory().getBoots() == null) {
						return false;
					}
					else {
						return true;
					}
				}
				else {
					return true;
				}
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
	}
	
	public static void removeArmor(Player p) {
		if(p.getInventory().getHelmet() != null) {
			p.getInventory().setHelmet(null);
		}
		if(p.getInventory().getChestplate() != null) {
			p.getInventory().setChestplate(null);
		}
		if(p.getInventory().getLeggings() != null) {
			p.getInventory().setLeggings(null);
		}
		if(p.getInventory().getBoots() != null) {
			p.getInventory().setBoots(null);
		}
	}
	
	public static int chooseItem(Player p) {
		Random r = new Random();
		int ch = r.nextInt(materials.length)+1;
		return ch;
	}
	@SuppressWarnings("deprecation")
	public static ItemStack bones() {
		ItemStack b = new ItemStack(351, 3, (short)15);
		ItemMeta m = b.getItemMeta();
		m.setDisplayName(ChatColor.LIGHT_PURPLE + "Crushed Sheep bones");
		b.setItemMeta(m);
		return b;
	}
	
	private static ItemStack helmet(Player p) {
		ItemStack helm = new ItemStack(head[p.getMetadata("Helm").get(0).asInt()]);
		helm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		return helm;
	}
	private static ItemStack chestplate(Player p) {
		ItemStack c = new ItemStack(chest[p.getMetadata("ChestPlate").get(0).asInt()]);
		c.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		return c;
	}
	private static ItemStack leggings(Player p) {
		ItemStack l = new ItemStack(leg[p.getMetadata("Leggings").get(0).asInt()]);
		l.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		return l;
	}
	private static ItemStack boots(Player p) {
		ItemStack b = new ItemStack(boot[p.getMetadata("Boots").get(0).asInt()]);
		b.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		return b;
	}
	private static ItemStack Axe(Player p) {
		ItemStack axe = new ItemStack(axes[p.getMetadata("Axe").get(0).asInt()]);
		axe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		return axe;
	}
	
	
	public static ItemStack StarterAxe() {
		ItemStack axe = new ItemStack(Material.GOLD_AXE);
		axe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		return axe;
	}
	
	public static boolean hasMaxAxe(Player p) {
		if(p.getMetadata("Axe").get(0).asInt() == axes.length-1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean hasMaxHelm(Player p) {
		if(p.getMetadata("Helm").get(0).asInt() == head.length-1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean hasMaxChestPlate(Player p) {
		if(p.getMetadata("ChestPlate").get(0).asInt() == chest.length-1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean hasMaxLeggings(Player p) {
		if(p.getMetadata("Leggings").get(0).asInt() == leg.length-1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean hasMaxBoots(Player p) {
		if(p.getMetadata("Boots").get(0).asInt() == boot.length-1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public static ItemStack upgradeAxe(Player p, Main m) {
		if(p.hasMetadata("Axe")) {
			if(p.getMetadata("Axe").get(0).asInt() != axes.length) {
				for(int i = 0; i < axes.length-1; i++) {
					if(i == p.getMetadata("Axe").get(0).asInt()) {
						p.setMetadata("Axe", new FixedMetadataValue(m, i+1));
						return Axe(p);
					}
				}
			
			
			}
			}
			return null;
	}
	public static ItemStack upgradeBoots(Player p, Main m) {
		if(p.hasMetadata("Boots")) {
		    if(p.getMetadata("Boots").get(0).asInt() != boot.length) {
			if(p.getInventory().getBoots() != null) {
			for(int i = 0; i < boot.length-1; i++) {
				if(i == p.getMetadata("Boots").get(0).asInt()) {
					p.setMetadata("Boots", new FixedMetadataValue(m, i+1));
					return boots(p);
				}
			}
		}
		else {
		  return boots(p);	
		}
		   }
		}
		return null;
	}
	public static ItemStack upgradeLeggings(Player p, Main m) {
		if(p.hasMetadata("Leggings")) {
		    if(p.getMetadata("Leggings").get(0).asInt() != leg.length) {
			if(p.getInventory().getLeggings() != null) {
			for(int i = 0; i < leg.length-1; i++) {
				if(i == p.getMetadata("Leggings").get(0).asInt()) {
					p.setMetadata("Leggings", new FixedMetadataValue(m, i+1));
					return leggings(p);
				}
			}
		}
		else {
		  return leggings(p);	
		}
		   }
		}
		return null;
	}
	
	public static ItemStack upgradeChestPlate(Player p, Main m) {
		if(p.hasMetadata("ChestPlate")) {
		    if(p.getMetadata("ChestPlate").get(0).asInt() != chest.length) {
			if(p.getInventory().getChestplate() != null) {
			for(int i = 0; i < chest.length-1; i++) {
				if(i == p.getMetadata("ChestPlate").get(0).asInt()) {
					p.setMetadata("ChestPlate", new FixedMetadataValue(m, i+1));
					return chestplate(p);
				}
			}
		}
		else {
		  return chestplate(p);	
		}
		   }
		}
		return null;
	}
	
	public static ItemStack upgradeHelmet(Player p, Main m) {
		if(p.hasMetadata("Helm")) {
		    if(p.getMetadata("Helm").get(0).asInt() != head.length) {
			if(p.getInventory().getHelmet() != null) {
			for(int i = 0; i < head.length-1; i++) {
				if(i == p.getMetadata("Helm").get(0).asInt()) {
					p.setMetadata("Helm", new FixedMetadataValue(m, i+1));
					return helmet(p);
				}
			}
		}
		else {
		  return helmet(p);	
		}
		   }
		}
		return null;
	}

}
