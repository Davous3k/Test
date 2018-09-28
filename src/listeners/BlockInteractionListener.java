package listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import airdrops.Airdrops;
import config.ConfigManager;
import messages.Messager;
import utils.LocationUtils;

public class BlockInteractionListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)  || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			
			if (event.getClickedBlock().hasMetadata("isPackage") && event.getClickedBlock().getMetadata("isPackage").get(0).asBoolean() == true){
				
				Block clickedBlock = event.getClickedBlock();
				
				clickedBlock.setType(Material.AIR);
				clickedBlock.removeMetadata("isPackage", Airdrops.instance);
				
				summonBreakFirework(clickedBlock.getLocation());
				
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 1, 1);
				
				//ITEM HADLING SECTION
				for (ItemStack item : Airdrops.dropManager.getDrops(ConfigManager.getConfig().getInt("options.items_per_drop"))){
					clickedBlock.getWorld().dropItem(clickedBlock.getLocation(), item);
				}
				
			}
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET){
				if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "§r§cSupply Signal"){
					
					ItemDropListener.handleSignal(event.getPlayer().getLocation());
					event.getPlayer().getInventory().getItemInMainHand().setType(Material.AIR);
					Bukkit.getServer().broadcastMessage(Messager.getSignalDropMessage(event.getPlayer().getLocation()));
				}
			}
		}
	}
	
	private void summonBreakFirework(Location loc){
		//Firework stuff 
		Firework fw = (Firework) loc.getWorld().spawnEntity(LocationUtils.offset(loc, 0, 1, 0), EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.addEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.YELLOW).withColor(Color.ORANGE).withFlicker().build());
		fw.setFireworkMeta(fwm);
		Airdrops.instance.getServer().getScheduler().runTaskLater(Airdrops.instance, new Runnable() {

			public void run() {
				fw.detonate();
				
			}
			
		}, 2L);
		
	}
	
}
