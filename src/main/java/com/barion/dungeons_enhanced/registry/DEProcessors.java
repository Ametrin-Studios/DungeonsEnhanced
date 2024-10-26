package com.barion.dungeons_enhanced.registry;

import com.legacy.structure_gel.util.RegistryHelper;
import com.legacy.structure_gel.worldgen.processors.RandomBlockSwapProcessor;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;

import static com.barion.dungeons_enhanced.DEUtil.locate;

public final class DEProcessors {
    public static final StructureProcessorList AIR_TO_COBWEB = register("air_to_cobweb", new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB));
    public static final StructureProcessor BRAIN_CORAL = new RandomBlockSwapProcessor(Blocks.DEAD_BRAIN_CORAL_BLOCK, 1, Blocks.BRAIN_CORAL_BLOCK);
    public static final StructureProcessor FIRE_CORAL = new RandomBlockSwapProcessor(Blocks.DEAD_FIRE_CORAL_BLOCK, 1, Blocks.FIRE_CORAL_BLOCK);
    public static final StructureProcessor BUBBLE_CORAL = new RandomBlockSwapProcessor(Blocks.DEAD_BUBBLE_CORAL_BLOCK, 1, Blocks.BUBBLE_CORAL_BLOCK);
    public static final StructureProcessor HORN_CORAL = new RandomBlockSwapProcessor(Blocks.DEAD_HORN_CORAL_BLOCK, 1, Blocks.HORN_CORAL_BLOCK);
    public static final StructureProcessor TUBE_CORAL = new RandomBlockSwapProcessor(Blocks.DEAD_TUBE_CORAL_BLOCK, 1, Blocks.TUBE_CORAL_BLOCK);

    private static StructureProcessorList register(String key, StructureProcessor processor){
        return RegistryHelper.registerProcessor(locate(key), processor);
    }
    private static StructureProcessorList register(String key, StructureProcessorList processorList){
        return RegistryHelper.registerProcessor(locate(key), processorList);
    }
}
