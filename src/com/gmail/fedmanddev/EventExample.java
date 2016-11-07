package com.gmail.fedmanddev;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EventExample implements Listener {
	VillagerTradeApi plugin;
	public EventExample(VillagerTradeApi instance) {
		this.plugin = instance;
	}
	
	//Usage
	@EventHandler
    public void on(PlayerInteractEntityEvent e) {
		Entity ent =  e.getRightClicked();
		if(ent instanceof Villager) {
			Villager villager = (Villager)  e.getRightClicked();
			VillagerTradeApi.clearTrades(villager);
			ItemStack item1 = new ItemStack(Material.GOLD_NUGGET, 10);
			ItemStack item2 = new ItemStack(Material.APPLE);
			ItemStack reward = new ItemStack(Material.DIAMOND_HOE);
			VillagerTrade trade = new VillagerTrade(item1, item2, reward);
			VillagerTradeApi.addTrade(villager, trade);
		}
	}
}