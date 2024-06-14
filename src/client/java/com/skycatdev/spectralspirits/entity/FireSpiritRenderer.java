package com.skycatdev.spectralspirits.entity;

import com.skycatdev.spectralspirits.SpectralSpiritsClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static com.skycatdev.spectralspirits.SpectralSpirits.MOD_ID;

public class FireSpiritRenderer extends MobEntityRenderer<FireSpiritEntity, FireSpiritModel<FireSpiritEntity>> {
    public FireSpiritRenderer(EntityRendererFactory.Context context) {
        super(context, new FireSpiritModel<>(context.getPart(SpectralSpiritsClient.FIRE_SPIRIT_MODEL_LAYER)), 0f);
    }

    @Override
    public Identifier getTexture(FireSpiritEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/spectral_spirit/fire_spirit.png");
    }
}
