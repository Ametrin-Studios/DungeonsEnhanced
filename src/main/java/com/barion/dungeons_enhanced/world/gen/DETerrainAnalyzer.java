package com.barion.dungeons_enhanced.world.gen;

// Tool to determine if a surface is suitable for structure generation
// created by BarionLP https://github.com/BarionLP/DungeonsEnhanced/blob/1.18.2/src/main/java/com/barion/dungeons_enhanced/world/gen/DETerrainAnalyzer.java
// version 1.1
// (c) you can only use it if you link the file and give credits to BarionLP

import com.barion.dungeons_enhanced.world.structures.prefabs.DEBaseStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;

public class DETerrainAnalyzer {
    public static Settings defaultCheckSettings = new Settings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;
    protected static LevelHeightAccessor heightAccessor;

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, DEBaseStructure.GenerationType generationType, LevelHeightAccessor heightAccessor) {return isPositionSuitable(chunkPos, chunkGenerator, generationType, defaultCheckSettings, heightAccessor);}

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, DEBaseStructure.GenerationType generationType, Settings settings, LevelHeightAccessor heightAccessor) {
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);

        if(generationType == DEBaseStructure.GenerationType.underground) {return y > chunkGenerator.getMinY() + 24;}
        if(generationType == DEBaseStructure.GenerationType.inAir) {return y < (chunkGenerator.getMinY() + chunkGenerator.getGenDepth()) - 72;}

        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;

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
        int steps = settings.steps();
        int stepSize = settings.stepSize();

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

    protected static BlockState getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z, heightAccessor).getBlock(y);}

    public record Settings(int steps, int stepSize, int columSpreading) {}
}