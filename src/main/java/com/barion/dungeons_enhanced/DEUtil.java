package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.util.RegistryHelper;
import com.legacy.structure_gel.worldgen.processors.RandomBlockSwapProcessor;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;

import java.util.Random;

public class DEUtil{
    public static ResourceLocation createRegistryName(String key){ return new ResourceLocation(DungeonsEnhanced.Mod_ID, key);}
    public static BlockPos Offset(int x, int y, int z){return new BlockPos(x, y, z);}

    public static class Processors {
        public static final StructureProcessorList AirToCobweb = register("air_to_cobweb", new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB));

        private static StructureProcessorList register(String key, StructureProcessor processor){
            return RegistryHelper.registerProcessor(createRegistryName(key), processor);
        }
        private static StructureProcessorList register(String key, StructureProcessorList processorList){
            return RegistryHelper.registerProcessor(createRegistryName(key), processorList);
        }
    }

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
}