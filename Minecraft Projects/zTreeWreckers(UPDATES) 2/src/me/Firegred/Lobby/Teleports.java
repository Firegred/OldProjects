package me.Firegred.Lobby;

import me.Firegred.Main.Main;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class Teleports implements Listener{
	
	private static Main plugin;
	
	@SuppressWarnings("static-access")
		public Teleports(Main instance) {
	       this.plugin = instance; 
	 }
	public static void ServerLobbyTeleport(Player p) {
		int x = plugin.getConfig().getInt("ServerLobby.X");
		int y = plugin.getConfig().getInt("ServerLobby.Y");
		int z = plugin.getConfig().getInt("ServerLobby.Z");
		World w = Main.w;
		Location l = new Location(w, x, y, z);
		p.setVelocity(new Vector(0, 0, 0));
		p.teleport(l);
		p.setLevel(0);
	}
	public static void SetServerLobby(int x, int y, int z) {
		plugin.getConfig().set("ServerLobby.X", x);
		plugin.getConfig().set("ServerLobby.Y", y);
		plugin.getConfig().set("ServerLobby.Z", z);
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	public static void SetGameLobby(String name, int x, int y, int z) {
		plugin.getConfig().set(Main.games + "." + name + ".Lobby.X", x);
		plugin.getConfig().set(Main.games + "." + name + ".Lobby.Y", y);
		plugin.getConfig().set(Main.games + "." + name + ".Lobby.Z", z);
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	public static void GameLobbyTeleport(Player p, String name) {
		int x = plugin.getConfig().getInt(Main.games + "." + name + ".Lobby.X");
		int y = plugin.getConfig().getInt(Main.games + "." + name + ".Lobby.Y");
		int z = plugin.getConfig().getInt(Main.games + "." + name + ".Lobby.Z");
		World w = Main.w;
		Location l = new Location(w, x, y, z);
		p.setVelocity(new Vector(0, 0, 0));
		p.teleport(l);
		
	}
}
