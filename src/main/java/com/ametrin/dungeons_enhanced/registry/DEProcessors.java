package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.structures.processor.ReplaceBlockProcessor;
import net.minecraft.world.level.block.Blocks;

public final class DEProcessors {
    public static final ReplaceBlockProcessor CRACK_BLACKSTONE_10 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.POLISHED_BLACKSTONE_BRICKS), 0.1f, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState());
    public static final ReplaceBlockProcessor CRACK_NETHER_BRICKS_10 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.NETHER_BRICKS), 0.1f, Blocks.CRACKED_NETHER_BRICKS.defaultBlockState());
    public static final ReplaceBlockProcessor AIR_TO_COBWEB_2 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.AIR), 0.02f, Blocks.COBWEB.defaultBlockState());

    public static ReplaceBlockProcessor mossifyCobblestone(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE), chance, Blocks.MOSSY_COBBLESTONE.defaultBlockState());
    }

    public static ReplaceBlockProcessor mossifyCobblestoneStairs(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_STAIRS), chance, Blocks.MOSSY_COBBLESTONE_STAIRS.defaultBlockState(), true);
    }

    public static ReplaceBlockProcessor mossifyCobblestoneSlabs(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_SLAB), chance, Blocks.MOSSY_COBBLESTONE_SLAB.defaultBlockState(), true);
    }

    public static ReplaceBlockProcessor mossifyCobblestoneWalls(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_WALL), chance, Blocks.MOSSY_COBBLESTONE_WALL.defaultBlockState(), true);
    }

    public static final ReplaceBlockProcessor MOSSY_STONE_BRICKS_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICKS), 0.3f, Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_STONE_BRICK_STAIRS_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_STAIRS), 0.3f, Blocks.MOSSY_STONE_BRICK_STAIRS.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_STONE_BRICK_SLAB_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_SLAB), 0.3f, Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_STONE_BRICK_WALL_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_WALL), 0.3f, Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), true);

    public static ReplaceBlockProcessor crackStoneBricks(float chance) {
        return new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICKS), chance, Blocks.CRACKED_STONE_BRICKS.defaultBlockState());
    }

    public static final ReplaceBlockProcessor CRACK_DEEPSLATE_BRICKS_20 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.DEEPSLATE_BRICKS), 0.2f, Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState());
    public static final ReplaceBlockProcessor CRACK_DEEPSLATE_TILES_20 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.DEEPSLATE_TILES), 0.2f, Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState());

}