package me.Firegred.NoteBlockComposer;

import java.util.ArrayList;

import me.Firegred.NoteBlock.Reflection;
import me.Firegred.NoteBlock.SoundID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class ComposerSelection {
	private Inventory p1;
	private Inventory p2;
	private Inventory p3;
	private Inventory p4;
	private Inventory p5;
	private Inventory p6;
	private Inventory p7;
	private Inventory p8;
	private Inventory p9;
	
	private Inventory notes1;
	private Inventory options;
	ItemStack notes = new ItemStack(Material.NOTE_BLOCK, 1);
	ItemMeta notesMeta = notes.getItemMeta();
	ItemStack backpage = new ItemStack(Material.GOLD_BLOCK, 1);
	ItemMeta backpageMeta = backpage.getItemMeta();
	ItemStack book = new ItemStack(Material.BOOK, 1);
	ItemMeta bookMeta = book.getItemMeta();
	ItemStack protect = new ItemStack(Material.FIREBALL, 1);
	ItemMeta protectMeta = protect.getItemMeta();
	ItemStack nextpage = new ItemStack(Material.DIAMOND_BLOCK, 1);
	ItemMeta nextpageMeta = nextpage.getItemMeta();
	
	ItemStack tuneLock = new ItemStack(Material.TRIPWIRE_HOOK,1);
	ItemMeta tunelockmeta = tuneLock.getItemMeta();
	
	ItemStack chooseSounds = new ItemStack(Material.NOTE_BLOCK, 1);
	ItemStack choosePitches = new ItemStack(Material.WOOL,1);
	ItemStack delete = new ItemStack(Material.REDSTONE_BLOCK);
	
	ItemMeta chooseSoundsMeta = chooseSounds.getItemMeta();
	ItemMeta choosePitchesMeta = choosePitches.getItemMeta();
	ItemMeta deleteMeta = delete.getItemMeta();
	
	ItemStack shortcut1 = new ItemStack(160,1,(short)9);
	ItemStack shortcut2 = new ItemStack(160,1,(short)9);
	ItemStack shortcut3 = new ItemStack(160,1,(short)9);
	ItemStack shortcut4 = new ItemStack(160,1,(short)9);
	ItemStack shortcut5 = new ItemStack(160,1,(short)9);
	ItemStack shortcut6 = new ItemStack(160,1,(short)9);
	
	ItemMeta shortcut1m = shortcut1.getItemMeta();
	ItemMeta shortcut2m = shortcut2.getItemMeta();
	ItemMeta shortcut3m = shortcut3.getItemMeta();
	ItemMeta shortcut4m = shortcut4.getItemMeta();
	ItemMeta shortcut5m = shortcut5.getItemMeta();
	ItemMeta shortcut6m = shortcut6.getItemMeta();
	
	private EntityType[] monsterType = {EntityType.CREEPER,EntityType.SKELETON,EntityType.SPIDER,EntityType.ZOMBIE,EntityType.SLIME,EntityType.GHAST,EntityType.PIG_ZOMBIE,EntityType.ENDERMAN,
			EntityType.CAVE_SPIDER,EntityType.SILVERFISH,EntityType.BLAZE,EntityType.MAGMA_CUBE,EntityType.BAT,EntityType.WITCH,EntityType.ENDERMITE,EntityType.GUARDIAN,
			EntityType.SHULKER,EntityType.PIG,EntityType.SHEEP,EntityType.COW,EntityType.CHICKEN,EntityType.SQUID,EntityType.WOLF,EntityType.MUSHROOM_COW,EntityType.OCELOT,
			EntityType.HORSE,EntityType.RABBIT,EntityType.VILLAGER};
	private short[] monsterID = {50,51,52,54,55,56,57,58,59,60,61,62,65,66,67,68,69,90,91,92,93,94,95,96,98,100,101,120};
	private String[] monsters = {"Creeper","Skeleton","Spider","Zombie","Slime","Ghast","Pigman","Enderman",
			"CaveSpider","Silverfish","Blaze","MagmaCube","Bat","Witch","Endermite","Guardian",
			"Shulker","Pig","Sheep","Cow","Chicken","Squid","Wolf","Mooshroom","Ocelot",
			"Horse","Rabbit","Villager"};
	private int[] blacklist = {0,8,9,10,11,26,34,51,55,64,71,90,92,93,94,117,119,127,132,141,142,207,209};
	public ComposerSelection() {
		p1 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 1)");
		p2 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 2)");
		p3 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 3)");
		p4 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 4)");
		p5 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 5)");
		p6 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 6)");
		p7 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 7)");
		p8 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 8)");
		p9 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select a sound (page 9)");
		
		notes1 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Select Pitch");
		options = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Note Options");
		define();
		definePitches();
		definePages();
		optionpage();
	}
	public void OpenPitches(Player p) {
		p.openInventory(notes1);
	}
	public void OpenPage1(Player p) {
		p.openInventory(p1);
	}
	public void OpenPage2(Player p) {
		p.openInventory(p2);
	}
	public void OpenPage3(Player p) {
		p.openInventory(p3);
	}
	public void OpenPage4(Player p) {
		p.openInventory(p4);
	}
	public void OpenPage5(Player p) {
		p.openInventory(p5);
	}
	public void OpenPage6(Player p) {
		p.openInventory(p6);
	}
	public void OpenPage7(Player p) {
		p.openInventory(p7);
	}
	public void OpenPage8(Player p) {
		p.openInventory(p8);
	}
	public void OpenPage9(Player p) {
		p.openInventory(p9);
	}
	public void OpenOptions(Player p) {
		p.openInventory(options);
	}
	private void optionpage() {
		options.addItem(chooseSounds);
		options.addItem(choosePitches);
		options.addItem(delete);
		options.addItem(shortcut1);
		options.addItem(shortcut2);
		options.addItem(shortcut3);
		options.addItem(shortcut4);
		//options.addItem(shortcut5);
		options.addItem(shortcut6);
		ItemStack back = new ItemStack(Material.GOLD_BLOCK, 1);
		ItemMeta backmeta = back.getItemMeta();
		backmeta.setDisplayName(ChatColor.GOLD + "Back to Composer");
		back.setItemMeta(backmeta);
		options.addItem(back);
		
		
	}
	private void page4() {
		p4.setItem(35, backpage);
	}
	private void page3() {
		p3.setItem(52, backpage);
		p3.setItem(53, nextpage);
	}
	
	private void page2() {
		p2.setItem(52, backpage);
		p2.setItem(53, nextpage);
	}
	
	private void page1() {
		p1.addItem(nextpage);
	}
	
	private ItemStack defineItem(String S, int id) {
		ChatColor c;
		ItemStack item = new ItemStack(Material.FLINT,1);
		String idd = ChatColor.GREEN + "#" + ChatColor.YELLOW;
		c = ChatColor.GOLD;
		boolean found=false;
		if(S.contains("NOTE") || S.contains("MUSIC")) {
			item = new ItemStack(Material.NOTE_BLOCK,1);
			c = ChatColor.AQUA;
		}
		else if(S.contains("RECORD")) {
			item = new ItemStack(Material.GREEN_RECORD,1);
			c = ChatColor.LIGHT_PURPLE;
		}
		else if(S.startsWith("ENTITY")) {
			for(int i = 256; i < 383;i++) {
				ItemStack temp = new ItemStack(i,1);
				if(S.contains(temp.getType().toString().split("_")[0])) {
					item = temp;
					found=true;
				}
			}
			for(int i = 0; i < monsters.length; i++) {
				String m = monsters[i].toUpperCase();
				if(S.contains(m)) {
					item = Reflection.setMonsterEggType(new ItemStack(Material.MONSTER_EGG,1), monsterType[i]);
					found=true;
				}
			}
			if(S.contains("ARMORSTAND")) {
				item = new ItemStack(Material.ARMOR_STAND,1);
				found=true;
			}
			else if(S.contains("CAT")) {
				item = Reflection.setMonsterEggType(new ItemStack(Material.MONSTER_EGG,1), EntityType.OCELOT);
				found=true;
			}
			else if(S.contains("FIREWORK")) {
				item = new ItemStack(Material.FIREWORK,1);
				found=true;
			}
			else if(S.contains("EXPERIENCE")) {
				item = new ItemStack(Material.EXP_BOTTLE,1);
				found=true;
			}
			else if(S.contains("ITEMFRAME")) {
				item = new ItemStack(Material.ITEM_FRAME,1);
				found=true;
			}
			else if(S.contains("MULE")) {
				item = Reflection.setMonsterEggType(new ItemStack(Material.MONSTER_EGG,1), EntityType.HORSE);
				found=true;
			}
			else if(S.contains("PLAYER")) {
				item = new ItemStack(Material.SKULL_ITEM,1,(short)SkullType.PLAYER.ordinal());
				found=true;
			}
			else if(S.contains("HOSTILE")) {
				item = new ItemStack(Material.SKULL_ITEM,1,(short)SkullType.SKELETON.ordinal());
				found=true;
			}
			else if(S.contains("WITHER")) {
				item = new ItemStack(Material.SKULL_ITEM,1,(short)SkullType.WITHER.ordinal());
				found=true;
			}
			else if(S.contains("ENDERDRAGON")) {
				item = new ItemStack(Material.SKULL_ITEM,1,(short)SkullType.DRAGON.ordinal());
				found=true;
			}
			if(!found) item = new ItemStack(Material.MONSTER_EGG, 1);	
			//if(!found) {
			//	item = new ItemStack(Material.MONSTER_EGG, 1);	
			//}
			c = ChatColor.RED;
		}
		else if(S.startsWith("BLOCK")) {
			for(int i = 0; i < 256; i++) {
				boolean invalid = false;
				for(int y = 0; y < blacklist.length; y++) {
					if(blacklist[y]==i) {
						invalid=true;
					}
				}
				if(!invalid) {
				ItemStack temp = new ItemStack(i,1);
				if(S.contains(temp.getType().toString())) {
					item=temp;
					found=true;
				}
				}
			}
			if(S.contains("TRIPWIRE")) {
				item = new ItemStack(Material.TRIPWIRE_HOOK,1);
				found=true;
			}
			else if(S.contains("CLOTH")) {
				item = new ItemStack(Material.WOOL,1);
				found=true;
			}
			else if(S.contains("BREWING")) {
				item = new ItemStack(Material.BREWING_STAND_ITEM,1);
				found=true;
			}
			else if(S.contains("BREWING")) {
				item = new ItemStack(Material.BREWING_STAND_ITEM,1);
				found=true;
			}
			else if(S.contains("IRON_DOOR")) {
				item = new ItemStack(Material.IRON_DOOR,1);
				found=true;
			}
			else if(S.contains("BREWING")) {
				item = new ItemStack(Material.BREWING_STAND_ITEM,1);
				found=true;
			}
			else if(S.contains("PISTON")) {
				item = new ItemStack(Material.PISTON_BASE,1);
				found=true;
			}
			else if(S.contains("LAVA") || S.contains("FIRE")) {
				item = new ItemStack(Material.LAVA_BUCKET,1);
				found=true;
			}
			else if(S.contains("SLIME")) {
				item = new ItemStack(Material.SLIME_BLOCK,1);
				found=true;
			}
			else if(S.contains("WATER")) {
				item = new ItemStack(Material.WATER_BUCKET,1);
				found=true;
			}
			if(!found) {
			item = new ItemStack(Material.BEDROCK, 1);
			}
			c = ChatColor.GREEN;
		}
		else if(S.startsWith("ITEM")) {
			for(int i = 256; i < 449; i++) {
				ItemStack temp = new ItemStack(i,1);
				if(S.contains(temp.getType().toString())) {
					item=temp;
					found=true;
				}
			}
			if(!found) {
			item = new ItemStack(Material.STICK,1);
			}
			c = ChatColor.YELLOW;
		}
		S = idd+String.valueOf(id) + " "+c+S;
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(S);
		item.setItemMeta(meta);
		return item;
		
	}
	private void definePages() {
		String S;
		for(int i = 0; i < 53; i++) {
			S = Sound.values()[i].toString();
			p1.addItem(new ItemStack(defineItem(S,i)));
		}
		p1.setItem(53, nextpage);
		for(int i = 53; i < 105; i++) {
			S = Sound.values()[i].toString();
			p2.addItem(new ItemStack(defineItem(S,i)));
		}
		p2.setItem(52, backpage);
		p2.setItem(53, nextpage);
		for(int i = 105; i < 157; i++) {
			S = Sound.values()[i].toString();
			p3.addItem(new ItemStack(defineItem(S,i)));
		}
		p3.setItem(52, backpage);
		p3.setItem(53, nextpage);
		for(int i = 157; i < 209; i++) {
			S = Sound.values()[i].toString();
			p4.addItem(new ItemStack(defineItem(S,i)));
		}
		p4.setItem(52, backpage);
		p4.setItem(53, nextpage);
		for(int i = 209; i < 261; i++) {
			S = Sound.values()[i].toString();
			p5.addItem(new ItemStack(defineItem(S,i)));
		}
		p5.setItem(52, backpage);
		p5.setItem(53, nextpage);
		for(int i = 261; i < 313; i++) {
			S = Sound.values()[i].toString();
			p6.addItem(new ItemStack(defineItem(S,i)));
		}
		p6.setItem(52, backpage);
		p6.setItem(53, nextpage);
		for(int i = 313; i < 365; i++) {
			S = Sound.values()[i].toString();
			p7.addItem(new ItemStack(defineItem(S,i)));
		}
		p7.setItem(52, backpage);
		p7.setItem(53, nextpage);
		for(int i = 365; i < 417; i++) {
			S = Sound.values()[i].toString();
			p8.addItem(new ItemStack(defineItem(S,i)));
		}
		p8.setItem(52, backpage);
		p8.setItem(53, nextpage);
		for(int i = 417; i < 462; i++) {
			S = Sound.values()[i].toString();
			p9.addItem(new ItemStack(defineItem(S,i)));
		}
		p9.setItem(45, backpage);
	}
	private void definePitches() {
		int sharp = 0;
		int natural = 0;
		for(int i = 0; i < 54; i++) {
			if(i == 0 ||i == 1 ||i == 2 ||i == 4 ||i == 5 ||i == 7 || i == 8) {
				ItemStack Sharp = new ItemStack(Material.WOOL, 1, (short) 15);
				ItemMeta Sharpmeta = Sharp.getItemMeta();
				Sharpmeta.setDisplayName(ChatColor.YELLOW + "Sharp: " + ChatColor.WHITE + SoundID.getSharp(i));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.YELLOW +SoundID.getSharpNote(sharp));
				Sharpmeta.setLore(lore);
				Sharp.setItemMeta(Sharpmeta);
				notes1.setItem(i, Sharp);
				sharp++;
			}
			if((i >= 10 && i <= 17)) {
				//int c = i-10;
				ItemStack Natural = new ItemStack(Material.WOOL, 1);
				ItemMeta Naturalmeta = Natural.getItemMeta();
				Naturalmeta.setDisplayName(ChatColor.GREEN+ "Natural: " + ChatColor.WHITE + SoundID.getNormal(i));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GREEN +SoundID.getNormalNote(natural));
				Naturalmeta.setLore(lore);
				Natural.setItemMeta(Naturalmeta);
				notes1.setItem(i, Natural);
				natural++;
			}
			if(i == 53) {
				ItemStack back = new ItemStack(Material.REDSTONE_BLOCK, 1);
				ItemMeta backmeta = back.getItemMeta();
				backmeta.setDisplayName(ChatColor.RED + "Cancel selection");
				back.setItemMeta(backmeta);
				notes1.setItem(i,back);
			}
			if(i == 27 || i == 29 || i == 30 || i == 32) {
				//int c = i-18;
				ItemStack Sharp = new ItemStack(Material.WOOL, 1, (short) 15);
				ItemMeta Sharpmeta = Sharp.getItemMeta();
				Sharpmeta.setDisplayName(ChatColor.YELLOW + "Sharp: " + ChatColor.WHITE + SoundID.getSharp(i));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.YELLOW +SoundID.getSharpNote(sharp));
				Sharpmeta.setLore(lore);
				Sharp.setItemMeta(Sharpmeta);
				notes1.setItem(i,Sharp);
				sharp++;
			}
			if(i >= 36 && i <= 41) {
				//int c = i-28;
				ItemStack Natural = new ItemStack(Material.WOOL, 1);
				ItemMeta Naturalmeta = Natural.getItemMeta();
				Naturalmeta.setDisplayName(ChatColor.GREEN+ "Natural: " + ChatColor.WHITE + SoundID.getNormal(i));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GREEN + SoundID.getNormalNote(natural));
				Naturalmeta.setLore(lore);
				Natural.setItemMeta(Naturalmeta);
				notes1.setItem(i, Natural);
				natural++;
			}
	}
	}
	
	private void define() {
		
		notesMeta.setDisplayName(ChatColor.GOLD + "Advanced Pitch Options");
		notes.setItemMeta(notesMeta);
		backpageMeta.setDisplayName(ChatColor.GOLD + "Previous page");
		backpage.setItemMeta(backpageMeta);
		bookMeta.setDisplayName(ChatColor.GOLD + "Plugin information");
		book.setItemMeta(bookMeta);
		protectMeta.setDisplayName(ChatColor.GOLD + "Protect/Unprotect this sign/noteblock");
		protect.setItemMeta(protectMeta);
		nextpageMeta.setDisplayName(ChatColor.GOLD + "Next Page");
		nextpage.setItemMeta(nextpageMeta);
		
		chooseSoundsMeta.setDisplayName(ChatColor.YELLOW + "Choose new Sound");
		choosePitchesMeta.setDisplayName(ChatColor.GREEN + "Change pitch");
		deleteMeta.setDisplayName(ChatColor.RED + "Delete Note");
		chooseSounds.setItemMeta(chooseSoundsMeta);
		choosePitches.setItemMeta(choosePitchesMeta);
		delete.setItemMeta(deleteMeta);
		
		shortcut1m.setDisplayName(ChatColor.AQUA + "Shortcut 1: " + ChatColor.YELLOW + "left-click");
		shortcut2m.setDisplayName(ChatColor.AQUA + "Shortcut 2: " + ChatColor.YELLOW + "middle-click");
		shortcut3m.setDisplayName(ChatColor.AQUA + "Shortcut 3: " + ChatColor.YELLOW + "right-click");
		shortcut4m.setDisplayName(ChatColor.AQUA + "Shortcut 4: " + ChatColor.YELLOW + "shift + left-click");
		shortcut5m.setDisplayName(ChatColor.AQUA + "Shortcut 5: " + ChatColor.YELLOW + "shift + middle-click");
		shortcut6m.setDisplayName(ChatColor.AQUA + "Shortcut 5: " + ChatColor.YELLOW + "shift + right-click");
		ArrayList<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.GREEN + "Gets you to this menu");
		shortcut1m.setLore(lore);
	    shortcut1.setItemMeta(shortcut1m);
	    lore.clear();
	    
	    lore.add(ChatColor.GREEN + "Copies the note selected");
		shortcut2m.setLore(lore);
	    shortcut2.setItemMeta(shortcut2m);
	    lore.clear();
	    
	    lore.add(ChatColor.GREEN + "Pastes the note selected");
	  	shortcut3m.setLore(lore);
	    shortcut3.setItemMeta(shortcut3m);
	    lore.clear();
	    
	    lore.add(ChatColor.GREEN + "Gets you to Sound Picker");
	  	shortcut4m.setLore(lore);
	    shortcut4.setItemMeta(shortcut4m);
	    lore.clear();
	    
	    lore.add(ChatColor.GREEN + "Gets you to Pitch Picker");
	  	shortcut5m.setLore(lore);
	    shortcut5.setItemMeta(shortcut5m);
	    lore.clear();
	    
	    //lore.add(ChatColor.GREEN + "Deletes the note");
	    lore.add(ChatColor.GREEN + "Gets you to Pitch Picker");
	  	shortcut6m.setLore(lore);
	    shortcut6.setItemMeta(shortcut6m);
	    lore.clear();
	
	}
//	public void soundSelector(Plugin pl, Player p, Sign sign) {
//		p.setMetadata("SignX", new FixedMetadataValue(pl, sign.getLocation().getBlockX()));
//		p.setMetadata("SignY", new FixedMetadataValue(pl, sign.getLocation().getBlockY()));
//		p.setMetadata("SignZ", new FixedMetadataValue(pl, sign.getLocation().getBlockZ()));
//	}
//	public Block getSelectSign(Plugin pl, Player p) {
//		int x = p.getMetadata("SignX").get(0).asInt();
//		int y = p.getMetadata("SignY").get(0).asInt();
//		int z = p.getMetadata("SignZ").get(0).asInt();
//		Location location = new Location(p.getWorld(), x, y, z);
//		Block block = p.getWorld().getBlockAt(location);
//		return block;
//	}
}

