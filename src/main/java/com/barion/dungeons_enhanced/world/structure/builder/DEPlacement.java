package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Optional;

@FunctionalInterface
public interface DEPlacement {
    DEPlacement DEFAULT_ON_GROUND = ((context, pieceFactory) ->{
        final var rawPos = DEUtil.ChunkPosToBlockPos(context.chunkPos(), 0);
        var piece = pieceFactory.createPiece(context.structureTemplateManager(), rawPos, context.random());

        final var size = piece.getSize();
        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) {return Optional.empty();}
        final var pos = rawPos.atY(Math.round(result.getFirst()));
        piece.setPosition(pos);

        return Optional.of(new Structure.GenerationStub(pos, (builder)-> builder.addPiece(piece)));
    });
    Optional<Structure.GenerationStub> getPlacement(Structure.GenerationContext context, IDETemplatePieceFactory pieceFactory);
}
