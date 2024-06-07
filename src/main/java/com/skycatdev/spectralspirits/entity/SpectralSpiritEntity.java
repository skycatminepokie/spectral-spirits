package com.skycatdev.spectralspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SpectralSpiritEntity extends PathAwareEntity implements Ownable {
    protected PlayerEntity owner;

    public SpectralSpiritEntity(EntityType<? extends SpectralSpiritEntity> entityType, World world) {
        super(entityType, world);
        // setNoGravity(true);
        noClip = true;
        owner = null;
        moveControl = new FlightMoveControl(this, 1, true);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        // goalSelector.add(1, new WanderNearTargetGoal(this, 3, 32));
        goalSelector.add(0, new FollowOwnerGoal(this::getOwner));
    }

    @Override
    public boolean damage(DamageSource damageSource, float amount) {
        if (damageSource.getAttacker() instanceof ServerPlayerEntity player) {
            owner = player;
        }
        return super.damage(damageSource, amount);
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true); // TODO later: Check if there's a better way to do this. Vexes just set it every tick.
    }

    @Override
    public boolean isOnGround() {
        return true;
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return owner;
    }

    public class FollowOwnerGoal extends Goal {
        public Supplier<Entity> owner;

        public FollowOwnerGoal(Supplier<Entity> owner) {
            this.owner = owner;
        }

        @Override
        public boolean canStart() {
            return owner.get() != null && !getMoveControl().isMoving();
        }

        @Override
        public void tick() {
            Entity owner = this.owner.get();
            if (squaredDistanceTo(owner) > 9) {
                moveControl.moveTo(owner.getX(), owner.getY() + 1, owner.getZ(), 0.75);
            }
        }
    }

}
