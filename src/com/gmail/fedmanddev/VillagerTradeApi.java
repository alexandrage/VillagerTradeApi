package com.gmail.fedmanddev;

import java.lang.reflect.Field;
import net.minecraft.server.v1_10_R1.EntityVillager;
import net.minecraft.server.v1_10_R1.ItemStack;
import net.minecraft.server.v1_10_R1.MerchantRecipe;
import net.minecraft.server.v1_10_R1.MerchantRecipeList;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagerTradeApi extends JavaPlugin {
  public void onEnable() {
    getLogger().info("[VillagerTradeApi] Enabled");
    //Example used
    //getServer().getPluginManager().registerEvents(new EventExample(this), this);
  }
  
  public static void clearTrades(Villager villager) {
    EntityVillager entityVillager = ((CraftVillager)villager).getHandle();
    try {
      //EntityVillager.class: Field -> private MerchantRecipeList trades;
      Field recipes = entityVillager.getClass().getDeclaredField("trades");
      recipes.setAccessible(true);
      MerchantRecipeList list = new MerchantRecipeList();
      recipes.set(entityVillager, list);
    }
    catch (Exception exc) {
      exc.printStackTrace();
    }
  }
  
  public static void addTrade(Villager villager, VillagerTrade villagerTrade, int maxUses) {
    EntityVillager entityVillager = ((CraftVillager)villager).getHandle();
    try {
      //EntityVillager.class: Field -> private MerchantRecipeList trades;
      Field recipes = entityVillager.getClass().getDeclaredField("trades");
      recipes.setAccessible(true);
      MerchantRecipeList list = (MerchantRecipeList)recipes.get(entityVillager);
      if (VillagerTrade.hasItem2(villagerTrade)) {
        ItemStack item1 = CraftItemStack.asNMSCopy(VillagerTrade.getItem1(villagerTrade));
        ItemStack item2 = CraftItemStack.asNMSCopy(VillagerTrade.getItem2(villagerTrade));
        ItemStack rewardItem = CraftItemStack.asNMSCopy(VillagerTrade.getRewardItem(villagerTrade));
        MerchantRecipe recipe = new MerchantRecipe(item1, item2, rewardItem);
        recipe.maxUses = maxUses;
        list.add(recipe);
      } else {
        ItemStack item1 = CraftItemStack.asNMSCopy(VillagerTrade.getItem1(villagerTrade));
        ItemStack rewardItem = CraftItemStack.asNMSCopy(VillagerTrade.getRewardItem(villagerTrade));
        MerchantRecipe recipe = new MerchantRecipe(item1, rewardItem);
        recipe.maxUses = maxUses;
        list.add(recipe);
      }
      recipes.set(entityVillager, list);
    }
    catch (Exception exc) {
      exc.printStackTrace();
    }
  }
  
  public static void addTrade(Villager villager, VillagerTrade villagerTrade) {
	  addTrade(villager, villagerTrade, 7);
  }
}