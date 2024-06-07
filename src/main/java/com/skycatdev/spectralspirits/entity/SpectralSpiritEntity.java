package com.skycatdev.spectralspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SpectralSpiritEntity extends PathAwareEntity implements Ownable {
    protected PlayerEntity owner;

    public SpectralSpiritEntity(EntityType<? extends SpectralSpiritEntity> entityType, World world) {
        super(entityType, world);
        // setNoGravity(true);
        // noClip = true;
        owner = null;
        moveControl = new SpectralSpiritMoveControl(this);
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
        //this.setNoGravity(true); // TODO later: Check if there's a better way to do this. Vexes just set it every tick.
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
            if (squaredDistanceTo(owner) > 4) {
                moveControl.moveTo(owner.getX(), owner.getY() + 1, owner.getZ(), 1);
            }
        }
    }

    public class SpectralSpiritMoveControl extends MoveControl { // Heavily adapted from VexEntity
        public SpectralSpiritMoveControl(MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
            if (this.state != MoveControl.State.MOVE_TO) {
                return;
            }
            Vec3d vec3d = new Vec3d(this.targetX - getX(), this.targetY - getY(), this.targetZ - getZ());
            double d = vec3d.length();
            if (d < getBoundingBox().getAverageSideLength()) {
                this.state = MoveControl.State.WAIT;
                setVelocity(getVelocity().multiply(0.5));
            } else {
                setVelocity(getVelocity().add(vec3d.multiply(this.speed * 0.05 / d)));
                 if (getTarget() == null) {
                     Vec3d vec3d2 = getVelocity();
                     setYaw(-((float) MathHelper.atan2(vec3d2.x, vec3d2.z)) * 57.295776f);
                     bodyYaw = getYaw();
                 } else {
                     double e = getTarget().getX() - getX();
                     double f = getTarget().getZ() - getZ();
                     setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776f);
                     bodyYaw = getYaw();
                 }
            }
        }
    }
}
