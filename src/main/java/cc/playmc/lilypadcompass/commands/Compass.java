package cc.playmc.lilypadcompass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.playmc.lilypadcompass.LilyPadCompass;

public class Compass implements CommandExecutor {

	private LilyPadCompass plugin;

	public Compass(LilyPadCompass plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {

		if (!sender.hasPermission("lpc.item")) {
			sender.sendMessage("§cYou do not have permission to use this command.");
			return false;
		}

		if (sender instanceof Player) {
			Player p = (Player) sender;
			plugin.createCompassItem();
			plugin.createInventory();
			p.getInventory().addItem(plugin.compassItem);
			return true;
		}
		return false;
	}
}