package me.Firegred.Snowball;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CursedAPI {

	private static HashMap<Player, Cursed> cursed = new HashMap<Player, Cursed>();
	private static HashMap<Integer, ArrayList<String>> greeting = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> OPgreeting = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> welcome = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Interruption = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Commands = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Respawn = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> DeadInterruption = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Throwing = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Saving = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Reboot = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Interaction = new HashMap<Integer, ArrayList<String>>();
	private static HashMap<Integer, ArrayList<String>> Moving = new HashMap<Integer, ArrayList<String>>();
	public static ArrayList<HashMap<Integer, ArrayList<String>>> list = new ArrayList<HashMap<Integer,ArrayList<String>>>();
	private static StringBuilder sb = new StringBuilder();
	
	public static HashMap<Player, Cursed> getCursed() {
		return cursed;
	}
	public static void clearLists() {
		greeting.clear();
		OPgreeting.clear();
		welcome.clear();
		Interruption.clear();
		Commands.clear();
		Respawn.clear();
		DeadInterruption.clear();
		Throwing.clear();
		Saving.clear();
		Reboot.clear();
		Interaction.clear();
		Moving.clear();
		list.clear();
		sb.setLength(0);
	}
	private static void defineList() {
		list.add(greeting);
		list.add(OPgreeting);
		list.add(welcome);
		list.add(Interruption);
		list.add(Commands);
		list.add(Respawn);
		list.add(DeadInterruption);
		list.add(Throwing);
		list.add(Saving);
		list.add(Reboot);
		list.add(Interaction);
		list.add(Moving);
		for(int i = 0; i < 34; i++) {
			sb.append("▉");
		}
	}
	
	public static void defineSpeech() {
		defineList();
		for(int i = 0; i < Main.getTextFile().size(); i++) {
			String g = Main.getTextFile().get(i);
			if(g.startsWith("Greeting")) {
				definePhrases(0, i+1);
			}
			if(g.startsWith("OP Greeting")) {
				definePhrases(1, i+1);
			}
			if(g.startsWith("Welcome back")) {
				definePhrases(2, i+1);
			}
			if(g.startsWith("Interruption")) {
				definePhrases(3, i+1);
			}
			if(g.startsWith("Commands")) {
				definePhrases(4, i+1);
			}
			if(g.startsWith("Respawn")) {
				definePhrases(5, i+1);
			}
			if(g.startsWith("Dead Interruption")) {
				definePhrases(6, i+1);
			}
			if(g.startsWith("Throwing")) {
				definePhrases(7, i+1);
			}
			if(g.startsWith("Saving")) {
				definePhrases(8, i+1);
			}
			if(g.startsWith("Reboot")) {
				definePhrases(9, i+1);
			}
			if(g.startsWith("Interaction")) {
				definePhrases(10, i+1);
			}
			if(g.startsWith("Moving")) {
				definePhrases(11, i+1);
			}
		}
	}
	
	private static void definePhrases(int type, int place) {
		ArrayList<String> file = Main.getTextFile();
		boolean done = false;
		ArrayList<String> phrases = new ArrayList<String>();
		int num = 0;
		while(!done) {
			if(file.get(place).startsWith("phrase")) {
				num = Integer.parseInt(file.get(place).split(" ")[1]);
				phrases.clear();
			}
			else if(file.get(place).startsWith("<done>")) {
				//list.get(type).put(num, phrases);
				done = true;
			}
			else if(file.get(place).startsWith("<end>")) {
				if(num != 0) {
					list.get(type).put(num, clone(phrases));
					//System.out.println("num" + (num) + " " + "phrases " + phrases);
				}
			}
			else {
			phrases.add(file.get(place));
			}
			place++;
			
			
		}
	}
	private static ArrayList<String> clone(ArrayList<String> target) {
		ArrayList<String> clone = new ArrayList<String>(target.size());
		for(String g : target) {
			clone.add(g);
		}
		return clone;
	}
	public static void playReadingSound(Player p) {
		p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 0.4f, 1);
	}
	
	private static String[] sentence(HashMap<Integer, ArrayList<String>> file, int phrase, int place) {
		return file.get(phrase).get(place).split(" ");		
	}
	public static void sendFrame(Player p) {
		p.sendMessage(ChatColor.RED + sb.toString());
		p.sendMessage(ChatColor.RED + sb.toString());
		p.sendMessage(ChatColor.RED + sb.toString());
	}
    
	
	
}
//int start = 3;
//int end = 47;
//int line = 4;
//int delay = 0;
//int counter = 3;
//for(String g : sentence) {
//	if(counter + g.length() > end) {
//		line = 5;
//		counter = 3;
//		for(char c : g.toCharArray()) {
//			lines[line][counter] = c;
//			counter++;
//			playReadingSound(p);
//		}
//	}
//	for(char c : g.toCharArray()) {
//		lines[line][counter] = c;
//		counter++;
//		playReadingSound(p);
//	}
//}
