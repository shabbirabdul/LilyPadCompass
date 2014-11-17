package cc.playmc.lilypadcompass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerInteract implements Listener {

    private LilyPadCompass plugin = LilyPadCompass.getInstance();

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack item = e.getItem();
		
		if(item == null) {
		    return;
		}
		
		if(item.getItemMeta() == null) {
		    return;
		}
		
		if(item.getItemMeta().getDisplayName() == null) {
		    return;
		}
		
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getCompassItem().getItemMeta().getDisplayName())) {
		    e.getPlayer().openInventory(plugin.getCompassInventory());
		}
	}
}