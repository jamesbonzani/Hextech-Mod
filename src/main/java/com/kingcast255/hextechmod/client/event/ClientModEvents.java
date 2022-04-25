package com.kingcast255.hextechmod.client.event;

import com.kingcast255.hextechmod.HextechMod;
import com.kingcast255.hextechmod.client.renderer.ArcanePedestalBER;
import com.kingcast255.hextechmod.common.block.entity.ModBlockEntities;
import com.kingcast255.hextechmod.init.BlockInit;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = HextechMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)

public final class ClientModEvents {

	
	
	private ClientModEvents() {
	}
	
	
	@SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
		 ItemBlockRenderTypes.setRenderLayer(BlockInit.ARCANE_PEDESTAL.get(), RenderType.cutout());
	}
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		
		
		event.registerBlockEntityRenderer(ModBlockEntities.ARCANE_PEDESTAL.get(), ArcanePedestalBER::new);
	}
}
