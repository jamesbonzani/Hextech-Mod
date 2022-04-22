package com.kingcast255.hextechmod.common.block.entity;

import com.kingcast255.hextechmod.HextechMod;
import com.kingcast255.hextechmod.init.BlockInit;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HextechMod.MOD_ID);
	

	
	//Entities
	public static final RegistryObject<BlockEntityType<ArcanePedestalBlockEntity>> ARCANE_PEDESTAL = BLOCK_ENTITIES.register("arcane_pedestal",
			() -> BlockEntityType.Builder.of(ArcanePedestalBlockEntity::new, BlockInit.ARCANE_PEDESTAL.get()).build(null));
	
	
	
	public static void register(IEventBus bus) {
		BLOCK_ENTITIES.register(bus);
	}
}
