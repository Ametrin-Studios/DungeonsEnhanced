package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.ametrin.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.ametrin.dungeons_enhanced.world.structure.processor.KeepStateRandomBlockSwapProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class DEProcessorTypes {
    public static final StructureProcessorType<DEUnderwaterProcessor> UNDERWATER = () -> DEUnderwaterProcessor.CODEC;
    public static final StructureProcessorType<DESwapDeadCoralsProcessor> SWAP_DEAD_CORALS_PROCESSOR = () -> DESwapDeadCoralsProcessor.CODEC;
    public static final StructureProcessorType<KeepStateRandomBlockSwapProcessor> KEEP_STATE_RANDOM_BLOCK_SWAP = () -> KeepStateRandomBlockSwapProcessor.CODEC;

    public static void register() {
        register("underwater", UNDERWATER);
        register("swap_dead_corals", SWAP_DEAD_CORALS_PROCESSOR);
        register("keep_state_random_block_swap", KEEP_STATE_RANDOM_BLOCK_SWAP);
    }

    private static <P extends StructureProcessor> void register(String key, StructureProcessorType<P> processorType) {
        Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, DungeonsEnhanced.locate(key), processorType);
    }
}
