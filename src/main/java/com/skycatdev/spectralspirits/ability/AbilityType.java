package com.skycatdev.spectralspirits.ability;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AbilityType {
    protected @NotNull Identifier id;
    protected boolean passive;
    protected ItemStack icon;

    protected AbilityType(@NotNull Identifier id, boolean passive, ItemStack icon) {
        this.id = id;
        this.passive = passive;
        this.icon = icon;
    }

    @Contract(pure = true)
    public @NotNull Identifier getId() {
        return id;
    }

    @Contract(pure = true)
    public boolean isPassive() {
        return passive;
    }

    @Contract("->new")
    public @NotNull ItemStack getIcon() {
        return icon.copy();
    }
}
