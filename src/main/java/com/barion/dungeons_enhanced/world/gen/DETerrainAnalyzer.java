package com.barion.dungeons_enhanced.world.gen;

// Tool to determine if a surface is suitable for structure generation
// created by BarionLP https://github.com/BarionLP/DungeonsEnhanced/blob/master/src/main/java/com/barion/dungeons_enhanced/world/gen/DETerrainAnalyzer.java
// version 4.0
// some features require functions from DEUtil. You can just copy them.
// (c) you can only use it if you link the file and give credits to BarionLP

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;

import java.util.function.Predicate;

public class DETerrainAnalyzer {
    public static Settings defaultCheckSettings = new Settings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;
    protected static LevelHeightAccessor heightAccessor;
    protected static RandomState randomState;

    public static boolean isFlatEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, RandomState randomState) {return isFlatEnough(chunkPos, chunkGenerator, defaultCheckSettings, heightAccessor, randomState);}

    public static boolean isFlatEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Settings settings, LevelHeightAccessor heightAccessor, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        DETerrainAnalyzer.randomState = randomState;
        BlockPos pos = ChunkPosToBlockPos(chunkPos, Heightmap.Types.WORLD_SURFACE_WG);

        if(getBlockAt(pos.below()).is(Blocks.WATER)) {return false;}

        int columSpreading = settings.columSpreading();
        if(isColumBlocked(pos.east(columSpreading), settings)) {return false;}
        if(isColumBlocked(pos.west(columSpreading), settings)) {return false;}
        if(isColumBlocked(pos.south(columSpreading), settings)) {return false;}
        return !isColumBlocked(pos.north(columSpreading), settings);
    }

    public static boolean isUnderwater(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int depth, LevelHeightAccessor heightAccessor, RandomState randomState) {
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        DETerrainAnalyzer.randomState = randomState;

        return getBlockAt(ChunkPosToBlockPos(chunkPos, Heightmap.Types.OCEAN_FLOOR_WG).above(depth)).is(Blocks.WATER);
    }

    public static boolean isGroundLevelAbove(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int height, LevelHeightAccessor heightAccessor, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        DETerrainAnalyzer.randomState = randomState;

        return getSurfaceLevelAt(chunkPos, Heightmap.Types.WORLD_SURFACE_WG) > height;
    }

    public static boolean isGroundLevelBelow(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int height, LevelHeightAccessor heightAccessor, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        DETerrainAnalyzer.randomState = randomState;

        return getSurfaceLevelAt(chunkPos, Heightmap.Types.WORLD_SURFACE_WG) < height;
    }

    public static boolean areNearbyBiomesValid(BiomeSource biomeSource, ChunkPos chunkPos, ChunkGenerator generator, int radius, Predicate<Holder<Biome>> validBiome, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = generator;
        for(Holder<Biome> biome : biomeSource.getBiomesWithin(chunkPos.getMiddleBlockX(), generator.getSeaLevel(), chunkPos.getMiddleBlockZ(), radius, randomState.sampler())) {
            if (!validBiome.test(biome)) {return false;}
        }
        return true;
    }

    protected static boolean isColumBlocked(BlockPos pos, Settings settings){
        if(!isDownwardsFree(pos, settings.stepSize(), settings.steps())){
            return isUpwardsBlocked(pos, settings.stepSize(), settings.steps());
        }
        return true;
    }

    protected static boolean isUpwardsBlocked(BlockPos pos, int stepSize, int steps){
        for(int i = 1; i <= steps; i++){
            if(!getBlockAt(pos.above(i * stepSize)).isAir()) {return true;}
        }
        return false;
    }

    protected static boolean isDownwardsFree(BlockPos pos, int stepSize, int steps){
        for(int i = 1; i <= steps; i++){
            if(getBlockAt(pos.below(i * stepSize)).isAir()) {return true;}
        }
        return false;
    }

    protected static BlockState getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z, heightAccessor, randomState).getBlock(y);}
    protected static BlockState getBlockAt(BlockPos pos) {return chunkGenerator.getBaseColumn(pos.getX(), pos.getZ(), heightAccessor, randomState).getBlock(pos.getY());}
    protected static BlockPos ChunkPosToBlockPos(ChunkPos chunkPos, Heightmap.Types heightmapType) {return DEUtil.ChunkPosToBlockPosFromHeightMap(chunkPos, chunkGenerator, heightmapType, heightAccessor, randomState);}

    protected static int getSurfaceLevelAt(ChunkPos pos, Heightmap.Types heightmapType){
        return ChunkPosToBlockPos(pos, heightmapType).getY();
    }
    protected static int getSurfaceLevelAt(BlockPos pos, Heightmap.Types heightmapType){
        return chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), heightmapType, heightAccessor, randomState);
    }

    public record Settings(int steps, int stepSize, int columSpreading) {}

    @Deprecated(forRemoval = true) public enum GenerationType {onGround, inAir, underground, onWater, underwater}
}