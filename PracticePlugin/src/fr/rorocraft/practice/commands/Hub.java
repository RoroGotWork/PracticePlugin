package fr.rorocraft.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.builduhc.UhcArena;
import fr.rorocraft.practice.nodebuff.NoDebuffArena;

public class Hub implements CommandExecutor {

	private Main main;

	public Hub(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player p =(Player) sender;
		
	if(main.getUhcArenaManager().getArenaByPlayer(p) != null) {
		UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(p);
		arena.getPlayers().remove(p);
		arena.checkWin(p);
	} else if(main.getNoDebuffArenaManager().getArenaByPlayer(p) != null) {
		NoDebuffArena arena = main.getNoDebuffArenaManager().getArenaByPlayer(p);
		arena.getPlayers().remove(p);
		arena.checkWin(p);
	}
	
	
	else  {
		p.sendMessage("�7[�9Yeti�7lounge] �7 Tu as �t� t�l�port� vers le hub");
		p.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
	}
	
	
	
	
		return false;
	}

}
