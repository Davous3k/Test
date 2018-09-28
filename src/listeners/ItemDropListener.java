package listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import airdrops.Airdrops;
import config.ConfigManager;
import entities.FallingPackageEntity;
import messages.Messager;
import utils.LocationUtils;

public class ItemDropListener implements Listener {

	@EventHandler
	public void onItemSpawn(ItemSpawnEvent event) {

		if (event.getEntity().getItemStack().getType() == Material.LEGACY_FIREWORK
				&& event.getEntity().getItemStack().getItemMeta().getLore().get(0).equals("§7Throw this on the ground")) {
			
			Airdrops.instance.getServer().getScheduler().runTaskLater(Airdrops.instance, new Runnable() {

				public void run() {
					Bukkit.getServer().broadcastMessage(Messager.getSignalDropMessage(event.getLocation()));
					handleSignal(event.getEntity().getLocation());
					event.getEntity().remove();
				}

			}, 20L);

		}
	}
	
	public static void handleSignal(Location loc){
		new SignalHandler(loc);
	}

}
class SignalHandler
{
  Location signalLoc;
  World world;
  private int counter = 0;
  
  public SignalHandler(Location loc) {
    this.signalLoc = loc;
    this.world = loc.getWorld();
    
    summonSignalFirework(LocationUtils.offset(this.signalLoc, 0.0D, 10.0D, 0.0D));
    
    updateSignal();
  }
  

  public void updateSignal()
  {
    Airdrops.instance.getServer().getScheduler().runTaskLater(Airdrops.instance, new Runnable()
    {
      public void run()
      {
        if (SignalHandler.this.counter < ConfigManager.getConfig().getInt("options.time__after_signal"))
        {
          if (SignalHandler.this.counter % 2 == 0) {
            SignalHandler.this.summonSignalFirework(LocationUtils.offset(SignalHandler.this.signalLoc, 0.0D, 10.0D, 0.0D));
          }
          SignalHandler.this.counter += 1;
          SignalHandler.this.updateSignal();
        }
        else {
          new FallingPackageEntity(LocationUtils.offset(SignalHandler.this.signalLoc, 0.0D, 100.0D, 0.0D), Material.NOTE_BLOCK);
        }
        
      }
      
    }, 20L);
  }
  

  private void summonSignalFirework(Location loc)
  {
    Firework fw = (Firework)this.world.spawnEntity(loc, EntityType.FIREWORK);
    FireworkMeta fwm = fw.getFireworkMeta();
    fwm.addEffect(
      FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.RED).withFade(Color.WHITE).withFlicker().withTrail().build());
    fwm.setPower(2);
    fw.setFireworkMeta(fwm);
  }
}