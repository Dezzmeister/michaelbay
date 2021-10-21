package com.obama69.michaelbay.event;

import com.obama69.michaelbay.explosion.EpikExplosion;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RightClickListeners {
	
	private boolean isHoldingStick(final ServerPlayer player) {
		final ItemStack itemstack1 = player.getMainHandItem();
		final ItemStack itemstack2 = player.getOffhandItem();
		final Item item1 = itemstack1.getItem();
		final Item item2 = itemstack2.getItem();
		
		return item1.equals(Items.STICK) || item2.equals(Items.STICK);
	}

	@SubscribeEvent
	public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
		final Player entity = event.getPlayer();
		final Level level = entity.level;
		
		if (!(entity instanceof ServerPlayer) || !(level instanceof ServerLevel)) {
			return;
		}
		
		final ServerPlayer player = (ServerPlayer) entity;
		
		if (!isHoldingStick(player)) {
			return;
		}
		
		final ServerLevel world = (ServerLevel) level;
		final BlockPos pos = event.getPos();
		
		EpikExplosion.explode(world, entity, pos.getX(), pos.getY(), pos.getZ(), 20, Explosion.BlockInteraction.BREAK, 0.7f);
	}
	
	@SubscribeEvent
	public void onRightClickItem(final PlayerInteractEvent.RightClickItem event) {
		final Player entity = event.getPlayer();
		final Level level = entity.level;
		
		if (!(entity instanceof ServerPlayer) || !(level instanceof ServerLevel)) {
			return;
		}
		
		final ServerPlayer player = (ServerPlayer) entity;
		
		if (!isHoldingStick(player)) {
			return;
		}
		
		final ServerLevel world = (ServerLevel) level;
		
		final Vec3 endVec = player.position().add(player.getLookAngle().scale(750));
		final ClipContext context = new ClipContext(player.position(), endVec, ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, null);
		final BlockHitResult result = world.clip(context);
		
		if (result.getType() == HitResult.Type.MISS) {
			return;
		}
		
		final BlockPos pos = result.getBlockPos();
		
		EpikExplosion.explode(world, entity, pos.getX(), pos.getY(), pos.getZ(), 20, Explosion.BlockInteraction.BREAK, 0.7f);
	}
}
