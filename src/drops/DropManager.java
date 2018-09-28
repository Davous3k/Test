package drops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import config.ConfigManager;

public class DropManager {

	private ArrayList<ItemStack> drops;
	private boolean isDefault;

	public DropManager() {
		drops = new ArrayList<ItemStack>();
	}

	public ItemStack[] getDrops(int amount) {

		ItemStack[] items = new ItemStack[amount];
		Random r = new Random();

		if (drops == null || drops.size() == 0){
			loadDefaultDrops();
		}
		
		while (amount > 0) {
			amount -= 1;
			ItemStack item = drops.get(r.nextInt(drops.size()));
			items[amount] = item;
		}

		if (isDefault){
			resetItems();
			isDefault = false;
		}
		
		return items;
	}

	public void addItem(ItemStack item) {
		drops.add(item);
	}
	
	public void resetItems(){
		drops.clear();
		this.saveDrops();
	}
	
	private void loadDefaultDrops(){
		
		ItemStack apple = new ItemStack(Material.APPLE, 5);
		ItemMeta appleMeta = apple.getItemMeta();
		
		appleMeta.setDisplayName("§cAirdrop §eApple");
		appleMeta.setLore(Arrays.asList(new String[]{"", "§eDo /airdrop additem to add", "§eyour own items"}));
		apple.setItemMeta(appleMeta);
		
		ItemStack bread = new ItemStack(Material.BREAD, 5);
		ItemMeta breadMeta = bread.getItemMeta();
		
		breadMeta.setDisplayName("§cAirdrop §eBread");
		breadMeta.setLore(Arrays.asList(new String[]{"", "§eDo /airdrop additem to add", "§eyour own items"}));
		bread.setItemMeta(breadMeta);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 5);
		ItemMeta arrowMeta = arrow.getItemMeta();
		
		arrowMeta.setDisplayName("§cAirdrop §eArrows");
		arrowMeta.setLore(Arrays.asList(new String[]{"", "§eDo /airdrop additem to add", "§eyour own items"}));
		arrow.setItemMeta(arrowMeta);
		
		drops.add(apple);
		drops.add(bread);
		drops.add(arrow);
		this.isDefault = true;
	}
	
	public void saveDrops(){
		ConfigManager.getItems().set("items", drops.toArray());
		ConfigManager.saveFiles();
	}
	
	public void loadDrops(){
		
		List maybeDropsList =  ConfigManager.getItems().getList("items");
		ArrayList<ItemStack> maybeDrops = new ArrayList<ItemStack>();
		
		if (maybeDropsList != null){
			for (Object o : maybeDropsList){
				
				ItemStack item = (ItemStack) o;
				
				maybeDrops.add(item);
			}
			
			drops = maybeDrops;
		}
		
	}
}
