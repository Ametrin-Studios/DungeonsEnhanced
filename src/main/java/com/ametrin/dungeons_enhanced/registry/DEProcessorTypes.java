package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//import com.ametrin.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;

public final class DEProcessorTypes {
    public static final DeferredRegister<StructureProcessorType<?>> REGISTER = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, DungeonsEnhanced.MOD_ID);

    public static final Supplier<StructureProcessorType<DESwapDeadCoralsProcessor>> SWAP_DEAD_CORALS_PROCESSOR = REGISTER.register("swap_dead_corals", () -> () -> DESwapDeadCoralsProcessor.CODEC);
}
