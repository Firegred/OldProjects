package me.Firegred.Game;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Enchanting {

	private static int axeE = 6;
	private static int axeS = 10;
	private static int prot = 15;
	
	public static boolean AxeChanceEfficiency() {
		Random r = new Random();
		int chance = r.nextInt(100);
		if(chance <= axeE) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean AxeChanceSharpness() {
		Random r = new Random();
		int chance = r.nextInt(100);
		if(chance <= axeS) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean ArmorChanceProtection() {
		Random r = new Random();
		int chance = r.nextInt(100);
		if(chance <= prot) {
			return true;
		}
		else {
			return false;
		}
	}
	
//	public static ItemStack[] CheckArmor(Player p) {
//		ItemStack[] armor = {Material.AIR};
//		if(p.getInventory().getHelmet() != null) {
//			armor[0] = p.getInventory().getHelmet();
//		}
//		if(p.getInventory().getChestplate() != null) {
//			armor[1] = p.getInventory().getChestplate();
//		}
//		if(p.getInventory().getLeggings() != null) {
//		    armor[2] = p.getInventory().getChestplate();
//		}
//		if(p.getInventory().getBoots() != null) {
//			armor[3] = p.getInventory().getChestplate();
//		}
//		return armor;
//	}
	
	public static ItemStack ChooseRandomArmor(ItemStack[] armor) {
		Random r = new Random();
	    int choose = r.nextInt(armor.length);
	    return armor[choose];
	}
	public static void EnchantArmor(ItemStack armor) {
		int level = 1;
		if(armor.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
		level = armor.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)+1;
	    }
		if(level != 10) {
	    armor.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		}
	}
	
	public static void enchantAxeSharpness(ItemStack axe) {
	    int level = 1;
		if(axe.containsEnchantment(Enchantment.DAMAGE_ALL)) {
		level = axe.getEnchantmentLevel(Enchantment.DAMAGE_ALL)+1;
	    }
		if(level != 10) {
	    axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);
		}
	}
	public static void enchantAxeEfficiency(ItemStack axe) {
		int level = 1;
		if(axe.containsEnchantment(Enchantment.DIG_SPEED)) {
		level = axe.getEnchantmentLevel(Enchantment.DIG_SPEED)+1;
		}
		if(level != 10) {
	    axe.addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
		}
	}
}
