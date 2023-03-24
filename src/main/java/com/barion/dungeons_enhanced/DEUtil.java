package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;

public class DEUtil{
    public static ResourceLocation location(String key){ return new ResourceLocation(DungeonsEnhanced.MOD_ID, key);}

    public static BlockPos ChunkPosToBlockPos(ChunkPos chunkPos) {return ChunkPosToBlockPos(chunkPos, 0);}
    public static BlockPos ChunkPosToBlockPos(ChunkPos chunkPos, int y){
        return new BlockPos(chunkPos.getMinBlockX(), y, chunkPos.getMinBlockZ());
    }

    public static BlockPos ChunkPosToBlockPosFromHeightMap(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Heightmap.Types heightmapType, LevelHeightAccessor heightAccessor, RandomState randomState){
        BlockPos pos = DEUtil.ChunkPosToBlockPos(chunkPos);
        return pos.atY(chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), heightmapType, heightAccessor, randomState));
    }

    public static DEStructurePieces.Builder pieceBuilder() {return new DEStructurePieces.Builder();}
}