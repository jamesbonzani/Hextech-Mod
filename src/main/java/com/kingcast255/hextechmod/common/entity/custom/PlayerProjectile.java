package com.kingcast255.hextechmod.common.entity.custom;

import com.kingcast255.hextechmod.common.item.HextechGauntletItem;
import com.kingcast255.hextechmod.common.util.ModItemProperties;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class PlayerProjectile extends Projectile {

	public PlayerProjectile(EntityType<? extends PlayerProjectile> p_37248_, Level p_37249_) {
		super(p_37248_, p_37249_);

	}

	@Override
	protected void defineSynchedData() {

	}



	@Override
	public void tick() {
		this.setDeltaMovement(this.getOwner().getDeltaMovement());

        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
           this.onHit(hitresult);
        }

		super.tick();
	}


	@Override
	protected void onHit(HitResult p_37260_) {
		super.onHit(p_37260_);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		int i = 0;
		if (this.getOwner() instanceof Player) {
			((Player)this.getOwner()).sendMessage(this.getOwner().getName(),this.getOwner().getUUID());
			if (((Player)this.getOwner()).getItemInHand(((Player) this.getOwner()).getUsedItemHand()).getItem() instanceof HextechGauntletItem){
				i = ((Player)this.getOwner()).getItemInHand(((Player) this.getOwner()).getUsedItemHand()).getDamageValue();
			}
		}
		entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
	}


	

}
