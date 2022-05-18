package com.barion.dungeons_enhanced.world.gen;

// Tool to determine if a surface is suitable for structure generation
// created by BarionLP https://github.com/BarionLP/DungeonsEnhanced/blob/1.18.2/src/main/java/com/barion/dungeons_enhanced/world/gen/DETerrainAnalyzer.java
// version 2.0
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

import java.util.function.Predicate;

public class DETerrainAnalyzer {
    public static Settings defaultCheckSettings = new Settings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;
    protected static LevelHeightAccessor heightAccessor;

    public static boolean isFlatEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor) {return isFlatEnough(chunkPos, chunkGenerator, defaultCheckSettings, heightAccessor);}

    public static boolean isFlatEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Settings settings, LevelHeightAccessor heightAccessor){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);

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

    public static boolean isUnderwater(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int depth, LevelHeightAccessor heightAccessor) {
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        return getBlockAt(x, chunkGenerator.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor) + depth, z).is(Blocks.WATER);
    }

    public static boolean isGroundHighEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int height, LevelHeightAccessor heightAccessor){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
        return y >= chunkGenerator.getMinY() + height;
    }

    public static boolean isGroundLowEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int freeBlocks, LevelHeightAccessor heightAccessor){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
        return y <= (chunkGenerator.getMinY() + chunkGenerator.getGenDepth()) - freeBlocks;
    }

    public static boolean areNearbyBiomesValid(BiomeSource biomeSource, ChunkPos chunkPos, ChunkGenerator generator, int radius, Predicate<Holder<Biome>> validBiome){
        DETerrainAnalyzer.chunkGenerator = generator;
        for(Holder<Biome> biome : biomeSource.getBiomesWithin(chunkPos.getMinBlockX(), generator.getSeaLevel(), chunkPos.getMinBlockZ(), radius, generator.climateSampler())) {
            if (!validBiome.test(biome)) {return false;}
        }
        return true;
    }

    @Deprecated //use specific methods instead
    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, GenerationType generationType, Settings settings, LevelHeightAccessor heightAccessor) {
        if(generationType == GenerationType.onWater) {return true;}
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        if(generationType == GenerationType.underwater) {return getBlockAt(x, chunkGenerator.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor) + 16, z).is(Blocks.WATER);}
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);

        if(generationType == GenerationType.underground) {return y > chunkGenerator.getMinY() + 24;}
        if(generationType == GenerationType.inAir) {return y < (chunkGenerator.getMinY() + chunkGenerator.getGenDepth()) - 72;}

        if(getBlockAt(x, y-1, z).is(Blocks.WATER)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed because Water");
            return false;
        }

        int columSpreading = settings.columSpreading();

        if(isColumBlocked(new BlockPos(x+columSpreading, y, z), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }
        if(isColumBlocked(new BlockPos(x-columSpreading, y, z), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }
        if(isColumBlocked(new BlockPos(x, y, z+columSpreading), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }
        if(isColumBlocked(new BlockPos(x, y, z-columSpreading), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }

        //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " passed");

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

    protected static BlockState getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z, heightAccessor).getBlock(y);}

    public record Settings(int steps, int stepSize, int columSpreading) {}

    public enum GenerationType {onGround, inAir, underground, onWater, underwater}
}