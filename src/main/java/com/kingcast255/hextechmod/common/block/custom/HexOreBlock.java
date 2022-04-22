package com.kingcast255.hextechmod.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HexOreBlock extends Block {

	public HexOreBlock(Properties properties) {

		super(properties);
		
	}



	

	
	
	
	@Override
	public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		
		float f = 4.0F;
		level.explode(null, pos.getX(), pos.getY(), pos.getZ(), f, null);
		
		super.playerWillDestroy(level, pos, state, player);
	}
	



}
