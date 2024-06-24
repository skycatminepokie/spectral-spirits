package com.skycatdev.spectralspirits.gui;

import com.skycatdev.spectralspirits.SpectralSpiritHolder;
import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.SpiritEventHandler;
import com.skycatdev.spectralspirits.SpiritProfile;
import com.skycatdev.spectralspirits.ability.FireResistanceAbility;
import com.skycatdev.spectralspirits.client.entity.SpectralSpiritEntity;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Set;

public class SummonSpiritGui extends SimpleGui {
    protected SpectralSpiritEntity currentSpirit;
    /**
     * Constructs a new simple container gui for the supplied player.
     *
     * @param player                the player to send this gui to
     */
    public SummonSpiritGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X1, player, false);
        currentSpirit = ((SpectralSpiritHolder) player).spectral_spirits$getSpirit();
        // TODO: Handle generating these builders in SpiritProfile
        addSlot(GuiElementBuilder.from(new ItemStack(Items.OAK_SIGN)).setName(Text.of("Toggle Spirit")).setCallback(this::toggleSpirit));
        addSlot(GuiElementBuilder.from(new ItemStack(Items.EMERALD)).setName(Text.of("Choose spirit type")).setCallback(() -> {
            close();
            new SpiritTypeGui(player).open();
        }));
        setTitle(Text.of("Spectral Spirits"));
    }

    private void toggleSpirit() {
        if (currentSpirit == null) {
            SpiritEventHandler.summonNew(new SpiritProfile(SpectralSpirits.FIRE_SPIRIT, Set.of(new FireResistanceAbility(true, true))), player);
        } else {
            SpiritEventHandler.dismissSpirit(player);
        }
        close();
    }

}
