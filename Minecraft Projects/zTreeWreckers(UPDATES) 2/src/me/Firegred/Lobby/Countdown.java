package me.Firegred.Lobby;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable{

	private String game;
	private int timer;
	private Countdown c;
	public Countdown(final String g) {
		game = g;
		timer = -1;
		c = this;
	}
	@Override
	public void run() {
		if(timer <= 30) {
		if(Waiting.ifEnough(game)) {
			timer++;
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.hasMetadata("Game")) {
					if(p.getMetadata("Game").get(0).asString().equals(game)) {
						//if(p.getLevel() == (k-1)) {
						p.setLevel(30-timer);
						if(timer >= 20) {
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
						}
					//}
					}
				}
			}
			if(timer==30) {
				Waiting.generateTrees(game);
				Waiting.startGame(game);
				Waiting.setIngame(game);
			}
			
			}
			else {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.hasMetadata("Game")) {
						if(p.getMetadata("Game").get(0).asString().equals(game)) {
							p.setLevel(0);
						}
					}
				}
				c.cancel();
			}
		}
	}

	
	
}
