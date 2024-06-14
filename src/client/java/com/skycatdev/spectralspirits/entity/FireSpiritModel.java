package com.skycatdev.spectralspirits.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;



public class FireSpiritModel<T extends FireSpiritEntity> extends EntityModel<T> {
    private final ModelPart spirit;
    public FireSpiritModel(ModelPart root) {
        super(RenderLayer::getEntityTranslucent);
        this.spirit = root.getChild("spirit");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData spirit = modelPartData.addChild("spirit", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData shell = spirit.addChild("shell", ModelPartBuilder.create().uv(2, 8).cuboid(-4.0F, -4.0F, -7.0F, 8.0F, 0.0F, 14.0F, new Dilation(0.0F))
                .uv(-14, 0).cuboid(-4.0F, 4.0F, -7.0F, 8.0F, 0.0F, 14.0F, new Dilation(0.0F))
                .uv(16, -14).cuboid(-4.0F, -4.0F, -7.0F, 0.0F, 8.0F, 14.0F, new Dilation(0.0F))
                .uv(32, -6).cuboid(4.0F, -4.0F, -7.0F, 0.0F, 8.0F, 14.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-4.0F, -4.0F, 7.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 1.0F));

        ModelPartData core = spirit.addChild("core", ModelPartBuilder.create().uv(22, 16).cuboid(-3.0F, -1.0F, -5.0F, 6.0F, 6.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 1.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        spirit.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}