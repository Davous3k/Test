package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import commands.sub.AddItem;
import commands.sub.GiveSignal;
import commands.sub.Reload;
import commands.sub.ResetItems;
import commands.sub.Summon;

public class AirdropsCommand extends CommandBase {

	public AirdropsCommand() {

		super(new String[] { "airdrop", "airdrops" }, "/airdrop", "The main Airdrops command", "airdrops.admin",
				false,
				new CommandBase[] { new Summon(), new GiveSignal(), new AddItem(), new ResetItems(), new Reload() });
	}

	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {

			sender.sendMessage("§c§lAirdrops §r§ev1.1.0");
			sender.sendMessage("§c§lBy §espookyFalco");
			sender.sendMessage("§e§l§m------------------------------------");
			for (CommandBase command : subCommands) {
				sender.sendMessage("§c§l" + command.use + " §e> §f" + command.desc);
			}
			sender.sendMessage("§e§l§m------------------------------------");
			return true;
		} else {
			return handleSubCommands(sender, cmd, label, args);
		}
	}

}
