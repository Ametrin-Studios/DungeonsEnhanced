package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.structures.processor.ConditionalProcessor;
import com.ametrin.structures.processor.ReplaceBlockProcessor;
import com.barion.block_variants.BlockVariants;
import com.barion.block_variants.registry.BVBuildingBlocks;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.List;

public final class DEBlockVariantsProcessors {
    public static ReplaceBlockProcessor crackStoneBrickStairs(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_STAIRS), chance, BVBuildingBlocks.CRACKED_STONE_BRICK_STAIRS.get().defaultBlockState(), true);
    }

    public static ReplaceBlockProcessor crackStoneBrickSlabs(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_SLAB), chance, BVBuildingBlocks.CRACKED_STONE_BRICK_SLAB.get().defaultBlockState(), true);
    }

    public static ReplaceBlockProcessor crackStoneBrickWalls(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_WALL), chance, BVBuildingBlocks.CRACKED_STONE_BRICK_WALL.get().defaultBlockState(), true);
    }

    public static ConditionalProcessor conditionalCrackStoneBrickVariants(float chance) {
        return ConditionalProcessor.of(List.of(new ModLoadedCondition(BlockVariants.MOD_ID)), List.of(crackStoneBrickStairs(chance), crackStoneBrickSlabs(chance), crackStoneBrickWalls(chance)));
    }

}
