package com.skycatdev.spectralspirits.ability;

import eu.pb4.sgui.api.elements.GuiElement;
import org.jetbrains.annotations.Contract;

public abstract class Ability { // TODO: Use this instead of raw AbilityTypes in SpiritProfile
    /**
     * <p>{@code true} if the ability is on, {@code false} otherwise.</p>
     * <p>This means that the ability can be used, but is not necessarily active.</p>
     */
    protected boolean on;
    /**
     * <p>{@code true} if the ability is active, {@code false} otherwise.</p>
     * <p>This means that the ability is being used, but not necessarily active (the player may turn it off while it is active).</p>
     */
    protected boolean active;
    public boolean isOn() {
        return on;
    }

    public boolean isActive() {
        return active;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Contract("->new")
    public abstract GuiElement getGuiElement();
}
