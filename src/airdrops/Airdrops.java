package airdrops;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import commands.CommandManager;
import config.ConfigManager;
import drops.DropManager;
import listeners.BlockInteractionListener;
import listeners.ItemDropListener;

public class Airdrops extends JavaPlugin {

	CommandManager cmanager = new CommandManager();

	public static final String DISPLAYNAME = "§7[§cAirdrops§7]";
	public static DropManager dropManager;
	public static JavaPlugin instance;

	public Airdrops() {
		super();
		instance = this;
	}

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new BlockInteractionListener(), this);
		getServer().getPluginManager().registerEvents(new ItemDropListener(), this);

		dropManager = new DropManager();
		ConfigManager.loadFiles();

		dropManager.loadDrops();

		Metrics metrics = null;
		try {
			metrics = new Metrics(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		metrics.start();
	}

	public void onDisable() {
		dropManager.saveDrops();
		ConfigManager.saveFiles();
	}

	public static void reload() {
		dropManager.saveDrops();
		ConfigManager.saveFiles();

		ConfigManager.loadFiles();
		dropManager.loadDrops();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return cmanager.execute(sender, cmd, label, args);
	}
	
	//CONFIG SECTION
	// Here goes a pathetic attempt at configuration handling
	public void loadConfig(){
		
	}

}
