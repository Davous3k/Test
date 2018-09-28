package config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import airdrops.Airdrops;

public class ConfigManager {

	private final static int CONFIG_VERSION = 2;

	private static File configFile;
	private static FileConfiguration config;

	private static File itemsFile;
	private static FileConfiguration items;

	public static void loadFiles() {

		try {

			configFile = new File(Airdrops.instance.getDataFolder(), "config.yml");
			itemsFile = new File(Airdrops.instance.getDataFolder(), "items.yml");

			// Check if the config file exists
			if (!configFile.exists()) {
				configFile.getParentFile().mkdirs();
				copy(Airdrops.instance.getResource("config.yml"), configFile);
			}
			if (!itemsFile.exists()) {
				itemsFile.getParentFile().mkdirs();
				copy(Airdrops.instance.getResource("items.yml"), itemsFile);
			}

			config = new YamlConfiguration();
			items = new YamlConfiguration();

			config.load(configFile);
			items.load(itemsFile);

		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}

		int curVersion = config.getInt("CONFIG_VERSION");

		if (curVersion != CONFIG_VERSION && curVersion > 0) {
			updateConfig();
		}
	}

	public static void saveFiles() {
		try {
			items.save(itemsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateConfig() {

		int time_after_signal = config.getInt("options.time__after_signal");
		int items_per_drop = config.getInt("options.items_per_drop");
		boolean fireworks_on_fall = config.getBoolean("options.fireworks_on_fall");
		boolean announce_drop = config.getBoolean("options.announce_drop");

		configFile.delete();
		
		loadFiles();

		config.set("options.time_after_signal", time_after_signal);
		config.set("options.items_per_drop", items_per_drop);
		config.set("options.fireworks_on_fall", fireworks_on_fall);
		config.set("options.announce_drop", announce_drop);

	}

	private static void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getConfig() {

		if (config == null) {
			loadFiles();
		}

		return config;
	}

	public static FileConfiguration getItems() {

		if (items == null) {
			loadFiles();
		}

		return items;
	}
}
