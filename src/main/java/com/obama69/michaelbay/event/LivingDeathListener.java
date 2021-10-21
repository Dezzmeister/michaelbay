package com.obama69.michaelbay.event;

import com.obama69.michaelbay.MichaelBay;
import com.obama69.michaelbay.explosion.EpikExplosion;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDeathListener {

	@SubscribeEvent
	public void onEntityDeath(final LivingDeathEvent event) {
		final LivingEntity entity = event.getEntityLiving();
		final Level level = entity.level;
		
		if (!(level instanceof ServerLevel) || !MichaelBay.isExplosive(entity)) {
			return;
		}
		
		final ServerLevel world = (ServerLevel) level;
		final Vec3 pos = entity.position();
		
		EpikExplosion.explode(world, entity, pos.x, pos.y, pos.z, 10, Explosion.BlockInteraction.BREAK, 0.3f);
	}
}
