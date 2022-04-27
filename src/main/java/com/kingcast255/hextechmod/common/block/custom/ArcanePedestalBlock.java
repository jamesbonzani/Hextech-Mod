package com.kingcast255.hextechmod.common.block.custom;

import java.util.stream.Stream;

import com.kingcast255.hextechmod.common.block.entity.ArcanePedestalBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;


public class ArcanePedestalBlock extends BaseEntityBlock {

	public ArcanePedestalBlock(Properties p_49795_) {
		super(p_49795_.noOcclusion().destroyTime(10f));
		
		
	}
	private static final VoxelShape SHAPE = Stream.of(
			Block.box(2, 0, 2, 14, 2, 14),
			Block.box(6, 2, 6, 10, 10, 10),
			Block.box(4, 10, 4, 12, 12, 12)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	
	
	
	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_,
			CollisionContext p_60558_) {
		return SHAPE;
	}
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ArcanePedestalBlockEntity(pos, state);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_49232_) {
		return RenderShape.MODEL;
	}



	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newstate,
			boolean isMoving) {
		
		if (state.getBlock() != newstate.getBlock()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof ArcanePedestalBlockEntity) {
				((ArcanePedestalBlockEntity) blockEntity).drops();
			}
		}
	}
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult result) {
		 
		  BlockEntity tile = level.getBlockEntity(pos);

	        if (tile instanceof ArcanePedestalBlockEntity altar) {
	            ItemStackHandler inventory = altar.getInventory();
	            ItemStack slot = inventory.getStackInSlot(0);

				//Check if pedestal has an item
	            if (!slot.isEmpty()) {
	                ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), slot.copy());
	                item.setNoPickUpDelay();
	                level.addFreshEntity(item);
					inventory.setStackInSlot(0, ItemStack.EMPTY);
					}
				//Pedestal is empty
				else {
	                ItemStack held = player.getItemInHand(hand);
				//Check if player has item in hand
	                if (slot.isEmpty() && !held.isEmpty()) {
						//Move 1 of item in hand to pedestal
	                    inventory.setStackInSlot(0, player.getItemInHand(hand).copy());
						inventory.getStackInSlot(0).setCount(1);
	                    player.getItemInHand(hand).shrink(1);
	                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
	                }
	            }
	        }

	        return InteractionResult.SUCCESS;
	    }


	
	
	
	
}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	

