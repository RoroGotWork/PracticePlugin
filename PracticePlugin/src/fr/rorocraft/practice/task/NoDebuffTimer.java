package fr.rorocraft.practice.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.builduhc.UhcArena;
import fr.rorocraft.practice.nodebuff.NoDebuffArena;
import fr.rorocraft.practice.state.PracticeState;

public class NoDebuffTimer extends BukkitRunnable {

	int timer = 5;
	
	private Player firstPlayer;
	private Player secondPlayer;

	private Main main;

	public NoDebuffTimer(Player firstPlayer, Player secondPlayer, Main main) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.main = main;
	}

	@Override
	public void run() {
		
		if(main.getNoDebuffArenaManager().getArenaByPlayer(firstPlayer) == null || main.getNoDebuffArenaManager().getArenaByPlayer(secondPlayer) == null ) {
			if(main.getNoDebuffArenaManager().getArenaByPlayer(firstPlayer) != null) {
				
				NoDebuffArena arena = main.getNoDebuffArenaManager().getArenaByPlayer(firstPlayer);
				
				arena.setStarted(false);
				arena.setState(PracticeState.WAITING);
				arena.getPlayers().clear();
				
				
				firstPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				secondPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
			}
			
			else if(main.getNoDebuffArenaManager().getArenaByPlayer(secondPlayer) != null) {
           NoDebuffArena arena = main.getNoDebuffArenaManager().getArenaByPlayer(secondPlayer);
				
				arena.setStarted(false);
				arena.setState(PracticeState.WAITING);
				arena.getPlayers().clear();
				firstPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				secondPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
			}
		}
		if(timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
			firstPlayer.setLevel( timer);
			secondPlayer.setLevel(timer);
			
			firstPlayer.playSound(firstPlayer.getLocation(), Sound.ORB_PICKUP, 1, 1);
			secondPlayer.playSound(firstPlayer.getLocation(), Sound.ORB_PICKUP, 1, 1);
			
			
		}
		
		
		if(timer == 0) {
			firstPlayer.setLevel( timer);
			secondPlayer.setLevel(timer);
			
			
			NoDebuffArena arena = main.getNoDebuffArenaManager().getArenaByPlayer(firstPlayer);
			arena.setState(PracticeState.PLAYING);
			
			main.getFrozenPlayers().remove(firstPlayer.getUniqueId());
			main.getFrozenPlayers().remove(secondPlayer.getUniqueId());
			
			firstPlayer.playSound(firstPlayer.getLocation(), Sound.EXPLODE, 1F, 1F);
			secondPlayer.playSound(firstPlayer.getLocation(), Sound.EXPLODE, 1F, 1F);
			
			firstPlayer.sendMessage("§7[§9Yeti§7lounge] §7 Lancement de la partie");
			secondPlayer.sendMessage("§7[§9Yeti§7lounge] §7 Lancement de la partie");
			
			firstPlayer.setGameMode(GameMode.ADVENTURE);
			secondPlayer.setGameMode(GameMode.ADVENTURE);
		}
		
		timer --;
	}

}
