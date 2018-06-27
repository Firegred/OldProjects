package me.Firegred.Kidnapper.Signs;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.LobbyFunctions.MainLobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.scoreboard.Scoreboard;

public class Creation implements Listener{
	
public Main plugin;

public Creation(Main instance) {
	plugin = instance;
}

@EventHandler
public void signs(SignChangeEvent event) {
	Player p = event.getPlayer();
    if(event.getLine(1).equals("teleport")) {
    	String s = event.getLine(0).toString();
    	event.setLine(0, ChatColor.RED + "Didn't teleport?");
    	event.setLine(1, ChatColor.RED + "Click this sign");
    	event.setLine(2, ChatColor.RED + "to teleport to");
    	event.setLine(3, ChatColor.GREEN + s);
    }
	
	if(event.getLine(1).equalsIgnoreCase("LeaveGame")) {
    	event.setLine(1, ChatColor.GREEN + "Click the Sign");
    	event.setLine(2, ChatColor.GREEN + "to leave game");
    }
    
	for(int i = 0; i < 100; i++) {
		for(int b = 0; b < 100; b++) {
	if(event.getLine(1).equalsIgnoreCase("Max:" + i + "Min:" + b)) {
		
			String I = event.getLine(0).toString();
			String II = event.getLine(1).toString();
			String III = event.getLine(2).toString();
			String IIII = event.getLine(3).toString();
			event.setLine(0, I);
			event.setLine(1, ChatColor.DARK_BLUE + "[Join]");
			event.setLine(2, ChatColor.GREEN + "0" + ChatColor.GREEN + "/" + ChatColor.GREEN + i);
			event.setLine(3, ChatColor.RED + "[Lobby]");
			p.sendMessage(ChatColor.GREEN + "Game " + ChatColor.GREEN + I + " has been created!");
			Sign sign = (Sign)event.getBlock().getState();
			sign.update();
			
			String paths = Main.path + I + ".location";
			String Xp = paths + ".XLoc";
			String Yp = paths + ".YLoc";
			String Zp = paths + ".ZLoc";
			String Maxs = Main.path + I + ".players";
			plugin.getConfig().set(Maxs + ".Max", i);
			plugin.getConfig().set(Maxs + ".Min", b);
			plugin.getConfig().set(Maxs + ".number", "0");
			plugin.getConfig().set(Maxs + ".Kidnappers", "0");
			plugin.getConfig().set(Maxs + ".Citizens", "0");
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			board.registerNewObjective(I, "stat");
			plugin.getConfig().set(Xp, sign.getLocation().getBlockX());
			plugin.getConfig().set(Yp, sign.getLocation().getBlockY());
			plugin.getConfig().set(Zp, sign.getLocation().getBlockZ());	
			plugin.getConfig().set(Main.path + I + ".Ingame", "false");
			plugin.saveConfig();
			plugin.reloadConfig();
			
			break;
			
			
	}
		}
		}
	}
}




