package com.kingcast255.hextechmod.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class PlayerProjectile extends Projectile{

	protected PlayerProjectile(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
		super(p_37248_, p_37249_);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void defineSynchedData() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void tick() {
	
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
           this.onHit(hitresult);
		
        }
	}
	
	@Override
	protected void onHit(HitResult p_37260_) {
		
		
		
	}
	

}
