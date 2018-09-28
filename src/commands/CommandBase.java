package commands;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandBase {
	
	public String[] labels;
	public String permissionNode;
	public String use;
	public String desc;
	public boolean playerOnly = false;
	protected CommandBase[] subCommands = null;

	public CommandBase(String[] labels, String use, String desc, String permissionNode){
		this.labels = labels;
		this.permissionNode = permissionNode;
		this.use = use;
		this.desc = desc;
	}
	
	public CommandBase(String[] labels, String use, String desc, String permissionNode, boolean playerOnly){
		this.labels = labels;
		this.permissionNode = permissionNode;
		this.use = use;
		this.desc = desc;
		this.playerOnly = playerOnly;
	}
	
	public CommandBase(String[] labels, String use, String desc, String permissionNode, boolean playerOnly, CommandBase[] subCommands) {
		this.labels = labels;
		this.permissionNode = permissionNode;
		this.use = use;
		this.desc = desc;
		this.playerOnly = playerOnly;
		this.subCommands = subCommands;
	}
	
	public abstract boolean execute(CommandSender sender, Command cmd, String label, String[] args);
	
	public boolean handleSubCommands(CommandSender sender, Command cmd, String label, String[] args){
		
		if (this.subCommands == null || args.length == 0){
			error(sender, "Command not found.");
			return false;
		}
		
		for (CommandBase c : subCommands){
			if (c.doesMatchLabel(args[0])){
				if (sender.hasPermission(c.getPermission())){
					if (c.isPlayerOnly() && isSenderPlayer(sender) || !c.isPlayerOnly()){
						
						ArrayList<String> args2 = new ArrayList<String>(Arrays.asList(args));
						
						args2.remove(0);
						
						String[] arguments = new String[args2.size()];
						arguments = args2.toArray(arguments);
						
						return c.execute(sender, cmd, args[0], args2.size() != 0 ? arguments : new String[]{});
					} else {
						CommandBase.error(sender, "This command can only be executed by players.");
						return false;
					}
					
				} else {
					CommandBase.error(sender, "You do not have permssion to use this command.");
					return false;
				}
			}
		}
		error(sender, "Command not found");
		return false;
	}
	
	public String getPermission(){
		return this.permissionNode;
	}
	
	public boolean doesMatchLabel(String input){
		for (String s : labels){
			if (input.equals(s)){
				return true;
			}
		} 
		return false;
	}
	
	public boolean isSenderPlayer(CommandSender sender){
		
		if (sender instanceof Player) return true;
		return false;
		
	}
	
	public boolean isPlayerOnly(){
		return this.playerOnly;
	}
	
	public static void good(CommandSender sender, String message){
		sender.sendMessage("§7[§cAirdrops§7]§a " + message);
	}
	
	public static void message(CommandSender sender, String message){
		sender.sendMessage("§7[§cAirdrops§7]§f " + message);
	}
	
	public static void error(CommandSender sender){
		sender.sendMessage("§7[§cAirdrops§7]§c ERROR");
	}
	
	public static void error(CommandSender sender, String message){
		sender.sendMessage("§7[§cAirdrops§7]§c " + message);
	}

}
