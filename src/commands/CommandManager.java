package commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager {
	
	ArrayList<CommandBase> commands = new ArrayList<CommandBase>();
	
	public CommandManager(){
		commands.add(new AirdropsCommand());
	}
	
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args){
		
		for (CommandBase c : commands){
			
			if (c.doesMatchLabel(label)){
				
				if (sender.hasPermission(c.getPermission())){
					
					if (c.isPlayerOnly() && isSenderPlayer(sender) || !(c.isPlayerOnly())){
						
						return c.execute(sender, cmd, label, args);
						
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
		return false;
	}
	
	public boolean isSenderPlayer(CommandSender sender){
		
		if (sender instanceof Player) return true;
		return false;
		
	}
	
	
}
