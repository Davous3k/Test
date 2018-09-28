package entities;

import airdrops.Airdrops;

public class PackageEntity {
	
	protected int counter = 0;
	
	public void summon(){
		
	}
	
	public void remove(){
		
	}
	
	protected void tick(){
		
	}
	
	protected void retick(){
		
		Airdrops.instance.getServer().getScheduler().runTaskLater(Airdrops.instance, new Runnable() {
			
			public void run() {
				tick();
			}
			
		}, 1L);
	}
}
