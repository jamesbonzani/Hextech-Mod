package com.kingcast255.hextechmod.common.entity;

import com.kingcast255.hextechmod.HextechMod;
import com.kingcast255.hextechmod.common.entity.custom.PlayerProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.http.client.entity.EntityBuilder;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, HextechMod.MOD_ID);

    public static final RegistryObject<EntityType<PlayerProjectile>> PLAYER_PROJECTILE =
            ENTITY_TYPES.register("player_projectile",
                    () -> EntityType.Builder.of(PlayerProjectile::new, MobCategory.MISC)
                    .sized(1.0f,1.0f)
                    .build(new ResourceLocation(HextechMod.MOD_ID,"player_projectile").toString()));



    public static void register(IEventBus bus){
        ENTITY_TYPES.register(bus);
    }

}
