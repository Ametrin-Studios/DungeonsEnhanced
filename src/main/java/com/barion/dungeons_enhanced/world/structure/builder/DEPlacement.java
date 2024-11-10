package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Optional;

@FunctionalInterface
public interface DEPlacement {
    DEPlacement ON_WORLD_SURFACE_FLAT = ((context, filter, pieceFactory) ->{
        final var rawPos = DEUtil.chunkPosToBlockPos(context.chunkPos(), 0);
        var piece = pieceFactory.createPiece(context.structureTemplateManager(), rawPos, context.random());

        final var size = piece.getSize();
        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, Heightmap.Types.WORLD_SURFACE_WG, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) { return Optional.empty(); }
        final var pos = rawPos.atY(Math.round(result.getFirst()));
        if(filter.cannotGenerate(pos, context)) { return Optional.empty(); }
        piece.setPosition(pos);

        return Optional.of(new Structure.GenerationStub(pos, (builder)-> builder.addPiece(piece)));
    });
    DEPlacement ON_OCEAN_FLOOR = ((context, filter, pieceFactory) ->{
        final var rawPos = DEUtil.chunkPosToBlockPos(context.chunkPos(), 0);
        var piece = pieceFactory.createPiece(context.structureTemplateManager(), rawPos, context.random());

        final var size = piece.getSize();
        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) { return Optional.empty(); }
        final var pos = rawPos.atY(Math.round(result.getFirst()));
        if(filter.cannotGenerate(pos, context)) { return Optional.empty(); }
        piece.setPosition(pos);

        return Optional.of(new Structure.GenerationStub(pos, (builder)-> builder.addPiece(piece)));
    });
    DEPlacement ABOVE_GROUND = ((context, filter, pieceFactory) ->{
        BlockPos rawPos = DEUtil.chunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

        if(rawPos.getY() > 224) { return Optional.empty(); }

        final int minY = rawPos.getY() + 48;
        final int maxY = 288;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random().nextInt(maxY - minY);}
        final BlockPos pos = rawPos.atY(y);

        if(filter.cannotGenerate(pos, context)) { return Optional.empty(); }
        final var piece = pieceFactory.createPiece(context.structureTemplateManager(), pos, context.random());

        return Optional.of(new Structure.GenerationStub(pos, builder -> builder.addPiece(piece)));
    });

    DEPlacement UNDERGROUND = ((context, filter, pieceFactory)->{
        var groundPos = DEUtil.chunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());

        if(groundPos.getY() < 0) { return Optional.empty(); }

        final int minY = context.chunkGenerator().getMinY() + 8;
        final int maxY = groundPos.getY() - 32;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random().nextInt(maxY - minY);}
        final var pos = groundPos.atY(y);

        if(filter.cannotGenerate(pos, context)) { return Optional.empty(); }
        final var piece = pieceFactory.createPiece(context.structureTemplateManager(), pos, context.random());

        return Optional.of(new Structure.GenerationStub(pos, builder -> builder.addPiece(piece)));
    });

    DEPlacement ON_WORLD_SURFACE = ((context, filter, pieceFactory) -> {
        var pos = DEUtil.chunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

        return Optional.of(new Structure.GenerationStub(pos, builder -> builder.addPiece(pieceFactory.createPiece(context.structureTemplateManager(), pos, context.random()))));
    });

    Optional<Structure.GenerationStub> getPlacement(Structure.GenerationContext context, DEPlacementFilter filter, IDEPieceFactory pieceFactory);
}
