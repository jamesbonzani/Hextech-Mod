package com.kingcast255.hextechmod.client.renderer;

import com.kingcast255.hextechmod.common.block.entity.ArcanePedestalBlockEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;

import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;



public class ArcanePedestalBER implements BlockEntityRenderer<ArcanePedestalBlockEntity>{


	
	public ArcanePedestalBER(BlockEntityRendererProvider.Context context) {
		
	}
	
	
	@Override
	public void render(ArcanePedestalBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer,
			int combinedOverlay, int packedLight) {
		
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		
		ItemStack itemStack = entity.getInventory().getStackInSlot(0);
		//System.out.print(itemStack.getHoverName().getString());
		stack.pushPose();
		stack.translate(0.5f, 0.925f+0.05*Math.cos(2*entity.getRotation()*Math.PI/180), 0.5f);
		stack.scale(0.25f, 0.25f, 0.25f);
		stack.mulPose(Vector3f.YN.rotationDegrees(entity.getRotation()));
		
		itemRenderer.renderStatic(itemStack, TransformType.NONE, combinedOverlay, packedLight, stack, buffer, 0);
		stack.popPose();
		entity.addRotation(1f);
	}

	
	
	
	
}
