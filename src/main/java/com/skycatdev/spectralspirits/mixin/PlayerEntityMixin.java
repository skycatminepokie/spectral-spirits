package com.skycatdev.spectralspirits.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.skycatdev.spectralspirits.SpectralSpiritHolder;
import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.SpiritProfile;
import com.skycatdev.spectralspirits.ability.AbilityTypes;
import com.skycatdev.spectralspirits.client.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("UnstableApiUsage")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements SpectralSpiritHolder {
    @Unique
    public @Nullable SpectralSpiritEntity spectral_spirits$spectral_spirit;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "remove", at = @At("TAIL"))
    protected void spectral_spirits$onRemove(CallbackInfo ci) {
        if (spectral_spirits$spectral_spirit != null) {
            setAttached(SpectralSpirits.SPECTRAL_SPIRIT_ATTACHMENT, spectral_spirits$spectral_spirit.toProfile());
            spectral_spirits$spectral_spirit.discard();
            spectral_spirits$spectral_spirit = null;
        }
    }

    @ModifyReturnValue(method = "isInvulnerableTo", at = @At("RETURN"))
    protected boolean spectral_spirits$modifyIsInvulnerableTo(boolean original, @Local(ordinal = 0, argsOnly = true) DamageSource dmgSource) { // TODO: Maybe find a better way
        return original || (spectral_spirits$spectral_spirit != null && dmgSource.isIn(DamageTypeTags.IS_FIRE) && spectral_spirits$spectral_spirit.hasActiveAbility(AbilityTypes.FIRE_RESISTANCE));
    }

    @Unique
    @Override
    public SpectralSpiritEntity spectral_spirits$getSpirit() {
        return spectral_spirits$spectral_spirit;
    }

    @Unique
    @Override
    public void spectral_spirits$setSpirit(SpectralSpiritEntity spirit) {
        spectral_spirits$spectral_spirit = spirit;
    }

    @Override
    public SpiritProfile spectral_spirits$getProfile() {
        return getAttached(SpectralSpirits.SPECTRAL_SPIRIT_ATTACHMENT);
    }

    @Override
    public void spectral_spirits$saveProfile(SpiritProfile profile) {
        setAttached(SpectralSpirits.SPECTRAL_SPIRIT_ATTACHMENT, profile);
    }
}
