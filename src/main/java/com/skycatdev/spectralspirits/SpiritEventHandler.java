package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpiritEventHandler {
    // WARN public for testing, should be protected
    public static void summon(SpiritProfile profile, ServerPlayerEntity player) {
        SpectralSpiritEntity currentSpirit = ((SpectralSpiritHolder)player).spectral_spirits$getSpirit();
        if (currentSpirit != null) {
            currentSpirit.discard();
        }
        ((SpectralSpiritHolder)player).spectral_spirits$setSpirit(profile.spawnEntity(player.getWorld(), player));
        ((SpectralSpiritHolder) player).spectral_spirits$saveProfile(profile);
    }

    public static void dismissSpirit(ServerPlayerEntity player) {
        SpectralSpiritHolder holder = ((SpectralSpiritHolder) player);
        SpectralSpiritEntity current = holder.spectral_spirits$getSpirit();
        holder.spectral_spirits$saveProfile(current.toProfile());
        holder.spectral_spirits$setSpirit(null);
        current.discard();
    }
}
