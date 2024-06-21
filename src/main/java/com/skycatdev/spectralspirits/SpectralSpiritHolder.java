package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;

public interface SpectralSpiritHolder {
    /**
     * Gets the holder's spirit entity
     *
     * @return The holder's spirit entity
     */
    SpectralSpiritEntity spectral_spirits$getSpirit();

    /**
     * Sets the holder's spirit entity. This is the holder remembering the spirit, but doesn't go the other way around.
     */
    void spectral_spirits$setSpirit(SpectralSpiritEntity spirit);

    /**
     * Gets the currently saved profile of {@link SpectralSpiritHolder#spectral_spirits$getSpirit()}.
     *
     * @return A copy of the saved profile - mutations won't affect {@code this}!
     */
    SpiritProfile spectral_spirits$getProfile();

    /**
     * Saves the given profile as this holder's profile.
     *
     * @param profile The profile to save
     */
    void spectral_spirits$saveProfile(SpiritProfile profile);
}
