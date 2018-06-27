package me.Firegred.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;

import me.Firegred.Cell.Generation.Bounds;
import me.Firegred.Cell.Generation.Cell;
import me.Firegred.main.Main;
import me.Firegred.main.PlayerConfig;
import me.Firegred.misc.Inv;

public class Interact implements Listener{

	public Main plugin;
	
	public Interact(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e) {
		if(Main.wait.containsKey(e.getPlayer())) {
			int x = e.getPlayer().getLocation().getBlockX();
			int y = e.getPlayer().getLocation().getBlockY();
			int z = e.getPlayer().getLocation().getBlockZ();
			
			int x2 = Main.wait.get(e.getPlayer()).getBlockX();
			int y2 =Main.wait.get(e.getPlayer()).getBlockY();
			int z2 =Main.wait.get(e.getPlayer()).getBlockZ();
			if(x!=x2 || y!=y2 || z!=z2) {
				Main.wait.remove(e.getPlayer());
				e.getPlayer().sendMessage(ChatColor.RED +"Teleport cancelled");
			}
		}
	}
	
//	@EventHandler
//	public void weather(WeatherChangeEvent e) {
//		if(e.getWorld().getName().equals(Main.world)) {
//			e.setCancelled(true);
//			Bukkit.getWorld(Main.world).setStorm(false);
//			Bukkit.getWorld(Main.world).setWeatherDuration(10000);
//		}
//	}
	@EventHandler
	public void plate(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.PHYSICAL)) {
			if(e.getPlayer().getWorld().getName().equals(Main.world)) {
				if(Bounds.isExitPlate(e.getClickedBlock().getLocation())) {
					Cell.teleportToLobby(e.getPlayer());
				}
			}
			else {
				if(Bounds.isSpecialPressurePlate(e.getClickedBlock().getLocation())) {
					Cell.teleportToCell(e.getPlayer());
				}
			}
		}
	}
	
	@EventHandler
	public void noInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getWorld().getName().equals(Main.world)) {
			if(!e.getAction().equals(Action.PHYSICAL) || !e.getAction().equals(Action.LEFT_CLICK_AIR) || !e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(PlayerConfig.hasConfig(e.getPlayer())) {
				Player p = e.getPlayer();
				if(e.getClickedBlock() != null) {
				if(e.getClickedBlock().getState() instanceof Sign) {
					if(Bounds.isModifySign(p, e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
						p.openInventory(Inv.createGUI(p));
					}
					else {
						e.setCancelled(true);
					}
				}
				else if(!Bounds.isInInteractBounds(p, e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
					e.setCancelled(true);
					//p.sendMessage(ChatColor.RED + "You cannot modify anything outside of your cell!");
				}
			}
			}
			else {
				e.setCancelled(true);
			}
		}
		}
}
	
	@EventHandler
	public void noDestroy(BlockPlaceEvent e) {
		if(e.getPlayer().getWorld().getName().equals(Main.world)) {
			if(PlayerConfig.hasConfig(e.getPlayer())) {
				Player p = e.getPlayer();
				if(!Bounds.isInBounds(p, e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ())) {
					e.setCancelled(true);
					//p.sendMessage(ChatColor.RED + "You cannot place this here!");
				}
			}
			else {
				e.setCancelled(true);
			}
		}
		}
	
	@EventHandler
	public void noBuild(BlockBreakEvent e) {
		if(e.getPlayer().getWorld().getName().equals(Main.world)) {
			if(PlayerConfig.hasConfig(e.getPlayer())) {
				Player p = e.getPlayer();
				if(!Bounds.isInBounds(p, e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ())) {
					e.setCancelled(true);
					//p.sendMessage(ChatColor.RED + "You cannot break this!");
				}
			}
			else {
				e.setCancelled(true);
			}
		}
		}
	}


