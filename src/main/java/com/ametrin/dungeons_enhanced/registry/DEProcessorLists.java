package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.world.structure.processor.DEPaleMonsterMazeProcessor;
import com.ametrin.structures.processor.RemoveUnsupportedProcessor;
import com.ametrin.structures.processor.ReplaceBlockProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public final class DEProcessorLists {
    public static final ResourceKey<StructureProcessorList> AIR_TO_COBWEB = resourceKey("air_to_cobweb");
    public static final ResourceKey<StructureProcessorList> BLACK_CITADEL = resourceKey("black_citadel/default");
    public static final ResourceKey<StructureProcessorList> CATACOMBS = resourceKey("catacombs");
    public static final ResourceKey<StructureProcessorList> MONSTER_MAZE_DARK = resourceKey("monster_maze/dark");
    public static final ResourceKey<StructureProcessorList> MONSTER_MAZE_PALE = resourceKey("monster_maze/pale");
    public static final ResourceKey<StructureProcessorList> DUNGEON_VARIANT_STONE = resourceKey("dungeon_variant/stone");
    public static final ResourceKey<StructureProcessorList> DUNGEON_VARIANT_DEEPSLATE = resourceKey("dungeon_variant/deepslate");
    public static final ResourceKey<StructureProcessorList> JUNGLE_MONUMENT_DEFAULT = resourceKey("jungle_monument/default");
    public static final ResourceKey<StructureProcessorList> JUNGLE_MONUMENT_AXOLOTL = resourceKey("jungle_monument/axolotl");

    public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
        context.register(AIR_TO_COBWEB, listOf(DEProcessors.AIR_TO_COBWEB_2));
        context.register(BLACK_CITADEL, listOf(DEProcessors.CRACK_BLACKSTONE_10, DEProcessors.CRACK_NETHER_BRICKS_10));
        context.register(CATACOMBS, listOf(DEProcessors.mossifyCobblestone(0.4f), DEProcessors.mossifyCobblestoneStairs(0.4f), DEProcessors.mossifyCobblestoneSlabs(0.4f), DEProcessors.mossifyCobblestoneWalls(0.4f)));
        context.register(MONSTER_MAZE_DARK, listOf(DEProcessors.crackStoneBricks(0.1f), DEBlockVariantsProcessors.conditionalCrackStoneBrickVariants(0.1f), DEProcessors.MOSSY_STONE_BRICKS_30, DEProcessors.MOSSY_STONE_BRICK_STAIRS_30, DEProcessors.MOSSY_STONE_BRICK_SLAB_30, DEProcessors.MOSSY_STONE_BRICK_WALL_30));
        context.register(MONSTER_MAZE_PALE, listOf(DEPaleMonsterMazeProcessor.INSTANCE, RemoveUnsupportedProcessor.INSTANCE, DEProcessors.crackStoneBricks(0.2f), DEBlockVariantsProcessors.conditionalCrackStoneBrickVariants(0.2f)));
        context.register(DUNGEON_VARIANT_STONE, listOf(DEProcessors.mossifyCobblestone(0.4f), DEProcessors.mossifyCobblestoneStairs(0.4f), DEProcessors.mossifyCobblestoneSlabs(0.4f), DEProcessors.crackStoneBricks(0.1f), DEProcessors.MOSSY_STONE_BRICKS_30));
        context.register(DUNGEON_VARIANT_DEEPSLATE, listOf(DEProcessors.CRACK_DEEPSLATE_BRICKS_20, DEProcessors.CRACK_DEEPSLATE_TILES_20));
        context.register(JUNGLE_MONUMENT_DEFAULT, listOf(DEProcessors.mossifyCobblestone(0.4f), DEProcessors.mossifyCobblestoneStairs(0.4f), DEProcessors.mossifyCobblestoneSlabs(0.4f), DEProcessors.mossifyCobblestoneWalls(0.4f)));

        context.register(JUNGLE_MONUMENT_AXOLOTL, listOf(
                new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.MOSSY_STONE_BRICKS), 0.05f, Blocks.CRACKED_STONE_BRICKS.defaultBlockState()),
                new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.MOSSY_STONE_BRICKS), 0.1f, Blocks.STONE_BRICKS.defaultBlockState()),
                new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE), 0.1f, Blocks.TUFF.defaultBlockState()),
                new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_STAIRS), 0.1f, Blocks.TUFF_STAIRS.defaultBlockState(), true),
                new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_SLAB), 0.1f, Blocks.TUFF_SLAB.defaultBlockState(), true),

                DEProcessors.mossifyCobblestone(0.4f), DEProcessors.mossifyCobblestoneStairs(0.4f), DEProcessors.mossifyCobblestoneSlabs(0.4f), DEProcessors.mossifyCobblestoneWalls(0.4f)
        ));
    }

    private static StructureProcessorList listOf(StructureProcessor... processors) {
        return new StructureProcessorList(List.of(processors));
    }

    static ResourceKey<StructureProcessorList> resourceKey(String path) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, DungeonsEnhanced.locate(path));
    }
}
