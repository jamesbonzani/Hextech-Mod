package com.kingcast255.hextechmod.init;

import com.google.common.base.Supplier;
import com.kingcast255.hextechmod.HextechMod;
import com.kingcast255.hextechmod.common.item.HextechGauntletItem;
import com.kingcast255.hextechmod.common.item.HextechStoneItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HextechMod.MOD_ID);
	
	
	
	//Items 
	
	public static final RegistryObject<Item> HEXTECH_STONE = register("hextech_stone", 	() -> new HextechStoneItem(new Item.Properties().tab(HextechMod.HEXTECH_TAB)));
 
	public static final RegistryObject<Item> HEXTECH_GAUNTLET = register("hextech_gauntlet", () -> new HextechGauntletItem(new Item.Properties().tab(HextechMod.HEXTECH_TAB).stacksTo(1)));
	
	public static final RegistryObject<Item> BRONZE_INGOT = register("bronze_ingot", () -> new Item(new Item.Properties().tab(HextechMod.HEXTECH_TAB)));
	public static final RegistryObject<Item> BRONZE_NUGGET = register("bronze_nugget", () -> new Item(new Item.Properties().tab(HextechMod.HEXTECH_TAB)));
	
	
	
	
	
	
	
	
	
	
	//Block Items
	
	public static final RegistryObject<BlockItem> HEX_ORE = register("hex_ore", () -> new BlockItem(BlockInit.HEX_ORE.get(),new Item.Properties().tab(HextechMod.HEXTECH_TAB)));
	//public static final RegistryObject<BlockItem> HEXTECH_PEDESTAL = register("hextech_pedestal",  () -> new BlockItem(BlockInit.HEXTECH_PEDESTAL.get(),new Item.Properties().tab(HextechMod.HEXTECH_TAB)));
	public static final RegistryObject<BlockItem> ARCANE_PEDESTAL = register("arcane_pedestal",  () -> new BlockItem(BlockInit.ARCANE_PEDESTAL.get(),new Item.Properties().tab(HextechMod.HEXTECH_TAB)));
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item)	{
		return ITEMS.register(name, item);
	}
	
	
	
}
