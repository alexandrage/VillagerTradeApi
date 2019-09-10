package com;

import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftVillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityVillager;
import net.minecraft.server.v1_14_R1.World;

public class VillagerNMS {
	public static Villager createVillager(Player p) {
		World world = ((CraftWorld) p.getWorld()).getHandle();
		EntityVillager entityvillager = new EntityVillager(EntityTypes.VILLAGER, world);
		CraftVillager villager = new CraftVillager(null, entityvillager);
		return villager;
	}
}