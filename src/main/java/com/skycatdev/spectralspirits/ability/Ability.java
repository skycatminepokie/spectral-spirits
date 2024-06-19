package com.skycatdev.spectralspirits.ability;

import com.mojang.serialization.Codec;
import eu.pb4.sgui.api.elements.GuiElement;
import org.jetbrains.annotations.Contract;

public abstract class Ability {
    public static final Codec<Ability> CODEC = AbilityTypes.REGISTRY.getCodec().dispatch("type", Ability::getType, AbilityType::getCodec);
    /**
     * <p>{@code true} if the ability is on, {@code false} otherwise.</p>
     * <p>This means that the ability can be used, but is not necessarily active.</p>
     */
    protected boolean enabled;
    /**
     * <p>{@code true} if the ability is active, {@code false} otherwise.</p>
     * <p>This means that the ability is being used, but not necessarily enabled (the player may turn it off while it is active).</p>
     */
    protected boolean active;

    public Ability(boolean enabled, boolean active) {
        this.enabled = enabled;
        this.active = active;
    }

    @Contract("->new")
    public abstract GuiElement getGuiElement();

    public abstract AbilityType<? extends Ability> getType();

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
