package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpiritEventHandler {
    // WARN public for testing, should be protected
    public static void summon(SpiritProfile profile, ServerPlayerEntity player) {
        SpectralSpiritEntity currentSpirit = ((SpectralSpiritHolder)player).spectral_spirits$getSpirit();
        currentSpirit.discard();
        profile.spawnEntity(player.getWorld(), player);
        ((SpectralSpiritHolder)player).spectral_spirits$setSpirit(profile.spawnEntity(player.getWorld(), player));
    }

    protected static void saveSpirit(ServerPlayerEntity player) { // TODO: Move this to SpectralSpiritHolder?
        SpectralSpiritEntity currentSpirit = ((SpectralSpiritHolder)player).spectral_spirits$getSpirit();
        player.setAttached(SpectralSpirits.SPECTRAL_SPIRIT_ATTACHMENT, currentSpirit.toProfile());
    }
}
