package com.obama69.michaelbay.event;

import com.obama69.michaelbay.MichaelBay;
import com.obama69.michaelbay.explosion.EpikExplosion;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingJumpListener {

	@SubscribeEvent
	public void onEntityJump(final LivingEvent.LivingJumpEvent event) {
		final Entity entity = event.getEntity();
		final Level level = entity.level;
		
		if (!(level instanceof ServerLevel) || !MichaelBay.isExplosive(entity) || !entity.isAddedToWorld()) {
			return;
		}
		
		final ServerLevel world = (ServerLevel) level;
		final Vec3 pos = entity.position();
		
		EpikExplosion.explode(world, entity, pos.x, pos.y - 1, pos.z, 5, Explosion.BlockInteraction.BREAK, 1.3f);
	}
}
