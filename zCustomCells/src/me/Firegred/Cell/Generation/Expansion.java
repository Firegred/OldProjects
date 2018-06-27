package me.Firegred.Cell.Generation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Firegred.main.Main;
import me.Firegred.main.PlayerConfig;
import net.milkbowl.vault.economy.Economy;


public class Expansion {

	@SuppressWarnings("deprecation")
	public static void upgrade(Measure type, Player p) {
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		World w = Bukkit.getWorld(Main.world);
		int x1 = f.getInt("Location.x1");
		int x2 = f.getInt("Location.x2");
		int y1 = f.getInt("Location.y1");
		int y2 = f.getInt("Location.y2");
		int z1 = f.getInt("Location.z1");
		int z2 = f.getInt("Location.z2");
		int width = f.getInt("Size.width");
		int length = f.getInt("Size.length");
		int height = f.getInt("Size.height");
		
		double widthcost = (double) f.getInt("Cost.Wupgrade");
		double lengthcost = (double) f.getInt("Cost.Lupgrade");
		double heightcost = (double) f.getInt("Cost.Hupgrade");
		
		int limitw = Main.m.getConfig().getInt("MaxWidth");
		int limitl = Main.m.getConfig().getInt("MaxLength");
		int limith = Main.m.getConfig().getInt("MaxHeight");
		
		if(type==Measure.LENGTH) {
			if(length == limitl) p.sendMessage(ChatColor.RED + "Error: You have reached the length limit");
			else {
			if(Main.economy.getBalance(p.getName()) > lengthcost) {
			Main.economy.withdrawPlayer(p.getName(), lengthcost);
			expandLength(p,f,w, x1, x2, y1, y2, z1, z2, length, width, height);
			p.sendMessage(ChatColor.GREEN + "You sucessfully expanded the length of your cell!");
			}
			else {
				p.sendMessage(ChatColor.RED + "Error: You do not have " + ChatColor.YELLOW + "$" + ChatColor.YELLOW + lengthcost);
			}
			}
		}
		if(type==Measure.WIDTH) {
			if(width == limitw) p.sendMessage(ChatColor.RED + "Error: You have reached the width limit");
			else {
			if(Main.economy.getBalance(p.getName()) > widthcost) {
			Main.economy.withdrawPlayer(p.getName(), widthcost);
			expandWidth(p,f,w, x1, x2, y1, y2, z1, z2, length, width, height);
			p.sendMessage(ChatColor.GREEN + "You sucessfully expanded the width of your cell!");
			}
			else {
				p.sendMessage(ChatColor.RED + "Error: You do not have " + ChatColor.YELLOW + "$" + ChatColor.YELLOW + widthcost);
			}
			}
		}
		if(type == Measure.HEIGHT) {
			if(height == limith) p.sendMessage(ChatColor.RED + "Error: You have reached the height limit");
			else {
			if(Main.economy.getBalance(p.getName()) > heightcost) {
				Main.economy.withdrawPlayer(p.getName(), heightcost);
			expandRoof(p,f,w, x1, x2, y1, y2, z1, z2, length, width, height);
			p.sendMessage(ChatColor.GREEN + "You sucessfully expanded the height of your cell!");
			}
			else {
				p.sendMessage(ChatColor.RED + "Error: You do not have " + ChatColor.YELLOW + "$" + ChatColor.YELLOW + heightcost);
			}
			}
		}
	}
	private static void expandRoof(Player p, FileConfiguration f, World w, int x1, int x2, int y1, int y2, int z1, int z2, int length, int width, int height) {
		for(int x = x1; x <= x2; x++) {
			for(int y = y1+height; y <= y1+height; y++) {
				for(int z = z1; z <= z1+length; z++) {
					w.getBlockAt(x, y, z).setType(Material.AIR);
				}
			}
		}
		y2 = y2+1;
		f.set("Location.y2", y2);
		for(int x = x1-1; x <= x2+1; x++) {
			for(int y = y2+1; y <= y2+1; y++) {
				for(int z = z1-1; z <= z1+length+2; z++) {
					w.getBlockAt(x,y,z).setType(Material.SMOOTH_BRICK);
				}
			}
		}
		f.set("Size.height", height+1);
		int h = f.getInt("Cost.Hupgrade");
		h = h*2;
		f.set("Cost.Hupgrade", h);
		PlayerConfig.save(f, p);
	}
	
	
	
	private static void expandLength(Player p, FileConfiguration f, World w, int x1, int x2, int y1, int y2, int z1, int z2, int length, int width, int height) {
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				for(int z = z1-1; z <= z1-1; z++) {
					w.getBlockAt(x, y, z).setType(Material.AIR);
				}
			}
		}
		z1 = z1-1;
		f.set("Location.z1", z1);
		for(int x = x1-1; x <= x2+1; x++) {
			for(int y = y1; y <= y1+height; y++) {
				for(int z = z1-1; z <= z1-1; z++) {
					w.getBlockAt(x,y,z).setType(Material.SMOOTH_BRICK);
				}
			}
		}
		f.set("Size.length", length+1);
		int l = f.getInt("Cost.Lupgrade");
		l = l*2;
		f.set("Cost.Lupgrade", l);
		PlayerConfig.save(f, p);
	}
	
	private static void expandWidth(Player p,FileConfiguration f, World w, int x1, int x2, int y1, int y2, int z1, int z2, int length, int width, int height) {
		for(int x = x1-1; x <= x1-1; x++) {
			for(int y = y1; y <= y2; y++) {
				for(int z = z1; z <= z2+1; z++) {
					w.getBlockAt(x, y, z).setType(Material.AIR);
				}
			}
		}
		w.getBlockAt(x1-1, y1-1,z2+1).setType(Material.COBBLESTONE);
		x1 = x1-1;
		f.set("Location.x1", x1);
		for(int x = x1-1; x <= x1-1; x++) {
			for(int y = y1; y <= y1+height; y++) {
				for(int z = z1-1; z <= z1+length+2; z++) {
					w.getBlockAt(x,y,z).setType(Material.SMOOTH_BRICK);
				}
			}
		}
		f.set("Size.width", width+1);
		int wi = f.getInt("Cost.Wupgrade");
		wi = wi*2;
		f.set("Cost.Wupgrade", wi);
		PlayerConfig.save(f, p);
	}
}
