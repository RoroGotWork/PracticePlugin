package fr.rorocraft.practice.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;

public class GameManager extends BukkitRunnable {

	private Main main;

	public GameManager(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		if(main.getBuildUhcQueue().size() >= 2) {
		Player firstPlayer = main.getBuildUhcQueue().get(0);
		
		if(main.getBuildUhcDuel().containsKey(firstPlayer)) {
			Player secondPlayer = main.getBuildUhcDuel().get(firstPlayer);
			main.getUhcArenaManager().joinArena(firstPlayer, secondPlayer, main);
		} else {
			Player secondPlayer = main.getBuildUhcQueue().get(1);
			main.getUhcArenaManager().joinArena(firstPlayer, secondPlayer, main);
		}
		}

	    if(main.getNoDebuffQueue().size() >= 2) {
	    	Player firstPlayer = main.getNoDebuffQueue().get(0);
	    	
	    	if(main.getBuildUhcDuel().containsKey(firstPlayer)) {
	    		Player secondPlayer = main.getBuildUhcDuel().get(firstPlayer);
	    		main.getNoDebuffArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	} else {
	    		Player secondPlayer = main.getNoDebuffQueue().get(1);
	    		main.getNoDebuffArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	}
	    	
	    }
	    
	    
	}

}
