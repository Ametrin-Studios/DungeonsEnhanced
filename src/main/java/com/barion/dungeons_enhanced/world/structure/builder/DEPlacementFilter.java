package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.GenerationContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.Heightmap;

@FunctionalInterface
public interface DEPlacementFilter {
    static DEPlacementFilter DIFFERENCE_OCEAN_FLOOR_MAX(int maxDifference) {
        return (chunkPos, context) -> {
            final BlockPos pos = DEUtil.chunkPosToBlockPosFromHeightMap(chunkPos, context.chunkGenerator, Heightmap.Type.WORLD_SURFACE_WG);
            final int oceanFloor = context.chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Type.OCEAN_FLOOR_WG);
            final int dif = pos.getY() - oceanFloor;

            return dif > maxDifference;
        };
    }

    static DEPlacementFilter DIFFERENCE_OCEAN_FLOOR_MIN(int minDifference) {
        return (chunkPos, context) -> {
            final BlockPos pos = DEUtil.chunkPosToBlockPosFromHeightMap(chunkPos, context.chunkGenerator, Heightmap.Type.WORLD_SURFACE_WG);
            final int oceanFloor = context.chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Type.OCEAN_FLOOR_WG);
            final int dif = pos.getY() - oceanFloor;

            return dif < minDifference;
        };
    }

//    static DEPlacementFilter worldSurfaceLowerThan(int height){
//        return (chunkPos, context) -> {
//            final int worldSurfaceHeight = DEUtil.ChunkPosToBlockPosFromHeightMap(chunkPos, context.chunkGenerator, Heightmap.Type.WORLD_SURFACE_WG).getY();
//            return worldSurfaceHeight < height;
//        };
//    }

    boolean cannotGenerate(ChunkPos chunkPos, GenerationContext context);
}
