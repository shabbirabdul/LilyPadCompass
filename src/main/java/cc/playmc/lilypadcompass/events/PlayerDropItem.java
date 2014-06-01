package cc.playmc.lilypadcompass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerDropItem implements Listener {

	private LilyPadCompass plugin;

	public PlayerDropItem(LilyPadCompass plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().equals(plugin.compassItem)
				&& !plugin.allowDrop) {
			event.setCancelled(true);
		}
	}
}