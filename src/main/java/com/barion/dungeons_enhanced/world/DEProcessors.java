package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEUnderwaterProcessor;
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
    private static final RegistrarHandler<StructureProcessorList> Handler = RegistrarHandler.getOrCreate(Registries.PROCESSOR_LIST, DungeonsEnhanced.MOD_ID);

    public static final Registrar.Pointer<StructureProcessorList> AirToCobweb = Handler.createPointer("air_to_cobweb", ()-> listOf(new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB)));

    public static final StructureProcessor BrainCoral = new RandomBlockSwapProcessor(Blocks.DEAD_BRAIN_CORAL_BLOCK, 1, Blocks.BRAIN_CORAL_BLOCK);
    public static final StructureProcessor FireCoral = new RandomBlockSwapProcessor(Blocks.DEAD_FIRE_CORAL_BLOCK, 1, Blocks.FIRE_CORAL_BLOCK);
    public static final StructureProcessor BubbleCoral = new RandomBlockSwapProcessor(Blocks.DEAD_BUBBLE_CORAL_BLOCK, 1, Blocks.BUBBLE_CORAL_BLOCK);
    public static final StructureProcessor HornCoral = new RandomBlockSwapProcessor(Blocks.DEAD_HORN_CORAL_BLOCK, 1, Blocks.HORN_CORAL_BLOCK);
    public static final StructureProcessor TubeCoral = new RandomBlockSwapProcessor(Blocks.DEAD_TUBE_CORAL_BLOCK, 1, Blocks.TUBE_CORAL_BLOCK);

    private static StructureProcessorList listOf(StructureProcessor... processors) {return new StructureProcessorList(List.of(processors));}

    public static class Types {
        public static final StructureProcessorType<DEUnderwaterProcessor> Underwater = () -> DEUnderwaterProcessor.CODEC;

        public static void register() {
            register("underwater", Underwater);
        }

        private static <P extends StructureProcessor> void register(String key, StructureProcessorType<P> processorType) {
            Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, DEUtil.location(key), processorType);
        }
    }
}