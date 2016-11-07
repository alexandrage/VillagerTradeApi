package com;

import org.bukkit.inventory.ItemStack;

public final class VillagerTrade {
  private ItemStack item1;
  private ItemStack item2;
  private ItemStack rewardItem;
  
  public VillagerTrade(ItemStack item1, ItemStack item2, ItemStack rewardItem) {
    this.item1 = item1;
    this.item2 = item2;
    this.rewardItem = rewardItem;
  }
  
  public VillagerTrade(ItemStack item1, ItemStack rewardItem) {
    this.item1 = item1;
    this.rewardItem = rewardItem;
  }
  
  public static boolean hasItem2(VillagerTrade villagerTrade) {
    return villagerTrade.item2 != null;
  }
  
  public static ItemStack getItem1(VillagerTrade villagerTrade) {
    return villagerTrade.item1;
  }
  
  public static ItemStack getItem2(VillagerTrade villagerTrade) {
    return villagerTrade.item2;
  }
  
  public static ItemStack getRewardItem(VillagerTrade villagerTrade) {
    return villagerTrade.rewardItem;
  }
}