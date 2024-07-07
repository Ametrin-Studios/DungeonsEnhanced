package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.world.structure.processor.KeepStateRandomBlockSwapProcessor;
import com.legacy.structure_gel.api.structure.processor.RandomBlockSwapProcessor;
import net.minecraft.world.level.block.Blocks;

public final class DEProcessors {
    public static final RandomBlockSwapProcessor CRACK_BLACKSTONE_10 = new RandomBlockSwapProcessor(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.1f, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    public static final RandomBlockSwapProcessor CRACK_NETHER_BRICKS_10 = new RandomBlockSwapProcessor(Blocks.NETHER_BRICKS, 0.1f, Blocks.CRACKED_NETHER_BRICKS);
    public static final RandomBlockSwapProcessor AIR_TO_COBWEB_2 = new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB);

    public static final RandomBlockSwapProcessor MOSSY_COBBLESTONE_40 = new RandomBlockSwapProcessor(Blocks.COBBLESTONE, 0.4f, Blocks.MOSSY_COBBLESTONE);
    public static final KeepStateRandomBlockSwapProcessor MOSSY_COBBLESTONE_STAIRS_40 = new KeepStateRandomBlockSwapProcessor(Blocks.COBBLESTONE_STAIRS, 0.4f, Blocks.MOSSY_COBBLESTONE_STAIRS);
    public static final KeepStateRandomBlockSwapProcessor MOSSY_COBBLESTONE_SLAB_40 = new KeepStateRandomBlockSwapProcessor(Blocks.COBBLESTONE_SLAB, 0.4f, Blocks.MOSSY_COBBLESTONE_SLAB);

    public static final RandomBlockSwapProcessor MOSSY_STONE_BRICKS_30 = new RandomBlockSwapProcessor(Blocks.STONE_BRICKS, 0.3f, Blocks.MOSSY_STONE_BRICKS);
    public static final RandomBlockSwapProcessor CRACK_STONE_BRICKS_20 = new RandomBlockSwapProcessor(Blocks.STONE_BRICKS, 0.2f, Blocks.CRACKED_STONE_BRICKS);

}