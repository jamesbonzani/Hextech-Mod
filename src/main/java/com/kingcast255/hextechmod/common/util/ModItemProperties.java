package com.kingcast255.hextechmod.common.util;

import com.kingcast255.hextechmod.HextechMod;
import com.kingcast255.hextechmod.init.ItemInit;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {

	public static void addCustomItemProperties() {
		

		ItemProperties.register(ItemInit.HEXTECH_GAUNTLET.get(), new ResourceLocation(HextechMod.MOD_ID, "charged"), (pStack, pLevel, pEntity, pSeed) -> {
		         if (pStack == null || pEntity.getUseItem() != pStack ) {
		            return 0F;
		         } else {
		            return (pEntity.getUseItemRemainingTicks() > 0) ? 0f : 1.0f ;
		         }
		      });
		
	}
	
	
	
	
}
