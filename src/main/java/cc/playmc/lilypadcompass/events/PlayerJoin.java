package cc.playmc.lilypadcompass.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerJoin implements Listener {

	private LilyPadCompass plugin;

	public PlayerJoin(LilyPadCompass plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.getInventory().contains(plugin.compassItem)) {
			p.getInventory().setItem(plugin.compassSlot, plugin.compassItem);
		}
	}
}