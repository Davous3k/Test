package utils;

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_13_R2.EntityLiving;
import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class EntityUtils {
	
	public static void setEntityNBT(Entity e, NBTTagCompound tag) {

        net.minecraft.server.v1_13_R2.Entity nmsEntity = ((CraftEntity) e).getHandle();
        
        nmsEntity.c(tag);
         
        //sets the entity's tag to OUR tag
        ((EntityLiving)nmsEntity).a(tag);
    }
	
	
}
