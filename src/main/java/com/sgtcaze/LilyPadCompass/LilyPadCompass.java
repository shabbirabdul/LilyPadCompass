package com.sgtcaze.LilyPadCompass;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class LilyPadCompass extends JavaPlugin implements CommandExecutor,
		Listener {
	
	
	public Inventory compassInv;
	public ItemStack compassItem;
	public HashMap<String, String> itemsAndCommands;
	public FileConfiguration config;
	public FileConfiguration admin;
	
	public Inventory inv;

	public void onEnable() {
		this.config = getConfig();
		saveDefaultConfig();

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(
				new LilyPadCompassListener(this), this);

		this.createInventory = makeInventory(this.createInventory(Inventory inventory));
		this.compassInv = makeInventory(this.config);
    	this.compassItem = makeItem(this.config);
		this.itemsAndCommands = makeCommandList(this.config, this.compassInv);
	}

	private Inventory makeInventory(FileConfiguration fileConf) {
		int slots = fileConf.getInt("global.slots");

		if (slots % 9 != 0) {
			slots = 9;
		}

		String title = ChatColor.translateAlternateColorCodes('&',
				fileConf.getString("global.invTitle"));
		return getServer().createInventory(null, slots, title);
	}

	private ItemStack makeItem(FileConfiguration fileConf) {
		ItemStack item = new ItemStack(Material.COMPASS, 1);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				fileConf.getString("item.title")));
		if (this.getConfig().getString("AllowCompassLore")
				.equalsIgnoreCase("true")) {
			meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes(
					'&', fileConf.getString("item.lore"))));
		}
		item.setItemMeta(meta);
		return item;
	}
	

	public int getID(String id) {
		return Integer.parseInt(id.split(":")[0]);
	}

	public short getDamage(String id) {
		String[] split = id.split(":");
		if (split.length >= 2) {
			return Short.parseShort(split[1]);
		}
		return 0;
	}

	public void createInventory(Inventory inventory){
	int slots = this.config.getInt("slots");
    int ID = getID(this.config.getString("commands.id"));
	short damage = getDamage(this.config.getString("damage"));
	String Name = this.config.getString("Name").replaceAll("&", "§");
    
    ItemStack item = new ItemStack(ID, 1, damage);
	}
	
	@SuppressWarnings("unchecked")
	private HashMap<String, String> makeCommandList(FileConfiguration fileConf,
			Inventory inv) {
		@SuppressWarnings("rawtypes")
		HashMap map = new HashMap();

		for (String key : fileConf.getConfigurationSection("commands").getKeys(
				false)) {

			String mat = key;
			int ID = getID(mat);
			short Damage = getDamage(mat);

			if (mat == null) {
				// Do nothing at all :D
			} else {
				map.put(mat, fileConf.getString("commands." + key + ".command"));

				ItemStack item = new ItemStack(ID, 1, Damage);

				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
						fileConf.getString("commands." + key + ".title")));
				if (this.getConfig().getString("AllowItemLore")
						.equalsIgnoreCase("true")) {
					meta.setLore(Arrays.asList(ChatColor
							.translateAlternateColorCodes(
									'&',
									fileConf.getString("commands." + key
											+ ".lore"))));
				}

				item.setItemMeta(meta);

				if (inv.firstEmpty() != -1)
					inv.addItem(new ItemStack[] { item });
			}
		}
		return map;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("compass")) {
				if (p.hasPermission("lpc.item")) {
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(
								new ItemStack[] { this.compassItem });
					}
					sender.sendMessage(ChatColor.GREEN
							+ "The compass has been added.");
				} else {
					sender.sendMessage(ChatColor.RED
							+ "You do not have permission.");

				}
			}
		}
		return true;
	}
}
