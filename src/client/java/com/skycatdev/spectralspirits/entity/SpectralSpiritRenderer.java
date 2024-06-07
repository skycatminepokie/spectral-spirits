package com.skycatdev.spectralspirits.entity;

import com.skycatdev.spectralspirits.SpectralSpiritsClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static com.skycatdev.spectralspirits.SpectralSpirits.MOD_ID;

public class SpectralSpiritRenderer extends MobEntityRenderer<SpectralSpiritEntity, SpectralSpiritModel> {
    public SpectralSpiritRenderer(EntityRendererFactory.Context context) {
        super(context, new SpectralSpiritModel(context.getPart(SpectralSpiritsClient.SPECTRAL_SPIRIT_MODEL_LAYER)), 0f);
    }

    @Override
    public Identifier getTexture(SpectralSpiritEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/spectral_spirit/spectral_spirit.png");
    }
}
