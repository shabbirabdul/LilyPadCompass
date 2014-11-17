package cc.playmc.lilypadcompass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LilyPadCompassCommand implements CommandExecutor {

    private LilyPadCompass plugin = LilyPadCompass.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {

		if (!sender.hasPermission("lpc.item")) {
			sender.sendMessage("§cYou do not have permission to use this command.");
			return false;
		}

		if (sender instanceof Player) {
			plugin.createCompassItem();
			plugin.createInventory();
			((Player)sender).getInventory().addItem(plugin.getCompassItem());
			return true;
		}
		return false;
	}
}