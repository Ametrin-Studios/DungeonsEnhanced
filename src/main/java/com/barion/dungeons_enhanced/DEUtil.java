package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.registry.RegistryHelper;
import com.legacy.structure_gel.api.structure.processor.RandomBlockSwapProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class DEUtil{
    public static ResourceLocation createRegistryName(String key){ return new ResourceLocation(DungeonsEnhanced.Mod_ID, key);}
    public static BlockPos Offset(int x, int y, int z){return new BlockPos(x, y, z);}

    public class Processors {
        public static final StructureProcessorList AirToCobweb = register("air_to_cobweb", new RandomBlockSwapProcessor(Blocks.AIR, Blocks.COBWEB));

        private static StructureProcessorList register(String key, StructureProcessor processor){
            return RegistryHelper.registerProcessor(createRegistryName(key), processor);
        }
        private static StructureProcessorList register(String key, StructureProcessorList processorList){
            return RegistryHelper.registerProcessor(createRegistryName(key), processorList);
        }
    }
}