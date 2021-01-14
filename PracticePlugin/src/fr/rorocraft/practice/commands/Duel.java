package fr.rorocraft.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.task.ExpirationInvit;

public class Duel implements CommandExecutor{

	private Main main;

	public Duel(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player p = (Player) sender;
		
		if(args.length == 0) {
			
			p.sendMessage("&7[&9Yeti&7lounge] &7 Vous devez faire soit /duel <Joueur> <mode de jeu> soit /duel accept <Joueur>, ou soit /duel deny <Joueur>".replace("&", "§"));
			
			return true;
		}
		
		
		 if(args[0].equalsIgnoreCase("accept")) {
			if(args.length == 1) {
				p.sendMessage("&7[&9Yeti&7lounge] &7 Vous devez faire /duel accept <Joueur>".replace("&", "§"));
				return true;
			}

			Player target = Bukkit.getPlayer(args[1]);
			
			
			if(target == null) {
			p.sendMessage("§7[§9Yeti§7lounge] §7 Le joueur " + args[1] + " est introuvable".replace("&", "§"));	
			return true;
			}
			
			if(main.getBuildUhcQueue().contains(p) || main.getBuildUhcDuel().containsKey(p) || main.getBuildUhcDuel().containsValue(p)
					|| main.getUhcArenaManager().getArenaByPlayer(p) != null) {
				p.sendMessage("§7[§9Yeti§7lounge] §7  Tu es déjà engagé à un duel :/");
			}
			
			if(main.getBuildUhcWaitingList().containsKey(target) && main.getBuildUhcWaitingList().get(target) == p) {
				main.getBuildUhcWaitingList().remove(target);
				
				main.getBuildUhcQueue().add(target);
				main.getBuildUhcQueue().add(p);
				main.getBuildUhcDuel().put(target, p);
				
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez accepté l'invitation de " + target.getName());
				target.sendMessage("§7[§9Yeti§7lounge] §7" + p.getName() + " a accepté votre invitation");
				
				return true;
			}
			
			if(main.getNoDebuffWaitingList().containsKey(target) && main.getNoDebuffWaitingList().get(target)  == p) {
				main.getNoDebuffWaitingList().remove(target);
				
				main.getNoDebuffQueue().add(target);
				main.getNoDebuffQueue().add(p);
				main.getBuildUhcDuel().put(target, p);
				
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez accepté l'invitation de " + target.getName());
				target.sendMessage("§7[§9Yeti§7lounge] §7" + p.getName() + " a accepté votre invitation");
				
				return true;
			}
			
			
			p.sendMessage("§7[§9Yeti§7lounge] §7 Le joueur §4"+ args[1] +"§7 ne vous a pas envoyé d'invitation ou son invitation a éxpiré");
		} 
		 
		 
		 else if(args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("refuser") ) {
			 if(args.length == 1) {
					p.sendMessage("§7[&9Yeti§7lounge] §7 Vous devez faire /duel deny/refuser <Joueur>".replace("&", "§"));
					return true;
				}

				Player target = Bukkit.getPlayer(args[1]);
				
				
				if(target == null) {
				p.sendMessage("§7[§9Yeti§7lounge] §7 Le joueur §4" + args[1] + "§7 est introuvable".replace("&", "§"));	
				return true;
				}
				
				if(main.getBuildUhcQueue().contains(p) || main.getBuildUhcDuel().containsKey(p) || main.getBuildUhcDuel().containsValue(p)
						|| main.getUhcArenaManager().getArenaByPlayer(p) != null) {
					p.sendMessage("§7[§9Yeti§7lounge] §7  Tu es déjà engagé à un duel :/");
					return true;
				}
				
				if(main.getBuildUhcWaitingList().containsKey(target) && main.getBuildUhcWaitingList().get(target) == p) {
					main.getBuildUhcWaitingList().remove(p);
					 
					p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez refusé l'invitation de §4" + target.getName());
					target.sendMessage("§7[§9Yeti§7lounge] §4" + p.getName() + "§7  a refusé votre invitation");
					
					return true;
				}
				
				if(main.getNoDebuffWaitingList().containsKey(target) && main.getNoDebuffWaitingList().get(target)  == p) {
					main.getNoDebuffWaitingList().remove(target); 
					
					p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez refusé l'invitation de §4" + target.getName());
					target.sendMessage("§7[§9Yeti§7lounge] §4" + p.getName() + "§7  a refusé votre invitation");
					
					return true;
					
				}
				
				
				p.sendMessage("§7[§9Yeti§7lounge] §7 Le joueur §4"+ args[1] +"§7 ne vous a pas envoyé d'invitation ou son invitation a éxpiré");
		}else 
		
		{
			
			Player target = Bukkit.getPlayer(args[0]);
			
			
			if(target == null) {
			p.sendMessage("§7[§9Yeti§7lounge] §7 Le joueur §4" + args[0] + "§7 est introuvable".replace("&", "§"));	
			return true;
			}
			
			if(args.length == 1) {
				p.sendMessage("&7[&9Yeti&7lounge] &7 Faites /duel <Joueur> <mode de jeu>".replace("&", "§"));
				return true;
			}
			
			
			if(main.getBuildUhcWaitingList().containsKey(p) || main.getBuildUhcWaitingList().containsValue(p)|| 
					main.getBuildUhcQueue().contains(p) || main.getBuildUhcDuel().containsKey(p) || main.getBuildUhcDuel().containsValue(p)) {
				p.sendMessage("§7[§9Yeti§7lounge] §7  Tu es déjà engagé à un duel :/");
				return true;
			}
			
		 if(args[1].equalsIgnoreCase("builduhc") ) {
			 
			 
			main.getBuildUhcWaitingList().put(p, target);
			
			p.sendMessage(" §7[§9Yeti§7lounge] §7 Vous avez envoyé un duel à §4"+ target.getName() +"§7 en §6§l%Mode%".replace("%Mode%", "BuildUhc"));
			
			target.sendMessage(" §7[§9Yeti§7lounge] §7 Le joueur §4" +p.getName() + "§7 veut te duel en §6§l%Mode%".replace("%Mode%", "BuildUhc"));
			
			
			
			Bukkit.getScheduler().runTaskLater(main, new ExpirationInvit(main,p, target), 20 * 120);
			
		} else if(args[1].equalsIgnoreCase("nodebuff")) {
			main.getNoDebuffWaitingList().put(p, target);
			
            p.sendMessage(" §7[§9Yeti§7lounge] §7 Vous avez envoyé un duel à §4"+ target.getName() +"§7 en §6§l%Mode%".replace("%Mode%", "NoDebuff"));
			
			target.sendMessage(" §7[§9Yeti§7lounge] §7 Le joueur §4" +p.getName() + "§7 veut te duel en §6§l%Mode%".replace("%Mode%", "NoDebuff"));
			
			Bukkit.getScheduler().runTaskLater(main, new ExpirationInvit(main, p, target),  20 * 120);
		}
		
		
		
		}
		 
		return false;
	}
}
