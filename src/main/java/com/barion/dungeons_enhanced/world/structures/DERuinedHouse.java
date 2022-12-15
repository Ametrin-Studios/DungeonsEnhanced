package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.ExperimentalTerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DERuinedHouse extends DESimpleStructure {
    private static int tries = 0;

    public DERuinedHouse(StructureSettings settings) {
        super(settings, pieceBuilder().offset(-5, 0, -5).weight(3).add("ruined_building/house").offset(-6, 0, -8).weight(2).add("ruined_building/house_big").offset(-4, 0, -5).weight(3).add("ruined_building/barn").build(), DEStructures.RuinedBuilding::getType);
    }

    // Why does it not work!!!!
    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        BlockPos pos = getGenPos(context);

        DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());

        DungeonsEnhanced.LOGGER.info("checked: {} {} {} with size {}", pos.getX(), pos.getY(), pos.getZ(), context.structureTemplateManager().get(piece.Resource).get().getSize());
        tries += 1;
        if(!ExperimentalTerrainAnalyzer.isFlatEnough(pos, context.structureTemplateManager().get(piece.Resource).get().getSize(), 1, 10, context.chunkGenerator(), context.heightAccessor(), context.randomState()).getSecond()){
            if(tries >= 150){
                throw new RuntimeException();
            }
            return Optional.empty();
        }

        tries = 0;
        return onTopOfChunkStart(context, Heightmap.Types.WORLD_SURFACE_WG, (builder)-> generatePieces(builder, context, piece, assembler, pos));
    }

    protected static void generatePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, DEStructurePieces.Piece piece, DEPieceAssembler assembler, BlockPos rawPos) {
        assembler.assemble(new DEPieceAssembler.Context(context.structureTemplateManager(), piece.Resource, rawPos/*.offset(piece.Offset)*/, Rotation.getRandom(context.random()), piecesBuilder));
    }
}