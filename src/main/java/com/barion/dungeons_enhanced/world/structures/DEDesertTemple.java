package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEDesertTemple extends DESimpleStructure {
    private static final ResourceLocation Bottom = location("desert_temple/down");

    public DEDesertTemple() {super(DEConfig.COMMON.desert_temple, false, DEDesertTemple::checkLocation, DEDesertTemple::assembleTemple, DEUtil.pieceBuilder().add("desert_temple/main").build());}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        if(DETerrainAnalyzer.areNearbyBiomesValid(context.biomeSource(), context.chunkPos(), context.chunkGenerator(), 20, context.validBiome())){
            return DETerrainAnalyzer.isFlatEnough(context.chunkPos(), context.chunkGenerator(), new DETerrainAnalyzer.Settings(1, 4, 5), context.heightAccessor());
        }
        return false;
    }

    public static void assembleTemple(DEPieceAssembler.Context context) {
        Rotation rotation = Rotation.NONE;
        BlockPos pos = context.pos().offset(-18, -6, -20);
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), context.piece(), pos, rotation));
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), Bottom, pos.offset(15, -11, 2), rotation));
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), Bottom, pos.offset(25, -11, 16), rotation));
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), Bottom, pos.offset(13, -11, 14), rotation));
    }
}