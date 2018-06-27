package me.Firegred.Cell.Generation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Firegred.main.Main;
import me.Firegred.main.PlayerConfig;

public class Bounds {

	
	public static boolean isModifySign(Player p, int xx, int yy, int zz) {
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		int x = f.getInt("Sign.x");
		int y = f.getInt("Sign.y");
		int z = f.getInt("Sign.z");
		Location l = new Location(Bukkit.getWorld(Main.world),x,y,z);
		Location l2 =  new Location(Bukkit.getWorld(Main.world),xx,yy,zz);
		if(l.equals(l2)) {
			return true;
		}
		return false;
	}
	
	public static boolean isInInteractBounds(Player p, int xx, int yy, int zz) {
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		int x1 = f.getInt("Location.x1")-1;
		int x2 = f.getInt("Location.x2")+1;
		int y1 = f.getInt("Location.y1")-1;
		int y2 = f.getInt("Location.y2")+1;
		int z1 = f.getInt("Location.z1")-1;
		int z2 = f.getInt("Location.z2")+1;
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				for(int z = z1; z <= z2; z++) {
					if(x==xx && y==yy && z==zz) return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isSpecialPressurePlate(Location l) {
		int x = Main.m.getConfig().getInt("PressurePlate.x");
		int y = Main.m.getConfig().getInt("PressurePlate.y");
		int z = Main.m.getConfig().getInt("PressurePlate.z");
		String world = Main.m.getConfig().getString("PressurePlate.world");
		Location l2 = new Location(Bukkit.getWorld(world),x,y,z);
		if(l.equals(l2)) {
			return true;
		}
		return false;
		
	}
	public static boolean isExitPlate(Location l) {
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		World w = l.getWorld();
		Location l2 = new Location(w,x,y+1,z);
		if(l2.getBlock() != null) {
			if(l2.getBlock().getState() instanceof Sign) {
				Sign s = (Sign) l2.getBlock().getState();
				if(s.getLine(0).contains(ChatColor.BLUE + "Click Here To")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isInBounds(Player p, int xx, int yy, int zz) {
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		int x1 = f.getInt("Location.x1");
		int x2 = f.getInt("Location.x2");
		int y1 = f.getInt("Location.y1");
		int y2 = f.getInt("Location.y2");
		int z1 = f.getInt("Location.z1");
		int z2 = f.getInt("Location.z2");
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				for(int z = z1; z <= z2; z++) {
					if(x==xx && y==yy && z==zz) return true;
				}
			}
		}
		return false;
	}
}
