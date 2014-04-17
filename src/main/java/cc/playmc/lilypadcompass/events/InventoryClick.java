package cc.playmc.lilypadcompass.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class InventoryClick implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		ItemStack item = e.getCurrentItem();

		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getName().equals(LilyPadCompass.compass.getName())) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null) {
				if (item.getItemMeta() != null) {
					if (item.getItemMeta().getDisplayName() != null) {
						String name = item.getItemMeta().getDisplayName();
						String striped = ChatColor.stripColor(name);

						String command = LilyPadCompass.magic.get(striped);

						if (!command.equalsIgnoreCase("na")) {
							p.performCommand(command);
						}
					}
				}
			}
		}
	}
}