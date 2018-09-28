package items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SupplySignal {
	
	public static ItemStack getItem(){
		
		ItemStack item = new ItemStack(Material.LEGACY_FIREWORK, 1);
		ItemMeta data = item.getItemMeta();
		
		data.setDisplayName("§r§cSupply Signal");
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Throw this on the ground");
		lore.add("§7To summon a Supply Pacakage");
		
		data.setLore(lore);
		data.addEnchant(Enchantment.DURABILITY, 1, true);
		data.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		item.setItemMeta(data);
		
		return item;
	}
}
