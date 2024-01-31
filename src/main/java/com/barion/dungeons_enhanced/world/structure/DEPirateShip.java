package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.structure.prefabs.DESwimmingStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEPirateShip extends DESwimmingStructure {
    public static final String ID = "pirate_ship";
    public static final Codec<DEPirateShip> CODEC = simpleCodec(DEPirateShip::new);
    private static final ResourceLocation BACK = location("pirate_ship/back");
    public DEPirateShip(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().yOffset(-3).add("pirate_ship/front").build(), DEStructures.PIRATE_SHIP::getType);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final var piece = Templates.getRandom(context.random());
        final var pos = getGenPos(context).above(piece.yOffset);

        return at(pos, (builder) -> generatePieces(builder, pos, piece, Rotation.NONE, context, DEPirateShip::assembleShip));
    }

    public static void assembleShip(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), BACK, context.pos().offset(25, 0, 0), context.rotation()));
    }
}