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
import cc.playmc.lilypadcompass.utils.LilyPad;

public class LilyPadCompass extends JavaPlugin implements Listener {

	public FileConfiguration config;

	public ItemStack compassItem;

	private LilyPad lilyPad;

	public Boolean allowDrop;

	public Inventory compass;

	public HashMap<String, String> commands = new HashMap<>();
	public HashMap<String, String> message = new HashMap<>();
	public HashMap<String, String> server = new HashMap<>();

	public int slotsSize, compassSlot;

	public void onEnable() {

		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new InventoryClick(this), this);
		pm.registerEvents(new PlayerDropItem(this), this);
		pm.registerEvents(new PlayerInteract(this), this);
		pm.registerEvents(new PlayerJoin(this), this);

		saveDefaultConfig();

		config = getConfig();

		getCommand("compass").setExecutor(new Compass(this));

		slotsSize = config.getInt("Inventory.Slots");
		compassSlot = config.getInt("Compass.JoinSlot");

		allowDrop = config.getBoolean("Compass.DropsDisabled");

		if (config.getBoolean("LilyPadEnabled")) {
			lilyPad = new LilyPad();
			lilyPad.registerConnect();
		}

		createCompassItem();
		createInventory();
	}

	public LilyPad getLilyUtils() {
		return lilyPad;
	}

	public ItemStack createItem(Material m, int a, int s, String dn,
			List<String> l) {
		ItemStack item = new ItemStack(m, a, (short) s);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(dn);
		meta.setLore(l);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack createItem(Material m, int a, int s, String dn) {
		ItemStack item = new ItemStack(m, a, (short) s);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(dn);
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

				if (config.getString("Items." + i + ".LilyPad") != null) {
					server.put(striped,
							config.getString("Items." + i + ".LilyPad"));
				}

				if (config.getString("Items." + i + ".Message") != null) {
					message.put(striped,
							config.getString("Items." + i + ".Message")
									.replaceAll("&", "§"));
				}
			}
		}
	}
}