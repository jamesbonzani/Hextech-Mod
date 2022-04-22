package com.kingcast255.hextechmod.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ArcanePedestalBlockEntity extends BlockEntity {

	public ArcanePedestalBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(ModBlockEntities.ARCANE_PEDESTAL.get(), p_155229_, p_155230_);
		
	}
	
	private final ItemStackHandler itemHandler = new ItemStackHandler(1);

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	
	
	
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return lazyItemHandler.cast();
		}
		
		return super.getCapability(cap, side);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}
	
	
	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);	
	}
	
	public ItemStackHandler getInventory(){
		return itemHandler;
	}
	
	
	
	
	
	@Override
	protected void saveAdditional(CompoundTag tag) {
		tag.put("inventory", itemHandler.serializeNBT());
		super.saveAdditional(tag);
	}
	
	
	
	public void drops() {
		SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inv.setItem(i, itemHandler.getStackInSlot(i));
		}
		
		Containers.dropContents(this.level, this.worldPosition, inv);
		
	}

		
		
		

}
