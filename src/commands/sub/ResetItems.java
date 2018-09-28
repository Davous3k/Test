package commands.sub;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import airdrops.Airdrops;
import commands.CommandBase;
import messages.Messager;

public class ResetItems extends CommandBase {

	public ResetItems() {
		super(new String[] { "resetitems" }, "/airdrop resetitems", "Reset all the drop items saved in the config.", "supplypackage.resetitems", true);
	}

	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;

		if (args.length > 1) {
			CommandBase.error(sender, "Unknown command!");
			return false;
		}
		
		Airdrops.dropManager.resetItems();
		
		player.sendMessage(Messager.getItemResetMessage());
		
		return true;
	}

}