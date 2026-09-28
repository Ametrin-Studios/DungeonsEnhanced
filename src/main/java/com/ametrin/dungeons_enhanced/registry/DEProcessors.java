package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.structures.processor.ReplaceBlockProcessor;
import net.minecraft.world.level.block.Blocks;

public final class DEProcessors {
    public static final ReplaceBlockProcessor CRACK_BLACKSTONE_10 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.POLISHED_BLACKSTONE_BRICKS), 0.1f, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState());
    public static final ReplaceBlockProcessor CRACK_NETHER_BRICKS_10 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.NETHER_BRICKS), 0.1f, Blocks.CRACKED_NETHER_BRICKS.defaultBlockState());
    public static final ReplaceBlockProcessor AIR_TO_COBWEB_2 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.AIR), 0.02f, Blocks.COBWEB.defaultBlockState());

    public static final ReplaceBlockProcessor MOSSY_COBBLESTONE_40 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE), 0.4f, Blocks.MOSSY_COBBLESTONE.defaultBlockState());
    public static final ReplaceBlockProcessor MOSSY_COBBLESTONE_STAIRS_40 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_STAIRS), 0.4f, Blocks.MOSSY_COBBLESTONE_STAIRS.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_COBBLESTONE_SLAB_40 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_SLAB), 0.4f, Blocks.MOSSY_COBBLESTONE_SLAB.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_COBBLESTONE_WALL_40 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.COBBLESTONE_WALL), 0.4f, Blocks.MOSSY_COBBLESTONE_WALL.defaultBlockState(), true);

    public static final ReplaceBlockProcessor MOSSY_STONE_BRICKS_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICKS), 0.3f, Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_STONE_BRICK_STAIRS_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_STAIRS), 0.3f, Blocks.MOSSY_STONE_BRICK_STAIRS.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_STONE_BRICK_SLAB_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_SLAB), 0.3f, Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState(), true);
    public static final ReplaceBlockProcessor MOSSY_STONE_BRICK_WALL_30 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICK_WALL), 0.3f, Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), true);
    public static final ReplaceBlockProcessor CRACK_STONE_BRICKS_20 = new ReplaceBlockProcessor(ReplaceBlockProcessor.Condition.of(Blocks.STONE_BRICKS), 0.2f, Blocks.CRACKED_STONE_BRICKS.defaultBlockState());

}