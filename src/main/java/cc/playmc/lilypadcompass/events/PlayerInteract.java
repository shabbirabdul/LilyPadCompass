package cc.playmc.lilypadcompass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerInteract implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		ItemStack item = e.getItem();

		if (item != null) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)
					|| (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
				if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
					if (item.getItemMeta()
							.getDisplayName()
							.equalsIgnoreCase(
									LilyPadCompass.compassItem.getItemMeta()
											.getDisplayName())) {
						e.getPlayer().openInventory(LilyPadCompass.compass);
					}
				}
			}
		}
	}
}