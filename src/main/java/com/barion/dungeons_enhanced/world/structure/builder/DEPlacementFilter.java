package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;


@FunctionalInterface
public interface DEPlacementFilter {
    /// Difference between the generation position and the water floor
    static DEPlacementFilter MaxWaterDepth(int maxDifference) {
        return (pos, context) -> {
            final var oceanFloor = context.chunkGenerator().getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());
            final var dif = Math.abs(pos.getY() - oceanFloor);

            return dif > maxDifference;
        };
    }

    /// Difference between the generation position and the water surface
    static DEPlacementFilter MinWaterHeight(int minDifference) {
        return (pos, context) -> {
            final var oceanFloor = context.chunkGenerator().getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
            final var dif = Math.abs(pos.getY() - oceanFloor);

            return dif < minDifference;
        };
    }

    DEPlacementFilter NO_WATER = (pos, context) -> context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState()).getBlock(pos.getY()).is(Blocks.WATER);

    boolean cannotGenerate(BlockPos pos, Structure.GenerationContext context);
}