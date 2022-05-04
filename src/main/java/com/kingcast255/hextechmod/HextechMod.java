package com.kingcast255.hextechmod;

import com.kingcast255.hextechmod.common.block.entity.ModBlockEntities;
import com.kingcast255.hextechmod.common.entity.ModEntityTypes;
import com.kingcast255.hextechmod.common.util.ModItemProperties;
import com.kingcast255.hextechmod.init.BlockInit;
import com.kingcast255.hextechmod.init.ItemInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod("hextechmod")
public class HextechMod {

	
	public static final String MOD_ID = "hextechmod";
	
	public static final CreativeModeTab HEXTECH_TAB = new CreativeModeTab(MOD_ID) {
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.HEXTECH_STONE.get());
		}
		
	};
	
	public HextechMod() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);
		
		ModBlockEntities.BLOCK_ENTITIES.register(bus);
		MinecraftForge.EVENT_BUS.register(this);

		ModEntityTypes.register(bus);


		bus.addListener(this::clientSetup);
		
		
		
		
		
	}
	
	private void clientSetup(final FMLClientSetupEvent event) {
		
		ModItemProperties.addCustomItemProperties();
		
		
	}
	
	
	
	
	
	
}

