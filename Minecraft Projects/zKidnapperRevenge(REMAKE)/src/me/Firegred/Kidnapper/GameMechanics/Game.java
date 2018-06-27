package me.Firegred.Kidnapper.GameMechanics;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Game {

	public static HashMap<String, ArrayList<Player>> players = new HashMap<String, ArrayList<Player>>();
	public static HashMap<String, ArrayList<Player>> citizens = new HashMap<String, ArrayList<Player>>();
	public static HashMap<String, ArrayList<Player>> kidnappers = new HashMap<String, ArrayList<Player>>();
	public static HashMap<String, Integer> max = new HashMap<String, Integer>();
	public static HashMap<String, Integer> min = new HashMap<String, Integer>();
	public static HashMap<String, Location> lobbyteleport = new HashMap<String, Location>();
	public static HashMap<String, Location> kidnapperlobbyteleport = new HashMap<String, Location>();
	public static HashMap<String, Location> kidnapperspawnteleport = new HashMap<String, Location>();
	public static HashMap<String, Location> citizenteleport = new HashMap<String, Location>();
	public static HashMap<String, Location> mainlobbyTeleport = new HashMap<String, Location>();
	public static HashMap<String, Boolean> ingame = new HashMap<String, Boolean>();
	
	
}
 