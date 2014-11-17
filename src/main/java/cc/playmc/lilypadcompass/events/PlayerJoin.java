package cc.playmc.lilypadcompass.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class PlayerJoin implements Listener {

    private LilyPadCompass plugin = LilyPadCompass.getInstance();

    private int compassSlot;
    
    public PlayerJoin() {
        this.compassSlot = plugin.getConfig().getInt("Compass.JoinSlot");
    }
    
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.getInventory().contains(plugin.getCompassItem())) {
			p.getInventory().setItem(compassSlot, plugin.getCompassItem());
		}
	}
}