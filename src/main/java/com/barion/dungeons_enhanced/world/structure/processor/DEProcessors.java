package com.barion.dungeons_enhanced.world.structure.processor;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import com.legacy.structure_gel.api.structure.processor.RandomBlockSwapProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.List;

public class DEProcessors {
    public static final RegistrarHandler<StructureProcessorList> HANDLER = RegistrarHandler.getOrCreate(Registries.PROCESSOR_LIST, DungeonsEnhanced.MOD_ID);

    public static final Registrar.Pointer<StructureProcessorList> AIR_TO_COBWEB = HANDLER.createPointer("air_to_cobweb", ()-> listOf(new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB)));


    private static StructureProcessorList listOf(StructureProcessor... processors) {return new StructureProcessorList(List.of(processors));}

    public static class Types {
        public static final StructureProcessorType<DEUnderwaterProcessor> UNDERWATER = () -> DEUnderwaterProcessor.CODEC;
        public static final StructureProcessorType<DESwapDeadCoralsProcessor> SWAP_DEAD_CORALS_PROCESSOR = () -> DESwapDeadCoralsProcessor.CODEC;

        public static void register() {
            register("underwater", UNDERWATER);
        }

        private static <P extends StructureProcessor> void register(String key, StructureProcessorType<P> processorType) {
            Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, DEUtil.location(key), processorType);
        }
    }
}