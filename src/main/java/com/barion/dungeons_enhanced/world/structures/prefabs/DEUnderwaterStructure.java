package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePieces;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEUnderwaterProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

public class DEUnderwaterStructure extends DEBaseStructure {
    public DEUnderwaterStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type) {super(settings, variants, type);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());
        final BlockPos pos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()).above(piece.yOffset);

        if(!DETerrainAnalyzer.isUnderwater(pos, context.chunkGenerator(), 16, context.heightAccessor(), context.randomState())) {return Optional.empty();}

        return at(pos, (builder) -> generatePieces(builder, pos, piece, Rotation.getRandom(context.random()), context, DEUnderwaterStructure::assemble));
    }

    private static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.SunkenShrine.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.SunkenShrine.getPieceType(), serializationContext, nbt);
        }

        @Override
        protected void addProcessors(StructurePlaceSettings settings) {
            settings.clearProcessors();
            settings.addProcessor(DEUnderwaterProcessor.Instance);
        }
    }
}