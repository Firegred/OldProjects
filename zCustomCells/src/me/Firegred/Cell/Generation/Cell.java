package me.Firegred.Cell.Generation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Firegred.main.Main;
import me.Firegred.main.PlayerConfig;

public class Cell {

	public static void createCell(Player p) {
		PlayerConfig.createNewConfig(p);
		Creation.createCell(p);
		teleportToCell(p);
	}
	public static void teleportToCell(Player p) {
		p.teleport(getCellLocation(p));
		p.sendMessage(ChatColor.GREEN + "Welcome to your cell " + p.getName());
	}
	public static Location getCellLocation(Player p) {
		FileConfiguration f = PlayerConfig.getPlayerConfig(p);
		int x = f.getInt("Location.x2")-1;
		int y = f.getInt("Location.y1");
		int z = f.getInt("Location.z2")+1;
		Location l = new Location(Bukkit.getWorld(Main.world),x,y,z);
		l.setYaw((float) -180.0);
		l.setPitch((float) 0.0);
		return l;
	}
	public static Location getTeleportLocation() {
		int x = Main.m.getConfig().getInt("Teleport.x");
		int y = Main.m.getConfig().getInt("Teleport.y");
		int z = Main.m.getConfig().getInt("Teleport.z");
		String world = Main.m.getConfig().getString("Teleport.world");
		Location l = new Location(Bukkit.getWorld(world),x,y,z);
		return l;
	}
	public static void teleportToLobby(Player p) {
		p.teleport(getTeleportLocation());
	}
}
