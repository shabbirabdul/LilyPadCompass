package cc.playmc.lilypadcompass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerDropItem implements Listener {

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack()
				.equals(LilyPadCompass.compassItem)
				&& LilyPadCompass.dropsDisabled) {
			event.setCancelled(true);
		}
	}
}