package cc.playmc.lilypadcompass;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cc.playmc.lilypadcompass.events.InventoryClick;
import cc.playmc.lilypadcompass.events.PlayerDropItem;
import cc.playmc.lilypadcompass.events.PlayerInteract;
import cc.playmc.lilypadcompass.events.PlayerJoin;

public class LilyPadCompass extends JavaPlugin implements Listener {

	public static FileConfiguration config;

	public static LilyPadCompass plugin;

	public static ItemStack compassItem;

	public static Boolean dropsDisabled;

	public static Inventory compass;

	public static HashMap<String, String> magic = new HashMap<>();

	public static int slotsSize, compassSlot;

	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerDropItem(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new PlayerJoin(), this);

		plugin = this;

		saveDefaultConfig();

		config = getConfig();

		slotsSize = config.getInt("Inventory.Slots");
		compassSlot = config.getInt("Compass.JoinSlot");

		dropsDisabled = config.getBoolean("Compass.DropsDisabled");

		createCompassItem();
		createInventory();
	}

	public static ItemStack createItem(Material material, int amount,
			short shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);

		item.setItemMeta(meta);
		return item;
	}

	public void createCompassItem() {

		Material mat = Material.matchMaterial(config.getString(
				"Compass.Material").toUpperCase());

		short sht = Short.valueOf(config.getString("Compass.Short"));

		String Name = config.getString("Compass.Name").replaceAll("&", "§");
		String Lore = config.getString("Compass.Lore").replaceAll("&", "§");

		String striped = ChatColor.stripColor(Name);

		magic.put(striped, "na");

		compassItem = createItem(mat, 1, sht, Name, Lore);
	}

	public void createInventory() {

		compass = Bukkit.createInventory(null, slotsSize,
				config.getString("Inventory.Name").replaceAll("&", "§"));

		for (int i = 0; i < slotsSize; i++) {
			if (config.getString("Items." + i + ".Material") != null) {

				int slot = config.getInt("Items." + i + ".Slot");
				Material mat = Material.matchMaterial(config.getString(
						"Items." + i + ".Material").toUpperCase());
				int Amount = config.getInt("Items." + i + ".Amount");
				short sht = Short.valueOf(config.getString("Items." + i
						+ ".Short"));
				String Name = config.getString("Items." + i + ".Name")
						.replaceAll("&", "§");
				String Lore = config.getString("Items." + i + ".Lore")
						.replaceAll("&", "§");
				String command = config.getString("Items." + i + ".Command");

				compass.setItem(slot,
						new ItemStack(createItem(mat, Amount, sht, Name, Lore)));

				String striped = ChatColor.stripColor(Name);
				magic.put(striped, command);
			}
		}
	}
}
