package com.barion.dungeons_enhanced.world.gen;
// Tool to determine if a surface is suitable for structure generation
// created by BarionLP https://github.com/BarionLP/DungeonsEnhanced/blob/1.18.2/src/main/java/com/barion/dungeons_enhanced/world/gen/DETerrainAnalyzer.java
// version 1.1
// (c) you can only use it if you link the file and give credits to BarionLP

import com.legacy.structure_gel.util.ConfigTemplates;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;

public class DETerrainAnalyzer {
    public static Settings defaultSettings = new Settings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;

    public static boolean isFlatEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator) {return isFlatEnough(chunkPos, chunkGenerator, defaultSettings);}

    public static boolean isFlatEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Settings settings){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);

        if(getBlockAt(x, y-1, z).is(Blocks.WATER)) {
            return false;
        }

        int columSpreading = settings.columSpreading;

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

    public static boolean isUnderwater(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int depth) {
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        return getBlockAt(x, chunkGenerator.getBaseHeight(x, z, Heightmap.Type.OCEAN_FLOOR_WG) + depth, z).is(Blocks.WATER);
    }

    public static boolean isGroundHighEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int height){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
        return y >= height;
    }

    public static boolean isGroundLowEnough(ChunkPos chunkPos, ChunkGenerator chunkGenerator, int freeBlocks){
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
        return y <= chunkGenerator.getGenDepth() - freeBlocks;
    }

    public static boolean areNearbyBiomesValid(BiomeProvider biomeProvider, ChunkPos chunkPos, ChunkGenerator generator, int radius, ConfigTemplates.StructureConfig config){
        DETerrainAnalyzer.chunkGenerator = generator;
        for(Biome biome : biomeProvider.getBiomesWithin(chunkPos.getMinBlockX(), generator.getSeaLevel(), chunkPos.getMinBlockZ(), radius)) {
            if (!config.isBiomeAllowed(biome)) {return false;}
        }
        return true;
    }

    @Deprecated //use specific methods instead
    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, GenerationType generationType, Settings settings) {
        if(generationType == GenerationType.onWater) {return true;}
        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        if(generationType == GenerationType.underwater) {return getBlockAt(x, chunkGenerator.getBaseHeight(x, z, Heightmap.Type.OCEAN_FLOOR_WG) + 16, z).is(Blocks.WATER);}
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);

        if(generationType == GenerationType.underground) {return y > 24;}
        if(generationType == GenerationType.inAir) {return y < (chunkGenerator.getGenDepth()) - 72;}

        if(getBlockAt(x, y-1, z).is(Blocks.WATER)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed because Water");
            return false;
        }

        int columSpreading = settings.columSpreading;

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
        if(!isDownwardsFree(pos, settings.stepSize, settings.steps)){
            return isUpwardsBlocked(pos, settings.stepSize, settings.steps);
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

    protected static BlockState getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z).getBlockState(new BlockPos(x, y, z));}

    public static class Settings{
        public final int steps;
        public final int stepSize;
        public final int columSpreading;
        public Settings(int steps, int stepSize, int columSpreading){
            this.steps = steps;
            this.stepSize = stepSize;
            this.columSpreading = columSpreading;
        }
    }

    public enum GenerationType {onGround, inAir, underground, onWater, underwater}
}