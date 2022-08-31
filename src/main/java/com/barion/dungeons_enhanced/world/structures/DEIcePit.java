package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEIcePit extends DESimpleStructure {
    private static final ResourceLocation Entrance = location("ice_pit/top");
    public DEIcePit(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().offset(-4, -25, -4).add("ice_pit/var1").add("ice_pit/var2").add("ice_pit/var3").build(), DEIcePit::assembleIcePit, DEStructures.IcePit::getType);}

    @Override
    protected boolean checkLocation(GenerationContext context) {
         return DETerrainAnalyzer.isFlatEnough(context.chunkPos(), context.chunkGenerator(), new DETerrainAnalyzer.Settings(1, 2, 2), context.heightAccessor(), context.randomState());
    }

    private static void assembleIcePit(DEPieceAssembler.Context context) {
        BlockPos pos = context.pos();
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), Entrance, pos, context.rotation()));
        int offsetY = -6;
        if(context.piece().getPath().contains("var3")) {offsetY = -11;}
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), pos.offset(-17, offsetY,-17), context.rotation()));
    }
}