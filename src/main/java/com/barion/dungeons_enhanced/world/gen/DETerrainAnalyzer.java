package com.barion.dungeons_enhanced.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;

public class DETerrainAnalyzer {
    public static TerrainCheckSettings defaultCheckSettings = new TerrainCheckSettings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator) {return isPositionSuitable(chunkPos, chunkGenerator, defaultCheckSettings);}

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, TerrainCheckSettings settings) {
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);

        DETerrainAnalyzer.chunkGenerator = chunkGenerator;

        if(getBlockAt(x, y-1, z) == Blocks.WATER) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed because Water");
            return false;
        }

        int columSpreading = settings.columSpreading;

        if(isColumSuitable(new BlockPos(x+columSpreading, y, z), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }
        if(isColumSuitable(new BlockPos(x-columSpreading, y, z), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }
        if(isColumSuitable(new BlockPos(x, y, z+columSpreading), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }
        if(isColumSuitable(new BlockPos(x, y, z-columSpreading), settings)) {
            //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
            return false;
        }

        //DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " passed");

        return true;
    }

    protected static boolean isColumSuitable(BlockPos pos, TerrainCheckSettings settings){
        int maxRangePerColum = settings.maxRangePerColum;
        int stepSize = settings.stepSize;

        if(!isDownwardsFree(pos, stepSize, maxRangePerColum)){
            return isUpwardsBlocked(pos, stepSize, maxRangePerColum);
        }

        return true;
    }

    protected static boolean isUpwardsBlocked(BlockPos pos, int stepSize, int range){
        for(int i = 1; i <= range; i++){
            if(getBlockAt(pos.getX(), pos.getY() + (i * stepSize), pos.getZ()) != Blocks.AIR) {return true;}
        }

        return false;
    }

    protected static boolean isDownwardsFree(BlockPos pos, int stepSize, int range){
        for(int i = 1; i <= range; i++){
            if(getBlockAt(pos.getX(), pos.getY() - (i * stepSize), pos.getZ()) == Blocks.AIR) {return true;}
        }

        return false;
    }

    protected static Block getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z).getBlockState(new BlockPos(x, y, z)).getBlock();}

    public static class TerrainCheckSettings{
        public final int maxRangePerColum;
        public final int stepSize;
        public final int columSpreading;

        public TerrainCheckSettings(int maxRangePerColum, int stepSize, int columSpreading){
            this.maxRangePerColum = maxRangePerColum;
            this.stepSize = stepSize;
            this.columSpreading = columSpreading;
        }
    }
}