package messages;

import org.bukkit.Location;

import airdrops.Airdrops;
import config.ConfigManager;

public class Messager {
	
	public static String getSignalDropMessage(Location loc){
		return Airdrops.DISPLAYNAME + "§e An Airdrop §ewill be falling near §6" + loc.getBlockX() + "§e, §6" + loc.getBlockY() + "§e, §6" + loc.getBlockZ() + "§e in §6" + ConfigManager.getConfig().getInt("options.time__after_signal")+ " seconds"; 
	}
	
	public static String getItemResetMessage(){
		return Airdrops.DISPLAYNAME + "§e Airdrop items have been reset!";
	}
	
	public static String getItemAddedMessage(){
		return Airdrops.DISPLAYNAME + "§e Item has been added to the Airdrop";
	}
	
	public static String packageCalledMessage(){
		return Airdrops.DISPLAYNAME + "§e An Airdrop has been summoned.";
	}
	
	public static String reloadMessage(){
		return Airdrops.DISPLAYNAME + "§e Plugin reloaded";
	}
}
