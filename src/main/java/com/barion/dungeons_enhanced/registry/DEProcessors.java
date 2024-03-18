package com.barion.dungeons_enhanced.registry;

import com.legacy.structure_gel.api.structure.processor.RandomBlockSwapProcessor;
import net.minecraft.world.level.block.Blocks;

public final class DEProcessors {
    public static final RandomBlockSwapProcessor CRACK_BLACKSTONE_10 = new RandomBlockSwapProcessor(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.1f, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    public static final RandomBlockSwapProcessor CRACK_NETHER_BRICKS_10 = new RandomBlockSwapProcessor(Blocks.NETHER_BRICKS, 0.1f, Blocks.CRACKED_NETHER_BRICKS);
    public static final RandomBlockSwapProcessor AIR_TO_COBWEB_2 = new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB);

}