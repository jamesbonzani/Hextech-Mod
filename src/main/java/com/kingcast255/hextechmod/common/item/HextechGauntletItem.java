package com.kingcast255.hextechmod.common.item;


import com.kingcast255.hextechmod.common.entity.ModEntityTypes;
import com.kingcast255.hextechmod.common.entity.custom.PlayerProjectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
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
			PlayerProjectile proj = new PlayerProjectile(ModEntityTypes.PLAYER_PROJECTILE.get(),level);
			proj.setOwner(player);


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
