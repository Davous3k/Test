package commands.sub;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import commands.CommandBase;
import entities.FallingPackageEntity;
import messages.Messager;
import utils.LocationUtils;

public class Summon extends CommandBase {

	public Summon() {
		super(new String[] { "summon", "call" }, "/airdrop summon", "Summon a Airdrop at your location.", "supplypackage.summon", true);
	}

	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;

		if (args.length > 1) {
			CommandBase.error(sender, "Unknown command!");
			return false;
		}

		new FallingPackageEntity(LocationUtils.offset(player.getLocation(), 0, 100, 0), Material.CHEST);
		Bukkit.broadcastMessage(Messager.packageCalledMessage());
		return true;
	}

}