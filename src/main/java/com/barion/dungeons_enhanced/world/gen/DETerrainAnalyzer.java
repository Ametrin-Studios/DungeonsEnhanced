package com.barion.dungeons_enhanced.world.gen;
// Tool to determine if a surface is suitable for structure generation
// created by BarionLP https://github.com/BarionLP/DungeonsEnhanced/blob/1.18.2/src/main/java/com/barion/dungeons_enhanced/world/gen/DETerrainAnalyzer.java
// version 1.1
// (c) you can only use it if you link the file and give credits to BarionLP

import com.barion.dungeons_enhanced.world.structures.prefabs.DEBaseStructure;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;

public class DETerrainAnalyzer {
    public static Settings defaultSettings = new Settings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, DEBaseStructure.GenerationType generationType) {return isPositionSuitable(chunkPos, chunkGenerator, generationType, defaultSettings);}

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, DEBaseStructure.GenerationType generationType, Settings settings) {
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);

        if(generationType == DEBaseStructure.GenerationType.underground) {return y > 24;}
        if(generationType == DEBaseStructure.GenerationType.inAir) {return y < 182;}

        DETerrainAnalyzer.chunkGenerator = chunkGenerator;

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
        int steps = settings.steps;
        int stepSize = settings.stepSize;

        if(!isDownwardsFree(pos, stepSize, steps)){
            return isUpwardsBlocked(pos, stepSize, steps);
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
}