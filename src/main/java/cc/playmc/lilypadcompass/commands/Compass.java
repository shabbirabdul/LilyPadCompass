package cc.playmc.lilypadcompass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class Compass implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {

		if (!sender.hasPermission("lpc.item")) {
			sender.sendMessage("§cYou do not have permission to use this command.");
			return false;
		}

		if (sender instanceof Player) {
			Player p = (Player) sender;
			LilyPadCompass.createCompassItem();
			LilyPadCompass.createInventory();
			p.getInventory().addItem(LilyPadCompass.compassItem);
			return true;
		}
		return false;
	}
}