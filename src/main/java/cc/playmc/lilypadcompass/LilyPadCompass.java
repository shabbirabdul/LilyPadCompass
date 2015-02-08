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

import cc.playmc.lilypadcompass.events.InventoryClick;
import cc.playmc.lilypadcompass.events.PlayerDropItem;
import cc.playmc.lilypadcompass.events.PlayerInteract;
import cc.playmc.lilypadcompass.events.PlayerJoin;

public class LilyPadCompass extends JavaPlugin implements Listener {

    private static LilyPadCompass plugin;
    private LilyPadHook lilyPad;
    	private int number;
	private ItemStack compassItem;

	private Inventory compassInventory;

	private HashMap<String, String> commands = new HashMap<>();
	private HashMap<String, String> message = new HashMap<>();
	private HashMap<String, String> server = new HashMap<>();

	public void onEnable() {
        plugin = this;
     
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerDropItem(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new PlayerJoin(), this);
		
		getCommand("compass").setExecutor(new LilyPadCompassCommand());

		saveDefaultConfig();
		
		if (getConfig().getBoolean("LilyPadEnabled")) {
			lilyPad = new LilyPadHook();
			lilyPad.registerConnect();
		}

		createCompassItem();
		createInventory();
	}

	public static LilyPadCompass getInstance() {
	    return plugin;
	}
	
	public LilyPadHook getLilyUtils() {
		return lilyPad;
	}
	
	public ItemStack getCompassItem() {
	    return compassItem;
	}
	
	public Inventory getCompassInventory() {
	    return compassInventory;
	}
	
	public HashMap<String, String> getCommandsMap() {
	    return commands;
	}
	
	public HashMap<String, String> getMessagesMap() {
	    return message;
	}
	
	public HashMap<String, String> getServerMap() {
	    return server;
	}
	
	private String colorize(String input) {
	    return ChatColor.translateAlternateColorCodes('&', input);
	}

	private ItemStack make(Material m, int a, int s, String dn, List<String> l) {
		ItemStack item = new ItemStack(m, a, (short) s);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(dn);
		meta.setLore(l);
		item.setItemMeta(meta);
		return item;
	}

	private ItemStack make(Material m, int a, int s, String dn) {
		ItemStack item = new ItemStack(m, a, (short) s);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(dn);
		item.setItemMeta(meta);
		return item;
	}

	public void createCompassItem() {
	    FileConfiguration config = getConfig();
		Material mat = Material.matchMaterial(config.getString("Compass.Material").toUpperCase());

		short sht = (short) config.getInt("Compass.Short");

		List<String> lore = new ArrayList<>();
		boolean hasLore = false;

		String name = colorize(config.getString("Compass.Name"));

		if (config.getString("Compass.Lore") != null) {
			hasLore = true;

			for (String s : config.getStringList("Compass.Lore")) {
				lore.add(colorize(s));
			}
		}

		String striped = ChatColor.stripColor(name);

		commands.put(striped, "na");

		if (hasLore) {
			compassItem = make(mat, 1, sht, name, lore);
		} else {
			compassItem = make(mat, 1, sht, name);
		}
	}

	public void createInventory() {
	    FileConfiguration config = getConfig();
	    compassInventory = Bukkit.createInventory(null, config.getInt("Inventory.Slots"), colorize(config.getString("Inventory.Name")));

		for (String i : config.getConfigurationSection("Items").getKeys(false)) {
			List<String> Lore = new ArrayList<>();
			boolean hasLore = false;

			int slot = config.getInt("Items." + i + ".Slot");
			Material mat = Material.matchMaterial(config.getString("Items." + i + ".Material").toUpperCase());

			int amount = config.getInt("Items." + i + ".Amount");

			short sht = (short) config.getInt("Items." + i + ".Short");

			String name = colorize(config.getString("Items." + i + ".Name"));

			if (config.getString("Items." + i + ".Lore") != null) {
				hasLore = true;

				for (String s : config.getStringList("Items." + i + ".Lore")) {
					Lore.add(colorize(s));
				}
			}

			String command = config.getString("Items." + i + ".Command");

			if (hasLore) {
			    compassInventory.setItem(slot, new ItemStack(make(mat, amount, sht, name, Lore)));
			} else {
			    compassInventory.setItem(slot, new ItemStack(make(mat, amount, sht, name)));
			}

			String striped = ChatColor.stripColor(name);
			commands.put(striped, command);

			if (config.getString("Items." + i + ".LilyPad") != null) {
				server.put(striped, config.getString("Items." + i + ".LilyPad"));
			}

			if (config.getString("Items." + i + ".Message") != null) {
				message.put(striped, config.getString("Items." + i + ".Message").replaceAll("&", "§"));
			}
		}
	}
}
