package com.skycatdev.spectralspirits.ability;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class FireResistanceAbility extends BooleanAbility {
    public static final MapCodec<FireResistanceAbility> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("enabled").forGetter(FireResistanceAbility::isEnabled),
            Codec.BOOL.fieldOf("active").forGetter(FireResistanceAbility::isActive)
    ).apply(instance, FireResistanceAbility::new));

    public FireResistanceAbility(boolean enabled, boolean active) {
        super(enabled, active);
    }

    @Override
    public AbilityType<FireResistanceAbility> getType() {
        return AbilityTypes.FIRE_RESISTANCE;
    }
}
