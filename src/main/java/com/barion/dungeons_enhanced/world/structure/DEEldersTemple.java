package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEBaseStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEUnderwaterStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEEldersTemple extends DEUnderwaterStructure {
    public static final String ID = "elders_temple";
    public static final Codec<DEEldersTemple> CODEC = simpleCodec(DEEldersTemple::new);
    private static final ResourceLocation NE = location("elders_temple/ne");
    private static final ResourceLocation NW = location("elders_temple/nw");
    private static final ResourceLocation SE = location("elders_temple/se");
    private static final ResourceLocation SW = location("elders_temple/sw");

    public DEEldersTemple(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().add("elders_temple/se").build(), DEStructures.ELDERS_TEMPLE::getType);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final BlockPos pos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());

        if(!DETerrainAnalyzer.isUnderwater(pos, context.chunkGenerator(), 32, context.heightAccessor(), context.randomState())) {return Optional.empty();}
        if(!DETerrainAnalyzer.areNearbyBiomesValid(context.biomeSource(), pos, context.chunkGenerator(), 30, context.validBiome(), context.randomState())) {return Optional.empty();}

        return at(pos, (builder) -> generatePieces(builder, pos, Templates.getRandom(context.random()), Rotation.getRandom(context.random()), context, DEEldersTemple::assembleTemple));
    }

    public static void assembleTemple(DEPieceAssembler.Context context) {
        var rotation = Rotation.NONE;
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), SE, context.pos().offset(0,0,0), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), SW, context.pos().offset(-30, 0, 0), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), NE, context.pos().offset(0, 0, -29), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), NW, context.pos().offset(-30, 0, -29), rotation));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.ELDERS_TEMPLE.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.ELDERS_TEMPLE.getPieceType(), serializationContext, nbt);
        }

        @Override
        protected void addProcessors(StructurePlaceSettings settings) {
            settings.clearProcessors();
            settings.addProcessor(DEUnderwaterProcessor.INSTANCE)
                    .addProcessor(DESwapDeadCoralsProcessor.INSTANCE);
        }
    }
}