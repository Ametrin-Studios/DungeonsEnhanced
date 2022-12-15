package com.barion.dungeons_enhanced.world.gen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;

public class ExperimentalTerrainAnalyzer {
    public static boolean isFlatEnough(BlockPos pos, ChunkGenerator generator, LevelHeightAccessor heightAccessor, RandomState randomState){
        if(generator instanceof FlatLevelSource) {return true;}

        return isFlatAt(pos, generator, heightAccessor, randomState);
    }

    /**
     * @return whether the average height difference between the corner points are smaller than the threshold and the average height, always true on {@link FlatLevelSource}
     */
    public static Pair<Float, Boolean> isFlatEnough(BlockPos pos, Vec3i size, int padding, int threshold, ChunkGenerator generator, LevelHeightAccessor heightAccessor, RandomState randomState){
        if(generator instanceof FlatLevelSource) {return Pair.of((float)generator.getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState), true);}
        if(!(heightAccessor instanceof ServerLevel level)) {return Pair.of(0f, false);}

        int x1 = pos.getX()+padding;
        int x2 = pos.getX()+size.getX()-padding;
        int z1 = pos.getZ()+padding;
        int z2 = pos.getZ()+size.getZ()-padding;


        int height1 = generator.getBaseHeight(x1, z1, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        level.setBlockAndUpdate(new BlockPos(x1, height1, z1), Blocks.RED_WOOL.defaultBlockState());
//        Ametrin.Logger.info("Testing at: {} {} {}", x1, pos.getY(), z1);
        int height2 = generator.getBaseHeight(x2, z1, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        level.setBlockAndUpdate(new BlockPos(x2, height2, z1), Blocks.RED_WOOL.defaultBlockState());
//        Ametrin.Logger.info("Testing at: {} {} {}", x2, pos.getY(), z1);
        int height3 = generator.getBaseHeight(x2, z2, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        level.setBlockAndUpdate(new BlockPos(x2, height3, z2), Blocks.RED_WOOL.defaultBlockState());
//        Ametrin.Logger.info("Testing at: {} {} {}", x2, pos.getY(), z2);
        int height4 = generator.getBaseHeight(x1, z2, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        level.setBlockAndUpdate(new BlockPos(x1, height4, z2), Blocks.RED_WOOL.defaultBlockState());
//        Ametrin.Logger.info("Testing at: {} {} {}", x1, pos.getY(), z2);

        float averageHeight = (height1+height2+height3+height4)/4f;
        float averageHeightDifference = (Math.abs(averageHeight-height1)+Math.abs(averageHeight-height2)+Math.abs(averageHeight-height3)+Math.abs(averageHeight-height4))/4f;
        return Pair.of(averageHeight, averageHeightDifference < threshold);
    }

    /** concept from SurfaceRules.Context.SteepMaterialCondition#compute() */
    protected static boolean isFlatAt(BlockPos pos, ChunkGenerator generator, LevelHeightAccessor heightAccessor, RandomState randomState){
        int maxZHeight = generator.getBaseHeight(pos.getX(), pos.getZ()+1, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        int minZHeight = generator.getBaseHeight(pos.getX(), pos.getZ()-1, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        if(Math.abs(maxZHeight-minZHeight)>3) {return false;}

        int maxXHeight = generator.getBaseHeight(pos.getX()+1, pos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        int minXHeight = generator.getBaseHeight(pos.getX()-1, pos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
        return Math.abs(maxXHeight-minXHeight)<=3;
    }

    /** not precise enough */
    public static double getErosionAt(BlockPos pos, RandomState randomState){
        return randomState.router().erosion().compute(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()));
    }
}