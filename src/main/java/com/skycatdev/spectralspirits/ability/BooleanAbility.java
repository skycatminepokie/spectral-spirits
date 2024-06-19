package com.skycatdev.spectralspirits.ability;

import eu.pb4.sgui.api.elements.GuiElement;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.text.Text;

public abstract class BooleanAbility extends Ability {

    public BooleanAbility(boolean enabled, boolean active) {
        super(enabled, active);
    }

    @Override
    public GuiElement getGuiElement() {
        return GuiElementBuilder.from(getType().getIcon()).addLoreLine(Text.of(enabled ? "ENABLED" : "DISABLED")).build(); // TODO Localize
    }
}
