package com.gmail.fedmanddev;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventExample implements Listener {
	VillagerTradeApi plugin;
	public EventExample(VillagerTradeApi instance) {
		this.plugin = instance;
	}
	
	//Example 1
	@EventHandler
    public void on(PlayerInteractEntityEvent e) {
		Entity ent =  e.getRightClicked();
		if(ent instanceof Villager) {
			Villager villager = (Villager)ent;
			VillagerTradeApi.clearTrades(villager);
			ItemStack item1 = new ItemStack(Material.APPLE);
			ItemStack reward = new ItemStack(Material.GOLD_NUGGET);
			VillagerTrade trade = new VillagerTrade(item1, reward);
			VillagerTradeApi.addTrade(villager, trade, Integer.MAX_VALUE);
		}
	}
	
	//Example 2
	@EventHandler
    public void on(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Villager villager = VillagerTradeApi.createVillager(p);
		ItemStack item1 = new ItemStack(Material.APPLE);
		ItemStack reward = new ItemStack(Material.GOLD_NUGGET);
		VillagerTrade trade = new VillagerTrade(item1, reward);
		VillagerTradeApi.addTrade(villager, trade, Integer.MAX_VALUE);
		e.getPlayer().openMerchant(villager, true);
	}
}