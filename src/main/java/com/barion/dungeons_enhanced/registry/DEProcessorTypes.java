package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructureProcessor;

public final class DEProcessorTypes {
    public static final IStructureProcessorType<DEUnderwaterProcessor> UNDERWATER = register("underwater", DEUnderwaterProcessor.CODEC);
    public static final IStructureProcessorType<DESwapDeadCoralsProcessor> SWAP_DEAD_CORALS_PROCESSOR = register("swap_dead_corals", DESwapDeadCoralsProcessor.CODEC);

    private static <P extends StructureProcessor> IStructureProcessorType<P> register(String key, Codec<P> codec) {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, DEUtil.location(key), () -> codec);
    }
}
