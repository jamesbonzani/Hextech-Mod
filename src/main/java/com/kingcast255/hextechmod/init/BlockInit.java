package com.kingcast255.hextechmod.init;

import com.google.common.base.Supplier;
import com.kingcast255.hextechmod.HextechMod;
import com.kingcast255.hextechmod.common.block.custom.ArcanePedestalBlock;
import com.kingcast255.hextechmod.common.block.custom.HexOreBlock;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HextechMod.MOD_ID);
	
	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;
	
	public static final RegistryObject<Block> HEX_ORE = registerBlock("hex_ore", () -> new HexOreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(10f, 10f)));
	//public static final RegistryObject<Block> HEXTECH_PEDESTAL = registerBlock("hextech_pedestal", () -> new HextechPedestalBlock(BlockBehaviour.Properties.of(Material.STONE)));
	public static final RegistryObject<Block> ARCANE_PEDESTAL = registerBlock("arcane_pedestal", () -> new ArcanePedestalBlock(BlockBehaviour.Properties.of(Material.STONE)));
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block)	{
		return BLOCKS.register(name, block);
		
	}
}

	
