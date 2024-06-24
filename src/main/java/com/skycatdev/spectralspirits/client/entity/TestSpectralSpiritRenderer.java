package com.skycatdev.spectralspirits.client.entity;

import com.skycatdev.spectralspirits.client.SpectralSpiritsClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static com.skycatdev.spectralspirits.SpectralSpirits.MOD_ID;

public class TestSpectralSpiritRenderer extends MobEntityRenderer<TestSpectralSpiritEntity, TestSpectralSpiritModel<TestSpectralSpiritEntity>> {
    public TestSpectralSpiritRenderer(EntityRendererFactory.Context context) {
        super(context, new TestSpectralSpiritModel<>(context.getPart(SpectralSpiritsClient.SPECTRAL_SPIRIT_MODEL_LAYER)), 0f);
    }

    @Override
    public Identifier getTexture(TestSpectralSpiritEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/spectral_spirit/test_spectral_spirit.png");
    }
}
