package cc.playmc.lilypadcompass.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class InventoryClick implements Listener {

    private LilyPadCompass plugin = LilyPadCompass.getInstance();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equals(plugin.getCompassInventory().getName())) {
            e.setCancelled(true);

            ItemStack item = e.getCurrentItem();

            if (item == null) {
                return;
            }

            if (item.getItemMeta() == null) {
                return;
            }

            if (item.getItemMeta().getDisplayName() == null) {
                return;
            }

            Player p = (Player) e.getWhoClicked();

            String name = item.getItemMeta().getDisplayName();
            String striped = ChatColor.stripColor(name);

            String command = plugin.getCommandsMap().get(striped);

            if (plugin.getMessagesMap().get(striped) != null) {
                p.sendMessage(plugin.getMessagesMap().get(striped));
            }

            if (command != null && !command.equalsIgnoreCase("na")) {
                p.performCommand(command);
            }

            p.closeInventory();

            if (plugin.getServerMap().get(striped) != null) {
                plugin.getLilyUtils().redirect(plugin.getServerMap().get(striped), p);
            }
        }
    }
}
