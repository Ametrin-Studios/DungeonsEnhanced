package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructureTemplates;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;

public final class DEUtil {
    public static ResourceLocation locate(String path) {
        return ResourceLocation.fromNamespaceAndPath(DungeonsEnhanced.MOD_ID, path);
    }

    public static BlockPos chunkPosToBlockPos(ChunkPos chunkPos, int y) {
        return new BlockPos(chunkPos.getMinBlockX(), y, chunkPos.getMinBlockZ());
    }

    public static BlockPos chunkPosToBlockPosFromHeightMap(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Heightmap.Types heightmapType, LevelHeightAccessor heightAccessor, RandomState randomState) {
        BlockPos pos = DEUtil.chunkPosToBlockPos(chunkPos, 0);
        return pos.atY(chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), heightmapType, heightAccessor, randomState));
    }

    public static DEStructureTemplates.Builder pieceBuilder() {
        return new DEStructureTemplates.Builder();
    }
}