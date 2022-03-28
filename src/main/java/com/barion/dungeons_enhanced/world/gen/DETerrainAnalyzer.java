package com.barion.dungeons_enhanced.world.gen;

import com.barion.dungeons_enhanced.world.structures.prefabs.DEBaseStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;

public class DETerrainAnalyzer {
    public static TerrainCheckSettings defaultCheckSettings = new TerrainCheckSettings(1, 3, 3);
    protected static ChunkGenerator chunkGenerator;
    protected static LevelHeightAccessor heightAccessor;

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, DEBaseStructure.GenerationType generationType, LevelHeightAccessor heightAccessor) {return isPositionSuitable(chunkPos, chunkGenerator, generationType, defaultCheckSettings, heightAccessor);}

    public static boolean isPositionSuitable(ChunkPos chunkPos, ChunkGenerator chunkGenerator, DEBaseStructure.GenerationType generationType, TerrainCheckSettings settings, LevelHeightAccessor heightAccessor) {
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int y = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);

        if(generationType == DEBaseStructure.GenerationType.underground) {return y > chunkGenerator.getMinY() + 20;}
        if(generationType == DEBaseStructure.GenerationType.inAir) {return y < (chunkGenerator.getGenDepth() + chunkGenerator.getMinY()) - 80;}

        DETerrainAnalyzer.chunkGenerator = chunkGenerator;
        DETerrainAnalyzer.heightAccessor = heightAccessor;

        if(getBlockAt(x, y-1, z) == Blocks.WATER) {
            //DungeonsEnhanced.LOGGER.info("Canceled at " + new BlockPos(x, y, z) + " because Water");
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

    protected static boolean isColumBlocked(BlockPos pos, TerrainCheckSettings settings){
        int maxRangePerColum = settings.maxRangePerColum();
        int stepSize = settings.stepSize();

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

    protected static Block getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z, heightAccessor).getBlock(y).getBlock();}

    public record TerrainCheckSettings(int maxRangePerColum, int stepSize, int columSpreading) {}
}