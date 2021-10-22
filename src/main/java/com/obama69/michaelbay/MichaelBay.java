package com.obama69.michaelbay;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obama69.michaelbay.event.LivingDeathListener;
import com.obama69.michaelbay.event.LivingJumpListener;
import com.obama69.michaelbay.event.RightClickListeners;
import com.obama69.michaelbay.event.WorldTickListener;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(MichaelBay.MODID)
public class MichaelBay {
	public static final String MODID = "michaelbay";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<Class<? extends Entity>> EXPLOSIVE = List.of(Villager.class, ServerPlayer.class, Pig.class, Cow.class, Skeleton.class);

    public MichaelBay() {
        MinecraftForge.EVENT_BUS.register(new LivingDeathListener());
        MinecraftForge.EVENT_BUS.register(new LivingJumpListener());
        MinecraftForge.EVENT_BUS.register(new RightClickListeners());
        MinecraftForge.EVENT_BUS.register(new WorldTickListener());
    }
    
    public static boolean isExplosive(final Entity entity) {
    	for (final Class<? extends Entity> clazz : EXPLOSIVE) {
    		if (clazz.isInstance(entity)) {
    			return true;
    		}
    	}
    	
    	return entity instanceof LivingEntity;
    	//return false;
    }
}
