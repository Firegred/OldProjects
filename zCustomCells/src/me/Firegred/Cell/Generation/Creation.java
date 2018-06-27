package me.Firegred.Cell.Generation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Firegred.main.Main;
import me.Firegred.main.PlayerConfig;

public class Creation {

	private static Location generateLocation() {
		int x = 0;
		int y = 100;
		for(int z = -5000; z < Integer.MAX_VALUE; z+=30) {
			Location l = new Location(Bukkit.getWorld(Main.world), x, y, z+2);
			if(l.getWorld().getBlockAt(l).isEmpty()) {
				l.setZ(z);
				return l;
			}
			
		}
		return null;
	}
	public static void createCell(Player p) {
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		Location l = generateLocation();
		int x2 = l.getBlockX();
		int x1 = x2-2;
		
		int y1 = l.getBlockY();
		int y2 = y1+2;
		
		int z2 = l.getBlockZ();
		int z1 = z2-2;
		World w = Bukkit.getWorld(Main.world);
		Location sign = new Location(w, x1+1,y1+1,z1+4);
		Location plate = new Location(w, x1+1,y1,z1+4);
		Location glowstone = new Location(w,x1+1,y1-1,z1+1);
		Wall1(w,x1,y1,z1);
		Wall2(w,x1,y1,z1);
		Wall3(w,x1,y1,z1);
		Wall4(w,x1,y1,z1);
		Wall5(w,x1,y1,z1);
		Roof(w,x1,y1,z1);
		andesite(w,x1,y1,z1);
		w.getBlockAt(sign).setType(Material.WALL_SIGN);
		w.getBlockAt(plate).setType(Material.STONE_PLATE);
		w.getBlockAt(glowstone).setType(Material.GLOWSTONE);
		Sign s = (Sign) w.getBlockAt(sign).getState();
		s.setLine(0, ChatColor.BLUE + "Click Here To");
		s.setLine(1, ChatColor.BLUE + "Upgrade Cell");
		s.update();
		
		f.set("Location.x1", x1);
		f.set("Location.x2", x2);
		f.set("Location.y1", y1);
		f.set("Location.y2", y2);
		f.set("Location.z1", z1);
		f.set("Location.z2", z2);
		f.set("Size.width", 3);
		f.set("Size.length", 3);
		f.set("Size.height", 3);
		f.set("Sign.x", x1+1);
		f.set("Sign.y", y1+1);
		f.set("Sign.z", z1+4);
		PlayerConfig.save(f, p);
	}
	
	private static void andesite(World w, int x1, int y1, int z1) {
		for(int x = x1; x <= x1+2; x++) {
			w.getBlockAt(new Location(w, x,y1-1,z1+3)).setType(Material.COBBLESTONE);
		}
	}
	private static void Wall1(World w, int x1, int y1, int z1) {
		for(int x = x1-1; x <= x1+3; x++) {
			for(int y = y1; y <= y1+3; y++) {
				for(int z = z1-1; z <= z1-1; z++)	{
					w.getBlockAt(new Location(w, x,y,z)).setType(Material.SMOOTH_BRICK);
				}
			}
		}
	}
	private static void Wall2(World w, int x1, int y1, int z1) {
		for(int x = x1-1; x <= x1-1; x++) {
			for(int y = y1; y <= y1+3; y++) {
				for(int z = z1-1; z <= z1+5; z++)	{
					w.getBlockAt(new Location(w, x,y,z)).setType(Material.SMOOTH_BRICK);
				}
			}
		}
	}
	private static void Wall3(World w, int x1, int y1, int z1) {
		for(int x = x1+3; x <= x1+3; x++) {
			for(int y = y1; y <= y1+3; y++) {
				for(int z = z1-1; z <= z1+5; z++)	{
					w.getBlockAt(new Location(w, x,y,z)).setType(Material.SMOOTH_BRICK);
				}
			}
		}
	}
	private static void Wall4(World w, int x1, int y1, int z1) {
		for(int x = x1-1; x <= x1+3; x++) {
			for(int y = y1; y <= y1+3; y++) {
				for(int z = z1+5; z <= z1+5; z++)	{
					w.getBlockAt(new Location(w, x,y,z)).setType(Material.SMOOTH_BRICK);
				}
			}
		}
	}
	private static void Wall5(World w, int x1, int y1, int z1) {
		for(int x = x1; x <= x1+2; x++) {
			for(int y = y1; y <= y1+3; y++) {
				for(int z = z1+4; z <= z1+4; z++)	{
					w.getBlockAt(new Location(w, x,y,z)).setType(Material.SMOOTH_BRICK);
				}
			}
		}
	}
	private static void Roof(World w, int x1, int y1, int z1) {
		for(int x = x1-1; x <= x1+3; x++) {
			for(int y = y1+3; y <= y1+3; y++) {
				for(int z = z1-1; z <= z1+5; z++)	{
					w.getBlockAt(new Location(w, x,y,z)).setType(Material.SMOOTH_BRICK);
				}
			}
		}
	}
}
