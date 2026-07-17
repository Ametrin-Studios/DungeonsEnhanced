package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public final class DEProcessorLists {
    public static final ResourceKey<StructureProcessorList> AIR_TO_COBWEB = resourceKey("air_to_cobweb");
    public static final ResourceKey<StructureProcessorList> BLACK_CITADEL = resourceKey("black_citadel/default");
    public static final ResourceKey<StructureProcessorList> MONSTER_MAZE = resourceKey("monster_maze/default");

    public static void bootstrap(BootstrapContext<StructureProcessorList> context){
        context.register(AIR_TO_COBWEB, listOf(DEProcessors.AIR_TO_COBWEB_2));
        context.register(BLACK_CITADEL, listOf(DEProcessors.CRACK_BLACKSTONE_10, DEProcessors.CRACK_NETHER_BRICKS_10));
        context.register(MONSTER_MAZE, listOf(DEProcessors.MOSSY_STONE_BRICKS_30, DEProcessors.MOSSY_STONE_BRICK_STAIRS_30, DEProcessors.MOSSY_STONE_BRICK_SLAB_30, DEProcessors.MOSSY_STONE_BRICK_WALL_30, DEProcessors.CRACK_STONE_BRICKS_20));
    }

    private static StructureProcessorList listOf(StructureProcessor... processors) {
        return new StructureProcessorList(List.of(processors));
    }

    static ResourceKey<StructureProcessorList> resourceKey(String path) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, DungeonsEnhanced.locate(path));
    }
}
