package fr.rorocraft.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;

public class Spec implements CommandExecutor {
	
	private Main main;

	public Spec(Main main) {
		this.main =main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command smd, String lbl, String[] args) {
		if(!(s instanceof Player)) return true;
		Player p = (Player) s;
		if(args.length == 0) {
			p.sendMessage("§7[§9Yeti§7lounge] §7 Faites /spec <Joueur>");
			return true;
		}
		
		if(main.getBuildUhcQueue().contains(p) || main.getUhcArenaManager().getArenaByPlayer(p) != null   
				|| main.getNoDebuffQueue().contains(p) || main.getNoDebuffArenaManager().getArenaByPlayer(p) != null) {
			p.sendMessage("§7[§9Yeti§7lounge] §7  Tu es déjà engagé à un duel :/");
			return true;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		
		if(target == null) {
			p.sendMessage("§7[§9Yeti§7lounge] §7 Le joueur §4" + args[1] + "§7 est introuvable");
			return true;
		}
		
		if(main.getUhcArenaManager().getArenaByPlayer(target) == null) {
			p.sendMessage("§7[§9Yeti§7lounge] §7  Le joueur §4" + target.getName() + "§7 n'est pas dans un duel");
			return true;
		}
		
		
		
		
		
		p.sendMessage("§7[§9Yeti§7lounge] §7 Téléportation à §4" + target.getName());
		p.teleport(target.getLocation());
		
		return false;
	}

}
