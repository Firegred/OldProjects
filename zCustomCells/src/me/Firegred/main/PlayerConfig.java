package me.Firegred.main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerConfig {
	
	public static void createNewConfig(Player p) {
		File file = new File(Main.m.getDataFolder(), p.getUniqueId().toString() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("name", p.getName());
		config.set("world", Main.world);
		config.set("public", false);
		config.set("Cost.Wupgrade", 5000);
		config.set("Cost.Lupgrade", 5000);
		config.set("Cost.Hupgrade", 10000);
		config.set("Block.Floor", Material.STONE.toString());
		config.set("Block.Walls", Material.SMOOTH_BRICK.toString());
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static FileConfiguration getPlayerConfig(Player p) {
		File f = new File(Main.m.getDataFolder(), p.getUniqueId().toString() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		return config;
	}
	public static boolean hasConfig(Player p) {
		FileConfiguration c = getPlayerConfig(p);
		if(c.getString("name") == null) {
			return false;
		}
		return true;
	}
	public static void save(FileConfiguration c, File f) {
		try {
			c.save(f);
			c.load(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void save(FileConfiguration c, Player p) {
		File f = new File(Main.m.getDataFolder(), p.getUniqueId().toString() + ".yml");
		try {
			c.save(f);
			c.load(f);
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
