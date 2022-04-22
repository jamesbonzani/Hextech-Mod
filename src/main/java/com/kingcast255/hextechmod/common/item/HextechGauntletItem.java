package com.kingcast255.hextechmod.common.item;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;


public class HextechGauntletItem extends Item {

	

	
	
	
	
	public HextechGauntletItem(Properties properties) {
		super(properties);
	}
	
	
   
  public float getForce(ItemStack stack, int timeLeft) {
	    int i = getUseDuration(stack) - timeLeft;
	    float f = i / 20.0F;
	    f = (f * f + f * 2.0F) / 3.0F;
	    f *= 4f;

	    if (f > 2f) {
	      f = 2f;
	    }
	    return f;
	  }

	public int getUseDuration(ItemStack p_40680_) {
		return 72000; 
	}
	
	
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		
		return true;
	}
	
	
	
	
	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		float x = getForce(stack,count);
		if (x==2) {
			setFullyCharged(stack);
			
		}
		//player.sendMessage(new TextComponent(""+x), player.getUUID());
		
	}
	
	
	@Override
	public boolean isFoil(ItemStack stack) {
		boolean value = false;
		if (stack.hasTag()) {
			if (stack.getTag().getBoolean("hextechmod.charged")) {
				value = true;
			}
		}
		return value;
	}
	
	
	
	
	
	
	public void setFullyCharged(ItemStack stack) {
		
		CompoundTag data = new CompoundTag();
		data.putBoolean("hextechmod.charged", true);
		
		stack.setTag(data);
		
	}
	
	
	public void removeFullyCharged(ItemStack stack) {
		CompoundTag empty = new CompoundTag();
		
		stack.setTag(empty);
		
	}
	
	
	

	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity ent) {
		
		
		if(ent instanceof Player) {
			Player player = (Player)ent;
			

		if (player != null) {

			player.sendMessage(new TextComponent("Charged." ), player.getUUID());
		}
	
		if(!level.isClientSide()) {
			BlockHitResult result = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
			
			//player.sendMessage(Component.nullToEmpty(pos.toString()), Util.NIL_UUID);
			
			//level.setBlockAndUpdate(result.getBlockPos(), Blocks.AIR.defaultBlockState());
			Direction dir = result.getDirection();
			//player.sendMessage(Component.nullToEmpty(dir.toString()), Util.NIL_UUID);
			BlockPos pos = result.getBlockPos();
			BlockPos[] group = new BlockPos[9];
			switch(dir) {
			case NORTH:
				group[0] = pos;
				group[1] = pos.above();
				group[2] = pos.below();
				group[3] = pos.east();
				group[4] = pos.east().above();
				group[5] = pos.east().below();
				group[6] = pos.west();
				group[7] = pos.west().above();
				group[8] = pos.west().below();
				break;
			case EAST:
				group[0] = pos;
				group[1] = pos.above();
				group[2] = pos.below();
				group[3] = pos.north();
				group[4] = pos.north().above();
				group[5] = pos.north().below();
				group[6] = pos.south();
				group[7] = pos.south().above();
				group[8] = pos.south().below();
				break;
			case WEST:
				group[0] = pos;
				group[1] = pos.above();
				group[2] = pos.below();
				group[3] = pos.north();
				group[4] = pos.north().above();
				group[5] = pos.north().below();
				group[6] = pos.south();
				group[7] = pos.south().above();
				group[8] = pos.south().below();
				break;
			case SOUTH:
				group[0] = pos;
				group[1] = pos.above();
				group[2] = pos.below();
				group[3] = pos.east();
				group[4] = pos.east().above();
				group[5] = pos.east().below();
				group[6] = pos.west();
				group[7] = pos.west().above();
				group[8] = pos.west().below();
				break;
			case UP:
				group[0] = pos;
				group[1] = pos.north();
				group[2] = pos.south();
				group[3] = pos.east();
				group[4] = pos.west();
				group[5] = pos.east().south();
				group[6] = pos.south().west();
				group[7] = pos.west().north();
				group[8] = pos.north().east();
				break;
			case DOWN:
				group[0] = pos;
				group[1] = pos.north();
				group[2] = pos.south();
				group[3] = pos.east();
				group[4] = pos.west();
				group[5] = pos.east().south();
				group[6] = pos.south().west();
				group[7] = pos.west().north();
				group[8] = pos.north().east();
				break;
				
			
			default:
				group = null;
				break;
			}
			if (group != null) {
			for (BlockPos p : group) {
					if(level.getBlockState(p).getDestroySpeed(level, p) > 0.0F) {
						level.destroyBlock(p, canRepair);
					}	}
				}
			level.broadcastEntityEvent(player, (byte) 26);
			
			
						
			
			
			
				}	
		
		}
		return super.finishUsingItem(stack, level, ent);
	}
	
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		
		return 1;
	}
	
	
	
	
	
	//releasing right click boosts the player in the direction they look
	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity ent, int x) {
		
		if (!ent.isOnGround() || !(ent instanceof Player)) {
			return;
		}
		
		Player player = (Player)ent;
		
		float f = getForce(stack, x);
		if(f == 2) {
			Vec3 vec = player.getLookAngle().normalize();
			player.push(vec.x*f, vec.y*f, vec.z*f);
			removeFullyCharged(stack);
			
			if (!level.isClientSide) {
		        player.getCooldowns().addCooldown(stack.getItem(), 10);
		        
			}
		}
		
	}
	
	
	
	   public UseAnim getUseAnimation(ItemStack p_40678_) {
		      return UseAnim.BOW;
		   }
	
	
	   public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand hand) {
		   		ItemStack itemStackIn = playerIn.getItemInHand(hand);
		   		playerIn.startUsingItem(hand);
		   		
		    return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStackIn);
		      }
		   


	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}	
