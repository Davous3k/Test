package commands.sub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import airdrops.Airdrops;
import commands.CommandBase;
import items.SupplySignal;

public class GiveSignal extends CommandBase {

	public GiveSignal() {
		super(new String[] { "signal", "givesignal" }, "/airdrop signal [player]", "Give a signal to a player.", "supplypackage.givesignal", false);
	}

	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {


		if (args.length > 1) {
			CommandBase.error(sender, "Unknown command!");
			return false;
		}
		
		if (args.length == 1){
			String name = args[0];
			
			for (Player p : Bukkit.getServer().getOnlinePlayers()){
				
				if (p.getName().equals(name)){
					
					p.getInventory().addItem(SupplySignal.getItem());
					p.sendMessage(Airdrops.DISPLAYNAME + " §eYou were given a signal.");
					sender.sendMessage(Airdrops.DISPLAYNAME + " §eYou gave a supply signal.");
					
					
					
					return true;
				}
				
			}
			
			sender.sendMessage(Airdrops.DISPLAYNAME + "§c Player not found.");
			return false;
			
		}
		
		Player player = (Player) sender;
		
		player.sendMessage(Airdrops.DISPLAYNAME + " §eYou were given a signal.");
		player.getInventory().addItem(SupplySignal.getItem());
		
		return true;
	}

}