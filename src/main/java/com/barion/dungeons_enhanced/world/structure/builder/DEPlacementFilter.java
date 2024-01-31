package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;


@FunctionalInterface
public interface DEPlacementFilter {
    DEPlacementFilter DIFFERENCE_OCEAN_FLOOR =  (pos, context)-> {
        final var oceanFloor = context.chunkGenerator().getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());
        final var dif = pos.getY() - oceanFloor;

        return dif > 4;
    };

    boolean cannotGenerate(BlockPos pos, Structure.GenerationContext context);
}