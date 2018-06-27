package me.Firegred.Treasures;

public class LootDefine {

	
	public static boolean isDefined(String g, Main m) {
		if(m.getConfig().get("Loot." + g + ".x1") == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean isOnlyAir(String g, Main m) {
		if(m.getConfig().getBoolean("Loot." + g + ".onlyAir") == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void onlyAir(String name, Main m, boolean b) {
		m.getConfig().set("Loot." + name + ".onlyAir", b);	
		m.saveConfig();
		m.reloadConfig();
	}
	
	public static void setTime(String g, Integer s, Main m) {
		m.getConfig().set("Loot." + g + ".time", s);
		//DropChest.enable.put(g, true);
		m.saveConfig();
		m.reloadConfig();
	}
	public static void enableTreasure(String g, Main m, boolean s) {
		m.getConfig().set("Loot." + g + ".enabled", s);
		DropChest.enable.put(g, true);
		m.saveConfig();
		m.reloadConfig();
	}
	public static boolean isEnabled(String g, Main m) {
		return m.getConfig().getBoolean("Loot." + g + ".enabled");
	}
	public static void setMin(String g, Main m, Integer number) {
		m.getConfig().set("Loot." + g + ".minLoot", number);
		m.saveConfig();
		m.reloadConfig();
	}
	public static void setMax(String g, Main m, Integer number) {
		m.getConfig().set("Loot." + g + ".maxLoot", number);
		m.saveConfig();
		m.reloadConfig();
	}
	public static boolean hasMin(String g, Integer value, Main m) {
		if(value <= m.getConfig().getInt("Loot." + g + ".maxLoot") ) {
			return true;
		}
		else {
		return false;
		}
	}
	public static boolean hasMax(String g, Integer value, Main m) {
		if(value >= m.getConfig().getInt("Loot." + g + ".minLoot") ) {
			return true;
		}
		else {
		return false;
		}
	}
	public static boolean hasTime(String g, Main m) {
		if(m.getConfig().getInt("Loot." + g + ".time") == 0) {
			return false;
		}
		else {
		return true;
		}
		
	}
	
}
