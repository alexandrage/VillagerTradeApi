package com;

import java.lang.reflect.Field;

import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityVillager;
import net.minecraft.server.v1_14_R1.ItemStack;
import net.minecraft.server.v1_14_R1.MerchantRecipe;
import net.minecraft.server.v1_14_R1.MerchantRecipeList;
import net.minecraft.server.v1_14_R1.World;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerTradeApi extends JavaPlugin {
	public void onEnable() {
		getLogger().info("[VillagerTradeApi] Enabled");
	}

	public void clearTrades(Villager villager) {
		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("trades");
			recipes.setAccessible(true);
			MerchantRecipeList list = new MerchantRecipeList();
			recipes.set(entityVillager, list);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addTrade(Villager villager, org.bukkit.inventory.ItemStack item1,
			org.bukkit.inventory.ItemStack rewardItem) {
		addTrade(villager, item1, null, rewardItem, 7);
	}

	public void addTrade(Villager villager, org.bukkit.inventory.ItemStack item1, org.bukkit.inventory.ItemStack item2,
			org.bukkit.inventory.ItemStack rewardItem) {
		addTrade(villager, item1, item2, rewardItem, 7);
	}

	public void addTrade(Villager villager, org.bukkit.inventory.ItemStack item1,
			org.bukkit.inventory.ItemStack rewardItem, int maxUses) {
		addTrade(villager, item1, null, rewardItem, maxUses);
	}

	public void addTrade(Villager villager, org.bukkit.inventory.ItemStack stack1,
			org.bukkit.inventory.ItemStack stack2, org.bukkit.inventory.ItemStack stack3, int maxUses) {
		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		VillagerTrade villagerTrade = setMerchant(stack1, stack2, stack3);
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("trades");
			recipes.setAccessible(true);
			MerchantRecipeList list = (MerchantRecipeList) recipes.get(entityVillager);
			if (villagerTrade.hasItem2()) {
				ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade.getItem1());
				ItemStack item2 = CraftItemStack.asNMSCopy(villagerTrade.getItem2());
				ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade.getRewardItem());
				MerchantRecipe recipe = new MerchantRecipe(item1, item2, rewardItem, 1, maxUses, 0f);
				list.add(recipe);
			} else {
				ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade.getItem1());
				ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade.getRewardItem());
				MerchantRecipe recipe = new MerchantRecipe(item1, rewardItem, 1, maxUses, 0f);
				list.add(recipe);
			}
			recipes.set(entityVillager, list);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public Villager createVillager(Player p) {
		World world = ((CraftWorld) p.getWorld()).getHandle();
		EntityVillager entityvillager = new EntityVillager(EntityTypes.VILLAGER, world);
		CraftVillager villager = new CraftVillager(null, entityvillager);
		clearTrades(villager);
		return villager;
	}

	private VillagerTrade setMerchant(org.bukkit.inventory.ItemStack item1, org.bukkit.inventory.ItemStack item2,
			org.bukkit.inventory.ItemStack rewardItem) {
		if (item2 == null) {
			return new VillagerTrade(item1, rewardItem);
		}
		return new VillagerTrade(item1, item2, rewardItem);
	}

	public void openMerchant(Player p, Villager villager) {
		p.openMerchant(villager, true);
	}
}