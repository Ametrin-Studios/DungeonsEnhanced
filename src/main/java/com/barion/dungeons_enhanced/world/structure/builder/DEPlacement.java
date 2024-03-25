package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.gen.GenerationContext;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Optional;

@FunctionalInterface
public interface DEPlacement {
    DEPlacement ON_WORLD_SURFACE = ((context, pieceFactory, templateManager) ->{
        final BlockPos rawPos = DEUtil.ChunkPosToBlockPos(context.chunkPos, 0);
        DESimpleStructurePiece piece = pieceFactory.createPiece(templateManager, rawPos, context.random);

        final Pair<Float, Boolean> result = DETerrainAnalyzer.isFlatEnough(rawPos, piece.getSize(), 1, 4, Heightmap.Type.WORLD_SURFACE_WG, context.chunkGenerator);
        if(!result.getSecond()) {return Optional.empty();}
        piece.setPosition(new BlockPos(rawPos.getX(), Math.round(result.getFirst()), rawPos.getZ()));

        return Optional.of(piece);
    });
    DEPlacement ON_OCEAN_FLOOR = ((context, pieceFactory, templateManager) ->{
        final BlockPos rawPos = DEUtil.ChunkPosToBlockPos(context.chunkPos, 0);
        DESimpleStructurePiece piece = pieceFactory.createPiece(templateManager, rawPos, context.random);

        final Pair<Float, Boolean> result = DETerrainAnalyzer.isFlatEnough(rawPos, piece.getSize(), 1, 4, Heightmap.Type.OCEAN_FLOOR_WG, context.chunkGenerator);
        if(!result.getSecond()) {return Optional.empty();}
        piece.setPosition(new BlockPos(rawPos.getX(), Math.round(result.getFirst()), rawPos.getZ()));

        return Optional.of(piece);
    });
    DEPlacement ABOVE_GROUND = ((context, pieceFactory, templateManager) ->{
        BlockPos rawPos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos, context.chunkGenerator, Heightmap.Type.WORLD_SURFACE_WG);

        if(rawPos.getY() > 224) {return Optional.empty();}

        final int minY = rawPos.getY() + 48;
        final int maxY = 288;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random.nextInt(maxY - minY);}
        final BlockPos pos = new BlockPos(rawPos.getX(), y, rawPos.getZ());

        return Optional.of(pieceFactory.createPiece(templateManager, pos, context.random));
    });

    DEPlacement UNDERGROUND = ((context, pieceFactory, templateManager)->{
        BlockPos groundPos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos, context.chunkGenerator, Heightmap.Type.OCEAN_FLOOR_WG);

        if(groundPos.getY() < 0) {return Optional.empty();}

        final int minY = 8;
        final int maxY = groundPos.getY() - 32;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random.nextInt(maxY - minY);}
        final BlockPos pos = new BlockPos(groundPos.getX(), y, groundPos.getZ());

        return Optional.of(pieceFactory.createPiece(templateManager, pos, context.random));
    });

    Optional<StructurePiece> getPiece(GenerationContext context, IDEPieceFactory pieceFactory, TemplateManager templateManager);

}
