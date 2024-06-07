package com.skycatdev.spectralspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SpectralSpiritEntity extends PathAwareEntity implements Ownable {
    protected PlayerEntity owner;

    protected SpectralSpiritEntity(EntityType<? extends SpectralSpiritEntity> entityType, World world, PlayerEntity owner) {
        super(entityType, world);
        this.owner = owner;
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return owner;
    }
}
