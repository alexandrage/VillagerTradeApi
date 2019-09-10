package com;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerTradeApi extends JavaPlugin {
	public void onEnable() {
		getLogger().info("[VillagerTradeApi] Enabled");
	}

	public void addTrade(Villager villager, ItemStack item1, ItemStack rewardItem) {
		addTrade(villager, item1, null, rewardItem, 7);
	}

	public void addTrade(Villager villager, ItemStack item1, ItemStack item2, ItemStack rewardItem) {
		addTrade(villager, item1, item2, rewardItem, 7);
	}

	public void addTrade(Villager villager, ItemStack item1, ItemStack rewardItem, int maxUses) {
		addTrade(villager, item1, null, rewardItem, maxUses);
	}

	public void addTrade(Villager villager, ItemStack stack1, ItemStack stack2, ItemStack stack3, int maxUses) {
		List<MerchantRecipe> recs = villager.getRecipes();
		if (stack2 != null) {
			MerchantRecipe rec = new MerchantRecipe(stack1, maxUses);
			rec.addIngredient(stack2);
			rec.addIngredient(stack3);
			recs.add(rec);
		} else {
			MerchantRecipe rec = new MerchantRecipe(stack1, maxUses);
			rec.addIngredient(stack2);
			recs.add(rec);
		}
		villager.setRecipes(recs);
	}

	public Villager createVillager(Player p) {
		return VillagerNMS.createVillager(p);
	}

	public void openMerchant(Player p, Villager villager) {
		p.openMerchant(villager, true);
	}
}