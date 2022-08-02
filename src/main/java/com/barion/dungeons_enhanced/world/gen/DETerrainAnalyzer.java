package com.barion.dungeons_enhanced.world.gen;

// Tool to determine if a surface is suitable for structure generation
// created by BarionLP https://github.com/BarionLP/DungeonsEnhanced/blob/master/src/main/java/com/barion/dungeons_enhanced/world/gen/DETerrainAnalyzer.java
// version 3.0
// (c) you can only use it if you link the file and give credits to BarionLP

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
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState);

        if(getBlockAt(x, y-1, z).is(Blocks.WATER)) {
            return false;
        }

        int columSpreading = settings.columSpreading();

        if(isColumBlocked(new BlockPos(x+columSpreading, y, z), settings)) {
            return false;
        }
        if(isColumBlocked(new BlockPos(x-columSpreading, y, z), settings)) {
            return false;
        }
        if(isColumBlocked(new BlockPos(x, y, z+columSpreading), settings)) {
            return false;
        }
        return !isColumBlocked(new BlockPos(x, y, z - columSpreading), settings);
    }

    public static boolean isUnderwater(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int depth, LevelHeightAccessor heightAccessor, RandomState randomState) {
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        return getBlockAt(x, chunkGenerator.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState) + depth, z).is(Blocks.WATER);
    }

    public static boolean isGroundHighEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int height, LevelHeightAccessor heightAccessor, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState);
        return y >= chunkGenerator.getMinY() + height;
    }

    public static boolean isGroundLowEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int freeBlocks, LevelHeightAccessor heightAccessor, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState);
        return y <= (chunkGenerator.getMinY() + chunkGenerator.getGenDepth()) - freeBlocks;
    }

    public static boolean areNearbyBiomesValid(BiomeSource biomeSource, ChunkPos chunkPos, ChunkGenerator generator, int radius, Predicate<Holder<Biome>> validBiome, RandomState randomState){
        DETerrainAnalyzer.chunkGenerator = generator;
        for(Holder<Biome> biome : biomeSource.getBiomesWithin(chunkPos.getMinBlockX(), generator.getSeaLevel(), chunkPos.getMinBlockZ(), radius, randomState.sampler())) {
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
            if(!getBlockAt(pos.getX(), pos.getY() + (i * stepSize), pos.getZ()).isAir()) {return true;}
        }
        return false;
    }

    protected static boolean isDownwardsFree(BlockPos pos, int stepSize, int steps){
        for(int i = 1; i <= steps; i++){
            if(getBlockAt(pos.getX(), pos.getY() - (i * stepSize), pos.getZ()).isAir()) {return true;}
        }
        return false;
    }

    protected static BlockState getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z, heightAccessor, randomState).getBlock(y);}

    public record Settings(int steps, int stepSize, int columSpreading) {}

    public enum GenerationType {onGround, inAir, underground, onWater, underwater}
}