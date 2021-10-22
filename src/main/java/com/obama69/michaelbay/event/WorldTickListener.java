package com.obama69.michaelbay.event;

import com.obama69.michaelbay.explosion.EpikExplosion;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldTickListener {
	private static final int EXPLODE_CHANCE_DENOM = 1000;

	@SubscribeEvent
	public void onTick(final WorldTickEvent event) {
		if (!event.side.isServer() || !(event.world instanceof ServerLevel)) {
			return;
		}
		
		final ServerLevel world = (ServerLevel) event.world;
		for (final Entity entity : world.getAllEntities()) {
			if (!(entity instanceof LivingEntity) || !entity.isAddedToWorld()) {
				continue;
			}
			
			final int rand = (int)(Math.random() * EXPLODE_CHANCE_DENOM);
			
			if (rand > 1) {
				continue;
			}
			
			final Vec3 pos = entity.position();
			EpikExplosion.explode(world, entity, pos.x, pos.y, pos.z, 20, Explosion.BlockInteraction.BREAK, 0.7f);
		}
	}
}
