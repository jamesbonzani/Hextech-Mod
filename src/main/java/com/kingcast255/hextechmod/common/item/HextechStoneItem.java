package com.kingcast255.hextechmod.common.item;



import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;


public class HextechStoneItem extends Item {

	public HextechStoneItem(Properties properties) {
		super(properties);
		
	}
	

	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) { 
	
		
		
		
		
		return super.onDroppedByPlayer(item, player);
	}
	
	
	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> smelting) {
		
		return 16000;
	}

}
