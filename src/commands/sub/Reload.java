package commands.sub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import airdrops.Airdrops;
import commands.CommandBase;
import messages.Messager;

public class Reload extends CommandBase {

	public Reload() {
		super(new String[] { "reload" }, "/airdrop reload", "Reload the config file", "supplypackage.reload", false);
	}

	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length > 1) {
			CommandBase.error(sender, "Unknown command!");
			return false;
		}
		
		Airdrops.reload();
		
		Bukkit.getServer().broadcastMessage(Messager.reloadMessage());
		
		return true;
	}

}