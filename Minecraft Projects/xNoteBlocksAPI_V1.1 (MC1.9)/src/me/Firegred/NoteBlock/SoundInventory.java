package me.Firegred.NoteBlock;

import java.util.ArrayList;

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
import org.bukkit.plugin.java.JavaPlugin;

public class SoundInventory {
	ItemStack notes = new ItemStack(Material.NOTE_BLOCK, 1);
	ItemMeta notesMeta = notes.getItemMeta();
	ItemStack backpage = new ItemStack(Material.GOLD_BLOCK, 1);
	ItemMeta backpageMeta = backpage.getItemMeta();
	ItemStack book = new ItemStack(Material.BOOK, 1);
	ItemMeta bookMeta = book.getItemMeta();
	ItemStack protect = new ItemStack(Material.FIREBALL, 1);
	ItemMeta protectMeta = protect.getItemMeta();
	ItemStack tuneLock = new ItemStack(Material.TRIPWIRE_HOOK,1);
	ItemMeta tunelockmeta = tuneLock.getItemMeta();
	ItemStack nextpage = new ItemStack(Material.DIAMOND_BLOCK, 1);
	ItemMeta nextpageMeta = nextpage.getItemMeta();
	
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
	public SoundInventory() {
		p1 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 1)");
		p2 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 2)");
		p3 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 3)");
		p4 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 4)");
		p5 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 5)");
		p6 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 6)");
		p7 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 7)");
		p8 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 8)");
		p9 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Pick a sound (page 9)");
		notes1 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Advanced Pitch Options");
		define();
		definePitches();
		definePages();
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
		p9.setItem(45, book);
		p9.setItem(46, notes);
		p9.setItem(47, protect);
		p9.setItem(48, tuneLock);
		p9.setItem(49, backpage);
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
				ItemStack back = new ItemStack(Material.GOLD_BLOCK, 1);
				ItemMeta backmeta = back.getItemMeta();
				backmeta.setDisplayName(ChatColor.GOLD + "Back to Sounds");
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
		tunelockmeta.setDisplayName(ChatColor.WHITE +"Tune lock/unlock this sign");
		tuneLock.setItemMeta(tunelockmeta);

	}
	public void soundSelector(Plugin pl, Player p, Sign sign) {
		p.setMetadata("SignX", new FixedMetadataValue(pl, sign.getLocation().getBlockX()));
		p.setMetadata("SignY", new FixedMetadataValue(pl, sign.getLocation().getBlockY()));
		p.setMetadata("SignZ", new FixedMetadataValue(pl, sign.getLocation().getBlockZ()));
	}
	public Block getSelectSign(Plugin pl, Player p) {
		int x = p.getMetadata("SignX").get(0).asInt();
		int y = p.getMetadata("SignY").get(0).asInt();
		int z = p.getMetadata("SignZ").get(0).asInt();
		Location location = new Location(p.getWorld(), x, y, z);
		Block block = p.getWorld().getBlockAt(location);
		return block;
	}
}
