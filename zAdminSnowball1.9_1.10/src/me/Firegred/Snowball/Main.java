package me.Firegred.Snowball;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin{

	private static final Logger log = Logger.getLogger("Minecraft");
	private static ArrayList<String> file = new ArrayList<String>();
	public static File customYml;
	public static String version = "1.2";
	public static FileConfiguration customConfig; 
	
	@Override
	public void onEnable()
	{
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			//this.getConfig().addDefault("Settings.destroyLoot", false);
			this.getConfig().addDefault("File", "default");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			this.reloadConfig();
		}
		customYml = new File(getDataFolder()+File.separator + "FrozenPlayers.yml");
		customConfig = YamlConfiguration.loadConfiguration(customYml);
		try {
			readfromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CursedAPI.defineSpeech();
		log.info("[Cursed snowball] has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CurseEvents(this), this);
		
	}

	@Override
	public void onDisable()
	{
		for(Player p : CursedAPI.getCursed().keySet()) {
			customConfig.set(p.getName(), "true");
		}
		for(String name : CurseEvents.leavers) {
			customConfig.set(name, "true");
		}
		for(String name : CurseEvents.rude) {
			customConfig.set(name, "true");
		}
		saveFrozenPlayersYml(customConfig, customYml);
		log.info("[Cursed snowball] has been disabled!");
	}
	public void saveFrozenPlayersYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
		ymlConfig.save(ymlFile);
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
	public static String spawnMessage() {
		Random r=  new Random();
		int ch = r.nextInt(Phrases.spawnphrase.length);
		String message = Phrases.spawnphrase[ch];
		return message;
	}
	public void readfromFile() throws IOException {
		Scanner s;
		if(this.getConfig().getString("File").equals("default")) {
		s = new Scanner(getClass().getResourceAsStream("/dialogue.txt"));
		}
		else {
		String name = this.getConfig().getString("File");
		File file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                .getFile());        
        String path = file.getParent() + File.separator + "CursedSnowball/" + name;
		s = new Scanner(new File(path));	
		}
		//BufferedReader s = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dialogue.txt")));
		int i = 0;
		String inputLine;
		while(s.hasNextLine()) {
		inputLine = s.nextLine().trim();
		file.add(inputLine);
		i++;
//		System.out.println(inputLine);
		}
//		while((inputLine = s.readLine()) != null) {
//		file.add(inputLine);
//		i++;
//		}
		
		log.info("[Cursed snowball] has successfully read from .txt file");
		s.close();
	}
	public static ArrayList<String >getTextFile() {
		return file;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(sender instanceof Player ){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("cursedball")) {
				if(p.hasPermission("CursedSnowball.ball")) {
					p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 0.5f, 1);
					p.sendMessage(ChatColor.LIGHT_PURPLE + spawnMessage());
					p.getInventory().addItem(Items.cursedball());
				}
			}
			if(cmd.getName().equalsIgnoreCase("eggcure")) {
				if(p.hasPermission("CursedSnowball.egg")) {
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
					p.sendMessage(ChatColor.GREEN + "Here is the cure to the curse!");
					p.getInventory().addItem(Items.cure());
				}
			}
			if(cmd.getName().equalsIgnoreCase("cursedwand")) {
				if(p.hasPermission("CursedSnowball.wand")) {
					p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 0.5f, 1);
					p.sendMessage(ChatColor.LIGHT_PURPLE + spawnMessage());
					p.getInventory().addItem(Items.cursedWand());
				}
			}
			if(cmd.getName().equalsIgnoreCase("curewand")) {
				if(p.hasPermission("CursedSnowball.wand")) {
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
					p.sendMessage(ChatColor.GREEN + "Here is the cure to the curse!");
					p.getInventory().addItem(Items.cureWand());
				}
			}
			if(cmd.getName().equalsIgnoreCase("CurseReload")) {
				if(p.hasPermission("CursedSnowball.reload")) {
					reloadConfig();
					file.clear();
					try {
						readfromFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CursedAPI.clearLists();
					CursedAPI.defineSpeech();
					p.sendMessage(ChatColor.GREEN + "CursedSnowball V" + version + " successfully reloaded");
					
				}
			}
			
		}
		return false;
	}
}

