package commands.sub;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import airdrops.Airdrops;
import commands.CommandBase;
import messages.Messager;

public class AddItem extends CommandBase {

	public AddItem() {
		super(new String[] { "additem" }, "/airdrop additem", "Add the held item to the Airdrops drop list.", "supplypackage.additem", true);
	}

	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;

		if (args.length > 1) {
			CommandBase.error(sender, "Unknown command!");
			return false;
		}
		
		ItemStack itemInHand = player.getInventory().getItemInMainHand();
		
		if (itemInHand.getType() != Material.AIR){
			Airdrops.dropManager.addItem(itemInHand);
			player.sendMessage(Messager.getItemAddedMessage());
		}
		
		Airdrops.dropManager.saveDrops();
		
		return true;
	}

}