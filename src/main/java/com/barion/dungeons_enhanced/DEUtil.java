package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePiece;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;

import java.util.Random;

public class DEUtil{
    public static ResourceLocation location(String key) {return new ResourceLocation(DungeonsEnhanced.MOD_ID, key);}

    public static int getRandomPiece(DEStructurePiece[] variants, int maxWeight, Random rand){
        int piece = 0;
        if(variants.length > 1) {
            int i = rand.nextInt(maxWeight+1);
            for (int j = 0; j < variants.length; j++) {
                if (variants[j].Weight >= i) {
                    piece = j;
                    break;
                } else {
                    i -= variants[j].Weight;
                }
            }
        }
        return piece;
    }

    public static int getMaxWeight(DEStructurePiece[] variants){
        int i = 0;
        for (DEStructurePiece piece : variants){
            i += piece.Weight;
        }
        return i;
    }

    public static BlockPos ChunkPosToBlockPos(int chunkX, int chunkY, int y){
        return ChunkPosToBlockPos(new ChunkPos(chunkY, chunkY), y);
    }
    public static BlockPos ChunkPosToBlockPos(ChunkPos chunkPos, int y){
        return new BlockPos(chunkPos.getMinBlockX(), y, chunkPos.getMinBlockZ());
    }
    public static BlockPos ChunkPosToBlockPosFromHeightMap(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Heightmap.Type heightmapType){
        BlockPos pos = DEUtil.ChunkPosToBlockPos(chunkPos, 0);
        return new BlockPos(pos.getX(), chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), heightmapType), pos.getZ());
    }

    public static BlockState withPropertiesOf(Block base, BlockState state) {
        BlockState blockstate = base.defaultBlockState();

        for (Property<?> value : state.getBlock().getStateDefinition().getProperties()) {
            if (blockstate.hasProperty(value)) {
                blockstate = copyProperty(state, blockstate, value);
            }
        }

        return blockstate;
    }
    private static <T extends Comparable<T>> BlockState copyProperty(BlockState sourceState, BlockState targetState, Property<T> property) {
        return targetState.setValue(property, sourceState.getValue(property));
    }

    public static DEStructurePiece.Builder pieceBuilder() {return new DEStructurePiece.Builder();}
}