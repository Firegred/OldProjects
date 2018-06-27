package me.Firegred.Snowball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Cursed extends BukkitRunnable{

	private Player p;
	//private char[][] lines;
	private int length,sentencecounter,phrase,pausecounter;
	private boolean talk;
	private int move,interact,throwing,commands, counter, charcounter, withercounter;
	private int textspeed;
	private StringBuilder sb1;
	private StringBuilder sb2;
	private boolean nextline,pause,end,name;
	private String names;
	public boolean greeting,OP,welcome,Interruption,Commands,Respawn,DeadInterruption,
	Throwing,Saving,Reboot,Interaction,Moving,error;
	public HashMap<Integer, ArrayList<String>> list;
	public Cursed(Player p, int l) {
		this.p = p;
//		lines = new char[2][50];
		//defineStructure();
		//list = CursedAPI.list.get(Integer.parseInt(p.getMetadata("List").get(0).asString()));
		try {
			this.list = CursedAPI.list.get(l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ArrayList<String> ll = new ArrayList<String>();
			ll.add(" ");
			ll.add(" ");
			for(int i = 1; i < 15; i++) {
				list.put(i, ll);
			}
		}
		chooseRandomPhrase();
		talk = true;
		nextline = false;
		pause = false;
		end = false;
		name=false;
		move = 0;
		interact = 0;
		throwing = 0;
		commands = 0;
		counter = 0;
		charcounter = 0;
		error = false;
		sentencecounter=0;
		length = 50;
		textspeed = 1;
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
		defineSpaces();
		pausecounter=0;
		//System.out.println("The phrase should be number: " + phrase);
		//System.out.println(list);
		withercounter = 0;
		
	}
	public void clearMessage() {
		sb1.setLength(0);
		sb2.setLength(0);
	}
	public void setInterruption(boolean i) {
		Interruption = i;
	}
	public boolean isInterrupted() {
		return Interaction;
	}
	public void setDeadInterruption(boolean i) {
		DeadInterruption = i;
	}
	public boolean isDeadInterruption() {
		return DeadInterruption;
	}
	public void setRespawn(boolean i) {
		Respawn = i;
	}
	public boolean isRespawn() {
		return Respawn;
	}
	
	public boolean isTalking() {
		return talk;
	}
	public void commandsCounter() {
		commands++;
	}
	public void resetCommands() {
		commands=0;
	}
	public int getCommands() {
		return commands;
	}
	public void throwingCounter() {
		throwing++;
	}
	public void resetThrowCounter() {
		throwing=0;
	}
	public int getThrowing() {
		return throwing;
	}
	public void moveCounter() {
		move++;
	}
	public void resetMoveCounter() {
		move=0;
	}
	public int getMoves() {
		return move;
	}
	public int getInteraction() {
		return interact;
	}
	public void interactCounter() {
		interact++;
	}
	public void resetInteraction() {
		interact=0;
	}
	public void setList(int l) {
		this.list = CursedAPI.list.get(l);
	}
	public void setTalking(boolean t) {
		sentencecounter=0;
		charcounter=0;
		pause=false;
		end=false;
		name=false;
		talk = t;
	}
	public void chooseRandomPhrase() {
		try {
			Random r = new Random();
			int ch = r.nextInt(list.keySet().size())+1;
			phrase = ch;
		} catch (Exception e) {
			phrase = 1;
			System.out.println(phrase);
			
		}
	}
	public void defineSpaces() {
		for(int i = 0; i < 3; i++) {
    		sb1.append(" ");
    		sb2.append(" ");
    	}
	}
//	public void setList(HashMap<Integer, ArrayList<String>> list) {
//		this.list = list;
//	}

	public char getErrorCharacter(String sentence, int charcount) {
		char c = sentence.toCharArray()[charcount];
		if(charcount+1 == sentence.length()) {
			charcounter=0;
			pause=true;
			end=true;
		}
		else {
			charcounter++;
		}
		if(c != ' ') {
			CursedAPI.playReadingSound(p);
		}
		return c;
	}
	
	public char getCharacter(String sentence, int charcount) {
//		if(list.get(phrase).get(sentencecounter).contains("<name>")) {
//			list.get(phrase).get(sentencecounter).replace("<name>", p.getName());
//		}
		if(name) {
			sentence = names;
		}
		char c = sentence.toCharArray()[charcount];
		if(c == 'ᚙ') {
			
		}
		if(charcount+1 == sentence.length()) {
			if(sentencecounter+1 < list.get(phrase).size()) {
			sentencecounter++;
			}
			else {
				end = true;
			}
			charcounter=0;
		    pause = true;
		}
		else {
		charcounter++;
		}
		if(c != ' ') {
		CursedAPI.playReadingSound(p);
		}
		return c;
	}
	public String getSentence() {
		return list.get(phrase).get(sentencecounter);
	}
	public int listSizeSentence() {
		return list.get(phrase).get(sentencecounter).length();
	}
	public boolean isFinalSentence() {
		if(sentencecounter+1 >= list.get(phrase).size()) {
			return true;
		}
		else {
			return false;
		}
	}
	private void sendFrame() {
		CursedAPI.sendFrame(p);
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		CursedAPI.sendFrame(p);
	}
	private void sendGap() {
		for(int i = 0; i < 10; i++) {
			p.sendMessage(" ");
		}
	}
//	private void defineStructure() {
//		for(int x = 0; x < lines.length; x++) {
//			for(int y = 0; y < lines[x].length; y++) {
//				lines[x][y] = ' ';
//		}
//		}
//		//walls
////		for(int i = 0; i < lines.length; i++) {
////			for(int y = 0; y < 2; y++) {
////				lines[i][y] = '▉';
////				lines[i][lines[i].length-y] = '▉';
////			}
////		}
//		for(int i = 0; i < 6; i++) {
//			int y = 0;
//			if(i <= 2) y = i;
//			else y = 4+i;
//			for(int x = 0; x < lines[y].length-16; i++) {
//				lines[y][x] = '▉';
//			}
//		}
//	}
//	public void saytext(Player p, String[] sentence, StringBuilder s1, StringBuilder s2, int length) {
//	p.sendMessage(" ");
//	int delay = 60;
//	boolean nextline = false;
//	for(String g : sentence) {
//		if(s1.length()+g.length() >= length) {
//			nextline = true;
//		}
//		for(char c : g.toCharArray()) {
//			if(!nextline) s1.append(c);
//			else s2.append(c);
//		}
//	}
//	
//	
//}
	@Override
	public void run() {
	    if(withercounter == 70) {
	    	//p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1f, 0.5f);
	    	p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 0.5f, 0.5f);
	    	withercounter = 0;
	    }
	    withercounter++;
		if(!talk) {
	    	sendGap();
	    	sendFrame();
	    }
	    else {
	    	sendGap();
	    	CursedAPI.sendFrame(p);
	    	p.sendMessage(" ");
	    	p.sendMessage(ChatColor.WHITE + sb1.toString());
	    	p.sendMessage(ChatColor.WHITE + sb2.toString());
	    	p.sendMessage(" ");
	    	CursedAPI.sendFrame(p);
	    	try {
				if(!error) 
	    		getSentence();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				sb1.setLength(0);
				charcounter=0;
				counter=0;
				error=true;
				pause=false;
				end=false;
				defineSpaces();
			}
	    	if(counter%textspeed==0) {
	    		if(error) {
	    			
	    		}
	    		else if(pause || end) {
	    			pausecounter++;
	    			if(pausecounter == 20) {
	    				pausecounter=0;
	    				counter = 0;
	    				sb1.setLength(0);
	    				sb2.setLength(0);
	    				defineSpaces();
	    				pause = false;
	    				name=false;
	    				if(end) {
		    				talk = false;
		    				end = false;
		    			}
	    			}
	    		}
	    		else {
	    		if(getSentence().contains("<name>")) {
	    			name = true;
	    			names = getSentence();
	    			names = names.replace("<name>", p.getName());
	    		}
	    		if(!nextline) {
	    			try {
						sb1.append(getCharacter(getSentence(), charcounter));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						sb1.setLength(0);
						charcounter=0;
						counter=0;
						error=true;
						pause=false;
						end=false;
						defineSpaces();
					}
	    		}
//	    		if(nextline) {
//	    			sb2.append(getCharacter(getSentence(), charcounter));
//	    		}
	    		if(getSentence().contains("<end>")) {
	    			sentencecounter=0;
	    			end = true;
	    		}
	    		}
	    	}
	       counter++;
	    }
	    
		
	}

}
