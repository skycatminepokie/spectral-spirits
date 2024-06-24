package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.client.entity.SpectralSpiritEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class SpiritEventHandler {
    /**
     * Summons the player's spirit, if it's not already summoned.
     *
     * @param player The owner of the spirit to summon
     * @return {@code true} if the spirit was summoned or already existed, {@code false} if the player has no spirit profile.
     */
    public static boolean summon(ServerPlayerEntity player) {
        SpectralSpiritHolder holder = (SpectralSpiritHolder) player;
        SpectralSpiritEntity currentSpirit = holder.spectral_spirits$getSpirit();
        if (currentSpirit == null) {
            SpiritProfile profile = holder.spectral_spirits$getProfile();
            if (profile == null) {
                return false;
            }
            summonNew(profile, player);
        }
        return true;
    }

    // WARN public for testing, should be protected

    /**
     * Summons a new spirit for a player and discards an old one, if any.
     *
     * @param profile The profile of the spirit to summon.
     * @param player  The player to summon the spirit for.
     */
    public static void summonNew(@NotNull SpiritProfile profile, @NotNull ServerPlayerEntity player) {
        SpectralSpiritHolder holder = (SpectralSpiritHolder) player;
        SpectralSpiritEntity currentSpirit = holder.spectral_spirits$getSpirit();
        if (currentSpirit != null) {
            currentSpirit.discard();
        }
        holder.spectral_spirits$setSpirit(profile.spawnEntity(player.getWorld(), player));
        holder.spectral_spirits$saveProfile(profile);
    }

    public static void dismissSpirit(ServerPlayerEntity player) {
        SpectralSpiritHolder holder = ((SpectralSpiritHolder) player);
        SpectralSpiritEntity current = holder.spectral_spirits$getSpirit();
        holder.spectral_spirits$saveProfile(current.toProfile());
        holder.spectral_spirits$setSpirit(null);
        current.discard();
    }
}
