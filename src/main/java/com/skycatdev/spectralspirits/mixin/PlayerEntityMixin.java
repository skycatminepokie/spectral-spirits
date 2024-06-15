package com.skycatdev.spectralspirits.mixin;

import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Unique
    public SpectralSpiritEntity spectral_spirits$spectral_spirit;

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

}
