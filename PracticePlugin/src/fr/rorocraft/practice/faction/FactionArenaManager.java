package fr.rorocraft.practice.faction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;

public class FactionArenaManager {
	private List<FactionArena> arenas = new ArrayList<>();

	public List<FactionArena> getArenas() {
		return arenas;
	}
	
	public void joinArena(Player firstPlayer, Player  secondPlayer, Main main) {
		FactionArena arena = getFreeArena();
		if(arena == null) {
			firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune ar�ne disponible pour l'instant".replace("&", "�"));
			secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune ar�ne disponible".replace("&", "�"));
		} else {
			
			arena.getPlayers().add(firstPlayer);
			arena.getPlayers().add(secondPlayer);
			
			arena.setState(PracticeState.STARTING);
			arena.setStarted(true);
			
			firstPlayer.teleport(arena.getLoc1());
			secondPlayer.teleport(arena.getLoc2());
			
			firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as �t� t�l�port� vers une ar�ne. Tu joue contre �4".replace("&", "�") + secondPlayer.getName());
	 		secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as �t� t�l�port� vers une ar�ne. Tu joue contre �4".replace("&", "�") + firstPlayer.getName());
	 		
	 		if(main.getFactionDuel().containsKey(firstPlayer)) {
	 			main.getFactionDuel().remove(firstPlayer);
	 		} else if(main.getFactionDuel().containsKey(secondPlayer)) {
	 			main.getFactionDuel().remove(secondPlayer);
	 		}
	 		
	 		main.getFactionQueue().remove(firstPlayer);
	 		main.getFactionQueue().remove(secondPlayer);
	 		
	 		main.getFrozenPlayers().add(firstPlayer.getUniqueId());
	 		main.getFrozenPlayers().add(secondPlayer.getUniqueId());
	 		
	 		// Ajout des kits
		}
	}
	
	
	
	
	public FactionArena getFreeArena() {
		for(FactionArena arena : getArenas()) {
			if(!arena.isStarted() && arena.isState(PracticeState.WAITING)) {
				return arena;
			}
			
		}
		return null;
	}
	
	public FactionArena getArenaByPlayer(Player p) {
		
		for(FactionArena arena : getArenas()) {
			if(arena.getPlayers().contains(p)) {
				return arena;
			}
		}
		
		return null;
	}

}
