package com.skycatdev.spectralspirits.ability;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AbilityType {
    protected @NotNull Identifier id;
    /**
     * {@code true} if this is active in the background, {@code false} if the player must activate it.
     */
    protected boolean ambient;
    protected ItemStack icon;

    protected AbilityType(@NotNull Identifier id, boolean ambient, ItemStack icon) {
        this.id = id;
        this.ambient = ambient;
        this.icon = icon;
    }

    @Contract(pure = true)
    public @NotNull Identifier getId() {
        return id;
    }

    @Contract(pure = true)
    public boolean isAmbient() {
        return ambient;
    }

    @Contract("->new")
    public @NotNull ItemStack getIcon() {
        return icon.copy();
    }
}
