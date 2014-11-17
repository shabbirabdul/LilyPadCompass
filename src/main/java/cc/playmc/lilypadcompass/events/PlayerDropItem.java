package cc.playmc.lilypadcompass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerDropItem implements Listener {

    private LilyPadCompass plugin = LilyPadCompass.getInstance();
    
    private boolean allowDrop;
    
    public PlayerDropItem() {
        this.allowDrop = plugin.getConfig().getBoolean("Compass.DropsDisabled");
    }

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().equals(plugin.getCompassItem()) && !allowDrop) {
			event.setCancelled(true);
		}
	}
}