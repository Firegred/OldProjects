package me.Firegred.Kidnapper.Signs;

import me.Firegred.Kidnapper.Main;
import me.Firegred.Kidnapper.GameMechanics.Game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class SignClick implements Listener{
	
	public Main plugin;
	
	public SignClick(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void Clicking(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock().getType().equals(Material.SIGN_POST) || event.getClickedBlock().getType().equals(Material.SIGN)) {
			Sign sign = (Sign) event.getClickedBlock().getState();
			//System.out.println("test");
			if(sign.getLine(1).contains(ChatColor.DARK_RED + "[Full]")) {
				p.sendMessage(ChatColor.RED + "That game is full!");
			}
			
			if(sign.getLine(1).contains(ChatColor.DARK_BLUE + "[Join]")) {
				//System.out.println("Lobby works");
	                 for(int i = 0; i < 100; i++) {
	                	 for(int b = -2; b < 100; b++) {
	                    String B = String.valueOf(b);
	                    String I = String.valueOf(i);
						if(sign.getLine(2).equalsIgnoreCase(ChatColor.GREEN + B + ChatColor.GREEN + "/" + ChatColor.GREEN + I)) {
							if(Game.ingame.get(sign.getLine(0).toString()) == null) {
						    Game.ingame.put(sign.getLine(0).toString(), false);
							}
							boolean game = Game.ingame.get(sign.getLine(0).toString());
							//String path = Main.path + sign.getLine(0).toString() + ".Ingame";
							//if(plugin.getConfig().getString(path).equalsIgnoreCase("true")) {
								if(game == true) {
							
							    sign.setLine(3, ChatColor.DARK_RED + "[Ingame]");
								sign.setLine(1, ChatColor.RED + "[NotJoinable]"); 
								sign.update();
								}
							//}
							else {
							
						
							
								
							//sign.getLine(2).replace(sign.getLine(1).toString(), ChatColor.GREEN + "" + b++ + ChatColor.GREEN + " / " + ChatColor.GREEN + i);
							   int b2 = b+1;
							   String B2 = String.valueOf(b2);
							 //  System.out.println("Should work :(");
							  
							  
							   
							   
							   
							   if(b != i) {
								  
								   if(!p.hasMetadata("Games")) {
									   
								   p.setMetadata("Games", new FixedMetadataValue(plugin, sign.getLine(0).toString()));   
								
							   sign.setLine(2, ChatColor.GREEN + B2 + ChatColor.GREEN + "/" + ChatColor.GREEN + I);
							   p.sendMessage(ChatColor.BLUE + "You have joined game " + sign.getLine(0));
							   sign.update();
							  // if(b2 == i) {
							//	   sign.setLine(1, ChatColor.DARK_RED + "[Full]");
								   sign.update();
							 //  }
								   }
								   else {
									   p.sendMessage(ChatColor.RED + "You are already in a game!");
								   }
							   }
							   
							  // if(b2 == i) {
							//	   sign.setLine(1, ChatColor.DARK_RED + "[Full]");
							//	   sign.update();
							//	   p.sendMessage(ChatColor.RED + "The game is full!");
							//   }
							   
							   
							   break;
							
					
				
			}
		}
	                 }
			}
	
			
			}}
		}
	}
}

