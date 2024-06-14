package com.skycatdev.spectralspirits.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.entity.FireSpiritEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.server.network.ServerPlayerEntity;

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
        ((FireSpiritEntity) SummonCommand.summon(context.getSource(), SpectralSpirits.FIRE_SPIRIT.getRegistryEntry(), player.getPos(), new NbtCompound(), true)).setOwner(player);
        return Command.SINGLE_SUCCESS;
    }
}
