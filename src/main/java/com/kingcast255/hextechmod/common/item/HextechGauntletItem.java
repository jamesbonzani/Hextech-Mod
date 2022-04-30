package com.kingcast255.hextechmod.common.item;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


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
		stack.getTag().putBoolean("hextechmod.charged", true);
	}
	
	
	public void removeFullyCharged(ItemStack stack) {
		CompoundTag empty = new CompoundTag();
		
		stack.setTag(empty);
		
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
			CompoundTag tag = new CompoundTag();
			stack.getTag().putBoolean("hextechmod.player_pushed",true);

			removeFullyCharged(stack);
			
			if (!level.isClientSide) {
		        player.getCooldowns().addCooldown(stack.getItem(), 10);
		        
			}
		}
		
	}

	/*
	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity ent, int x, boolean b) {
		if (!level.isClientSide && ent instanceof Player) {
			if (stack.hasTag() && stack.getTag().get("hextechmod.player_pushed") != null) {
				if (stack.getTag().getBoolean("hextechmod.player_pushed")) {
					if (((Player) ent).isOnGround()) {
						stack.getTag().putBoolean("hextechmod.player_pushed", false);
					} else {
						java.util.List<VoxelShape> collisions = level.getEntityCollisions(
								ent, new AABB(ent.getX() - 10D, ent.getY() - 10D, ent.getZ() - 10D,
										ent.getX() + 10D, ent.getY() + 10D, ent.getZ() + 10D)
						);
						for (VoxelShape vshape : collisions) {
							if (!vshape.isEmpty()) {
								((Player) ent).sendMessage(new TextComponent("Collided with Entity"), ent.getUUID());
							}
						}

					}
				}
			}
		}
	}
*/
	public UseAnim getUseAnimation(ItemStack p_40678_) {
		      return UseAnim.BOW;
		   }
	
	
	   public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand hand) {
		   		ItemStack itemStackIn = playerIn.getItemInHand(hand);
		   		playerIn.startUsingItem(hand);
		   		
		    return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStackIn);
		      }
		   


	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}	
