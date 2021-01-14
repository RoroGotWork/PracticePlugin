package fr.rorocraft.practice.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;

public class ExpirationInvit extends BukkitRunnable {

	private Player p;
	private Player target;
	private Main main;

	public ExpirationInvit(Main main,Player p, Player target) {
		this.p =p;
		this.target = target;
		this.main = main;
	}

	@Override
	public void run() {
		if(main.getBuildUhcWaitingList().containsKey(p) && main.getBuildUhcWaitingList().get(p) == target) {
			main.getBuildUhcWaitingList().remove(p);
			p.sendMessage("�7[�9Yeti�7lounge] �7 Votre demande d'invitation pour �4" + target.getName() + "�7 a expir�");
			target.sendMessage("�7[�9Yeti�7lounge] �7 La demande d'invitation de �4" + p.getName() + "�7 a �xpir�");
		} else if(main.getNoDebuffWaitingList().containsKey(p) && main.getNoDebuffWaitingList().get(p) == target) {
			main.getNoDebuffWaitingList().remove(p);
			p.sendMessage("�7[�9Yeti�7lounge] �7 Votre demande d'invitation pour �4" + target.getName() + "�7 a expir�");
			target.sendMessage("�7[�9Yeti�7lounge] �7 La demande d'invitation de �4" + p.getName() + "�7 a �xpir�");
		}

	}

}
