package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public final class DEProcessorLists {
    public static final RegistrarHandler<StructureProcessorList> HANDLER = RegistrarHandler.getOrCreate(Registries.PROCESSOR_LIST, DungeonsEnhanced.MOD_ID);

    public static final Registrar.Pointer<StructureProcessorList> AIR_TO_COBWEB = HANDLER.createPointer("air_to_cobweb", () -> listOf(DEProcessors.AIR_TO_COBWEB_2));
    public static final Registrar.Pointer<StructureProcessorList> BLACK_CITADEL = HANDLER.createPointer("black_citadel/default", () -> listOf(DEProcessors.CRACK_BLACKSTONE_10, DEProcessors.CRACK_NETHER_BRICKS_10));
    public static final Registrar.Pointer<StructureProcessorList> MONSTER_MAZE = HANDLER.createPointer("monster_maze/default", () -> listOf(DEProcessors.MOSSY_STONE_BRICKS_30, DEProcessors.MOSSY_STONE_BRICK_STAIRS_30, DEProcessors.MOSSY_STONE_BRICK_SLAB_30, DEProcessors.MOSSY_STONE_BRICK_WALL_30, DEProcessors.CRACK_STONE_BRICKS_20));


    private static StructureProcessorList listOf(StructureProcessor... processors) {
        return new StructureProcessorList(List.of(processors));
    }
}
