package utils;

import org.bukkit.Location;

public class LocationUtils {
	
	public static Location offset(Location original, double offx, double offy, double offz){
		
		double newX = original.getX() + offx;
		double newY = original.getY() + offy;
		double newZ = original.getZ() + offz;
		
		if (newY > 255){
			newY = 255;
		} else if (newY < 0){
			newY = 0;
		}
		
		
		return new Location(original.getWorld(), newX, newY, newZ);
	}
}
