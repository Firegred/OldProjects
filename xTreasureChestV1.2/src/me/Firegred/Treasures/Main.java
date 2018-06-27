package me.Firegred.Treasures;

import java.io.File;
import java.util.logging.Logger;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public class Main extends JavaPlugin{
	public static String version = "1.1";
	private static final Logger log = Logger.getLogger("Minecraft");
	private final WandListener w = new WandListener(this);
	private final ChestListener cc = new ChestListener(this);
	@Override
	public void onEnable() {
		log.info("[Treasure Chests] has been enabled!");
		File file = new File(getDataFolder() + File.separator + "config.yml");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this.w, this);
		pm.registerEvents(this.cc, this);
		if(!file.exists()) {
			this.getLogger().info("Generating config.yml...");
			
			this.getConfig().addDefault("Settings.enable", true);
			this.getConfig().addDefault("Loot", null);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			this.reloadConfig();
		}

		        if(this.getConfig().get("Loot") != null) {
				DropChest.bootUpChests(this);
				
	            }
	            BukkitTask TaskName = new DropChest(this).runTaskTimer(this, 20, 20);
		
			
	}
	@Override
	public void onDisable() {
		log.info("[Treasure Chests] has been disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("TreasureChests") || cmd.getName().equalsIgnoreCase("TC")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.BLUE + "Do /treasurechests 1 for commands");
			}
			else if(args.length == 1) {
				
				
				if(!(sender instanceof Player)) {
					if(args[0].equals("1")) {
						sender.sendMessage(ChatColor.BLUE + "Page 1 of Treasure Chests");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests wand " + ChatColor.BLUE + "- gives user wand for setting treasure regions");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests set <name> " + ChatColor.BLUE + "- sets region for treasure");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests reset <name> " + ChatColor.BLUE + "- resets region for treasure");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests remove <name> " + ChatColor.BLUE + "- removes region for treasure");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests time <name> <seconds> " + ChatColor.BLUE + "- sets respawn rate for treasure");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests enable <name>"  + ChatColor.BLUE + "- enables the treasure to drop");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests disable <name>" + ChatColor.BLUE + "- disables the treasure to drop");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests list " + ChatColor.BLUE + "- lists all treasure regions");
						sender.sendMessage(ChatColor.BLUE + "Do /treasurechests 2 for page 2");
						}
					else if(args[0].equals("2")) {
							sender.sendMessage(ChatColor.BLUE + "Page 2 of Treasure Chests");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests addItem <itemname> <region>" + ChatColor.BLUE + "- adds item from your hand to the treasure loot");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests removeItem <itemname> <region> " + ChatColor.BLUE + "- removes item name from treasure loot");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests setMinItem <itemname> <region> <number>" + ChatColor.BLUE + "- sets minimum amount of a treasure loot");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests setMaxItem <itemname> <region> <number>" + ChatColor.BLUE + "- sets maximum amount of a treasure loot");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests getTreasure <name> " + ChatColor.BLUE + "- opens inventory of treasure");	
							sender.sendMessage(ChatColor.GREEN + "/treasurechests setMinTreasure <region> <number> " + ChatColor.BLUE + "- sets min number of treasures for a loot");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests setMaxTreasure <region> <number>" + ChatColor.BLUE + "- sets max number of treasures for a loot");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests onlyAir <region> <true/false>" + ChatColor.BLUE + "- Chests can only spawn in air, not in blocks");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests setMaxChests <region> <number>" + ChatColor.BLUE + "- sets max number of chests that can exist");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests overrideChests <region> " + ChatColor.BLUE + "- Toggles if there's a chest spawn limit, new chests will replace old chests");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests reload " + ChatColor.BLUE + "- reloads plugin configuration");
							sender.sendMessage(ChatColor.GREEN + "/treasurechests version" + ChatColor.BLUE + "- Author and version of plugin");
						}
					
					else if(args[0].equalsIgnoreCase("reload")) {
						this.reloadConfig();
			        	System.out.println(ChatColor.GREEN + "TreasureChests V" + ChatColor.GREEN + version + ChatColor.GREEN + " reloaded");
					}
					else if(args[0].equalsIgnoreCase("version")) {
						System.out.println(ChatColor.GOLD + "TreasureChests V" + ChatColor.GREEN + version + ChatColor.GOLD + " Coded by " + ChatColor.RED + "Firegred");
					}
				}
				
				if(sender instanceof Player) {
		        Player p = (Player) sender;	      
		        
		        if(args[0].equals("1")) {
					sender.sendMessage(ChatColor.BLUE + "Page 1 of Treasure Chests");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests wand " + ChatColor.BLUE + "- gives user wand for setting treasure regions");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests set <name> " + ChatColor.BLUE + "- sets region for treasure");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests reset <name> " + ChatColor.BLUE + "- resets region for treasure");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests remove <name> " + ChatColor.BLUE + "- removes region for treasure");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests time <name> <seconds> " + ChatColor.BLUE + "- sets respawn rate for treasure");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests enable <name>"  + ChatColor.BLUE + "- enables the treasure to drop");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests disable <name>" + ChatColor.BLUE + "- disables the treasure to drop");
					sender.sendMessage(ChatColor.GREEN + "/treasurechests list " + ChatColor.BLUE + "- lists all treasure regions");
					sender.sendMessage(ChatColor.BLUE + "Do /treasurechests 2 for page 2");
					}
				else if(args[0].equals("2")) {
						sender.sendMessage(ChatColor.BLUE + "Page 2 of Treasure Chests");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests addItem <itemname> <region>" + ChatColor.BLUE + "- adds item from your hand to the treasure loot");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests removeItem <itemname> <region> " + ChatColor.BLUE + "- removes item name from treasure loot");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests setMinItem <itemname> <region> <number>" + ChatColor.BLUE + "- sets minimum amount of a treasure loot");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests setMaxItem <itemname> <region> <number>" + ChatColor.BLUE + "- sets maximum amount of a treasure loot");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests getTreasure <name> " + ChatColor.BLUE + "- opens inventory of treasure");	
						sender.sendMessage(ChatColor.GREEN + "/treasurechests setMinTreasure <region> <number> " + ChatColor.BLUE + "- sets min number of treasures for a loot");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests setMaxTreasure <region> <number>" + ChatColor.BLUE + "- sets max number of treasures for a loot");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests onlyAir <region> <true/false>" + ChatColor.BLUE + "- Chests can only spawn in air, not in blocks");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests setMaxChests <region> <number>" + ChatColor.BLUE + "- sets max number of chests that can exist");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests overrideChests <region> " + ChatColor.BLUE + "- Toggles if there's a chest spawn limit, new chests will replace old chests");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests reload " + ChatColor.BLUE + "- reloads plugin configuration");
						sender.sendMessage(ChatColor.GREEN + "/treasurechests version" + ChatColor.BLUE + "- Author and version of plugin");
					}  
				else if(args[0].equalsIgnoreCase("list") && p.hasPermission("TreasureChests.wand")) {
		        	if(this.getConfig().get("Loot") == null) {
		        		p.sendMessage(ChatColor.RED + "Error: You need to define at least 1 region");
		        	}
		        	else {
					StringBuilder sb = new StringBuilder();
		        	for(String g : this.getConfig().getConfigurationSection("Loot").getKeys(false)) {
		        		sb.append(g + " ");
		        		
		        	}
		        	p.sendMessage(ChatColor.GREEN + "Treasure Chests Regions: " + ChatColor.YELLOW + sb.toString());
		        	}
		        }
		        else if(args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.GOLD + "TreasureChests V" + ChatColor.GREEN + version + ChatColor.GOLD + " Coded by " + ChatColor.RED + "Firegred");
				}
		        else if(args[0].equalsIgnoreCase("reload") && p.hasPermission("TreasureChests.region")) {
		        	this.reloadConfig();
		        	p.sendMessage(ChatColor.GREEN + "TreasureChests V" + ChatColor.GREEN + version + ChatColor.GREEN + " reloaded");
		        }
		        else if(args[0].equalsIgnoreCase("wand") && p.hasPermission("TreasureChests.wand")) {
					Wand.giveWand(p);
					p.sendMessage(ChatColor.GREEN + "Use the wand to define a treasure region");
				}
				else {
					p.sendMessage(ChatColor.RED + "Invalid command. use /treasurechests 1 for valid commands");
				}
				
				}
			}
			else if(args.length == 2) {
				if(sender instanceof Player) {
			        Player p = (Player) sender;	
			        String g = args[1];			        			      
			        if(args[0].equalsIgnoreCase("getTreasure") && p.hasPermission("TreasureChests.item")) {
			        	if(!Items.hasTreasure(g, this)) {
			        		p.sendMessage(ChatColor.RED + "Error: This region has no treasure");
			        	}
			        	else {
			        		Chests.OpenTreasure(p, g, this);
			        	}
			        }
			        else if(args[0].equalsIgnoreCase("enable") && p.hasPermission("TreasureChests.region")) {
			          if(!LootDefine.isEnabled(g, this)) {
			        	  if(LootDefine.hasTime(g, this)) {		        	  
			        	  LootDefine.enableTreasure(g, this, true);
			        	  DropChest.enableChests(this, g);
			        	  p.sendMessage(ChatColor.YELLOW + g + ChatColor.GREEN + " has been enabled!");
			        	  }
			        	  else{
			        		  p.sendMessage(ChatColor.RED + "Error: You need to set a respawn time for this region");
			        	  }
			          }
			          else{
			        	  p.sendMessage(ChatColor.RED + "Error: This treasure region is already enabled");
			          }
			        }
			        else if(args[0].equalsIgnoreCase("disable") && p.hasPermission("TreasureChests.region")) {
			        	 if(LootDefine.isEnabled(g, this)) {
				        	  LootDefine.enableTreasure(g, this, false);
				        	  DropChest.disableChests(this, g);
				        	  p.sendMessage(ChatColor.YELLOW + g + ChatColor.GREEN + " has been disabled!");
				          }
				          else{
				        	  p.sendMessage(ChatColor.RED + "Error: This treasure region is already disabled");
				          }
			        }
			        else if(args[0].equalsIgnoreCase("remove") && p.hasPermission("TreasureChests.region")) {
			        	if(this.getConfig().get("Loot." + g + ".x1") != null){
			        	Wand.removeRegion(g, this);
			        	DropChest.removeRegionTime(g);
			        	p.sendMessage(ChatColor.YELLOW + g + ChatColor.RED + " has been removed!");
			        	}
			        	else{
			        		p.sendMessage(ChatColor.RED + "Error: region doesn't exist");
			        	}
			        	
			        }
			        else  if(args[0].equalsIgnoreCase("set") && p.hasPermission("TreasureChests.wand")) {
			        	
			        	if(this.getConfig().get("Loot." + g + ".x1") == null) {
			        		if(Wand.hasP1(p) && Wand.hasP2(p)) {
			        		    Wand.setRegion(g, this, p);
			        			p.sendMessage(ChatColor.YELLOW + g + ChatColor.GREEN + " has been defined!");
			        		   
			        		}
			        		else {
			        			p.sendMessage(ChatColor.RED + "Error: You did not properly define the region with the wand");
			        		}
			        	}
			        	else {
			        	 p.sendMessage(ChatColor.RED + "Error: This region already exists");
			        	}
			        }
			        else if(args[0].equalsIgnoreCase("reset") && p.hasPermission("TreasureChests.wand")) {
			        	if(this.getConfig().get("Loot." + g + ".x1") != null) {
			        		if(Wand.hasP1(p) && Wand.hasP2(p)) {
			        		    Wand.resetRegion(g, this, p);
			        			p.sendMessage(ChatColor.YELLOW + g + ChatColor.GREEN + " has been redefined!");
			        		   
			        		}
			        		else {
			        			p.sendMessage(ChatColor.RED + "Error: You did not properly define the region with the wand");
			        		}
			        	}
			        	else {
			        	 p.sendMessage(ChatColor.RED + "Error: This region does not exist");
			        	}
			        }
			        else {
						p.sendMessage(ChatColor.RED + "Invalid command. use /treasurechests 1 for valid commands");
					}
				}
			
			}
			else if(args.length == 3) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					String g1 = args[1];
					String g2 = args[2];
					
					  if(args[0].equalsIgnoreCase("onlyAir") && p.hasPermission("TreasureChests.region")) {
				        	if(LootDefine.isDefined(g1, this)) {
				        		if(g2.equalsIgnoreCase("true")) {
				        			LootDefine.onlyAir(g1, this, true);
				        			p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " will now replace only air for treasure chests!");
				        		}
				        		else if(g2.equalsIgnoreCase("false")) {
				        			LootDefine.onlyAir(g1, this, false);
				        			p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " will now replace any block for treasure chests!");
				        		}
				        		else {
				        			p.sendMessage(ChatColor.RED + "Error: Value must be either true or false");
				        		}
				        	}
				        	else{
				        		p.sendMessage(ChatColor.RED + "Error: The region does not exist");
				        	}
				        }
					
					else if(args[0].equalsIgnoreCase("removeItem") && p.hasPermission("TreasureChests.item")) {
						if(Items.itemExists(g1, g2, this)) {
							Items.removeItem(g1, g2, this);
							Items.MaxMinCorrection(g2, this);
							p.sendMessage(ChatColor.GREEN + "The item " + ChatColor.RED + g1 + ChatColor.GREEN + " has been removed from region " + ChatColor.YELLOW + g2);
						}
						else{
							p.sendMessage(ChatColor.RED + "Error: The region/item name does not exist");
						}
					}
					
					else if(args[0].equalsIgnoreCase("addItem") && p.hasPermission("TreasureChests.item")) {
						if(LootDefine.isDefined(g2, this)) {
						if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
							p.sendMessage(ChatColor.RED + "Error: You cannot have Air as a treasure");
						}
						else if(!Items.hasTreasure(g2, this)) {
							 Items.addItem(g1, g2, p.getItemInHand(), this);
							 p.sendMessage(ChatColor.GREEN + "The item from your hand has been added to " + ChatColor.YELLOW + g2);
						}
						
						else if(Items.ItemCount(g2, this) >= 27) {
							p.sendMessage(ChatColor.RED + "Error: You cannot add more items to this treasure region");
						}
						else if(!Items.itemExists(g1, g2, this)) {
							 Items.addItem(g1, g2, p.getItemInHand(), this);
							 p.sendMessage(ChatColor.GREEN + "The item from your hand has been added to " + ChatColor.YELLOW + g2);
						 }
						 else{
							 p.sendMessage(ChatColor.RED + "Error: That item name exists");
						 }
						}
						else {
							p.sendMessage(ChatColor.RED + "Error: Region doesn't exist");
						}
					 }
					
					else if(args[0].equalsIgnoreCase("setMinTreasure") && p.hasPermission("TreasureChests.region")) {
				            if(this.getConfig().get("Loot." + g1 + ".x1") != null) {
				            	try { 
							        int c = Math.abs(Integer.parseInt(g2)); 
							        if(c >= 0 && c < 28) {
							        if(Items.ItemCount(g1, this) >= c) {
							        if(LootDefine.hasMin(g1, c, this)) {
							        LootDefine.setMin(g1, this, c);
							        p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " has a min number loot of: " + ChatColor.YELLOW + c);
							        }
							        else {
							        	p.sendMessage(ChatColor.RED + "Error: Min must be less than or equal to Max");
							        }
							        }
							        else {
							        	p.sendMessage(ChatColor.RED + "Error: Min count must be less than or equal to treasure count");
							        }
							        }
							        else {
							        	p.sendMessage(ChatColor.RED + "Error: Loot number must be between 0 and 27");
							        }
							    } catch(NumberFormatException e) { 
							        p.sendMessage(ChatColor.RED + "Error: You entered an invalid number");
							    	return false; 
							    }
				            }
				        }
					else if(args[0].equalsIgnoreCase("setMaxTreasure") && p.hasPermission("TreasureChests.region")) {
	                    	if(this.getConfig().get("Loot." + g1 + ".x1") != null) {
				            	try { 
							        int c = Math.abs(Integer.parseInt(g2)); 
							        if(c >= 0 && c < 28) {
							        if(Items.ItemCount(g1, this) >= c) {
							        if(LootDefine.hasMax(g1, c, this)) {
							        LootDefine.setMax(g1, this, c);
							        p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " has a max number loot of: " + ChatColor.YELLOW + c);
							        }
							        else {
							        	p.sendMessage(ChatColor.RED + "Error: Max must be greater or equal to Min");
							        }
							        }
							        else {
							        	p.sendMessage(ChatColor.RED + "Error: Max count must be less than or equal to treasure count");
							        }
							        }
							        else {
							        	p.sendMessage(ChatColor.RED + "Error: Loot number must be between 0 and 27");
							        }
							    } catch(NumberFormatException e) { 
							        p.sendMessage(ChatColor.RED + "Error: You entered an invalid number");
							    	return false; 
							    }
				            }
				        }
					//in future, make time not allow 0 seconds
					else if(args[0].equalsIgnoreCase("time") && p.hasPermission("TreasureChests.region")) {
						if(this.getConfig().get("Loot." + g1 + ".x1") != null) {
							try { 
						        int c = Math.abs(Integer.parseInt(g2)); 
						        if(c != 0) {
						        LootDefine.setTime(g1, c, this);
						        DropChest.setRegionTime(g1, c);
						        p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " has a respawn rate of: " + ChatColor.YELLOW + c + ChatColor.YELLOW + " seconds");
						        }
						        else {
						        	p.sendMessage(ChatColor.RED + "Error: The respawn time cannot be 0");
						        }
						    } catch(NumberFormatException e) { 
						        p.sendMessage(ChatColor.RED + "Error: You entered an invalid time");
						    	return false; 
						    }
							
							
						}
						else{
							p.sendMessage(ChatColor.RED + "Error: region doesn't exist");
						}
					}
					else {
						p.sendMessage(ChatColor.RED + "Invalid command. use /treasurechests 1 for valid commands");
					}
				}
			}
			else if(args.length == 4) {
				if(sender instanceof Player) {
				Player p = (Player) sender;
				String g1 = args[1];
				String g2 = args[2];
				String g3 = args[3];
				if(args[0].equalsIgnoreCase("setMinItem") && p.hasPermission("TreasureChests.item")) {
				    if(Items.itemExists(g1, g2, this)) {
				    	try { 
					        int c = Math.abs(Integer.parseInt(g3)); 
					        if(c >= 1 && c < 65) {
					        if(Items.hasMin(g1, g2, c, this)) {
					        Items.setMin(g1, g2, c, this);
					        p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " has a max number loot of: " + ChatColor.YELLOW + c);
					        }
					        else {
					        p.sendMessage(ChatColor.RED + "Error: Min must be less than or equal to Max");
					        }
					        }
					        else {
					        	p.sendMessage(ChatColor.RED + "Error: Item number must be between 1 and 64");
					        }
					    } catch(NumberFormatException e) { 
					        p.sendMessage(ChatColor.RED + "Error: You entered an invalid number");
					    	return false; 
					    }
				    }
				    else {
				    	p.sendMessage(ChatColor.RED + "Error: This item doesn't exist in this region");
				    }
				 }
				else if(args[0].equalsIgnoreCase("setMaxItem") && p.hasPermission("TreasureChests.item")) {
				    if(Items.itemExists(g1, g2, this)) {
				    	try { 
					        int c = Math.abs(Integer.parseInt(g3)); 
					        if(c >= 1 && c < 65) {
					        if(Items.hasMax(g1, g2, c, this)) {
					        Items.setMax(g1, g2, c, this);
					        p.sendMessage(ChatColor.YELLOW + g1 + ChatColor.GREEN + " has a max number loot of: " + ChatColor.YELLOW + c);
					        }
					        else {
					        	p.sendMessage(ChatColor.RED + "Error: Max must be greater or equal to Min");
					        }
					        }
					        else {
					        	p.sendMessage(ChatColor.RED + "Error: Item number must be between 1 and 64");
					        }
					    } catch(NumberFormatException e) { 
					        p.sendMessage(ChatColor.RED + "Error: You entered an invalid number");
					    	return false; 
					    }
				    }
				    else {
				    	p.sendMessage(ChatColor.RED + "Error: This item doesn't exist in this region");
				    }
				 }
				else {
					p.sendMessage(ChatColor.RED + "Invalid command. use /treasurechests 1 for valid commands");
				}	
				
				}
			}
			//sender.sendMessage(this.getConfig().getString("message"));
		}
		return false;
		
	}

}
