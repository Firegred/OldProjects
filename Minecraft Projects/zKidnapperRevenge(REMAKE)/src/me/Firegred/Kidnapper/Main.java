package me.Firegred.Kidnapper;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;


import me.Firegred.Kidnapper.GameMechanics.Captured;
import me.Firegred.Kidnapper.GameMechanics.Capturing;
import me.Firegred.Kidnapper.GameMechanics.Citizen;
import me.Firegred.Kidnapper.GameMechanics.Kidnapper;
import me.Firegred.Kidnapper.LobbyFunctions.EndingGame;
import me.Firegred.Kidnapper.LobbyFunctions.GameLobbyTeleport;
import me.Firegred.Kidnapper.LobbyFunctions.MainLobby;
import me.Firegred.Kidnapper.PlayerFunctions.PlayerBasicInteractions;
import me.Firegred.Kidnapper.PlayerFunctions.PlayerSignLogoff;
import me.Firegred.Kidnapper.Signs.Creation;
import me.Firegred.Kidnapper.Signs.SignBreak;
import me.Firegred.Kidnapper.Signs.SignClick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public class Main extends JavaPlugin {

public static String path = "Games.";
public static String rank = "Rank.";
public static String lobpath = "Lobby.";
private static final Logger log = Logger.getLogger("Minecraft");
private final Chat neat = new Chat(this);
private final Creation signs = new Creation(this);
private final SignClick click = new SignClick(this);
private final PlayerSignLogoff rawr = new PlayerSignLogoff(this);
private final SignBreak broke = new SignBreak(this);
private final MainLobby lobbys = new MainLobby(this);
private final GameLobbyTeleport tele = new GameLobbyTeleport(this);
private final PlayerBasicInteractions basic = new PlayerBasicInteractions(this);
private final Kidnapper kid = new Kidnapper(this);
private final Captured cap = new Captured(this);
private final Citizen cit = new Citizen(this);
private final EndingGame end = new EndingGame(this);
private final CaptureArea area = new CaptureArea(this);
private final Capturing captr = new Capturing(this);

public int id;
@Override
public void onEnable()
{
	log.info("[Abduction] has been enabled!");
	PluginManager pm = Bukkit.getPluginManager();
	pm.registerEvents(this.neat, this);
	pm.registerEvents(this.signs, this);
	pm.registerEvents(this.click, this);
	pm.registerEvents(this.rawr, this);
	pm.registerEvents(this.broke, this);
	pm.registerEvents(this.lobbys, this);
	pm.registerEvents(this.tele, this);
	pm.registerEvents(this.basic, this);
	pm.registerEvents(this.kid, this);
	pm.registerEvents(this.cap, this);
	pm.registerEvents(this.cit, this);
	pm.registerEvents(this.end, this);
	pm.registerEvents(this.area, this);
	pm.registerEvents(this.captr, this);
	File file = new File(getDataFolder() + File.separator + "config.yml");
	if(!file.exists()) {
		this.getLogger().info("Generating config.yml...");
			
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
}

@Override
public void onDisable()
{
	log.info("[Abduction] has been disabled!");
}

public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	if(cmd.getName().equalsIgnoreCase("gameban")) {
    	if(sender instanceof Player) {
    		Player p = (Player) sender;
    		if(p.isOp() || p.hasMetadata("Admin")) {
    			if(args.length == 0 || args.length >= 2) {
				       sender.sendMessage(ChatColor.WHITE + "/gameban <Player>");
				}
    			else {
    				String s = args[0];
    				Player tar = Bukkit.getPlayer(s);
    				if(tar == null) {
    					p.sendMessage(ChatColor.RED + "That player is no online!");
    				}
    				else {
    					tar.kickPlayer(ChatColor.RED + "You are banned from this server");
    					tar.setBanned(true);
    				}
    			}
    		}
    	}
    }
	
	if(cmd.getName().equalsIgnoreCase("gamekick")) {
    	if(sender instanceof Player) {
    		Player p = (Player) sender;
    		if(p.isOp() || p.hasMetadata("Admin")) {
    			if(args.length == 0 || args.length >= 2) {
				       sender.sendMessage(ChatColor.WHITE + "/gamekick <Player>");
				}
    			else {
    				String s = args[0];
    				Player tar = Bukkit.getPlayer(s);
    				if(tar == null) {
    					p.sendMessage(ChatColor.RED + "That player is not online!");
    				}
    				else {
    					tar.kickPlayer("You have been kicked");
    				}
    			}
    		}
    	}
    }
	
	if(cmd.getName().equalsIgnoreCase("deadmin")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				if(args.length == 0 || args.length >= 2) {
				       sender.sendMessage(ChatColor.WHITE + "/deadmin <Player>");
				}
				else {
					String s = args[0];
					OfflinePlayer tar = Bukkit.getOfflinePlayer(s);
					this.getConfig().set(rank + "Admin." + tar.getName(), null);
					this.saveConfig();
					this.reloadConfig();
					sender.sendMessage(ChatColor.RED + "You made " + ChatColor.WHITE + tar.getName() + ChatColor.RED + " a " + ChatColor.RED + " dead admin!");
					if(tar.isOnline()) {
						Player ss = Bukkit.getPlayer(s);
						ss.removeMetadata("Admin", this);
					}
				}
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("admin")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				if(args.length == 0 || args.length >= 2) {
				       sender.sendMessage(ChatColor.WHITE + "/admin <Player>");
				}
				else {
					String s = args[0];
					OfflinePlayer tar = Bukkit.getOfflinePlayer(s);
					this.getConfig().set(rank + "Admin." + tar.getName(), "true");
					this.saveConfig();
					this.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "You made " + ChatColor.WHITE + tar.getName() + ChatColor.GREEN + " an " + ChatColor.RED + " admin!");
					if(tar.isOnline()) {
						Player ss = Bukkit.getPlayer(s);
						ss.setMetadata("Admin", new FixedMetadataValue(this, "true"));
					}
					
				}
			}
		}
	}
	
	if(cmd.getName().equalsIgnoreCase("resetgame")) {
			if(sender instanceof Player) {
				final Player p = (Player) sender;
				if(p.isOp() || p.hasMetadata("Admin")) {
				if(args.length == 0 || args.length >= 2) {
		       sender.sendMessage(ChatColor.WHITE + "/resetgame <Game Name>");
		}
			else {
				
				final String game = args[0];
				for(String s : this.getConfig().getConfigurationSection("Games").getKeys(false)) {
					System.out.println(s);
				}
				
				String resetgame = Main.path + args[0];
				String numbers = resetgame + ".players";
				String lobby = resetgame + ".location";
				int x = this.getConfig().getInt(lobby + ".XLoc");
				int y = this.getConfig().getInt(lobby + ".YLoc");
				int z = this.getConfig().getInt(lobby + ".ZLoc");
				final Location location = new Location(p.getWorld(), x, y, z);
				final int max = this.getConfig().getInt(numbers + ".Max");
				for(Player s : Bukkit.getOnlinePlayers()) {
					if(s.hasMetadata("Games")) {
						if(s.getMetadata("Games").get(0).asString().equalsIgnoreCase(game)) {
						   s.kickPlayer("Game has been reset");
						}
					}
				}
				this.getConfig().set(numbers + ".number", 0);
				this.getConfig().set(numbers + ".Kidnappers", 0);
				this.getConfig().set(numbers + ".Citizens", 0);
				this.getConfig().set(resetgame + ".Ingame", "false");
				this.saveConfig();
				this.reloadConfig();
				p.sendMessage(ChatColor.WHITE + "Game " + args[0] + " has been reset!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						
					
				
				if(p.getWorld().getBlockAt(location).getType().equals(Material.SIGN_POST) || p.getWorld().getBlockAt(location).getType().equals(Material.SIGN)) {
					Sign sign = (Sign) p.getWorld().getBlockAt(location).getState();
					sign.setLine(1, ChatColor.DARK_BLUE + "[Join]");
					sign.setLine(2, ChatColor.GREEN + "0" + ChatColor.GREEN + "/" + ChatColor.GREEN + max);
					sign.setLine(3, ChatColor.RED + "[Lobby]");
					sign.update();
					
					
				}
					}
				}, 3L);
			}
	}
	}
	}
	if(cmd.getName().equalsIgnoreCase("setcapturearea")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
			if(args.length == 0 || args.length >= 2) {
				sender.sendMessage(ChatColor.WHITE + "/setcapturearea <Game Name>");
			}
			else {
			   //String chest = path + args[0] + ".Chestarea";
				
				if(!p.hasMetadata("Chest")) {
				((Player) sender).setMetadata("Chest", new FixedMetadataValue(this, args[0]));
				ItemStack wand = new ItemStack(Material.GOLD_AXE, 1);
				ItemMeta wandmeta = wand.getItemMeta();
				ArrayList<String> wandlore = new ArrayList<String>();
				
				wandmeta.setDisplayName(ChatColor.GOLD + "Wand for game: " + ChatColor.GREEN + args[0]);
				wandlore.clear();
				wandlore.add(ChatColor.RED + "Set the capture region");
				wandmeta.setLore(wandlore);
				wand.setItemMeta(wandmeta);
				p.getInventory().setItem(0, wand);
				}
				else {
					p.sendMessage(ChatColor.RED + "You already are setting up a game!");
				}
			   
			}
		}
	}
	}
	if(cmd.getName().equalsIgnoreCase("kidreload")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp() || p.hasMetadata("Admin")) {
				this.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Plugin reloaded");
			}
		}
		else {
			this.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Plugin reloaded");
		}
	}
	if(cmd.getName().equalsIgnoreCase("setjail")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
			if(args.length == 0 || args.length >= 2) {
			   sender.sendMessage(ChatColor.WHITE + "/setjail <Game Name>");
			}
			else {
				
				String clob = path + args[0] + ".gamespawn.jail.";
				this.getConfig().set(clob + "X", p.getLocation().getBlockX());
				this.getConfig().set(clob + "Y", p.getLocation().getBlockY());
				this.getConfig().set(clob + "Z", p.getLocation().getBlockZ());
				this.saveConfig();
				this.reloadConfig();
				sender.sendMessage(ChatColor.WHITE + "Jail spawn for " + args[0] + ChatColor.WHITE + " set!");
			
			}
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("reboot")) {
		if(sender instanceof Player) {
			 Player o = (Player) sender;
		     if(o.isOp() || o.hasMetadata("Admin")) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			//if(p != (Player) sender) {
			    if(p.hasMetadata("Games")) {
			    	String paths = path + p.getMetadata("Games").get(0).asString() + ".Ingame";
			    	this.getConfig().set(paths, "false");
			    	this.saveConfig();
			    	this.reloadConfig();
			    }
			    final Player c = p;
			    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			    	@Override
			    	public void run() {
			    		c.kickPlayer(ChatColor.WHITE + "Reboot");
			    	}
			    }, 3L);
			    
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						Bukkit.shutdown();
					}
				}, 9L);
			//}
		}
	}
	}
		else {
			for(Player p : Bukkit.getOnlinePlayers()) {
				//if(p != (Player) sender) {
				    if(p.hasMetadata("Games")) {
				    	String paths = path + p.getMetadata("Games").get(0).asString() + ".Ingame";
				    	this.getConfig().set(paths, "false");
				    	this.saveConfig();
				    	this.reloadConfig();
				    }
				    final Player c = p;
				    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				    	@Override
				    	public void run() {
				    		c.kickPlayer(ChatColor.WHITE + "Reboot");
				    	}
				    }, 3L);
				    
					Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						@Override
						public void run() {
							Bukkit.shutdown();
						}
					}, 9L);
				//}
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("setMainLobby")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
			sender.sendMessage(ChatColor.WHITE + "Lobby set!");
		
		this.getConfig().set(lobpath + "X", p.getLocation().getBlockX());
		this.getConfig().set(lobpath + "Y", p.getLocation().getBlockY());
		this.getConfig().set(lobpath + "Z", p.getLocation().getBlockZ());
		this.saveConfig();
		this.reloadConfig();
		}
		}
	}
	if(cmd.getName().equalsIgnoreCase("setlobby")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
			if(args.length == 0 || args.length >= 2) {
				sender.sendMessage(ChatColor.WHITE + "setlobby <Game name>");
			}
			else{
				String lobb = path + args[0] + ".lobby.";
				
				this.getConfig().set(lobb + "X", p.getLocation().getBlockX());
				this.getConfig().set(lobb + "Y", p.getLocation().getBlockY());
				this.getConfig().set(lobb + "Z", p.getLocation().getBlockZ());
				this.saveConfig();
				this.reloadConfig();
				sender.sendMessage(ChatColor.WHITE + "Lobby for " + args[0] + " set!");
				
			}
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("gameinfo")) {
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(p.isOp() || p.hasMetadata("Admin")) {
			if(p.hasMetadata("Games")) {
			String paths2 = Main.path + p.getMetadata("Games").get(0).asString() + ".players";
			String paths3 = Main.path + p.getMetadata("Games").get(0).asString();
			String numb = this.getConfig().get(paths2 + ".number").toString();
			String Kid = this.getConfig().get(paths2 + ".Kidnappers").toString();
			String cit = this.getConfig().get(paths2 + ".Citizens").toString();
			String max = this.getConfig().get(paths2 + ".Max").toString();
			String min = this.getConfig().get(paths2 + ".Min").toString();
			String games = p.getMetadata("Games").get(0).asString();
			p.sendMessage(ChatColor.GOLD + "Game: " + ChatColor.RED + games);
			p.sendMessage(ChatColor.GOLD + "Players: " + ChatColor.RED + numb);
			p.sendMessage(ChatColor.GOLD + "Max players: " + ChatColor.RED + max);
			p.sendMessage(ChatColor.GOLD + "Min needed: " + ChatColor.RED + min);
			p.sendMessage(ChatColor.GOLD + "Ingame: " + ChatColor.RED + this.getConfig().getString(paths3 + ".Ingame"));
			if(p.hasMetadata("Start")) {
				p.sendMessage(ChatColor.GOLD + "Kidnappers: " + ChatColor.RED + Kid);
				p.sendMessage(ChatColor.GOLD + "Citizens: " + ChatColor.RED + cit);
			}
			}
			else {
				p.sendMessage(ChatColor.RED + "You must be in a game!");
			}
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("gamedel")) {
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(p.isOp()) {
			p.sendMessage(ChatColor.YELLOW + "Now destroy a game sign!");
			p.setMetadata("Sign", new FixedMetadataValue(this, "yes"));
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("build")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				if(!p.hasMetadata("Build")) {
			p.setMetadata("Build", new FixedMetadataValue(this, "yes"));
			p.sendMessage(ChatColor.GOLD + "Build: " + ChatColor.RED + "On");
				}
				else {
				p.removeMetadata("Build", this);
				p.sendMessage(ChatColor.GOLD + "Build: " + ChatColor.RED + "Off");
				}
			}
		}
	}
	if(cmd.getName().equalsIgnoreCase("setcitizenspawn")) {
		if(sender instanceof Player) {
		   Player p = (Player) sender;
		   if(p.isOp()) {
			if(args.length == 0 || args.length >= 2) {
		   sender.sendMessage(ChatColor.WHITE + "/setcitizenspawn <Game name>");
		}
		else {
			
			String clob = path + args[0] + ".gamespawn.Citizens.";
			this.getConfig().set(clob + "X", p.getLocation().getBlockX());
			this.getConfig().set(clob + "Y", p.getLocation().getBlockY());
			this.getConfig().set(clob + "Z", p.getLocation().getBlockZ());
			this.saveConfig();
			this.reloadConfig();
			sender.sendMessage(ChatColor.WHITE + "Citizen spawn for " + args[0] + ChatColor.WHITE + " set!");
		
		}
		}
		}
	}
	if(cmd.getName().equalsIgnoreCase("setkidnapperlobby")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
			if(args.length == 0 || args.length >= 2) {
				sender.sendMessage(ChatColor.WHITE + "/setkidnapperlobby <Game name>");
			}
			else {
				
				String clob = path + args[0] + ".gamespawn.Kidnappers.";
				this.getConfig().set(clob + "lobX", p.getLocation().getBlockX());
				this.getConfig().set(clob + "lobY", p.getLocation().getBlockY());
				this.getConfig().set(clob + "lobZ", p.getLocation().getBlockZ());
				this.saveConfig();
				this.reloadConfig();
				sender.sendMessage(ChatColor.WHITE + "Kidnapper lobby for " + args[0] + ChatColor.WHITE + " set!");
			}
		}
	}
	}
	if(cmd.getName().equalsIgnoreCase("setkidnapperspawn")) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
			if(args.length == 0 || args.length >= 2) {
				sender.sendMessage(ChatColor.WHITE + "/setkidnapperspawn <Game name>");
			}
			else {
				
				String clob = path + args[0] + ".gamespawn.Kidnappers.";
				this.getConfig().set(clob + "spawnX", p.getLocation().getBlockX());
				this.getConfig().set(clob + "spawnY", p.getLocation().getBlockY());
				this.getConfig().set(clob + "spawnZ", p.getLocation().getBlockZ());
				this.saveConfig();
				this.reloadConfig();
				sender.sendMessage(ChatColor.WHITE + "Kidnapper spawn for " + args[0] + ChatColor.WHITE + " set!");
			}
		
		}
		}
		
	}
	else if(!((Player) sender).isOp() || ((Player) sender).hasMetadata("Admin")) {
		sender.sendMessage(ChatColor.WHITE + "Unkown Command!");
		return false;
		
	}
	return false;
}
}
