package cc.playmc.lilypadcompass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import cc.playmc.lilypadcompass.commands.Compass;
import cc.playmc.lilypadcompass.events.InventoryClick;
import cc.playmc.lilypadcompass.events.PlayerDropItem;
import cc.playmc.lilypadcompass.events.PlayerInteract;
import cc.playmc.lilypadcompass.events.PlayerJoin;

public class LilyPadCompass extends JavaPlugin implements Listener {

	public FileConfiguration config;

	public LilyPadCompass plugin;

	public ItemStack compassItem;

	public Boolean allowDrop;

	public Inventory compass;

	public HashMap<String, String> commands = new HashMap<>();
	public HashMap<String, String> message = new HashMap<>();

	public int slotsSize, compassSlot;

	public void onEnable() {

		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new InventoryClick(this), this);
		pm.registerEvents(new PlayerDropItem(this), this);
		pm.registerEvents(new PlayerInteract(this), this);
		pm.registerEvents(new PlayerJoin(this), this);

		plugin = this;

		saveDefaultConfig();

		config = getConfig();

		getCommand("compass").setExecutor(new Compass(this));

		slotsSize = config.getInt("Inventory.Slots");
		compassSlot = config.getInt("Compass.JoinSlot");

		allowDrop = config.getBoolean("Compass.DropsDisabled");

		createCompassItem();
		createInventory();
	}

	public ItemStack createItem(Material material, int amount, short shrt,
			String displayname, List<String> Lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Lore);

		item.setItemMeta(meta);
		return item;
	}

	public ItemStack createItem(Material material, int amount, short shrt,
			String displayname) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);

		item.setItemMeta(meta);
		return item;
	}

	public void createCompassItem() {

		Material mat = Material.matchMaterial(config.getString(
				"Compass.Material").toUpperCase());

		short sht = Short.valueOf(config.getString("Compass.Short"));

		List<String> lore = new ArrayList<>();
		Boolean hasLore = false;

		String Name = config.getString("Compass.Name").replaceAll("&", "§");

		if (config.getString("Compass.Lore") != null) {
			hasLore = true;

			for (String s : config.getStringList("Compass.Lore")) {
				lore.add(s.replaceAll("&", "§"));
			}
		}

		String striped = ChatColor.stripColor(Name);

		commands.put(striped, "na");

		if (hasLore) {
			compassItem = createItem(mat, 1, sht, Name, lore);
		} else {
			compassItem = createItem(mat, 1, sht, Name);
		}
	}

	public void createInventory() {

		compass = Bukkit.createInventory(null, slotsSize,
				config.getString("Inventory.Name").replaceAll("&", "§"));

		for (int i = 0; i < slotsSize; i++) {
			if (config.getString("Items." + i + ".Material") != null) {

				List<String> Lore = new ArrayList<>();
				Boolean hasLore = false;

				int slot = config.getInt("Items." + i + ".Slot");
				Material mat = Material.matchMaterial(config.getString(
						"Items." + i + ".Material").toUpperCase());

				int Amount = config.getInt("Items." + i + ".Amount");

				short sht = Short.valueOf(config.getString("Items." + i
						+ ".Short"));

				String Name = config.getString("Items." + i + ".Name")
						.replaceAll("&", "§");

				if (config.getString("Items." + i + ".Lore") != null) {
					hasLore = true;

					for (String s : config
							.getStringList("Items." + i + ".Lore")) {
						Lore.add(s.replaceAll("&", "§"));
					}
				}

				String command = config.getString("Items." + i + ".Command");

				if (hasLore) {
					compass.setItem(
							slot,
							new ItemStack(createItem(mat, Amount, sht, Name,
									Lore)));
				} else {
					compass.setItem(slot,
							new ItemStack(createItem(mat, Amount, sht, Name)));
				}

				String striped = ChatColor.stripColor(Name);
				commands.put(striped, command);

				if (config.getString("Items." + i + ".Message") != null) {
					message.put(striped,
							config.getString("Items." + i + ".Message")
									.replaceAll("&", "§"));
				}
			}
		}
	}
}