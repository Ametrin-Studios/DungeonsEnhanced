package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class DEProcessorTypes {
    public static final StructureProcessorType<DEUnderwaterProcessor> UNDERWATER = () -> DEUnderwaterProcessor.CODEC;
    public static final StructureProcessorType<DESwapDeadCoralsProcessor> SWAP_DEAD_CORALS_PROCESSOR = () -> DESwapDeadCoralsProcessor.CODEC;

    public static void register() {
        register("underwater", UNDERWATER);
    }

    private static <P extends StructureProcessor> void register(String key, StructureProcessorType<P> processorType) {
        Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, DEUtil.locate(key), processorType);
    }
}
