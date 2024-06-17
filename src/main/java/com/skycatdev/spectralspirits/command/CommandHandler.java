package com.skycatdev.spectralspirits.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.skycatdev.spectralspirits.SpectralSpiritHolder;
import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.SpiritProfile;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandHandler {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        var spectralSpirits = literal("spectralSpirits")
                .requires((source) -> source.hasPermissionLevel(3))
                .build();
        var grant = literal("grant")
                .requires((source) -> source.hasPermissionLevel(3))
                .build();
        var grantPlayer = argument("player", EntityArgumentType.player())
                .requires((source) -> source.hasPermissionLevel(3))
                .executes(CommandHandler::grantSpectral)
                .build();
        spectralSpirits.addChild(grant);
        grant.addChild(grantPlayer);

        dispatcher.getRoot().addChild(spectralSpirits);
    }

    private static int grantSpectral(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
        var profile = player.getAttachedOrSet(SpectralSpirits.SPECTRAL_SPIRIT_ATTACHMENT, new SpiritProfile(new ArrayList<>()));
        var spirit = profile.createEntity(SpectralSpirits.FIRE_SPIRIT, context.getSource().getWorld(), player);
        ((SpectralSpiritHolder) player).setSpirit(spirit);
        return Command.SINGLE_SUCCESS;
    }
}
