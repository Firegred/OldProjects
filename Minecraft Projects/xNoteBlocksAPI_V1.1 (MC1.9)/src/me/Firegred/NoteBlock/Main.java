package me.Firegred.NoteBlock;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;




import me.Firegred.NoteBlockComposer.ChestFunctions;
import me.Firegred.NoteBlockComposer.CompInv;
import me.Firegred.NoteBlockComposer.ComposerSelection;
import me.Firegred.NoteBlockComposer.ComposerSelectorEvents;
import me.Firegred.NoteBlockComposer.Inventories;
import me.Firegred.NoteBlockComposer.PlayAPI;
import me.Firegred.NoteBlockComposer.SignCreation;
import me.Firegred.NoteBlockComposer.SpeedSelection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.NoteBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Sign;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static Main instance;
	private static final Logger log = Logger.getLogger("Minecraft");
	public static String[] MetaData = {"Create", "Composer","Speed","Slot","Copy","Delete"};
	public static String[] InventoryMeta = {"Composer","Speed","Slot","Copy","Delete"};
//	private final NoteBlockGUI note = new NoteBlockGUI(this);
//	private final Creation create = new Creation(this);
//	private final Signs sign = new Signs(this);
//	private final Interactions inter = new Interactions(this);
//	private final fastplace fast = new fastplace(this);
	public static final HashMap<Player, Block> place = new HashMap<Player, Block>();
	public static final ArrayList<Player> placer = new ArrayList<Player>();
	public static final HashMap<Player, String[]> specialplacer = new HashMap<Player, String[]>();
	public static String version = "1.1 ";
	public static SoundInventory s;
	public static ComposerSelection ccc;
	@Override
	public void onEnable() {
		instance = this;
		s = new SoundInventory();
		ccc = new ComposerSelection();
		log.info("[NoteBlockPlus] has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			
			this.getConfig().addDefault("Global Volume", "1.6");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
//		pm.registerEvents(this.note, this);
//		pm.registerEvents(this.create, this);
//		pm.registerEvents(this.sign, this);
//		pm.registerEvents(this.inter, this);
//		pm.registerEvents(this.fast, this);
		CompInv.defineItems();
		CompInv.definePlayStuff();
		SpeedSelection.define();
		PlayAPI.define();
		pm.registerEvents(new Signs(), this);
		pm.registerEvents(new NoteBlockGUI(), this);
		pm.registerEvents(new Creation(), this);
		pm.registerEvents(new Interactions(), this);
		pm.registerEvents(new fastplace(this), this);
		pm.registerEvents(new SignCreation(), this);
		pm.registerEvents(new ChestFunctions(), this);
		pm.registerEvents(new Inventories(), this);
		pm.registerEvents(new ComposerSelectorEvents(), this);
	}
	@Override
	public void onDisable() {
		log.info("[NoteBlockPlus] has been disabled!");
		if(!PlayAPI.ChestPlayers.isEmpty()) {
			for(Chest c : PlayAPI.ChestPlayers.keySet()) {
				PlayAPI.stop(c);
			}
		}
	}
	public static SoundInventory getSoundInventory() {
		return s;
	}
	public static ComposerSelection getComposerSelector() {
		return ccc;
	}
	
	public static void getSign(Player p, String[] args) {
		String[] special = new String[4];
			String[] parts = args;
			String p1 = parts[0];
			String p2 = " ";
			String p3 = " ";
			String p4 = " ";
			if(parts.length == 4) {
				p2 = parts[1];
				p3 = parts[2];
				p4 = parts[3];
			}
			if(parts.length == 3) {
				p2 = parts[1];
				p3 = parts[2];
			}
			if(parts.length == 2) {
				p2 = parts[1];
			}
			boolean copynote = false;
			boolean protect = false;
			float f = 0.0f;
			int id = 1000;
			boolean tune = false;
			if(p1.equalsIgnoreCase("n")) {
				copynote = true;
			}
			else if(p2.equalsIgnoreCase("n")) {
            	copynote = true;
			}
			else if(p3.equalsIgnoreCase("n")) {
            	copynote = true;
            }
			else if(p4.equalsIgnoreCase("n")) {
				copynote = true;
			}
			
			if(p1.equalsIgnoreCase("p")) {
				protect = true;
			}
			else if(p2.equalsIgnoreCase("p")) {
				protect = true;
			}
			else if(p3.equalsIgnoreCase("p")) {
				protect = true;
			}
			else if(p4.equalsIgnoreCase("p")) {
				protect = true;
			}
			
			if(Creation.isInteger(p1)) {
                id = Integer.valueOf(p1);
			}
			else if(Creation.isInteger(p2)) {
				id = Integer.valueOf(p2);
			}
			else if(Creation.isInteger(p3)) {
				id = Integer.valueOf(p3);
			}
			else if(Creation.isInteger(p4)) {
				id = Integer.valueOf(p4);
			}
			

             
			
            
             if(p1.contains("f")) {
				   p1 = p1.split("f")[0];
             if(Creation.isFloat(p1)) {
				f = Float.parseFloat(p1);
				copynote = false;	
				}
             }
             else if(p2.contains("f")) {
				   p2 = p2.split("f")[0];
				if(Creation.isFloat(p2)) {
				f = Float.parseFloat(p2);
				copynote = false;		
				}
         	}
             else if(p3.contains("f")) {
                 p3 = p3.split("f")[0];
 				
				if(Creation.isFloat(p3)) {
				f = Float.parseFloat(p3);
				copynote = false;	
                }
             }
             else if(p4.contains("f")) {
                 p4 = p4.split("f")[0];
 				
				if(Creation.isFloat(p4)) {
				f = Float.parseFloat(p4);
				copynote = false;	
                }
             }
			
             if(p1.contains("t")) {
            	 tune = true;
             }
             else if(p2.contains("t")) {
            	 tune = true;
             }
             else if(p3.contains("t")) {
            	 tune = true;
             }
             else if(p4.contains("t")) {
            	 tune = true;
             }
			
			if(f > 0.5) {
				if(f > 2.0) {
					
					special[1] = "2.0";
				}
				else {
					special[1] = String.valueOf(f);
				}
			}
			else {
				special[1]= "0.5";
			}
			
			if(id < SoundID.soundLength()) {
				special[2] = String.valueOf(id);
				special[3] = SoundID.ID(id).toString();
			}
			else {
				special[2]= "0";
				special[3]= ChatColor.DARK_RED + "null";
			}
			if(copynote) {
				special[1] = "CopyNote";
			}
//			if(copynote) {
//				boolean k = false;
//				for(Block neighbor : new Block[] {b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH), b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN)}) {
//					if(neighbor.getState() instanceof NoteBlock) {
//						k = true;
//						NoteBlock n = (NoteBlock) neighbor.getState();
//						special[1] = String.valueOf(SoundID.calculateNote(n.getNote().toString(), n.getNote().getOctave()));
//						break;
//					}
//					}
//				if(!k) {
//				special[1] =  "0.5";
//				p.sendMessage(ChatColor.RED + "There are no noteblocks nearby");
//				}
//				
//			}
			if(protect) {
				String name = ChatColor.GREEN + "[P]" + ChatColor.GREEN + p.getName();
				special[0] = name;
			}
			else {
				special[0] = ChatColor.GREEN + "NoteBlock";
			}
			if(tune) {
				String t = special[0];
				special[0] = ChatColor.RED + "#" + t;
			}
			specialplacer.put(p, special);
			//System.out.println(special.toString());
		}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("noteblockIDlist")) {
			if(args.length == 1) {
				String n = args[0]; 
				if(Creation.isInteger(n)) {
					 int number = Integer.parseInt(n); 
					 if(number < 0) {
						 sender.sendMessage(ChatColor.RED + "minimum page is page 1");
					 }
					 else if(number > 46) {
						 sender.sendMessage(ChatColor.RED + "maximum page is page 46"); 
					 }
					 else {
						 if(number == 47) {
							 for(int i = 460; i < 462; i++) {
								 sender.sendMessage(ChatColor.WHITE + "ID: " + ChatColor.GREEN + i + ChatColor.WHITE + " == " + ChatColor.BLUE + SoundID.ID(i));
							 }
							 sender.sendMessage(ChatColor.GOLD + "Page " + ChatColor.GREEN + number + ChatColor.GOLD + " out of " + ChatColor.GREEN + "46");
						 }
						 else {
						 for(int i = (number-1)*10; i < ((number-1)*10)+10; i++) {
							 if(i == ((number-1)*10)+9) {
								 sender.sendMessage(ChatColor.WHITE + "ID: " + ChatColor.GREEN + i + ChatColor.WHITE + " == " + ChatColor.BLUE + SoundID.ID(i) 
										 + "  " + ChatColor.GOLD + "Page " + ChatColor.GREEN + number + ChatColor.GOLD + " out of " + ChatColor.GREEN + "46"); 
							 }
							 else {
							 sender.sendMessage(ChatColor.WHITE + "ID: " + ChatColor.GREEN + i + ChatColor.WHITE + " == " + ChatColor.BLUE + SoundID.ID(i));
							 }
						 }
						// sender.sendMessage(ChatColor.GOLD + "Page " + ChatColor.GREEN + number + ChatColor.GOLD + " out of " + ChatColor.GREEN + " 20");
					 }
					 }
				}
				else {
				sender.sendMessage(ChatColor.RED + "/noteblockIDList <page>");	
				}
			}
			else {
			sender.sendMessage(ChatColor.RED + "/noteblockIDList <page>");	
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("noteblockID")) {
			   if(args.length == 1) {
				   String n = args[0];
				   if(Creation.isInteger(n)) {
					   int number = Integer.parseInt(n);
				   if(number < 0) {
					   sender.sendMessage(ChatColor.RED + "The ID is too low. Minimum ID is " + ChatColor.WHITE + "0");
				   }
				   else if(number < SoundID.soundLength()) {
					   sender.sendMessage(ChatColor.WHITE + "ID: " + ChatColor.GREEN + n + ChatColor.WHITE + " == " + ChatColor.BLUE + SoundID.ID(number));
				   }
				   else if(number >= SoundID.soundLength()){
					   sender.sendMessage(ChatColor.RED +"The ID is too high. Maximum ID is " + ChatColor.WHITE + (SoundID.soundLength()-1));
				   }
				   }else {
					   sender.sendMessage(ChatColor.RED + "ID must be a number");
				   }
				   
			   }
				   else {
					   sender.sendMessage(ChatColor.RED + "/noteblockID <number>");   
				   }
			   
			   }
			  
		
		
		if(cmd.getName().equalsIgnoreCase("noteblocksetvolume")) {	
			if(sender instanceof Player) {
			if(sender.isOp()) {
				if(args.length == 0 || args.length >= 2) {
					sender.sendMessage(ChatColor.WHITE + "noteblocksetvolume <volume>");
				}
				else {
					String s = args[0];
					if(Creation.isFloat(s)) {
					if(Float.parseFloat(s) > 0) {
					this.getConfig().set("Global Volume", s);
					this.saveConfig();
					this.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "Volume changed to " + args[0]);
					}
					else {
						sender.sendMessage(ChatColor.RED + "Volume must be greater than 0");	
					}
					}
					else {
					sender.sendMessage(ChatColor.RED + "Volume must be a number");
					}
				}
			}
			}
		}
		
		
		if(cmd.getName().equalsIgnoreCase("noteblockinfo")) {
			sender.sendMessage(ChatColor.GOLD + "NoteBlockPlus V" + ChatColor.RED + version + ChatColor.GOLD + " - Coded by Firegred");
		}
		if(cmd.getName().equalsIgnoreCase("composerlist")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("NoteBlock.composer.list")) {
					PlayAPI.sendList(sender);
				}
			}
			else {
				PlayAPI.sendList(sender);
			}
		}
		if(cmd.getName().equalsIgnoreCase("composercreate")) {
			if(sender instanceof Player) {
			if(sender.hasPermission("NoteBlock.composer")) {
				Player p = (Player) sender;
				if(args.length == 1) {
					if(p.hasMetadata("Delete")) {
						p.removeMetadata("Delete", this);
					}
					if(p.hasMetadata("Copy")) {
						p.removeMetadata("Copy", this);	
					}
					if(p.hasMetadata("Create")) {
					p.removeMetadata("Create", this);
					p.sendMessage(ChatColor.RED + "You are no longer creating a noteblock composer");
				}
				else {
					p.setMetadata("Create", new FixedMetadataValue(this, args[0]));
					p.sendMessage(ChatColor.GREEN + "Select an empty chest");
				}
			}
				else {
					p.sendMessage(ChatColor.RED + "/composercreate <name>");
				}
			}
			}
		}
		//create command to copy noteblock composer blocks
		if(cmd.getName().equalsIgnoreCase("composercopy")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("NoteBlock.composer.copy")) {
					Player p= (Player) sender;
					if(p.hasMetadata("Delete")) {
						p.removeMetadata("Delete", this);
					}
					if(p.hasMetadata("Create")) {
						p.removeMetadata("Create", this);
					}
					if(!p.hasMetadata("Copy") && !CompInv.hasChestCopier(p)) {
						p.sendMessage(ChatColor.GREEN + "Select a Composer Chest to copy");
					    p.setMetadata("Copy", new FixedMetadataValue(this, "1"));
					}
					else {
						p.sendMessage(ChatColor.RED + "No Longer copying Composer Chest");
						if(p.hasMetadata("Copy")) {
							p.removeMetadata("Copy", this);
						}
						if(CompInv.hasChestCopier(p)) {
							CompInv.removeInventoryCopy(p);
						}
					}
				}
		}
		}
		if(cmd.getName().equalsIgnoreCase("composerdelete")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(CompInv.canDelete(p)) {
					if(p.hasMetadata("Copy")) {
						p.removeMetadata("Copy", this);	
					}
					if(p.hasMetadata("Create")) {
						p.removeMetadata("Create", this);
					}
					if(!p.hasMetadata("Delete")) {
						p.sendMessage(ChatColor.GREEN + "Destroy the desired a Composer Chest");
					    p.setMetadata("Delete", new FixedMetadataValue(this, "1"));
					}
					else {
						p.sendMessage(ChatColor.RED + "No Longer deleting Composer Chest");
						p.removeMetadata("Delete", this);
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("noteblockfastplace")) {
			if(sender instanceof Player) {
			if(sender.hasPermission("NoteBlock.fastplace")) {
				Player p = (Player) sender;
				if(place.containsKey(p) || (specialplacer.containsKey(p) && args.length == 0)){
				sender.sendMessage(ChatColor.GREEN + "Fast Placing is now Off!");
			    place.remove(p);
			    specialplacer.remove(p);
				placer.remove(p);
				}
				else if(!placer.contains(p)) {
				if(args.length == 0) {
				sender.sendMessage(ChatColor.GREEN + "Click a NoteBlock Sign!");
			    
				placer.add(p);
				}
				else  {
				getSign(p, args);
				sender.sendMessage(ChatColor.GREEN + "You can now fast place this NoteBlock Sign");
					
				}
				}
			}
			}
		}
		return false;
	}
}
