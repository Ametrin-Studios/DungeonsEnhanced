package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

public class DESimpleStructure extends DEBaseStructure {
    public DESimpleStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type){
        super(settings, variants, type);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());
        final BlockPos rawPos = getGenPos(context.chunkPos()).above(piece.yOffset);
        final Rotation rotation = Rotation.getRandom(context.random());

        final Vec3i size = context.structureTemplateManager().get(piece.Resource).get().getSize(rotation);

        final Pair<Float, Boolean> result =  DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) {return Optional.empty();}
        final BlockPos pos = rawPos.atY(Math.round(result.getFirst())).above(piece.yOffset);

        return at(pos, (builder)-> generatePieces(builder, pos, piece, rotation, context, DESimpleStructure::assemble));
    }

    protected BlockPos getGenPos(ChunkPos chunkPos){
        return DEUtil.ChunkPosToBlockPos(chunkPos);
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.RuinedBuilding.getPieceType(), structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.RuinedBuilding.getPieceType(), serializationContext, nbt);
        }
    }
}