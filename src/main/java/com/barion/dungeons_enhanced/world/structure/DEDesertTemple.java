package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEBaseStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEGroundStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEDesertTemple extends DEGroundStructure {
    public static final String ID = "desert_temple";
    public static final Codec<DEDesertTemple> CODEC = simpleCodec(DEDesertTemple::new);
    private static final ResourceLocation BOTTOM = location("desert_temple/down");
    public DEDesertTemple(StructureSettings structureSettings) {super(structureSettings, DEUtil.pieceBuilder().yOffset(-6).add("desert_temple/main").build(), DEStructures.DESERT_TEMPLE::getType);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final var rawPos = getGenPos(context.chunkPos());
        final var piece = Templates.getRandom(context.random());
        final var size = context.structureTemplateManager().getOrCreate(piece.Resource).getSize();

        if(!DETerrainAnalyzer.areNearbyBiomesValid(context.biomeSource(), rawPos, context.chunkGenerator(), 20, context.validBiome(), context.randomState())) {return Optional.empty();}

        var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 6, context.chunkGenerator(), context.heightAccessor(), context.randomState());
//        if(!result.getSecond()) {return Optional.empty();}

        final var pos = rawPos.atY(Math.round(result.getFirst())).above(piece.yOffset);
        return at(pos, (builder)-> generatePieces(builder, pos, piece, Rotation.NONE, context, DEDesertTemple::assembleTemple));
    }

    public static void assembleTemple(DEPieceAssembler.Context context) {
        var pos = context.pos();
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), pos, context.rotation()));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), BOTTOM, pos.offset(15, -11, 2), context.rotation()));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), BOTTOM, pos.offset(25, -11, 16), context.rotation()));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), BOTTOM, pos.offset(13, -11, 14), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.DESERT_TEMPLE.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {
            super(DEStructures.DESERT_TEMPLE.getPieceType(), context, nbt);
        }
    }
}